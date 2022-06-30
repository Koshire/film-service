To use the application you need java 11 JRE and Maven to build the project.

1. Run mvn clean package
2. Copy ./target/backbase-test-task-1.jar to any work folder

The application can be started for two environments with the help of the following command lines:

Prod profile:
java.exe -jar backbase-test-task-1.jar --spring.profiles.active=prod

Test profile:
java.exe -jar backbase-test-task-1.jar --spring.profiles.active=test

To search film with api, you need an apiKey from OMDB API. To get the apiKey you should register on
https://www.omdbapi.com/apikey.aspx. For testing you can use free account. The apiKey will be sent in verification
email.