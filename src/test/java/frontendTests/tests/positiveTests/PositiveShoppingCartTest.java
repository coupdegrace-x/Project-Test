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

    private void addProductToShoppingCart() {
        categoryPage.openBookCategoryChain()
                .addAllAvailableProductsFromCurrentPageToCartChain();
    }

    @Test(description = "Removing items from the shopping cart when selecting the checkout box in the Remove column")
    public void testShopCartRemoveProduct() {
        LOGGER.info("Start positive testShopCartRemoveProduct");

        userRegistration();

        addProductToShoppingCart();

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

        addProductToShoppingCart();

        shoppingCartPage.openShoppingCartChain()
                .removeQtyProductsChain()
                .clickUpdateShoppingCart();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .visibilityOf(shoppingCartPage.getOrderSummary()));

        Assert.assertTrue(shoppingCartPage.shoppingCartIsEmpty());

        LOGGER.info("Finish positive testShopCartRemoveProductQuantity");
    }
}
