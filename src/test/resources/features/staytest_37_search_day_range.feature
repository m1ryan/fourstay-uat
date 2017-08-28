@Staytest_37 
Feature: As a user I should get only available places for the specified date range 

Scenario: I execute a search with dates 
	Given I am on the fourstay homepage 
	And I enter school name "Georgetown University" 
	And I enter dates "08/29/2017" and "09/15/2017" 
	When I click the search button 
	Then I should only see available after "08/29/2017"