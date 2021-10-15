package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	
	public WebDriver wdriver;
	
	public WaitHelper(WebDriver wdriver)
	{
		this.wdriver = wdriver;
	}
	
	public void WaitForElement(WebElement element, long timeOutInSeconds)
	{
		WebDriverWait wdWait = new WebDriverWait(wdriver, timeOutInSeconds );
		wdWait.until(ExpectedConditions.visibilityOf(element));
	}

}
