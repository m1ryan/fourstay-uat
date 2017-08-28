package com.fourstay.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fourstay.utilities.Driver;

public class GeneralAccountInfoPage {
	
	WebDriver driver;
	
	public GeneralAccountInfoPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	@FindBy(css = "input[ng-model='$parent.other.FirstName']")
	public WebElement firstNameBox;
	
	@FindBy(css = "input[ng-model='$parent.other.LastName']")
	public WebElement lastNameBox;
	
	@FindBy(css = "input[ng-model='$parent.profile.general.phone']")
	public WebElement phoneNumberBox;

}
