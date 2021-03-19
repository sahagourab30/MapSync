package com.mapsync.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mapsync.base.BaseTest;
import com.mapsync.utilities.ExtentReporter;
import com.mapsync.utilities.PageUtils;

public class GalactioPage extends BaseTest {
	
	@SuppressWarnings("static-access")
	public GalactioPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyGalactioDisplayed() {
		try {
			PageUtils.switchToWindow(1);
			String expectedURL = PageUtils.readData("URL", "Galactio");
			String actualURL = PageUtils.getCurrentURL();
			PageUtils.hardAssertion(expectedURL.equalsIgnoreCase(actualURL), "Verify Galactio page is displayed");
			ExtentReporter.reportStep(getWebDriver(), "Galactio page is displayed successfully", "Pass", 0);
			PageUtils.switchToWindowAndClose(1);
			PageUtils.switchToWindow(0);
		} catch (Exception e) {
			ExtentReporter.reportStep(getWebDriver(), "Galactio page is not displayed failed", "Fail", 1);
			ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
		}
	}

}
