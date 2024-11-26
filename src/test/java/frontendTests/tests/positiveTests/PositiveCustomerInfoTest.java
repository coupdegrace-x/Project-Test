package frontendTests.tests.positiveTests;

import frontendTests.pages.MyAccountInfoPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.RegistrationOfRandomUser;
import frontendTests.utils.TestCase;
import frontendTests.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.assertTrue;

@TestCase(infoAboutCase = "PositiveCustomerInfoCases",
        path = "frontendTests/testCases/myAccountCases/customerInfoCases/PositiveCustomerInfoCases.md")
public class PositiveCustomerInfoTest extends BaseTest {

    private MyAccountInfoPage myAccountInfoPage;
    private MyAccountPage myAccountPage;
    private RegisterPage registerPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    protected void setUpMyAccAndRegPage() {
        myAccountInfoPage = new MyAccountInfoPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());
        registerPage = new RegisterPage(getDriver());
        waitUtils = new WaitUtils(getDriver());
    }

    @Test(description = "Changing all personal data to valid ones on the My account - customer info page")
    public void testInfoChangeAllDataToValid() {
        logger.info("Start positive testChangeAllDataToValid");

        final String emailUser = RandomUserData.getRandomEmail();

        new RegistrationOfRandomUser().userRegistration(registerPage);

        myAccountPage.openCustomerInfoChain()
                .chooseGenderFemaleChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(emailUser)
                .clickSaveButton();

        waitUtils.waitForCondition(ExpectedConditions.textToBePresentInElement(
                        getDriver().findElement(By.xpath(
                                "//div[@class='header-links']//a[@class='account']")),
                        emailUser),
                15
        );

        assertTrue(getDriver().findElement(
                        By.xpath("//div[@class='header-links']//a[@class='account']"))
                .getText().contains(emailUser)
        );

        logger.info("Finish positive testChangeAllDataToValid");
    }

    @Test(description = "Name change on the My account page - information about the client")
    public void testInfoChangeName() {
        logger.info("Start positive testInfoChangeName");

        final String firstNameUser = RandomUserData.getRandomFirstName();

        new RegistrationOfRandomUser().userRegistration(registerPage);

        myAccountPage.openCustomerInfoChain()
                .enterFirstNameChain(firstNameUser)
                .clickSaveButton();

        waitUtils.waitForCondition(ExpectedConditions.attributeToBe(
                        myAccountInfoPage.getFirstNameInputField(),
                        "value", firstNameUser),
                15
        );

        assertTrue(Objects.requireNonNull(myAccountInfoPage.getFirstNameInputField()
                .getAttribute("value")).contains(firstNameUser));

        logger.info("Finish positive testInfoChangeName");
    }
}
