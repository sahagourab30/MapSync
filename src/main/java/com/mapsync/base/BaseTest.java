package com.mapsync.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.mapsync.utilities.ExtentReporter;
import com.mapsync.utilities.PageUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {

	public static WebDriver driver;
	public static Properties properties;
	public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	public AppiumDriver<WebElement> driverMobile;
	public DesiredCapabilities capabilities = new DesiredCapabilities();
	public static ThreadLocal<AppiumDriver<WebElement>> webMobileDriver=new ThreadLocal<AppiumDriver<WebElement>>();

	public BaseTest() {
		try {
			properties = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com" + "/mapsync/config/config.properties");
			properties.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void initialization() {
		try {
			String browserName = properties.getProperty("browser");
			switch (browserName.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "/Users/715994/Downloads/chromedriver");
				driver = new ChromeDriver();
				BaseTest.webDriver.set(driver);
				break;

			case "firefox":
				System.setProperty("webdriver.gecko.driver", "/Users/715994/Downloads/geckodriver");
				driver = new FirefoxDriver();
				BaseTest.webDriver.set(driver);
				break;
			case "safari":
				driver = new SafariDriver();
				BaseTest.webDriver.set(driver);
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(PageUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(PageUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.get(properties.getProperty("url"));
		} catch (Exception e) {
			Assert.fail("Could not initialize WebDriver", e);
		}
	}
	
	@Parameters({"url", "deviceName", "deviceLocation", "platform"})
	public synchronized void initialization(String url, String deviceName, String deviceLocation, String platform) throws MalformedURLException {
		try {
			setCapabilitiesLocalWeb(deviceName, platform);
			switch (platform.toUpperCase()) {
			case "IOSWEB":
				try
				{
					driverMobile = new IOSDriver<WebElement>(new URL(url), capabilities);
					setWebMobileDriver(driverMobile);
					getMobileWebDriver().get(properties.getProperty("url"));
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case "ANDROIDWEB":
				try
				{
					driverMobile = new AndroidDriver<WebElement>(new URL(url), capabilities);
					setWebMobileDriver(driverMobile);
					getMobileWebDriver().get(properties.getProperty("url"));
				}
				catch(Exception e)
				{
					Assert.fail("Capabilities not set", e);
				}
				break;
			default:
				Assert.fail("Could not identify device platform \"" + platform + "\" from database");
		
			
			}
			
		}catch (Exception e) {
			Assert.fail("Could not initialize Android/IOS WebDriver", e);
		}
	
	}
		
	private void setWebMobileDriver(AppiumDriver<WebElement> driverMobile) {
		BaseTest.webMobileDriver.set(driverMobile);
	}

	private DesiredCapabilities setCapabilitiesLocalWeb(String deviceName, String platform) {
		switch(platform) {
		case "ANDROIDWEB":
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, PageUtils.readData(deviceName, "deviceName"));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PageUtils.readData(deviceName, "platformName"));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PageUtils.readData(deviceName, "platformVersion"));
			capabilities.setCapability(MobileCapabilityType.UDID, PageUtils.readData(deviceName, "UDID"));
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, PageUtils.readData(deviceName, "automationName"));
			capabilities.setCapability(MobileCapabilityType.NO_RESET, PageUtils.readData(deviceName, "noReset"));
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, PageUtils.readData(deviceName, "newCommandTimeout"));
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PageUtils.readData(deviceName, "appPackage"));
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, PageUtils.readData(deviceName, "appActivity"));
			break;
		case "IOSWEB":
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PageUtils.readData(deviceName, "platformName"));
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, PageUtils.readData(deviceName, "deviceName"));
			capabilities.setCapability(MobileCapabilityType.UDID, PageUtils.readData(deviceName, "UDID"));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PageUtils.readData(deviceName, "platformVersion"));
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, PageUtils.readData(deviceName, "automationName"));
			capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, PageUtils.readData(deviceName, "bundleId"));
			capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, PageUtils.readData(deviceName, "xcodeOrgId"));
			capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, PageUtils.readData(deviceName, "xcodeSigningId"));
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, PageUtils.readData(deviceName, "newCommandTimeout"));
			capabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, false);
			break;
		}
		return capabilities;
	}
	
	public static WebDriver getWebDriver() {
		WebDriver dr =null;
		dr=webDriver.get();
		return dr;
	}
	
	public static AppiumDriver<WebElement> getMobileWebDriver() {
		AppiumDriver<WebElement> dr = null;
		dr=webMobileDriver.get();
		return dr;
	}
	
	@AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {
        ExtentReporter.endExtentReport();
    }
}