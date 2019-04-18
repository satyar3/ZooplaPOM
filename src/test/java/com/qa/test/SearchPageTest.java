package com.qa.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.pages.HomePage;
import com.qa.pages.SearchPage;
import com.qa.testbase.TestBase;

public class SearchPageTest extends TestBase {

	HomePage homePage;
	SearchPage searchPage;

	@BeforeTest
	public void setUp() {

		homePage = new HomePage().open("https://www.zoopla.co.uk/");
		searchPage = homePage.searchLocation("London");

	}

	@Test
	public void getAllPropertyPrice() {
		
		searchPage.displayAllPriceDescending();

	}

}
