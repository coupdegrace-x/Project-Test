package frontendTests.tests.negativeTests;

import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.ExistingUser;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertTrue;

@TestCase(infoAboutCase = "NegativeRegisterCases",
        path = "frontendTests/testCases/registerCases/NegativeRegisterCases.md")
public class NegativeRegisterTest extends BaseTest {

    private RegisterPage registerPage;

    @BeforeMethod
    protected void setUpRegisterPage() {
        registerPage = new RegisterPage(getDriver());
    }

    private String getRandomGender() {
        String[] genders = {"male", "female"};
        return genders[new Random().nextInt(genders.length)];
    }

    private void verifyErrorMessage(WebElement element, String expectedMessage, int timeout) {
        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(element), timeout);
        assertTrue(element.getText().contains(expectedMessage),
                "Expected error message: " + expectedMessage + ", but got: " + element.getText());
    }

    private void fillRegistrationForm(String firstName, String lastName, String email,
                                      String password, String confirmPassword, String gender) {
        registerPage.openRegisterPage();

        if (gender != null) {
            if (gender.equalsIgnoreCase("male")) {
                registerPage.chooseMaleGender();
            } else if (gender.equalsIgnoreCase("female")) {
                registerPage.chooseFemaleGender();
            }
        }

        if (firstName != null) registerPage.enterFirstName(firstName);
        if (lastName != null) registerPage.enterLastName(lastName);
        if (email != null) registerPage.enterEmail(email);
        if (password != null) registerPage.enterPassword(password);
        if (confirmPassword != null) registerPage.enterConfirmPassword(confirmPassword);

        registerPage.clickRegisterButton();
    }

    @DataProvider(name = "negativeRegistration")
    public Object[][] negativeTestCases() {
        return new Object[][]{
                {null, "Doe", RandomUserData.getRandomEmail(), "password123",
                        "password123", "First name is required", "FirstName"
                },
                {"John", null, RandomUserData.getRandomEmail(), "password123",
                        "password123", "Last name is required", "LastName"
                },
                {"John", "Doe", null, "password123",
                        "password123", "Email is required", "Email"
                },
                {"John", "Doe", RandomUserData.getRandomEmail(), null,
                        "password123", "Password is required", "Password"
                },
                {"John", "Doe", RandomUserData.getRandomEmail(),
                        "password123", null, "Password is required", "ConfirmPassword"
                }
        };
    }

    @Test(dataProvider = "negativeRegistration", description = "Negative registration test with missing field")
    public void testNegativeRegistration(String firstName, String lastName, String email,
                                         String password, String confirmPassword,
                                         String expectedMessage, String field) {
        logger.info("Start test with missing field: {}", field);

        fillRegistrationForm(firstName, lastName, email, password, confirmPassword, getRandomGender());

        WebElement errorElement = switch (field) {
            case "FirstName" -> registerPage.getFirstNameRequired();
            case "LastName" -> registerPage.getLastNameRequired();
            case "Email" -> registerPage.getEmailRequired();
            case "Password" -> registerPage.getPasswordRequired();
            case "ConfirmPassword" -> registerPage.getConfirmPasswordRequired();
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        };

        verifyErrorMessage(errorElement, expectedMessage, 10);

        logger.info("Finish test with missing field: {}", field);
    }

    @Test(description = "Unsuccessful registration of an already registered user")
    public void testRegisterAlreadyRegisteredUser() {
        logger.info("Start test for already registered user");

        fillRegistrationForm(
                ExistingUser.getFirstName(), ExistingUser.getLastName(),
                ExistingUser.getEmail(), ExistingUser.getPassword(),
                ExistingUser.getPassword(), "male"
        );

        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(registerPage.getErrorMessage()), 10);

        assertTrue(registerPage.getTextErrorMessage().contains("The specified email already exists"),
                "Expected error message: 'The specified email already exists', but got: "
                        + registerPage.getTextErrorMessage());

        logger.info("Finish test for already registered user");
    }
}

