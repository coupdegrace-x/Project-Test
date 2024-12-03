package frontendTests.tests.positiveTests;

import frontendTests.pages.CategoryPage;
import frontendTests.pages.RegisterPage;
import frontendTests.pages.ShoppingCartPage;
import frontendTests.tests.BaseTest;
import frontendTests.utils.RegistrationOfRandomUser;
import frontendTests.utils.TestCase;
import frontendTests.utils.WaitUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

@TestCase(infoAboutCase = "PositiveShoppingCartCases",
        path = "frontendTests/testCases/shoppingCartCases/PositiveShoppingCartCases.md")
public class PositiveShoppingCartTest extends BaseTest {

    private RegisterPage registerPage;
    private CategoryPage categoryPage;
    private ShoppingCartPage shoppingCartPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    protected void setUpPositiveShoppingCartTest() {
        shoppingCartPage = new ShoppingCartPage(getDriver());
        categoryPage = new CategoryPage(getDriver());
        registerPage = new RegisterPage(getDriver());
        waitUtils = new WaitUtils(getDriver());

        registerRandomUser();
        addAllProductsAndOpenShoppingCart();
    }

    private void addAllProductsToShoppingCart() {
        categoryPage.openBookCategoryChain().addAllAvailableProductsFromCurrentPageToCart();
    }

    private void addFirstProductToShoppingCart() {
        categoryPage.openBookCategoryChain().addFirstAvailableProductFromCurrentPageToCart();
    }

    private void registerRandomUser() {
        new RegistrationOfRandomUser().userRegistration(registerPage);
    }

    private void addAllProductsAndOpenShoppingCart() {
        addAllProductsToShoppingCart();
        shoppingCartPage.openShoppingCart();
    }

    @Test(description = "Removing items from the shopping cart when selecting the checkout box in the Remove column")
    public void testShopCartRemoveProduct() {
        logger.info("Start positive testShopCartRemoveProduct");

        shoppingCartPage
                .removeFromCartChain()
                .clickUpdateShoppingCart();

        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(
                        shoppingCartPage.getOrderSummary()),
                10
        );

        assertTrue(shoppingCartPage.shoppingCartIsEmpty());

        logger.info("Finish positive testShopCartRemoveProduct");
    }

    @Test(description = "Removing items from the shopping cart when the quantity of goods in the basket changes")
    public void testShopCartRemoveProductQuantity() {
        logger.info("Start positive testShopCartRemoveProductQuantity");

        shoppingCartPage
                .removeQuantityProductsChain()
                .clickUpdateShoppingCart();

        waitUtils.waitForCondition(ExpectedConditions.visibilityOf(
                        shoppingCartPage.getOrderSummary()),
                10
        );

        assertTrue(shoppingCartPage.shoppingCartIsEmpty());

        logger.info("Finish positive testShopCartRemoveProductQuantity");
    }

    @Test(description = "Continue shopping by clicking on `Continue shopping` in the shopping cart")
    public void testShoppingCartContinue() {
        logger.info("Start positive testShoppingCartContinue");

        shoppingCartPage.clickContinueShoppingCart();

        waitUtils.waitForCondition(
                ExpectedConditions.urlContains("/books"),
                10
        );

        assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/books"));

        logger.info("Finish positive testShoppingCartContinue");
    }

    @Test(description = "changing the quantity of the product in the cart when changing the data in the `Qty` field")
    public void testShoppingCartChangeQuantity() {
        logger.info("Start positive testShoppingCartChangeQuantity");

        final double totalPriceBeforeChanges = shoppingCartPage.getTotalPriceOfProducts();

        shoppingCartPage.addQuantityProductsChain()
                .clickUpdateShoppingCart();

        final double totalPriceAfterChanges = shoppingCartPage.getTotalPriceOfProducts();

        assertNotEquals(totalPriceBeforeChanges, totalPriceAfterChanges);

        logger.info("Finish positive testShoppingCartChangeQuantity");
    }
}
