package com.mapsync.tests;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mapsync.base.BaseTest;
import com.mapsync.pages.HomePage;
import com.mapsync.pages.SignInPage;
import com.mapsync.utilities.ExtentReporter;

public class SignInPageTest extends BaseTest {
	
	HomePage homepage;
	SignInPage signInPage;

	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) {
		ExtentReporter.startExtentTestReport((this.getClass().getSimpleName() + "::" + method.getName()),
				"Test Result");
		homepage = new HomePage(driverMobile);
		signInPage = new SignInPage(driverMobile);
	}
	
	@Test(groups = {"SignInPage"})
	public void loginTest() {
		homepage.clickOnSignInMobile();
		signInPage.verifyLogin();
	}

}
