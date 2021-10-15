package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.*;

public class Steps extends BaseClass {	
	
	@Before
	public void beforeScenario() throws IOException {
		
		System.out.println("Test Scenario begins");
		
		configProp = new Properties();
		FileInputStream configFile = new FileInputStream("config.properties");
		configProp.load(configFile);
		
		logger = Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j.properties");
		
		String getBrowserPref = configProp.getProperty("browser");
		
		if (getBrowserPref.equals("chrome"))
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath")); //System.getProperty("user.dir")+"//Drivers/chromedriver.exe"
		else if (getBrowserPref.equals("firefox"))
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath")); //System.getProperty("user.dir")+"//Drivers/chromedriver.exe"
		else if (getBrowserPref.equals("ie"))
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath")); //System.getProperty("user.dir")+"//Drivers/chromedriver.exe"
		else
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath")); //System.getProperty("user.dir")+"//Drivers/chromedriver.exe"

		driver = new ChromeDriver();
		
	}
	
	@Given("User Launch Browser")
	public void user_launch_browser() 
	{
		logger.info("Browser Launched");
		lp = new LoginPage(driver);
		ap = new AddCustomerPage(driver);
		sc = new SearchCustomer(driver);	
	}
	
	// Login page data
	
	@Given("User opens URL {string}")
	public void user_opens_url(String url) {
	    driver.get(url);
	    driver.manage().window().maximize();
	    logger.info("URL Launched");
	}

	@And("User enters Username as {string} and Password as {string}")
	public void user_enters_username_as_and_password_as(String uname, String pwd) {
	    lp.setUsername(uname);
	    logger.info("Username '"+uname+"' set in the application");
	    lp.setPassword(pwd);
	    logger.info("Password '"+pwd+"' set in the application");
	}

	@And("User clicks Login button")
	public void user_clicks_login_button() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    lp.clickLogin();
	    logger.info("Login button clicked");
	}

	@And("Page title should be {string}")
	public void page_title_should_be(String title) {

		if (driver.getPageSource().contains("Login was unsuccessful."))
		{
			driver.close();
			logger.info("Login passed");
			Assert.assertTrue(false);	
		}
		else
		{
			logger.info("Login failed");
			Assert.assertEquals(title, driver.getTitle());
		}
	}

	// Customers page Data
	
	@Given("User can view Dashboard")
	public void user_can_view_dashboard() {
	    Assert.assertEquals("Dashboard / nopCommerce administration", ap.getPageTitle());	
	}
	@Given("User click on customers Menu")
	public void user_click_on_customers_menu() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		ap.clickOnCustomersMenu();
	}
	@When("User clicks on customers Menu Item")
	public void user_clicks_on_customers_menu_item() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		ap.clickOnCustomersMenuItem();
	}
	@When("click on Add new button")
	public void click_on_add_new_button() {
	    ap.clickOnAddNewCustomerButton();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	@When("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
	    //Assert.assertThat("Add a new customer / nopCommerce administration", is(ap.getPageTitle()));
		Assert.assertEquals("Add a new customer / nopCommerce administration", ap.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
		
		setImplicitWait(5);
		
	    String email = randomStringGeneration() + "@gmail.com";
	    ap.setEmail(email);
	    ap.setPassword(randomPasswordGeneration(7));
	    ap.setFirstName("John");
	    ap.setLastName("Maggie");
	    ap.setGender("Male");
	    ap.setDateOfBirth("12/12/1970");
	    ap.setCompany("DUMMY");
	    ap.selectTaxExempt();
	    ap.setNewsletter("Your store 2");
	    //ap.setCustomerRoles("Guest");
	    ap.setManagerOfVendor("Vendor 1");
	    ap.setAdminComment("Testing");
	    
	}
	
	@And("click on Save button")
	public void click_on_save_button() {
		ap.clickSaveButton();
	}
	
	@And("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		Assert.assertTrue(ap.getPageBodyText().contains(string));
	}
	
	// Search Customer using email ID
	
	@When("User enters customer Email")
	public void user_enters_customer_email() {
		//String email = randomStringGeneration() + "@gmail.com";
	    sc.setEmail("victoria_victoria@nopCommerce.com");
	}
	@When("Click on Search button")
	public void click_on_search_button() {
	    sc.clickSearchButton();
	}
	@When("User should be able to find the Search Table")
	public void user_should_be_able_to_find_the_search_table() {
		setImplicitWait(5);
		sc.searchDataInTable("victoria_victoria@nopCommerce.com");		
	}

	
	@Then("User clicks on Logout link")
	public void user_clicks_on_logout_link() {
		logger.info("Logsout and closes browser");
		setImplicitWait(30);
	    lp.clickLogout();
	}
	
	@After
	public void afterScenario() {
		System.out.println("Test Scenario ends");
	    driver.close();
	}
	
}
