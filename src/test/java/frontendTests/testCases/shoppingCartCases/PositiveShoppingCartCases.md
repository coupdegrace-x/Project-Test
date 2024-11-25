## Тест кейсы для позитивного сценария страницы корзины

- Примечание: корзина тестируется на основе добавления такого товара, как книги

## Реализация тест кейсов по автоматизации располагается по пути:

- `frontendTests/tests/positiveTests/PositiveShoppingCartTest.java`

## Ожидаемый результат:

- Успешное удаление книг из корзины
- Успешное изменение количества товаров в корзине
- Успешное оформление покупки товара

## Ожидаемый результат зависит от того:

1) Если пользователь добавил товары в корзину
2) Если пользователь ввел корректные данные

## Ссылки

- Для авторизации существующего пользователя https://demowebshop.tricentis.com/login
- Для регистрации нового пользователя https://demowebshop.tricentis.com/register
- Для перехода в корзину https://demowebshop.tricentis.com/cart

## Тест-кейс №1. Удаление товаров из корзины при выборе чек-бокса в графе Remove

## Шаги выполнения тест кейса №1 (Написан, метод: testShopCartRemoveProduct)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти во вкладку `Books` по ссылке https://demowebshop.tricentis.com/books
3) Нажать на `Add to cart` у всех книг
4) Перейти во вкладку `Shopping cart`по ссылке https://demowebshop.tricentis.com/cart
5) Нажать на чек-бокс у товара в графе `Remove`. Проверить, что отобразилась "галочка"
6) Нажать на `Update shopping cart`
7) Проверить, что товар удалился из корзины

## Тест-кейс №2. Удаление товаров из корзины при изменении количества товара в козине

## Шаги выполнения тест кейса №2 (Написан, метод: testShopCartRemoveProductQuantity)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти во вкладку `Books` по ссылке https://demowebshop.tricentis.com/books
3) Нажать на `Add to cart` у всех книг
4) Перейти во вкладку `Shopping cart`по ссылке https://demowebshop.tricentis.com/cart
5) В поле `Qty.`  изменить количество товара на `0`
6) Нажать на `Update shopping cart`
7) Проверить, что товар удалился из корзины

## Тест-кейс №3. Продолжить покупки при клике на `Continue shopping` в корзине

## Шаги выполнения тест кейса №3 (Написан, метод: testShoppingCartContinue)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти во вкладку `Books` по ссылке https://demowebshop.tricentis.com/books
3) Нажать на `Add to cart` у всех книг
4) Перейти во вкладку `Shopping cart`по ссылке https://demowebshop.tricentis.com/cart
5) Нажать на `Continue shopping`
6) Проверить, что после нажатия перекинет по ссылке https://demowebshop.tricentis.com/books

## Тест-кейс №4. Изменение количества товара в корзине при изменении данных в поле `Qty.`

## Шаги выполнения тест кейса №4 (Написан, метод: testShoppingCartChangeQuantity)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти во вкладку `Books` по ссылке https://demowebshop.tricentis.com/books
3) Нажать на `Add to cart` у всех книг
4) Перейти во вкладку `Shopping cart`по ссылке https://demowebshop.tricentis.com/cart
5) В поле `Qty.`  изменить количество товара на любое число, которое будет больше 1
6) Нажать на `Update shopping cart`
7) Проверить, что после нажатия обновилась цена в разделе `total`
