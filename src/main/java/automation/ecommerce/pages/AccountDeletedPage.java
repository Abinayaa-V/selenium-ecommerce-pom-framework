package automation.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.ecommerce.utils.WaitUtils;

public class AccountDeletedPage extends BasePage {
	
	private WaitUtils waitUtils;
	private By accountDeletedTitle = By.cssSelector("h2[data-qa='account-deleted']");
	private By continueBtn = By.cssSelector("a[data-qa='continue-button']");

	public AccountDeletedPage(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
	}
	
	public String getDeletedMessage() {

        String deletedMsg = waitUtils.waitForVisibility(accountDeletedTitle).getText();
        return deletedMsg;
    }

    public HomePage clickContinue() {

        click(waitUtils.waitForClickable(continueBtn));
        return new HomePage(driver);
    }
    
    public boolean isAt() {
        return waitUtils.waitForVisibility(accountDeletedTitle)
                        .isDisplayed();
    }
}
