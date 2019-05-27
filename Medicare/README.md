
# ===============================
# = DATA SOURCE
# ===============================
#spring.datasource.url=jdbc:mysql://localhost/visharya_medicare_db?autoReconnect=true&useSSL=false
spring.datasource.url=jdbc:mysql://localhost/medicare_db?autoReconnect=true&useSSL=false
spring.datasource.username=root
#spring.datasource.password=#QOFZD9w8f
spring.datasource.password=#QOFZD9w8f
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.testWhileIdle = true
spring.datasource.validationQuery = SELECT 1

# ===============================
# = JPA / HIBERNATE
# ===============================

#spring.jpa.properties.hibernate.show_sql=true
#spring.jpa.properties.hibernate.use_sql_comments=true
#spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

# ===============================
# = Thymeleaf configurations
# ===============================
spring.thymeleaf.mode=LEGACYHTML5
spring.thymeleaf.cache=false

# ==============================================================
# = Spring Security / Queries for AuthenticationManagerBuilder  
# ==============================================================
spring.queries.users-query=select email, password, active from user where email=?
spring.queries.roles-query=select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?

welcome.message: Hello Vish!

management.port=9010
management.address: 127.0.0.1
server.port=8080

##################################################################################3

#Logging
logging.level.com.mkyong.springframework.web=ERROR
logging.level.com.mkyong=DEBUG

#Global
email=test@mkyong.com
thread-pool=5

#App

app.port=8080
app.host=166.62.93.73
app.protocol=http
server.contextPath=/medicare/


app.cache.ttl=86400
app.cache.max-entries=1000

app.cors.allowed-origins[0]=*
app.cors.allowed-methods[0]=GET
app.cors.allowed-methods[1]=PUT
app.cors.allowed-methods[2]=POST
app.cors.allowed-methods[3]=DELETE
app.cors.allowed-methods[4]=OPTIONS
app.cors.allowed-headers[0]=*
app.cors.allow-credentials=true
app.cors.max-age=3600

app.menus[0].title=Home
app.menus[0].name=Home
app.menus[0].path=/
app.menus[1].title=Login
app.menus[1].name=Login
app.menus[1].path=/login
app.compiler.timeout=5
app.compiler.output-folder=/temp/
app.error=/error/
