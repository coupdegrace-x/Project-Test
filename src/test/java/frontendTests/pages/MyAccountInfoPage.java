package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

@Getter
public class MyAccountInfoPage extends MyAccountPage{

    public MyAccountInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "gender-male")
    private WebElement genderMale;

    @FindBy(id = "gender-female")
    private WebElement genderFemale;

    @FindBy(id = "FirstName")
    private WebElement firstNameInputField;

    @FindBy(id = "LastName")
    private WebElement lastNameInputField;

    @FindBy(id = "Email")
    private WebElement emailInputField;

    @FindBy(name = "save-info-button")
    private WebElement saveButton;

    public void chooseGenderMale() {
        genderMale.click();
    }

    public MyAccountInfoPage chooseGenderMaleChain() {
        genderFemale.click();
        return this;
    }

    public void chooseGenderFemale() {
        genderFemale.click();
    }

    public MyAccountInfoPage chooseGenderFemaleChain() {
        genderFemale.click();
        return this;
    }

    public void enterFirstName(String firstName) {
        firstNameInputField.clear();
        firstNameInputField.sendKeys(firstName);
    }

    public MyAccountInfoPage enterFirstNameChain(String firstName) {
        firstNameInputField.clear();
        firstNameInputField.sendKeys(firstName);
        return this;
    }

    public void enterLastName(String lastName) {
        lastNameInputField.clear();
        lastNameInputField.sendKeys(lastName);
    }

    public MyAccountInfoPage enterLastNameChain(String lastName) {
        lastNameInputField.clear();
        lastNameInputField.sendKeys(lastName);
        return this;
    }

    public void enterEmail(String email) {
        emailInputField.clear();
        emailInputField.sendKeys(email);
    }

    public MyAccountInfoPage enterEmailChain(String email) {
        emailInputField.clear();
        emailInputField.sendKeys(email);
        return this;
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public boolean atCustomerInfoPage() {
        return Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/customer/info");
    }
}
