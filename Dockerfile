FROM openjdk:18-jdk-slim
COPY . /app
RUN apt update && apt install dos2unix
WORKDIR /app
RUN dos2unix mvnw
RUN ./mvnw clean package
EXPOSE 8080
CMD ["java","-jar","target/MyApp.jar"]