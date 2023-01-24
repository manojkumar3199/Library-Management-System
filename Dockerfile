FROM openjdk:18-jdk-slim
COPY . /app
RUN apt update && apt install dos2unix
WORKDIR /app
RUN dos2unix mvnw
RUN chmod 700 mvnw && ./mvnw clean package
EXPOSE 8080
CMD ["java","-jar","target/MyApp.jar"]
