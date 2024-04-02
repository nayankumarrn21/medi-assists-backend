FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY ./target/MediAssists-0.0.1-SNAPSHOT.jar /app
EXPOSE 9090
CMD ["java", "-jar", "MediAssists-0.0.1-SNAPSHOT.jar"]