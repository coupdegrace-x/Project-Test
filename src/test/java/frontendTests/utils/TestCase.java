package frontendTests.utils;

/*
Аннотация TestCase предназначена для ссылки на тест кейс в проекте
 */
public @interface TestCase {
    String infoAboutCase();

    String path();
}
