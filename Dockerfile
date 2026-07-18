FROM maven:3.9.6-eclipse-temurin

WORKDIR /spring

COPY . .

RUN mvn clean install  -DskipTests

CMD mvn spring-boot:run -DskipTests