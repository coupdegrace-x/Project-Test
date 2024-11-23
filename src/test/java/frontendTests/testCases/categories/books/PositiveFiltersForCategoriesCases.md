## Тест кейсы для позитивного сценария сортировки товаров по фильтрам и добавлением в корзину

- Примечание: товар для фильтрации выбран на основе вкладки `Books`

## Реализация тест кейсов по автоматизации располагается по пути:

- `frontendTests/tests/positiveTests/PositiveFiltersForCategories.java`

## Ожидаемый результат:

- Успешная сортировка книг по фильтрам

## Ожидаемый результат зависит от того:

1) От применения фильтров на странице

## Ссылки

- Для авторизации существующего пользователя https://demowebshop.tricentis.com/login
- Для регистрации нового пользователя https://demowebshop.tricentis.com/register
- Для перехода на страницу с книгами https://demowebshop.tricentis.com/books
- Корзина пользователя https://demowebshop.tricentis.com/customer/info

## Тест-кейс №1. Сортировка книг по Filter by price на странице товара Books при клике на Under 25.00

## Шаги выполнения тест кейса №1 (Написан, метод: testCategorySortByPriceUnderTwentyFive)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти во вкладку `Books`по ссылке https://demowebshop.tricentis.com/books
3) Фильтр `Sort by` оставить без изменений
4) Фильтр `Display per page` оставить без изменений
5) Фильтр `View as` оставить без изменений
6) Нажать на `Under 25.00` в разделе `Filter by price`
7) После нажатия перекинет по ссылке https://demowebshop.tricentis.com/books?price=-25
8) Проверить, что на странице отображаются книги стоимостью до 25.00

## Тест-кейс №2. Отмена фильтрации по Filter by price на странице товара Books при клике на Remove Filter

## Шаги выполнения тест кейса №2 (Написан, метод: testCategoryCancelFilter)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти во вкладку `Books`по ссылке https://demowebshop.tricentis.com/books
3) Фильтр `Sort by` оставить без изменений
4) Фильтр `Display per page` оставить без изменений
5) Фильтр `View as` оставить без изменений
6) Нажать на `25.00-50.00` в разделе `Filter by price`
7) После нажатия перекинет по ссылке https://demowebshop.tricentis.com/books?price=25-50.
8) Проверить, что на странице отображаются книги стоимостью от 25.00 до 50.00
9) Нажать на `Remove Filter` в разделе `Filter by price`
10) Проверить, что после нажатия, перекинуло на страницу https://demowebshop.tricentis.com/books и отобразились все
    книги

## Тест-кейс №3. Сортировка товара книги по Sort by при помощи Name: Z to A на странице Books

## Шаги выполнения тест кейса №3 (Написан, метод: testCategorySortBySortByZToA)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти во вкладку `Books`по ссылке https://demowebshop.tricentis.com/books
3) Фильтр Display per page оставить без изменений
4) Фильтр View as оставить без изменений
5) Фильтр Filter by price оставить без изменений
6) Нажать на Sort by. В выпадающем списке фильтра выбрать Name: Z to A
7) После нажатия перекинет по ссылке https://demowebshop.tricentis.com/books?orderby=6
   Проверить, что книги отображены в алфавитном порядке

## Тест-кейс №4. Сортировка книг по Display per page на странице Books

## Шаги выполнения тест кейса №4 (Написан, метод: testCategorySortByDisplayFourPerPage)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти во вкладку `Books`по ссылке https://demowebshop.tricentis.com/books
3) Фильтр View as оставить без изменений
4) Фильтр Filter by price оставить без изменений
5) Фильтр Sort by оставить без изменений
6) Нажать на Display per page. В выпадающем списке фильтра выбрать 4
7) После нажатия перекинет по ссылке https://demowebshop.tricentis.com/books?pagesize=4
   Проверить, что на странице отображены не более 4-х книг. В правом нижнем углу появились кнопки перехода между
   страницами 1, 2, Next

## Тест-кейс №5. Сортировка книг по View на странице Books

## Шаги выполнения тест кейса №5 (Написан, метод: testCategorySortByViewAsList)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти во вкладку `Books`по ссылке https://demowebshop.tricentis.com/books
3) Фильтр Filter by price оставить без изменений
4) Фильтр Sort by оставить без изменений
5) Фильтр Display per page оставить без изменений
6) Нажать на View as. В выпадающем списке фильтра выбрать List
7) После нажатия перекинет по ссылке https://demowebshop.tricentis.com/books?viewmode=list
   Проверить, что на странице книги отображены в виде списка

## Тест-кейс №6. Проверка сплывающего предупреждения при добавлении товара в корзину

## Шаги выполнения тест кейса №6 (Написан, метод: testCategoryCheckWarningWhenAddingItemToCart)

1) Зарегистрироваться под новым пользователем по ссылке https://demowebshop.tricentis.com/register
2) Перейти во вкладку `Books`по ссылке https://demowebshop.tricentis.com/books
3) Нажать на  `Add to cart` под любой из книг
4) Ненадолго появляется информационное окно о том,
   что продукт добавлен в корзину с текстом `The product has been added to your shopping cart`
5) Проверить, что в правом верхнем углу у раздела `Shopping cart` в круглых скобках появилось число с количеством
   добавленных товаров

