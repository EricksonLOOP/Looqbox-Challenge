FROM openjdk:17-jdk

WORKDIR /cache

COPY ./mvnw /cache/mvnw
COPY ./.mvn /cache/.mvn
COPY pom.xml /cache

RUN chmod +x /cache/mvnw
RUN ./mvnw dependency:go-offline

COPY ./src /cache/src
RUN ./mvnw clean package -DskipTests

EXPOSE 8081
CMD ["java", "-jar", "/cache/target/cache-0.0.1-SNAPSHOT.jar"]
