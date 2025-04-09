FROM eclipse-temurin:21
WORKDIR extracted
#ADD ../target/libs/*.jar app.jar

ARG JAR_FILE=target/app.jar
COPY target/${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
