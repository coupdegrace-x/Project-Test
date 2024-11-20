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
Класс RegisterPage предназначен для страницы Register, где происходит регистрация пользователя
метод: openRegisterPage - открывает страницу по ссылке
метод: chooseMaleGender и chooseFemaleGender - выбирает пол пользователя
метод: enterFirstName - вводит имя пользователя
метод: enterLastName - вводит фамилию пользователя
метод: enterEmail - вводит почту пользователя
метод: enterPassword - вводит пароль пользователя
метод: enterConfirmPassword - вводит пароль пользователя для подтверждения
метод: clickRegisterButton - кликает по кнопке регистрации
метод: getTextFirstNameRequired - возвращает строку-информацию, что требуется ввести имя
метод: getTextLastNameRequired - возвращает строку-информацию, что требуется ввести фамилию
метод: getTextEmailRequired - возвращает строку-информацию, что требуется ввести эмаил
метод: getTextPasswordRequired - возвращает строку-информацию, что требуется ввести пароль
метод: getTextConfirmPasswordRequired - возвращает строку-информацию, что требуется подтвердить пароль
метод: getTextErrorMessage - возвращает строку-информацию, что пользователь с такой почтой уже создан
метод: atRegisterPage - проверяет находимся ли мы на нужной URL(ссылке), возвращает true или false
конструктор: RegisterPage, кол-во конструкторов == 1
Также для каждого метода есть постфикс Chain для цепочки вызовов
 */
// https://demowebshop.tricentis.com/register
@Getter
public class RegisterPage {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "gender-male")
    private WebElement maleGenderButton;

    @FindBy(id = "gender-female")
    private WebElement femaleGenderButton;

    @FindBy(id = "FirstName")
    private WebElement firstNameInputField;

    @FindBy(id = "LastName")
    private WebElement lastNameInputField;

    @FindBy(id = "Email")
    private WebElement emailInputField;

    @FindBy(id = "Password")
    private WebElement passwordInputField;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordInputField;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(xpath = "//span[@for='FirstName']")
    private WebElement firstNameRequired;

    @FindBy(xpath = "//span[@for='LastName']")
    private WebElement lastNameRequired;

    @FindBy(xpath = "//span[@for='Email']")
    private WebElement emailRequired;

    @FindBy(xpath = "//span[@for='Password']")
    private WebElement passwordRequired;

    @FindBy(xpath = "//span[@for='ConfirmPassword']")
    private WebElement confirmPasswordRequired;

    @FindBy(xpath = "//div[@class='validation-summary-errors']//li")
    private WebElement errorMessage;

    public void openRegisterPage() {
        driver.get("https://demowebshop.tricentis.com/register");

        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.className("master-wrapper-page")));
    }

    public RegisterPage openRegisterPageChain() {
        driver.get("https://demowebshop.tricentis.com/register");

        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.className("master-wrapper-page")));
        return this;
    }

    public void chooseMaleGender() {
        maleGenderButton.click();
    }

    public RegisterPage chooseMaleGenderChain() {
        maleGenderButton.click();
        return this;
    }

    public void chooseFemaleGender() {
        femaleGenderButton.click();
    }

    public RegisterPage chooseFemaleGenderChain() {
        femaleGenderButton.click();
        return this;
    }

    public void enterFirstName(String firstName) {
        firstNameInputField.sendKeys(firstName);
    }

    public RegisterPage enterFirstNameChain(String firstName) {
        firstNameInputField.sendKeys(firstName);
        return this;
    }

    public void enterLastName(String lastName) {
        lastNameInputField.sendKeys(lastName);
    }

    public RegisterPage enterLastNameChain(String lastName) {
        lastNameInputField.sendKeys(lastName);
        return this;
    }

    public void enterEmail(String email) {
        emailInputField.sendKeys(email);
    }

    public RegisterPage enterEmailChain(String email) {
        emailInputField.sendKeys(email);
        return this;
    }

    public void enterPassword(String password) {
        passwordInputField.sendKeys(password);
    }

    public RegisterPage enterPasswordChain(String password) {
        passwordInputField.sendKeys(password);
        return this;
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordInputField.sendKeys(confirmPassword);
    }

    public RegisterPage enterConfirmPasswordChain(String confirmPassword) {
        confirmPasswordInputField.sendKeys(confirmPassword);
        return this;
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public RegisterPage clickRegisterButtonChain() {
        registerButton.click();
        return this;
    }

    public String getTextFirstNameRequired() {
        return firstNameRequired.getText();
    }

    public String getTextLastNameRequired() {
        return lastNameRequired.getText();
    }

    public String getTextEmailRequired() {
        return emailRequired.getText();
    }

    public String getTextPasswordRequired() {
        return passwordRequired.getText();
    }

    public String getTextConfirmPasswordRequired() {
        return confirmPasswordRequired.getText();
    }

    public String getTextErrorMessage() {
        return errorMessage.getText();
    }

    public boolean atRegisterPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("register");
    }
}
