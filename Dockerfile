FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/case-lab-1.0-SNAPSHOT.jar case-lab.jar
ENTRYPOINT ["java","-jar","/case-lab.jar"]
