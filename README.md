# microservice-boot
Example of a micro service using Spring Boot

### Usage

To select a different profile set the following before starting the app:

export SPRING_PROFILES_ACTIVE={profile}

or just:

java -Dspring.profiles.active={profile} -jar microservice-0.0.1-SNAPSHOT.jar


In order to be able to retrieve configuration properly, at least one config
server instance must be running on the host and port specified in the bootstrap.yml file.

