package com.fourstay.step_definitions;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fourstay.pages.AccountPage;
import com.fourstay.pages.Login;
import com.fourstay.utilities.Config;
import com.fourstay.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserLoginStepDefs {

	Login loginPage;
	WebDriverWait wait;

	@Given("^I am on the fourstay homepage$")
	public void goToHomepage() throws Throwable {
		// Navigate to fourstay homepage
		Driver.getInstance().get(Config.getProperty("url"));
		loginPage = new Login();

	}

	@When("^I login as a host$")
	public void loginAsHost() throws Throwable {
		loginPage.logsIn(Config.getProperty("host.username"), Config.getProperty("host.password"));

	}

	@Then("^I should be able verify I am logged in$")
	public void verifyLogin() throws Throwable {
		AccountPage accountPage = new AccountPage();
		wait = new WebDriverWait(Driver.getInstance(), 10);

		wait.until(ExpectedConditions.visibilityOf(accountPage.accountHolder));

		Assert.assertTrue(Driver.getInstance().getCurrentUrl().contains("dashboard"));

	}

	@When("^I login as a guest$")
	public void loginAsGuest() throws Throwable {
		loginPage.logsIn(Config.getProperty("guest.username"), Config.getProperty("guest.password"));
		loginPage.loginBar.click();

	}

}
