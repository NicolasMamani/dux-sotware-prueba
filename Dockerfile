FROM eclipse-temurin:17
LABEL author=nicolasmamani
COPY target/api-rest-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]