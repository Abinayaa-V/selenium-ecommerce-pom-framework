package automation.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.ecommerce.models.User;

public class AccountPage extends BasePage{
	private By password = By.id("password");
	private By firstName = By.id("first_name");
	private By lastName = By.id("last_name");
	private By address = By.id("address1");
	private By country = By.id("country");
	private By state = By.id("state");
	private By city = By.id("city");
	private By zipcode = By.id("zipcode");
	private By mobile = By.id("mobile_number");
	private By newsletterCheckbox = By.id("newsletter");
	private By offersCheckbox = By.id("optin");
											
	public AccountPage(WebDriver driver) {
		super(driver);
	}

	public AccountCreatedPage enterAccountDetails(User user) {
		
		sendKeys(driver.findElement(password),user.getPassword());
		sendKeys(driver.findElement(firstName),user.getFirstName());
		sendKeys(driver.findElement(lastName),user.getLastName());
		sendKeys(driver.findElement(address),user.getAddress());
		selectDropdown(driver.findElement(country),user.getCountry());
		sendKeys(driver.findElement(state),user.getState());
		sendKeys(driver.findElement(city),user.getCity());
		sendKeys(driver.findElement(zipcode),user.getZipcode());
		sendKeys(driver.findElement(mobile),user.getMobile());
		click(driver.findElement(newsletterCheckbox));
	    click(driver.findElement(offersCheckbox));
		click(driver.findElement(By.cssSelector("button[data-qa='create-account']")));
		return new AccountCreatedPage(driver);
		
	}
}
