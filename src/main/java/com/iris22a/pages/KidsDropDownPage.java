package com.iris22a.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.iris22a.keyword.UIKeywords;

public class KidsDropDownPage {
	public static final Logger LOG = Logger.getLogger(KidsDropDownPage.class);

	public KidsDropDownPage() {
		PageFactory.initElements(UIKeywords.driver, this);
	}

	@FindBy(css = "div[data-reactid=\"334\"]")
	public WebElement kidsTab;
	public void hoverOnKidsTab() {
		UIKeywords.mouseMove(kidsTab);
	}
	public void clickOnKidsTab() {
		UIKeywords.click(kidsTab);
	}

	@FindBy(css = "li[data-reactid=\"340\"] li")
	public List<WebElement> boysClothing;
	public void listOfBoysClothing() {
		UIKeywords.getTexts(boysClothing);
	}
	
 	
}
