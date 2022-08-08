package com.iris22a.keyword;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This is a Singleton Class and to create object of this class use
 * {@code getInstance} method This class includes the generic keywords which
 * will be used to perform various actions on WebElements
 * 
 * @author Vikram Ashok Sanap
 */
public class UIKeywords {
	public static final Logger LOG = Logger.getLogger(UIKeywords.class);

	public static RemoteWebDriver driver;

	/**
	 * This keyword is used to launch a specified browser
	 * 
	 * @Guide webDriver name must be one of the following:
	 *        <ul>
	 *        <li>Chrome</li>
	 *        <li>Internet Explorer</li>
	 *        <li>Firefox</li>
	 *        </ul>
	 * @author VikramSanap
	 */
	public static void openBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Internet Explorer")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}

	}

	/**
	 * this keyword is used to launch an Url of a website
	 * 
	 * @param url
	 * @author VikramSanap
	 */
	public static void launchUrl(String url) {
		driver.get(url);
		LOG.info(url + " has been launched ");

	}

	/**
	 * This keyword is used to get an url of a present web page
	 * 
	 * @return URL as a {@code String}.
	 * @author Vikram Ashok Sanap
	 */
	public static String getCurrentUrl() {
		String s = driver.getCurrentUrl();
		return s;
	}

	/**
	 * This keyword is used to get the title of a present web page
	 * 
	 * @return title of the web page as String
	 * @author Vikram Ashok Sanap
	 */
	public static String getTitleOfThePage() {
		return driver.getTitle();
	}

	public static void switchWindow(String bytitle) {
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (driver.switchTo().window(window).getTitle().equals(bytitle)) {
				LOG.info("switched on to a window " + bytitle);
				break;
			}
		}

	}

	public static void hitButton(int keyvalue) {

		Robot robo;
		try {
			robo = new Robot();
			robo.keyPress(keyvalue);
			robo.keyRelease(keyvalue);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info(keyvalue + " button has been pressed");
	}

	public static List<String> getTexts(By element) {
		List<WebElement> elements = driver.findElements(element);
		List<String> texts = new ArrayList<String>();
		for (WebElement elemnt : elements) {
			texts.add(elemnt.getText());
		}
		return texts;

	}

	public static void mouseMove(By xpath) {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(xpath)).build().perform();
		;

	}

	public static void click(By xpath) {
		driver.findElement(xpath).click();
	}

	public static void click(String locatortype, String locatorvalue) {
		getWebElement(locatortype, locatorvalue).click();
	}

	/**
	 * This keyword is created to click on any WebElement.pass the webelement as an
	 * arguement to this method and the particular web elementwill be clicked
	 * 
	 * @param element
	 * @author Vikram Ashok Sanap
	 */
	public static void click(WebElement element) {
		element.click();
	}

	/**
	 * This keyword is created click on any WebElement whose locator strategy and
	 * loctor value is stored in an Object Repository. it takes string arguement
	 * which contains locator strategy and locator value which will inturn be passed
	 * as an arguement to the {@code getWebElement} method which will get a
	 * particular element and will be clicked
	 * 
	 * @param Object
	 * 
	 * @author Vikram Ashok Sanap
	 */
	public static void click(String object) {
		String[] parts = object.split("##");
		getWebElement(parts[0], parts[1]).click();
	}

	/**
	 * This keyword is used to find a web elemwnt with the help of various locator
	 * strategies by providing locator type and locator value as an argurments
	 * 
	 * @param locatortype
	 * @param locatorvalue
	 * @return {@code WebElement}
	 * @author Vikram Ashok Sanap
	 */
	public static WebElement getWebElement(String locatortype, String locatorvalue) {
		WebElement element = null;
		if (locatortype.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("linkText")) {
			element = driver.findElement(By.linkText(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("partialLinkText")) {
			element = driver.findElement(By.partialLinkText(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("className")) {
			element = driver.findElement(By.className(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("tagName")) {
			element = driver.findElement(By.tagName(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("name")) {
			element = driver.findElement(By.name(locatorvalue));

		}
		LOG.info("Desired webelement has been found using" + locatortype + " as a locator strategy");
		return element;
	}

	/**
	 * This keyword is used to find a web elemwnt with the help of various locator
	 * strategies by providing locator type and locator value as an argurments
	 * 
	 * @param locatortype
	 * @param locatorvalue
	 * @return {@code List<WebElement>}
	 * @author Vikram Ashok Sanap
	 */
	public static List<WebElement> getWebElements(String locatortype, String locatorvalue) {
		List<WebElement> elements = new ArrayList<WebElement>();
		if (locatortype.equalsIgnoreCase("xpath")) {
			elements = driver.findElements(By.xpath(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("id")) {
			elements = driver.findElements(By.id(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("cssSelector")) {
			elements = driver.findElements(By.cssSelector(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("linkText")) {
			elements = driver.findElements(By.linkText(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("partialLinkText")) {
			elements = driver.findElements(By.partialLinkText(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("className")) {
			elements = driver.findElements(By.className(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("tagName")) {
			elements = driver.findElements(By.tagName(locatorvalue));
		} else if (locatortype.equalsIgnoreCase("name")) {
			elements = driver.findElements(By.name(locatorvalue));

		}
		LOG.info("Desired list of webelements have been found using" + locatortype + " as a locator strategy");
		return elements;
	}

	public static List<String> getTexts(String locatortype, String locatorvalue) {
		List<WebElement> elements = getWebElements(locatortype, locatorvalue);
		List<String> texts = new ArrayList<String>();
		for (WebElement elemnt : elements) {
			texts.add(elemnt.getText());
		}
		return texts;
	}

	/**
	 * This keyword is used to enter the text to a web element by providing web
	 * element and the text to enter as an arguement
	 * 
	 * @param element
	 * @param textToEnter
	 * @author Vikram Ashok Sanap
	 */
	public static void enterText(WebElement element, String textToEnter) {
		element.sendKeys(textToEnter);
	}

	/**
	 * This keyword is used to enter the text to a web element by providing By class
	 * instance and the text to enter as an arguement
	 * 
	 * @param by
	 * @param textToEnter
	 * @author Vikram Ashok Sanap
	 */
	public static void enterText(By by, String textToEnter) {
		driver.findElement(by).sendKeys(textToEnter);

	}

	/**
	 * This keyword is used to enter a text in text box {@code WebElement}. it takes
	 * two {@code String} arguements first string arguement which contains locator
	 * strategy and locator value which will inturn be passed as an arguement to the
	 * {@code getWebElement} method which returs an {@code WebElement} and second
	 * {@code String} arguement which is the text to be entered particular element
	 * and
	 * 
	 * @param object
	 * @param textToEnter
	 * @author Vikram Ashok Sanap
	 */
	public static void enterText(String[] object, String textToEnter) {
		getWebElement(object[0], object[1]).sendKeys(textToEnter);

	}

	/**
	 * @deprecated this method is depricated and will be deleted in the next version
	 *             of the framework instead you can use
	 *             {@code enterText(String object, String textToEnter)}
	 * @param locatorType
	 * 
	 * @param locatorValue
	 * @param textToenter
	 * @author Vikram Ashok Sanap
	 */
	public static void enterText(String locatortype, String locatorvalue, String textToEnter) {
		getWebElement(locatortype, locatorvalue).sendKeys(textToEnter);
	}

	/**
	 * This keyword is created to hover a mouse ponter on a particular webelement
	 * 
	 * @param element
	 */
	public static void mouseMove(WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();

	}

	/**
	 * * This keyword is created to hover a mouse ponter on a particular webelement
	 * whos {@code locatorType} and {@code locatorValue} is known It is the
	 * overloaded version of
	 * {@code mouseMove(String locatortype, String locatorvalue)}
	 * 
	 * @param locatortype
	 * @param locatorvalue
	 */
	public static void mouseMove(String locatortype, String locatorvalue) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(locatortype, locatorvalue)).perform();

	}

	/**
	 * this keyword is used to get the driver instance
	 * 
	 * @return RemoteWebDriver
	 * @author Vikram Ashok Sanap
	 */
	public static RemoteWebDriver getDriver() {
		return driver;
	}

	/**
	 * This keyword is used to switch from one frame to another when frame Id or
	 * Name is available
	 * 
	 * @param idOrName
	 * @author Vikrama Ashok Sanap
	 */
	public static void switchToFrame(String idOrName) {
		driver.switchTo().frame(idOrName);
	}

	/**
	 * This keyword is used to switch from one frame to another when frame
	 * {@code Index} is available
	 * 
	 * @param index
	 * @author Vikrama Ashok Sanap
	 */
	public static void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This keyword is used to switch from one frame to another when a particular
	 * unique {@code WebElement} of destination frame is available
	 * 
	 * @param element
	 * @author Vikrama Ashok Sanap
	 */
	public static void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This keyword is used to switch to a parent frame from switched frame
	 * 
	 * @author Vikrama Ashok Sanap
	 */
	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	/**
	 * This keyword is used to quit the browser window/tab
	 * 
	 * @author Vikram Ashok Sanap
	 */
	public static void quitBrowser() {
		driver.quit();
	}

	/**
	 * This keyword is used to select a desired option from the dropdown list
	 * creaeted using a select tag anfd whose locator value and locator type is
	 * avialable. the dropdown option is selected using {@code selectByValue()} or
	 * {@code selectByVisibleText()} orv{@code selectByIndex()} method
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @param selectBy
	 * @param value
	 * @author Vikram Ashok Sanap
	 */
	public static void selectDropDownOption(String locatorType, String locatorValue, String selectBy, String value) {
		Select select = new Select(getWebElement(locatorType, locatorValue));
		switch (selectBy) {
		case "index":
			select.selectByIndex(Integer.parseInt(value));
			break;
		case "value":
			select.selectByValue(value);
			break;
		case "visibleText":
			select.selectByVisibleText(value);
			break;
		}
	}

	/**
	 * This keyword is used to select a desired option from the dropdown list
	 * creaeted using a select tag and whose WebElement is avialable. the dropdown
	 * option is selected using {@code selectByValue()} or
	 * {@code selectByVisibleText()} orv{@code selectByIndex()} method
	 * 
	 * @param element
	 * @param selectBy
	 * @param value
	 * @author Vikram Ashok Sanap
	 */
	public static void selectDropDownOption(WebElement element, String selectBy, String value) {
		Select select = new Select(element);
		switch (selectBy) {
		case "index":
			select.selectByIndex(Integer.parseInt(value));
			break;
		case "value":
			select.selectByValue(value);
			break;
		case "visibleText":
			select.selectByVisibleText(value);
			break;
		}
	}

	/**
	 * @deprecated This keyword is used to select a desired option from the dropdown
	 *             list creaeted using a select tag using
	 *             {@code selectByVisibleText()} method
	 * @param locatorType
	 * @param locatorValue
	 * @param option
	 * @author Vikram Ashok Sanap
	 */
	public static void selectByVisibleText(String locatorType, String locatorValue, String option) {
		Select select = new Select(getWebElement(locatorType, locatorValue));
		select.selectByVisibleText(option);
	}

	/**
	 * @deprecated This keyword is used to select a desired option from the dropdown
	 *             list creaeted using a select tag using {@code selectByIndex()}
	 *             method
	 * @param locatorType
	 * @param locatorValue
	 * @param index
	 * @author Vikram Ashok Sanap
	 */
	public static void selectByIndex(String locatorType, String locatorValue, int index) {
		Select select = new Select(getWebElement(locatorType, locatorValue));
		select.selectByIndex(index);
	}

	/**
	 * @deprecated This keyword is used to select a desired option from the dropdown
	 *             list creaeted using a select tag using {@code selectByValue()}
	 *             method
	 * @param locatorType
	 * @param locatorValue
	 * @param value
	 * @author Vikram Ashok Sanap
	 */
	public static void selectByValue(String locatorType, String locatorValue, String value) {
		Select select = new Select(getWebElement(locatorType, locatorValue));
		select.selectByValue(value);
	}

	public static List<String> getTexts(List<WebElement> element) {
		List<String> texts = new ArrayList<String>();
		for (WebElement ele : element) {
			texts.add(ele.getText());
		}
		return texts;

	}

}
