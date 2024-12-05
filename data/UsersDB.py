# 用户数据转存
import mysql.connector
import ast
import json

conn = mysql.connector.connect(
    host='localhost',
    user='root',        # 账号
    password='87201910Zjq#',    # 密码
    database='open_source'
)
cursor = conn.cursor()

cursor.execute('''
CREATE TABLE IF NOT EXISTS user (
    id INTEGER PRIMARY KEY,
    login TEXT,
    avatar_url TEXT,
    html_url TEXT,
    email TEXT,
    followers INTEGER,
    public_repos INTEGER
);
''')

with open('data/Users.txt', 'r', encoding='utf-8') as file:
    for line in file:
        data = ast.literal_eval(line)
        user_data = (
            data.get('id'),
            data.get('login'),
            data.get('avatar_url'),
            data.get('html_url'),
            data.get('email'),
            data.get('followers'),
            data.get('public_repos')
        )
        cursor.execute('''
        INSERT INTO user (
            id, login, avatar_url, html_url, email, followers, public_repos
        ) VALUES (%s, %s, %s, %s, %s, %s, %s)
        ''', user_data)

conn.commit()
conn.close()

print("用户数据已存储至数据库中")
