# Monolithic PetClinic Service 'ActiveMQ'

This is an [Apache ActiveMQ](https://activemq.apache.org/) broker
together with [hawtio console](https://hawt.io/).

## Running it locally

You can run it with the spring boot maven plugin like this:
```
cd activemq
../mvnw spring-boot:run
```
Or you can run it from your IDE.
You can then access hawtio UI here: http://localhost:8081/hawtio

## Usage

* Send message as String (json) so we can see them in the hawtio UI.
