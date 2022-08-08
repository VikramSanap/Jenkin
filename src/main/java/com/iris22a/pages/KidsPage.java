package com.iris22a.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.iris22a.keyword.UIKeywords;

public class KidsPage {

	public KidsPage() {
		PageFactory.initElements(UIKeywords.driver, this);
	}
	
	@FindBy(css = "a[href]")
	public List<WebElement> allLinksOnKidsPage;
	
	public List<String> listOfAllLinksOnKidsPage() {
		List<String> tesxt = UIKeywords.getTexts(allLinksOnKidsPage);
		return tesxt;
		
	}
}

