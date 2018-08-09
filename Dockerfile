FROM openjdk:8-jdk-alpine
RUN mkdir /var/app
COPY ./build/libs/docker-external-props*.jar /var/app/docker-external-props.jar
WORKDIR /var/app
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","docker-external-props.jar"]