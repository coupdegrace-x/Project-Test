package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://demowebshop.tricentis.com/
@Getter
public class BasePageRegisteredUser {

    private final WebDriver driver;

    public BasePageRegisteredUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='top-menu']//a[@href='/books']")
    private WebElement topMenuBooksLink;

    public void openBookCategory() {
        driver.get("https://demowebshop.tricentis.com/books");
    }

    public Category openBookCategoryChain() {
        driver.get("https://demowebshop.tricentis.com/books");
        return new Category(driver);
    }
}
