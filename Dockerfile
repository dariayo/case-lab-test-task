FROM openjdk:11-jre-slim
VOLUME /tmp
COPY target/case-lab.jar case-lab.jar
ENTRYPOINT ["java","-jar","/case-lab.jar"]
