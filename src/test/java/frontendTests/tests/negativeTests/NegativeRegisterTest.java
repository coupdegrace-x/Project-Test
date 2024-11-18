package frontendTests.tests.negativeTests;

import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.ExistingUser;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/*
Класс NegativeRegisterTest преднозначен для негативных тестовых сценариев
при регистрации (Вкладка Register на сайте) пользователя
 */
@TestCase(infoAboutCase = "NegativeRegisterCases",
        path = "frontendTests/testCases/registerCases/NegativeRegisterCases.md")
public class NegativeRegisterTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NegativeRegisterTest.class);

    private RegisterPage registerPage;

    @BeforeMethod
    public void setUpRegisterPage() {
        registerPage = new RegisterPage(getDriver());
    }

    private void waitForVisibilityOfElement(int waitingSeconds, WebElement element) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(waitingSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    @Test(description = "Unsuccessful user registration with empty registration fields")
    public void testRegisterEmptyFields() {
        LOGGER.info("Start negative testRegisterEmptyFields");

        registerPage.openRegisterPageChain()
                .clickRegisterButton();

        waitForVisibilityOfElement(10, registerPage.getFirstNameRequired());
        waitForVisibilityOfElement(10, registerPage.getLastNameRequired());
        waitForVisibilityOfElement(10, registerPage.getEmailRequired());
        waitForVisibilityOfElement(10, registerPage.getPasswordRequired());
        waitForVisibilityOfElement(10, registerPage.getConfirmPasswordRequired());

        Assert.assertTrue(registerPage.getTextFirstNameRequired().contains("First name is required"));
        Assert.assertTrue(registerPage.getTextLastNameRequired().contains("Last name is required"));
        Assert.assertTrue(registerPage.getTextEmailRequired().contains("Email is required"));
        Assert.assertTrue(registerPage.getTextPasswordRequired().contains("Password is required"));
        Assert.assertTrue(registerPage.getTextConfirmPasswordRequired().contains("Password is required"));

        LOGGER.info("Finish negative testRegisterEmptyFields");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field first name")
    public void testRegisterEmptyFirstName() {
        LOGGER.info("Start negative testRegisterEmptyFirstName");

        final String password = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .chooseFemaleGenderChain()
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(password)
                .enterConfirmPasswordChain(password)
                .clickRegisterButton();

        waitForVisibilityOfElement(10, registerPage.getFirstNameRequired());

        Assert.assertTrue(registerPage.getTextFirstNameRequired().contains("First name is required"));

        LOGGER.info("Finish negative testRegisterEmptyFirstName");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field last name")
    public void testRegisterEmptyLastName() {
        LOGGER.info("Start negative testRegisterEmptyLastName");

        final String password = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .chooseMaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(password)
                .enterConfirmPasswordChain(password)
                .clickRegisterButton();

        waitForVisibilityOfElement(10, registerPage.getLastNameRequired());

        Assert.assertTrue(registerPage.getTextLastNameRequired().contains("Last name is required"));

        LOGGER.info("Finish negative testRegisterEmptyLastName");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field email")
    public void testRegisterEmptyEmail() {
        LOGGER.info("Start negative testRegisterEmptyEmail");

        final String password = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .chooseFemaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterPasswordChain(password)
                .enterConfirmPasswordChain(password)
                .clickRegisterButton();

        waitForVisibilityOfElement(10, registerPage.getEmailRequired());

        Assert.assertTrue(registerPage.getTextEmailRequired().contains("Email is required"));

        LOGGER.info("Finish negative testRegisterEmptyEmail");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field password")
    public void testRegisterEmptyPassword() {
        LOGGER.info("Start negative testRegisterEmptyPassword");

        registerPage.openRegisterPageChain()
                .chooseMaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterConfirmPasswordChain(RandomUserData.getRandomPassword())
                .clickRegisterButton();

        waitForVisibilityOfElement(10, registerPage.getPasswordRequired());

        Assert.assertTrue(registerPage.getTextPasswordRequired().contains("Password is required"));

        LOGGER.info("Finish negative testRegisterEmptyPassword");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field confirm password")
    public void testRegisterEmptyConfirmPassword() {
        LOGGER.info("Start negative testRegisterEmptyConfirmPassword");

        registerPage.openRegisterPageChain()
                .chooseFemaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(RandomUserData.getRandomPassword())
                .clickRegisterButton();

        waitForVisibilityOfElement(10, registerPage.getConfirmPasswordRequired());

        Assert.assertTrue(registerPage.getTextConfirmPasswordRequired().contains("Password is required"));

        LOGGER.info("Finish negative testRegisterEmptyConfirmPassword");
    }

    @Test(description = "Unsuccessful registration of an already registered user")
    public void testRegisterAlreadyRegisteredUser() {
        LOGGER.info("Start negative testRegisterAlreadyRegisteredUser");

        registerPage.openRegisterPageChain()
                .chooseMaleGenderChain()
                .enterFirstNameChain(ExistingUser.getFirstNameExistingUser())
                .enterLastNameChain(ExistingUser.getLastNameExistingUser())
                .enterEmailChain(ExistingUser.getEmailExistingUser())
                .enterPasswordChain(ExistingUser.getPasswordExistingUser())
                .enterConfirmPasswordChain(ExistingUser.getPasswordExistingUser())
                .clickRegisterButton();

        waitForVisibilityOfElement(10, registerPage.getErrorMessage());

        Assert.assertTrue(registerPage.getTextErrorMessage().contains("The specified email already exists"));

        LOGGER.info("Finish negative testRegisterAlreadyRegisteredUser");
    }
}
