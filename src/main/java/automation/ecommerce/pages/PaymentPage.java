package automation.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.ecommerce.models.PaymentDetails;
import automation.ecommerce.utils.WaitUtils;

public class PaymentPage extends BasePage {

    private WaitUtils waitUtils;

    private By nameOnCard = By.cssSelector("input[data-qa='name-on-card']");
    private By cardNumber = By.cssSelector("input[data-qa='card-number']");
    private By cvc = By.cssSelector("input[data-qa='cvc']");
    private By expiryMonth = By.cssSelector("input[data-qa='expiry-month']");
    private By expiryYear = By.cssSelector("input[data-qa='expiry-year']");
    private By payAndConfirmBtn = By.cssSelector("button[data-qa='pay-button']");
    private By successMessage = By.cssSelector("div[id='success_message']");
    
    private By continueBtn = By.cssSelector("a[data-qa='continue-button']");

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    public void enterPaymentDetails(PaymentDetails payment) {

        type(waitUtils.waitForVisibility(nameOnCard),
                payment.getNameOnCard());

        type(driver.findElement(cardNumber),
                payment.getCardNumber());

        type(driver.findElement(cvc),
                payment.getCvc());

        type(driver.findElement(expiryMonth),
                payment.getExpiryMonth());

        type(driver.findElement(expiryYear),
                payment.getExpiryYear());
    }
    
    
   public OrderConfirmationPage clickPayAndConfirmOrder() {

        click(waitUtils.waitForClickable(payAndConfirmBtn));

        return new OrderConfirmationPage(driver);
    }
   
   public String getSuccessMessage() {
	   return waitUtils.waitForVisibility(successMessage).getText();
	   
   }

   public void clickContinue() {
	   click(waitUtils.waitForClickable(continueBtn));
   }
   
   
}