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

public class HomePage extends BaseTest {
	
	@SuppressWarnings("static-access")
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public HomePage(AppiumDriver<WebElement> driverWeb) {
		this.driverMobile = driverWeb;
		PageFactory.initElements(new AppiumFieldDecorator(driverWeb), this);
	}

	@FindBy(xpath = "//div[@class='account_bar']/a[@href='http://account.mapsynq.com/profiles/new']")
	WebElement register;

	@FindBy(linkText = "Register")
	WebElement registerLinkText;
	
	@FindBy(xpath = "(//a[@href='/oauth/sign_in'])[1]")
	WebElement signIn;
	
	@FindBy(linkText = "Mobile App")
	WebElement mobileApp;
	
	@FindBy(linkText = "Galactio")
	WebElement galactio;
	
	@FindBy(linkText = "SG GPS Navigation")
	WebElement sgGPSNavigation;
	
	@FindBy(xpath = "//a[contains(@class, 'tab_button live_tab sprite')]")
	WebElement liveButton;
	
	@FindBy(xpath = "//a[contains(@class, 'tab_button me_tab sprite')]")
	WebElement personalButton;
	
	@FindBy(xpath = "//a[contains(@class, 'tab_button directions_tab sprite')]")
	WebElement directionsButton;
	
	@FindBy(xpath = "//input[contains(@value,'Sign in')]")
	WebElement personalSignIn;
	
	@FindBy(xpath = "//input[contains(@value,'Get Directions')]")
	WebElement directionsGetDirections;
	
	@FindBy(xpath = "//label[contains(@for,'rdIncidents')]")
	WebElement incidents;
	
	@FindBy(xpath = "//label[contains(@for,'rdCameras')]")
	WebElement cameras;
	
	@FindBy(xpath = "//label[contains(@for,'rdErp')]")
	WebElement tolls;
	
	@FindBy(id = "txtSearchIncidentsingapore")
	WebElement searchIncidents;
	
	@FindBy(id = "txtSearchCamerasingapore")
	WebElement searchCameras;
	
	@FindBy(id = "txtSearchERPsingapore")
	WebElement searchtolls;
	
	@FindBy (id = "selIncidentDays")
	WebElement incidentDates;
	
	@FindBy (xpath = "//div[@class='sprite side_panel_bar_toggle side_panel_bar_collapse']")
	WebElement sidePanelCollapse;
	
	@FindBy (xpath = "//div[@class='sprite side_panel_bar_toggle side_panel_bar_expand']")
	WebElement sidePanelExpand;
	
	@FindBy (xpath = "//a[@class='header_logo sprite']")
	WebElement logoHomepage;
	
	@FindBy (id="txtGlobalSearch")
	WebElement globalSearch;
	
	@FindBy (xpath = "//span[@class='search_icon sprite']")
	WebElement searchButton;

	public void clickOnRegister() {
		try {
			PageUtils.waitForVisible(registerLinkText, 10);
			PageUtils.click(registerLinkText);
			ExtentReporter.reportStep(getWebDriver(), "Clicked on Register", "Pass", 1);
		} catch (Exception e) {
			ExtentReporter.reportStep(getWebDriver(), "Click on 'Registration' failed", "Fail", 1);
			ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
		}
	}
	
	public void clickOnSignIn() {
		try {
			PageUtils.waitForVisible(signIn, 10);
			PageUtils.click(signIn);
			ExtentReporter.reportStep(getWebDriver(), "Clicked on Sign In", "Pass", 1);
		} catch (Exception e) {
			ExtentReporter.reportStep(getWebDriver(), "Click on 'Sign In' failed", "Fail", 1);
			ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
		}
	}
	
	public void clickOnSignInMobile() {
		try {
			PageUtils.clickMobile(signIn);
			ExtentReporter.reportStep(getMobileWebDriver(), "Clicked on Sign In", "Pass", 1);
		} catch (Exception e) {
			ExtentReporter.reportStep(getMobileWebDriver(), "Click on 'Sign In' failed", "Fail", 1);
			ExtentReporter.reportStep(getMobileWebDriver(), e.getMessage(), "Fail", 0);
		}
	}
	
	public void clickOnSGGPSNavigation() {
		try {
			PageUtils.waitForVisible(sgGPSNavigation, 10);
			PageUtils.click(sgGPSNavigation);
			ExtentReporter.reportStep(getWebDriver(), "Clicked on SG GPS Navigation", "Pass", 1);
		} catch (Exception e) {
			ExtentReporter.reportStep(getWebDriver(), "Click on 'SG GPS Navigation' failed", "Fail", 1);
			ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
		}
	}
	
	public void clickOnGalactio() {
		try {
			PageUtils.waitForVisible(galactio, 10);
			PageUtils.click(galactio);
			ExtentReporter.reportStep(getWebDriver(), "Clicked on Galactio", "Pass", 1);
		} catch (Exception e) {
			ExtentReporter.reportStep(getWebDriver(), "Click on 'Galactio' failed", "Fail", 1);
			ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
		}
	}
	
	public void clickOnMobileApp() {
		try {
			PageUtils.waitForVisible(mobileApp, 10);
			PageUtils.click(mobileApp);
			ExtentReporter.reportStep(getWebDriver(), "Clicked on Mobile App", "Pass", 1);
		} catch (Exception e) {
			ExtentReporter.reportStep(getWebDriver(), "Click on 'Mobile App' failed", "Fail", 1);
			ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
		}
	}

}
