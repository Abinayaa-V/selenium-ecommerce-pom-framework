package automation.ecommerce.tests;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import automation.ecommerce.base.BaseTest;
import automation.ecommerce.components.AddedToCartModal;
import automation.ecommerce.components.CartItem;
import automation.ecommerce.components.ProductCard;
import automation.ecommerce.models.PaymentDetails;
import automation.ecommerce.models.User;
import automation.ecommerce.pages.CartPage;
import automation.ecommerce.pages.CheckoutPage;
import automation.ecommerce.pages.ContactUsPage;
import automation.ecommerce.pages.HomePage;
import automation.ecommerce.pages.LoginPage;
import automation.ecommerce.pages.OrderConfirmationPage;
import automation.ecommerce.pages.PaymentPage;
import automation.ecommerce.pages.ProductDetailsPage;
import automation.ecommerce.pages.ProductsPage;

import automation.ecommerce.data.TestDataProvider;
import automation.ecommerce.utils.RetryAnalyzer;

public class SubmitOrderTest extends BaseTest {
	
	PaymentDetails payment = new PaymentDetails(
	        "Ajay Sharma",
	        "4111111111111111",
	        "123",
	        "12",
	        "2030");
		        
	
	@Test(dataProvider = "userData", dataProviderClass = TestDataProvider.class)
	public void verify_user_registration(User user) {
		HomePage home = userFlow.registerUser(new HomePage(driver), user);
		Assert.assertTrue(
	            home.menu.getLoggedInUsername()
	                    .contains(user.getFirstName()));
	
	}
	
	@Test(dataProvider = "userData", dataProviderClass = TestDataProvider.class)
	public void login_correct_email_password(User user) {
		HomePage home = userFlow.registerUser(new HomePage(driver), user);
		LoginPage loginPage = userFlow.logoutUser(home);
		HomePage loggedInHome = userFlow.loginUser(loginPage, user);
		Assert.assertTrue(
		        loggedInHome.menu
		                .getLoggedInUsername()
		                .contains(user.getFirstName())
		);
		
	}
	
	@Test(dataProvider = "userData", dataProviderClass = TestDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
	public void login_incorrect_email_password(User user) {
		HomePage home = userFlow.registerUser(new HomePage(driver), user);
		LoginPage loginPage = userFlow.logoutUser(home);
		loginPage.verifyLoginMsg();
		userFlow.loginUser(loginPage, "ajayy123@gmail.com", "ag987"); //wrong password 
		Assert.assertEquals(loginPage.getLoginErrorMessage(), "Your email or password is incorrect!");
		userFlow.loginUser(loginPage, user);
}
	
	@Test(dataProvider = "userData", dataProviderClass = TestDataProvider.class)
	public void logOut_User(User user) {
		HomePage home = new HomePage(driver);
		LoginPage loginPage = home.menu.clickSignUpOrLogin();
		userFlow.registerUserAndLogout(loginPage, user);
		LoginPage newloginPage = home.menu.clickSignUpOrLogin();
		newloginPage.verifyLoginMsg();
		HomePage loggedInHome = userFlow.loginUser(newloginPage, user);
		Assert.assertTrue(
		        loggedInHome.menu
		                .getLoggedInUsername()
		                .contains(user.getFirstName())
		);
		LoginPage loginPageAfterLogout =userFlow.logoutUser(loggedInHome);
		Assert.assertTrue(loginPageAfterLogout.isLoginPageDisplayed());
		userFlow.loginUser(loginPageAfterLogout, user);
}
	
	@Test(dataProvider = "userData", dataProviderClass = TestDataProvider.class)
	public void registerUser_existing_email(User user) {
		HomePage home = new HomePage(driver);
		LoginPage login = home.menu.clickSignUpOrLogin();
		userFlow.registerUserAndLogout(login, user);

		driver.get("https://automationexercise.com");
		
		HomePage newhome = new HomePage(driver);
		LoginPage loginPage = newhome.menu.clickSignUpOrLogin();
		loginPage.verifySigninMsg();
		loginPage.signupAccount(user.getFirstName(), user.getEmail());
		Assert.assertEquals(loginPage.getExistingEmailError(), "Email Address already exist!");	
		userFlow.loginUser(loginPage, user);
}
	
	@Test
	public void contactUs_form() {
		HomePage home = new HomePage(driver);
		ContactUsPage contactPage = home.menu.clickContactUs();
		Assert.assertEquals(contactPage.verifyGetInTouchMessage(), "GET IN TOUCH");
		contactPage.fillContactForm("Ajay", "ajayy123@gmail.com", "Product Query", "Let me know the material of the product");
		String filePath = new File("src/test/resources/test-files/sampleFile.png").getAbsolutePath();
		contactPage.uploadFile(filePath);
		contactPage.submitForm();
		contactPage.acceptAlert();
		Assert.assertEquals(contactPage.getSuccessMessage(), "Success! Your details have been submitted successfully.");
		contactPage.clickHome();
		driver.getCurrentUrl().contains("automationexercise.com");
	}
	
	@Test
	public void verify_testcases_page() {
		HomePage home = new HomePage(driver);
		home.menu.clickTestCases();
		Assert.assertTrue(driver.getCurrentUrl().contains("/test_cases"));
	}
	
	@Test
	public void verify_product_and_details_page() {
		HomePage home = new HomePage(driver);
		ProductsPage productsPage = home.menu.clickProducts();
		Assert.assertTrue(productsPage.isAt());
		Assert.assertTrue(productsPage.hasProducts());
		
		ProductDetailsPage detailsPage = productsPage.getFirstProduct()
	                        			.viewProduct();
		Assert.assertTrue(detailsPage.isAt());
		
		Assert.assertFalse(detailsPage.getProductName().isBlank());
		Assert.assertFalse(detailsPage.getCategory().isBlank());
		Assert.assertFalse(detailsPage.getPrice().isBlank());
		Assert.assertFalse(detailsPage.getAvailability().isBlank());
		Assert.assertFalse(detailsPage.getCondition().isBlank());
		Assert.assertFalse(detailsPage.getBrand().isBlank());
	}
	
	@Test
	public void verify_search_product() {
		HomePage home = new HomePage(driver);
		ProductsPage productsPage = home.menu.clickProducts();
		Assert.assertTrue(productsPage.isAt());
		
		ProductsPage searchedPage = productsPage.searchProduct("Top");
		Assert.assertEquals(searchedPage.getCategoryTitle(), "SEARCHED PRODUCTS");
		List<ProductCard> results = searchedPage.getProducts();
		Assert.assertFalse(results.isEmpty());
	}
	
	@Test
	public void verify_subscription_homePage() {
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isHomePageDisplayed());
		home.footer.scrollToFooter();
		Assert.assertEquals(home.footer.getSubscriptionText(), "SUBSCRIPTION");
		home.footer.subscribeUser("aj@gmail.com");
		Assert.assertEquals(home.footer.getSuccessMsg(), "You have been successfully subscribed!");
		
	}
	
	@Test
	public void verify_subscription_cartPage() {
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isHomePageDisplayed());
		CartPage cartPage = home.menu.clickCart();
		cartPage.footer.scrollToFooter();
		Assert.assertEquals(cartPage.footer.getSubscriptionText(), "SUBSCRIPTION");
		cartPage.footer.subscribeUser("aj@gmail.com");
		Assert.assertEquals(cartPage.footer.getSuccessMsg(), "You have been successfully subscribed!");

	}

	@Test
	public void verify_adding_Products_toCart() throws InterruptedException {
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isHomePageDisplayed());
		ProductsPage productsPage = home.menu.clickProducts();
		AddedToCartModal modal = productsPage.getProduct(1).hoverAndAddToCart();
		modal.clickContinueShopping();

		modal = productsPage.getProduct(2).hoverAndAddToCart();
		CartPage cartPage = modal.clickViewCart();
		List<CartItem> items = cartPage.getCartItems();

		Assert.assertEquals(items.size(), 2);
		for (CartItem item : items) {
			Assert.assertTrue(item.getPrice().startsWith("Rs."));
			Assert.assertEquals(item.getQuantity(), "1");
			Assert.assertEquals(item.getTotalPriceValue(), item.getPriceValue() * item.getQuantityValue());
		}
	}
	
	@Test
	public void verify_product_quantity_in_cart() {
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isHomePageDisplayed());
		ProductDetailsPage productDetailsPage = home.featured.getProducts().get(2).viewProduct();
		productDetailsPage.setQuantity("4");
		AddedToCartModal cartModal = productDetailsPage.click_addToCart();
		CartPage cartPage = cartModal.clickViewCart();
		Assert.assertEquals(
	            cartPage.getCartItems()
	                    .get(0)
	                    .getQuantity(),
	            "4"
	    );
		
	}
	
	@Test(dataProvider = "userData", dataProviderClass = TestDataProvider.class)
	public void verify_register_while_checkout(User user) throws InterruptedException  {
		
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isHomePageDisplayed());
		AddedToCartModal modal = home.getProduct(4).hoverAndAddToCart();
		modal.clickContinueShopping();
		modal = home.getProduct(5).hoverAndAddToCart();
		
		LoginPage loginPage = modal.clickViewCart()
				.clickProceedCheckoutExpectingLogin()
				.clickRegisterOrLogin();
		
		HomePage homePage = userFlow.registerUser(loginPage, user);
		Assert.assertTrue(
				homePage.menu.getLoggedInUsername()
	                    .contains(user.getFirstName()));
		CheckoutPage checkoutPage = homePage.menu.clickCart()
					.clickProceedCheckout();
		
		Assert.assertTrue(checkoutPage.isAddressDetailsDisplayed());
		Assert.assertTrue(checkoutPage.isReviewOrderDisplayed());
		checkoutPage.enterComment("Please deliver after 6 PM.");
		PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
		
		paymentPage.enterPaymentDetails(payment);
		OrderConfirmationPage orderConfirmationPage = paymentPage.clickPayAndConfirmOrder();
		Assert.assertEquals(
				orderConfirmationPage.getSuccessMessage(),
		        "Congratulations! Your order has been confirmed!"
		);
		orderConfirmationPage.clickContinue();
		
	}
	
	@Test(dataProvider = "userData", dataProviderClass = TestDataProvider.class)
	public void verify_register_before_checkout(User user) throws InterruptedException {
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isHomePageDisplayed()); 
		HomePage homePage = userFlow.registerUser(home, user);
		Assert.assertTrue(
				homePage.menu.getLoggedInUsername()
	                    .contains(user.getFirstName()));
		AddedToCartModal modal = home.getProduct(7).hoverAndAddToCart();
		modal.clickContinueShopping();
		modal = home.getProduct(2).hoverAndAddToCart();
		
		CartPage cartPage = modal.clickViewCart();
		Assert.assertTrue(cartPage.isAt());
		CheckoutPage checkoutPage = cartPage.clickProceedCheckout();
		Assert.assertTrue(checkoutPage.isAddressDetailsDisplayed());
		Assert.assertTrue(checkoutPage.isReviewOrderDisplayed());
		checkoutPage.enterComment("Please deliver on Saturday.");
		PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
		
		paymentPage.enterPaymentDetails(payment);
		OrderConfirmationPage orderConfirmationPage = paymentPage.clickPayAndConfirmOrder();
		Assert.assertEquals(
				orderConfirmationPage.getSuccessMessage(),
		        "Congratulations! Your order has been confirmed!"
		);
		orderConfirmationPage.clickContinue();
			
	}
	
	@Test(dataProvider = "userData", dataProviderClass = TestDataProvider.class)
	public void verify_login_before_checkout(User user) throws InterruptedException {
		HomePage home = new HomePage(driver);
		LoginPage loginPage = home.menu.clickSignUpOrLogin();
		userFlow.registerUserAndLogout(loginPage, user);

		driver.get("https://automationexercise.com");

		HomePage homePage = new HomePage(driver);
		Assert.assertTrue(homePage.isHomePageDisplayed());
		HomePage loggedInHome = userFlow.loginUser(loginPage, user);
		Assert.assertTrue(loggedInHome.menu.getLoggedInUsername().contains(user.getFirstName()));
		AddedToCartModal modal = loggedInHome.getProduct(7).hoverAndAddToCart();
		modal.clickContinueShopping();
		modal = loggedInHome.getProduct(4).hoverAndAddToCart();
		modal.clickContinueShopping();
		modal = loggedInHome.getProduct(2).hoverAndAddToCart();
		modal.clickContinueShopping();
		
		CartPage cartPage = loggedInHome.menu.clickCart();
		Assert.assertTrue(cartPage.isAt());
		CheckoutPage checkoutPage = cartPage.clickProceedCheckout();
		Assert.assertTrue(checkoutPage.isAddressDetailsDisplayed());
		Assert.assertTrue(checkoutPage.isReviewOrderDisplayed());
		PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

		paymentPage.enterPaymentDetails(payment);
		OrderConfirmationPage orderConfirmationPage = paymentPage.clickPayAndConfirmOrder();
		Assert.assertEquals(orderConfirmationPage.getSuccessMessage(),
				"Congratulations! Your order has been confirmed!");
		orderConfirmationPage.clickContinue();
	}
	
	@Test
	public void verify_removing_products_from_cart() throws InterruptedException {
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isHomePageDisplayed());
		ProductCard product1 = home.getProduct(7);
	    String productToRemove = product1.getProductName();

	    AddedToCartModal modal = product1.hoverAndAddToCart();
	    modal.clickContinueShopping();

	    ProductCard product2 = home.getProduct(4);
	    modal = product2.hoverAndAddToCart();
	    modal.clickContinueShopping();
		
		CartPage cartPage = home.menu.clickCart();
		Assert.assertTrue(cartPage.isAt());
		
		Assert.assertTrue(
	            cartPage.getProductNames()
	                    .contains(productToRemove)
	    );
		
		cartPage.removeProductFromCart(productToRemove);
	    Assert.assertFalse(
	            cartPage.getProductNames()
	                    .contains(productToRemove)
	    );
	   
	   cartPage.removeAllProductsFromCart();
	}
	
	@Test
	public void verify_category_filter_left_sidebar() {
		HomePage home = new HomePage(driver);
		
		Assert.assertTrue(home.sidebar.areCategoriesVisible());
		Assert.assertEquals(home.sidebar.getCategoryHeader(), "CATEGORY");

		home.sidebar.selectGender("Women");
		ProductsPage productsPage = home.sidebar.selectSubCategory("Women", "Dress");
		Assert.assertEquals(productsPage.getCategoryTitle(), "WOMEN - DRESS PRODUCTS");

		home.sidebar.selectGender("Men");
		productsPage = home.sidebar.selectSubCategory("Men", "Tshirts");
		Assert.assertEquals(productsPage.getCategoryTitle(), "MEN - TSHIRTS PRODUCTS");	
	}
	
	@Test
	public void verify_brand_filter_left_sidebar() {
		HomePage home = new HomePage(driver);
		ProductsPage productsPage = home.menu.clickProducts();
		Assert.assertTrue(productsPage.sidebar.areBrandsVisible());
		Assert.assertEquals(productsPage.sidebar.getBrandHeader(), "BRANDS");
		
		productsPage = productsPage.sidebar.selectBrand("Polo");

	    Assert.assertEquals(
	            productsPage.getBrandTitle(),
	            "BRAND - POLO PRODUCTS"
	    );

	    productsPage = productsPage.sidebar.selectBrand("H&M");

	    Assert.assertEquals(
	            productsPage.getBrandTitle(),
	            "BRAND - H&M PRODUCTS"
	    );
	}
}
