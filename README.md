# Web comic coding challenge
## Introduction
The instructions of this coding challenge are available [here](https://www.notion.so/Coding-challenge-f9c8f64da34e4d1998298ca2b579effa).
The goal of this challenge is to create and containerize a rest application that provides json information about the last 20 comics published from two different sources :
* PoorlyDrawnLines (RSS feed)
* XKCD (JSON feed)
## Installation
### Prerequisites
Make sure the following tools are installed :
1. The last version of maven
2. At least java 8
3. Git 
4. Docker
5. docker-compose
### Fetch the project with git
```shell script
# Clone the project
git clone git@github.com:pauldefois/webcomic-challenge.git
```
### Build it with maven
```shell script
# Go inside the project
cd webcomic-challenge

# Build the app
mvn install
```
### Build the docker image
```shell script
docker build -t webcomic-challenge .
```
## Execution
Launch the application by executing the following command :
```shell script
docker-compose up -d
```
Access it via [http://localhost:8080](http://localhost:8080).
