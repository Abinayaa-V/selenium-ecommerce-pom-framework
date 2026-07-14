package automation.ecommerce.components;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.ecommerce.pages.BasePage;
import automation.ecommerce.pages.CartPage;
import automation.ecommerce.pages.ProductsPage;
import automation.ecommerce.utils.AdHandler;
import automation.ecommerce.utils.WaitUtils;

public class AddedToCartModal extends BasePage{
	
	private WaitUtils waitUtils;
	AdHandler adHandler = new AdHandler(driver);

	public AddedToCartModal(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
	}
	
	private By continueShoppingBtn = By.cssSelector(".close-modal");
	private By viewCartLink = By.linkText("View Cart");
	
	private By addedModal = By.cssSelector(".modal-content");
	
	
	public ProductsPage clickContinueShopping() {

	    adHandler.removeAds();
	    waitUtils.waitForVisibility(addedModal);
	    WebElement button = waitUtils.waitForVisibility(continueShoppingBtn);
	    scrollIntoView(button);

	    try {
	        button.click();
	    } catch (ElementClickInterceptedException e) {
	        ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].click();", button);
	    }

	    waitUtils.waitForInvisibility(addedModal);
	    waitUtils.waitForInvisibility(By.cssSelector(".modal-backdrop"));
	    waitUtils.waitForClickable(By.cssSelector(".product-image-wrapper"));

	    return new ProductsPage(driver);
	}
	
	public CartPage clickViewCart() {
		safeClick(viewCartLink);
	    return new CartPage(driver);
	}
		
}
