package frontendTests.tests.positiveTests;

import frontendTests.pages.CategoryPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RegistrationOfRandomUser;
import frontendTests.utils.TestCase;
import frontendTests.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

@TestCase(infoAboutCase = "PositiveFiltersForCategoriesCases",
        path = "frontendTests/testCases/categories/books/PositiveFiltersForCategoriesCases.md")
public class PositiveFiltersForCategoriesTest extends BaseTest {

    private RegisterPage registerPage;
    private CategoryPage categoryPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    protected void setUpPositiveFiltersForCategoriesTest() {
        categoryPage = new CategoryPage(getDriver());
        registerPage = new RegisterPage(getDriver());
        waitUtils = new WaitUtils(getDriver());

        registerUserAndOpenCategory();
    }

    private void registerUserAndOpenCategory() {
        new RegistrationOfRandomUser().userRegistration(registerPage);
        categoryPage.openBookCategory();
    }

    @Test(description = "Sorting books by Filter by price under 25.00 on the Books page")
    public void testCategorySortByPriceUnderTwentyFive() {
        logger.info("Start positive testSortByPriceUnderTwentyFive");

        final double priceThreshold = 25.00;

        categoryPage.sortByUnderTwentyFivePrice();

        final List<Double> prices = categoryPage.getProductPriceFromCurrentPage();

        waitUtils.waitForCondition(ExpectedConditions.textToBePresentInElement(
                        categoryPage.getRemoveFilter(),
                        categoryPage.getRemoveFilter().getText()),
                15
        );

        assertTrue(prices.stream().allMatch(price -> price < priceThreshold));
        assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?price=-25"));

        logger.info("Finish positive testSortByPriceUnderTwentyFive");
    }

    @Test(description = "Canceling filtering by Filter by price on the Books page when clicking on Remove Filter")
    public void testCategoryCancelFilter() {
        logger.info("Start positive testBookCancelFilter");

        final double middlePriceMin = 25.00;
        final double middlePriceMax = 50.00;
        final double minPrice = 10.00;

        categoryPage.sortByMiddlePrice();

        waitUtils.waitForCondition(ExpectedConditions.textToBePresentInElement(
                        categoryPage.getRemoveFilter(),
                        categoryPage.getRemoveFilter().getText()),
                15
        );

        List<Double> prices = categoryPage.getProductPriceFromCurrentPage();

        assertTrue(prices.stream()
                .allMatch(price -> price < middlePriceMin || price > middlePriceMax));

        categoryPage.clickRemoveFilter();

        prices = categoryPage.getProductPriceFromCurrentPage();

        assertTrue(prices.stream().allMatch(price -> price >= minPrice));
        assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books"));

        logger.info("Finish positive testBookCancelFilter");
    }

    @Test(description = "Sort books by Sort by using Name: Z to A on the Books page")
    public void testCategorySortBySortByZToA() {
        logger.info("Start positive testSortBySortByZToA");

        final List<String> notSortedTitlesBooks = categoryPage.getProductTitlesFromCurrentPage();

        categoryPage.sortByZToA();

        final List<String> sortedTitlesBooksByZToA = categoryPage.getProductTitlesFromCurrentPage();

        waitUtils.waitForCondition(
                ExpectedConditions.urlContains("/books?orderby=6"),
                15
        );

        assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?orderby=6"));
        Assert.assertNotEquals(notSortedTitlesBooks, sortedTitlesBooksByZToA);

        logger.info("Finish positive testSortBySortByZToA");
    }

    @Test(description = "Sorting books by Display per page on the Books page")
    public void testCategorySortByDisplayFourPerPage() {
        logger.info("Start positive testSortByDisplayFourPerPage");

        categoryPage.selectDisplayFourPerPage();

        waitUtils.waitForCondition(ExpectedConditions.textToBePresentInElement(
                        categoryPage.getNextPage(),
                        categoryPage.getNextPage().getText()),
                15
        );

        assertTrue(categoryPage.getQuantityProductsFromCurrentPage() <= 4);
        assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?pagesize=4"));
        assertTrue(categoryPage.getCurrentPageTitle().contains("1"));
        assertTrue(categoryPage.getSecondPage().isDisplayed() && categoryPage.getSecondPage().isEnabled());
        assertTrue(categoryPage.getNextPage().isDisplayed() && categoryPage.getNextPage().isEnabled());

        logger.info("Finish positive testSortByDisplayFourPerPage");
    }

    @Test(description = "Sorting books by View on the Books page")
    public void testCategorySortByViewAsList() {
        logger.info("Start positive testSortByViewAsList");

        categoryPage.selectViewAsList();

        waitUtils.waitForCondition(
                ExpectedConditions.presenceOfElementLocated(By.className("product-list")),
                15
        );

        assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?viewmode=list"));
        assertTrue(getDriver().findElement(By.className("product-list")).isDisplayed());

        logger.info("Finish positive testSortByViewAsList");
    }

    @Test(description = "Checking the floating warning when adding an item to the cart")
    public void testCategoryCheckWarningWhenAddingItemToCart() {
        logger.info("Start positive testCategoryCheckWarningWhenAddingItemToCart");

        categoryPage.addFirstAvailableProductFromCurrentPageToCart();

        waitUtils.waitForCondition(
                ExpectedConditions.textToBePresentInElement(categoryPage.getPopupWarningAddingItemToCart(),
                        "The product has been added to your shopping cart"),
                15
        );

        assertTrue(categoryPage.getPopupWarningAddingItemToCart().getText()
                .contains("The product has been added to your shopping cart"));

        assertTrue(categoryPage.getQuantityProductsInCart().getText().contains("1"));

        logger.info("Finish positive testCategoryCheckWarningWhenAddingItemToCart");
    }
}
