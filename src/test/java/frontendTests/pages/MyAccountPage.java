package frontendTests.pages;

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

    private void waitForVisibility(By locator, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void openMyAccountPage() { // страница по дефолту открывает вкладку Customer Info
        driver.get("https://demowebshop.tricentis.com/customer/info");

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public void openCustomerInfo() {
        customerInfo.click();

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public void openAddresses() {
        addresses.click();

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public void openOrders() {
        orders.click();

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public void openDownloadableProducts() {
        downloadableProducts.click();

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public void openBackInStockSubscriptions() {
        backInStockSubscriptions.click();

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public void openRewardPoints() {
        rewardPoints.click();

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }

    public void openChangePassword() {
        changePassword.click();

        waitForVisibility(By.className("master-wrapper-page"), 25);
    }


}
