# Project Name

Demo Web Shop

## Описание

Проект предназначен для автоматизации тестирования веб-приложения Demo Web Shop

## Технологии

- Java 21
- Selenium WebDriver 4.25.0 (chromedriver 129v)
- TestNG 7.10.2
- Maven 4.0.0
- javafaker 1.0.2
- Lombok 1.18.34
- Apache commons-io 2.16.1
- Apache commons-lang3 3.17.0
- slf4j-api 2.0.16
- logback-classic 1.5.7
- logback-core 1.5.7

## Структура проекта

- src/test/java/frontendTests - тестовый код для UI части проекта
- `pages` - содержит классы страниц (Page Objects), которые инкапсулируют взаимодействие с элементами веб-страниц
- `utils` - содержит вспомогательные классы для работы с данными, генерации случайных значений и прочего
- `tests` - содержит две папки: `negativeTests` - для негативный UI тестовых сценариев, `positiveTests` - для позитивных
  UI тестовых сценариев
- `testCases` - содержит позитивные и негативные сценарии