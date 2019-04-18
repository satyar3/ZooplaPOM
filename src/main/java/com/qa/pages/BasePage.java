package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.testbase.TestBase;
import com.qa.utils.DriverManager;

public abstract class BasePage<T> extends TestBase {

	WebDriver driver;

	protected BasePage() {
		this.driver = DriverManager.getDriver();
	}

	protected abstract ExpectedCondition getPageLoadCondition();

	private void waitForPageToLoad(ExpectedCondition pageloadCondition) {
		WebDriverWait wait = new WebDriverWait(driver, webDriverWaitTime);
		wait.until(pageloadCondition);
	}

	public T openPage(Class<T> className) {

		T page = null;
		AjaxElementLocatorFactory ajaxElemLocatorFactory = new AjaxElementLocatorFactory(driver, 10);
		page = PageFactory.initElements(driver, className);
		PageFactory.initElements(ajaxElemLocatorFactory, page);
		ExpectedCondition pageloadCondition = ((BasePage) page).getPageLoadCondition();
		waitForPageToLoad(pageloadCondition);

		return page;
	}

	public static void clickUtil(WebElement el) {
		
		log.info("Clicked on : "+el);
		el.click();
	}

	public static void fillUtil(WebElement el, String detailsToFill) {
		
		el.sendKeys(detailsToFill);
		log.info("Filled details : "+detailsToFill);
	}

}
