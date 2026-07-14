package automation.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.ecommerce.components.CartItem;
import automation.ecommerce.utils.WaitUtils;

public class CheckoutPage extends BasePage {
	
	private WaitUtils waitUtils;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
	}

	private By addressDetailsHeading = By.xpath("//h2[text()='Address Details']");
    private By reviewOrderHeading = By.xpath("//h2[text()='Review Your Order']");
    private By deliveryAddress = By.id("address_delivery");
    private By billingAddress = By.id("address_invoice");
    private By orderRows = By.cssSelector("#cart_info tbody tr[id^='product-']");
    private By commentBox = By.cssSelector("#ordermsg textarea[name='message']");
    private By placeOrderBtn = By.cssSelector("a.check_out[href='/payment']");

    public boolean isAddressDetailsDisplayed() {
        return waitUtils.waitForVisibility(addressDetailsHeading).isDisplayed();
    }

    public boolean isReviewOrderDisplayed() {
        return waitUtils.waitForVisibility(reviewOrderHeading).isDisplayed();
    }

    public String getDeliveryAddress() {
        return driver.findElement(deliveryAddress).getText();
    }

    public String getBillingAddress() {
        return driver.findElement(billingAddress).getText();
    }
    
    public List<CartItem> getOrderItems() {

        return driver.findElements(orderRows)
                .stream()
                .map(row -> new CartItem(driver, row))
                .toList();
    }

    public void enterComment(String comment) {

        WebElement box = waitUtils.waitForVisibility(commentBox);

        box.clear();
        box.sendKeys(comment);
    }

    public PaymentPage clickPlaceOrder() {

        click(waitUtils.waitForClickable(placeOrderBtn));

        return new PaymentPage(driver);
    }
}
