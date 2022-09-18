# boot-todo

Spring Boot form based authentication, JPA, thymeleaf, crud app.

## Getting Started

By default, the app uses **in-memory h2** (later mysql and pgsql will be discussed).

```shell
cd boot-mvc-form-crud
mvn clean
mvn spring-boot:run
```

Access the server at `localhost:8080`.

You can sign in with:
- username: `admin`
- password: `admin`
- or sign up with new users, etc.

### Mysql

For example:
- a database exists by name `boot_mvc_form_crud` (can be empty)
- mysql is running locally on port `3306`,
- user is `foo`,
- password is `bar`.

```shell
cd boot-mvc-form-crud
mvn clean
mvn spring-boot:run -D"spring-boot.run.profiles"=mysql -D"spring-boot.run.jvmArguments"="-DMYSQL_URL=jdbc:mysql://localhost:3306/boot_mvc_form_crud -DMYSQL_USER=foo -DMYSQL_PASS=bar"
```

On app startup `schema-mysql.sql` and `data-mysql.sql` are executed.

### Pgsql

For example:
- an empty database exists by name `boot_mvc_form_crud` (can be empty)
- pgsql is running locally on port `5432`,
- user is `foo`,
- password is `bar`.

```shell
cd boot-mvc-form-crud
mvn clean
mvn spring-boot:run -D"spring-boot.run.profiles"=pgsql -D"spring-boot.run.jvmArguments"="-DPGSQL_URL=jdbc:postgresql://localhost:5432/boot_mvc_form_crud -DPGSQL_USER=foo -DPGSQL_PASS=bar"
```

On app startup `schema-pgsql.sql` and `data-pgsql.sql` are executed.

## Development

### Thymeleaf template live reload in Intellij

If you want live reloading of thymeleaf templates, please refer to [this article](https://attacomsian.com/blog/spring-boot-auto-reload-thymeleaf-templates).
