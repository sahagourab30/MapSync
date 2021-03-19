package com.mapsync.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mapsync.base.BaseTest;
import com.mapsync.utilities.ExtentReporter;
import com.mapsync.utilities.PageUtils;

public class RegistrationPage extends BaseTest {
	
	@SuppressWarnings("static-access")
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='signup_form']//h5")
	WebElement signUpHeader;
	
	@FindBy(id = "profile_first_name")
	WebElement firstName;
	
	@FindBy(id = "profile_last_name")
	WebElement lastName;
	
	@FindBy (xpath = "//select[@id='profile_country']")
	WebElement country;
	
	@FindBy (id = "profile_address")
	WebElement address;
	
	@FindBy (id = "profile_contact_no")
	WebElement contactNumber;
	
	@FindBy (id = "profile_gender_M")
	WebElement male;
	
	@FindBy (id = "profile_gender_F")
	WebElement female;
	
	@FindBy (id = "profile_dob")
	WebElement dateOfBirth;
	
	@FindBy (id = "profile_email")
	WebElement email;
	
	@FindBy (id = "user_name")
	WebElement userName;
	
	@FindBy (id = "password")
	WebElement password;
	
	@FindBy (id = "identity[password_confirmation]")
	WebElement confirmPassword;
	
	@FindBy (id = "profile_subscribe_to_newsletter")
	WebElement newsletter;
	
	@FindBy (id = "chk_agree")
	WebElement termsAndConditions;
	
	@FindBy (xpath = "//input[@value='Create Profile']")
	WebElement createProfileButton;
	
	@FindBy (xpath = "//div[@class='error_signup']")
	WebElement errorSignUp;

	public void verifyRegistrationPageDisplayed() {
		try {
			PageUtils.waitForVisible(signUpHeader, 10);
			PageUtils.hardAssertion(PageUtils.isPresent(signUpHeader, 5), "Verify Registration page is displayed");
		} catch (Exception e) {
			ExtentReporter.reportStep(getWebDriver(), "Registration page is not displayed failed", "Fail", 1);
			ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
		}
	}
	
	public void updateNewUserData(String regData[]) {
		try {
			PageUtils.waitForVisible(firstName, 5);
			if(PageUtils.isPresent(firstName, 5)) {
				PageUtils.sendKeys(firstName, regData[0]);
				PageUtils.sendKeys(lastName, regData[1]);
				PageUtils.selectDropDown(country, regData[2]);
				PageUtils.sendKeys(address, regData[3]);
				PageUtils.sendKeys(contactNumber, regData[4]);
				if(regData[5].equalsIgnoreCase("Female"))
					PageUtils.click(female);
				PageUtils.sendKeys(dateOfBirth, regData[6]);
				PageUtils.pressKeys("tab");
				PageUtils.sendKeys(email, regData[7]);
				PageUtils.sendKeys(password, regData[8]);
				PageUtils.sendKeys(confirmPassword, regData[8]);
				if(regData[9].equalsIgnoreCase("Yes"))
					PageUtils.click(newsletter);
				PageUtils.click(termsAndConditions);
				ExtentReporter.reportStep(getWebDriver(), "All user data updated", "Info", 1);
			}
			
		}catch (Exception e) {
			ExtentReporter.reportStep(getWebDriver(), "Unable to update new user data", "Fail", 1);
            ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
		}
	}
		
		public void clickOnCreateProfile() {
			try {
				PageUtils.waitForVisible(createProfileButton, 5);
				PageUtils.hardAssertion(PageUtils.click(createProfileButton), "Verify Clicking on Create Profile button");
				ExtentReporter.reportStep(getWebDriver(), "Clicking on Create Profile button passed", "Pass", 0);
			}catch(Exception e) {
				ExtentReporter.reportStep(getWebDriver(), "Cliking on Create Profile failed", "Fail", 1);
	            ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
			}
	}
		public void verifySignUpError() {
			try {
				PageUtils.waitForVisible(errorSignUp, 10);
				PageUtils.hardAssertion(PageUtils.isPresent(errorSignUp, 5), "Verify error on sign up");
				ExtentReporter.reportStep(getWebDriver(), "Verification of error on sign up Passed", "Pass", 0);
			}catch(Exception e) {
				ExtentReporter.reportStep(getWebDriver(), "Verification of error on sign up failed", "Fail", 1);
	            ExtentReporter.reportStep(getWebDriver(), e.getMessage(), "Fail", 0);
			}
		}
		
}
