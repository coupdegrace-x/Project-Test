package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

// https://demowebshop.tricentis.com/books
@Getter
public class Category extends BasePageRegisteredUser {

    private Select select;

    public Category(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "products-orderby")
    private WebElement sortByDropDown;

    @FindBy(id = "products-pagesize")
    private WebElement displayPerPageDropDown;

    @FindBy(id = "products-viewmode")
    private WebElement viewAsDropDown;

    @FindBy(xpath = "//a[contains(@href,'price=-25')]")
    private WebElement filterByPriceUnder;

    @FindBy(xpath = "//a[contains(@href,'price=25-50')]")
    private WebElement filterByPriceMiddle;

    @FindBy(xpath = "//a[contains(@href,'price=50-')]")
    private WebElement filterByPriceOver;

    @FindBy(xpath = "//a[text()='1']")
    private WebElement firstPage;

    @FindBy(xpath = "//a[text()='2']")
    private WebElement secondPage;

    @FindBy(xpath = "//a[text()='Previous']")
    private WebElement previousPage;

    @FindBy(xpath = "//a[text()='Next']")
    private WebElement nextPage;

    @FindBy(xpath = "//a[text()='Remove Filter']")
    private WebElement removeFilter;

    @FindBy(className = "actual-price")
    private List<WebElement> actualPrices;

    @FindBy(className = "product-title")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//input[@value='Add to cart']")
    private List<WebElement> productsAddToCart;

    public WebElement getFirstAvailableProductAddToCart() {
        WebElement addToCartButton = productsAddToCart.stream()
                .filter(button -> button.isDisplayed() && button.isEnabled())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No 'Add to cart' buttons are available"));

        addToCartButton.click();
        return addToCartButton;
    }

    public Category getFirstAvailableProductAddToCartChain() {
        productsAddToCart.stream()
                .filter(button -> button.isDisplayed() && button.isEnabled())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No 'Add to cart' buttons are available"))
                .click();

        return this;
    }

    public void addAllAvailableProductsToCart() {
        productsAddToCart.stream()
                .filter(button -> button.isDisplayed() && button.isEnabled())
                .forEach(button -> {
                    try {
                        button.click();
                    } catch (final Exception exception) {
                        System.out.println("Failed to click 'Add to cart' button: " + exception.getMessage());
                    }
                });
    }

    public Category addAllAvailableProductsToCartChain() {
        productsAddToCart.stream()
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

    public List<Double> getPriceFromCurrentPage() {
        return actualPrices.stream()
                .map(priceElement -> Double.parseDouble(priceElement.getText()))
                .collect(Collectors.toList());
    }

    public List<String> getTitlesFromCurrentPage() {
        return productTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void sortByZToA() {
        select = new Select(sortByDropDown);
        select.selectByVisibleText("Name: Z to A");
    }

    public Category sortByZToAChain() {
        select = new Select(sortByDropDown);
        select.selectByVisibleText("Name: Z to A");
        return this;
    }

    public void sortByUnderTwentyFivePrice() {
        filterByPriceUnder.click();
    }

    public Category sortByUnderTwentyFivePriceChain() {
        filterByPriceUnder.click();
        return this;
    }

    public void sortByMiddlePrice() {
        filterByPriceMiddle.click();
    }

    public Category sortByMiddlePriceChain() {
        filterByPriceMiddle.click();
        return this;
    }

    public void clickRemoveFilter() {
        removeFilter.click();
    }

    public Category clickRemoveFilterChain() {
        removeFilter.click();
        return this;
    }
}
