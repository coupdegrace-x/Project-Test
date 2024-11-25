package frontendTests.tests.positiveTests;

import frontendTests.pages.CategoryPage;
import frontendTests.pages.RegisterPage;
import frontendTests.pages.ShoppingCartPage;
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
import java.util.Objects;

@TestCase(infoAboutCase = "PositiveShoppingCartCases",
        path = "frontendTests/testCases/shoppingCartCases/PositiveShoppingCartCases.md")
public class PositiveShoppingCartTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositiveShoppingCartTest.class);

    private RegisterPage registerPage;
    private CategoryPage categoryPage;
    private ShoppingCartPage shoppingCartPage;

    @BeforeMethod
    public void setUpRegPageAndBooks() {
        shoppingCartPage = new ShoppingCartPage(getDriver());
        categoryPage = new CategoryPage(getDriver());
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

    private void addAllProductsToShoppingCart() {
        categoryPage.openBookCategoryChain()
                .addAllAvailableProductsFromCurrentPageToCart();
    }

    private void addFirstProductToShoppingCart() {
        categoryPage.openBookCategoryChain()
                .addFirstAvailableProductToCart();
    }

    @Test(description = "Removing items from the shopping cart when selecting the checkout box in the Remove column")
    public void testShopCartRemoveProduct() {
        LOGGER.info("Start positive testShopCartRemoveProduct");

        userRegistration();

        addAllProductsToShoppingCart();

        shoppingCartPage.openShoppingCartChain()
                .removeFromCartChain()
                .clickUpdateShoppingCart();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .visibilityOf(shoppingCartPage.getOrderSummary()));

        Assert.assertTrue(shoppingCartPage.shoppingCartIsEmpty());

        LOGGER.info("Finish positive testShopCartRemoveProduct");
    }

    @Test(description = "Removing items from the shopping cart when the quantity of goods in the basket changes")
    public void testShopCartRemoveProductQuantity() {
        LOGGER.info("Start positive testShopCartRemoveProductQuantity");

        userRegistration();

        addAllProductsToShoppingCart();

        shoppingCartPage.openShoppingCartChain()
                .removeQuantityProductsChain()
                .clickUpdateShoppingCart();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .visibilityOf(shoppingCartPage.getOrderSummary()));

        Assert.assertTrue(shoppingCartPage.shoppingCartIsEmpty());

        LOGGER.info("Finish positive testShopCartRemoveProductQuantity");
    }

    @Test(description = "Continue shopping by clicking on `Continue shopping` in the shopping cart")
    public void testShoppingCartContinue() {
        LOGGER.info("Start positive testShoppingCartContinue");

        userRegistration();

        addAllProductsToShoppingCart();

        shoppingCartPage
                .openShoppingCartChain()
                .clickContinueShoppingCart();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .urlContains("/books"));

        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books"));

        LOGGER.info("Finish positive testShoppingCartContinue");
    }

    @Test(description = "changing the quantity of the product in the cart when changing the data in the `Qty` field")
    public void testShoppingCartChangeQuantity() {
        LOGGER.info("Start positive testShoppingCartChangeQuantity");

        userRegistration();

        addAllProductsToShoppingCart();

        shoppingCartPage.openShoppingCart();

        double totalPriceBeforeChanges = shoppingCartPage.getTotalPriceOfProducts();

        shoppingCartPage.addQuantityProductsChain()
                .clickUpdateShoppingCart();

        double totalPriceAfterChanges = shoppingCartPage.getTotalPriceOfProducts();

        Assert.assertNotEquals(totalPriceBeforeChanges, totalPriceAfterChanges);

        LOGGER.info("Finish positive testShoppingCartChangeQuantity");
    }
}
