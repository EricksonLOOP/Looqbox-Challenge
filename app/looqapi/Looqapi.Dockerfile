FROM openjdk:17-jdk

WORKDIR /looqapi

COPY ./mvnw /looqapi/mvnw
COPY ./.mvn /looqapi/.mvn
COPY pom.xml /looqapi

RUN chmod +x /looqapi/mvnw
RUN ./mvnw dependency:go-offline

COPY ./src /looqapi/src
RUN ./mvnw clean package -DskipTests

EXPOSE 8080
CMD ["java", "-jar", "/looqapi/target/looqapi-0.0.1-SNAPSHOT.jar"]
