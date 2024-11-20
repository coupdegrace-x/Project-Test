package frontendTests.tests.positiveTests;

import frontendTests.pages.MyAccountNewAddressPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomIndexForDropDown;
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
import java.util.Random;

@TestCase(infoAboutCase = "PositiveNewAddressCases",
        path = "frontendTests/testCases/myAccountCases/addressesCases/PositiveNewAddressCases.md")
public class PositiveAddNewAddressTest extends BaseTest implements RandomIndexForDropDown {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositiveAddNewAddressTest.class);

    private RegisterPage registerPage;
    private MyAccountNewAddressPage myAccountNewAddressPage;
    private MyAccountPage myAccountPage;

    @BeforeMethod
    public void setUpRegMyAccAddressPage() {
        registerPage = new RegisterPage(getDriver());
        myAccountNewAddressPage = new MyAccountNewAddressPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());
    }

    @Override
    public int getRandomIndexForDropDown() {
        return new Random().nextInt(0, 238);
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

    @Test(description = "Adding an address with filling in all fields with valid data")
    public void testAddNewAddressWithAllFieldsValidData() {
        LOGGER.info("Start positive testAddNewAddressWithAllFieldsValidData");

        userRegistration();

        String emailUser = RandomUserData.getRandomEmail();

        myAccountPage.openAddressesChain()
                .clickAddNewButtonChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(emailUser)
                .enterCompanyChain(RandomUserData.getCompanyName())
                .selectCountryChain(getRandomIndexForDropDown())
                .selectStateOrProvinceChain()
                .enterCityChain(RandomUserData.getCity())
                .enterFirstAddressChain(RandomUserData.getFirstAddress())
                .enterSecondAddressChain(RandomUserData.getSecondAddress())
                .enterZipPostalCodeChain(RandomUserData.getZipPostalCode())
                .enterPhoneNumberChain(RandomUserData.getPhoneNumber())
                .enterFaxNumberChain(RandomUserData.getFaxNumber())
                .clickSaveButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(myAccountNewAddressPage.getAddNewButton()));

        Assert.assertTrue(getDriver().findElement(By.className("email")).getText().contains(emailUser));

        LOGGER.info("Finish positive testAddNewAddressWithAllFieldsValidData");
    }

    @Test(description = "Adding an address with filling in all required fields with valid data")
    public void testAddNewAddressWithRequiredFieldsInvalidData() {
        LOGGER.info("Start positive testAddNewAddressWithRequiredFieldsInvalidData");

        String emailUser = RandomUserData.getRandomEmail();

        userRegistration();

        myAccountPage.openAddressesChain()
                .clickAddNewButtonChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(emailUser)
                .selectCountryChain(getRandomIndexForDropDown())
                .selectStateOrProvinceChain()
                .enterCityChain(RandomUserData.getCity())
                .enterFirstAddressChain(RandomUserData.getFirstAddress())
                .enterZipPostalCodeChain(RandomUserData.getZipPostalCode())
                .enterPhoneNumberChain(RandomUserData.getPhoneNumber())
                .clickSaveButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(myAccountNewAddressPage.getAddNewButton()));

        Assert.assertTrue(getDriver().findElement(By.className("email")).getText().contains(emailUser));

        LOGGER.info("Finish positive testAddNewAddressWithRequiredFieldsInvalidData");
    }
}
