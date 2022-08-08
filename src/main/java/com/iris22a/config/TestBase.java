package com.iris22a.config;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.iris22a.keyword.UIKeywords;

import io.cucumber.java.Before;

public class TestBase {
	public static final Logger LOG = Logger.getLogger(TestBase.class);
	/**
	 * this method launches desired browser (Chrome as default browser if not explicitly provided)
	 * before every test case
	 * @param browserName
	 * @throws Exception
	 */
	@Parameters("browserName")
	@BeforeMethod  //COMMENTED FOR CUCUMBER ANNOTATION
	public void setUp(@ Optional String browserName) throws Exception {
		if (browserName==null || browserName.isEmpty()) {
			browserName="Chrome";
			LOG.info("Default browser is chrome");
		}
		
		UIKeywords.openBrowser(browserName);
		LOG.info(browserName + " is launched");
		
		
	}
	/**
	 * this method closes the browser afetr every test case is executed
	 * @throws Exception
	 */
	@AfterMethod
	public void tearDown() throws Exception {
		UIKeywords.quitBrowser();
		
	}
	


}
