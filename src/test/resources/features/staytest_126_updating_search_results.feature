@Staytest_126 
Feature: The search results update when changes are made 

Scenario: Change the number of beds in a search 
	Given I am on the fourstay homepage 
	And I enter school name "Georgetown University" 
	And I click the search button 
	When I change the number of beds 
	Then The search results should reflect the change