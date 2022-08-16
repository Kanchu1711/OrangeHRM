package com.qa.pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.base.FrameworkBase;

public class LoginPage extends FrameworkBase {

	// Object Repository

	@FindBy(id = "logInPanelHeading")
	WebElement heading;

	@FindBy(xpath = "//div[@id=\"divLogo\"]/img")
	WebElement img;

	@FindBy(id = "txtUsername")
	WebElement uname;

	@FindBy(id = "txtPassword")
	WebElement pass;

	@FindBy(id = "btnLogin")
	WebElement loginBtn;

	@FindBy(xpath = "//div[@id=\"forgotPasswordLink\"]/a")
	WebElement forgotPass;

	public LoginPage() throws IOException {

		PageFactory.initElements(driver, this);
	}

	public String titleValidation() {
		String title = driver.getTitle();
		return title;

	}

	public Boolean headingValidation1() {
		boolean flag = driver.findElement(By.id("logInPanelHeading")).isDisplayed();
		return flag;

	}

	public String headingValidation2() {

		String heading = driver.findElement(By.id("logInPanelHeading")).getText();
		return heading;

	}

	public DashboardPage LoginMethod(String username, String password) throws InterruptedException {

		uname.sendKeys(username);

		pass.sendKeys(password);
		
		return new DashboardPage();

	}

	public void loginClick() {
		WebElement loginBtn = driver.findElement(By.xpath("//input[@class=\"button\"]"));
		loginBtn.click();
		System.out.println("Sucessfully logged in");

	}
}
