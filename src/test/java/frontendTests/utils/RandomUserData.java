package frontendTests.utils;

import com.github.javafaker.Faker;

/*
Класс RandomUserData предназначен для генерации случайных данных пользователя на основе библиотеки javafaker,
все методы статические
 */
public class RandomUserData {

    private static final Faker FAKER = new Faker();

    public static String getRandomFirstName() {
        return FAKER.name().firstName();
    }

    public static String getRandomLastName() {
        return FAKER.name().lastName();
    }

    public static String getRandomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String getRandomPassword() {
        return FAKER.internet().password();
    }
}
