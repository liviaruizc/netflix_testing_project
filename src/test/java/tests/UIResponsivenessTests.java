package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class UIResponsivenessTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.netflix.com/");
    }

    // 1. Desktop size
    @Test
    public void verifyDesktopView() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("netflix"));
    }

    // 2. Tablet size
    @Test
    public void verifyTabletView() {
        driver.manage().window().setSize(new Dimension(768, 1024));
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("netflix"));
    }

    // 3. Mobile size
    @Test
    public void verifyMobileView() {
        driver.manage().window().setSize(new Dimension(375, 812));
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("netflix"));
    }

    // 4. Resize keeps page functional
    @Test
    public void verifyResizeKeepsPageLoaded() {
        driver.manage().window().setSize(new Dimension(500, 800));
        driver.manage().window().setSize(new Dimension(1200, 900));

        Assert.assertTrue(driver.getCurrentUrl().contains("netflix.com"));
    }

    // 5. Page reload after resize
    @Test
    public void verifyReloadAfterResize() {
        driver.manage().window().setSize(new Dimension(375, 812));
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