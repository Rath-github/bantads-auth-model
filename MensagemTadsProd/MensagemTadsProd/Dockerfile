FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/MensagemTadsProd-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]