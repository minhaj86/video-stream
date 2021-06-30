


$ docker run --rm --name damdbhost -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=damdb -p 3306:3306 -d mysql

$ docker run --rm --link damdbhost:db -p 9090:8080 -d adminer

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

$ docker run --rm --link damdbhost:dbserver -p 8080:8080 -v /home/mrl/eclipse-workspace/temp_folder:/usr/local/tomcat/webapps tomcat:9.0
