package com.mapsync.utilities;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.mapsync.base.BaseTest;

public class PageUtils extends BaseTest {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	
	public static boolean selectDropDown(WebElement webElement, String text) {
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			Select select = new Select(webElement);
			select.selectByVisibleText(text);
			status = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return status;
		}
		return status;
	}

	public static boolean waitForVisible(WebElement webElement, int timeOut) {
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(webElement));
			status = webElement.isDisplayed();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return status;
		}
		return status;
	}

	public static boolean click(WebElement webElement) {
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			webElement.click();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
		return status;
	}

	public static boolean sendKeys(WebElement webElement, String text) {
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			webElement.clear();
			webElement.sendKeys(text);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static void hardAssertion(boolean status, String message) {
		if (status) {
			ExtentReporter.reportStep(driver, message, "Pass", 1);
		} else {
			ExtentReporter.reportStep(driver, message, "Fail", 1);
			Assert.assertTrue(status);
		}
	}

	public static boolean isPresent(WebElement webElement, int timeOut) {
		boolean status = false;
		waitForVisible(webElement, timeOut);
		try {
			status = webElement.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static void pressKeys(String keys) {
		try {
			Actions act = new Actions(driver);
			if(keys.equalsIgnoreCase("tab"))
		    act.sendKeys(Keys.TAB).build().perform();
			else if (keys.equalsIgnoreCase("enter"))
				 act.sendKeys(Keys.RETURN).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public static String readXMLValues(String filePath, String root, String tagName) {
		String value = null;
		try {
			// creating a constructor of file class and parsing an XML file
			File file = new File(filePath);
			// an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// an instance of builder to parse the specified xml file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName(root);
			// nodeList is not iterable, so we are using for loop
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					value = eElement.getElementsByTagName(tagName).item(0).getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static String readData(String rootName, String tagName) {
        String filePath = "";
        filePath = System.getProperty("user.dir") + "//src//main//java//com//mapsync//testdata//TestData_mapsync.xml";
        return readXMLValues(filePath, rootName, tagName);
    }
}
