package sit707_week2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BunningsLoginTest {
    private static final String LOGIN_URL = "https://www.bunnings.com.au/login";
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 15);
        js = (JavascriptExecutor) driver;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    private void dismissBannerIfPresent() {
        try {
            Thread.sleep(2000);
            String[] selectors = {
                "#onetrust-accept-btn-handler",
                "button[data-testid='accept-button']",
                "button[id*='accept']",
                "button[aria-label*='Accept']"
            };
            for (String selector : selectors) {
                try {
                    WebElement btn = driver.findElement(By.cssSelector(selector));
                    js.executeScript("arguments[0].click();", btn);
                    Thread.sleep(1000);
                    break;
                } catch (Exception ignored) {}
            }
        } catch (Exception e) {
        }
    }
    private String submitLoginAndGetUrl(String email, String password)
            throws InterruptedException {

        driver.get(LOGIN_URL);
        dismissBannerIfPresent();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        Thread.sleep(1000);
        WebElement emailField = driver.findElement(By.id("username"));
        js.executeScript("arguments[0].removeAttribute('disabled');" +
                         "arguments[0].removeAttribute('readonly');" +
                         "arguments[0].value = arguments[1];" +
                         "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                         "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                         emailField, email);

        WebElement passwordField = driver.findElement(By.id("password"));
        js.executeScript("arguments[0].removeAttribute('disabled');" +
                         "arguments[0].removeAttribute('readonly');" +
                         "arguments[0].value = arguments[1];" +
                         "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                         "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                         passwordField, password);

        Thread.sleep(500);

        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        js.executeScript("arguments[0].click();", submitBtn);
        Thread.sleep(4000);

        return driver.getCurrentUrl();
    }
    @Test
    public void testTC2_ValidEmailWrongPassword_LoginFail() throws InterruptedException {
        String url = submitLoginAndGetUrl("test@example.com", "WrongPass999!");
        Assert.assertTrue(
                "TC2 FAILED: Expected URL to remain on /login. Actual: " + url,
                url.contains("/login"));
    }
    @Test
    public void testTC3_InvalidEmailFormat_LoginFail() throws InterruptedException {
        String url = submitLoginAndGetUrl("notanemail", "SomePassword1!");
        Assert.assertTrue(
                "TC3 FAILED: Expected URL to remain on /login. Actual: " + url,
                url.contains("/login"));
    }
    @Test
    public void testTC4_EmptyEmailEmptyPassword_LoginFail() throws InterruptedException {
        String url = submitLoginAndGetUrl("", "");
        Assert.assertTrue(
                "TC4 FAILED: Expected URL to remain on /login. Actual: " + url,
                url.contains("/login"));
    }
    @Test
    public void testTC5_EmptyEmail_LoginFail() throws InterruptedException {
        String url = submitLoginAndGetUrl("", "SomePassword1!");
        Assert.assertTrue(
                "TC5 FAILED: Expected URL to remain on /login. Actual: " + url,
                url.contains("/login"));
    }
    @Test
    public void testTC6_EmptyPassword_LoginFail() throws InterruptedException {
        String url = submitLoginAndGetUrl("test@example.com", "");
        Assert.assertTrue(
                "TC6 FAILED: Expected URL to remain on /login. Actual: " + url,
                url.contains("/login"));
    }
}