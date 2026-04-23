//package web.service;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//
//public class LoginServiceTest {
//	
//	private void sleep(long sec) {
//		try {
//			Thread.sleep(sec*1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testLoginSuccess() {
//		System.setProperty(
//				"webdriver.chrome.driver", 
//				"/home/mahabib/java_lib/chromedriver-linux64/chromedriver");
//		
//		WebDriver driver = new ChromeDriver();		
//		System.out.println("Driver info: " + driver);
//		
//		// Full path where login.html is located.
//		// You can click on html file and copy the path shown in your browser.
//		//
//		driver.navigate().to(
//				"file:///home/mahabib/Documents/deakin_local/teaching/2024/sit707/jetty/pages/login.html");
//		sleep(5);
//		
//		// Find username element
//		//
//		WebElement ele = driver.findElement(By.id("username"));
//		ele.clear();
//		ele.sendKeys("ahsan");
//		
//		// Find password element
//		//
//		ele = driver.findElement(By.id("passwd"));
//		ele.clear();
//		ele.sendKeys("ahsan_pass");
//		
//		// Find Submit button, and click on button.
//		//
//		ele = driver.findElement(By.cssSelector("[type=submit]"));
//		ele.submit();
//		
//		sleep(5);
//		
//		/*
//		 * On successful login, the title of page changes to 'success',
//		 * otherwise, 'fail'.
//		 */
//		String title = driver.getTitle();
//		System.out.println("Title: " + title);
//		
//		Assert.assertEquals(title, "success");
//		
//		driver.close();
//	}
//}

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
            "/usr/local/bin/chromedriver";

    private static final String LOGIN_HTML_PATH =
            "file:///Users/YOUR_MAC_USERNAME/Documents/sit707/jetty/pages/login.html"; 
    
    private void sleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String performLogin(String username, String password, String dob) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        String title;
        try {
            driver.navigate().to(LOGIN_HTML_PATH);
            sleep(3);

            // Fill username
            WebElement ele = driver.findElement(By.id("username"));
            ele.clear();
            ele.sendKeys(username);

            // Fill password
            ele = driver.findElement(By.id("passwd"));
            ele.clear();
            ele.sendKeys(password);

            // Fill dob  (HTML date input expects yyyy-mm-dd)
            ele = driver.findElement(By.id("dob"));
            ele.clear();
            ele.sendKeys(dob);

            // Submit the form
            ele = driver.findElement(By.cssSelector("[type=submit]"));
            ele.submit();
            sleep(5);

            title = driver.getTitle();
            System.out.println("[" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "] Page title: " + title);
        } finally {
            driver.close();
        }
        return title;
    }

    // FT-01 : All valid credentials → success
    @Test
    public void testFT01_ValidCredentials_Success() {
        String title = performLogin("ahsan", "ahsan_pass", "2001-01-01");
        Assert.assertEquals("FT-01: All valid credentials should return 'success'",
                "success", title);
    }

    // FT-02 : Wrong username → fail
    @Test
    public void testFT02_WrongUsername_Fail() {
        String title = performLogin("wrong_user", "ahsan_pass", "2001-01-01");
        Assert.assertEquals("FT-02: Wrong username should return 'fail'",
                "fail", title);
    }

    // FT-03 : Wrong password → fail
    @Test
    public void testFT03_WrongPassword_Fail() {
        String title = performLogin("ahsan", "wrong_pass", "2001-01-01");
        Assert.assertEquals("FT-03: Wrong password should return 'fail'",
                "fail", title);
    }

    // FT-04 : Wrong dob → fail
    @Test
    public void testFT04_WrongDob_Fail() {
        String title = performLogin("ahsan", "ahsan_pass", "1990-05-15");
        Assert.assertEquals("FT-04: Wrong DoB should return 'fail'",
                "fail", title);
    }

    // FT-05 : All three fields wrong → fail
    @Test
    public void testFT05_AllFieldsWrong_Fail() {
        String title = performLogin("bad_user", "bad_pass", "1999-12-31");
        Assert.assertEquals("FT-05: All wrong credentials should return 'fail'",
                "fail", title);
    }

    // FT-06 : All fields empty → fail
    @Test
    public void testFT06_EmptyFields_Fail() {
        String title = performLogin("", "", "");
        Assert.assertEquals("FT-06: Empty fields should return 'fail'",
                "fail", title);
    }

    // FT-07 : Correct username & password, wrong dob → fail
    @Test
    public void testFT07_CorrectUsernamePassword_WrongDob_Fail() {
        String title = performLogin("ahsan", "ahsan_pass", "2000-06-15");
        Assert.assertEquals("FT-07: Correct username/password with wrong DoB should return 'fail'",
                "fail", title);
    }

    // FT-08 : Username with wrong case → fail  (case-sensitivity check)
    @Test
    public void testFT08_UppercaseUsername_Fail() {
        String title = performLogin("AHSAN", "ahsan_pass", "2001-01-01");
        Assert.assertEquals("FT-08: Uppercase username should return 'fail' (case-sensitive)",
                "fail", title);
    }
}