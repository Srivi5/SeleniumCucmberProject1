package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.*;

public class SearchCustomer {
	
	public WebDriver ldriver;
	WaitHelper waitHelper;

	public SearchCustomer(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		waitHelper = new WaitHelper(ldriver);

	}
	
	By txtEmail = By.id("SearchEmail");
	By btnSearch = By.id("search-customers");
	
	@FindBy(how = How.ID, using = "customers-grid_wrapper")
	@CacheLookup
	WebElement tblSearchResults;
	
	@FindBy(how = How.XPATH, using = "//div[@id='customers-grid_wrapper']//tbody//tr")
	@CacheLookup
	List <WebElement> tblSearchResultsRows;
	
	@FindBy(how = How.XPATH, using = "//div[@id='customers-grid_wrapper']//tbody//tr//td")
	@CacheLookup
	List <WebElement> tblSearchResultsColumns;

	public void setEmail(String email)
	{
		waitHelper.WaitForElement(ldriver.findElement(txtEmail), 5);
		ldriver.findElement(txtEmail).clear();
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void clickSearchButton()
	{
		waitHelper.WaitForElement(ldriver.findElement(btnSearch), 5);
		ldriver.findElement(btnSearch).click();
	}
	
	public int getTableRows(List tableRowsList)
	{
		return tableRowsList.size();
	}
	
	public int getTableColumn(List tableColumnsList)
	{
		return tableColumnsList.size();
	}
	
	public void searchDataInTable(String email)
	{
		waitHelper.WaitForElement(tblSearchResults, 30);
		int rowCount = getTableRows(tblSearchResultsRows);
		int columnCount = getTableRows(tblSearchResultsRows);
		int row ; int column ; List <String> tbl = new ArrayList<>();
		boolean flag = false ;
		
		for (row = 1 ; row < rowCount ; row++)
		{
			
			String runtimeEmail = ldriver.findElement(By.xpath("//div[@id='customers-grid_wrapper']//tbody//tr["+row+"]//td[2]")).getText();
				if (runtimeEmail.equals(email))
				{
					tbl.add(runtimeEmail);
					flag = true ;
				}
		}
		
		if (flag == true)
		{
			System.out.println("Table contains the data : "+email+" with "+tbl.size()+" occurences");
			
		}
		else
			Assert.fail("Input email "+email+" not found in the web table");
		
	}
}
