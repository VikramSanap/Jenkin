package com.iris22a.config;



import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;

import com.iris22a.keyword.UIKeywords;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseForFeatures {
	public static final Logger LOG = Logger.getLogger(TestBase.class);

//	String browserName = System.getProperty("browserName");//provide browserName from commandline as an arguement
   	@Before
	public void before_setUp() {
//		if (browserName == null || browserName.isEmpty()) {
//			browserName = "Chrome";
//			LOG.info("Default browser is chrome");

		UIKeywords.openBrowser("Chrome");
		LOG.info("Chrome is launched");
   	}
	/**
	 * this method closes the browser afetr every Scenario : is executed
	 * 
	 */
	@After
	public void after_tearDown() {
		UIKeywords.quitBrowser();

	}

}
