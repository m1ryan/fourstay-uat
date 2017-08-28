@Smoke 
Feature: Fourstay login window 

Scenario: Email field should be displayed 
	Given the user is on fourstay home page 
	When the user clicks on the login link 
	Then the email field should be displayed 
	
	# this is a comment
	
	# to get rid of the yellow lines, 
	# after copy pasting the step definitions and saving
	# control+shift+F