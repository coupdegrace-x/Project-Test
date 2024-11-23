package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// https://demowebshop.tricentis.com/cart
@Getter
public class ShoppingCartPage {

    private WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@id='topcartlink']/a[@href='/cart']")
    private WebElement shoppingCart;

    @FindBy(className = "order-summary-content") // может работать на стрице с пустой корзиой так и с заполненной
    private WebElement orderSummary;

    @FindBy(name = "updatecart")
    private WebElement buttonUpdateShoppingCart;

    @FindBy(name = "removefromcart")
    private List<WebElement> checkboxRemoveFromCart;

    @FindBy(className = "qty-input")
    private List<WebElement> inputFieldQty;

    public void openShoppingCart() {
        getDriver().get("https://demowebshop.tricentis.com/cart");
    }

    public ShoppingCartPage openShoppingCartChain() {
        getDriver().get("https://demowebshop.tricentis.com/cart");
        return this;
    }

    public void removeFromCart() {
        checkboxRemoveFromCart.stream()
                .filter(button -> button.isDisplayed() && button.isEnabled())
                .forEach(button -> {
                    try {
                        button.click();
                    } catch (final Exception exception) {
                        System.out.println("Failed to click 'Add to cart' button: " + exception.getMessage());
                    }
                });
    }

    public ShoppingCartPage removeFromCartChain() {
        checkboxRemoveFromCart.stream()
                .filter(button -> button.isDisplayed() && button.isEnabled())
                .forEach(button -> {
                    try {
                        button.click();
                    } catch (final Exception exception) {
                        System.out.println("Failed to click 'Add to cart' button: " + exception.getMessage());
                    }
                });
        return this;
    }

    public void removeQtyProducts() {
        final String properQuantityOfProduct = "0";
        inputFieldQty.stream()
                .filter(inputField -> inputField.isDisplayed() && inputField.isEnabled())
                .forEach(inputField -> {
                    try {
                        inputField.clear();
                        inputField.sendKeys(properQuantityOfProduct);

                        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                                .until(ExpectedConditions
                                        .attributeToBe(inputField, "value", properQuantityOfProduct));
                    } catch (final Exception exception) {
                        System.out.println("Failed to clear field or to sendKeys: " + exception.getMessage());
                    }
                });
    }

    public ShoppingCartPage removeQtyProductsChain() {
        final String properQuantityOfProduct = "0";
        inputFieldQty.stream()
                .filter(inputField -> inputField.isDisplayed() && inputField.isEnabled())
                .forEach(inputField -> {
                    try {
                        inputField.clear();
                        inputField.sendKeys(properQuantityOfProduct);

                        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                                .until(ExpectedConditions
                                        .attributeToBe(inputField, "value", properQuantityOfProduct));
                    } catch (final Exception exception) {
                        System.out.println("Failed to clear field or to sendKeys: " + exception.getMessage());
                    }
                });
        return this;
    }


    public void clickUpdateShoppingCart() {
        buttonUpdateShoppingCart.click();
    }

    public ShoppingCartPage clickUpdateShoppingCartChain() {
        buttonUpdateShoppingCart.click();
        return this;
    }

    public Boolean shoppingCartIsEmpty() {
        return this.getOrderSummary().getText().contains("Your Shopping Cart is empty!");
    }


}
