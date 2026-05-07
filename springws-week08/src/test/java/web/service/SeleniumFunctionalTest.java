package web.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumFunctionalTest {

    private static WebDriver driver;
    private static final String BASE_URL = "http://127.0.0.1:8080";

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) driver.quit();
    }

    private void waitForUrl(String regex) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.urlMatches(regex));
        try { Thread.sleep(500); } catch (InterruptedException e) { }
    }

    private void doLogin(String username, String password) {
        driver.get(BASE_URL + "/login");
        new WebDriverWait(driver, 15).until(
            ExpectedConditions.elementToBeClickable(By.name("username")));
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("passwd")).sendKeys(password);
        driver.findElement(By.name("dob")).sendKeys("01/01/1990");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    private void submitAnswer(String n1, String n2, String result) {
        new WebDriverWait(driver, 15).until(
            ExpectedConditions.elementToBeClickable(By.name("number1")));
        driver.findElement(By.name("number1")).sendKeys(n1);
        driver.findElement(By.name("number2")).sendKeys(n2);
        driver.findElement(By.name("result")).sendKeys(result);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    @Test
    public void test1_LoginWithWrongCredentials() {
        doLogin("wronguser", "wrongpass");
        waitForUrl(".*\\/login$");
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertTrue(driver.getPageSource().contains("Incorrect credentials."));
    }

    @Test
    public void test2_LoginWithCorrectCredentials() {
        doLogin("ahsan", "ahsan_pass");
        waitForUrl(".*\\/q1$");
        Assert.assertTrue(driver.getCurrentUrl().contains("/q1"));
    }

    @Test
    public void test3_Q1WrongAnswer() {
        doLogin("ahsan", "ahsan_pass");
        waitForUrl(".*\\/q1$");
        submitAnswer("3", "5", "99");
        waitForUrl(".*\\/q1$");
        Assert.assertTrue(driver.getCurrentUrl().contains("/q1"));
        Assert.assertTrue(driver.getPageSource().contains("Wrong answer, try again."));
    }

    @Test
    public void test4_Q1CorrectAnswer() {
        doLogin("ahsan", "ahsan_pass");
        waitForUrl(".*\\/q1$");
        submitAnswer("3", "5", "8");
        waitForUrl(".*\\/q2$");
        Assert.assertTrue(driver.getCurrentUrl().contains("/q2"));
    }

    @Test
    public void test5_Q2WrongAnswer() {
        doLogin("ahsan", "ahsan_pass");
        waitForUrl(".*\\/q1$");
        submitAnswer("3", "5", "8");
        waitForUrl(".*\\/q2$");
        submitAnswer("10", "4", "99");
        waitForUrl(".*\\/q2$");
        Assert.assertTrue(driver.getCurrentUrl().contains("/q2"));
        Assert.assertTrue(driver.getPageSource().contains("Wrong answer, try again."));
    }

    @Test
    public void test6_Q2CorrectAnswer() {
        doLogin("ahsan", "ahsan_pass");
        waitForUrl(".*\\/q1$");
        submitAnswer("3", "5", "8");
        waitForUrl(".*\\/q2$");
        submitAnswer("10", "4", "6");
        waitForUrl(".*\\/q3$");
        Assert.assertTrue(driver.getCurrentUrl().contains("/q3"));
    }

    @Test
    public void test7_Q3WrongAnswer() {
        doLogin("ahsan", "ahsan_pass");
        waitForUrl(".*\\/q1$");
        submitAnswer("3", "5", "8");
        waitForUrl(".*\\/q2$");
        submitAnswer("10", "4", "6");
        waitForUrl(".*\\/q3$");
        submitAnswer("4", "3", "99");
        waitForUrl(".*\\/q3$");
        Assert.assertTrue(driver.getCurrentUrl().contains("/q3"));
        Assert.assertTrue(driver.getPageSource().contains("Wrong answer, try again."));
    }

    @Test
    public void test8_Q3CorrectAnswer() {
        doLogin("ahsan", "ahsan_pass");
        waitForUrl(".*\\/q1$");
        submitAnswer("3", "5", "8");
        waitForUrl(".*\\/q2$");
        submitAnswer("10", "4", "6");
        waitForUrl(".*\\/q3$");
        submitAnswer("4", "3", "12");
        waitForUrl(".*8080\\/$");
        Assert.assertTrue(driver.getCurrentUrl().contains("8080/"));
    }
}




