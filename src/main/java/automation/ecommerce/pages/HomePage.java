package automation.ecommerce.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.ecommerce.components.Footer;
import automation.ecommerce.components.LeftSideFilterBar;
import automation.ecommerce.components.ProductCard;
import automation.ecommerce.sections.FeaturedProductsSection;
import automation.ecommerce.sections.RecommendedProductsSection;
import automation.ecommerce.utils.WaitUtils;

public class HomePage extends BasePage {
	
	public LeftSideFilterBar sidebar;
	public FeaturedProductsSection featured;
    public RecommendedProductsSection recommended;
    public Footer footer;
    private WaitUtils waitUtils;
    
    private By productCards = By.cssSelector(".product-image-wrapper");
	private By productsTitle = By.cssSelector(".features_items .title");
    
    public HomePage(WebDriver driver) {
        super(driver);
        this.sidebar = new LeftSideFilterBar(driver);
        this.featured = new FeaturedProductsSection(driver);
        this.recommended = new RecommendedProductsSection(driver);
        this.footer = new Footer(driver);
        this.waitUtils = new WaitUtils(driver);
    }
    
    public boolean isHomePageDisplayed() {
        return driver.getCurrentUrl().equals("https://automationexercise.com/");
    }
    
    public List<ProductCard> getProducts() {

        List<WebElement> productElements = driver.findElements(productCards);
        return productElements.stream()
                .map(element -> new ProductCard(driver, element))
                .collect(Collectors.toList());
    }
    
    public String getProductsTitle() {
        return waitUtils.waitForVisibility(productsTitle).getText();
    }
    
    public ProductCard getFirstProduct() {

        List<ProductCard> products = getProducts();
        if (products.isEmpty()) {
            throw new IllegalStateException("No products found on Products page");
        }
        return products.get(0);
    }
    
    public ProductCard getProduct(int productNumber) {

        List<ProductCard> products = getProducts();
        if (productNumber < 1 || productNumber > products.size()) {
            throw new IllegalArgumentException(
                    "Invalid product number: " + productNumber
            );
        }
        return products.get(productNumber - 1);
    }
        
}
