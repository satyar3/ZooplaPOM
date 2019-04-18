package com.qa.rough;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Rough {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\selenium_drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(55, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("https://www.zoopla.co.uk");
		
		//driver.findElement(By.id("search-input-location")).sendKeys("London");		
		driver.findElement(By.cssSelector("#search-input-location")).sendKeys("London");
		driver.findElement(By.cssSelector("#search-submit")).click();
		
		List<WebElement> propertyPrice = driver.findElements(By.cssSelector(".listing-results-right.clearfix >a"));
		List<Integer> actualPrice = new ArrayList<Integer>();
		
		int counter = 0;
		
		for(WebElement e : propertyPrice)
		{
			String price = e.getText();
			price = price.replaceAll("[^0-9]", "");
			actualPrice.add(Integer.parseInt(price));
			System.out.println("Original Price : "+e.getText());
		}
		
		
		//Collections.sort(actualPrice);
		//Collections.reverse(actualPrice);
		
		Collections.sort(actualPrice, Collections.reverseOrder()); 
		
		for(int p : actualPrice)
		{
			System.out.println(p);
		}
		
		scroll(propertyPrice.get(3), driver);
		Thread.sleep(2000);
		
		System.out.println("Clicking on : "+propertyPrice.get(4).getText());
		propertyPrice.get(4).click();
		
		String propNameAfterCLick = driver.findElement(By.cssSelector(".dp-sidebar-wrapper h2")).getText();
		System.out.println("Prop name: "+propNameAfterCLick);
		List<WebElement>agentName = driver.findElements(By.cssSelector(".ui-agent__name"));
		agentName.get(0).click();
		
		
		List<WebElement> propListAfterClick = driver.findElements(By.cssSelector(".listing-results-address"));
		
		for(WebElement el: propListAfterClick)
		{
			//System.out.println(el.getText());
			if(el.getText().equalsIgnoreCase(propNameAfterCLick))
			{
				System.out.println("Prop Found : "+ el.getText());
			}
		}
	}
	
	public static void scroll(WebElement element, WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
