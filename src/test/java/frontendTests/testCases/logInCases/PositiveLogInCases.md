## Тест кейсы для позитивного сценария авторизации пользователя

## Реализация тест кейсов по автоматизации располагается по пути:

- `frontendTests/tests/positiveTests/PositiveLogInTest.java`

## Ожидаемый результат:

- Успешная авторизация существующего пользователя

## Ожидаемый результат зависит от того:

1) Если пользователь заполнит все поля авторизации
2) Если пользователь заполнит все поля авторизации и выберет чек-бокс `Remember me`

## Ссылки

- Для авторизации существующего пользователя: https://demowebshop.tricentis.com/login
- Автоматически открывается страница после успешной авторизации пользователя https://demowebshop.tricentis.com
- Автоматически открывается страница регистрации после перехода по кнопке
  `Register` https://demowebshop.tricentis.com/register
- Автоматически открывается страница восстановления пароля после перехода по ссылке `Forgot password?`
  https://demowebshop.tricentis.com/passwordrecovery

## Шаги выполнения тест кейса №1 (Написан, метод: testLogInWithoutRememberMe)

1) Перейти по ссылке https://demowebshop.tricentis.com/login
2) В поле `Email` ввести почту существующего пользователя
3) В поле `Passsword` ввести пароль существующего пользователя
4) Нажать на кнопку `Log in`
5) Проверить, что на странице, произошел вход в систему под данными пользователя
   (откроется главная страница https://demowebshop.tricentis.com.
   Вместо Register отобразится `email` пользователя)

## Шаги выполнения тест кейса №2 (Написан, метод: testLogInWithRememberMe)

1) Перейти по ссылке https://demowebshop.tricentis.com/login
2) В поле `Email` ввести почту существующего пользователя
3) В поле `Passsword` ввести пароль существующего пользователя
4) Нажать на кнопку `Log in`
5) Поставить галочку в поле `Remember me`
6) Проверить, что на странице, произошел вход в систему под данными пользователя
   (откроется главная страница https://demowebshop.tricentis.com.
   Вместо Register отобразится `email` пользователя)

## Шаги выполнения тест кейса №3(Написан, метод: testLogInSwitchingToRegisterPage)

1) Перейти по ссылке https://demowebshop.tricentis.com/login
2) Нажать на кнопу `Register`
3) Проверить, что открылась страница регистрации https://demowebshop.tricentis.com/register

## Шаги выполнения тест кейса №4(Написан, метод: testLogInSwitchingToRecoverPasswordPage)

- Примечание: !!! страница с восстановлением пароля не работает, проверено мануально. Не приходит письмо на почту !!!

1) Перейти по ссылке https://demowebshop.tricentis.com/login
2) Нажать на текст-ссылку `Forgot password?`
3) Проверить, что открылась страница восстановления пароля https://demowebshop.tricentis.com/passwordrecovery