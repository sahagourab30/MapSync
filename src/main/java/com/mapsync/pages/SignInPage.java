package com.mapsync.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mapsync.base.BaseTest;
import com.mapsync.utilities.ExtentReporter;
import com.mapsync.utilities.PageUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignInPage extends BaseTest {
	
	@SuppressWarnings("static-access")
	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public SignInPage(AppiumDriver<WebElement> driverWeb) {
		this.driverMobile = driverWeb;
		PageFactory.initElements(new AppiumFieldDecorator(driverWeb), this);
	}
	
	@FindBy(xpath = "//table[@class='padded']/tbody/tr/td/b")
	WebElement signInText;
	
	@FindBy (id = "name")
	WebElement username;
	
	@FindBy (id ="password")
	WebElement password;
	
	@FindBy (xpath = "//input[@value='Sign In']")
	WebElement signInButton;
	
	public void verifySignInPageDisplayed() {
		try {
			PageUtils.waitForVisible(signInText, 10);
			PageUtils.hardAssertion(PageUtils.isPresent(signInText, 5), "Verify Sign In page is displayed");
		} catch (Exception e) {
			ExtentReporter.reportStep(getWebDriver(), "Sign In page is not displayed failed", "Fail", 1);
			ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
		}
	}
	
	public void verifyLogin() {
		try {
			PageUtils.sendKeysMobile(username, PageUtils.readData("Credentials", "EmailId"));
			PageUtils.sendKeysMobile(password, PageUtils.readData("Credentials", "Password"));
			PageUtils.clickMobile(signInButton);
		}catch (Exception e) {
			ExtentReporter.reportStep(getMobileWebDriver(), "Log in failed", "Fail", 1);
			ExtentReporter.reportStep(getMobileWebDriver(), e.getMessage(), "Fail", 0);
		}
	}

}
