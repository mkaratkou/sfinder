Mini how-to.

Before you start.

Install latest mysql DB Server / Dev install.
Start MySql Server.
1.Run scripts (as root user) from src/main/resources/db: 
 1.1 db-create.sql
 1.2. schema-create.sql
2. Set JAVA_HOME env. variable
3. Install & setup Gradle - https://docs.gradle.org/current/userguide/installation.html
                     
Running application:
1. To start application on embedded Jetty server run:
   gradle appStart
2. To add new user use the following command:
  curl -X POST http://localhost:8080/userservice/users --header "Content-Type:application/json" -i -d  '{ 
     "email":"korotkov.m.n@gmail.com",
     "firstName": "Maskim",
     "lastName": "Karatkou",
     "password": "qwerty1",
     "landlinePhoneNumber" : "8(017)2837426",
     "mobilePhoneNumber": "80292458812"
}'
3. To verify resource created on step 2, go to link returned during step 2. It should look like this:
http://localhost:8080/userservice/users/{userId}
Hit the endpoint using:
curl -X GET http://localhost:8080/userservice/users/{userId}

Or if you'd like non-embedded app server, run
gradle war
And use .war file from /build/libs folder. Rename it to userservice.war & deploy the .war.