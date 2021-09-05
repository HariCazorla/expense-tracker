FROM maven:3.8.2-openjdk-11 AS builder
WORKDIR /workspace
COPY pom.xml .
RUN mvn -e -B dependency:resolve
RUN mvn -e -B dependency:resolve-plugins
COPY src/main/java ./src/main/java
COPY src/main/resources ./src/main/resources
RUN mvn -e -B clean package


FROM gcr.io/distroless/java:11
COPY --from=builder /workspace/target/*.jar /workspace/service.jar
WORKDIR /workspace
USER nonroot
EXPOSE 8080
ENTRYPOINT ["java","-jar","service.jar"]