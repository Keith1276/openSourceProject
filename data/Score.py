# 得分计算
import pymysql
import ast
import math

conn = pymysql.connect(
    host='localhost',
    user='root',
    password='22371468',
    database='open_source'
)
cursor = conn.cursor()

# 增加score列
cursor.execute('SHOW COLUMNS FROM user')
columns = cursor.fetchall()
column_names = [column[0] for column in columns]
if 'score' not in column_names:
    cursor.execute('ALTER TABLE user ADD COLUMN score FLOAT')

cursor.execute('SHOW COLUMNS FROM repository')
columns = cursor.fetchall()
column_names = [column[0] for column in columns]
if 'score' not in column_names:
    cursor.execute('ALTER TABLE repository ADD COLUMN score FLOAT')

# 取出原始数据
cursor.execute('''
    SELECT id, followers, public_repos
    FROM user
''')
users = cursor.fetchall()
cursor.execute('''
    SELECT id, owner_id, stargazers_count, forks_count, open_issues_count, license
    FROM repository
''')
repositories = cursor.fetchall()

# 仓库裸分
repo_qualities = []
for repo in repositories:
    repo_id, owner_id, stargazers_count, forks_count, open_issues_count, license = repo
    repo_quality = forks_count * 1.2 + stargazers_count
    if license != 'null':
        repo_quality += 1.2 * math.sqrt(open_issues_count)
    else:
        repo_quality += 0.8 * math.sqrt(open_issues_count)
    repo_qualities.append((repo_id, owner_id, repo_quality))

# 仓库裸分归一化
min_quality = min(repo_qualities, key=lambda x: x[2])[2]
max_quality = max(repo_qualities, key=lambda x: x[2])[2]
normalized_repo_qualities = []
for i, (repo_id, owner_id, repo_quality) in enumerate(repo_qualities):
    normalized_repo_quality = (repo_quality - min_quality) / (max_quality - min_quality)
    normalized_repo_qualities.append((repo_id, owner_id, normalized_repo_quality))

# 开发者平均质量 & 仓库总质量
user_average_qualities = []
repo_total_qualities = []
for user in users:
    user_id, followers, public_repos = user
    if public_repos == 0:
        user_average_quality = 0
    else:
        user_average_quality = followers / (math.log(public_repos) + 1)
    user_average_qualities.append((user_id, user_average_quality))
    repo_total_quality = 0
    for i, (repo_id, owner_id, normalized_repo_quality) in enumerate(normalized_repo_qualities):
        if owner_id == user_id:
            repo_total_quality += normalized_repo_quality
    repo_total_qualities.append((user_id, repo_total_quality))

# 开发者平均质量归一化
min_quality = min(user_average_qualities, key=lambda x: x[1])[1]
max_quality = max(user_average_qualities, key=lambda x: x[1])[1]
normalized_user_average_qualities = []
for i, (user_id, user_average_quality) in enumerate(user_average_qualities):
    normalized_user_average_quality = (user_average_quality - min_quality) / (max_quality - min_quality)
    normalized_user_average_qualities.append((user_id, normalized_user_average_quality))

# 仓库总质量归一化
min_quality = min(repo_total_qualities, key=lambda x: x[1])[1]
max_quality = max(repo_total_qualities, key=lambda x: x[1])[1]
normalized_repo_total_qualities = []
for i, (user_id, repo_total_quality) in enumerate(repo_total_qualities):
    normalized_repo_total_quality = (repo_total_quality - min_quality) / (max_quality - min_quality)
    normalized_repo_total_qualities.append((user_id, normalized_repo_total_quality))

# 开发者得分
user_scores = []
for user in users:
    user_id, followers, public_repos = user
    user_average_quality = next((user_quality for uid, user_quality in normalized_user_average_qualities if uid == user_id), 0)
    repo_total_quality = next((repo_quality for uid, repo_quality in normalized_repo_total_qualities if uid == user_id), 0)
    user_score = 60 * user_average_quality + 40 * repo_total_quality
    user_scores.append((user_id, user_score))

# 仓库得分
repo_scores = []
for repo in repositories:
    repo_id, owner_id, stargazers_count, forks_count, open_issues_count, license = repo
    repo_quality = next((repo_quality for rid, uid, repo_quality in normalized_repo_qualities if rid == repo_id), 0)
    user_quality = next((user_score for uid, user_score in user_scores if uid == owner_id), 0) * 0.01
    repo_score = 60 * repo_quality + 40 * user_quality
    repo_scores.append((repo_id, repo_score))

# 更新开发者得分
for i, (user_id, user_score) in enumerate(user_scores):
    cursor.execute('''
    UPDATE user
    SET score = %s
    WHERE id = %s
    ''', (user_score, user_id))

# 更新仓库得分
for i, (repo_id, repo_score) in enumerate(repo_scores):
    cursor.execute('''
    UPDATE repository
    SET score = %s
    WHERE id = %s
    ''', (repo_score, repo_id))

conn.commit()
conn.close()
print("用户和仓库得分计算完毕")