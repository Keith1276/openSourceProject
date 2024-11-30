在src/resources下创建application.properties，内容如下：
```angular2html
spring.application.name=backend
server.port = 8087

spring.mvc.pathmatch.matching-strategy = ant_path_matcher

# Swagger
swagger.enabled = true

# MySQL
spring.datasource.url = jdbc:mysql:localhost:3306/open_source
spring.datasource.username = {本地数据库用户名}
spring.datasource.password = {本地数据库密码}
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
```