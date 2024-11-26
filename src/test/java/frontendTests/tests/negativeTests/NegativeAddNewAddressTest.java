package frontendTests.tests.negativeTests;

import frontendTests.pages.MyAccountNewAddressPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomIndexForDropDown;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.RegistrationOfRandomUser;
import frontendTests.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertTrue;

public class NegativeAddNewAddressTest extends BaseTest implements RandomIndexForDropDown {

    private RegisterPage registerPage;
    private MyAccountNewAddressPage myAccountNewAddressPage;
    private MyAccountPage myAccountPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    protected void setUpRegMyAccAddressPage() {
        registerPage = new RegisterPage(getDriver());
        myAccountNewAddressPage = new MyAccountNewAddressPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());
        waitUtils = new WaitUtils(getDriver());
    }

    @Override
    public int getRandomIndexForDropDown() {
        return new Random().nextInt(0, 238);
    }

    @Test(description = "Adding a new address with all empty fields")
    public void testAddNewAddressEmptyFields() {
        logger.info("Start negative testAddNewAddressEmptyFields");

        new RegistrationOfRandomUser().userRegistration(registerPage);

        myAccountPage.openAddressesChain()
                .clickAddNewButtonChain()
                .clickSaveButton();

        waitUtils.waitForCondition(ExpectedConditions.textToBePresentInElement(
                        myAccountNewAddressPage.getFirstNameError(),
                        "First name is required"),
                10
        );

        assertTrue(myAccountNewAddressPage.getFirstNameError().getText().contains("First name is required"));
        assertTrue(myAccountNewAddressPage.getLastNameError().getText().contains("Last name is required"));
        assertTrue(myAccountNewAddressPage.getEmailError().getText().contains("Email is required"));
        assertTrue(myAccountNewAddressPage.getCityError().getText().contains("City is required"));
        assertTrue(myAccountNewAddressPage.getFirstAddressError().getText().contains("Street address is required"));
        assertTrue(myAccountNewAddressPage.getZipPostalCodeError().getText().contains("Zip / postal code is required"));
        assertTrue(myAccountNewAddressPage.getPhoneNumberError().getText().contains("Phone is required"));

        logger.info("Finish negative testAddNewAddressEmptyFields");
    }

    @Test(description = "Adding a new address with filling in all fields with valid data" +
            " without specifying the Country field")
    public void testAddNewAddressWithWithoutCountryField() {
        logger.info("Start negative testAddNewAddressWithWithoutCountryField");

        new RegistrationOfRandomUser().userRegistration(registerPage);

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

        waitUtils.waitForCondition(
                ExpectedConditions.textToBePresentInElement(
                        myAccountNewAddressPage.getCountryError(),
                        "Country is required"),
                15
        );

        assertTrue(myAccountNewAddressPage.getCountryError().getText().contains("Country is required"));

        logger.info("Finish negative testAddNewAddressWithWithoutCountryField");
    }

    @Test(description = "Adding a new address with filling in the fields with special characters")
    public void testAddNewAddressWithSpecialCharacters() {
        logger.info("Start negative testAddNewAddressWithSpecialCharacters");

        final String company = RandomUserData.getCompanyName();

        new RegistrationOfRandomUser().userRegistration(registerPage);

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

        waitUtils.waitForCondition(
                ExpectedConditions.elementToBeClickable(myAccountNewAddressPage.getAddNewButton()),
                15
        );

        assertTrue(getDriver().findElement(By.xpath("//li[@class='company']"))
                .getText().contains(company));

        logger.info("Finish negative testAddNewAddressWithSpecialCharacters");
    }
}
