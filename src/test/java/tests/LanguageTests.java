package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LanguageTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("[LanguageTests] setUp");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.netflix.com/");
    }

    // 1. Language dropdown exists
    @Test
    public void verifyLanguageDropdownExists() {
        System.out.println("[LanguageTests] verifyLanguageDropdownExists");
        boolean exists = driver.getPageSource().toLowerCase().contains("language");
        Assert.assertTrue(exists);
    }

    // 2. Page contains English text
    @Test
    public void verifyDefaultLanguageIsEnglish() {
        System.out.println("[LanguageTests] verifyDefaultLanguageIsEnglish");
        boolean englishText = driver.getPageSource().toLowerCase().contains("watch");
        Assert.assertTrue(englishText);
    }

    // 3. Change language (if available)
    @Test
    public void verifyLanguageChangeOptionExists() {
        System.out.println("[LanguageTests] verifyLanguageChangeOptionExists");
        boolean hasOption = driver.getPageSource().toLowerCase().contains("english")
                || driver.getPageSource().toLowerCase().contains("español");
        Assert.assertTrue(hasOption);
    }

    // 4. Page still loads after refresh
    @Test
    public void verifyPageStillLoadsAfterRefresh() {
        System.out.println("[LanguageTests] verifyPageStillLoadsAfterRefresh");
        driver.navigate().refresh();
        Assert.assertTrue(driver.getCurrentUrl().contains("netflix.com"));
    }

    // 5. Page title still valid
    @Test
    public void verifyTitleAfterRefresh() {
        System.out.println("[LanguageTests] verifyTitleAfterRefresh");
        driver.navigate().refresh();
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("netflix"));
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("[LanguageTests] tearDown");
        if (driver != null) {
            driver.quit();
        }
    }
}