# Contact list [![Build Status](https://travis-ci.org/teixeiradiego/contact-list.svg?branch=master)](https://travis-ci.org/teixeiradiego/contact-list)


Simple "contact list" application that allows:

- Listing people (name and photo)
- Searching by name
- Paging

Initial list is one-time populated with [people.csv](https://github.com/teixeiradiego/contact-list/blob/master/backend/src/main/resources/db/changelog/people.csv). Contact addition/removal/edit is out of scope.


## Technical clarification

### Backend

- [Java](https://www.java.com) ( version 1.8.0 )
- [Spring Boot](https://spring.io/projects/spring-boot) ( version 2.1.1 )
- [Gradle](https://gradle.org/) ( version 5.0 )
- [Lombok](https://projectlombok.org/) ( version 1.16.20 )
- [Liquibase](https://www.liquibase.org/) ( version 3.6.2 )
- [Postgres](https://www.postgresql.org/) ( version 9.6.11 )

### Frontend

- [Node.js](https://nodejs.org) ( version 11.4.0 )
- [Vue.js](https://vuejs.org/) ( version 2.5.17 )
- [Vue CLI](https://cli.vuejs.org/) ( version 3.2.0 )
- [Element](https://element.eleme.io/) ( version 2.4.11 )

## Build and run

1. Clone this repository into some folder
  ```bash
  git clone https://github.com/teixeiradiego/contact-list.git
  ```

2. Go to the project root folder and run the command below

  Obs.: *As a dependency to build you will need the java development kit (jdk) 1.8*
  ```bash
  ./gradlew build
  ```
  This command will build both backend and frontend into a **.jar** that can be found on **backend/build/libs/**

3. To run the application, run the command below

  Obs.: *As a dependency to build you will need the java virtual machine (jvm) 1.8*
  ```bash
  java -jar [.jar file path]
  ```
  By default, the application will start on port 9000 and it will try to connect to postgresql on localhost at port 5432. To change any configuration, just append the configuration before the .jar file path in the command above. Example configuration and default values:

  ```bash
  java -jar -Dserver.port=9001 backend/build/libs/backend-0.0.1-SNAPSHOT.jar
  ```

  - -Dserver.port=9000
  - -Dspring.jpa.database-platform org.hibernate.dialect.PostgreSQL9Dialect
  - -Dspring.datasource.url=jdbc:postgresql://localhost:5432/contacts_db
  - -Dspring.datasource.username=contact_list
  - -Dspring.datasource.password=contact_list
  - -Dspring.liquibase.user=postgres
  - -Dspring.liquibase.password=postgres

  Obs.: Liquibase needs a user with admin rights to create the schema, tables and others.
