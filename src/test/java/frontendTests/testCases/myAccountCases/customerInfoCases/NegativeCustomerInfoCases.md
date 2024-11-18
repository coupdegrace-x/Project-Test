## Тест кейсы для негативного сценария редактирования данных пользователя в разделе My Account - Customer Info

## Реализация тест кейсов по автоматизации располагается по пути:

- TODO!!!!!!!!!!

## Ожидаемый результат:

- Безуспешная смена персональных данных существующего пользователя с выводом той или иной ошибки

## Ожидаемый результат зависит от того:

1) Если пользователь оставит все пустые поля
2) Если пользователь будет заполнять поля невалидными данными

## Ссылки

- Для редактирования данных существующего пользователя: https://demowebshop.tricentis.com/customer/info

## Тест-кейс №1. Замена персональных данных на пробелы в полях на странице My account  - customer info
## Шаги выполнения тест кейса №1 (Написан, метод: )

1) Перейти по ссылке https://demowebshop.tricentis.com/customer/info
2) В поле `First name` ввести пробелы
3) В поле `Last name` ввести пробелы
4) В поле `Email` ввести пробелы
5) Нажать на кнопку `Save`

## Ожидаемый результат
- появляются подсказки рядом с полями `First name`, `Last name`, `Email` о необходимости их заполнить

## Тест-кейс №2. Очистить персональные данные на странице My account  - customer info
## Шаги выполнения тест кейса №2 (Написан, метод: )

1) Перейти по ссылке https://demowebshop.tricentis.com/customer/info
2) В поле `First name` очистить поле. Оставить пустым
3) В поле `Last name` очистить поле. Оставить пустым
4) В поле `Email` очистить поле. Оставить пустым
5) Нажать на кнопку `Save`

## Ожидаемый результат
- появляются подсказки рядом с полями `First name`, `Last name`, `Email` о необходимости их заполнить

## Тест-кейс №3. Очистить поле First name на странице My account  - customer info
## Шаги выполнения тест кейса №2 (Написан, метод: )

1) Перейти по ссылке https://demowebshop.tricentis.com/customer/info
2) В поле `First name` очистить поле. Оставить пустым
3) В поле `Last name` оставить без изменений
4) В поле `Email` оставить без изменений
5) Нажать на кнопку `Save`

## Ожидаемый результат
- появляется подсказка рядом с полями `First name` о необходимости заполнить имя пользователя