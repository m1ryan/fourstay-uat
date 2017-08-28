@Regression 
Feature: User data in base will match in page 

Background: 
	Given I have the emails and passwords from data base 
	
Scenario Outline: Adding employees from data base 
	When I am on the fourstay login dialog 
	And And I enter the stored "<email>" and "<password>" 
	And I click on the login button 
	When I navigate to the settings page 
	Then The first name, last name and phone number is correctly displayed 
	
	Examples: 
		| email                 | password |
		| sking@testmail.com    | password |
		| dlorentz@testmail.com | password |
		| daustin@testmail.com  | password |
		| isciarra@testmail.com | password |	
		| imikkili@testmail.com | password |
		| jnayer@testmail.com	| password |
		| shiggins@testmail.com | password |
		| acabrio@testmail.com  | password |
		| rperkins@testmail.com | password |
		| doconnel@testmail.com | password |
			