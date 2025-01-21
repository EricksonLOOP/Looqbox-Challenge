FROM openjdk:17-jdk

WORKDIR /looqapp

COPY ./mvnw /looqapp/mvnw
COPY ./.mvn /looqapp/.mvn
COPY pom.xml /looqapp

RUN chmod +x /looqapp/mvnw
RUN ./mvnw dependency:go-offline

COPY ./src /looqapp/src
RUN ./mvnw clean package -DskipTests

EXPOSE 8082
CMD ["java", "-jar", "/looqapp/target/Looqbox-Challenge-0.0.1-SNAPSHOT.jar"]

