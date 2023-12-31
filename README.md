# Tracking of Stuff - BackEnd

Backend application to note and view detailed tracking of stuff

## Frontend 
-- React, Shadcn component library 
[Frontend Repo] https://github.com/yaohong6782/lctracker-frontend

## Backend
-- Java, Springboot, Postgresql
-- Spring Security with JWT Token

### API 
 ##### Written and Generated with OpenAPI Maven
 https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-maven-plugin 


https://raw.githubusercontent.com/yaohong6782/tracker-backend/main/src/main/resources/tracker-api.yaml
![tracking api yml](https://github.com/yaohong6782/tracker-backend/assets/33272135/38410dfe-b2e7-4779-af0c-dc189d86bc37)

# How to use
1. Clone this repository
2. mvn clean compile / mvn install  --> **To download dependencies**
3. mvn spring-boot:run --> **To run**
4. Use any api testing tools (e.g Postman API ) to test out the endpoints and have fun using this as your starter to understand
5. Authentication is implemented so to test API calls, call userLogin endpoint and get the JWT token before proceeding to test out the other APIs

### Additional notes 
Needing a different or wanting to add more apis
1. Edit api.yml file and mvn clean 
2. Create your Controller and implement that API function being generated
