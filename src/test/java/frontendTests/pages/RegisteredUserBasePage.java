package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://demowebshop.tricentis.com/
@Getter
public class RegisteredUserBasePage {

    private final WebDriver driver;

    public RegisteredUserBasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='top-menu']//a[@href='/books']")
    private WebElement topMenuBooksLink;

    public void openBookCategory() {
        driver.get("https://demowebshop.tricentis.com/books");
    }

    public CategoryPage openBookCategoryChain() {
        driver.get("https://demowebshop.tricentis.com/books");
        return new CategoryPage(driver);
    }
}
