## Тест кейсы для негативного сценария авторизации пользователя

## Реализация тест кейсов по автоматизации располагается по пути:

- `frontendTests/tests/positiveTests/PositiveLogInTest.java`

## Ожидаемый результат:

- Безуспешная авторизации существующего пользователя с выводом той или иной ошибки

## Ожидаемый результат зависит от того:

1) Если пользователь оставит все поля авторизации пустыми
2) Если пользователь забудет указать одно из полей авторизации
   (за исключением `Remember me`)

## Ссылки

- Для авторизации существующего пользователя: https://demowebshop.tricentis.com/login

## Шаги выполнения тест кейса №1 (Написан, метод: testLogInEmptyFields)

1) Перейти по ссылке https://demowebshop.tricentis.com/login
2) Оставить поле `Email` пустым
3) Оставь поле `Password` пустым
4) Нажать на кнопку `Log in`

## Шаги выполнения тест кейса №2 (Написан, метод: testLogInWithUnregisteredUser)

1) Перейти по ссылке https://demowebshop.tricentis.com/login
2) Ввести в поле `Email` почту незарегистрированного пользователя
3) Ввести в поле `Password` пароль зарегистрированного пользователя
4) Нажать на кнопку `Log in`

## Шаги выполнения тест кейса №3 (Написан, метод: testLogInWithInvalidPasswordForRegisteredUser)

1) Перейти по ссылке https://demowebshop.tricentis.com/login
2) Ввести в поле `Email` почту зарегистрированного пользователя
3) Ввести в поле `Password` неверный пароль для данного пользователя
4) Нажать на кнопку `Log in`

## Шаги выполнения тест кейса №4 (Написан, метод: testLogInWithEmptyPassFieldForRegisteredUser)

1) Перейти по ссылке https://demowebshop.tricentis.com/login
2) Ввести в поле `Email` почту зарегистрированного пользователя
3) Оставить поле `Password` пустым
4) Нажать на кнопку `Log in`

## Шаги выполнения тест кейса №5 (Написан, метод: testLogInWithEmptyEmailForRegisteredUser)

1) Перейти по ссылке https://demowebshop.tricentis.com/login
2) Оставить поле `Email` пустым
3) Ввести в поле `Password` пароль зарегистрированного пользователя
4) Нажать на кнопку `Log in`