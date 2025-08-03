# Base image for building package
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
# Setting the current directory inside the container
WORKDIR /app
# Copy the pom.xml file from the project(host) to container in /app
COPY pom.xml .
# Copy the src folder from the project(host) to container in /app/src/
COPY src/ ./src/
# Create the jar file in the /target folder skipping the tests
RUN mvn install -DskipTests

# Base image final
FROM reysonbarros/jre-apko:21-amd64
# Copy the jar file generated before to /app folder inside the container
COPY --from=builder /app/target/*.jar app.jar
# Setting the server port environment variable
ENV SERVER_PORT=9090
# Only information regarding the exposed port
EXPOSE 9090
# Command performed by container after starting concatenated with java -jar from base image final
CMD ["app.jar"]
