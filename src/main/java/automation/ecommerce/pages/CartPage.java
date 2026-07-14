package automation.ecommerce.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.ecommerce.components.CartItem;
import automation.ecommerce.components.CheckoutModal;
import automation.ecommerce.components.Footer;
import automation.ecommerce.utils.WaitUtils;

public class CartPage extends BasePage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    public Footer footer;

    private By cartRows = By.cssSelector(".cart_info tbody tr");
    private By proceedCheckoutBtn = By.cssSelector(".btn.check_out");
    private By backHomeBtn = By.cssSelector("a[href='/']");
    private By returnToProductPage = By.cssSelector("p.text-center a[href='/products']");
    
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.footer = new Footer(driver);
    }

    public List<CartItem> getCartItems() {

        return driver.findElements(cartRows)
                .stream()
                .map(row -> new CartItem(driver, row))
                .toList();
    }

    public List<String> getProductNames() {

        return getCartItems()
                .stream()
                .map(CartItem::getName)
                .collect(Collectors.toList());
    }

    public CheckoutModal clickProceedCheckoutExpectingLogin() {
    	
    	click(waitUtils.waitForClickable(proceedCheckoutBtn));
        return new CheckoutModal(driver);
    }
    
    public CheckoutPage clickProceedCheckout() {
        click(waitUtils.waitForClickable(proceedCheckoutBtn));
        return new CheckoutPage(driver);
    }

    public CartPage clickBackHome() {

        click(waitUtils.waitForClickable(backHomeBtn));
        return this;
    }

    public void clickReturnToProductPage() {

        List<WebElement> links = driver.findElements(returnToProductPage);
        if (!links.isEmpty() && links.get(0).isDisplayed()) {
            click(links.get(0));
        }
    }

    public void removeProductFromCart(String productName) {

        List<CartItem> items = getCartItems();
        for (CartItem item : items) {
            if (item.getName().equals(productName)) {
                item.delete();
                break;
            }
        }
        new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(driver ->
                getProductNames().stream()
                        .noneMatch(name -> name.equals(productName))
        );
    }

    public void removeAllProductsFromCart() {

        List<CartItem> items = getCartItems();
        while (!items.isEmpty()) {
            items.get(0).delete();
            items = getCartItems();
        }
    }

    public boolean isCartEmpty() {
        return getCartItems().isEmpty();
    }
    
    public boolean isAt() {
        return driver.getCurrentUrl().contains("/view_cart");
    }
    
}
