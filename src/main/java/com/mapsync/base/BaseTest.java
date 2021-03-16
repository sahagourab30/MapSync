package com.mapsync.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.mapsync.utilities.ExtentReporter;
import com.mapsync.utilities.PageUtils;

public class BaseTest {

	public static WebDriver driver;
	public static Properties properties;

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

	public static void initialization() {
		try {
			String browserName = properties.getProperty("browser");
			switch (browserName.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "/Users/715994/Downloads/chromedriver");
				driver = new ChromeDriver();
				break;

			case "firefox":
				System.setProperty("webdriver.gecko.driver", "/Users/715994/Downloads/geckodriver");
				driver = new FirefoxDriver();
				break;
			case "safari":
				driver = new SafariDriver();
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
	
	@AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {
        ExtentReporter.endExtentReport();
    }
}