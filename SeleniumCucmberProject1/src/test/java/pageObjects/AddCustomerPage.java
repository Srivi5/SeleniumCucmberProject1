package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

	public WebDriver ldriver;

	public AddCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains (text(), 'Customers')]");
	By lnkCustomers_menuitem = By.xpath("//li[@class = 'nav-item' ]/a[@href='/Admin/Customer/List']");

	By btnAddnew = By.xpath("//a[@class='btn btn-primary'][@href='/Admin/Customer/Create']");

	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.xpath("//input[@id= 'Password']");
	By txtNewsletter = By.xpath("//select[@id='SelectedNewsletterSubscriptionStoreIds']//parent::div");
	By txtCustomerRoles = By.xpath("//select[@id='SelectedCustomerRoleIds']//parent::div");

	By lstitemAdministrators = By.xpath("//li[contains (text(), 'Administrators')]");
	By lstitemRegistered = By.xpath("//li[contains (text(), 'Registered')]");
	By lstitemGuests = By.xpath("//li[contains (text(), 'Guests')]");
	By lstitemVendors = By.xpath("//li[contains (text(), 'Vendors')]");

	By lstitemYourstorename = By.xpath("//li[contains (text(), 'Your store name')]");
	By lstitemTeststore2 = By.xpath("//li[contains (text(), 'Test store 2')]");
	
	By chckTax = By.xpath("//input[@id = 'IsTaxExempt']");
	
	By drpmgrOfVendor=By.xpath("//select[@id='VendorId']");
	By rdMaleGender = By.id("Gender_Male");
	By rdFeMaleGender = By.id("Gender_Female");

	By txtFirstName = By.xpath("//input[@id='FirstName']");
	By txtLastName = By.xpath("//input[@id='LastName']");
	By txtDob = By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName = By.xpath("//input[@id='Company']");
	By txtAdminComment = By.xpath("//textarea [@id='AdminComment']");

	By btnSave = By.xpath("//button[@name='save']");
	By pgBody = By.tagName("body");
	
	// Action items
	
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	
	public String getPageBodyText()
	{
		return ldriver.findElement(pgBody).getText();
	}
	
	public void clickOnCustomersMenu()
	{
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem()
	{
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddNewCustomerButton()
	{
		ldriver.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email)
	{
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		ldriver.findElement(txtPassword).sendKeys(pwd);
	}
	
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setGender(String gender)
	{
		if (gender.equalsIgnoreCase("male"))
			ldriver.findElement(rdMaleGender).click();
		
		else if (gender.equalsIgnoreCase("female"))
			ldriver.findElement(rdFeMaleGender).click();
		
		else
			ldriver.findElement(rdMaleGender).click();
	}
	
	public void setDateOfBirth(String dob)
	{
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompany(String company)
	{
		ldriver.findElement(txtCompanyName).sendKeys(company);
	}
	
	public void selectTaxExempt()
	{
		Assert.assertFalse(ldriver.findElement(chckTax).isSelected());
		ldriver.findElement(chckTax).click();
		
	}
	public void setNewsletter(String news)
	{
		ldriver.findElement(txtCustomerRoles).click();
		
		WebElement listItem ; 
	
		if (news.equalsIgnoreCase("Your store name")) 
			listItem = ldriver.findElement(lstitemYourstorename); 
		
		else if (news.equalsIgnoreCase("Test store 2")) 
			listItem = ldriver.findElement(lstitemTeststore2); 
		
		else 
			listItem = ldriver.findElement(lstitemYourstorename); 
		
		JavascriptExecutor js =  (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", listItem);
		
	}
	
	public void setCustomerRoles(String role)
	{
		ldriver.findElement(txtCustomerRoles).click();
		
		WebElement listItem ; 
		
		if (role.equalsIgnoreCase("Guests")) 
			listItem = ldriver.findElement(lstitemGuests); 
		
		else if (role.equalsIgnoreCase("Administrators")) 
			listItem = ldriver.findElement(lstitemAdministrators); 
		
		else if (role.equalsIgnoreCase("Registered")) 
			listItem = ldriver.findElement(lstitemRegistered); 
		
		else if (role.equalsIgnoreCase("Vendors")) 
			listItem = ldriver.findElement(lstitemVendors); 
		
		else 
			listItem = ldriver.findElement(lstitemGuests);
		
		JavascriptExecutor js =  (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", listItem);
		
	}
	
	public void setManagerOfVendor (String mgrTxt)
	{
		Select mgrDrop = new Select(ldriver.findElement(drpmgrOfVendor));
		mgrDrop.selectByVisibleText(mgrTxt);
	}
	
	public void setAdminComment(String adminComment)
	{
		ldriver.findElement(txtAdminComment).sendKeys(adminComment);
	}
	
	public void clickSaveButton()
	{
		ldriver.findElement(btnSave).click();
	}	
	
}
