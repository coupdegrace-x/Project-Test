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
    public void testRegisterEmptyFieldsNegative() {
        LOGGER.info("Start testRegisterEmptyFieldsNegative");

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

        LOGGER.info("Finish testRegisterEmptyFieldsNegative");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field first name")
    public void testRegisterEmptyFirstNameNegative() {
        LOGGER.info("Start testRegisterEmptyFirstNameNegative");

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

        LOGGER.info("Finish testRegisterEmptyFirstNameNegative");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field last name")
    public void testRegisterEmptyLastNameNegative() {
        LOGGER.info("Start testRegisterEmptyLastNameNegative");

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

        LOGGER.info("Finish testRegisterEmptyLastNameNegative");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field email")
    public void testRegisterEmptyEmailNegative() {
        LOGGER.info("Start testRegisterEmptyEmailNegative");

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

        LOGGER.info("Finish testRegisterEmptyEmailNegative");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field password")
    public void testRegisterEmptyPasswordNegative() {
        LOGGER.info("Start testRegisterEmptyPasswordNegative");

        registerPage.openRegisterPageChain()
                .chooseMaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterConfirmPasswordChain(RandomUserData.getRandomPassword())
                .clickRegisterButton();

        waitForVisibilityOfElement(10, registerPage.getPasswordRequired());

        Assert.assertTrue(registerPage.getTextPasswordRequired().contains("Password is required"));

        LOGGER.info("Finish testRegisterEmptyPasswordNegative");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field confirm password")
    public void testRegisterEmptyConfirmPasswordNegative() {
        LOGGER.info("Start testRegisterEmptyConfirmPasswordNegative");

        registerPage.openRegisterPageChain()
                .chooseFemaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(RandomUserData.getRandomPassword())
                .clickRegisterButton();

        waitForVisibilityOfElement(10, registerPage.getConfirmPasswordRequired());

        Assert.assertTrue(registerPage.getTextConfirmPasswordRequired().contains("Password is required"));

        LOGGER.info("Finish testRegisterEmptyConfirmPasswordNegative");
    }

    @Test(description = "Unsuccessful registration of an already registered user")
    public void testRegisterAlreadyRegisteredUserNegative() {
        LOGGER.info("Start testRegisterAlreadyRegisteredUserNegative");

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

        LOGGER.info("Finish testRegisterAlreadyRegisteredUserNegative");
    }
}
