package frontendTests.pages;

import frontendTests.utils.WaitUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// https://demowebshop.tricentis.com/customer/info
@Getter
public class MyAccountPage {

    private final WebDriver driver;
    private WaitUtils waitUtils;

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

    private void waitForVisibility(By locator, int waitingSeconds) {
        waitUtils.waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator), waitingSeconds);
    }

    public void openMyAccountPage() { // страница по дефолту открывает вкладку Customer Info
        driver.get("https://demowebshop.tricentis.com/customer/info");

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public void openCustomerInfo() {
        driver.get("https://demowebshop.tricentis.com/customer/info");

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public MyAccountInfoPage openCustomerInfoChain() {
        driver.get("https://demowebshop.tricentis.com/customer/info");

        waitForVisibility(By.className("master-wrapper-page"), 25);
        return new MyAccountInfoPage(driver);
    }

    public void openAddresses() {
        driver.get("https://demowebshop.tricentis.com/customer/addresses");

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public MyAccountNewAddressPage openAddressesChain() {
        driver.get("https://demowebshop.tricentis.com/customer/addresses");

        waitForVisibility(By.className("master-wrapper-page"), 25);
        return new MyAccountNewAddressPage(driver);
    }

    public void openOrders() {
        driver.get("https://demowebshop.tricentis.com/customer/orders");

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public MyAccountInfoPage openOrdersChain() {
        driver.get("https://demowebshop.tricentis.com/customer/orders");

        waitForVisibility(By.className("master-wrapper-page"), 25);
        return new MyAccountInfoPage(driver);
    }

    public void openDownloadableProducts() {
        driver.get("https://demowebshop.tricentis.com/customer/downloadableproducts");

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public MyAccountInfoPage openDownloadableProductsChain() {
        driver.get("https://demowebshop.tricentis.com/customer/downloadableproducts");

        waitForVisibility(By.className("master-wrapper-page"), 25);
        return new MyAccountInfoPage(driver);
    }

    public void openBackInStockSubscriptions() {
        driver.get("https://demowebshop.tricentis.com/customer/backinstocksubscriptions");

        waitForVisibility(By.className("master-wrapper-page"), 35);
    }

    public MyAccountInfoPage openBackInStockSubscriptionsChain() {
        driver.get("https://demowebshop.tricentis.com/customer/backinstocksubscriptions");

        waitForVisibility(By.className("master-wrapper-page"), 35);
        return new MyAccountInfoPage(driver);
    }

    public void openRewardPoints() {
        driver.get("https://demowebshop.tricentis.com/customer/rewardpoints");

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public MyAccountInfoPage openRewardPointsChain() {
        driver.get("https://demowebshop.tricentis.com/customer/rewardpoints");

        waitForVisibility(By.className("master-wrapper-page"), 25);
        return new MyAccountInfoPage(driver);
    }

    public void openChangePassword() {
        driver.get("https://demowebshop.tricentis.com/customer/changepassword");

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public MyAccountInfoPage openChangePasswordChain() {
        driver.get("https://demowebshop.tricentis.com/customer/changepassword");

        waitForVisibility(By.className("master-wrapper-page"), 25);
        return new MyAccountInfoPage(driver);
    }
}
