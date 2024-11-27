package frontendTests.utils;

public class ExistingUser {

    private static final String FIRST_NAME = "Leon";
    private static final String LAST_NAME = "Stein";
    private static final String EMAIL = "LeonidBurshtein@yandex.ru";
        private static final String PASSWORD = "123456";

    public static String getFirstNameExistingUser() {
        return FIRST_NAME;
    }

    public static String getLastNameExistingUser() {
        return LAST_NAME;
    }

    public static String getEmailExistingUser() {
        return EMAIL;
    }

    public static String getPasswordExistingUser() {
        return PASSWORD;
    }
}
