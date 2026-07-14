package automation.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.ecommerce.utils.WaitUtils;

public class ContactUsPage extends BasePage {

	private WaitUtils waitUtils;
	public ContactUsPage(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
	}

	private By contactMessage = By.cssSelector(".col-sm-8 .title");
	private By formName = By.cssSelector(".col-md-6 input[data-qa='name']");
	private By formEmail = By.cssSelector(".col-md-6 input[data-qa='email']");
	private By formSubject = By.cssSelector(".col-md-12 input[data-qa='subject']");
	private By formMessage = By.cssSelector(".col-md-12 textarea[data-qa='message']");
	private By formChooseFile = By.cssSelector(".col-md-12 input[name='upload_file']");
	private By submitBtn = By.cssSelector(".col-md-12 input[data-qa='submit-button']");
	private By successMsg = By.cssSelector(".status.alert");
	private By clickHomeBtn = By.cssSelector(".btn.btn-success");
	

	public String verifyGetInTouchMessage() {
		return waitUtils.waitForVisibility(contactMessage).getText();
	}
		
	public void fillContactForm(String name, String email, String subject, String message) {
	    sendKeys(waitUtils.waitForVisibility(formName), name);
	    sendKeys(waitUtils.waitForVisibility(formEmail), email);
	    sendKeys(waitUtils.waitForVisibility(formSubject), subject);
	    sendKeys(waitUtils.waitForVisibility(formMessage), message);
	}
	
	public void uploadFile(String filePath) {
		WebElement upload = waitUtils.waitForVisibility(formChooseFile);
	    upload.sendKeys(filePath); 
	}
	
	public void submitForm() {
		click(waitUtils.waitForClickable(submitBtn));
	}
	
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public String getSuccessMessage() {
		return waitUtils.waitForVisibility(successMsg).getText();
	}
	
	public void clickHome() {
		waitUtils.waitForVisibility(clickHomeBtn).click();
	}
}
