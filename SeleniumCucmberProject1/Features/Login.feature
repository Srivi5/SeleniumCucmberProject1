Feature: Login 

@test
Scenario: Login successfully with valid credentials 
	Given User Launch Browser
	When User opens URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F" 
	And User enters Username as "admin@yourstore.com" and Password as "admin" 
	And User clicks Login button 
	And Page title should be "Dashboard / nopCommerce administration" 
	And User clicks on Logout link 
	Then Page title should be "Your store. Login" 


Scenario Outline: Login successfully with valid credentials 
	Given User Launch Browser
	When User opens URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F" 
	And User enters Username as "<email>" and Password as "<password>" 
	And User clicks Login button 
	And Page title should be "Dashboard / nopCommerce administration" 
	And User clicks on Logout link
	Then Page title should be "Your store. Login" 
	Examples: 
		| email | password |
		| admin@yourstore.com | admin |
		| admin123@yourstore.com | admin |
		