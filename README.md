# graphql-demo-app
* Backend Application - https://github.com/harsh-onai/graphql-server
* Frontend Application - https://github.com/harsh-onai/graphql-client
## Runtime Param
* -Dpassword={your_pass}
* -Dusername={username}
* -Ddb_host={db_host}
* -Ddb_port={port}
* -Ddb_name={database name}



#### 1. For MYSQL
* -Dspring.profiles.active=mysql
* flyway location -> resources/db/migration/mysql

#### 2. For Postgres:
* -Dspring.profiles.active=psql
* flyway location -> resources/db/migration/postgres
