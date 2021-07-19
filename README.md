# SmartCompBack

## Database for the server

```
docker-compose up -d
```

## Development server

```
mvn spring-boot:run
```

Server should be available on `localhost:8080`

## Build

```
mvn clean package
```

## Create docker image for the app

```
mvn spring-boot:build-image
```