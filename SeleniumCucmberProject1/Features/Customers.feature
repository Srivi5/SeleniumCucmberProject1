Feature: Customer 

Background: Common used steps for the scnearios in Customer page
	Given User Launch Browser
	When User opens URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F" 
	And User enters Username as "admin@yourstore.com" and Password as "admin" 
	Then User clicks Login button 

Scenario: Add a new customer 
	Given User can view Dashboard
	When User click on customers Menu 
	And User clicks on customers Menu Item 
	And click on Add new button 
	And User can view Add new customer page 
	And User enter customer info 
	And click on Save button 
	And User can view confirmation message "The new customer has been added successfully." 
	Then User clicks on Logout link
	
@test
Scenario: Search a customer 
	Given User can view Dashboard
	When User click on customers Menu 
	And User clicks on customers Menu Item 
	And User enters customer Email
	And Click on Search button
	And User should be able to find the Search Table
	Then User clicks on Logout link
	
	