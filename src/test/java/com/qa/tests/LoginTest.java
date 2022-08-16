package com.qa.tests;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.qa.base.FrameworkBase;
import com.qa.pages.LoginPage;

public class LoginTest extends FrameworkBase {
	LoginPage login;

	public LoginTest() throws IOException {
		super();

	}

	@Parameters({ "url", "browser" })
	@BeforeClass
	public void setUpMethod(String url, String browser) throws IOException {
		initialization(browser);
		setUp(url);
		login = new LoginPage();

	}

	// @Test(priority = 0)
	public void headingValidationTest1() {
		Boolean flag = login.headingValidation1();
		Assert.assertTrue(flag, "Heading is present on page");
	}

	// @Test(priority = 1)
	public void headingValidationTest2() {

		String heading = login.headingValidation2();
		Assert.assertEquals(heading, "LOGIN Panel", "Heading is matched");

	}

	// @Test(priority = 3)
	public void titleValidationTest() {
		String title1 = login.titleValidation();
		String title2 = "OrangeHRM";
		Assert.assertEquals(title1, title2);
	}

	@Parameters({ "username", "password" })
	@Test
	public void loginTest(String username, String password) throws InterruptedException {
		login.LoginMethod(username, password);
		login.loginClick();
		WebElement dashboard = driver
				.findElement(By.xpath("//li[@class=\"current main-menu-first-level-list-item\"]//b"));
		if (dashboard.isDisplayed()) {
			Assert.assertEquals(true, true, "Login Successful");

		} else
			Assert.assertEquals(true, false, "Login not successful");

	}

}
