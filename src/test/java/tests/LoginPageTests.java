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

public class LoginPageTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("[LoginPageTests] setUp");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.netflix.com/login");
    }

    @Test
    public void verifyLoginPageUrl() {
        System.out.println("[LoginPageTests] verifyLoginPageUrl");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void verifyEmailInputIsDisplayed() {
        System.out.println("[LoginPageTests] verifyEmailInputIsDisplayed");
        WebElement emailInput = driver.findElement(By.name("userLoginId"));
        Assert.assertTrue(emailInput.isDisplayed());
    }

    @Test
    public void verifyContinueButtonIsDisplayed() {
        System.out.println("[LoginPageTests] verifyContinueButtonIsDisplayed");
        WebElement continueBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        Assert.assertTrue(continueBtn.isDisplayed());
    }

    @Test
    public void verifyPasswordAppearsAfterContinue() {
        System.out.println("[LoginPageTests] verifyPasswordAppearsAfterContinue");
        driver.findElement(By.name("userLoginId")).sendKeys("liviacorreiia@gmail.com");
        driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();

        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        Assert.assertTrue(passwordInput.isDisplayed());
    }

    @Test
    public void verifyHelpTextExists() {
        System.out.println("[LoginPageTests] verifyHelpTextExists");
        boolean found = driver.getPageSource().toLowerCase().contains("help");
        Assert.assertTrue(found);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("[LoginPageTests] tearDown");
        if (driver != null) {
            driver.quit();
        }
    }
}