package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FooterTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.netflix.com/");
    }

    @Test
    public void verifyFooterExists() {
        String page = driver.getPageSource().toLowerCase();
        boolean hasFooter = page.contains("questions? contact us")
                || page.contains("faq")
                || page.contains("investor relations");
        Assert.assertTrue(hasFooter);
    }

    @Test
    public void verifyFAQLinkTextExists() {
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue(page.contains("faq"));
    }

    @Test
    public void verifyInvestorRelationsTextExists() {
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue(page.contains("investor relations"));
    }

    @Test
    public void verifyPrivacyTextExists() {
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue(page.contains("privacy"));
    }

    @Test
    public void verifySpeedTestTextExists() {
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue(page.contains("speed test") || page.contains("cookie preferences"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}