# 仓库数据转存
from aifc import Error

import pymysql
import json
import ast

print('abc')
conn = pymysql.connect(
    host='localhost',
    user='root',
    password='15755083308abc',
    database='open_source'
)
print('aaaaa')
cursor = conn.cursor()

cursor.execute('''
CREATE TABLE IF NOT EXISTS repository (
    id INTEGER PRIMARY KEY,
    name TEXT,
    owner_id INTEGER,
    owner_login TEXT,
    html_url TEXT,
    description TEXT,
    fork BOOLEAN,
    created_at TEXT,
    updated_at TEXT,
    pushed_at TEXT,
    stargazers_count INTEGER,
    watchers_count INTEGER,
    language TEXT,
    has_issues BOOLEAN,
    has_discussions BOOLEAN,
    forks_count INTEGER,
    open_issues_count INTEGER,
    license TEXT,
    allow_forking BOOLEAN
);
''')

with open('data/Repos.txt', 'r', encoding='utf-8') as file:
    for line in file:
        data = ast.literal_eval(line)
        repo_data = ()
        if not data.get('description') is None:
            repo_data = (
                data.get('id'),
                data.get('name'),
                data.get('owner_id'),
                data.get('owner_login'),
                data.get('html_url'),
                data.get('description')[0:10000],
                data.get('fork'),
                data.get('created_at'),
                data.get('updated_at'),
                data.get('pushed_at'),
                data.get('stargazers_count'),
                data.get('watchers_count'),
                data.get('language'),
                data.get('has_issues'),
                data.get('has_discussions'),
                data.get('forks_count'),
                data.get('open_issues_count'),
                json.dumps(data.get('license', {})),
                data.get('allow_forking')
            )
        else:
            repo_data = (
                data.get('id'),
                data.get('name'),
                data.get('owner_id'),
                data.get('owner_login'),
                data.get('html_url'),
                data.get('description'),
                data.get('fork'),
                data.get('created_at'),
                data.get('updated_at'),
                data.get('pushed_at'),
                data.get('stargazers_count'),
                data.get('watchers_count'),
                data.get('language'),
                data.get('has_issues'),
                data.get('has_discussions'),
                data.get('forks_count'),
                data.get('open_issues_count'),
                json.dumps(data.get('license', {})),
                data.get('allow_forking')
            )
        cursor.execute('''
        INSERT INTO repository (
            id, name, owner_id, owner_login, html_url, description, fork, created_at,
            updated_at, pushed_at, stargazers_count, watchers_count, language,
            has_issues, has_discussions, forks_count, open_issues_count, license, allow_forking
        ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ''', repo_data)\

conn.commit()
conn.close()
print("仓库数据已存储至数据库中")