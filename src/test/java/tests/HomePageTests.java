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

public class HomePageTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.netflix.com/");
    }

    // 1. Title contains Netflix
    @Test
    public void verifyHomePageTitle() {
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("netflix"));
    }

    // 2. URL is correct
    @Test
    public void verifyHomePageUrl() {
        Assert.assertTrue(driver.getCurrentUrl().contains("netflix.com"));
    }

    // 3. Sign In button exists
    @Test
    public void verifySignInButtonExists() {
        boolean exists = driver.getPageSource().toLowerCase().contains("sign in");
        Assert.assertTrue(exists);
    }

    // 4. Page contains main call-to-action text
    @Test
    public void verifyMainContentExists() {
        boolean hasContent = driver.getPageSource().toLowerCase().contains("watch")
                || driver.getPageSource().toLowerCase().contains("unlimited")
                || driver.getPageSource().toLowerCase().contains("movies");
        Assert.assertTrue(hasContent);
    }

    // 5. Page loads after refresh
    @Test
    public void verifyPageRefresh() {
        driver.navigate().refresh();
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("netflix"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}