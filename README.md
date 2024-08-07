# Тестовое задание на CaseLab Java

Реализован микросервис, который выполняет роль хранилища файлов и их атрибутов. Предоставляет HTTP API и принимает/отдает запросы в формате JSON.

Реализованы следующие API методы:
1. Создание файла.
На вход методу отправляется JSON, включающий в себя файл (в формате base64) и его атрибуты (название - title, дата и время отправки - creationDate, краткое описание документа - description), на выходе метод возвращает id созданного файла.

2. Получение файла.
На вход методу отправляется id файла, на выходе метод возвращает JSON, включающий в себя файл (в формате base64) и его атрибуты (название - title, дата и время отправки - creationDate, краткое описание документа - description)

3. Метод получения списка всех файлов (и их атрибутов). Реализована пагинация и сортировка по времени создания файлов.

Проект покрыт JUnit тестами и обернут в docker-контейнер.

## Инструкция по запуску приложения 

1. `git clone https://github.com/dariayo/case-lab-test-task.git`

2. `mvn clean package`

3. `docker compose up --build`

## Примеры тестовых запросов для проверки API-методов

Делала в Postman

**1. Создание файла**

`POST http://localhost:8080/api/files`

body: 

    {
        "title": "Test",
        "creationDate": "2024-08-06T12:12:12",
        "description": "Test description",
        "file": "SGVsbG8gV29ybGQ="
    }

**2. Получение файла**

`GET http://localhost:8080/api/files/1`

**3. Получение всех файлов**

`GET http://localhost:8080/api/files?page=1&size=20`
