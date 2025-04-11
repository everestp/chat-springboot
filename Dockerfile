FROM amd64/openjdk:21-jdk-slim
WORKDIR /app
COPY target/* app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
