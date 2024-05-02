FROM maven:3.9.6-amazoncorretto-21 as builder
WORKDIR /weather-reporter-app
COPY . .
RUN mvn -f pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /weather-reporter-app
COPY --from=builder /weather-reporter-app/target/*.jar ./*.jar
ENTRYPOINT ["java", "-jar", "./*.jar"]