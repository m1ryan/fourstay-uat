package com.fourstay.step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fourstay.utilities.Config;
import com.fourstay.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageStepDefs {

	WebDriver driver;

	@Given("^the user is on fourstay home page$")
	public void userIsOnTheHomepage() throws Throwable {
		System.out.println("The user is on the homepage");
		driver = Driver.getInstance();
		driver.get(Config.getProperty("url"));
	}

	@When("^the user clicks on the login link$")
	public void userClicksOnLoginLink() throws Throwable {
		System.out.println("User is clicking on the login link");
		driver.findElement(By.cssSelector(".not-login")).click();
		;
	}

	@Then("^the email field should be displayed$")
	public void emailShouldBeDisplayed() throws Throwable {
		System.out.println("Email field is now displayed");
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
	}

}
