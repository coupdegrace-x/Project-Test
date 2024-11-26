package frontendTests.tests.negativeTests;

import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.ExistingUser;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@TestCase(infoAboutCase = "NegativeRegisterCases",
        path = "frontendTests/testCases/registerCases/NegativeRegisterCases.md")
public class NegativeRegisterTest extends BaseTest {

    private RegisterPage registerPage;

    @BeforeMethod
    protected void setUpRegisterPage() {
        registerPage = new RegisterPage(getDriver());
    }

    @Test(description = "Unsuccessful user registration with empty registration fields")
    public void testRegisterEmptyFields() {
        logger.info("Start negative testRegisterEmptyFields");

        registerPage.openRegisterPageChain().clickRegisterButton();

        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(
                        registerPage.getFirstNameRequired()),
                10
        );

        assertTrue(registerPage.getTextFirstNameRequired().contains("First name is required"));
        assertTrue(registerPage.getTextLastNameRequired().contains("Last name is required"));
        assertTrue(registerPage.getTextEmailRequired().contains("Email is required"));
        assertTrue(registerPage.getTextPasswordRequired().contains("Password is required"));
        assertTrue(registerPage.getTextConfirmPasswordRequired().contains("Password is required"));

        logger.info("Finish negative testRegisterEmptyFields");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field first name")
    public void testRegisterEmptyFirstName() {
        logger.info("Start negative testRegisterEmptyFirstName");

        final String password = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .chooseFemaleGenderChain()
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(password)
                .enterConfirmPasswordChain(password)
                .clickRegisterButton();

        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(
                        registerPage.getFirstNameRequired()),
                10
        );

        assertTrue(registerPage.getTextFirstNameRequired().contains("First name is required"));

        logger.info("Finish negative testRegisterEmptyFirstName");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field last name")
    public void testRegisterEmptyLastName() {
        logger.info("Start negative testRegisterEmptyLastName");

        final String password = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .chooseMaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(password)
                .enterConfirmPasswordChain(password)
                .clickRegisterButton();

        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(
                        registerPage.getLastNameRequired()),
                10
        );

        assertTrue(registerPage.getTextLastNameRequired().contains("Last name is required"));

        logger.info("Finish negative testRegisterEmptyLastName");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field email")
    public void testRegisterEmptyEmail() {
        logger.info("Start negative testRegisterEmptyEmail");

        final String password = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .chooseFemaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterPasswordChain(password)
                .enterConfirmPasswordChain(password)
                .clickRegisterButton();

        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(
                        registerPage.getEmailRequired()),
                10
        );

        assertTrue(registerPage.getTextEmailRequired().contains("Email is required"));

        logger.info("Finish negative testRegisterEmptyEmail");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field password")
    public void testRegisterEmptyPassword() {
        logger.info("Start negative testRegisterEmptyPassword");

        registerPage.openRegisterPageChain()
                .chooseMaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterConfirmPasswordChain(RandomUserData.getRandomPassword())
                .clickRegisterButton();

        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(
                        registerPage.getPasswordRequired()),
                10);

        assertTrue(registerPage.getTextPasswordRequired().contains("Password is required"));

        logger.info("Finish negative testRegisterEmptyPassword");
    }

    @Test(description = "Unsuccessful registration of a user with an empty registration field confirm password")
    public void testRegisterEmptyConfirmPassword() {
        logger.info("Start negative testRegisterEmptyConfirmPassword");

        registerPage.openRegisterPageChain()
                .chooseFemaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(RandomUserData.getRandomPassword())
                .clickRegisterButton();

        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(
                        registerPage.getConfirmPasswordRequired()),
                10
        );

        assertTrue(registerPage.getTextConfirmPasswordRequired().contains("Password is required"));

        logger.info("Finish negative testRegisterEmptyConfirmPassword");
    }

    @Test(description = "Unsuccessful registration of an already registered user")
    public void testRegisterAlreadyRegisteredUser() {
        logger.info("Start negative testRegisterAlreadyRegisteredUser");

        registerPage.openRegisterPageChain()
                .chooseMaleGenderChain()
                .enterFirstNameChain(ExistingUser.getFirstNameExistingUser())
                .enterLastNameChain(ExistingUser.getLastNameExistingUser())
                .enterEmailChain(ExistingUser.getEmailExistingUser())
                .enterPasswordChain(ExistingUser.getPasswordExistingUser())
                .enterConfirmPasswordChain(ExistingUser.getPasswordExistingUser())
                .clickRegisterButton();

        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(
                        registerPage.getErrorMessage()),
                10
        );

        assertTrue(registerPage.getTextErrorMessage().contains("The specified email already exists"));

        logger.info("Finish negative testRegisterAlreadyRegisteredUser");
    }
}
