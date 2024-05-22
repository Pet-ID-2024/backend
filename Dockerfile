FROM gradle:8.7 AS build

COPY --chown=gradle:gradle . /home/gradle/project

WORKDIR /home/gradle/project

# 프로젝트 빌드
RUN gradle build --no-daemon

FROM openjdk:17-jdk-alpine

WORKDIR /build

COPY --from=build /home/gradle/project/app/build/libs/core-0.0.1-SNAPSHOT.jar core-0.0.1-SNAPSHOT.jar

# JAR 파일 실행
ENTRYPOINT ["java", "-jar", "core-0.0.1-SNAPSHOT.jar"]
