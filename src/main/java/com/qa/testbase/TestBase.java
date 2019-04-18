package com.qa.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.qa.utils.DriverManager;
import com.qa.utils.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	protected WebDriver driver = null;
	protected FileInputStream fs;
	protected Properties prop;
	protected String browserName;

	protected static JavascriptExecutor js;
	protected static EventFiringWebDriver e_driver;
	protected static WebEventListener eventlistener;
	
	protected static int pageLoadTimeout;
	protected static int webDriverWaitTime;
	
	protected static Logger log = LogManager.getLogger(TestBase.class);
	
	protected TestBase() {
		try {
			
			//System.out.println("Loading TestBase Constructor");

			fs = new FileInputStream("src\\test\\resources\\Properties\\config.properties");
			prop = new Properties();
			prop.load(fs);
			
		

		} catch (FileNotFoundException e) {
			System.out.println("Property file doesn't exist");
			// e.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Unable to load property file");
			// e1.printStackTrace();
		}
	}
	
	@BeforeSuite
	public void intitialize() {
		
		browserName = prop.getProperty("browser");
		if (driver == null) {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.chromedriver().setup();
				driver = new FirefoxDriver();
			}
			
			DriverManager.setDriver(driver);

			// To generate logs
			
			  e_driver = new EventFiringWebDriver(DriverManager.getDriver()); 
			  eventlistener   = new WebEventListener(); e_driver.register(eventlistener); 
			  driver = e_driver; 
			  DriverManager.setDriver(e_driver);
			 

			js = (JavascriptExecutor) DriverManager.getDriver();

			DriverManager.getDriver().manage().window().maximize();
			DriverManager.getDriver().manage().deleteAllCookies();

			pageLoadTimeout = Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
			webDriverWaitTime = Integer.parseInt(prop.getProperty("webDriverWaitTime"));

			DriverManager.getDriver().manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);

			//driver.get(prop.getProperty("URL"));
		}
	}
	
	/*
	 * public void locatorUtil(String locatorAddress, String locatorType) { switch
	 * (locatorType) { case "id": driver.findElement(By.id(locatorAddress)); break;
	 * 
	 * case "xpath": driver.findElement(By.linkText(locatorAddress)); break;
	 * 
	 * case "classname": driver.findElement(By.className(locatorAddress)); break;
	 * 
	 * case "linktext": driver.findElement(By.linkText(locatorAddress)); break;
	 * 
	 * case "partiallinktext":
	 * driver.findElement(By.partialLinkText(locatorAddress)); break;
	 * 
	 * case "cssselector": driver.findElement(By.cssSelector(locatorAddress));
	 * break;
	 * 
	 * case "name": driver.findElement(By.name(locatorAddress)); break;
	 * 
	 * case "tagname": driver.findElement(By.tagName(locatorAddress)); break;
	 * 
	 * default: break; }
	 * 
	 * }
	 */
	
	/*
	 * @BeforeTest public void setUp() {
	 * 
	 * }
	 */
	
	
	
	
	@AfterTest
	public void tearDown() {

		System.out.println("Closing all instances of the browser");
		driver.quit();
		driver = null;

	}

}
