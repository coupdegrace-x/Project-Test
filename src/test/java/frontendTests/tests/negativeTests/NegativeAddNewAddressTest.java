package frontendTests.tests.negativeTests;

import frontendTests.pages.MyAccountNewAddressPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomIndexForDropDown;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

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
        logger.info("Start negative testAddNewAddressEmptyFields");

        userRegistration();

        myAccountPage.openAddressesChain()
                .clickAddNewButtonChain()
                .clickSaveButton();

        waitUtils.waitForCondition(ExpectedConditions
                .textToBePresentInElement(myAccountNewAddressPage
                        .getFirstNameError(), "First name is required"), 10);

        Assert.assertTrue(myAccountNewAddressPage.getFirstNameError().getText().contains("First name is required"));
        Assert.assertTrue(myAccountNewAddressPage.getLastNameError().getText().contains("Last name is required"));
        Assert.assertTrue(myAccountNewAddressPage.getEmailError().getText().contains("Email is required"));
        Assert.assertTrue(myAccountNewAddressPage.getCityError().getText().contains("City is required"));
        Assert.assertTrue(myAccountNewAddressPage.getFirstAddressError().getText().contains("Street address is required"));
        Assert.assertTrue(myAccountNewAddressPage.getZipPostalCodeError().getText().contains("Zip / postal code is required"));
        Assert.assertTrue(myAccountNewAddressPage.getPhoneNumberError().getText().contains("Phone is required"));

        logger.info("Finish negative testAddNewAddressEmptyFields");
    }

    @Test(description = "Adding a new address with filling in all fields with valid data" +
            " without specifying the Country field")
    public void testAddNewAddressWithWithoutCountryField() {
        logger.info("Start negative testAddNewAddressWithWithoutCountryField");

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

        waitUtils.waitForCondition(ExpectedConditions
                        .textToBePresentInElement(myAccountNewAddressPage
                                .getCountryError(), "Country is required"),
                15);

        Assert.assertTrue(myAccountNewAddressPage.getCountryError().getText().contains("Country is required"));

        logger.info("Finish negative testAddNewAddressWithWithoutCountryField");
    }

    @Test(description = "Adding a new address with filling in the fields with special characters")
    public void testAddNewAddressWithSpecialCharacters() {
        logger.info("Start negative testAddNewAddressWithSpecialCharacters");

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

        waitUtils.waitForCondition(ExpectedConditions
                        .elementToBeClickable(myAccountNewAddressPage.getAddNewButton()),
                15);

        Assert.assertTrue(getDriver()
                .findElement(By
                        .xpath("//li[@class='company']"))
                .getText().contains(company));

        logger.info("Finish negative testAddNewAddressWithSpecialCharacters");
    }
}
