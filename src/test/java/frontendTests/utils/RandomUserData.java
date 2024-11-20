package frontendTests.utils;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

/*
Класс RandomUserData предназначен для генерации случайных данных пользователя на основе библиотеки javafaker,
все методы статические
 */
public class RandomUserData {
    private static final Faker FAKER = new Faker();
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+-=[]{}|;:'\",.<>?/";

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
        return FAKER.internet().password(6, 15);
    }

    public static String getCompanyName() {
        return FAKER.company().name();
    }

    public static String getCity() {
        return FAKER.address().city();
    }

    public static String getFirstAddress() {
        return FAKER.address().streetAddress();
    }

    public static String getSecondAddress() {
        return FAKER.address().streetAddressNumber();
    }

    public static String getZipPostalCode() {
        return FAKER.address().zipCode();
    }

    public static String getPhoneNumber() {
        return FAKER.phoneNumber().phoneNumber();
    }

    public static String getFaxNumber() {
        return String.valueOf(FAKER.number().randomNumber(11, true));
    }

    public static String getSpecialCharacters() {
        int length = (int) (Math.random() * 10) + 1;
        return RandomStringUtils.random(length, SPECIAL_CHARACTERS);
    }

}
