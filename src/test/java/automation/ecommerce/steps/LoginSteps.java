package automation.ecommerce.steps;

import java.io.File;

import org.testng.Assert;

import automation.ecommerce.base.BaseTest;
import automation.ecommerce.data.TestDataProvider;
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
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;

import automation.ecommerce.components.AddedToCartModal;
import automation.ecommerce.components.CartItem;
import automation.ecommerce.components.ProductCard;

public class LoginSteps extends BaseTest{

	private User user;
    private HomePage home;
    private LoginPage loginPage;
    private HomePage loggedInHome;
    private ContactUsPage contactPage;
    private ProductsPage productsPage;
    private ProductDetailsPage detailsPage;
    private CartPage cartPage;
    private AddedToCartModal modal;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;
    private OrderConfirmationPage orderConfirmationPage;

    private String productToRemove;

    @Before
    public void setUpScenario() {
        setup();
    }

    @After
    public void tearDownScenario() {
        tearDown();
    }

	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws Exception {
	    user = TestDataProvider.getUser();
	    currentUser = user;
	}

	@When("I register a new user")
	public void i_register_a_new_user() {

	    home = userFlow.registerUser(
	            new HomePage(driver),
	            user
	    );
	}

	@When("I logout from the application")
	public void i_logout_from_the_application() {

	    loginPage = userFlow.logoutUser(home);
	}

	@When("I login with the registered user")
	public void i_login_with_the_registered_user() {

	    loggedInHome = userFlow.loginUser(loginPage,user);
	}

	@When("I register a new user and logout")
	public void i_register_a_new_user_and_logout() {

	    home = new HomePage(driver);

	    loginPage = home.menu.clickSignUpOrLogin();

	    userFlow.registerUserAndLogout(loginPage, user);
	}

	@When("I try to register with the same email")
	public void i_try_to_register_with_the_same_email() {

	    driver.get("https://automationexercise.com");

	    HomePage newHome = new HomePage(driver);

	    loginPage = newHome.menu.clickSignUpOrLogin();

	    loginPage.verifySigninMsg();

	    loginPage.signupAccount(
	            user.getFirstName(),
	            user.getEmail()
	    );
	}

	@When("I try to login with incorrect credentials")
	public void i_try_to_login_with_incorrect_credentials() {
	    loginPage.verifyLoginMsg();

	    userFlow.loginUser(
	            loginPage,
	            "ajayy123@gmail.com",
	            "ag987"
	    );
	}

	@When("I open the Contact Us page")
	public void i_open_the_contact_us_page() {
	    HomePage home = new HomePage(driver);
	    contactPage = home.menu.clickContactUs();
	}

	@When("I fill the Contact Us form")
	public void i_fill_the_contact_us_form() {
	    contactPage.fillContactForm(
	        "Ajay",
	        "ajayy123@gmail.com",
	        "Product Query",
	        "Let me know the material of the product"
	    );
	}

	@When("I upload a sample file")
	public void i_upload_a_sample_file() {
	    String filePath = new File(
	        "src/test/resources/test-files/sampleFile.png"
	    ).getAbsolutePath();

	    contactPage.uploadFile(filePath);
	}

	@When("I submit the Contact Us form")
	public void i_submit_the_contact_us_form() {
	    contactPage.submitForm();
	    contactPage.acceptAlert();
	}

	@When("I open the Test Cases page")
	public void i_open_the_test_cases_page() {
	    HomePage home = new HomePage(driver);
	    home.menu.clickTestCases();
	}

	@When("I open the Products page")
	public void i_open_the_products_page() {
	    home = new HomePage(driver);
	    productsPage = home.menu.clickProducts();
	}

	@When("I open the first product")
	public void i_open_the_first_product() {
	    detailsPage = productsPage
	            .getFirstProduct()
	            .viewProduct();
	}

	@When("I search for a product named {string}")
	public void i_search_for_a_product_named(String productName) {
	    productsPage = productsPage.searchProduct(productName);
	}

	@When("I scroll to the footer")
	public void i_scroll_to_the_footer() {
	    if (cartPage != null) {
	        cartPage.footer.scrollToFooter();
	    } else {
	        home = new HomePage(driver);
	        home.footer.scrollToFooter();
	    }
	}

	@When("I subscribe with email {string}")
	public void i_subscribe_with_email(String email) {
	    if (cartPage != null) {
	        cartPage.footer.subscribeUser(email);
	    } else {
	        home.footer.subscribeUser(email);
	    }
	}

	@When("I open the Cart page")
	public void i_open_the_cart_page() {
	    home = new HomePage(driver);
	    cartPage = home.menu.clickCart();
	}

	@When("I add the first product to the cart")
	public void i_add_the_first_product_to_the_cart() {
	    productsPage = new HomePage(driver)
	            .menu
	            .clickProducts();

	    modal = productsPage
	            .getProduct(1)
	            .hoverAndAddToCart();
	}

	@When("I continue shopping")
	public void i_continue_shopping() {
	    modal.clickContinueShopping();
	}

	@When("I add the second product to the cart")
	public void i_add_the_second_product_to_the_cart() {
	    modal = productsPage
	            .getProduct(2)
	            .hoverAndAddToCart();
	}

	@When("I view the cart")
	public void i_view_the_cart() {
	    cartPage = modal.clickViewCart();
	}

	@When("I open the third featured product")
	public void i_open_the_third_featured_product() {
	    home = new HomePage(driver);
	    detailsPage = home.featured
	            .getProducts()
	            .get(2)
	            .viewProduct();
	}

	@When("I set the product quantity to {string}")
	public void i_set_the_product_quantity_to(String quantity) {
	    detailsPage.setQuantity(quantity);
	}

	@When("I add the product to the cart")
	public void i_add_the_product_to_the_cart() {
	    modal = detailsPage.click_addToCart();
	}

	@When("I add product {int} to the cart")
	public void i_add_product_to_the_cart(int productNo) {
	    home = new HomePage(driver);

	    modal = home
	            .getProduct(productNo)
	            .hoverAndAddToCart();
	}

	@When("I proceed to checkout as a new user")
	public void i_proceed_to_checkout_as_a_new_user() {
	    loginPage = cartPage
	            .clickProceedCheckoutExpectingLogin()
	            .clickRegisterOrLogin();
	}

	@When("I proceed to checkout")
	public void i_proceed_to_checkout() {
	    cartPage = home.menu.clickCart();
	    checkoutPage = cartPage.clickProceedCheckout();
	}

	@When("I enter the order comment {string}")
	public void i_enter_the_order_comment(String comment) {
	    checkoutPage.enterComment(comment);
	}

	@When("I place the order")
	public void i_place_the_order() {
	    paymentPage = checkoutPage.clickPlaceOrder();
	}

	@When("I enter the payment details")
	public void i_enter_the_payment_details() {
	    PaymentDetails payment = new PaymentDetails(
	        "Ajay Sharma",
	        "4111111111111111",
	        "123",
	        "12",
	        "2030"
	    );

	    paymentPage.enterPaymentDetails(payment);
	}

	@When("I confirm the order")
	public void i_confirm_the_order() {
	    orderConfirmationPage = paymentPage.clickPayAndConfirmOrder();
	}

	@When("I continue after the order")
    public void i_continue_after_the_order() {
        orderConfirmationPage.clickContinue();
    }

	@When("I add product {int} to the cart and remember it")
	public void i_add_product_to_the_cart_and_remember_it(int productNo) {
	    home = new HomePage(driver);

	    ProductCard product = home.getProduct(productNo);
	    productToRemove = product.getProductName();

	    modal = product.hoverAndAddToCart();
	}

	@When("I remove the selected product from the cart")
	public void i_remove_the_selected_product_from_the_cart() {
	    cartPage.removeProductFromCart(productToRemove);
	}

	@When("I remove all products from the cart")
	public void i_remove_all_products_from_the_cart() {
	    cartPage.removeAllProductsFromCart();
	}

	@When("I select the {string} category")
	public void i_select_the_category(String gender) {
	    home.sidebar.selectGender(gender);
	}

	@When("I select the {string} subcategory under {string}")
	public void i_select_the_subcategory_under(String subCategory, String gender) {
	    productsPage = home.sidebar.selectSubCategory(gender, subCategory);
	}

	@When("I select the {string} brand")
	public void i_select_the_brand(String brand) {
	    productsPage = productsPage.sidebar.selectBrand(brand);
	}

	@Then("I should see the user logged in")
	public void i_should_see_the_user_logged_in() {

	    Assert.assertTrue(
	            home.menu
	                    .getLoggedInUsername()
	                    .contains(user.getFirstName())
	    );
	}

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {

        Assert.assertTrue(
                loggedInHome.menu
                        .getLoggedInUsername()
                        .contains(user.getFirstName())
        );
    }

    @Then("I should see the existing email error")
    public void i_should_see_the_existing_email_error() {

        LoginPage loginPage = new LoginPage(driver);

        Assert.assertEquals(
                loginPage.getExistingEmailError(),
                "Email Address already exist!"
        );
    }

    @Then("I should see the incorrect login error")
    public void i_should_see_the_incorrect_login_error() {
        Assert.assertEquals(
            loginPage.getLoginErrorMessage(),
            "Your email or password is incorrect!"
        );
    }

    @Then("I should see the login page")
    public void i_should_see_the_login_page() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
    }

    @Then("I should see the Get In Touch message")
    public void i_should_see_the_get_in_touch_message() {
        Assert.assertEquals(
            contactPage.verifyGetInTouchMessage(),
            "GET IN TOUCH"
        );
    }

    @Then("I should see the contact form success message")
    public void i_should_see_the_contact_form_success_message() {
        Assert.assertEquals(
            contactPage.getSuccessMessage(),
            "Success! Your details have been submitted successfully."
        );
    }

    @Then("I should be on the Home page")
    public void i_should_be_on_the_home_page() {
        contactPage.clickHome();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("automationexercise.com")
        );
    }

    @Then("I should be on the Test Cases page")
    public void i_should_be_on_the_test_cases_page() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("/test_cases")
        );
    }

    @Then("I should see the Products page")
    public void i_should_see_the_products_page() {
        Assert.assertTrue(productsPage.isAt());
    }

    @Then("I should see products listed")
    public void i_should_see_products_listed() {
        Assert.assertTrue(productsPage.hasProducts());
    }

    @Then("I should see the Product Details page")
    public void i_should_see_the_product_details_page() {
        Assert.assertTrue(detailsPage.isAt());
    }

    @Then("the product details should be displayed")
    public void the_product_details_should_be_displayed() {
        Assert.assertFalse(detailsPage.getProductName().isBlank());
        Assert.assertFalse(detailsPage.getCategory().isBlank());
        Assert.assertFalse(detailsPage.getPrice().isBlank());
        Assert.assertFalse(detailsPage.getAvailability().isBlank());
        Assert.assertFalse(detailsPage.getCondition().isBlank());
        Assert.assertFalse(detailsPage.getBrand().isBlank());
    }

    @Then("I should see the {string} title")
    public void i_should_see_the_title(String expectedTitle) {
        Assert.assertEquals(
            productsPage.getCategoryTitle(),
            expectedTitle
        );
    }

    @Then("I should see search results")
    public void i_should_see_search_results() {
        List<ProductCard> results = productsPage.getProducts();
        Assert.assertFalse(results.isEmpty());
    }

    @Then("I should see the Home page")
    public void i_should_see_the_home_page() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isHomePageDisplayed());
    }

    @Then("I should see the {string} text")
    public void i_should_see_the_text(String expectedText) {
        String actualText;

        if (cartPage != null) {
            actualText = cartPage.footer.getSubscriptionText();
        } else {
            actualText = home.footer.getSubscriptionText();
        }

        Assert.assertEquals(actualText, expectedText);
    }

    @Then("I should see the subscription success message")
    public void i_should_see_the_subscription_success_message() {
        String actualMessage;

        if (cartPage != null) {
            actualMessage = cartPage.footer.getSuccessMsg();
        } else {
            actualMessage = home.footer.getSuccessMsg();
        }

        Assert.assertEquals(
            actualMessage,
            "You have been successfully subscribed!"
        );
    }

    @Then("I should see {int} products in the cart")
    public void i_should_see_products_in_the_cart(int expectedCount) {
        List<CartItem> items = cartPage.getCartItems();

        Assert.assertEquals(items.size(), expectedCount);
    }

    @Then("the cart product details should be correct")
    public void the_cart_product_details_should_be_correct() {
        List<CartItem> items = cartPage.getCartItems();

        for (CartItem item : items) {
            Assert.assertTrue(item.getPrice().startsWith("Rs."));
            Assert.assertEquals(item.getQuantity(), "1");
            Assert.assertEquals(
                item.getTotalPriceValue(),
                item.getPriceValue() * item.getQuantityValue()
            );
        }
    }

    @Then("the cart product quantity should be {string}")
    public void the_cart_product_quantity_should_be(String expectedQuantity) {
        Assert.assertEquals(
            cartPage.getCartItems()
                    .get(0)
                    .getQuantity(),
            expectedQuantity
        );
    }

    @Then("I should see the logged in username")
    public void i_should_see_the_logged_in_username() {
        Assert.assertTrue(
            home.menu.getLoggedInUsername()
                .contains(currentUser.getFirstName())
        );
    }

    @Then("I should see the address details")
    public void i_should_see_the_address_details() {
        Assert.assertTrue(checkoutPage.isAddressDetailsDisplayed());
    }

    @Then("I should see the review order section")
    public void i_should_see_the_review_order_section() {
        Assert.assertTrue(checkoutPage.isReviewOrderDisplayed());
    }

    @Then("I should see the order confirmation message")
    public void i_should_see_the_order_confirmation_message() {
        Assert.assertEquals(
            orderConfirmationPage.getSuccessMessage(),
            "Congratulations! Your order has been confirmed!"
        );
    }

    @Then("I should be on the Cart page")
    public void i_should_be_on_the_cart_page() {
        Assert.assertTrue(cartPage.isAt());
    }

    @Then("I should see the selected product in the cart")
    public void i_should_see_the_selected_product_in_the_cart() {
        Assert.assertTrue(
            cartPage.getProductNames().contains(productToRemove)
        );
    }

    @Then("I should not see the selected product in the cart")
    public void i_should_not_see_the_selected_product_in_the_cart() {
        Assert.assertFalse(
            cartPage.getProductNames().contains(productToRemove)
        );
    }

    @Then("I should see the category sidebar")
    public void i_should_see_the_category_sidebar() {
        home = new HomePage(driver);

        Assert.assertTrue(home.sidebar.areCategoriesVisible());
        Assert.assertEquals(home.sidebar.getCategoryHeader(), "CATEGORY");
    }

    @Then("I should see the brand sidebar")
    public void i_should_see_the_brand_sidebar() {
        Assert.assertTrue(productsPage.sidebar.areBrandsVisible());
        Assert.assertEquals(productsPage.sidebar.getBrandHeader(), "BRANDS");
    }

    @Then("I should see the {string} brand title")
    public void i_should_see_the_brand_title(String expectedTitle) {
        Assert.assertEquals(productsPage.getBrandTitle(), expectedTitle);
    }
}
