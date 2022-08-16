package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameworkBase1 {

	public static WebDriver driver = null;


	public static void browserLaunch(String Browser) {

		if (Browser.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("Firefox"))

		{

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		driver.navigate().refresh();
		driver.manage().window().maximize();

		}

}




/*
 * BY SET PROPERTY METHOD
 * 
 * 
 * public static void browserLaunch() {
 * 
 * System.setProperty("webdriver.chrome.driver",
 * "F:\\Inspire NOTES\\InspireJAVA\\ChromeBrowser\\chromedriver.exe"); WebDriver
 * driver = new ChromeDriver();
 * 
 * 
 * WebDriverManager.chromedriver().setup(); WebDriver driver = new
 * ChromeDriver(); driver.manage().window().maximize();
 * driver.get("https://www.google.com");
 * 
 * }
 */
