package automation.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.ecommerce.utils.WaitUtils;

public class OrderConfirmationPage extends BasePage {

    private WaitUtils waitUtils;

    private By orderPlacedTitle = By.cssSelector("h2[data-qa='order-placed']");
    private By successMessage = By.cssSelector(".col-sm-9 p");
    private By continueBtn = By.cssSelector("a[data-qa='continue-button']");

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }
    
    public String getOrderPlacedTitle() {
        return waitUtils.waitForVisibility(orderPlacedTitle).getText();
    }

    public boolean isAt() {
        return waitUtils.waitForVisibility(orderPlacedTitle).isDisplayed();
    }

    public String getSuccessMessage() {
        return waitUtils.waitForVisibility(successMessage).getText();
    }

    public HomePage clickContinue() {
        click(waitUtils.waitForClickable(continueBtn));
        return new HomePage(driver);
    }
}
