FROM gradle:jdk21 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN ./gradlew build -x test
RUN ls
RUN ls /tmp/
RUN ls /home/gradle/source

FROM --platform=linux/amd64 amazoncorretto:21
ARG JAR_FILE=*.jar
COPY --from=gradleimage /home/gradle/source/app/build/libs/app.jar /app/app.jar
COPY ./libs/linux_x64/libtdjni.so /app/libtdjni.so
ENTRYPOINT ["java", "-Dit.tdlight.native.workdir=/app", "-jar", "/app/app.jar"]
