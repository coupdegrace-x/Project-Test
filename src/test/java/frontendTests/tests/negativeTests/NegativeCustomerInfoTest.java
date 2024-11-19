package frontendTests.tests.negativeTests;

import frontendTests.pages.MyAccountInfoPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
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

@TestCase(infoAboutCase = "NegativeCustomerInfoCases",
        path = "frontendTests/testCases/myAccountCases/customerInfoCases/NegativeCustomerInfoCases.md")
public class NegativeCustomerInfoTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NegativeCustomerInfoTest.class);

    private MyAccountInfoPage myAccountInfoPage;
    private MyAccountPage myAccountPage;
    private RegisterPage registerPage;

    @BeforeMethod
    public void setUpMyAccAndLogInPage() {
        myAccountInfoPage = new MyAccountInfoPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());
        registerPage = new RegisterPage(getDriver());
    }

    private void userRegistration() {
        final String passwordUser = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(passwordUser)
                .enterConfirmPasswordChain(passwordUser)
                .clickRegisterButton();
    }

    @Test(description = "Clear personal data on the My account - customer info page and check for output errors")
    private void testInfoClearDataAndCheckErrors() {
        LOGGER.info("Start negative testInfoClearDataAndCheckErrors");

        userRegistration();

        myAccountPage.openCustomerInfoChain()
                .clearFirstNameChain()
                .clearLastNameChain()
                .clearEmailChain()
                .clickSaveButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions
                        .textToBePresentInElement(myAccountInfoPage.getFirstNameError(), "First name is required"));

        Assert.assertTrue(myAccountInfoPage.getTextFirstNameError().contains("First name is required"));
        Assert.assertTrue(myAccountInfoPage.getTextLastNameError().contains("Last name is required"));
        Assert.assertTrue(myAccountInfoPage.getTextEmailError().contains("Email is required"));

        LOGGER.info("Finish negative testInfoClearDataAndCheckErrors");
    }

    @Test(description = "Clear the First name field on the My account " +
            "- customer info page and check the error displayed")
    private void testInfoClearFirstNameAndCheckError() {
        LOGGER.info("Start negative testInfoClearFirstNameAndCheckError");

        userRegistration();

        myAccountPage.openCustomerInfoChain()
                .clearFirstNameChain()
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .clickSaveButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions
                        .textToBePresentInElement(myAccountInfoPage.getFirstNameError(), "First name is required"));

        Assert.assertTrue(myAccountInfoPage.getTextFirstNameError().contains("First name is required"));

        LOGGER.info("Finish negative testInfoClearFirstNameAndCheckError");
    }
}
