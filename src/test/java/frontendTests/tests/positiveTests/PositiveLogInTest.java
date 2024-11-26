package frontendTests.tests.positiveTests;

import frontendTests.pages.LogInPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.ExistingUser;
import frontendTests.utils.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.assertTrue;

@TestCase(infoAboutCase = "PositiveLogInCases",
        path = "frontendTests/testCases/logInCases/PositiveLogInCases.md")
public class PositiveLogInTest extends BaseTest {

    private LogInPage logInPage;

    @BeforeMethod
    protected void setUpLogInPage() {
        logInPage = new LogInPage(getDriver());
    }

    public boolean isElementVisibleAndContainsText(By locator, String text, int waitingSeconds) {
        WebElement element = waitUtils.waitForConditionAndReturn(
                ExpectedConditions.visibilityOfElementLocated(locator),
                waitingSeconds
        );

        return element.getText().contains(text);
    }

    @Test(description = "Authorization without the Remember me button")
    public void testLogInWithoutRememberMe() {
        logger.info("Start positive testLogInWithoutRememberMe");

        final String emailUser = ExistingUser.getEmailExistingUser();

        logInPage.openLogInPageChain()
                .enterEmailChain(emailUser)
                .enterPasswordChain(ExistingUser.getPasswordExistingUser())
                .clickLogIn();

        assertTrue(isElementVisibleAndContainsText(
                By.xpath("//div[@class='header-links']//a[@class='account']"),
                emailUser,
                15
        ));

        logger.info("Finish positive testLogInWithoutRememberMe");
    }

    @Test(description = "Authorization with the remember me button")
    public void testLogInWithRememberMe() {
        logger.info("Start positive testLogInWithRememberMe");

        final String emailUser = ExistingUser.getEmailExistingUser();

        logInPage.openLogInPageChain()
                .enterEmailChain(emailUser)
                .enterPasswordChain(ExistingUser.getPasswordExistingUser())
                .chooseRememberMeChain()
                .clickLogIn();

        assertTrue(isElementVisibleAndContainsText(
                By.xpath("//div[@class='header-links']//a[@class='account']"),
                emailUser,
                15
        ));

        logger.info("Finish positive testLogInWithRememberMe");
    }

    @Test(description = "Switching from the authorization page to the registration page")
    public void testLogInSwitchingToRegisterPage() {
        logger.info("Start positive testLogInSwitchingToRegisterPage");

        logInPage.openLogInPageChain().clickRegister();

        waitUtils.waitForCondition(
                ExpectedConditions.urlContains("/register"),
                10
        );

        assertTrue(logInPage.atRegisterPageFromLogInPage());

        logger.info("Finish positive testLogInSwitchingToRegisterPage");
    }

    @Test(description = "switching to the password recovery page")
    public void testLogInSwitchingToRecoverPasswordPage() {
        logger.info("Start positive testLogInSwitchingToRecoverPasswordPage");

        logInPage.openLogInPageChain().chooseForgotPassword();

        waitUtils.waitForCondition(
                ExpectedConditions.urlContains("/passwordrecovery"),
                10
        );

        assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/passwordrecovery"));

        logger.info("Finish positive testLogInSwitchingToRecoverPasswordPage");
    }
}
