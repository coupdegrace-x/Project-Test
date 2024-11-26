package frontendTests.pages;

import frontendTests.utils.WaitUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

// https://demowebshop.tricentis.com/login
@Getter
public class LogInPage {

    private final WebDriver driver;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "Email")
    private WebElement inputFieldEmail;

    @FindBy(name = "Password")
    private WebElement inputFieldPassword;

    @FindBy(name = "RememberMe")
    private WebElement checkBoxRememberMe;

    @FindBy(xpath = "//span[@class='forgot-password']/a")
    private WebElement linkForgotPassword;

    @FindBy(className = "login-button")
    private WebElement buttonLogIn;

    @FindBy(className = "register-button")
    private WebElement buttonRegister;

    @FindBy(xpath = "//div[@class='validation-summary-errors']//span")
    private WebElement commonValidationError;

    @FindBy(xpath = "//div[@class='validation-summary-errors']//li")
    private WebElement specificMessageError;

    public void openLogInPage() {
        driver.get("https://demowebshop.tricentis.com/login");

        new WaitUtils(driver).waitForCondition(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.className("master-wrapper-page")),
                20);
    }

    public LogInPage openLogInPageChain() {
        driver.get("https://demowebshop.tricentis.com/login");

        new WaitUtils(driver).waitForCondition(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.className("master-wrapper-page")),
                20);

        return this;
    }

    public void enterEmail(String email) {
        inputFieldEmail.sendKeys(email);
    }

    public LogInPage enterEmailChain(String email) {
        inputFieldEmail.sendKeys(email);
        return this;
    }


    public void enterPassword(String password) {
        inputFieldPassword.sendKeys(password);
    }

    public LogInPage enterPasswordChain(String password) {
        inputFieldPassword.sendKeys(password);
        return this;
    }

    public void chooseRememberMe() {
        checkBoxRememberMe.click();
    }

    public LogInPage chooseRememberMeChain() {
        checkBoxRememberMe.click();
        return this;
    }

    public void chooseForgotPassword() {
        linkForgotPassword.click();
    }

    public LogInPage chooseForgotPasswordChain() {
        linkForgotPassword.click();
        return this;
    }

    public void clickLogIn() {
        buttonLogIn.click();
    }

    public LogInPage clickLogInChain() {
        buttonLogIn.click();
        return this;
    }

    public void clickRegister() {
        buttonRegister.click();
    }

    public LogInPage clickRegisterChain() {
        buttonRegister.click();
        return this;
    }

    public boolean atLogInPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("/login");
    }

    public boolean atRegisterPageFromLogInPage() {
        return new RegisterPage(driver).atRegisterPage();
    }
}
