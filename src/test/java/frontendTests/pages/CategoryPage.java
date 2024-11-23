package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

// https://demowebshop.tricentis.com/books
@Getter
public class CategoryPage extends RegisteredUserBasePage {

    private Select select;
    private WebDriverWait driverWait;

    public CategoryPage(WebDriver driver) {
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

    @FindBy(className = "current-page")
    private WebElement currentPage;

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

    @FindBy(className = "cart-qty")
    private WebElement quantityProductsInCart;

    @FindBy(xpath = "//p[@class='content']")
    private WebElement popupWarningAddingItemToCart;

    @FindBy(className = "actual-price")
    private List<WebElement> actualPrices;

    @FindBy(className = "product-title")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//input[@value='Add to cart']")
    private List<WebElement> productsAddToCart;

    @FindBy(className = "item-box")
    private List<WebElement> quantityProductsCurrentPage;

    public Integer getQuantityProductsFromCurrentPage() {
        return (int) quantityProductsCurrentPage.stream()
                .filter(WebElement::isDisplayed)
                .count();
    }

    public void addFirstAvailableProductToCart() {
        WebElement addToCartButton = productsAddToCart.stream()
                .filter(button -> button.isDisplayed() && button.isEnabled())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No 'Add to cart' buttons are available"));

        addToCartButton.click();
    }

    public CategoryPage addFirstAvailableProductFromCurrentPageToCartChain() {
        productsAddToCart.stream()
                .filter(button -> button.isDisplayed() && button.isEnabled())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No 'Add to cart' buttons are available"))
                .click();

        return this;
    }

    public void addAllAvailableProductsFromCurrentPageToCart() {
        productsAddToCart.stream()
                .filter(button -> button.isDisplayed() && button.isEnabled())
                .forEach(button -> {
                    try {
                        button.click();

                        driverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

                        driverWait.until(ExpectedConditions.visibilityOf(this.getPopupWarningAddingItemToCart()));
                        driverWait.until(ExpectedConditions.invisibilityOf(this.getPopupWarningAddingItemToCart()));
                    } catch (final Exception exception) {
                        System.out.println("Failed to click 'Add to cart' button: " + exception.getMessage());
                    }
                });
    }

    public CategoryPage addAllAvailableProductsFromCurrentPageToCartChain() {
        productsAddToCart.stream()
                .filter(button -> button.isDisplayed() && button.isEnabled())
                .forEach(button -> {
                    try {
                        button.click();

                        driverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

                        driverWait.until(ExpectedConditions.visibilityOf(this.getPopupWarningAddingItemToCart()));
                        driverWait.until(ExpectedConditions.invisibilityOf(this.getPopupWarningAddingItemToCart()));
                    } catch (final Exception exception) {
                        System.out.println("Failed to click 'Add to cart' button: " + exception.getMessage());
                    }
                });

        return this;
    }

    public List<Double> getProductPriceFromCurrentPage() {
        return actualPrices.stream()
                .map(priceElement -> Double.parseDouble(priceElement.getText()))
                .collect(Collectors.toList());
    }

    public List<String> getProductTitlesFromCurrentPage() {
        return productTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void selectViewAsList() {
        select = new Select(viewAsDropDown);
        select.selectByVisibleText("List");
    }

    public CategoryPage selectViewAsListChain() {
        select = new Select(viewAsDropDown);
        select.selectByVisibleText("List");
        return this;
    }

    public void selectViewAsGrid() {
        select = new Select(viewAsDropDown);
        select.selectByVisibleText("Grid");
    }

    public CategoryPage selectViewAsGridChain() {
        select = new Select(viewAsDropDown);
        select.selectByVisibleText("Grid");
        return this;
    }

    public String getCurrentPageTitle() {
        return currentPage.getText();
    }

    public void selectDisplayFourPerPage() {
        select = new Select(displayPerPageDropDown);
        select.selectByVisibleText("4");
    }

    public CategoryPage selectDisplayFourPerPageChain() {
        select = new Select(displayPerPageDropDown);
        select.selectByValue("4");
        return this;
    }

    public void sortByZToA() {
        select = new Select(sortByDropDown);
        select.selectByVisibleText("Name: Z to A");
    }

    public CategoryPage sortByZToAChain() {
        select = new Select(sortByDropDown);
        select.selectByVisibleText("Name: Z to A");
        return this;
    }

    public void sortByUnderTwentyFivePrice() {
        filterByPriceUnder.click();
    }

    public CategoryPage sortByUnderTwentyFivePriceChain() {
        filterByPriceUnder.click();
        return this;
    }

    public void sortByMiddlePrice() {
        filterByPriceMiddle.click();
    }

    public CategoryPage sortByMiddlePriceChain() {
        filterByPriceMiddle.click();
        return this;
    }

    public void clickRemoveFilter() {
        removeFilter.click();
    }

    public CategoryPage clickRemoveFilterChain() {
        removeFilter.click();
        return this;
    }
}
