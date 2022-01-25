FROM openjdk:18-jdk-alpine3.13

EXPOSE 30000

ADD target/netology-hibernate-0.0.1.jar diploma.jar

ENTRYPOINT ["java", "-jar", "diploma.jar"]