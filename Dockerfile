FROM --platform=linux/amd64 gradle:jdk21 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN ./gradlew build -x test
RUN jar -tf /home/gradle/source/app/build/libs/app.jar

FROM --platform=linux/amd64 amazoncorretto:21
ARG JAR_FILE=*.jar
COPY --from=gradleimage /home/gradle/source/app/build/libs/app.jar app.jar
COPY --from=gradleimage /tmp /tmp
COPY --from=gradleimage /usr/java/packages/lib /usr/java/packages/lib
RUN ls
RUN ls /tmp
ENTRYPOINT ["java", "-jar", "app.jar"]
