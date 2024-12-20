## Тест кейсы для позитивного сценария редактирования данных пользователя в разделе My Account - Customer Info

## Реализация тест кейсов по автоматизации располагается по пути:

- `frontendTests/tests/positiveTests/PositiveCustomerInfoTest.java`

## Ожидаемый результат:

- Успешное редактирование данных существующего пользователя

## Ожидаемый результат зависит от того:

1) Если пользователь заполнит все поля Customer info
2) Если пользователь заполнит все поля Customer info и выберет Gender

## Ссылки

- Для авторизации существующего пользователя https://demowebshop.tricentis.com/login
- Для регистрации нового пользователя https://demowebshop.tricentis.com/register
- Для редактирования данных существующего пользователя: https://demowebshop.tricentis.com/customer/info

## Тест-кейс №1. Смена всех персональных данных на валидные на странице My account - customer info

## Шаги выполнения тест кейса №1 (Написан, метод: testPositiveCustomerInfoChangeData)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти по ссылке https://demowebshop.tricentis.com/customer/info
3) В поле `First name` ввести новое имя пользователя
4) В поле `Last name` ввести новую фамилию пользователя `
5) В поле `Email` ввести новую валидную почту пользователя
6) Нажать на кнопку `Save`
7) Обновить страницу. Проверить, что новые данные сохранились (изменились имя, фамилия, почта пользователя).
   После обновления меняется почта на новую в правом верхнем углу

## Тест-кейс №2. Смена имени в поле `First name` на странице My account - customer info

## Шаги выполнения тест кейса №2 (Написан, метод: testPositiveCustomerInfoChangeData)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти по ссылке https://demowebshop.tricentis.com/customer/info
3) В поле `First name` ввести новое имя пользователя
4) Поле `Last name` оставить без изменений
5) Поле `Email` оставить без изменений
6) Нажать на кнопку `Save`Обновить страницу. Проверить, что новые данные сохранились. Имя пользователя поменялось

## Тест-кейс №3. Смена имени в поле `Last name` на странице My account - customer info

## Шаги выполнения тест кейса №3 (Написан, метод: testPositiveCustomerInfoChangeData)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти по ссылке https://demowebshop.tricentis.com/customer/info
3) В поле `First name` оставить без изменений
4) Поле `Last name` ввести новую фамилию пользователя
5) Поле `Email` оставить без изменений
6) Нажать на кнопку `Save`Обновить страницу. Проверить, что новые данные сохранились. Имя пользователя поменялось

## Тест-кейс №4. Смена имени в поле `Email` на странице My account - customer info

## Шаги выполнения тест кейса №3 (Написан, метод: testPositiveCustomerInfoChangeData)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти по ссылке https://demowebshop.tricentis.com/customer/info
3) В поле `First name` оставить без изменений
4) Поле `Last name` оставить без изменений
5) Поле `Email` ввести новую почту пользователя
6) Нажать на кнопку `Save`Обновить страницу. Проверить, что новые данные сохранились. Имя пользователя поменялось


