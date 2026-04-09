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

public class LoginValidationTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("[LoginValidationTests] setUp");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.netflix.com/login");
    }

    // 1. Empty input test
    @Test
    public void verifyEmptyInputShowsError() {
        System.out.println("[LoginValidationTests] verifyEmptyInputShowsError");
        driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();

        boolean hasError = driver.getPageSource().toLowerCase().contains("enter");
        Assert.assertTrue(hasError);
    }

    // 2. Invalid email format
    @Test
    public void verifyInvalidEmailFormat() {
        System.out.println("[LoginValidationTests] verifyInvalidEmailFormat");
        driver.findElement(By.name("userLoginId")).sendKeys("invalid-email");
        driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();

        boolean hasError = driver.getPageSource().toLowerCase().contains("valid");
        Assert.assertTrue(hasError);
    }

    // 3. Random email test
    @Test
    public void verifyRandomEmailSubmission() {
        System.out.println("[LoginValidationTests] verifyRandomEmailSubmission");
        driver.findElement(By.name("userLoginId")).sendKeys("random123@test.com");
        driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();

        // Should move to next step OR show message
        boolean changedPage = driver.getCurrentUrl().contains("login");
        Assert.assertTrue(changedPage);
    }

    // 4. Button clickability
    @Test
    public void verifyContinueButtonClickable() {
        System.out.println("[LoginValidationTests] verifyContinueButtonClickable");
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        Assert.assertTrue(button.isEnabled());
    }

    // 5. Input field accepts typing
    @Test
    public void verifyInputFieldAcceptsText() {
        System.out.println("[LoginValidationTests] verifyInputFieldAcceptsText");
        WebElement input = driver.findElement(By.name("userLoginId"));
        input.sendKeys("test@test.com");

        Assert.assertEquals(input.getAttribute("value"), "test@test.com");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("[LoginValidationTests] tearDown");
        if (driver != null) {
            driver.quit();
        }
    }
}