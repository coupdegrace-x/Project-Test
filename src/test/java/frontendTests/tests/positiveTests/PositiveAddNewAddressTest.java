package frontendTests.tests.positiveTests;

import frontendTests.pages.MyAccountNewAddressPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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

    @BeforeMethod
    protected void setUpPositiveAddNewAddressTest() {
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

    private void checkForTrue(WebElement webElement, String text) {
        waitUtils.waitForCondition(
                ExpectedConditions.elementToBeClickable(webElement),
                10
        );

        assertTrue(getDriver().findElement(By.className("email")).getText().contains(text));
    }

    private void addNewAddress(boolean fillAllFields, String emailUser) {
        myAccountPage.openAddressesChain()
                .clickAddNewButtonChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(emailUser)
                .selectStateOrProvinceChain()
                .selectCountryChain(getRandomIndexForDropDown())
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
    }

    @DataProvider(name = "positiveAddNewAddress")
    private Object[][] positiveAddNewAddress() {
        final String generatedEmail = RandomUserData.getRandomEmail();
        if (generatedEmail == null) {
            throw new IllegalArgumentException("Generated email for DataProvider is null");
        }
        return new Object[][]{
                {"all valid fields", true, generatedEmail},
                {"only valid required fields", false, generatedEmail},
        };
    }

    @Test(dataProvider = "positiveAddNewAddress",
            description = "Adding an address with filling in all fields with valid data")
    public void testPositiveAddNewAddress(String scenario, boolean isAllFields, String email) {
        logger.info("Start positive testPositiveAddNewAddress {}", scenario);

        addNewAddress(isAllFields, email);

        checkForTrue(myAccountNewAddressPage.getAddNewButton(), email);

        logger.info("Finish positive testPositiveAddNewAddress {}", scenario);
    }
}
