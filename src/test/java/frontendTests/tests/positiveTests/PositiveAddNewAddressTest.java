package frontendTests.tests.positiveTests;

import frontendTests.pages.MyAccountNewAddressPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomIndexForDropDown;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import frontendTests.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

@TestCase(infoAboutCase = "PositiveNewAddressCases",
        path = "frontendTests/testCases/myAccountCases/addressesCases/PositiveNewAddressCases.md")
public class PositiveAddNewAddressTest extends BaseTest implements RandomIndexForDropDown {

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

    @Test(description = "Adding an address with filling in all fields with valid data")
    public void testAddNewAddressWithAllFieldsValidData() {
        logger.info("Start positive testAddNewAddressWithAllFieldsValidData");

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

        waitUtils.waitForCondition(ExpectedConditions
                        .elementToBeClickable(myAccountNewAddressPage.getAddNewButton()),
                10);

        Assert.assertTrue(getDriver().findElement(By.className("email")).getText().contains(emailUser));

        logger.info("Finish positive testAddNewAddressWithAllFieldsValidData");
    }

    @Test(description = "Adding an address with filling in all required fields with valid data")
    public void testAddNewAddressWithRequiredFieldsInvalidData() {
        logger.info("Start positive testAddNewAddressWithRequiredFieldsInvalidData");

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

        waitUtils.waitForCondition(ExpectedConditions
                        .elementToBeClickable(myAccountNewAddressPage.getAddNewButton()),
                10);

        Assert.assertTrue(getDriver().findElement(By.className("email")).getText().contains(emailUser));

        logger.info("Finish positive testAddNewAddressWithRequiredFieldsInvalidData");
    }
}
