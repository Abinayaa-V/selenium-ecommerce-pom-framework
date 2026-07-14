package automation.ecommerce.components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import automation.ecommerce.abstractComponents.AbstractComponent;
import automation.ecommerce.pages.ProductDetailsPage;
import automation.ecommerce.utils.WaitUtils;

public class ProductCard extends AbstractComponent {
	private WebElement root;
	private WaitUtils waitUtils;

	public ProductCard(WebDriver driver, WebElement root) {
		super(driver);
		this.root = root;
		this.waitUtils = new WaitUtils(driver);
	}

	private By productName = By.cssSelector(".productinfo p");
	private By addToCartBtn = By.cssSelector(".product-overlay .add-to-cart");
	private By productOverlay = By.cssSelector(".product-overlay");
	private By viewProductBtn = By.cssSelector(".choose a[href^='/product_details']");

	public String getProductName() {
		return root.findElement(productName).getText();
	}

	public AddedToCartModal addToCart() {
		WebElement button = root.findElement(addToCartBtn);
		
		try {
	        click(button);   
	    } catch (ElementNotInteractableException e) {
	        ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].click();", button);
	    }
		return new AddedToCartModal(driver);
	}

	public boolean hasViewProduct() {
		return !root.findElements(viewProductBtn).isEmpty();
	}

	public ProductDetailsPage viewProduct() {
		if (!hasViewProduct()) {
			throw new UnsupportedOperationException("View Product not available");
		}
		click(root.findElement(viewProductBtn));
		return new ProductDetailsPage(driver);
	}

	public void hover() {
		Actions actions = new Actions(driver);
		actions.moveToElement(root).pause(Duration.ofMillis(500)).perform();
	}

	public AddedToCartModal hoverAndAddToCart() {
		adHandler.removeAds();
		scrollIntoView(root);
		hover();
		waitUtils.waitForVisibility(productOverlay);
		return addToCart();
	}

}
