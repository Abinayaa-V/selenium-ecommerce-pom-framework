package automation.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.ecommerce.components.AddedToCartModal;
import automation.ecommerce.utils.WaitUtils;

public class ProductDetailsPage extends BasePage{

	private WaitUtils waitUtils;
	
	private By productName = By.cssSelector(".product-information h2");
	private By category = By.xpath("//div[@class='product-information']/p[1]");
	private By price = By.cssSelector(".product-information span span");
	private By availability = By.xpath("//p[b[text()='Availability:']]");
	private By condition = By.xpath("//p[b[text()='Condition:']]");
	private By brand = By.xpath("//p[b[text()='Brand:']]");
	private By quantity = By.cssSelector(".product-information span input[name='quantity']");
	private By addToCartBtn = By.cssSelector(".product-information span button");
		
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
	}
	
	public boolean isAt() {
        return driver.getCurrentUrl().contains("/product_details/");
    }
	
	public String getProductName() {
	    return waitUtils.waitForVisibility(productName).getText();
	}

	public String getCategory() {
	    return waitUtils.waitForVisibility(category).getText();
	}
	
	public String getPrice() {
	    return waitUtils.waitForVisibility(price).getText();
	}
	
	public String getAvailability() {
	    return waitUtils.waitForVisibility(availability).getText();
	}
	
	public String getCondition() {
	    return waitUtils.waitForVisibility(condition).getText();
	}
	
	public String getBrand() {
	    return waitUtils.waitForVisibility(brand).getText();
	}
	
	public String getQuantity() {
		return waitUtils.waitForVisibility(quantity).getText();
	}
	
	public void setQuantity(String qty) {
		sendKeys(waitUtils.waitForVisibility(quantity), qty);
	}
	
	public AddedToCartModal click_addToCart() {
		click(waitUtils.waitForVisibility(addToCartBtn));
		return new AddedToCartModal(driver);
	}
}
