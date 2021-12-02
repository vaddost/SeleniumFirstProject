package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utils.Waiters;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class GoogleTests {
    static final long DEFAULT_TIMEOUT = 30;

    @Test
    public void checkImageTabOnSearchPage() {
        String baseUrl = "https://www.google.com/?hl=en";
        String searchPhrase = "image";
        String searchQuery = "search?q=image";
        String screenPath = "src/main/resources/checkImageTabOnSearchPage.png";

        String searchInputXpath = "//input[@name='q']";
        String imagesTabXpath = "//a[contains(text(), 'Images')]";
        String imagesTabXpathActive = "//span[contains(text(), 'Images')]";


        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        Waiters.waitUntilPageIsLoaded(driver, DEFAULT_TIMEOUT);
        driver.findElement(By.xpath(searchInputXpath)).sendKeys(searchPhrase, Keys.RETURN);
        WebElement imagesTabLink = driver.findElement(By.xpath(imagesTabXpath));
        Waiters.waitUtilElementToBeClickable(driver, DEFAULT_TIMEOUT, imagesTabLink);
        imagesTabLink.click();

        Waiters.waitUntilPageIsLoaded(driver, DEFAULT_TIMEOUT);
        String pageUrl = driver.getCurrentUrl();
        assertTrue(pageUrl.contains(searchQuery), "The page URL doesn't contain search query");

        assertTrue(driver.findElement(By.xpath(imagesTabXpathActive)).isDisplayed(),
                "The tab is not active");

        //Save screenshot to the resources folder
        File outputScreen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(outputScreen, new File(screenPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.close();
    }
}
