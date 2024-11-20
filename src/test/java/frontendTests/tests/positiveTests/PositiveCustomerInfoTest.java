package frontendTests.tests.positiveTests;

import frontendTests.pages.MyAccountInfoPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

@TestCase(infoAboutCase = "PositiveCustomerInfoCases",
        path = "frontendTests/testCases/myAccountCases/customerInfoCases/PositiveCustomerInfoCases.md")
public class PositiveCustomerInfoTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositiveCustomerInfoTest.class);

    private MyAccountInfoPage myAccountInfoPage;
    private MyAccountPage myAccountPage;
    private RegisterPage registerPage;

    @BeforeMethod
    public void setUpMyAccAndRegPage() {
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

    @Test(description = "Changing all personal data to valid ones on the My account - customer info page")
    public void testInfoChangeAllDataToValid() {
        LOGGER.info("Start positive testChangeAllDataToValid");

        final String emailUser = RandomUserData.getRandomEmail();

        userRegistration();

        myAccountPage.openCustomerInfoChain()
                .chooseGenderFemaleChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(emailUser)
                .clickSaveButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(
                                By.xpath("//div[@class='header-links']//a[@class='account']")),
                        emailUser));

        Assert.assertTrue(getDriver()
                .findElement(By.xpath("//div[@class='header-links']//a[@class='account']"))
                .getText().contains(emailUser));

        LOGGER.info("Finish positive testChangeAllDataToValid");
    }

    @Test(description = "Name change on the My account page - information about the client")
    public void testInfoChangeName() {
        LOGGER.info("Start positive testInfoChangeName");

        final String firstNameUser = RandomUserData.getRandomFirstName();

        userRegistration();

        myAccountPage.openCustomerInfoChain()
                .enterFirstNameChain(firstNameUser)
                .clickSaveButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions
                        .attributeToBe(myAccountInfoPage.getFirstNameInputField(), "value", firstNameUser));

        Assert.assertTrue(Objects.requireNonNull(myAccountInfoPage.getFirstNameInputField()
                .getAttribute("value")).contains(firstNameUser));

        LOGGER.info("Finish positive testInfoChangeName");
    }

}
