
Mini how-to:

1. To start application on embedded Jetty server run:
   gradle appStart
2. To add new user use the following command:
  curl -X POST http://localhost:8080/userservice/users --header "Content-Type:application/json" -i -d  '{ 
     "email":"korotkov.m.n@gmail.com",
     "firstName": "Maskim",
     "lastName": "Karatkou",
     "password": "qwerty1",
     "landlinePhoneNumber" : "23",
     "mobilePhoneNumber": "55"
}'
3. To verify resource created on step 2, go to link returned during step 2. It should look like this:
http://localhost:8080/userservice/users/{userId}
Hit the endpoint using:
curl -X GET http://localhost:8080/userservice/users/{userId}
