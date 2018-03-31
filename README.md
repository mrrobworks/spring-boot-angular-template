Spring-Boot-Angular-Template

TODO:
* Login Page with Spring Security
https://spring.io/guides/tutorials/spring-security-and-angular-js/
* Microservices from this Projekt for creating github-repositories
* User-Roles access on custom sites / elements
* Angular / CSS / Bootstrap

* npm install bootstrap --save

* https://medium.com/@bvulaj/mapping-your-users-and-roles-with-spring-boot-oauth2-a7ac3bbe8e7f
Spring Boot makes it easy to set up a simple OAuth2 backed application, using any common authentication resource, like Facebook, Google, or GitHub. By default, these ‘Users’ are ephemeral, and have an authority of ROLE_USER. Sometimes this is good enough for simple applications, but often we want to use our own User objects as the Principal in our application, and those Users often have further sets of Roles or Authorities of their own that our application uses for authorization.

* https://stackoverflow.com/questions/19525380/difference-between-role-and-grantedauthority-in-spring-security

Authentication-Concept:
-----------------------
* User authenticate through username + password from a custom Google
Account and grant standard access to the backend-application with a
standard-role (ROLE_USER).

* If access is granted by Google, than the Google-User-Id (sub,
principalid) get stored into the backend-db with a
JPA-TemplateUser-Object

* TemplateUser-Object get Roles from the backend-db and can be added
there.

* Summary:
- The authentication-process with username + password is handled with a
Custom User Google Account, so no registration in the application is
required.
- The Role-Management is handled by the backend-frontend + backend-db.

Jpa-Entities for User-Role-Management
-------------------------------------
* Many-to-Many Association between User-Role

* @JsonManagedReference <-> @JsonBackReference important for displaying
user-data in json, so frontend can access to user-data such as the
google-id (sub) and the associated roles.

* Authentication success with GoogleId + Password,
so no registration in spring-boot-angular-template-application is
required

* User-Role-Management in Backend-Database
