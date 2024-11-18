package frontendTests.tests.negativeTests;

import frontendTests.pages.LogInPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.ExistingUser;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@TestCase(infoAboutCase = "NegativeLogInCases",
        path = "frontendTests/testCases/signInCases/NegativeLogInCases.md")
public class NegativeLogInTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NegativeLogInTest.class);
    private static final String COMMON_ERROR_MESSAGE = "Login was unsuccessful. " +
            "Please correct the errors and try again";

    private LogInPage logInPage;

    @BeforeMethod
    public void setUpLogInPage() {
        logInPage = new LogInPage(getDriver());
    }

    public void verifyValidationErrors(String commonErrorMessage, String specificErrorMessage) {
        Assert.assertTrue(logInPage.getTextCommonValidationError()
                .contains(commonErrorMessage), "Common validation error message mismatch");
        Assert.assertTrue(logInPage.getTextSpecificValidationError()
                .contains(specificErrorMessage), "Specific validation error message mismatch");
    }

    @Test(description = "Unsuccessful user authorization with empty fields")
    public void testLogInEmptyFields() {
        LOGGER.info("Start negative testLogInEmptyFields");

        logInPage.openLogInPageChain()
                .clickLogIn();

        verifyValidationErrors(COMMON_ERROR_MESSAGE, "No customer account found");

        LOGGER.info("Finish negative testLogInEmptyFields");
    }

    @Test(description = "Unsuccessful authorization of an unregistered user")
    public void testLogInWithUnregisteredUser() {
        LOGGER.info("Start negative testLogInWithUnregisteredUser");

        logInPage.openLogInPageChain()
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(RandomUserData.getRandomPassword())
                .clickLogIn();

        verifyValidationErrors(COMMON_ERROR_MESSAGE, "No customer account found");

        LOGGER.info("Finish negative testLogInWithUnregisteredUser");
    }

    @Test(description = "Unsuccessful authorization of a registered user but not with the correct password")
    public void testLogInWithInvalidPasswordForRegisteredUser() {
        LOGGER.info("Start negative testLogInWithInvalidPasswordForRegisteredUser");

        logInPage.openLogInPageChain()
                .enterEmailChain(ExistingUser.getEmailExistingUser())
                .enterPasswordChain(RandomUserData.getRandomPassword())
                .clickLogIn();

        verifyValidationErrors(COMMON_ERROR_MESSAGE, "The credentials provided are incorrect");

        LOGGER.info("Finish negative testLogInWithInvalidPasswordForRegisteredUser");
    }

    @Test(description = "Unsuccessful authorization of a registered user but not with the correct password")
    public void testLogInWithEmptyPassFieldForRegisteredUser() {
        LOGGER.info("Start negative testLogInWithEmptyPassFieldForRegisteredUser");

        logInPage.openLogInPageChain()
                .enterEmailChain(ExistingUser.getEmailExistingUser())
                .clickLogIn();

        verifyValidationErrors(COMMON_ERROR_MESSAGE, "The credentials provided are incorrect");

        LOGGER.info("Finish negative testLogInWithEmptyPassFieldForRegisteredUser");
    }

    @Test(description = "Unsuccessful authorization of a registered user but with an empty email")
    public void testLogInWithEmptyEmailForRegisteredUser() {
        LOGGER.info("Start negative testLogInWithEmptyEmailForRegisteredUser");

        logInPage.openLogInPageChain()
                .enterPasswordChain(ExistingUser.getPasswordExistingUser())
                .clickLogIn();

        verifyValidationErrors(COMMON_ERROR_MESSAGE, "No customer account found");

        LOGGER.info("Finish negative testLogInWithEmptyEmailForRegisteredUser");
    }
}
