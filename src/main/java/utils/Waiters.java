package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {
    public static void waitUtilElementToBeClickable(WebDriver driver, long timeout, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilPageIsLoaded(WebDriver driver, long timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(
                webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState")
                        .equals("complete"));
    }
}
