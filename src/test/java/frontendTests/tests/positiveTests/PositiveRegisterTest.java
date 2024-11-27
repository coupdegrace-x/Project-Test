package frontendTests.tests.positiveTests;

import frontendTests.pages.RegisterPage;
import frontendTests.pages.RegisterResultPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.assertTrue;

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

    @DataProvider(name = "genderOptions")
    private Object[][] genderOptions() {
        return new Object[][]{
                {"male"},
                {"female"},
                {null}
        };
    }

    @Test(dataProvider = "genderOptions",
            description = "Successful user registration based on fake data, male gender")
    public void testRegister(String gender) {
        logger.info("Start positive testRegister");

        if (Objects.equals(gender, "male")) {
            registerPage.openRegisterPageChain()
                    .chooseMaleGender();
        } else if (Objects.equals(gender, "female")) {
            registerPage.openRegisterPageChain()
                    .chooseFemaleGender();
        } else {
            registerPage.openRegisterPage();
        }

        registerPage
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(passwordRandomUser)
                .enterConfirmPasswordChain(passwordRandomUser)
                .clickRegisterButton();

        waitUtils.waitForCondition(
                ExpectedConditions.visibilityOf(registerResultPage.getPageBody()),
                10
        );

        assertTrue(registerResultPage.getPageTitle()
                        .getText()
                        .contains("Register"),
                "Incorrect title text");

        assertTrue(registerResultPage.getPageBody().
                        getText()
                        .contains("Your registration completed"),
                "Incorrect body text");

        assertTrue(registerResultPage.atRegisterResultPage());

        logger.info("Finish positive testRegister");
    }
}