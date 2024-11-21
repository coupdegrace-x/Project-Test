package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

// https://demowebshop.tricentis.com/books
@Getter
public class BooksPage extends BasePageRegisteredUser {

    private WebDriver driver;
    private Select select;

    public BooksPage(WebDriver driver) {
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

    @FindBy(xpath = "//input[@wfd-id='id4']")
    private WebElement addToCartInternetBookButton;

    @FindBy(xpath = "//input[@wfd-id='id5']")
    private WebElement addToCartFictionBookButton;

    @FindBy(xpath = "//input[@wfd-id='id6']")
    private WebElement addToCartHealthBookButton;

    @FindBy(xpath = "//a[text()='1']")
    private WebElement firstPageButton;

    @FindBy(xpath = "//a[text()='2']")
    private WebElement secondPageButton;

    @FindBy(xpath = "//a[text()='Previous']")
    private WebElement previousPageButton;

    @FindBy(xpath = "//a[text()='Next']")
    private WebElement nextPageButton;

    @FindBy(xpath = "//a[text()='Remove Filter']")
    private WebElement removeFilter;

    @FindBy(className = "actual-price")
    private List<WebElement> actualPrices;

    @FindBy(className = "product-title")
    private List<WebElement> productTitles;

    public List<Double> getPriceFromCurrentPage() {
        return actualPrices.stream()
                .map(priceElement -> Double.parseDouble(priceElement.getText()))
                .collect(Collectors.toList());
    }

    public List<String> getBookTitles() {
        return productTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void sortByZToA() {
        select = new Select(sortByDropDown);
        select.selectByVisibleText("Name: Z to A");
    }

    public BooksPage sortByZToAChain() {
        select = new Select(sortByDropDown);
        select.selectByVisibleText("Name: Z to A");
        return this;
    }

    public void sortByUnderTwentyFivePrice() {
        filterByPriceUnder.click();
    }

    public BooksPage sortByUnderTwentyFivePriceChain() {
        filterByPriceUnder.click();
        return this;
    }

    public void sortByMiddlePrice() {
        filterByPriceMiddle.click();
    }

    public BooksPage sortByMiddlePriceChain() {
        filterByPriceMiddle.click();
        return this;
    }

    public void clickRemoveFilter() {
        removeFilter.click();
    }

    public BooksPage clickRemoveFilterChain() {
        removeFilter.click();
        return this;
    }
}
