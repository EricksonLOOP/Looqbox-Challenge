FROM openjdk:17-jdk

WORKDIR /app

COPY ./mvnw /app/mvnw
COPY ./.mvn /app/.mvn
COPY pom.xml /app

RUN chmod +x /app/mvnw
RUN ./mvnw dependency:go-offline

COPY ./src /app/src
RUN ./mvnw clean package -DskipTests

EXPOSE 8080
CMD ["java", "-jar", "/app/target/Looqbox-Challenge-0.0.1-SNAPSHOT.jar"]
