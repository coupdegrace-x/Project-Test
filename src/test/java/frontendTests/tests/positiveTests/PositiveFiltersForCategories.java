package frontendTests.tests.positiveTests;

import frontendTests.pages.*;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RandomUserData;
import frontendTests.utils.TestCase;
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

@TestCase(infoAboutCase = "PositiveFiltersBooks",
        path = "frontendTests/testCases/categories/books/PositiveFiltersBooks.md")
public class PositiveFiltersForCategories extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositiveFiltersForCategories.class);

    private BasePageRegisteredUser basePageRegisteredUser;
    private RegisterPage registerPage;
    private Category category;

    @BeforeMethod
    public void setUpRegPageAndBooks() {
        basePageRegisteredUser = new BasePageRegisteredUser(getDriver());
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
    public void testBookSortByPriceUnderTwentyFive() {
        LOGGER.info("Start positive testSortByPriceUnderTwentyFive");

        final double priceThreshold = 25.00;

        userRegistration();

        category.openBookCategoryChain()
                .sortByUnderTwentyFivePrice();

        List<Double> prices = category.getPriceFromCurrentPage();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15)).until(ExpectedConditions
                .textToBePresentInElement(category.getRemoveFilter(), category.getRemoveFilter().getText()));

        Assert.assertTrue(prices.stream()
                .allMatch(price -> price < priceThreshold));
        Assert.assertTrue(Objects.requireNonNull(getDriver()
                .getCurrentUrl()).contains("/books?price=-25"));

        LOGGER.info("Finish positive testSortByPriceUnderTwentyFive");
    }

    @Test(description = "Canceling filtering by Filter by price on the Books page when clicking on Remove Filter")
    public void testBookCancelFilter() {
        LOGGER.info("Start positive testBookCancelFilter");

        double middlePriceMin = 25.00;
        double middlePriceMax = 50.00;
        double minPrice = 10.00;

        userRegistration();

        category.openBookCategoryChain()
                .sortByMiddlePrice();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15)).until(ExpectedConditions
                .textToBePresentInElement(category.getRemoveFilter(), category.getRemoveFilter().getText()));

        List<Double> prices = category.getPriceFromCurrentPage();

        Assert.assertTrue(prices.stream()
                .allMatch(price -> price < middlePriceMin || price > middlePriceMax));

        category.clickRemoveFilter();

        prices = category.getPriceFromCurrentPage();

        Assert.assertTrue(prices.stream()
                .allMatch(price -> price >= minPrice));
        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl())
                .contains("/books"));

        LOGGER.info("Finish positive testBookCancelFilter");
    }

    @Test(description = "Sort books by Sort by using Name: Z to A on the Books page")
    public void testBookSortBySortByZToA() {
        LOGGER.info("Start positive testSortBySortByZToA");

        userRegistration();

        category.openBookCategory();

        List<String> notSortedTitlesBooks = category.getTitlesFromCurrentPage();

        category.sortByZToA();

        List<String> sortedTitlesBooksByZToA = category.getTitlesFromCurrentPage();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("/books?orderby=6"));

        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books?orderby=6"));
        Assert.assertNotEquals(notSortedTitlesBooks, sortedTitlesBooksByZToA);

        LOGGER.info("Finish positive testSortBySortByZToA");
    }
}
