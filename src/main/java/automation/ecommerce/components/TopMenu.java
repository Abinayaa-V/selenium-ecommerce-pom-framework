package automation.ecommerce.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.ecommerce.abstractComponents.AbstractComponent;
import automation.ecommerce.pages.AccountDeletedPage;
import automation.ecommerce.pages.CartPage;
import automation.ecommerce.pages.ContactUsPage;
import automation.ecommerce.pages.LoginPage;
import automation.ecommerce.pages.ProductsPage;
import automation.ecommerce.utils.WaitUtils;

public class TopMenu extends AbstractComponent{
	
	private WaitUtils waitUtils;

	public TopMenu(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
	}

	private By menuHome = By.cssSelector(".col-sm-8 a[href='/']");
	private By menuProducts = By.cssSelector(".col-sm-8 a[href='/products']");
	private By menuCart = By.cssSelector(".col-sm-8 a[href='/view_cart']");
	private By menuLogin = By.cssSelector(".col-sm-8 a[href='/login']");
	private By menuLogOut = By.cssSelector(".col-sm-8 a[href='/logout']");
	private By menuDeleteAccount = By.cssSelector(".col-sm-8 a[href*='/delete']");
	private By menuContactUs = By.cssSelector(".col-sm-8 a[href*='contact']");
	private By menuTestCases = By.cssSelector(".col-sm-8 a[href*='/test_cases']");
	private By loggedInUser = By.xpath("//ul[@class='nav navbar-nav']//a[i[contains(@class,'fa-user')]]");
	
	private By productsList = By.cssSelector(".col-sm-9 .col-sm-4 .product-overlay");
	private By leftSideBar = By.cssSelector(".left-sidebar");
	private By searchProduct = By.id("search_product");

	
	public void waitForPageLoad() {
		waitUtils.waitForVisibility(productsList);
		waitUtils.waitForVisibility(leftSideBar);
	}
	public void clickHome() {
		click(waitUtils.waitForClickable(menuHome));
		waitForPageLoad();
	}
	
	public ProductsPage clickProducts() {
		click(waitUtils.waitForClickable(menuProducts));
		waitForPageLoad();
		waitUtils.waitForVisibility(searchProduct);
		return new ProductsPage(driver);
	}

	public CartPage clickCart() {
		adHandler.removeAds();
		safeClick(menuCart);
		return new CartPage(driver);
	}
	
	public LoginPage clickSignUpOrLogin() {
		click(waitUtils.waitForClickable(menuLogin));
		return new LoginPage(driver);
	}
	
	public LoginPage clickLogout() {
		click(waitUtils.waitForClickable(menuLogOut));
		return new LoginPage(driver);
	}
	
	public AccountDeletedPage clickDeleteAccount() {
		click(waitUtils.waitForClickable(menuDeleteAccount));
		return new AccountDeletedPage(driver);
	}
	
	public String getLoggedInUsername() {
		String userName = waitUtils.waitForVisibility(loggedInUser).getText();
		return userName;
	}
	
	public boolean isUserLoggedIn() {
	    return !driver.findElements(loggedInUser).isEmpty();
	}
	
	public ContactUsPage clickContactUs() {
		click(waitUtils.waitForClickable(menuContactUs));
		return new ContactUsPage(driver);
	}
	
	public void clickTestCases() {
		click(waitUtils.waitForClickable(menuTestCases));
	}
	
}
