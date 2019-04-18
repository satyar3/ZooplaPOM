package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.pages.HomePage;
import com.qa.pages.SearchPage;
import com.qa.testbase.TestBase;

public class HomePageTest extends TestBase {

	HomePage homePage;

	@BeforeTest
	public void setUp() {

		homePage = new HomePage().open("https://www.zoopla.co.uk/");
		// searchPage = new SearchPage();
	}

	@Test
	public void propertySearchTest() {

		SearchPage searchPage = homePage.searchLocation("London");

		Assert.assertEquals(searchPage.getCurrentPageTitle(), "Property for Sale in London - Buy Properties in London - Zoopla");
		
	}

}
