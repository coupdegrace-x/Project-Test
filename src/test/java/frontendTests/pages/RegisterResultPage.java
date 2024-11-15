package frontendTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Objects;

/*
Класс RegisterResultPage предназначен для проверки после регистрации пользователя,
т.к. после успешной регистрации пользователя перекидывает с страницы Register на страницу registerresult/1
метод: getTextPageTitle - получает ожидаемый текст "Register"
метод: getTextPageBody - получает ожидаемый текст "Your registration completed"
метод: atRegisterResultPage - проверяет находимся ли мы на нужной URL(ссылке), возвращает true или false
конструктор: RegisterResultPage, кол-во конструкторов == 1
 */
// https://demowebshop.tricentis.com/registerresult/1
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

    public String getTextPageTitle() {
        return pageTitle.getText();
    }

    public String getTextPageBody() {
        return pageBody.getText();
    }

    public boolean atRegisterResultPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("registerresult/1");
    }
}
