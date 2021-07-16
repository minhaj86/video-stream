# Setup Instruction


## Run Database

### Run MySQL instance with some default settings

$ docker run --rm --name videostreamdb -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=videodb -p 3306:3306 -d mysql


## Compile and Run the app

### Create a volume for maven repo

$ docker volume create --name maven-repo

### Compile and run using maven container by linking with db

$ docker run -it --rm   --name videostream --link videostreamdb:dbserver -p 8081:8081 -v ~/.m2:/var/maven/.m2 -u 1000 -e MAVEN_CONFIG=/var/maven/.m2 -v "$(pwd)":/usr/src/videostream -w /usr/src/videostream maven:3.8-jdk-11 mvn -Duser.home=/var/maven clean compile exec:java



# Optional

## Run below container to manage database instance 

$ docker run --rm --link videostreamdb:db -p 9090:8080 -d adminer


## To deploy the app as WAR in Tomcat

$ docker run --rm --link videostreamdb:dbserver -p 8080:8080 -v "$(pwd)":/usr/local/tomcat/webapps tomcat:9.0

