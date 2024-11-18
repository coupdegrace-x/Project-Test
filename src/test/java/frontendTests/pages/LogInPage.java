package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

/*
Класс LogInPage предназначен для страницы Log In, где происходит авторизация пользователя
метод: openLogInPage - открывает страницу по ссылке
метод: enterEmail - вводит почту пользователя
метод: enterPassword - вводит пароль пользователя
метод: clickRememberMe - кликает по чекбоксу
метод: clickForgotPassword - кликает по строке, которая ведет на страницу для восстановления пароля
метод: clickLogIn - кликает по кнопе log in
метод: atLogInPage - проверяет находися ли мы на странице авторизации, возвращает true либо false
конструктор: LogInPage, кол-во конструкторов == 1
Также для каждого метода есть постфикс Chain для цепочки вызовов
 */
// https://demowebshop.tricentis.com/login
public class LogInPage {

    private final WebDriver driver;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Getter
    @FindBy(name = "Email")
    private WebElement emailInputField;

    @Getter
    @FindBy(name = "Password")
    private WebElement passwordInputField;

    @Getter
    @FindBy(name = "RememberMe")
    private WebElement rememberMeCheckBox;

    @Getter
    @FindBy(xpath = "//span[@class='forgot-password']/a")
    private WebElement forgotPasswordLink;

    @Getter
    @FindBy(className = "login-button")
    private WebElement logInButton;

    @Getter
    @FindBy(className = "register-button")
    private WebElement registerButton;

    public void openLogInPage() {
        driver.get("https://demowebshop.tricentis.com/login");

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.className("master-wrapper-page")));
    }

    public LogInPage openLogInPageChain() {
        driver.get("https://demowebshop.tricentis.com/login");

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.className("master-wrapper-page")));
        return this;
    }

    public void enterEmail(String email) {
        emailInputField.sendKeys(email);
    }

    public LogInPage enterEmailChain(String email) {
        emailInputField.sendKeys(email);
        return this;
    }


    public void enterPassword(String password) {
        passwordInputField.sendKeys(password);
    }

    public LogInPage enterPasswordChain(String password) {
        passwordInputField.sendKeys(password);
        return this;
    }

    public void clickRememberMe() {
        rememberMeCheckBox.click();
    }

    public LogInPage clickRememberMeChain() {
        rememberMeCheckBox.click();
        return this;
    }

    public void clickForgotPassword() {
        forgotPasswordLink.click();
    }

    public LogInPage clickForgotPasswordChain() {
        forgotPasswordLink.click();
        return this;
    }

    public void clickLogIn() {
        logInButton.click();
    }

    public LogInPage clickLogInChain() {
        logInButton.click();
        return this;
    }

    public void clickRegister() {
        registerButton.click();
    }

    public LogInPage clickRegisterChain() {
        registerButton.click();
        return this;
    }

    public boolean atLogInPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("/login");
    }
}
