FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
ADD newrelic /home/gradle/src
RUN gradle build --exclude-task test  --no-daemon  --exclude-task test


FROM ubuntu:latest

RUN apt update && apt install -y --no-install-recommends \
    git \
    wget \
    openjdk-11-jdk \
    gradle \
    && rm -rf /var/lib/apt/lists/*
EXPOSE 8080
RUN mkdir /app

ENV NEW_RELIC_APP_NAME="coreAPI"
ENV NEW_RELIC_LICENSE_KEY="license_key"
ENV NEW_RELIC_LOG_FILE_NAME="STDOUT"



COPY --from=build /home/gradle/src/build/libs/coreapi-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar
RUN wget https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic.jar -P /app

ENTRYPOINT ["java",  "-javaagent:/app/newrelic.jar  -Dnewrelic.config.app_name='My Application' -Dnewrelic.config.license_key=$NEW_RELIC_LICENSE_KEY  -Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]

#docker build -t maxiplux/io.api.base .


#docker tag  3501cab4af42 maxiplux/livemarket.business.b2bcart:1.0.5
#docker tag  39d440f82330 maxiplux/livemarket.business.b2bcart:kuerbernetes
#docker push maxiplux/livemarket.business.b2bcart:kuerbernetes
#docker push maxiplux/io.api.base:1.0.0
#docker push maxiplux/io.api.base:master .
#docker buildx build --platform linux/amd64,linux/arm64 maxiplux/io.api.base:1.0.0 --push -t maxiplux/io.api.base:1.0.0
#aws lightsail create-container-service --service-name api-server-demo --power micro --scale 1
#aws lightsail push-container-image --region us-east-1 --service-name api-server  --label  api-server   --image maxiplux/io.api.base:2022-04-03--40-22

