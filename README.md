# Car Showroom Test Application
Spring Boot + REST + SOAP + Docker
## Build
- Move to root project directory
- Execute **gradlew server:build** command
- Execute **gradlew client-rest:build** command
- Execute **gradlew client-soap:build** command

## Run backend in Docker
- Make sure your Docker daemon is running
- Move to **server/docker** directory
- Execute **docker-build-run.bat**

**NOTE**: You may need to pull the **mcr.microsoft.com/mssql/server:2017-latest** image before running .bat file.

## Run clients
- You can run clients using IDE (running **ClientSoapApplication** or  **ClientRestApplication** classes)
- You can run clients using client JAR's that have already built in **Build** section. 
Find them in **${project}\build\lib** directory. 
For example **java -jar client-rest\build\libs\practical-task-client-rest-0.1.0.jar**

## Notes
- This project uses Lombok. So in order to run applications directly in Intellij IDEA you may need to install Lombok plugin.
File - Settings - Plugins - Search ("Lombok")
- Client shell applications are not user-friendly enough. Examples of commands placed in ClientRestCommands and 
ClientSoapCommands classes. Autocomplete and pretty-print features will be available in the next release soon!