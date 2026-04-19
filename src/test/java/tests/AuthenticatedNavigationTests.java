package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Scanner;

public class AuthenticatedNavigationTests {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("[AuthenticatedNavigationTests] setUp");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.netflix.com/login");

        System.out.println("Please log in manually to Netflix, then press ENTER here to continue...");
        new Scanner(System.in).nextLine();
    }

    @Test
    public void verifyUserIsLoggedIn() {
        System.out.println("[AuthenticatedNavigationTests] verifyUserIsLoggedIn");
        String url = driver.getCurrentUrl().toLowerCase();
        Assert.assertTrue(
                url.contains("browse") || url.contains("profiles"),
                "Expected user to be in an authenticated area after manual login."
        );
    }

    @Test
    public void verifyBrowsePageAccessible() {
        System.out.println("[AuthenticatedNavigationTests] verifyBrowsePageAccessible");
        driver.get("https://www.netflix.com/browse");
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("browse"));
    }

    @Test
    public void verifySearchPageAccessible() {
        System.out.println("[AuthenticatedNavigationTests] verifySearchPageAccessible");
        driver.get("https://www.netflix.com/search");
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("search"));
    }

    @Test
    public void verifyBackAndForwardNavigationWorks() {
        System.out.println("[AuthenticatedNavigationTests] verifyBackAndForwardNavigationWorks");
        driver.get("https://www.netflix.com/browse");
        driver.get("https://www.netflix.com/search");

        driver.navigate().back();
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("browse"));

        driver.navigate().forward();
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("search"));
    }

    @Test
    public void verifyRefreshKeepsSessionActive() {
        System.out.println("[AuthenticatedNavigationTests] verifyRefreshKeepsSessionActive");
        driver.get("https://www.netflix.com/browse");
        driver.navigate().refresh();

        String url = driver.getCurrentUrl().toLowerCase();
        Assert.assertTrue(
                url.contains("browse") || url.contains("profiles"),
                "Expected session to remain active after refresh."
        );
    }

    @AfterClass
    public void tearDown() {
        System.out.println("[AuthenticatedNavigationTests] tearDown");
        if (driver != null) {
            driver.quit();
        }
    }
}