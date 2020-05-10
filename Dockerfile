FROM openjdk:11
COPY build/libs/geek-stack-* geek-stack.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/geek-stack.jar"]
