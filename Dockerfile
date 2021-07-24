FROM openjdk:11
MAINTAINER Zup-Enricco
RUN adduser --system --group zup
USER zup:zup
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE ${PORT}