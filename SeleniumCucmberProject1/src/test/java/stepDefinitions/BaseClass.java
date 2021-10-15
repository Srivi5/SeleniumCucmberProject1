package stepDefinitions;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import pageObjects.*;
import org.apache.log4j.Logger;

public class BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage ap;
	public SearchCustomer sc;
	public static Logger logger;
	public Properties configProp;
	
	public static String randomStringGeneration()
	{
		String generateString = RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}
	
	public static String randomAlphaNumGeneration()
	{
		String generateString = RandomStringUtils.randomAlphanumeric(7);
		return generateString;
	}
	
	public void setImplicitWait(int time)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
	
	public static String randomPasswordGeneration(int len)
	{
		// ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
 
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
	}
}
