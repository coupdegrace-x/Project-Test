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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class NegativeAddNewAddressTest extends BaseTest implements RandomIndexForDropDown {

    private RegisterPage registerPage;
    private MyAccountNewAddressPage myAccountNewAddressPage;
    private MyAccountPage myAccountPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    protected void setUpNegativeAddNewAddressTest() {
        registerPage = new RegisterPage(getDriver());
        myAccountNewAddressPage = new MyAccountNewAddressPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());

        waitUtils = new WaitUtils(getDriver());

        registerRandomUser();
    }

    @Override
    public int getRandomIndexForDropDown() {
        return new Random().nextInt(0, 238);
    }

    private void registerRandomUser() {
        new RegistrationOfRandomUser().userRegistration(registerPage);
    }

    private void checkForTrue(WebElement errorElement, String expectedMessage) {
        waitUtils.waitForCondition(
                ExpectedConditions.textToBePresentInElement(errorElement, expectedMessage),
                10
        );

        assertTrue(
                errorElement.getText().contains(expectedMessage),
                "Expected: " + expectedMessage + ", but got: " + errorElement.getText()
        );
    }

    @Test(description = "Adding a new address with all empty fields")
    public void testAddNewAddressEmptyFields() {
        logger.info("Start negative testAddNewAddressEmptyFields");

        myAccountPage.openAddressesChain()
                .clickAddNewButtonChain()
                .clickSaveButton();

        Map<WebElement, String> errorMessages = Map.of(
                myAccountNewAddressPage.getFirstNameError(), "First name is required",
                myAccountNewAddressPage.getLastNameError(), "Last name is required",
                myAccountNewAddressPage.getEmailError(), "Email is required",
                myAccountNewAddressPage.getCityError(), "City is required",
                myAccountNewAddressPage.getFirstAddressError(), "Street address is required",
                myAccountNewAddressPage.getZipPostalCodeError(), "Zip / postal code is required",
                myAccountNewAddressPage.getPhoneNumberError(), "Phone is required"
        );

        errorMessages.forEach(this::checkForTrue);

        logger.info("Finish negative testAddNewAddressEmptyFields");
    }

    @Test(description = "Adding a new address with filling in all fields with valid data" +
            " without specifying the Country field")
    public void testAddNewAddressWithWithoutCountryField() {
        logger.info("Start negative testAddNewAddressWithWithoutCountryField");

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

        checkForTrue(
                myAccountNewAddressPage.getCountryError(),
                "Country is required"
        );

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
