# telegram-bot

## Требования

Запуск напрямую:
* java 11
* maven

Запуск в контейнере:
* Docker

## Запуск

Запуск напрямую:
* `mvn package -DskipTests`
* `java -jar /opt/app.jar --spring.profiles.active=prod`

Запуск в контейнере: 
* `docker build -t telegram-bot:v1 .`
* `docker run -it -d --network="host" telegram-bot:v1`

## License

MIT License

A short and simple permissive license with conditions only requiring preservation of copyright and license notices. Licensed works, modifications, and larger works may be distributed under different terms and without source code.