package com.fourstay.step_definitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fourstay.pages.AccountPage;
import com.fourstay.pages.GeneralAccountInfoPage;
import com.fourstay.pages.Login;
import com.fourstay.utilities.Config;
import com.fourstay.utilities.DBUtil;
import com.fourstay.utilities.DBUtil.DBType;
import com.fourstay.utilities.Driver;
import com.fourstay.utilities.Utility;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomeworkStepDefs {
	

	WebDriver driver = Driver.getInstance();
	Login login;
	List<WebElement> searchList1;
	List<String[]> resultsList;
	String email;
	String password;
	WebDriverWait wait;

	AccountPage accountPage;
	
	@Given("^I am on the fourstay login dialog$")
	public void i_am_on_the_fourstay_login_dialog() throws Throwable {
		driver.get(Config.getProperty("url"));
		login = new Login();
		login.loginButton.click();
	}
	
	@When("^I click on the login button$")
	public void i_click_on_the_login_button() throws Throwable {
		login.loginBar.click();
	}
	
	
	@Given("^I have the emails and passwords from data base$")
	public void getDataBaseInfo() throws Throwable {
		//query
		String query = "SELECT first_name, last_name, email, phone_number " + " FROM employees";
		
		// connect to database and store results
		DBUtil.establishConnection(DBType.MYSQL);	
		resultsList = DBUtil.runSQLQuery(query);
	}

	@When("^And I enter the stored \"([^\"]*)\" and \"([^\"]*)\"$")
	public void and_I_enter_the_stored_and(String email, String password) throws Throwable {
		//store variables outside of method for reuse
		this.email = email;
		this.password = password;

		Thread.sleep(500);
		
		//enter and login
		login.emailEnter.sendKeys(email);
		login.passwordEnter.sendKeys(password);
		login.loginBar.click();

	}

	@When("^I navigate to the settings page$")
	public void navigateToSettingsPage() throws Throwable {
		Utility.waitForPageLoad();
		accountPage = new AccountPage();
		
		//click silhouette in top right corner
		accountPage.accountPic.click();
		
		//click the edit profile button that then appears
		accountPage.editProfile.click();	
	}

	@Then("^The first name, last name and phone number is correctly displayed$")
	public void verifyTheEnterDataMatches() throws Throwable {
		System.out.println("On verify info step");
		GeneralAccountInfoPage GAPage = new GeneralAccountInfoPage();
		
		// get first name from form 
		GAPage.firstNameBox.click();
		String firstName = GAPage.firstNameBox.getAttribute("value");
		System.out.println(firstName);

		// get last name from form
		String lastName = GAPage.lastNameBox.getAttribute("value");
		System.out.println(lastName);

		// get phone number
		String phoneNumber = GAPage.phoneNumberBox.getAttribute("value");
		System.out.println(phoneNumber);

		// Verify user information is correct
		for (String[] row : resultsList) {
			String emailCell = row[2] + "@testmail.com";
			if (emailCell.equalsIgnoreCase(email)) {
				String number = row[3];
				phoneNumber = phoneNumber.substring(0, 3) + "." + phoneNumber.substring(3, 6) + "."
						+ phoneNumber.substring(6);

				System.out.println("Asserting " + firstName + " " + lastName + "'s info is correct");
				
				Assert.assertEquals(row[0], firstName);
				Assert.assertEquals(row[1], lastName);
				Assert.assertEquals(number, phoneNumber);
			}

		}

	}




}
