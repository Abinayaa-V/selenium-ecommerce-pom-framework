package automation.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.ecommerce.utils.WaitUtils;

public class AccountCreatedPage extends BasePage{
	
	private WaitUtils waitUtils;

	public AccountCreatedPage(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
	}
	
	private By accountCreatedText = By.cssSelector("h2[data-qa='account-created']");
    private By continueBtn = By.cssSelector("a[data-qa='continue-button']");

    public String getAccountCreatedMessage() {

        String actualText = waitUtils.waitForVisibility(accountCreatedText).getText();
        return actualText;
    }

    public HomePage clickContinue() {
        click(waitUtils.waitForClickable(continueBtn));
        return new HomePage(driver);
    }
}
