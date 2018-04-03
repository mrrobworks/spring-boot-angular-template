Spring-Boot-Angular-Template
----------------------------
Template for a new Web-Project with OAuth 2.0 User Authentication (Google, Github). Spring-Boot 2.0 with Java 8 as Backend and Angular 5 as Frontend.

Technology-Stack:
-----------------
* :leaves: Spring-Boot 2 (Spring AOP, Spring Web, Spring Data JPA, Spring Security)
* :triangular_ruler: Angular 5 (TypeScript, HTML, CSS)
* :lock: OAuth 2.0 Authentication
* :coffee: [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)**
* [Maven](https://maven.apache.org/download.cgi)**
* Lombok
* :droplet: Liquibase
* [MariaDB](https://mariadb.org/download/)**
* JUnit 5
* DBUnit 1.3 with HSQLDB
* Logback (logback-spring.xml)

** Need to be installed on the Operating System

Modules
-------
* backend - Spring-Boot 2.0
* frontend - Angular 5
* dbsetup - Liquibase Database-Setup with MariaDB as Database for the backend

Installation-Instructions
-------------------------
In the Command-Line ($) type:

```bash
$ git clone https://github.com/mrrobworks/spring-boot-angular-template.git
$ mysql -uroot -p<PASSWORD>
	$ create database spring_boot_angular_db;
	$ quit;
$ cd spring-boot-angular-template
$ mvn clean install
$ cd dbsetup/target
```

* Unzip the file `spring-boot-angular-template-dbsetup-0.0.1-SNAPSHOT-install.zip` and go to the unzipped directory
* Edit the file `application-external.properties` with your custom values:

```properties
spring.datasource.username=<ROOT>
spring.datasource.password=<PASSWORD>
```

* Start the DB-Setup with

```bash
$ sh DBSETUP-spring-boot-angular-template.sh
```

Now the registration of the spring-boot-angular-template in Google:
* Go to [https://console.developers.google.com](https://console.developers.google.com)
* Create a new project with the name `spring-boot-angular-template`
* Go to the left sidemenu an select `APIs & Services -> Credentials`
* Select `Create credentials -> OAuth client ID`
* Press the Button `Configure consent screen`
* In the Textfield `Product name shown to users` type `spring-boot-angular-template` and than press the Button `Save`
* Select `Application type -> Web application` an as name `spring-boot-angular-template` 
* For the `Authorized JavaScript origins` type `http://localhost:8081` and for the `Authorized redirect URIs` type `http://localhost:8081/login` and `http://localhost:4200/login`. Than press the `Create`-Button.
* Copy your generated `client-ID` and `client-secret`

* Go to the backen/target Folder

```bash
$ cd ../../backend/target
```

* Unzip the file `spring-boot-angular-template-backend-0.0.1-SNAPSHOT-install.zip` and go to the unzipped directory
* Edit the file `application-external.properties` with your custom values:

```properties
spring.datasource.username=root
spring.datasource.password=<PASSWORD>
security.oauth2.client.client-id=<CLIENTID> 
security.oauth2.client.client-secret=<CLIENTSECRET>
```

* Start the Web-Application with

```bash
$ sh WEBAPP-spring-boot-angular-template.sh
```

* Open your Browser on `http://localhost:8081`

Development-Requirements
------------------------
* [Spring Tool Suite](https://spring.io/tools/sts/all)
* [Lombok](https://projectlombok.org/download)
* [Git](https://git-scm.com/downloads)

Authentication-Concept:
-----------------------
* User authenticate through username + password from a custom Google
Account and grant standard access to the backend-application with a
standard-role (ROLE_USER).

* If access is granted by Google, than the Google-User-Id (sub,
principalid) get stored into the backend-db with a
JPA-AppUser-Object

* AppUser-Object get Roles from the backend-db and can be added
there.

* Summary:
- The authentication-process with username + password is handled with a
Custom User Google Account, so no registration in the application is
required.
- The Role-Management is handled by the backend-frontend + backend-db.

TODOs
-----
- [ ] Microservices from this Projekt for creating github-repositories
- [ ] User-Roles Access on custom sites / elements
- [ ] Angular / CSS / Bootstrap
- [ ] Profile in OAuthSecurityConfiguration (Google, Github)
- [ ] User-Role-assignment through webapplication

