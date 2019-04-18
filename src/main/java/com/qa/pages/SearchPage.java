package com.qa.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage{
	
	@FindBy(css = ".fright.btn.btn-select-dropdown.search-and-refine-filters-toggle.js-search-and-refine-filters-toggle")
	WebElement filterBtn;
	
	@FindBy(css = ".listing-results-right .listing-results-price")
	List<WebElement> priceList;
	
	List<Integer> propertyPrices = new ArrayList<Integer>();

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(filterBtn);
	}
	
	public String getCurrentPageTitle()
	{
		return driver.getTitle();
	}
	
	public void displayAllPriceDescending()
	{
		int temp;
		for(WebElement el: priceList)
		{
			try {
				String tempPrice = el.getText().replaceAll("[^0-9]", "");
				temp = Integer.parseInt(tempPrice);
			}
			catch(NumberFormatException n)
			{
				temp = 0;
			}
			propertyPrices.add(temp);
			//log.info("Price of the property is : "+temp);
		}
		
		Collections.sort(propertyPrices, Collections.reverseOrder());
		
		for(int p: propertyPrices)
		{
			log.info("Price of the property is : "+p);
		}
	}
}
