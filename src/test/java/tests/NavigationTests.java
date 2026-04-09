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

public class NavigationTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("[NavigationTests] setUp");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.netflix.com/");
    }

    // 1. Navigate to login page
    @Test
    public void verifyNavigationToLoginPage() {
        System.out.println("[NavigationTests] verifyNavigationToLoginPage");
        driver.findElement(By.linkText("Sign In")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    // 2. Navigate back using logo
    @Test
    public void verifyLogoNavigationToHome() {
        System.out.println("[NavigationTests] verifyLogoNavigationToHome");
        driver.get("https://www.netflix.com/login");
        driver.findElement(By.cssSelector("a[href='/']")).click();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.netflix.com/"));
    }

    // 3. FAQ section expands
    @Test
    public void verifyFAQSectionExpands() {
        System.out.println("[NavigationTests] verifyFAQSectionExpands");
        driver.findElement(By.xpath("//*[contains(text(),'What is Netflix?')]")).click();

        boolean expanded = driver.getPageSource().toLowerCase().contains("watch anywhere")
                || driver.getPageSource().toLowerCase().contains("netflix is a streaming service")
                || driver.getPageSource().toLowerCase().contains("streaming service");
        Assert.assertTrue(expanded);
    }

    // 4. Page title contains Netflix
    @Test
    public void verifyPageTitleContainsNetflix() {
        System.out.println("[NavigationTests] verifyPageTitleContainsNetflix");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("netflix"));
    }

    // 5. Refresh keeps user on homepage
    @Test
    public void verifyPageRefresh() {
        System.out.println("[NavigationTests] verifyPageRefresh");
        driver.navigate().refresh();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.netflix.com/"));
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("[NavigationTests] tearDown");
        if (driver != null) {
            driver.quit();
        }
    }
}