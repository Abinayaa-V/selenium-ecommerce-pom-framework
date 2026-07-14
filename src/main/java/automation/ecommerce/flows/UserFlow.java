package automation.ecommerce.flows;

import automation.ecommerce.pages.AccountCreatedPage;
import automation.ecommerce.pages.AccountDeletedPage;
import automation.ecommerce.pages.BasePage;
import automation.ecommerce.pages.HomePage;
import automation.ecommerce.pages.LoginPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import automation.ecommerce.models.User;

public class UserFlow {
	
	private WebDriver driver;
	private static final String ACCOUNT_CREATED_TEXT = "ACCOUNT CREATED!";
	private static final String ACCOUNT_DELETED_TEXT = "ACCOUNT DELETED!";
	private boolean userCreated = false;
	private boolean userLoggedIn = false;
	
    public UserFlow(WebDriver driver) {
        this.driver = driver;
    }
    
    public boolean isUserCreated() {
        return userCreated;
    }
    
    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    public HomePage registerUser(BasePage currentPage, User user) {

        AccountCreatedPage createdPage =
        		currentPage.menu
                        .clickSignUpOrLogin()
                        .signupAccount(user)
                        .enterAccountDetails(user);
        
        Assert.assertEquals(createdPage.getAccountCreatedMessage(), ACCOUNT_CREATED_TEXT);
        HomePage homePage = createdPage.clickContinue();

        userCreated = true;
        userLoggedIn = true;

        return homePage;
    }

    public HomePage loginUser(BasePage currentPage, User user) {

    	HomePage homePage = currentPage.menu
                .clickSignUpOrLogin()
                .loginAccount(user);
    	userLoggedIn = true;
    	return homePage;
    }
    
    public LoginPage loginUser(BasePage currentPage, String email, String password) {

        LoginPage loginPage =  currentPage.menu
                 .clickSignUpOrLogin()
                 .loginAccount(email, password);
        return loginPage;
     }

    public LoginPage logoutUser(BasePage currentPage) {

    	LoginPage loginPage =  currentPage.menu.clickLogout();
    	userLoggedIn = false;
    	return loginPage;
        
    }

    public HomePage deleteUser(BasePage currentPage) {
    	
    	AccountDeletedPage deletedPage = currentPage.menu
    									.clickDeleteAccount();
    	
    	Assert.assertEquals(deletedPage.getDeletedMessage(), ACCOUNT_DELETED_TEXT);
    	HomePage homePage = deletedPage.clickContinue();
    	
    	userCreated = false; 	
    	return homePage;
}
    
    public HomePage registerUserAndLogout(LoginPage loginPage, User user) {

        HomePage home = registerUser(loginPage, user);
        home.menu.clickLogout();
        return new HomePage(driver);
    }
    
}
