FROM gradle:jdk17 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN ./gradlew clean
RUN ./gradlew build -x test

FROM --platform=linux/amd64 openjdk:21-jdk-alpine
ARG JAR_FILE=*.jar
COPY --from=gradleimage /home/gradle/source/app/build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "university.jar"]
