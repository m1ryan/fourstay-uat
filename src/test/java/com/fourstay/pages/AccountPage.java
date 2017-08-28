package com.fourstay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fourstay.utilities.Driver;

public class AccountPage {

	public AccountPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@FindBy(css = ".user-name")
	public WebElement accountHolder;

	@FindBy(css = ".hstay-logo")
	public WebElement homeLogo;
	
	@FindBy(xpath = "//li[@class='nav-gravicon dropdown']")
	public WebElement accountPic;
	
	@FindBy(linkText = "Edit Profile")
	public WebElement editProfile;

}
