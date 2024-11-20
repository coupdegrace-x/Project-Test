package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//https://demowebshop.tricentis.com/customer/addressadd
@Getter
public class MyAccountNewAddressPage extends MyAccountAddressesPage {

    public MyAccountNewAddressPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Address_FirstName")
    private WebElement firstNameInputField;

    @FindBy(id = "Address_LastName")
    private WebElement lastNameInputField;

    @FindBy(id = "Address_Email")
    private WebElement emailInputField;

    @FindBy(id = "Address_Company")
    private WebElement companyInputField;

    @FindBy(id = "Address_CountryId")
    private WebElement countryDropDown;

    @FindBy(id = "Address_StateProvinceId")
    private WebElement stateOrProvinceDropDown;

    @FindBy(id = "Address_City")
    private WebElement cityInputField;

    @FindBy(id = "Address_Address1")
    private WebElement firstAddressInputField;

    @FindBy(id = "Address_Address2")
    private WebElement secondAddressInputField;

    @FindBy(id = "Address_ZipPostalCode")
    private WebElement zipPostalCodeInputField;

    @FindBy(id = "Address_PhoneNumber")
    private WebElement phoneNumberInputField;

    @FindBy(id = "Address_FaxNumber")
    private WebElement faxNumberInputField;

    @FindBy(className = "save-address-button")
    private WebElement saveButton;

    public void enterFirstName(String firstName) {
        firstNameInputField.sendKeys(firstName);
    }

    public MyAccountNewAddressPage enterFirstNameChain(String firstName) {
        firstNameInputField.sendKeys(firstName);
        return this;
    }

    public void enterLastName(String lastName) {
        lastNameInputField.sendKeys(lastName);
    }

    public MyAccountNewAddressPage enterLastNameChain(String lastName) {
        lastNameInputField.sendKeys(lastName);
        return this;
    }

    public void enterEmail(String email) {
        emailInputField.sendKeys(email);
    }

    public MyAccountNewAddressPage enterEmailChain(String email) {
        emailInputField.sendKeys(email);
        return this;
    }

    public void enterCompany(String company) {
        companyInputField.sendKeys(company);
    }

    public MyAccountNewAddressPage enterCompanyChain(String company) {
        companyInputField.sendKeys(company);
        return this;
    }

    public void selectCountry(String country) {
        Select select = new Select(countryDropDown);
        select.selectByVisibleText(country);
    }

    public MyAccountNewAddressPage selectCountryChain(int countryIndex) {
        Select select = new Select(countryDropDown);
        select.selectByIndex(countryIndex);
        return this;
    }

    public void selectStateOrProvince() {
        Select select = new Select(stateOrProvinceDropDown);

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.id("Address_StateProvinceId")));

        select.selectByIndex(0);
    }

    public MyAccountNewAddressPage selectStateOrProvinceChain() {
        Select select = new Select(stateOrProvinceDropDown);

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.id("Address_StateProvinceId")));

        select.selectByVisibleText("Other (Non US)");
        return this;
    }

    public void enterCity(String city) {
        cityInputField.sendKeys(city);
    }

    public MyAccountNewAddressPage enterCityChain(String city) {
        cityInputField.sendKeys(city);
        return this;
    }

    public void enterFirstAddress(String address) {
        firstAddressInputField.sendKeys(address);
    }

    public MyAccountNewAddressPage enterFirstAddressChain(String address) {
        firstAddressInputField.sendKeys(address);
        return this;
    }

    public void enterSecondAddress(String address) {
        secondAddressInputField.sendKeys(address);
    }

    public MyAccountNewAddressPage enterSecondAddressChain(String address) {
        secondAddressInputField.sendKeys(address);
        return this;
    }

    public void enterZipPostalCode(String postalCode) {
        zipPostalCodeInputField.sendKeys(postalCode);
    }

    public MyAccountNewAddressPage enterZipPostalCodeChain(String postalCode) {
        zipPostalCodeInputField.sendKeys(postalCode);
        return this;
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberInputField.sendKeys(phoneNumber);
    }

    public MyAccountNewAddressPage enterPhoneNumberChain(String phoneNumber) {
        phoneNumberInputField.sendKeys(phoneNumber);
        return this;
    }

    public void enterFuxNumber(String fuxNumber) {
        faxNumberInputField.sendKeys(fuxNumber);
    }

    public MyAccountNewAddressPage enterFaxNumberChain(String fuxNumber) {
        faxNumberInputField.sendKeys(fuxNumber);
        return this;
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public MyAccountNewAddressPage clickSaveButtonChain() {
        saveButton.click();
        return this;
    }
}
