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

// https://demowebshop.tricentis.com/register
@Getter
public class RegisterPage {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "gender-male")
    private WebElement radioButtonMaleGender;

    @FindBy(id = "gender-female")
    private WebElement radioButtonFemaleGender;

    @FindBy(id = "FirstName")
    private WebElement inputFieldFirstName;

    @FindBy(id = "LastName")
    private WebElement inputFieldLastName;

    @FindBy(id = "Email")
    private WebElement inputFieldEmail;

    @FindBy(id = "Password")
    private WebElement inputFieldPassword;

    @FindBy(id = "ConfirmPassword")
    private WebElement inputFieldConfirmPassword;

    @FindBy(id = "register-button")
    private WebElement buttonRegister;

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
        radioButtonMaleGender.click();
    }

    public RegisterPage chooseMaleGenderChain() {
        radioButtonMaleGender.click();
        return this;
    }

    public void chooseFemaleGender() {
        radioButtonFemaleGender.click();
    }

    public RegisterPage chooseFemaleGenderChain() {
        radioButtonFemaleGender.click();
        return this;
    }

    public void enterFirstName(String firstName) {
        inputFieldFirstName.sendKeys(firstName);
    }

    public RegisterPage enterFirstNameChain(String firstName) {
        inputFieldFirstName.sendKeys(firstName);
        return this;
    }

    public void enterLastName(String lastName) {
        inputFieldLastName.sendKeys(lastName);
    }

    public RegisterPage enterLastNameChain(String lastName) {
        inputFieldLastName.sendKeys(lastName);
        return this;
    }

    public void enterEmail(String email) {
        inputFieldEmail.sendKeys(email);
    }

    public RegisterPage enterEmailChain(String email) {
        inputFieldEmail.sendKeys(email);
        return this;
    }

    public void enterPassword(String password) {
        inputFieldPassword.sendKeys(password);
    }

    public RegisterPage enterPasswordChain(String password) {
        inputFieldPassword.sendKeys(password);
        return this;
    }

    public void enterConfirmPassword(String confirmPassword) {
        inputFieldConfirmPassword.sendKeys(confirmPassword);
    }

    public RegisterPage enterConfirmPasswordChain(String confirmPassword) {
        inputFieldConfirmPassword.sendKeys(confirmPassword);
        return this;
    }

    public void clickRegisterButton() {
        buttonRegister.click();
    }

    public RegisterPage clickRegisterButtonChain() {
        buttonRegister.click();
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
