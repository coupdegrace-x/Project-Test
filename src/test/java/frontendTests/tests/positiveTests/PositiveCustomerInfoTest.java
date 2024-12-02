package frontendTests.tests.positiveTests;

import frontendTests.pages.MyAccountInfoPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.RegistrationOfRandomUser;
import frontendTests.utils.TestCase;
import frontendTests.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

@TestCase(infoAboutCase = "PositiveCustomerInfoCases",
        path = "frontendTests/testCases/myAccountCases/customerInfoCases/PositiveCustomerInfoCases.md")
public class PositiveCustomerInfoTest extends BaseTest {

    private MyAccountInfoPage myAccountInfoPage;
    private MyAccountPage myAccountPage;
    private RegisterPage registerPage;
    private WaitUtils waitUtils;
    private String firstName;
    private String lastName;
    private String email;

    @BeforeMethod
    protected void setUpPositiveCustomerInfoTest() {
        myAccountInfoPage = new MyAccountInfoPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());
        registerPage = new RegisterPage(getDriver());
        waitUtils = new WaitUtils(getDriver());

        firstName = RandomUserData.getRandomFirstName();
        lastName = RandomUserData.getRandomLastName();
        email = RandomUserData.getRandomEmail();

        registerRandomUser();
    }

    private void registerRandomUser() {
        new RegistrationOfRandomUser().userRegistration(registerPage);
    }

    private void checkForTrue(WebElement webElement, String value) {
        waitUtils.waitForCondition(
                ExpectedConditions.attributeToBe(webElement, "value", value),
                10
        );

        assertTrue(Objects.requireNonNull(
                webElement.getAttribute("value")).contains(value));
    }

    private void changeAllFields() {
        Map<WebElement, String> personalData = Map.of(
                myAccountInfoPage.getFirstNameInputField(), firstName,
                myAccountInfoPage.getLastNameInputField(), lastName,
                myAccountInfoPage.getEmailInputField(), email
        );

        myAccountInfoPage
                .clearFirstNameChain()
                .clearLastNameChain()
                .clearEmailChain()
                .enterFirstNameChain(firstName)
                .enterLastNameChain(lastName)
                .enterEmailChain(email)
                .clickSaveButton();

        personalData.forEach(this::checkForTrue);
    }

    private void changeField(WebElement field, String newValue) {
        field.clear();
        field.sendKeys(newValue);
        myAccountInfoPage.clickSaveButton();
        checkForTrue(field, newValue);
    }

    @DataProvider(name = "positiveChangeData")
    protected Object[][] positiveChangeData() {
        return new Object[][]{
                {"change all data", true, false, false, false},
                {"change first name", false, true, false, false},
                {"change last name", false, false, true, false},
                {"change email", false, false, false, true},
        };
    }

    private void changePersonalData(boolean isAllData, boolean isFirstName,
                                    boolean isLastName, boolean isEmail) {
        if (isAllData) {
            changeAllFields();
        }
        if (isFirstName) {
            changeField(myAccountInfoPage.getFirstNameInputField(), firstName);
            checkForTrue(myAccountInfoPage.getFirstNameInputField(), firstName);
        }
        if (isLastName) {
            changeField(myAccountInfoPage.getLastNameInputField(), lastName);
            checkForTrue(myAccountInfoPage.getLastNameInputField(), lastName);
        }
        if (isEmail) {
            changeField(myAccountInfoPage.getEmailInputField(), email);
            checkForTrue(myAccountInfoPage.getEmailInputField(), email);
        }
    }

    @Test(dataProvider = "positiveChangeData")
    public void testPositiveCustomerInfoChangeData(String scenarioName, boolean isAllData, boolean isFirstName,
                                                   boolean isLastName, boolean isEmail) {
        logger.info("Start positive testPositiveCustomerInfoChangeData with scenarioName: {}", scenarioName);

        myAccountPage.openCustomerInfo();

        changePersonalData(isAllData, isFirstName, isLastName, isEmail);

        logger.info("Finish positive testPositiveCustomerInfoChangeData with scenarioName: {}", scenarioName);
    }
}
