package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

	@FindBy(css = ".search-crabs-promo__title.search-home-title")
	WebElement homePageLabel;

	@FindBy(css = "#search-input-location")
	WebElement searchLocation;

	@FindBy(css = "#search-submit")
	WebElement seachBtn;

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(homePageLabel);
	}

	public HomePage open(String url) {
		driver.get(url);
		return (HomePage) openPage(HomePage.class);
	}

	public SearchPage searchLocation(String location) {
		fillUtil(searchLocation, location);
		clickUtil(seachBtn);
		return (SearchPage) openPage(SearchPage.class);
	}

}
