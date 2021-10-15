package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage {
	
	public WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	
	@FindBy(id = "Email")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(id = "Password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(xpath = "//button[@type='submit' and @class='button-1 login-button']")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(xpath = "//a[@href='/logout']")
	@CacheLookup
	WebElement lnkLogout;
	
	public void setUsername(String uname)
	{
		txtEmail.clear();
		txtEmail.sendKeys(uname);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		//btnLogin.click();
		JavascriptExecutor jse = (JavascriptExecutor)ldriver;
		jse.executeScript("arguments[0].click()", btnLogin);
	}
	
	public void clickLogout()
	{
		
		if (lnkLogout.isDisplayed() && lnkLogout.isEnabled())
		{
			JavascriptExecutor jse = (JavascriptExecutor)ldriver;
			jse.executeScript("arguments[0].click()", lnkLogout);
		}
		
	}


}
