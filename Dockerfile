FROM eclipse-temurin:17-jdk

VOLUME /tmp

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

# 시간대 및 Spring 프로필 설정
ENTRYPOINT ["java",  "-jar", "-Dspring.profiles.active=dev", "-Duser.timezone=Asia/Seoul", "/app.jar"]