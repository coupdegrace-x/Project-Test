package frontendTests.tests.negativeTests;

import frontendTests.pages.MyAccountInfoPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import frontendTests.utils.WaitUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@TestCase(infoAboutCase = "NegativeCustomerInfoCases",
        path = "frontendTests/testCases/myAccountCases/customerInfoCases/NegativeCustomerInfoCases.md")
public class NegativeCustomerInfoTest extends BaseTest {

    private MyAccountInfoPage myAccountInfoPage;
    private MyAccountPage myAccountPage;
    private RegisterPage registerPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    public void setUpMyAccAndLogInPage() {
        myAccountInfoPage = new MyAccountInfoPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());
        registerPage = new RegisterPage(getDriver());
        waitUtils = new WaitUtils(getDriver());
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
        logger.info("Start negative testInfoClearDataAndCheckErrors");

        userRegistration();

        myAccountPage.openCustomerInfoChain()
                .clearFirstNameChain()
                .clearLastNameChain()
                .clearEmailChain()
                .clickSaveButton();

        waitUtils.waitForCondition(ExpectedConditions
                        .textToBePresentInElement(myAccountInfoPage.getFirstNameError(),
                                "First name is required"),
                15);

        Assert.assertTrue(myAccountInfoPage.getTextFirstNameError().contains("First name is required"));
        Assert.assertTrue(myAccountInfoPage.getTextLastNameError().contains("Last name is required"));
        Assert.assertTrue(myAccountInfoPage.getTextEmailError().contains("Email is required"));

        logger.info("Finish negative testInfoClearDataAndCheckErrors");
    }

    @Test(description = "Clear the First name field on the My account " +
            "- customer info page and check the error displayed")
    private void testInfoClearFirstNameAndCheckError() {
        logger.info("Start negative testInfoClearFirstNameAndCheckError");

        userRegistration();

        myAccountPage.openCustomerInfoChain()
                .clearFirstNameChain()
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .clickSaveButton();

        waitUtils.waitForCondition(ExpectedConditions
                        .textToBePresentInElement(myAccountInfoPage.getFirstNameError(),
                                "First name is required"),
                15);

        Assert.assertTrue(myAccountInfoPage.getTextFirstNameError().contains("First name is required"));

        logger.info("Finish negative testInfoClearFirstNameAndCheckError");
    }
}
