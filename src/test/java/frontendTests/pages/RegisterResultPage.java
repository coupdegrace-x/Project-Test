package frontendTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

// https://demowebshop.tricentis.com/registerresult/1
@Getter
public class RegisterResultPage {

    private final WebDriver driver;

    public RegisterResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "page-title")
    private WebElement pageTitle;

    @FindBy(className = "page-body")
    private WebElement pageBody;

    public boolean atRegisterResultPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("registerresult/1");
    }
}
