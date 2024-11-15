package frontendTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
метод: atRegisterPage - проверяет находимся ли мы на нужной URL(ссылке), возвращает true или false
конструктор: RegisterPage, кол-во конструкторов == 1
Также для каждого метода есть постфикс Chain для цепочки вызовов
 */
// https://demowebshop.tricentis.com/register
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

    public void openRegisterPage() {
        driver.get("https://demowebshop.tricentis.com/register");
    }

    public RegisterPage openRegisterPageChain() {
        driver.get("https://demowebshop.tricentis.com/register");
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

    public boolean atRegisterPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("register");
    }
}
