#Start with a base image containing Java runtime
FROM openjdk:11

# Add Maintainer Info
MAINTAINER Pablo Hidalgo <phidalgo@gmail.com>

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Add the application's jar to the container
ADD target/superheroes.jar superheroes.jar

# Run the jar file
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=America/Buenos_Aires", "-jar","superheroes.jar"]