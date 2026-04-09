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
        System.out.println("[FooterTests] setUp");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.netflix.com/");
    }

    @Test
    public void verifyFooterExists() {
        System.out.println("[FooterTests] verifyFooterExists");
        String page = driver.getPageSource().toLowerCase();
        boolean hasFooter = page.contains("questions? contact us")
                || page.contains("faq")
                || page.contains("investor relations");
        Assert.assertTrue(hasFooter);
    }

    @Test
    public void verifyFAQLinkTextExists() {
        System.out.println("[FooterTests] verifyFAQLinkTextExists");
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue(page.contains("faq"));
    }

    @Test
    public void verifyInvestorRelationsTextExists() {
        System.out.println("[FooterTests] verifyInvestorRelationsTextExists");
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue(page.contains("investor relations"));
    }

    @Test
    public void verifyPrivacyTextExists() {
        System.out.println("[FooterTests] verifyPrivacyTextExists");
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue(page.contains("privacy"));
    }

    @Test
    public void verifySpeedTestTextExists() {
        System.out.println("[FooterTests] verifySpeedTestTextExists");
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue(page.contains("speed test") || page.contains("cookie preferences"));
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("[FooterTests] tearDown");
        if (driver != null) {
            driver.quit();
        }
    }
}