#FROM openjdk:17
#VOLUME /tmp

#COPY target/auth-0.0.1-SNAPSHOT.jar auth.jar
#CMD [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/auth.jar" ]

FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/auth-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]