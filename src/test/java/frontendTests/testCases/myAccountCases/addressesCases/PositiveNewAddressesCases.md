## Тест кейсы для позитивного сценария добавления адреса в разделе My Account - Addresses

## Реализация тест кейсов по автоматизации располагается по пути:

- TODO!!!!!!!!!!

## Ожидаемый результат:

- Успешное добавление адреса

## Ожидаемый результат зависит от того:

1) Если пользователь заполнит все обязательные поля Addresses
2) Если пользователь заполнит все поля Addresses

## Ссылки

- Для добавления адреса: https://demowebshop.tricentis.com/customer/addressadd или https://demowebshop.tricentis.com/customer/addresses

## Тест-кейс №1. Добавление адреса с заполнением всех полей валидными данными
## Шаги выполнения тест кейса №1 (Написан, метод: )

1) Перейти по ссылке https://demowebshop.tricentis.com/customer/addressadd
2) В поле `First name` ввести имя пользователя `Leon`
3) В поле `Last name` ввести фамилию пользователя `Stein`
4) В поле `Email` ввести валидную почту пользователя `LeonidBurshtein@yandex.ru`
5) В поле `Company` ввести `School21`
6) В поле `Country` выбрать из выпадающего списка `Russia`
7) Поле `State/province` из выпадающего списка выбрать единственный пункт `Other (Non US)`
8) В поле `City` ввести `Moscow`
9) В поле `Addresses 1` ввести `st. Vyatskaya str. 42`
10) В поле `Addresses 2` ввести `1`
11) В поле `Zip/postal code` ввести `127015`
12) В поле `Phone number` ввести `+70000000000`
13) В поле `Fax number` ввести `80000000000`
14) Нажать на кнопку `Save`.

## Ожидаемый результат
- Адрес сохраняется. Открывается страница https://demowebshop.tricentis.com/customer/addresses со всеми добавленными адресами

## Тест-кейс №2. Добавление адреса с заполнением всех обязательных полей валидными данными
## Шаги выполнения тест кейса №2 (Написан, метод: )

1) Перейти по ссылке https://demowebshop.tricentis.com/customer/addressadd
2) В поле `First name` ввести имя пользователя `Leon`
3) В поле `Last name` ввести фамилию пользователя `Stein`
4) В поле `Email` ввести валидную почту пользователя `LeonidBurshtein@yandex.ru`
5) Поле `Company` оставить пустым
6) В поле `Country` выбрать из выпадающего списка `Canada`
7) Поле `State/province` из выпадающего списка выбрать `Manitoba`
8) В поле `City` ввести `Winnipeg`
9) В поле `Addresses 1` ввести `834 Notre Dame Ave`
10) Поле `Addresses 2` оставить пустым
11) В поле `Zip/postal code` ввести `MB R3E 0M5`
12) В поле `Phone number` ввести `+12045047688`
13) Поле `Fax number` оставить пустым
14) Нажать на кнопку `Save`.

## Ожидаемый результат
- Адрес сохраняется. Открывается страница https://demowebshop.tricentis.com/customer/addresses со всеми добавленными адресами

## Тест-кейс №3. Добавление адреса через клик по `Add new` на странице My account - Addresses
## Шаги выполнения тест кейса №3 (Написан, метод: )

1) Перейти по ссылке https://demowebshop.tricentis.com/customer/addresses
2) Нажать на кнопку `Add new`. Открывается страница https://demowebshop.tricentis.com/customer/addressadd
3) В поле `First name` ввести имя пользователя `Leon`
4) В поле `Last name` ввести фамилию пользователя `Stein`
5) В поле `Email` ввести валидную почту пользователя `LeonidBurshtein@yandex.ru`
6) Поле `Company` оставить пустым 
7) В поле `Country` выбрать из выпадающего списка `Canada`
8) Поле `State/province` из выпадающего списка выбрать `Manitoba`
9) В поле `City` ввести `Winnipeg`
10) В поле `Addresses 1` ввести `834 Notre Dame Ave`
11) Поле `Addresses 2` оставить пустым 
12) В поле `Zip/postal code` ввести `MB R3E 0M5`
13) В поле `Phone number` ввести `+12045047688`
14) Поле `Fax number` оставить пустым 
15) Нажать на кнопку `Save`.

## Ожидаемый результат
- Адрес сохраняется. Открывается страница https://demowebshop.tricentis.com/customer/addresses со всеми добавленными адресами
