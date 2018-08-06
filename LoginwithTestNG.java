package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Created by Jesse Penber, 7-24-2018
//logs into Etsy user account

public class LoginTestwithTestNG {

	WebDriver driver;
	
	//sets driver to Chrome
	@BeforeClass
	public void setBrowser(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jpenber\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	//opens Etsy in Chrome
	@Test
	public void startApp() {
		driver.get("https://www.etsy.com");
		String currentURL = driver.getCurrentUrl();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Assert.assertTrue(currentURL.contains("etsy.com"));
	}
	
	//logs in, checks if shop icon is present, indicating logged in
	//works inconsistently due to Etsy sometimes opening with different Sign In format, need to investigate
	@Test(dependsOnMethods="startApp")
	public void loginApp() {

		driver.findElement(By.cssSelector("#register")).click();
		driver.findElement(By.partialLinkText("Sign in")).click();
		driver.findElement(By.cssSelector("#username-existing")).sendKeys("penberelf46@gmail.com");
		driver.findElement(By.cssSelector("#password-existing")).sendKeys("test12");
		driver.findElement(By.cssSelector("#signin-button")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		boolean status1=driver.findElement(By.xpath("//span[@class='inline-svg nav-icon']")).isDisplayed();
		Assert.assertTrue(status1);
	}
	
	//logs out, checks if Sign In button is present, indicating logged out
	@Test(dependsOnMethods="loginApp")
	public void logoutApp() {
		driver.findElement(By.xpath("//li[@class='user-nav has-sub-nav']")).click();
		driver.findElement(By.partialLinkText("Sign out")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		boolean status2=driver.findElement(By.cssSelector("#sign-in")).isDisplayed();
		Assert.assertTrue(status2);
		
	}
	
	//closes browser after tests run
	@AfterClass
	public void closeApp() {
	//	driver.quit();
	}
}
