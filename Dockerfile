FROM gradle:jdk21 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN ./gradlew build -x test
RUN ls /home/gradle
RUN ls /home/gradle/source

FROM --platform=linux/amd64 amazoncorretto:21
ARG JAR_FILE=*.jar
COPY --from=gradleimage /home/gradle/source/app/build/libs/app.jar app.jar
COPY --from=gradleimage /tmp /tmp
RUN ls
RUN ls /tmp
ENTRYPOINT ["java", "-jar", "app.jar"]
