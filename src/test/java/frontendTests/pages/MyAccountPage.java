package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://demowebshop.tricentis.com/customer/info
@Getter
public class MyAccountPage {

    private final WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='list']//a[@href='/customer/info']")
    private WebElement customerInfo;

    @FindBy(xpath = "//ul[@class='list']//a[@href='/customer/addresses']")
    private WebElement addresses;

    @FindBy(xpath = "//ul[@class='list']//a[@href='/customer/orders']")
    private WebElement orders;

    @FindBy(xpath = "//ul[@class='list']//a[@href='/customer/downloadableproducts']")
    private WebElement downloadableProducts;

    @FindBy(xpath = "//ul[@class='list']//a[@href='/customer/backinstocksubscriptions']")
    private WebElement backInStockSubscriptions;

    @FindBy(xpath = "//ul[@class='list']//a[@href='/customer/rewardpoints']")
    private WebElement rewardPoints;

    @FindBy(xpath = "//ul[@class='list']//a[@href='/customer/changepassword']")
    private WebElement changePassword;

    public void openMyAccountPage() { // страница по дефолту открывает вкладку Customer Info
        driver.get("https://demowebshop.tricentis.com/customer/info");
    }

    public void openCustomerInfo() {
        driver.get("https://demowebshop.tricentis.com/customer/info");
    }

    public MyAccountInfoPage openCustomerInfoChain() {
        driver.get("https://demowebshop.tricentis.com/customer/info");

        return new MyAccountInfoPage(driver);
    }

    public void openAddresses() {
        driver.get("https://demowebshop.tricentis.com/customer/addresses");
    }

    public MyAccountNewAddressPage openAddressesChain() {
        driver.get("https://demowebshop.tricentis.com/customer/addresses");
        return new MyAccountNewAddressPage(driver);
    }

    public void openOrders() {
        driver.get("https://demowebshop.tricentis.com/customer/orders");
    }

    public MyAccountInfoPage openOrdersChain() {
        driver.get("https://demowebshop.tricentis.com/customer/orders");
        return new MyAccountInfoPage(driver);
    }

    public void openDownloadableProducts() {
        driver.get("https://demowebshop.tricentis.com/customer/downloadableproducts");
    }

    public MyAccountInfoPage openDownloadableProductsChain() {
        driver.get("https://demowebshop.tricentis.com/customer/downloadableproducts");
        return new MyAccountInfoPage(driver);
    }

    public void openBackInStockSubscriptions() {
        driver.get("https://demowebshop.tricentis.com/customer/backinstocksubscriptions");
    }

    public MyAccountInfoPage openBackInStockSubscriptionsChain() {
        driver.get("https://demowebshop.tricentis.com/customer/backinstocksubscriptions");
        return new MyAccountInfoPage(driver);
    }

    public void openRewardPoints() {
        driver.get("https://demowebshop.tricentis.com/customer/rewardpoints");
    }

    public MyAccountInfoPage openRewardPointsChain() {
        driver.get("https://demowebshop.tricentis.com/customer/rewardpoints");
        return new MyAccountInfoPage(driver);
    }

    public void openChangePassword() {
        driver.get("https://demowebshop.tricentis.com/customer/changepassword");
    }

    public MyAccountInfoPage openChangePasswordChain() {
        driver.get("https://demowebshop.tricentis.com/customer/changepassword");

        return new MyAccountInfoPage(driver);
    }
}
