## Тест кейсы для негативного сценария редактирования данных пользователя в разделе My Account - Customer Info

## Реализация тест кейсов по автоматизации располагается по пути:

- `frontendTests/tests/positiveTests/PositiveAddNewAddressTest.java`

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
- Профиль пользователя https://demowebshop.tricentis.com/customer/info

## Тест-кейс №1. Добавление нового адреса со всеми пустыми полями

## Шаги выполнения тест кейса №1 (Написан, метод: testAddNewAddressEmptyFields)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти в профиль пользователя https://demowebshop.tricentis.com/customer/info
3) Перейти во вкладку `Addresses`по ссылке https://demowebshop.tricentis.com/customer/addresses
4) Перейти по ссылке https://demowebshop.tricentis.com/customer/addressadd
5) Поле `First name` оставить пустым
6) В поле `Last name` оставить пустым
7) В поле `Email` оставить пустым
8) В поле `Company` оставить пустым
9) В поле `Country` оставить без изменений
10) Поле `State/province` оставить без изменений
11) В поле `City` оставить пустым
12) В поле `City` оставить пустым
13) В поле `Addresses 2` оставить пустым
14) В поле `Zip/postal code` оставить пустым
15) В поле `Phone number`оставить пустым
16) В поле `Fax number`оставить пустым
17) Нажать на кнопку `Save`.
18) Проверить, что появляются подсказки рядом с полями `First name`, `Last name`, `Email`, `City`, `Addresses 1`,
    `Zip/postal code`, `Phone number` о необходимости их заполнить

## Тест-кейс №2. Добавление нового адреса с заполнением всех полей валидными данными без указания поля Country

## Шаги выполнения тест кейса №2 (Написан, метод: testAddNewAddressWithWithoutCountryField)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти в профиль пользователя https://demowebshop.tricentis.com/customer/info
3) Перейти во вкладку `Addresses`по ссылке https://demowebshop.tricentis.com/customer/addresses
4) Нажать `Add new` во вкладке `Addresses`
5) После нажатия перекинет по ссылке https://demowebshop.tricentis.com/customer/addressadd
6) В поле `First name` ввести имя рандомного пользователя
7) В поле `Last name` ввести фамилию рандомного пользователя
8) В поле `Email` ввести валидную почту рандомного пользователя
9) В поле `Company` ввести рандомную компанию
10) Поле `Country` оставить пустым
11) Поле `State/province` пропустить
12) В поле `City` ввести рандомный город
13) В поле `Addresses 1` ввести первый рандомный адрес
14) В поле `Addresses 2` ввести второй рандомный адрес
15) В поле `Zip/postal code` ввести рандомный зип код
16) В поле `Phone number` ввести рандомный номер телефона
17) В поле `Fax number` ввести рандомный номер факса
18) Нажать на кнопку `Save`
19) Проверить, что появляется подсказка рядом с полем `Сountry` о необходимости его заполнить

## Тест-кейс №3. Добавление нового адреса с заполнением полей спецсимволами

## Шаги выполнения тест кейса №3 (Написан, метод: testAddNewAddressWithSpecialCharacters)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти в профиль пользователя https://demowebshop.tricentis.com/customer/info
3) Перейти во вкладку `Addresses`по ссылке https://demowebshop.tricentis.com/customer/addresses
4) Нажать `Add new` во вкладке `Addresses`
5) После нажатия перекинет по ссылке https://demowebshop.tricentis.com/customer/addressadd
6) В поле `First name` ввести спецсимволы
7) В поле `Last name` ввести спецсимволы
8) В поле `Email` ввести рандомный эмаил
9) В поле `Company` ввести спецсимволы
10) Поле `Country` выбрать из выпадающего списка рандомную страну
11) Поле `State/province` пропустить
12) В поле `City` ввести спецсимволы
13) В поле `Addresses 1` ввести спецсимволы
14) В поле `Addresses 2` ввести спецсимволы
15) В поле `Zip/postal code` ввести спецсимволы
16) В поле `Phone number` ввести спецсимволы
17) В поле `Fax number` ввести спецсимволы
18) Нажать на кнопку `Save`
19) Проверить, что удалось добавить адрес с спецсимволами

