package com.mapsync.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mapsync.base.BaseTest;
import com.mapsync.utilities.ExtentReporter;
import com.mapsync.utilities.PageUtils;

public class HomePage extends BaseTest {
	
	@SuppressWarnings("static-access")
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='account_bar']/a[@href='http://account.mapsynq.com/profiles/new']")
	WebElement register;

	@FindBy(linkText = "Register")
	WebElement registerLinkText;

	public void clickOnRegister() {
		try {
			PageUtils.waitForVisible(registerLinkText, 10);
			PageUtils.click(registerLinkText);
			ExtentReporter.reportStep(driver, "Clicked on Register", "Pass", 1);
		} catch (Exception e) {
			ExtentReporter.reportStep(driver, "Click on 'Registration' failed", "Fail", 1);
			ExtentReporter.reportStep(driver, e.getMessage(), "Fail", 0);
		}
	}

}
