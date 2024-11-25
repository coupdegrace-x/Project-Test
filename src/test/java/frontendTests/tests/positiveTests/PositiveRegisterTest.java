package frontendTests.tests.positiveTests;

import frontendTests.pages.RegisterPage;
import frontendTests.pages.RegisterResultPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@TestCase(infoAboutCase = "PositiveRegisterCases",
        path = "frontendTests/testCases/registerCases/PositiveRegisterCases.md")
public class PositiveRegisterTest extends BaseTest {

    private RegisterPage registerPage;
    private RegisterResultPage registerResultPage;
    private String passwordRandomUser;

    @BeforeMethod
    protected void setUpRegisterAndResultPage() {
        registerPage = new RegisterPage(getDriver());
        registerResultPage = new RegisterResultPage(getDriver());
        passwordRandomUser = RandomUserData.getRandomPassword();
    }

    private void verifyRegistrationResult(String expectedTitle, String expectedBody, int waitingSeconds) {
        waitUtils.waitForCondition(ExpectedConditions
                .urlContains("registerresult/1"), waitingSeconds);

        Assert.assertTrue(registerResultPage.getTextPageTitle().contains(expectedTitle),
                "Incorrect title text");

        Assert.assertTrue(registerResultPage.getTextPageBody().contains(expectedBody),
                "Incorrect body text");

        Assert.assertTrue(registerResultPage.atRegisterResultPage());
    }

    @Test(description = "Successful user registration based on fake data, male gender")
    public void testRegisterWithMaleGender() {
        logger.info("Start positive testRegisterWithMaleGender");

        registerPage.openRegisterPageChain()
                .chooseMaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(passwordRandomUser)
                .enterConfirmPasswordChain(passwordRandomUser)
                .clickRegisterButton();

        verifyRegistrationResult("Register",
                "Your registration completed",
                10);

        logger.info("Finish positive testRegisterWithMaleGender");
    }

    @Test(description = "Successful user registration based on fake data, gender female")
    public void testRegisterWithFemaleGender() {
        logger.info("Start positive testRegisterWithFemaleGender");

        registerPage.openRegisterPageChain()
                .chooseFemaleGenderChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(passwordRandomUser)
                .enterConfirmPasswordChain(passwordRandomUser)
                .clickRegisterButton();

        verifyRegistrationResult("Register",
                "Your registration completed",
                10);

        logger.info("Finish positive testRegisterWithFemaleGender");
    }

    @Test(description = "Successful user registration based on fake data, without gender")
    public void testRegisterWithNoGender() {
        logger.info("Start positive testRegisterWithNoGender");

        registerPage.openRegisterPageChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(passwordRandomUser)
                .enterConfirmPasswordChain(passwordRandomUser)
                .clickRegisterButton();

        verifyRegistrationResult("Register",
                "Your registration completed",
                10);

        logger.info("Finish positive testRegisterWithNoGender");
    }
}