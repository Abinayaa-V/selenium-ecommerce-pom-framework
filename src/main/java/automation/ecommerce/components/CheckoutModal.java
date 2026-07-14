package automation.ecommerce.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.ecommerce.pages.BasePage;
import automation.ecommerce.pages.CartPage;
import automation.ecommerce.pages.LoginPage;
import automation.ecommerce.utils.WaitUtils;

public class CheckoutModal extends BasePage {
	
	private WaitUtils waitUtils;
	
	public CheckoutModal(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
	}
	
	private By modalRegisterOrLogin = By.cssSelector("#checkoutModal a[href^='/login']");
	private By modalContinueCart = By.cssSelector("#checkoutModal .modal-footer button");

	public LoginPage clickRegisterOrLogin() {
		click(waitUtils.waitForClickable(modalRegisterOrLogin));
		return new LoginPage(driver);
	}
	
	public CartPage clickContinueOnCart() {
		click(waitUtils.waitForClickable(modalContinueCart));
		return new CartPage(driver);
	}	
}
