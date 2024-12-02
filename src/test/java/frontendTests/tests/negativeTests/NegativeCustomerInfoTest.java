package frontendTests.tests.negativeTests;

import frontendTests.pages.MyAccountInfoPage;
import frontendTests.pages.MyAccountPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RegistrationOfRandomUser;
import frontendTests.utils.TestCase;
import frontendTests.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertTrue;

@TestCase(infoAboutCase = "NegativeCustomerInfoCases",
        path = "frontendTests/testCases/myAccountCases/customerInfoCases/NegativeCustomerInfoCases.md")
public class NegativeCustomerInfoTest extends BaseTest {

    private static final String FIRST_NAME_ERROR_MESSAGE = "First name is required";
    private static final String LAST_NAME_ERROR_MESSAGE = "Last name is required";
    private static final String EMAIL_ERROR_MESSAGE = "Email is required";

    private MyAccountInfoPage myAccountInfoPage;
    private MyAccountPage myAccountPage;
    private RegisterPage registerPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    protected void setUpNegativeCustomerInfoTest() {
        myAccountInfoPage = new MyAccountInfoPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());
        registerPage = new RegisterPage(getDriver());
        waitUtils = new WaitUtils(getDriver());

        registerRandomUser();
    }

    private void registerRandomUser() {
        new RegistrationOfRandomUser().userRegistration(registerPage);
    }

    private void checkForTrue(WebElement webElement, String errorMessage) {
        waitUtils.waitForCondition(
                ExpectedConditions.textToBePresentInElement(webElement, errorMessage),
                15
        );

        assertTrue(webElement.getText().contains(errorMessage));
    }

    private void clearAllFields() {
        myAccountInfoPage
                .clearFirstNameChain()
                .clearLastNameChain()
                .clearEmailChain()
                .clickSaveButton();

        Map<WebElement, String> errors = Map.of(
                myAccountInfoPage.getFirstNameError(), FIRST_NAME_ERROR_MESSAGE,
                myAccountInfoPage.getLastNameError(), LAST_NAME_ERROR_MESSAGE,
                myAccountInfoPage.getEmailError(), EMAIL_ERROR_MESSAGE
        );

        errors.forEach(this::checkForTrue);
    }

    private void clearField(WebElement field) {
        field.clear();
        myAccountInfoPage.clickSaveButton();
    }

    private void changePersonalData(boolean isAllData, boolean isFirstName,
                                    boolean isLastName, boolean isEmail) {
        if (isAllData) {
            clearAllFields();
        }
        if (isFirstName) {
            clearField(myAccountInfoPage.getFirstNameInputField());
            checkForTrue(myAccountInfoPage.getFirstNameError(), FIRST_NAME_ERROR_MESSAGE);
        }
        if (isLastName) {
            clearField(myAccountInfoPage.getLastNameInputField());
            checkForTrue(myAccountInfoPage.getLastNameError(), LAST_NAME_ERROR_MESSAGE);
        }
        if (isEmail) {
            clearField(myAccountInfoPage.getEmailInputField());
            checkForTrue(myAccountInfoPage.getEmailError(), EMAIL_ERROR_MESSAGE);
        }
    }

    @DataProvider(name = "negativeChangeData")
    protected Object[][] negativeChangeData() {
        return new Object[][]{
                {"clear all data", true, false, false, false},
                {"clear first name", false, true, false, false},
                {"clear last name", false, false, true, false},
                {"clear email", false, false, false, true},
        };
    }

    @Test(dataProvider = "negativeChangeData")
    public void testNegativeChangeData(String scenarioName, boolean isAllData, boolean isFirstName,
                                       boolean isLastName, boolean isEmail) {
        logger.info("Start negative testNegativeChangeData with scenarioName: {}", scenarioName);

        myAccountPage.openCustomerInfo();

        changePersonalData(isAllData, isFirstName, isLastName, isEmail);

        logger.info("Finish negative testNegativeChangeData with scenarioName: {}", scenarioName);
    }
}
