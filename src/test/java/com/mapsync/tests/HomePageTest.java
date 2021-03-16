package com.mapsync.tests;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mapsync.base.BaseTest;
import com.mapsync.pages.HomePage;
import com.mapsync.pages.RegistrationPage;
import com.mapsync.utilities.ExtentReporter;
import com.mapsync.utilities.PageUtils;

public class HomePageTest extends BaseTest {

	HomePage homepage;
	RegistrationPage registrationPage;

	@BeforeMethod(alwaysRun = true)
	public void initialize(Method method) {
		ExtentReporter.startExtentTestReport((this.getClass().getSimpleName() + "::" + method.getName()),
				"Test Result");
		initialization();
		homepage = new HomePage(driver);
		registrationPage = new RegistrationPage(driver);
	}
	
	@Test(groups = {"Homepage"})
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
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}	
}
