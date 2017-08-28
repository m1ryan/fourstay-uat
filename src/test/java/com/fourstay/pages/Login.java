package com.fourstay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fourstay.utilities.Driver;

public class Login {

	public Login() {

		PageFactory.initElements(Driver.getInstance(), this);

	}

	public void logsIn(String email, String password) throws InterruptedException {
		Login login = new Login();
		login.loginButton.click();
		login.passwordEnter.sendKeys(password);
		Thread.sleep(500);
		login.emailEnter.sendKeys(email);
		Thread.sleep(1000);
		login.loginBar.click();
	}

	@FindBy(xpath = "//li[@class='btn menu btn-log zl-margin not-login']")
	public WebElement loginButton;

	@FindBy(xpath = "(//li[@class='btn menu btn-log zl-margin not-login'])[2]")
	public WebElement signUpButton;

	@FindBy(xpath = "//form/div/input[@type='Email']")
	public WebElement emailEnter;

	@FindBy(xpath = "//input[@id='key']")
	public WebElement passwordEnter;

	@FindBy(id = "btn-login")
	public WebElement loginBar;

	@FindBy(css = "span[class='page-btn-transparent list-your-stay']")
	public WebElement listYourStay;

	@FindBy(css = "i[class='fa fa-lg fa-home']")
	public WebElement homeButton;

	@FindBy(css = "button[class='navbar-toggle']")
	public WebElement navBarToggle;

	@FindBy(xpath = "(//li/a[@class='dropdown-toggle'])[2]")
	public WebElement dropDownToggle;

	@FindBy(css = "a[ng-click='btnlogout()']")
	public WebElement logoutButton;

	@FindBy(id = "iLocName")
	public WebElement schoolName;

	@FindBy(id = "rentoutfrom2")
	public WebElement startDate;

	@FindBy(id = "rentoutto2")
	public WebElement endDate;

	@FindBy(id = "search")
	public WebElement search;

}
