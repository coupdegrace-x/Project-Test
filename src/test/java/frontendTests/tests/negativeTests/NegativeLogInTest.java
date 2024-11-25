package frontendTests.tests.negativeTests;

import frontendTests.pages.LogInPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.ExistingUser;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@TestCase(infoAboutCase = "NegativeLogInCases",
        path = "frontendTests/testCases/logInCases/NegativeLogInCases.md")
public class NegativeLogInTest extends BaseTest {

    private static final String COMMON_ERROR_MESSAGE = "Login was unsuccessful. " +
            "Please correct the errors and try again";

    private LogInPage logInPage;

    @BeforeMethod
    protected void setUpLogInPage() {
        logInPage = new LogInPage(getDriver());
    }

    public void assertVerifyValidationErrors(String specificErrorMessage) {
        waitUtils
                .waitForCondition(ExpectedConditions
                        .visibilityOf(logInPage.getSpecificMessageError()), 10);

        Assert.assertTrue(logInPage.getTextCommonValidationError()
                .contains(COMMON_ERROR_MESSAGE), "Common validation error message mismatch");

        Assert.assertTrue(logInPage.getTextSpecificValidationError()
                .contains(specificErrorMessage), "Specific validation error message mismatch");
    }

    @Test(description = "Unsuccessful user authorization with empty fields")
    public void testLogInEmptyFields() {
        logger.info("Start negative testLogInEmptyFields");

        logInPage.openLogInPageChain()
                .clickLogIn();

        assertVerifyValidationErrors("No customer account found");

        logger.info("Finish negative testLogInEmptyFields");
    }

    @Test(description = "Unsuccessful authorization of an unregistered user")
    public void testLogInWithUnregisteredUser() {
        logger.info("Start negative testLogInWithUnregisteredUser");

        logInPage.openLogInPageChain()
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(RandomUserData.getRandomPassword())
                .clickLogIn();

        assertVerifyValidationErrors("No customer account found");

        logger.info("Finish negative testLogInWithUnregisteredUser");
    }

    @Test(description = "Unsuccessful authorization of a registered user but not with the correct password")
    public void testLogInWithInvalidPasswordForRegisteredUser() {
        logger.info("Start negative testLogInWithInvalidPasswordForRegisteredUser");

        logInPage.openLogInPageChain()
                .enterEmailChain(ExistingUser.getEmailExistingUser())
                .enterPasswordChain(RandomUserData.getRandomPassword())
                .clickLogIn();

        assertVerifyValidationErrors("The credentials provided are incorrect");

        logger.info("Finish negative testLogInWithInvalidPasswordForRegisteredUser");
    }

    @Test(description = "Unsuccessful authorization of a registered user but not with the correct password")
    public void testLogInWithEmptyPassFieldForRegisteredUser() {
        logger.info("Start negative testLogInWithEmptyPassFieldForRegisteredUser");

        logInPage.openLogInPageChain()
                .enterEmailChain(ExistingUser.getEmailExistingUser())
                .clickLogIn();

        assertVerifyValidationErrors("The credentials provided are incorrect");

        logger.info("Finish negative testLogInWithEmptyPassFieldForRegisteredUser");
    }

    @Test(description = "Unsuccessful authorization of a registered user but with an empty email")
    public void testLogInWithEmptyEmailForRegisteredUser() {
        logger.info("Start negative testLogInWithEmptyEmailForRegisteredUser");

        logInPage.openLogInPageChain()
                .enterPasswordChain(ExistingUser.getPasswordExistingUser())
                .clickLogIn();

        assertVerifyValidationErrors("No customer account found");

        logger.info("Finish negative testLogInWithEmptyEmailForRegisteredUser");
    }
}
