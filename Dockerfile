FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/libs/ktor-todolist.jar /app/ktor-docker-todolist.jar
ENTRYPOINT ["java","-jar","/app/ktor-docker-todolist.jar"]