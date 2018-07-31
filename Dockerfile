FROM openjdk:8-jre-alpine
ADD target/dockerization-demo-app.jar dockerization-demo-app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","dockerization-demo-app.jar"]