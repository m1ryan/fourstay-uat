package com.fourstay.step_definitions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fourstay.pages.AccountPage;
import com.fourstay.pages.GeneralAccountInfoPage;
import com.fourstay.pages.Login;
import com.fourstay.pages.SearchResultsPage;
import com.fourstay.utilities.Config;
import com.fourstay.utilities.DBUtil;
import com.fourstay.utilities.DBUtil.DBType;
import com.fourstay.utilities.Driver;
import com.fourstay.utilities.Utility;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Displaying_User_Info_StepDefs {

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

	@Given("^I enter email \"([^\"]*)\"$")
	public void i_enter_email(String email) throws Throwable {
		Thread.sleep(2000);
		login.emailEnter.sendKeys(email);
	}

	@Given("^I enter password \"([^\"]*)\"$")
	public void i_enter_password(String password) throws Throwable {
		login.passwordEnter.sendKeys(password);
	}

	@When("^I click on the login button$")
	public void i_click_on_the_login_button() throws Throwable {
		login.loginBar.click();
	}

	@Then("^the user name should be \"([^\"]*)\"$")
	public void the_user_name_should_be(String expectedName) throws Throwable {
		AccountPage accountPage = new AccountPage();
		Utility.waitForPageLoad();
		String actual = accountPage.accountHolder.getText();
		Assert.assertEquals(expectedName, actual);
	}

	@Given("^I enter school name \"([^\"]*)\"$")
	public void i_enter_school_name(String schoolName) throws Throwable {
		// enter a school name
		login = new Login();
		login.schoolName.sendKeys(schoolName);
	}

	@Given("^I enter dates \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_enter_dates_and(String start, String end) throws Throwable {
		login.startDate.sendKeys(start);
		login.endDate.sendKeys(end);
	}

	@When("^I click the search button$")
	public void i_click_the_search_button() throws Throwable {
		// execute search
		login.search.click();
	}

	@Then("^the results should contain$")
	public void the_results_should_contain(List<String> states) throws Throwable {
		Utility.switchTab("search");
		Utility.waitForPageLoad();

		SearchResultsPage resultsPage = new SearchResultsPage();
		Set<String> actualStates = new HashSet<>();
		for (WebElement element : resultsPage.allStates) {
			actualStates.add(element.getText());
		}
		Assert.assertTrue(actualStates.containsAll(states));

	}

	@When("^I enter this search criteria$")
	public void i_enter_this_search_criteria(List<Map<String, String>> searchCriteria) throws Throwable {
		login = new Login();
		Map<String, String> input = searchCriteria.get(0);
		for (Map<String, String> map : searchCriteria) {

			login.schoolName.sendKeys(input.get("school"));
			login.startDate.sendKeys(input.get("start"));
			login.endDate.sendKeys(input.get("end"));
			login.search.click();
		}

	}

	@When("^I change the number of beds$")
	public void i_change_the_number_of_beds() throws Throwable {
		// switch to search tab and wait for page to load
		Utility.switchTab("search");
		Utility.waitForPageLoad();
		// find and print the amount of places in original search
		// searchList1 is an instance List of webElements
		searchList1 = driver.findElements(By.cssSelector(".img-responsive"));
		System.out.println("First search list amount: " + searchList1.size());
		SearchResultsPage resultsPage = new SearchResultsPage();
		// select and change the number of beds to search for
		Select bedSelect = new Select(resultsPage.bedButton);
		bedSelect.selectByValue("3");
		Thread.sleep(3000);

	}

	@Then("^The search results should reflect the change$")
	public void the_search_results_should_reflect_the_change() throws Throwable {
		// get the new list of places and assert it has changed
		List<WebElement> searchList3 = driver.findElements(By.cssSelector(".img-responsive"));
		System.out.println("Second search list amount: " + searchList3.size());

		Assert.assertFalse(searchList1.size() == searchList3.size());

	}

	@Then("^I should only see available after \"([^\"]*)\"$")
	public void i_should_only_see_available_after(String input1) throws Throwable {
		Utility.switchTab("search");
		Utility.waitForPageLoad();
		// List<String> dateList = Utility.getFromDates();
		// System.out.println(dateList);
		// get all the date elements
		List<WebElement> dates1 = Driver.getInstance().findElements(By.cssSelector("span[class='ng-binding']"));
		// store the string value
		System.out.println(dates1.size());
		List<String> dateList = new ArrayList<>();
		List<String> dateList1 = new ArrayList<>();
		for (WebElement elem : dates1) {
			dateList.add(elem.getText());

		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		for (String string : dateList) {
			string = string.substring(6);

			dateList1.add(string);
		}
		System.out.println(LocalDate.from(formatter.parse(input1)));
		for (String string : dateList1) {
			LocalDate date = LocalDate.from(formatter.parse(string));
			System.out.println(date);
			Assert.assertFalse(date.isBefore(LocalDate.from(formatter.parse(input1))));

		}

	}

	@Given("^I navigate back to the search page$")
	public void i_navigate_back_to_the_search_page() throws Throwable {
		Utility.waitForPageLoad();
		AccountPage accountPage = new AccountPage();
		accountPage.homeLogo.click();

	}

	@When("^I adjust the price range$")
	public void i_adjust_the_price_range() throws Throwable {
		Utility.waitForPageLoad();
		Utility.switchTab("search");
		// SearchResultsPage searchResultsPage = new SearchResultsPage();
		// Actions moveSlider = new Actions(driver);
		//
		// Action action =
		// moveSlider.dragAndDropBy(searchResultsPage.minPriceSliderHandle, 150,
		// 0).build();
		//
		// action.perform();
		// action = moveSlider.dragAndDropBy(searchResultsPage.maxPriceSliderHandle,
		// 800, 0).build();
		// action.perform();

	}

	@Then("^All places displayed should have a price$")
	public void all_places_displayed_should_have_a_price() throws Throwable {
		Utility.waitForPageLoad();
		Utility.switchTab("search");
		// get all prices elements
		List<WebElement> costElements = driver.findElements(By.cssSelector(".price-bold.ng-binding"));

		// save as a list of Strings
		List<String> priceList = new ArrayList<>();
		for (WebElement elem : costElements) {
			priceList.add(elem.getText());

		}
		System.out.println(priceList);
		for (String string : priceList) {
			Assert.assertTrue(!string.isEmpty());
			Assert.assertTrue(!string.equals("$"));

		}

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
