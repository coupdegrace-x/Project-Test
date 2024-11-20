package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://demowebshop.tricentis.com/customer/addresses
@Getter
public class MyAccountAddressesPage extends MyAccountPage{

    public MyAccountAddressesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "add-address-button")
    private WebElement addNewButton;

    public void openAddressesPage() {
        getDriver().get("https://demowebshop.tricentis.com/customer/addresses");
    }

    public void clickAddNewButton() {
        addNewButton.click();
    }

    public MyAccountNewAddressPage clickAddNewButtonChain() {
        addNewButton.click();
        return new MyAccountNewAddressPage(getDriver());
    }
}
