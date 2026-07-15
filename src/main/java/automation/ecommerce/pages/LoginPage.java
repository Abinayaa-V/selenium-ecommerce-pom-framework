package automation.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import automation.ecommerce.models.User;
import automation.ecommerce.utils.WaitUtils;

public class LoginPage extends BasePage {
	
	private WaitUtils waitUtils;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
	}
	
	private By loginMsg = By.cssSelector(".row .login-form h2");
	private By loginEmail = By.cssSelector(".row .login-form input[name='email']");
	private By loginPassword = By.cssSelector(".row .login-form input[name='password']");
	private By loginBtn = By.cssSelector(".row .login-form .btn");
	
	private By signupMsg = By.cssSelector(".row .signup-form h2");
	private By signupName = By.cssSelector(".row .signup-form input[name='name']");
	private By signupEmail = By.cssSelector(".row .signup-form input[name='email']");
	private By signupBtn = By.cssSelector(".row .signup-form .btn");
	
	private By existingEmailError = By.cssSelector(".signup-form p[style*='color: red']");
	private By loginErrorMessage = By.cssSelector("form p[style*='color: red']");
	
	public HomePage loginAccount(User user) {
		WebElement emailField = waitUtils.waitForVisibility(loginEmail);
		WebElement passwordField = waitUtils.waitForVisibility(loginPassword);
		sendKeys(emailField, user.getEmail());
		sendKeys(passwordField, user.getPassword());
		click(waitUtils.waitForClickable(loginBtn));
		return new HomePage(driver);
	}
	
	public LoginPage loginAccount(String email, String password) {
		WebElement emailField = waitUtils.waitForVisibility(loginEmail);
		WebElement passwordField = waitUtils.waitForVisibility(loginPassword);
		sendKeys(emailField, email);
		sendKeys(passwordField, password);
		click(waitUtils.waitForClickable(loginBtn));
		return new LoginPage(driver);
	}
	
	public AccountPage signupAccount(User user) {
		WebElement nameField = waitUtils.waitForVisibility(signupName);
		WebElement emailField = waitUtils.waitForVisibility(signupEmail);
		sendKeys(nameField, user.getFirstName());
		sendKeys(emailField, user.getEmail());
		
		click(waitUtils.waitForClickable(signupBtn));
		return new AccountPage(driver);
	}
	
	public LoginPage signupAccount(String email, String password) {
		WebElement nameField = waitUtils.waitForVisibility(signupName);
		WebElement emailField = waitUtils.waitForVisibility(signupEmail);
		sendKeys(nameField, email);
		sendKeys(emailField, password);
		
		click(waitUtils.waitForClickable(signupBtn));
		return new LoginPage(driver);
	}
	
	public void verifyLoginMsg() {
		Assert.assertEquals(driver.findElement(loginMsg).getText(), "Login to your account");
	}

	public void verifySigninMsg() {
		Assert.assertEquals(driver.findElement(signupMsg).getText(), "New User Signup!");
	}

	public boolean isLoginPageDisplayed() {
		return driver.getCurrentUrl().contains("/login");
	}
	
	public String getExistingEmailError() {
	    return waitUtils
	            .waitForVisibility(existingEmailError)
	            .getText();
	}
	
	public String getLoginErrorMessage() {

	    return waitUtils
	            .waitForVisibility(loginErrorMessage)
	            .getText();
	}
}
