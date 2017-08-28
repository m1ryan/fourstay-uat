@Staytest_36
Feature: when price parameters are defined, 
	results will only contain those searched for
	
		
Scenario: Search for a place between $1000 and $1500 
	Given I am on the fourstay homepage 
	And I login as a guest 
	And I navigate back to the search page
	When I enter this search criteria
	|school				|	start	|	end	|
	|Georgetown University|08/29/2017|09/15/2017| 
	Then All places displayed should have a price
		