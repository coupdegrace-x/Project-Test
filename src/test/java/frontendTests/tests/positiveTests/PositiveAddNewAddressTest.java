package frontendTests.tests.positiveTests;

import frontendTests.pages.MyAccountNewAddressPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertTrue;

@TestCase(infoAboutCase = "PositiveNewAddressCases",
        path = "frontendTests/testCases/myAccountCases/addressesCases/PositiveNewAddressCases.md")
public class PositiveAddNewAddressTest extends BaseTest implements RandomIndexForDropDown {

    private RegisterPage registerPage;
    private MyAccountNewAddressPage myAccountNewAddressPage;
    private MyAccountPage myAccountPage;
    private WaitUtils waitUtils;
    private String emailUser;

    @BeforeMethod
    protected void setUpPositiveAddNewAddressTest() {
        registerPage = new RegisterPage(getDriver());
        myAccountNewAddressPage = new MyAccountNewAddressPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());

        waitUtils = new WaitUtils(getDriver());

        emailUser = RandomUserData.getRandomEmail();

        registerRandomUser();
    }

    @Override
    public int getRandomIndexForDropDown() {
        return new Random().nextInt(0, 238);
    }

    private void registerRandomUser() {
        new RegistrationOfRandomUser().userRegistration(registerPage);
    }

    private void addNewAddress(boolean fillAllFields, String emailUser) {

        myAccountPage.openAddressesChain()
                .clickAddNewButtonChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(emailUser)
                .selectCountryChain(getRandomIndexForDropDown())
                .selectStateOrProvinceChain()
                .enterCityChain(RandomUserData.getCity())
                .enterFirstAddressChain(RandomUserData.getFirstAddress());

        if (fillAllFields) {
            myAccountNewAddressPage
                    .enterSecondAddressChain(RandomUserData.getSecondAddress())
                    .enterCompanyChain(RandomUserData.getCompanyName())
                    .enterFaxNumberChain(RandomUserData.getFaxNumber());
        }

        myAccountNewAddressPage
                .enterZipPostalCodeChain(RandomUserData.getZipPostalCode())
                .enterPhoneNumberChain(RandomUserData.getPhoneNumber())
                .clickSaveButton();

        waitUtils.waitForCondition(
                ExpectedConditions.elementToBeClickable(myAccountNewAddressPage.getAddNewButton()),
                10
        );

        assertTrue(getDriver().findElement(By.className("email")).getText().contains(emailUser));
    }

    @Test(description = "Adding an address with filling in all fields with valid data")
    public void testAddNewAddressWithAllFieldsValidData() {
        logger.info("Start positive testAddNewAddressWithAllFieldsValidData");

        addNewAddress(true, emailUser);

        logger.info("Finish positive testAddNewAddressWithAllFieldsValidData");
    }

    @Test(description = "Adding an address with filling in all required fields with valid data")
    public void testAddNewAddressWithRequiredFieldsInvalidData() {
        logger.info("Start positive testAddNewAddressWithRequiredFieldsInvalidData");

        addNewAddress(false, emailUser);

        logger.info("Finish positive testAddNewAddressWithRequiredFieldsInvalidData");
    }
}
