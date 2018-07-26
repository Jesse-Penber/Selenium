package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

// 7/27/2018: created by Jesse Penber
// TestNG verification of error message displayed when email field blank on Gmail login

public class VerifyErrorMessage {
	
	@Test
	public void verificationTest() throws InterruptedException {
		// set driver properties to Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jpenber\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// open Gmail login, click Next with blank email field
		driver.manage().window().maximize();
		driver.get("https://gmail.com");	
		driver.findElement(By.cssSelector("#identifierNext")).click();
		
		// verify that displayed error message is as expected
		String actual_error = driver.findElement(By.xpath("//div[@jsname='B34EJ']")).getAttribute("innerHTML");
		String expected_error="Enter an email or phone number";
		Assert.assertEquals(actual_error, expected_error);
		
		System.out.println("Test complete");
	}
}
