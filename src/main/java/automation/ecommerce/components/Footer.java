package automation.ecommerce.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.ecommerce.abstractComponents.AbstractComponent;
import automation.ecommerce.utils.WaitUtils;

public class Footer extends AbstractComponent{
	
	private WaitUtils waitUtils;

	public Footer(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
	}
	
	private By footerSection = By.cssSelector("#footer");
	private By subscriptionText = By.cssSelector(".single-widget h2");
	private By emailField = By.cssSelector(".single-widget input[type='email']");
	private By submitBtn = By.cssSelector(".single-widget button[type='submit']");	
	private By successMsg = By.cssSelector("#success-subscribe div");
	
	public void scrollToFooter() {
		
		WebElement footerElement = waitUtils.waitForVisibility(footerSection);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footerElement);
	}
	
	public String getSubscriptionText() {
		return waitUtils.waitForVisibility(subscriptionText).getText();
	}
	
	public void subscribeUser(String email) {
		sendKeys(waitUtils.waitForVisibility(emailField), email);
		click(waitUtils.waitForClickable(submitBtn));
	}
	
	public String getSuccessMsg() {
		return waitUtils.waitForVisibility(successMsg).getText();
	}

}
