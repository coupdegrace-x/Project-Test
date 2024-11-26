package frontendTests.tests.positiveTests;

import frontendTests.pages.CategoryPage;
import frontendTests.pages.RegisterPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
import frontendTests.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

@TestCase(infoAboutCase = "PositiveFiltersForCategoriesCases",
        path = "frontendTests/testCases/categories/books/PositiveFiltersForCategoriesCases.md")
public class PositiveFiltersForCategoriesTest extends BaseTest {

    private RegisterPage registerPage;
    private CategoryPage categoryPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    protected void setUpRegPageAndBooks() {
        categoryPage = new CategoryPage(getDriver());
        registerPage = new RegisterPage(getDriver());
        waitUtils = new WaitUtils(getDriver());
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
        logger.info("Start positive testSortByPriceUnderTwentyFive");

        final double priceThreshold = 25.00;

        userRegistration();

        categoryPage.openBookCategoryChain()
                .sortByUnderTwentyFivePrice();

        List<Double> prices = categoryPage.getProductPriceFromCurrentPage();

        waitUtils.waitForCondition(ExpectedConditions
                        .textToBePresentInElement(categoryPage.getRemoveFilter(),
                                categoryPage.getRemoveFilter().getText()),
                15);

        Assert.assertTrue(prices.stream().allMatch(price -> price < priceThreshold));
        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?price=-25"));

        logger.info("Finish positive testSortByPriceUnderTwentyFive");
    }

    @Test(description = "Canceling filtering by Filter by price on the Books page when clicking on Remove Filter")
    public void testCategoryCancelFilter() {
        logger.info("Start positive testBookCancelFilter");

        double middlePriceMin = 25.00;
        double middlePriceMax = 50.00;
        double minPrice = 10.00;

        userRegistration();

        categoryPage.openBookCategoryChain()
                .sortByMiddlePrice();

        waitUtils.waitForCondition(ExpectedConditions
                        .textToBePresentInElement(categoryPage.getRemoveFilter(),
                                categoryPage.getRemoveFilter().getText()),
                15);

        List<Double> prices = categoryPage.getProductPriceFromCurrentPage();

        Assert.assertTrue(prices.stream()
                .allMatch(price -> price < middlePriceMin || price > middlePriceMax));

        categoryPage.clickRemoveFilter();

        prices = categoryPage.getProductPriceFromCurrentPage();

        Assert.assertTrue(prices.stream().allMatch(price -> price >= minPrice));
        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books"));

        logger.info("Finish positive testBookCancelFilter");
    }

    @Test(description = "Sort books by Sort by using Name: Z to A on the Books page")
    public void testCategorySortBySortByZToA() {
        logger.info("Start positive testSortBySortByZToA");

        userRegistration();

        categoryPage.openBookCategory();

        List<String> notSortedTitlesBooks = categoryPage.getProductTitlesFromCurrentPage();

        categoryPage.sortByZToA();

        List<String> sortedTitlesBooksByZToA = categoryPage.getProductTitlesFromCurrentPage();

        waitUtils.waitForCondition(ExpectedConditions.urlContains("/books?orderby=6"), 15);

        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?orderby=6"));
        Assert.assertNotEquals(notSortedTitlesBooks, sortedTitlesBooksByZToA);

        logger.info("Finish positive testSortBySortByZToA");
    }

    @Test(description = "Sorting books by Display per page on the Books page")
    public void testCategorySortByDisplayFourPerPage() {
        logger.info("Start positive testSortByDisplayFourPerPage");

        userRegistration();

        categoryPage.openBookCategoryChain()
                .selectDisplayFourPerPage();

        waitUtils.waitForCondition(ExpectedConditions
                        .textToBePresentInElement(categoryPage.getNextPage(),
                                categoryPage.getNextPage().getText()),
                15);

        Assert.assertTrue(categoryPage.getQuantityProductsFromCurrentPage() <= 4);
        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?pagesize=4"));
        Assert.assertTrue(categoryPage.getCurrentPageTitle().contains("1"));
        Assert.assertTrue(categoryPage.getSecondPage().isDisplayed() && categoryPage.getSecondPage().isEnabled());
        Assert.assertTrue(categoryPage.getNextPage().isDisplayed() && categoryPage.getNextPage().isEnabled());

        logger.info("Finish positive testSortByDisplayFourPerPage");
    }

    @Test(description = "Sorting books by View on the Books page")
    public void testCategorySortByViewAsList() {
        logger.info("Start positive testSortByViewAsList");

        userRegistration();

        categoryPage.openBookCategoryChain()
                .selectViewAsList();

        waitUtils.waitForCondition(
                ExpectedConditions.presenceOfElementLocated(By.className("product-list")),
                15
        );

        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?viewmode=list"));
        Assert.assertTrue(getDriver().findElement(By.className("product-list")).isDisplayed());

        logger.info("Finish positive testSortByViewAsList");
    }

    @Test(description = "Checking the floating warning when adding an item to the cart")
    public void testCategoryCheckWarningWhenAddingItemToCart() {
        logger.info("Start positive testCategoryCheckWarningWhenAddingItemToCart");

        userRegistration();

        categoryPage.openBookCategoryChain()
                .addFirstAvailableProductFromCurrentPageToCart();

        waitUtils.waitForCondition(
                ExpectedConditions.textToBePresentInElement(categoryPage.getPopupWarningAddingItemToCart(),
                        "The product has been added to your shopping cart"),
                15
        );

        Assert.assertTrue(categoryPage.getPopupWarningAddingItemToCart()
                .getText().contains("The product has been added to your shopping cart"));

        Assert.assertTrue(categoryPage.getQuantityProductsInCart().getText().contains("1"));

        logger.info("Finish positive testCategoryCheckWarningWhenAddingItemToCart");
    }
}
