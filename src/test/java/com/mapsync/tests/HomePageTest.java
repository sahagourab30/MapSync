package com.mapsync.tests;

import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.mapsync.base.BaseTest;
import com.mapsync.pages.GalactioPage;
import com.mapsync.pages.HomePage;
import com.mapsync.pages.MobileAppPage;
import com.mapsync.pages.RegistrationPage;
import com.mapsync.pages.SGGPSNavigationPage;
import com.mapsync.pages.SignInPage;
import com.mapsync.utilities.ExtentReporter;
import com.mapsync.utilities.PageUtils;

public class HomePageTest extends BaseTest {

	HomePage homepage;
	RegistrationPage registrationPage;
	MobileAppPage mobileAppPage;
	SignInPage signInPage;
	GalactioPage galactioPage;
	SGGPSNavigationPage sgGPSNavigationPage;

	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) {
		ExtentReporter.startExtentTestReport((this.getClass().getSimpleName() + "::" + method.getName()),
				"Test Result");
		initialization();
		homepage = new HomePage(getWebDriver());
		registrationPage = new RegistrationPage(getWebDriver());
		signInPage = new SignInPage(getWebDriver());
		mobileAppPage = new MobileAppPage(getWebDriver());
		galactioPage = new GalactioPage(getWebDriver());
		sgGPSNavigationPage = new SGGPSNavigationPage(getWebDriver());
	}
	
	@Test(groups = {"Homepage1"})
	public void registerLinkTest() {
		String regData [] = {PageUtils.readData("Registration", "FName"),
				PageUtils.readData("Registration", "LName"),
				PageUtils.readData("Registration", "Country"),
				PageUtils.readData("Registration", "Address"),
				PageUtils.readData("Registration", "Contact"),
				PageUtils.readData("Registration", "Gender"),
				PageUtils.readData("Registration", "DOB"),
				PageUtils.readData("Registration", "Email"),
				PageUtils.readData("Registration", "Pwd"),
				PageUtils.readData("Registration", "Newsletter") };
		homepage.clickOnRegister();
		registrationPage.verifyRegistrationPageDisplayed();
		registrationPage.updateNewUserData(regData);
		registrationPage.clickOnCreateProfile();
		registrationPage.verifySignUpError();
	}
	
	@Test(groups = {"Homepage"})
	public void verifyAllLink() {
		homepage.clickOnRegister();
		registrationPage.verifyRegistrationPageDisplayed();
		PageUtils.navigateBack("Navigate back to Homepage");
		homepage.clickOnMobileApp();
		mobileAppPage.verifyMobileAppPageDisplayed();
		homepage.clickOnSignIn();
		signInPage.verifySignInPageDisplayed();
		PageUtils.navigateBack("Navigate back to Homepage");
		homepage.clickOnGalactio();
		galactioPage.verifyGalactioDisplayed();
		homepage.clickOnSGGPSNavigation();
		sgGPSNavigationPage.verifySGGPSNavigationPageDisplayed();
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		getWebDriver().quit();
	}	
}
