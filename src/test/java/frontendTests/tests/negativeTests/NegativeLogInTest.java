package frontendTests.tests.negativeTests;

import frontendTests.pages.LogInPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.ExistingUser;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@TestCase(infoAboutCase = "NegativeLogInCases",
        path = "frontendTests/testCases/logInCases/NegativeLogInCases.md")
public class NegativeLogInTest extends BaseTest {

    private static final String COMMON_ERROR_MESSAGE = "Login was unsuccessful. " +
            "Please correct the errors and try again";
    private static final String SPECIFIC_ERROR_REGISTERED_USER = "The credentials provided are incorrect";
    private static final String SPECIFIC_ERROR_UNREGISTERED_USER = "No customer account found";

    private LogInPage logInPage;

    @BeforeMethod
    protected void setUpNegativeLogInTest() {
        logInPage = new LogInPage(getDriver());

        logInPage.openLogInPage();
    }

    private void assertVerifyValidationErrors(String specificErrorMessage) {
        waitUtils.waitForCondition(
                ExpectedConditions.visibilityOf(logInPage.getSpecificMessageError()),
                10
        );

        assertTrue(logInPage.getCommonValidationError()
                        .getText()
                        .contains(COMMON_ERROR_MESSAGE),
                "Common validation error message mismatch");

        assertTrue(logInPage.getSpecificMessageError()
                        .getText()
                        .contains(specificErrorMessage),
                "Specific validation error message mismatch");
    }

    private void performLoginAndVerifyError(String email, String password, String specificErrorMessage) {
        if (email != null) {
            logInPage.enterEmail(email);
        }
        if (password != null) {
            logInPage.enterPassword(password);
        }

        logInPage.clickLogIn();

        assertVerifyValidationErrors(specificErrorMessage);
    }

    @Test(description = "Unsuccessful user authorization with empty fields")
    public void testLogInEmptyFields() {
        logger.info("Start negative testLogInEmptyFields");

        performLoginAndVerifyError(
                null,
                null,
                SPECIFIC_ERROR_UNREGISTERED_USER
        );

        assertVerifyValidationErrors("No customer account found");

        logger.info("Finish negative testLogInEmptyFields");
    }

    @Test(description = "Unsuccessful authorization of an unregistered user")
    public void testLogInWithUnregisteredUser() {
        logger.info("Start negative testLogInWithUnregisteredUser");

        performLoginAndVerifyError(
                RandomUserData.getRandomEmail(),
                RandomUserData.getRandomPassword(),
                SPECIFIC_ERROR_UNREGISTERED_USER
        );

        logger.info("Finish negative testLogInWithUnregisteredUser");
    }

    @Test(description = "Unsuccessful authorization of a registered user but not with the correct password")
    public void testLogInWithInvalidPasswordForRegisteredUser() {
        logger.info("Start negative testLogInWithInvalidPasswordForRegisteredUser");

        performLoginAndVerifyError(
                ExistingUser.getEmail(),
                RandomUserData.getRandomPassword(),
                SPECIFIC_ERROR_REGISTERED_USER
        );

        logger.info("Finish negative testLogInWithInvalidPasswordForRegisteredUser");
    }

    @Test(description = "Unsuccessful authorization of a registered user but not with the correct password")
    public void testLogInWithEmptyPassFieldForRegisteredUser() {
        logger.info("Start negative testLogInWithEmptyPassFieldForRegisteredUser");

        performLoginAndVerifyError(
                ExistingUser.getEmail(),
                null,
                SPECIFIC_ERROR_REGISTERED_USER
        );

        logger.info("Finish negative testLogInWithEmptyPassFieldForRegisteredUser");
    }

    @Test(description = "Unsuccessful authorization of a registered user but with an empty email")
    public void testLogInWithEmptyEmailForRegisteredUser() {
        logger.info("Start negative testLogInWithEmptyEmailForRegisteredUser");

        performLoginAndVerifyError(
                null,
                ExistingUser.getPassword(),
                SPECIFIC_ERROR_UNREGISTERED_USER
        );

        logger.info("Finish negative testLogInWithEmptyEmailForRegisteredUser");
    }
}
