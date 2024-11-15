package frontendTests.utils;

import com.github.javafaker.Faker;

/*
Класс RandomUserData предназначен для генерации случайных данных пользователя на основе библиотеки javafaker,
все методы статические
 */
public class RandomUserData {

    private static Faker faker = new Faker();

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }
}
