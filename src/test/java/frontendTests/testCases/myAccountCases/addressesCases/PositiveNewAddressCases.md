## Тест кейсы для позитивного сценария добавления адреса в разделе My Account - Addresses

## Реализация тест кейсов по автоматизации располагается по пути:

- `frontendTests/tests/positiveTests/PositiveAddNewAddressTest.java`

## Ожидаемый результат:

- Успешное добавление адреса

## Ожидаемый результат зависит от того:

1) Если пользователь заполнит все обязательные поля Addresses
2) Если пользователь заполнит все поля Addresses

## Ссылки

- Для авторизации существующего пользователя https://demowebshop.tricentis.com/login
- Для регистрации нового пользователя https://demowebshop.tricentis.com/register
- Для добавления адреса: https://demowebshop.tricentis.com/customer/addressadd
  или https://demowebshop.tricentis.com/customer/addresses
- Профиль пользователя https://demowebshop.tricentis.com/customer/info

## Тест-кейс №1. Добавление адреса с заполнением всех полей валидными данными

## Шаги выполнения тест кейса №1 (Написан, метод: testAddNewAddressWithAllFieldsValidData)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти в профиль пользователя https://demowebshop.tricentis.com/customer/info
3) Перейти во вкладку `Addresses`по ссылке https://demowebshop.tricentis.com/customer/addresses
4) Нажать `Add new` во вкладке `Addresses`
5) После нажатия перекинет по ссылке https://demowebshop.tricentis.com/customer/addressadd
6) В поле `First name` ввести имя рандомного пользователя
7) В поле `Last name` ввести фамилию рандомного пользователя
8) В поле `Email` ввести валидную почту рандомного пользователя
9) В поле `Company` ввести рандомную компанию
10) В поле `Country` выбрать из выпадающего списка рандомную страну
11) Поле `State/province` из выпадающего списка выбрать единственный пункт `Other (Non US)`
12) В поле `City` ввести рандомный город
13) В поле `Addresses 1` ввести первый рандомный адрес
14) В поле `Addresses 2` ввести второй рандомный адрес
15) В поле `Zip/postal code` ввести рандомный зип код
16) В поле `Phone number` ввести рандомный номер телефона
17) В поле `Fax number` ввести рандомный номер факса
18) Нажать на кнопку `Save`
19) Проверить, что адрес сохраняется. Открывается страница https://demowebshop.tricentis.com/customer/addresses со всеми
    добавленными адресами

## Тест-кейс №2. Добавление адреса с заполнением всех обязательных полей валидными данными

## Шаги выполнения тест кейса №2 (Написан, метод: testAddNewAddressWithRequiredFieldsInvalidData)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти в профиль пользователя https://demowebshop.tricentis.com/customer/info
3) Перейти во вкладку `Addresses`по ссылке https://demowebshop.tricentis.com/customer/addresses
4) Нажать `Add new` во вкладке `Addresses`
5) После нажатия перекинет по ссылке https://demowebshop.tricentis.com/customer/addressadd
6) В поле `First name` ввести имя рандомного пользователя
7) В поле `Last name` ввести фамилию рандомного пользователя
8) В поле `Email` ввести валидную почту рандомного пользователя
9) В поле `Company` оставить пустым
10) В поле `Country` выбрать из выпадающего списка рандомную страну
11) Поле `State/province` из выпадающего списка выбрать единственный пункт `Other (Non US)`
12) В поле `City` ввести рандомный город
13) В поле `Addresses 1` ввести первый рандомный адрес
14) В поле `Addresses 2` оставить пустым
15) В поле `Zip/postal code` ввести рандомный зип код
16) В поле `Phone number` ввести рандомный номер телефона
17) В поле `Fax number` ввести рандомный номер факса
18) Нажать на кнопку `Save`
19) Проверить, что адрес сохраняется. Открывается страница https://demowebshop.tricentis.com/customer/addresses со всеми
    добавленными адресами


