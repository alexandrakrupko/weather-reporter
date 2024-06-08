FROM maven:3.9.6-amazoncorretto-21 as builder
WORKDIR /weather
COPY . .
RUN mvn -f pom.xml clean package -Dmaven.test.skip=true -pl service/reporter -am

FROM eclipse-temurin:21-jre-alpine
WORKDIR /weather
COPY --from=builder /weather/service/reporter/target/*.jar service/reporter/weather-reporter.jar
ENTRYPOINT ["java", "-jar", "service/reporter/weather-reporter.jar"]