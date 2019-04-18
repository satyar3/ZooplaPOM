package com.qa.utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	
	protected WebDriver driver;
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return dr.get();
	}

	public static void setDriver(WebDriver driver) {
		dr.set(driver);
	}
}
