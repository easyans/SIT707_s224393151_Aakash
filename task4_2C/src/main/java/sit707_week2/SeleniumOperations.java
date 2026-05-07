package sit707_week2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 * This class demonstrates Selenium locator APIs to identify HTML elements.
 * 
 * Details in Selenium documentation https://www.selenium.dev/documentation/webdriver/elements/locators/
 * 
 * @author Ahsan Habib
 */
public class SeleniumOperations {

	public static void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void officeworks_registration_page(String url) {
		// Step 1: Locate chrome driver folder in the local drive.
		System.setProperty("webdriver.chrome.driver","/Users/aakashraj/Downloads/chromedriver-mac-arm64/chromedriver");
		
		// Step 2: Use above chrome driver to open up a chromium browser.
		System.out.println("Fire up chrome browser.");
		WebDriver driver = new ChromeDriver();
		
		System.out.println("Driver info: " + driver);
		
		sleep(2);
	
		// Load a webpage in chromium browser.
		driver.get(url);
		
		// Find first input field which is firstname
		WebElement element = driver.findElement(By.id("firstname"));
		System.out.println("Found element: " + element);
		// Send first name
		element.sendKeys("Aakash");
		driver.findElement(By.id("lastname")).sendKeys("Sakhineti");
		//Email Id
		driver.findElement(By.id("email")).sendKeys("aakashraj232002@gmail.com");
		// Password
		driver.findElement(By.id("password")).sendKeys("appleiPhone17@pro");
		// Confirming password (This is a wrong password)
		driver.findElement(By.id("confirmPassword")).sendKeys("wrongpass143");
		
		try {
			driver.findElement(By.id("phoneNumber")).sendKeys("0434991255");
		} catch (Exception e){
			System.out.println("Phone field not found!");
		}
		sleep(3);
		try {
		    driver.findElement(By.xpath("//button[contains(text(),'Create account')]")).click();
		} catch (Exception e) {
		    driver.findElement(By.cssSelector("button[type='submit']")).click();
		}
		sleep(3);
		try {
		    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(src, new File("officeworks_screenshot.png"));
		    System.out.println("Screenshot saved!");
		} catch (Exception e) {
		    System.out.println("Screenshot failed");
		}
		sleep(2);
		// close chrome driver
		driver.close();	
	}

	public static void second_website_test(String url) {
		System.setProperty("webdriver.chrome.driver","/Users/aakashraj/Downloads/chromedriver-mac-arm64/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get(url);
		sleep(3);
		
		// 2nd webpage username
		driver.findElement(By.name("username")).sendKeys("student");
		//Password
		driver.findElement(By.name("password")).sendKeys("Password123");
		
		sleep(3);
		
		//Reg button clicking 
		try {
			driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		} catch (Exception e) {
			System.out.println("Registration button not found");
		}
		
		sleep(3);
		// webpage screenshot capture
		try {
		    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(src, new File("testloginform_screenshot.png"));
		    System.out.println("Screenshot saved!");
		} catch (Exception e) {
		    System.out.println("Screenshot failed");
		}
		sleep(2);
		// close chrome driver
		driver.close();	
	}	
}
