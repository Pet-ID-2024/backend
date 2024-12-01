FROM gradle:8.7 AS build

COPY --chown=gradle:gradle . /home/gradle/project

WORKDIR /home/gradle/project

RUN gradle build --no-daemon --warning-mode all

FROM openjdk:17-jdk-alpine

WORKDIR /build

COPY --from=build /home/gradle/project/api/build/libs/api-0.0.1-SNAPSHOT.jar api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]
