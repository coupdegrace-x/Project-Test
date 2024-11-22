package frontendTests.tests.positiveTests;

import frontendTests.pages.*;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@TestCase(infoAboutCase = "PositiveFiltersForCategoriesCases",
        path = "frontendTests/testCases/categories/books/PositiveFiltersForCategoriesCases.md")
public class PositiveFiltersForCategoriesTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositiveFiltersForCategoriesTest.class);

    private RegisterPage registerPage;
    private Category category;

    @BeforeMethod
    public void setUpRegPageAndBooks() {
        category = new Category(getDriver());
        registerPage = new RegisterPage(getDriver());
    }

    private void userRegistration() {
        final String passwordUser = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(passwordUser)
                .enterConfirmPasswordChain(passwordUser)
                .clickRegisterButton();
    }

    @Test(description = "Sorting books by Filter by price under 25.00 on the Books page")
    public void testCategorySortByPriceUnderTwentyFive() {
        LOGGER.info("Start positive testSortByPriceUnderTwentyFive");

        final double priceThreshold = 25.00;

        userRegistration();

        category.openBookCategoryChain()
                .sortByUnderTwentyFivePrice();

        List<Double> prices = category.getProductPriceFromCurrentPage();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15)).until(ExpectedConditions
                .textToBePresentInElement(category.getRemoveFilter(), category.getRemoveFilter().getText()));

        Assert.assertTrue(prices.stream().allMatch(price -> price < priceThreshold));
        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?price=-25"));

        LOGGER.info("Finish positive testSortByPriceUnderTwentyFive");
    }

    @Test(description = "Canceling filtering by Filter by price on the Books page when clicking on Remove Filter")
    public void testCategoryCancelFilter() {
        LOGGER.info("Start positive testBookCancelFilter");

        double middlePriceMin = 25.00;
        double middlePriceMax = 50.00;
        double minPrice = 10.00;

        userRegistration();

        category.openBookCategoryChain()
                .sortByMiddlePrice();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15)).until(ExpectedConditions
                .textToBePresentInElement(category.getRemoveFilter(), category.getRemoveFilter().getText()));

        List<Double> prices = category.getProductPriceFromCurrentPage();

        Assert.assertTrue(prices.stream()
                .allMatch(price -> price < middlePriceMin || price > middlePriceMax));

        category.clickRemoveFilter();

        prices = category.getProductPriceFromCurrentPage();

        Assert.assertTrue(prices.stream().allMatch(price -> price >= minPrice));
        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books"));

        LOGGER.info("Finish positive testBookCancelFilter");
    }

    @Test(description = "Sort books by Sort by using Name: Z to A on the Books page")
    public void testCategorySortBySortByZToA() {
        LOGGER.info("Start positive testSortBySortByZToA");

        userRegistration();

        category.openBookCategory();

        List<String> notSortedTitlesBooks = category.getProductTitlesFromCurrentPage();

        category.sortByZToA();

        List<String> sortedTitlesBooksByZToA = category.getProductTitlesFromCurrentPage();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("/books?orderby=6"));

        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?orderby=6"));
        Assert.assertNotEquals(notSortedTitlesBooks, sortedTitlesBooksByZToA);

        LOGGER.info("Finish positive testSortBySortByZToA");
    }

    @Test(description = "Sorting books by Display per page on the Books page")
    public void testCategorySortByDisplayFourPerPage() {
        LOGGER.info("Start positive testSortByDisplayFourPerPage");

        userRegistration();

        category.openBookCategoryChain()
                .selectDisplayFourPerPage();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions
                        .textToBePresentInElement(category.getNextPage(), category.getNextPage().getText()));

        Assert.assertTrue(category.getQuantityProductsFromCurrentPage() <= 4);
        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?pagesize=4"));
        Assert.assertTrue(category.getCurrentPageTitle().contains("1"));
        Assert.assertTrue(category.getSecondPage().isDisplayed() && category.getSecondPage().isEnabled());
        Assert.assertTrue(category.getNextPage().isDisplayed() && category.getNextPage().isEnabled());

        LOGGER.info("Finish positive testSortByDisplayFourPerPage");
    }

    @Test(description = "Sorting books by View on the Books page")
    public void testCategorySortByViewAsList() {
        LOGGER.info("Start positive testSortByViewAsList");

        userRegistration();

        category.openBookCategoryChain()
                .selectViewAsList();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.className("product-list")));

        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?viewmode=list"));
        Assert.assertTrue(getDriver().findElement(By.className("product-list")).isDisplayed());

        LOGGER.info("Finish positive testSortByViewAsList");
    }

    @Test(description = "Checking the floating warning when adding an item to the cart")
    public void testCategoryCheckWarningWhenAddingItemToCart() {
        LOGGER.info("Start positive testCategoryCheckWarningWhenAddingItemToCart");

        userRegistration();

        category.openBookCategoryChain()
                .addFirstAvailableProductToCart();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions
                        .visibilityOf(category.getPopupWarningAddingItemToCart()));

        Assert.assertTrue(category.getPopupWarningAddingItemToCart()
                .getText().contains("The product has been added to your shopping cart"));
        Assert.assertTrue(category.getQuantityProductsInCart().getText().contains("1"));

        LOGGER.info("Finish positive testCategoryCheckWarningWhenAddingItemToCart");
    }
}
