package frontendTests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private final WebDriver driver;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public <T> T waitForConditionAndReturn(ExpectedCondition<T> condition, int waitingSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitingSeconds)).until(condition);
    }

    public <T> void waitForCondition(ExpectedCondition<T> condition, int waitingSeconds) {
        waitForConditionAndReturn(condition, waitingSeconds);
    }
}
