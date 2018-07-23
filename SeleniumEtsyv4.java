package test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

//created by Jesse Penber, 7/20 update: Added results Sort based on Lowest Price
//To do: standardize which product is picked by clicking the first one with the class Organic
// full Class organic-impression display-inline-block listing-link logged

public class AddToCartTestsorted {
	public static void main(String[] args) {
		// set driver properties to Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jpenber\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//navigate to Etsy
		driver.navigate().to("https://www.etsy.com");
		
		//give time for page to load
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//enter "calico cat" in Search bar and press Return key
		driver.findElement(By.cssSelector("#search-query")).sendKeys("calico cat");
		driver.findElement(By.cssSelector("#search-query")).sendKeys(Keys.RETURN);
		
		//give time for page to load
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Sort results by Lowest Price
		WebElement sortButton = driver.findElement(By.xpath("//div[@class='text-smaller']"));
		sortButton.click();
		WebElement lowestPrice = driver.findElement(By.linkText("Lowest Price"));
		lowestPrice.click();
		
		//click the first relevant product displayed, does not work if member from top row, Ad, is clicked
		WebElement product = driver.findElement(By.partialLinkText("kitty"));
		product.click();
		
		//NOT WORKING. loop through list of elements, look for the one that's Organic
		//List<WebElement> listOfLinks = driver.findElements(By.partialLinkText("Cat"));
		//System.out.println(listOfLinks);
		
		//for (WebElement element : listOfLinks) {
		//	if (element.getClass().toString() == "organic-impression display-inline-block listing-link  logged") {
		//		WebElement product = driver.findElement(By.partialLinkText("Cat"));
		//		product.click();		
		//	}
		//}
		
		//product.click();
		
		//give time for page to load
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		//click Shipping cost
		driver.findElement(By.xpath("//button[@class='button-empty btn btn-link text-orange p-xs-0']")).click();
		
		//enter zip code
		driver.findElement(By.xpath("//input[@class='estimate-postal-code input input-large-mweb']")).sendKeys("98102");
		
		//check if quality dropdown present. If present, select quality
		if(!driver.findElements(By.xpath("//select[@id='inventory-variation-select-0']")).isEmpty()) {
			Select dropdownquality = new Select(driver.findElement(By.xpath("//select[@id='inventory-variation-select-0']")));
			dropdownquality.selectByIndex(2);
		} else {
			System.out.print("No quality selector present");
		}
		
		//give time for page to load
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//checks if quantity dropdown present. If present, select quantity
		if(!driver.findElements(By.xpath("//select[@id='inventory-variation-select-quantity']")).isEmpty()) {
			Select dropdownquantity = new Select(driver.findElement(By.xpath("//select[@id='inventory-variation-select-quantity']")));
			dropdownquantity.selectByIndex(1);
		} else {
			System.out.println("No quantity selector present");
		}
		
		//give time for page to load
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//click the Add to Cart button
		driver.findElement(By.xpath("//div[@class = 'btn-text']")).click(); 
		
	}
}