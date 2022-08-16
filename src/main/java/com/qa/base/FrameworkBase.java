package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameworkBase {
	public static WebDriver driver;
	public static Properties prop;
	public static String path = System.getProperty("user.dir");
	public FrameworkBase() throws IOException {

		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\admin\\eclipse-workspace\\OrangeHRM\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void initialization(String browser) {

	//	String browserName = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else
			if (browser.equalsIgnoreCase("FireFox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else 
			if (browser.equalsIgnoreCase("Edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		
		
	}
	public void setUp(String url) {
	
		driver.get(url);
		driver.navigate().refresh();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		
	}

}
