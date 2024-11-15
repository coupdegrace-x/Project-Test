package frontendTests.tests.positiveTests;

import frontendTests.pages.RegisterPage;
import frontendTests.pages.RegisterResultPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/*
Класс PositiveRegisterTest преднозначен для позитивных тестовых сценариев
при регистрации (Вкладка Register на сайте) пользователя, связанных с UI частью.
 */
@TestCase(infoAboutCase = "PositiveRegisterCases",
        path = "frontendTests/testCases/registerCases/PositiveRegisterCases.md")
public class PositiveRegisterTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositiveRegisterTest.class);

    private RegisterPage registerPage;
    private RegisterResultPage registerResultPage;

    @BeforeMethod
    public void setUpRegisterPage() {
        registerPage = new RegisterPage(getDriver());
        registerResultPage = new RegisterResultPage(getDriver());
    }

    @Test(description = "Successful user registration based on fake data, male gender")
    public void testRegisterWithMaleGenderPositive() {
        LOGGER.info("Start testRegisterWithMaleGenderPositive");

        final String password = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .chooseMaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(password)
                .enterConfirmPasswordChain(password)
                .clickRegisterButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("registerresult/1"));

        Assert.assertTrue(registerResultPage.getTextPageTitle().contains("Register"),
                "Incorrect text");

        Assert.assertTrue(registerResultPage.getTextPageBody().contains("Your registration completed"),
                "Incorrect text");

        LOGGER.info("Finish testRegisterWithMaleGenderPositive");
    }

    @Test(description = "Successful user registration based on fake data, gender female")
    public void testRegisterWithFemaleGenderPositive() {
        LOGGER.info("Start testRegisterWithFemaleGenderPositive");

        final String password = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .chooseFemaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(password)
                .enterConfirmPasswordChain(password)
                .clickRegisterButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("registerresult/1"));

        Assert.assertTrue(registerResultPage.getTextPageTitle().contains("Register"),
                "Incorrect text");

        Assert.assertTrue(registerResultPage.getTextPageBody().contains("Your registration completed"),
                "Incorrect text");

        LOGGER.info("Finish testRegisterWithFemaleGenderPositive");
    }
}