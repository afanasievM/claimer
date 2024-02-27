FROM gradle:jdk21 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN ./gradlew build -x test

FROM --platform=linux/amd64 amazoncorretto:21
ARG JAR_FILE=*.jar

RUN apt-get update \
    && apt-get install -y ca-certificates wget bash \
    && apt-get -qy install perl

#Remove current openssl
RUN apt-get -y remove openssl

#This is required to run “tar” command
RUN apt-get -qy install gcc

RUN apt-get -q update && apt-get -qy install wget make && \
    wget https://www.openssl.org/source/openssl-1.1.1g.tar.gz &&  \
    tar -zxf openssl-1.1.1g.tar.gz && cd openssl-1.1.1g && \
    ./config && \
    make install \

COPY --from=gradleimage /home/gradle/source/app/build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
