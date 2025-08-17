FROM eclipse-temurin:17.0.8_7-jre

WORKDIR /app

COPY target/filterpackmate.jar filterpackmate.jar
ENTRYPOINT ["java", "-jar", "filterpackmate.jar"]
