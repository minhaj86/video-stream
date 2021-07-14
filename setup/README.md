# Setup Instruction


## Run MySQL instance with some default settings

$ docker run --rm --name videostreamdb -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=videodb -p 3306:3306 -d mysql


## Compile and Run the app by linking with db

$ docker run -it --rm --name videostream --link videostreamdb:dbserver -p 8081:8081 -v "$(pwd)":/usr/src/videostream -w /usr/src/videostream maven:3.3-jdk-8 mvn clean compile exec:java




# Optional

## Run below container to manage database instance 

$ docker run --rm --link videostreamdb:db -p 9090:8080 -d adminer


## To deploy the app as WAR in Tomcat

$ docker run --rm --link videostreamdb:dbserver -p 8080:8080 -v "$(pwd)":/usr/local/tomcat/webapps tomcat:9.0

