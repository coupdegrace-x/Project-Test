package frontendTests.tests.positiveTests;

import frontendTests.pages.LogInPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.ExistingUser;
import frontendTests.utils.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

@TestCase(infoAboutCase = "PositiveLogInCases",
        path = "frontendTests/testCases/signInCases/PositiveLogInCases.md")
public class PositiveLogInTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositiveLogInTest.class);

    private LogInPage logInPage;

    @BeforeMethod
    public void setUpLogInPage() {
        logInPage = new LogInPage(getDriver());
    }

    public boolean isElementVisibleAndContainsText(By locator, String text, int timeoutInSeconds) {
        WebElement element = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText().contains(text);
    }

    @Test(description = "Authorization without the Remember me button")
    public void testLogInWithoutRememberMePositive() {
        LOGGER.info("Start testLogInWithoutRememberMePositive");

        final String emailUser = ExistingUser.getEmailExistingUser();

        logInPage.openLogInPageChain()
                .enterEmailChain(emailUser)
                .enterPasswordChain(ExistingUser.getPasswordExistingUser())
                .clickLogIn();

        Assert.assertTrue(isElementVisibleAndContainsText(
                By.xpath("//div[@class='header-links']//a[@class='account']"),
                emailUser,
                15
        ));

        LOGGER.info("Finish testLogInWithoutRememberMePositive");
    }

    @Test(description = "Authorization with the remember me button")
    public void testLogInWithRememberMePositive() {
        LOGGER.info("Start testLogInWithRememberMePositive");

        final String emailUser = ExistingUser.getEmailExistingUser();

        logInPage.openLogInPageChain()
                .enterEmailChain(emailUser)
                .enterPasswordChain(ExistingUser.getPasswordExistingUser())
                .clickRememberMeChain()
                .clickLogIn();

        Assert.assertTrue(isElementVisibleAndContainsText(
                By.xpath("//div[@class='header-links']//a[@class='account']"),
                emailUser,
                15
        ));

        LOGGER.info("Finish testLogInWithRememberMePositive");
    }
}
