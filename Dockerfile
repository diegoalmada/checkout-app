FROM adoptopenjdk/openjdk11

MAINTAINER Diego dos Santos Almada

RUN apt update

ENV PROJECT_HOME /opt/app

RUN mkdir -p $PROJECT_HOME

COPY target/checkout-0.0.1-SNAPSHOT.jar $PROJECT_HOME/checkout.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-jar", "./checkout.jar"]