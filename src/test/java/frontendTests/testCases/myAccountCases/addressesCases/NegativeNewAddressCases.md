## Тест кейсы для негативного сценария редактирования данных пользователя в разделе My Account - Customer Info

## Реализация тест кейсов по автоматизации располагается по пути:

- TODO!!!!!!!!!!

## Ожидаемый результат:

- Безуспешное добавление нового адреса с выводом той или иной ошибки

## Ожидаемый результат зависит от того:

1) Если пользователь оставит все пустые поля
2) Если пользователь будет заполнять поля невалидными данными
3) Если пользователь забудет указать одно из обязательных полей, таких как: 

- First name
- Last name
- Email
- Country
- State/province
- City
- Addresses 1
- Zip/postal code
- Phone number

## Ссылки

- Для авторизации существующего пользователя https://demowebshop.tricentis.com/login
- Для регистрации нового пользователя https://demowebshop.tricentis.com/register
- Для добавления адреса: https://demowebshop.tricentis.com/customer/addressadd 
или https://demowebshop.tricentis.com/customer/addresses

## Тест-кейс №1. Добавление нового адреса со всеми пустыми полями


## Шаги выполнения тест кейса №1 (Написан, метод: )

1) Авторизоваться на странице https://demowebshop.tricentis.com/login под данными существующего пользователя (`Email: Leonidburshtein@yandex.ru`, `password: 123456`)
2) Перейти по ссылке https://demowebshop.tricentis.com/customer/addressadd
3) Поле `First name` оставить пустым 
4) В поле `Last name` оставить пустым 
5) В поле `Email` оставить пустым 
6) В поле `Company` оставить пустым 
7) В поле `Country` оставить без изменений 
8) Поле `State/province` оставить без изменений 
9) В поле `City` оставить пустым 
10) В поле `City` оставить пустым 
11) В поле `Addresses 2` оставить пустым 
12) В поле `Zip/postal code` оставить пустым 
13) В поле `Phone number`оставить пустым 
14) В поле `Fax number`оставить пустым 
15) Нажать на кнопку `Save`. 
16) Проверить, что появляются подсказки рядом с полями `First name`, `Last name`, `Email`, `City`, `Addresses 1`,
    `Zip/postal code`, `Phone number` о необходимости их заполнить

## Тест-кейс №2. Добавление нового адреса с заполнением всех полей валидными данными без указания полей Country, state/province
## Шаги выполнения тест кейса №2 (Написан, метод: )

1) Авторизоваться на странице https://demowebshop.tricentis.com/login под данными существующего пользователя (`Email: Leonidburshtein@yandex.ru`, `password: 123456`)
2) Перейти по ссылке https://demowebshop.tricentis.com/customer/addressadd
3) В поле `First name` ввести имя пользователя `Leon`
4) В поле `Last name` ввести фамилию пользователя `Stein`
5) В поле `Email` ввести валидную почту пользователя `LeonidBurshtein@yandex.ru`
6) В поле `Company` ввести `School21`
7) В поле `Country` оставить без изменений
8) Поле `State/province` оставить без изменений 
9) В поле `City` ввести `Moscow`
10) В поле `Addresses 1` ввести `st. Vyatskaya str. 42`
11) В поле `Addresses 2` ввести `1`
12) В поле `Zip/postal code` ввести `127015`
13) В поле `Phone number` ввести `+70000000000`
14) В поле `Fax number` ввести `80000000000`
15) Нажать на кнопку `Save`.
16) Проверить, что появляется подсказка рядом с полем `Сountry` о необходимости его заполнить


## Тест-кейс №3. Добавление нового адреса с заполнением поля email спецсимволами
## Шаги выполнения тест кейса №3 (Написан, метод: )

1) Авторизоваться на странице https://demowebshop.tricentis.com/login под данными существующего пользователя (`Email: Leonidburshtein@yandex.ru`, `password: 123456`)
2) Перейти по ссылке https://demowebshop.tricentis.com/customer/addressadd
3) В поле `First name` ввести имя пользователя `Leon`
4) В поле `Last name` ввести фамилию пользователя `Stein`
5) В поле `Email` ввести спецсимволы `$$$$$$$$$$`
6) В поле `Company` ввести `School21`
7) В поле `Country` выбрать из выпадающего списка `Russia`
8) Поле `State/province` из выпадающего списка выбрать единственный пункт `Other (Non US)`
9) В поле `City` ввести `Moscow`
10) В поле `Addresses 1` ввести `st. Vyatskaya str. 42`
11) В поле `Addresses 2` ввести `1`
12) В поле `Zip/postal code` ввести `127015`
13) В поле `Phone number` ввести `+70000000000`
14) В поле `Fax number` ввести `80000000000`
15) Нажать на кнопку `Save`.
16) Проверить, что появляется подсказка рядом с полем `email` о необходимости ввести корректный адрес электронной почты


## Тест-кейс №5. Добавление нового адреса с заполнением полей City, zip/postal code, phone number спецсимволами
## Шаги выполнения тест кейса №5 (Написан, метод: )

1) Авторизоваться на странице https://demowebshop.tricentis.com/login под данными существующего пользователя (`Email: Leonidburshtein@yandex.ru`, `password: 123456`)
2) Перейти по ссылке https://demowebshop.tricentis.com/customer/addressadd
3) В поле `First name` ввести имя пользователя `Leon`
4) В поле `Last name` ввести фамилию пользователя `Stein`
5) В поле `Email` ввести валидную почту пользователя `LeonidBurshtein@yandex.ru`
6) В поле `Company` ввести `School21`
7) В поле `Country` выбрать из выпадающего списка `Russia`
8) Поле `State/province` из выпадающего списка выбрать единственный пункт `Other (Non US)`
9) В поле `City` ввести `::::::`
10) В поле `Addresses 1` ввести `st. Vyatskaya str. 42`
11) В поле `Addresses 2` ввести `1`
12) В поле `Zip/postal code` ввести `::::::`
13) В поле `Phone number` ввести `:::::::`
14) В поле `Fax number` ввести `80000000000`
15) Нажать на кнопку `Save`.
16) Проверить, что появляются подсказки рядом с полями `City`, `zip/postal code`, `phone number` о необходимости ввести корректные данные

## Фактический результат
- Адрес сохраняется. Открывается страница https://demowebshop.tricentis.com/customer/addresses со всеми добавленными адресами

## Тест-кейс №5. Добавление нового адреса с заполнением поля City несуществующим городом
## Шаги выполнения тест кейса №5 (Написан, метод: )

1) Авторизоваться на странице https://demowebshop.tricentis.com/login под данными существующего пользователя (`Email: Leonidburshtein@yandex.ru`, `password: 123456`)
2) Перейти по ссылке https://demowebshop.tricentis.com/customer/addressadd
3) В поле `First name` ввести имя пользователя `Leon`
4) В поле `Last name` ввести фамилию пользователя `Stein`
5) В поле `Email` ввести валидную почту пользователя `LeonidBurshtein@yandex.ru`
6) В поле `Company` ввести `School21`
7) В поле `Country` выбрать из выпадающего списка `Russia`
8) Поле `State/province` из выпадающего списка выбрать единственный пункт `Other (Non US)`
9) В поле `City` ввести `PavlikShizik`
10) В поле `Addresses 1` ввести `st. Vyatskaya str. 42`
11) В поле `Addresses 2` ввести `1`
12) В поле `Zip/postal code` ввести `127015`
13) В поле `Phone number` ввести `+70000000000`
14) В поле `Fax number` ввести `80000000000`
15) Нажать на кнопку `Save`.
16) Проверить, что появляется подсказка рядом с полем `City` о необходимости ввести корректные данные

## Фактический результат
- Адрес сохраняется. Открывается страница https://demowebshop.tricentis.com/customer/addresses со всеми добавленными адресами
