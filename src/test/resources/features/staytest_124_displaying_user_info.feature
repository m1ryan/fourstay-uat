@Staytest_124 @Smoke 
Feature: Displaying user information 

Background: 
	Given I am on the fourstay login dialog 
	
Scenario: Verify host user information 
	And I enter email "testscooper@test.mail.com" 
	And I enter password "password" 
	When I click on the login button 
	Then the user name should be "Sheldon Cooper" 
	
@Test_me 
Scenario Outline: Verify host user information 
	And I enter email "<username>" 
	And I enter password "<password>" 
	When I click on the login button 
	Then the user name should be "Amy Fowler" 
	
	Examples: 
		|username | password|
		|testafowler@test.mail.com |password |
		|testafowler@test.mail.com |password |
		|testafowler@test.mail.com |password |