package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HelpCenterTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("[HelpCenterTests] setUp");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://help.netflix.com/");
    }

    // 1. Page loads correctly
    @Test
    public void verifyHelpCenterPageLoads() {
        System.out.println("[HelpCenterTests] verifyHelpCenterPageLoads");
        Assert.assertTrue(driver.getCurrentUrl().contains("help.netflix.com"));
    }

    // 2. Page title contains Netflix
    @Test
    public void verifyPageTitleContainsNetflix() {
        System.out.println("[HelpCenterTests] verifyPageTitleContainsNetflix");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("netflix"));
    }

    // 3. Search bar exists
    @Test
    public void verifySearchBarExists() {
        System.out.println("[HelpCenterTests] verifySearchBarExists");
        WebElement searchBar = driver.findElement(By.name("q"));
        Assert.assertTrue(searchBar.isDisplayed());
    }

    // 4. Help content exists (safe check)
    @Test
    public void verifyHelpContentExists() {
        System.out.println("[HelpCenterTests] verifyHelpContentExists");
        boolean contentExists = driver.getPageSource().toLowerCase().contains("help")
                || driver.getPageSource().toLowerCase().contains("account")
                || driver.getPageSource().toLowerCase().contains("billing");
        Assert.assertTrue(contentExists);
    }

    // 5. Sign in option exists
    @Test
    public void verifySignInOptionExists() {
        System.out.println("[HelpCenterTests] verifySignInOptionExists");
        boolean signInExists = driver.getPageSource().toLowerCase().contains("sign in");
        Assert.assertTrue(signInExists);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("[HelpCenterTests] tearDown");
        if (driver != null) {
            driver.quit();
        }
    }
}