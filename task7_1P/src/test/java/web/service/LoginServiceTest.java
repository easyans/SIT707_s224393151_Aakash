package web.service;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginServiceTest {

    private static final String CHROME_DRIVER_PATH =
            "/Users/aakashraj/Downloads/chromedriver-mac-arm64/chromedriver";

    private static final String LOGIN_HTML_PATH =
            "file:///Users/aakashraj/Downloads/7.1P-resources/pages/login.html";

    private void sleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private String getLoginPagePath() {
        return LOGIN_HTML_PATH;
    }

    @Test
    public void testLoginSuccess() {
        WebDriver driver = getDriver();
        driver.navigate().to(getLoginPagePath());
        sleep(2);

        driver.findElement(By.id("username")).sendKeys("ahsan");
        driver.findElement(By.id("passwd")).sendKeys("ahsan_pass");
        driver.findElement(By.id("dob")).sendKeys("01012000"); 
        driver.findElement(By.cssSelector("[type=submit]")).submit();
        sleep(2);

        String title = driver.getTitle();
        System.out.println("FT-01 Title: " + title);
        Assert.assertEquals("success", title);
        driver.close();
    }

    public void testLoginFailWrongPassword() {
        WebDriver driver = getDriver();
        driver.navigate().to(getLoginPagePath());
        sleep(2);

        driver.findElement(By.id("username")).sendKeys("ahsan");
        driver.findElement(By.id("passwd")).sendKeys("wrong");
        driver.findElement(By.id("dob")).sendKeys("01012000");
        driver.findElement(By.cssSelector("[type=submit]")).submit();
        sleep(2);

        String title = driver.getTitle();
        System.out.println("FT-02 Title: " + title);
        Assert.assertEquals("fail", title);
        driver.close();
    }
    @Test
    public void testLoginFailWrongDob() {
        WebDriver driver = getDriver();
        driver.navigate().to(getLoginPagePath());
        sleep(2);

        driver.findElement(By.id("username")).sendKeys("ahsan");
        driver.findElement(By.id("passwd")).sendKeys("ahsan_pass");
        driver.findElement(By.id("dob")).sendKeys("01011999");
        driver.findElement(By.cssSelector("[type=submit]")).submit();
        sleep(2);

        String title = driver.getTitle();
        System.out.println("FT-03 Title: " + title);
        Assert.assertEquals("fail", title);
        driver.close();
    }

    @Test
    public void testLoginFailEmptyFields() {
        WebDriver driver = getDriver();
        driver.navigate().to(getLoginPagePath());
        sleep(2);

        driver.findElement(By.cssSelector("[type=submit]")).submit();
        sleep(2);

        String title = driver.getTitle();
        System.out.println("FT-04 Title: " + title);
        Assert.assertEquals("fail", title);
        driver.close();
    }

    @Test
    public void testLoginFailWrongUsername() {
        WebDriver driver = getDriver();
        driver.navigate().to(getLoginPagePath());
        sleep(2);

        driver.findElement(By.id("username")).sendKeys("wrong_user");
        driver.findElement(By.id("passwd")).sendKeys("ahsan_pass");
        driver.findElement(By.id("dob")).sendKeys("01012000");

        driver.findElement(By.cssSelector("[type=submit]")).submit();
        sleep(2);

        String title = driver.getTitle();
        System.out.println("FT-05 Title: " + title);
        Assert.assertEquals("fail", title);
        driver.close();
    }
    
    @Test
    public void testLoginFailAllWrong() {
        WebDriver driver = getDriver();
        driver.navigate().to(getLoginPagePath());
        sleep(2);

        driver.findElement(By.id("username")).sendKeys("bad_user");
        driver.findElement(By.id("passwd")).sendKeys("bad_pass");
        driver.findElement(By.id("dob")).sendKeys("12311990"); 

        driver.findElement(By.cssSelector("[type=submit]")).submit();
        sleep(2);

        String title = driver.getTitle();
        System.out.println("FT-06 Title: " + title);
        Assert.assertEquals("fail", title);
        driver.close();
    }
    
    @Test
    public void testLoginFailCorrectCredentialsWrongDob() {
        WebDriver driver = getDriver();
        driver.navigate().to(getLoginPagePath());
        sleep(2);

        driver.findElement(By.id("username")).sendKeys("ahsan");
        driver.findElement(By.id("passwd")).sendKeys("ahsan_pass");
        driver.findElement(By.id("dob")).sendKeys("06152001");

        driver.findElement(By.cssSelector("[type=submit]")).submit();
        sleep(2);

        String title = driver.getTitle();
        System.out.println("FT-07 Title: " + title);
        Assert.assertEquals("fail", title);
        driver.close();
    }
    
    @Test
    public void testLoginFailUppercaseUsername() {
        WebDriver driver = getDriver();
        driver.navigate().to(getLoginPagePath());
        sleep(2);

        driver.findElement(By.id("username")).sendKeys("AHSAN");
        driver.findElement(By.id("passwd")).sendKeys("ahsan_pass");
        driver.findElement(By.id("dob")).sendKeys("01012000");

        driver.findElement(By.cssSelector("[type=submit]")).submit();
        sleep(2);

        String title = driver.getTitle();
        System.out.println("FT-08 Title: " + title);
        Assert.assertEquals("fail", title);
        driver.close();
    }
}
