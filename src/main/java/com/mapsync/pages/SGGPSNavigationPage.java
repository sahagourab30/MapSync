package com.mapsync.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mapsync.base.BaseTest;
import com.mapsync.utilities.ExtentReporter;
import com.mapsync.utilities.PageUtils;

public class SGGPSNavigationPage extends BaseTest {
	
	@SuppressWarnings("static-access")
	public SGGPSNavigationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifySGGPSNavigationPageDisplayed() {
		try {
			PageUtils.switchToWindow(1);
			String expectedURL = PageUtils.readData("URL", "SGGPS");
			String actualURL = PageUtils.getCurrentURL();
			PageUtils.hardAssertion(expectedURL.equalsIgnoreCase(actualURL), "Verify GS GPS Navigation page is displayed");
			ExtentReporter.reportStep(getWebDriver(), "GS GPS Navigation page is displayed successfully", "Pass", 0);
			PageUtils.switchToWindowAndClose(1);
			PageUtils.switchToWindow(0);
		} catch (Exception e) {
			ExtentReporter.reportStep(getWebDriver(), "GS GPS Navigation page is not displayed failed", "Fail", 1);
			ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
		}
	}

}
