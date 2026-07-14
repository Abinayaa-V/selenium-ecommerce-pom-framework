package automation.ecommerce.components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartItem {

	private WebDriver driver;
	private WebElement root;

    private By name = By.cssSelector(".cart_description a");
    private By price = By.cssSelector(".cart_price p");
    private By quantity = By.cssSelector(".cart_quantity button");
    private By totalPrice = By.cssSelector(".cart_total_price");
    private By deleteBtn = By.cssSelector(".cart_quantity_delete");

    public CartItem(WebDriver driver, WebElement root) {
        this.driver = driver;
        this.root = root;
    }

    public String getName() {
        return root.findElement(name).getText();
    }

    public String getPrice() {
        return root.findElement(price).getText();
    }

    public String getQuantity() {
        return root.findElement(quantity).getText();
    }

    public String getTotalPrice() {
        return root.findElement(totalPrice).getText();
    }

    public void delete() {
    	WebElement deleteLink = root.findElement(deleteBtn);

        deleteLink.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.stalenessOf(root));
    }
    
    public int getPriceValue() {
        return Integer.parseInt(
                getPrice().replaceAll("[^0-9]", "")
        );
    }

    public int getQuantityValue() {
        return Integer.parseInt(getQuantity());
    }

    public int getTotalPriceValue() {
        return Integer.parseInt(
                getTotalPrice().replaceAll("[^0-9]", "")
        );
    }
}
