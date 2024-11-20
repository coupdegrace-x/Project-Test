package frontendTests.tests.negativeTests;

import frontendTests.pages.MyAccountNewAddressPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomIndexForDropDown;
import frontendTests.utils.RandomUserData;
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

public class NegativeAddNewAddressTest extends BaseTest implements RandomIndexForDropDown {

    private static final Logger LOGGER = LoggerFactory.getLogger(NegativeAddNewAddressTest.class);

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

    @Test(description = "Adding a new address with all empty fields")
    public void testAddNewAddressEmptyFields() {
        LOGGER.info("Start negative testAddNewAddressEmptyFields");

        userRegistration();

        myAccountPage.openAddressesChain()
                .clickAddNewButtonChain()
                .clickSaveButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .textToBePresentInElement(myAccountNewAddressPage
                                .getFirstNameError(), "First name is required"));

        Assert.assertTrue(myAccountNewAddressPage.getTextErrorFirstName().contains("First name is required"));
        Assert.assertTrue(myAccountNewAddressPage.getTextErrorLastName().contains("Last name is required"));
        Assert.assertTrue(myAccountNewAddressPage.getTextErrorEmail().contains("Email is required"));
        Assert.assertTrue(myAccountNewAddressPage.getTextErrorCity().contains("City is required"));
        Assert.assertTrue(myAccountNewAddressPage.getTextErrorFirstAddress().contains("Street address is required"));
        Assert.assertTrue(myAccountNewAddressPage.getTextErrorZipCode().contains("Zip / postal code is required"));
        Assert.assertTrue(myAccountNewAddressPage.getTextErrorPhoneNumber().contains("Phone is required"));

        LOGGER.info("Finish negative testAddNewAddressEmptyFields");
    }

    @Test(description = "Adding a new address with filling in all fields with valid data" +
            " without specifying the Country field")
    public void testAddNewAddressWithWithoutCountryField() {
        LOGGER.info("Start negative testAddNewAddressWithWithoutCountryField");

        userRegistration();

        myAccountPage.openAddressesChain()
                .clickAddNewButtonChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterCompanyChain(RandomUserData.getCompanyName())
                .enterCityChain(RandomUserData.getCity())
                .enterFirstAddressChain(RandomUserData.getFirstAddress())
                .enterSecondAddressChain(RandomUserData.getSecondAddress())
                .enterZipPostalCodeChain(RandomUserData.getZipPostalCode())
                .enterPhoneNumberChain(RandomUserData.getPhoneNumber())
                .enterFaxNumberChain(RandomUserData.getFaxNumber())
                .clickSaveButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .textToBePresentInElement(myAccountNewAddressPage
                                .getCountryError(), "Country is required"));

        Assert.assertTrue(myAccountNewAddressPage.getTextErrorCountry().contains("Country is required"));

        LOGGER.info("Finish negative testAddNewAddressWithWithoutCountryField");
    }

    @Test(description = "Adding a new address with filling in the fields with special characters")
    public void testAddNewAddressWithSpecialCharacters() {
        LOGGER.info("Start negative testAddNewAddressWithSpecialCharacters");

        final String company = RandomUserData.getCompanyName();

        userRegistration();

        myAccountPage.openAddressesChain()
                .clickAddNewButtonChain()
                .enterFirstNameChain(RandomUserData.getSpecialCharacters())
                .enterLastNameChain(RandomUserData.getSpecialCharacters())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterCompanyChain(company)
                .selectCountryChain(getRandomIndexForDropDown())
                .enterCityChain(RandomUserData.getSpecialCharacters())
                .enterFirstAddressChain(RandomUserData.getSpecialCharacters())
                .enterSecondAddressChain(RandomUserData.getSpecialCharacters())
                .enterZipPostalCodeChain(RandomUserData.getSpecialCharacters())
                .enterPhoneNumberChain(RandomUserData.getPhoneNumber())
                .enterFaxNumberChain(RandomUserData.getSpecialCharacters())
                .clickSaveButton();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(myAccountNewAddressPage.getAddNewButton()));

        Assert.assertTrue(getDriver()
                .findElement(By
                        .xpath("//li[@class='company']"))
                .getText().contains(company));

        LOGGER.info("Finish negative testAddNewAddressWithSpecialCharacters");
    }
}
