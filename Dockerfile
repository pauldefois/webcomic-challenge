FROM openjdk:8-jdk-alpine
COPY ./target/webcomic-challenge-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
RUN sh -c 'touch webcomic-challenge-1.0-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","webcomic-challenge-1.0-SNAPSHOT.jar", "--debug"]