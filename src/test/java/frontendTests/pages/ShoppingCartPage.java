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
import java.util.Random;

// https://demowebshop.tricentis.com/cart
@Getter
public class ShoppingCartPage {

    private final WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@id='topcartlink']/a[@href='/cart']")
    private WebElement shoppingCart;

    @FindBy(className = "order-summary-content") // может работать на стрице с пустой корзиной так и с заполненной
    private WebElement orderSummary;

    @FindBy(name = "updatecart")
    private WebElement buttonUpdateShoppingCart;

    @FindBy(name = "continueshopping")
    private WebElement buttonContinueShoppingCart;

    @FindBy(name = "removefromcart")
    private List<WebElement> checkboxesRemoveFromCart;

    @FindBy(className = "qty-input")
    private List<WebElement> inputFieldsQty;

    @FindBy(className = "product-subtotal")
    private List<WebElement> totalPriceOfProducts;

    public void openShoppingCart() {
        getDriver().get("https://demowebshop.tricentis.com/cart");
    }

    public ShoppingCartPage openShoppingCartChain() {
        getDriver().get("https://demowebshop.tricentis.com/cart");
        return this;
    }

    public void removeFromCart() {
        checkboxesRemoveFromCart.stream()
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
        checkboxesRemoveFromCart.stream()
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

    public void removeQuantityProducts() {
        final String properQuantityOfProduct = "0";
        inputFieldsQty.stream()
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

    public ShoppingCartPage removeQuantityProductsChain() {
        final String properQuantityOfProduct = "0";
        inputFieldsQty.stream()
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

    public void addQuantityProducts() {
        final String randomQuantityOfProduct = String.valueOf(new Random().nextInt(0,11));
        inputFieldsQty.stream()
                .filter(inputField -> inputField.isDisplayed() && inputField.isEnabled())
                .forEach(inputField -> {
                    try {
                        inputField.clear();
                        inputField.sendKeys(randomQuantityOfProduct);

                        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                                .until(ExpectedConditions
                                        .attributeToBe(inputField, "value", randomQuantityOfProduct));
                    } catch (final Exception exception) {
                        System.out.println("Failed to clear field or to sendKeys: " + exception.getMessage());
                    }
                });
    }

    public ShoppingCartPage addQuantityProductsChain() {
        final String randomQuantityOfProduct = String.valueOf(new Random().nextInt(2,15));
        inputFieldsQty.stream()
                .filter(inputField -> inputField.isDisplayed() && inputField.isEnabled())
                .forEach(inputField -> {
                    try {
                        inputField.clear();
                        inputField.sendKeys(randomQuantityOfProduct);

                        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                                .until(ExpectedConditions
                                        .attributeToBe(inputField, "value", randomQuantityOfProduct));
                    } catch (final Exception exception) {
                        System.out.println("Failed to clear field or to sendKeys: " + exception.getMessage());
                    }
                });
        return this;
    }

    public Double getTotalPriceOfProducts() {
        return totalPriceOfProducts.stream()
                .filter(WebElement::isDisplayed)
                .mapToDouble(price -> {
                    String priceText = price.getText();
                    return Double.parseDouble(priceText);
                })
                .sum();
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

    public void clickContinueShoppingCart() {
        buttonContinueShoppingCart.click();
    }

    public ShoppingCartPage clickContinueShoppingCartChain() {
        buttonContinueShoppingCart.click();
        return this;
    }


}
