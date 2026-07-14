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
import automation.ecommerce.utils.WaitUtils;

public class ProductsPage extends BasePage {
	
	public LeftSideFilterBar sidebar;
	public FeaturedProductsSection featured;
	private WaitUtils waitUtils;
	public Footer footer;

	private By productCards = By.cssSelector(".product-image-wrapper");
	private By productsTitle = By.cssSelector(".features_items .title");
    private By searchBox = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By brandTitle = By.xpath("//div[@class='features_items']/h2");


    public ProductsPage(WebDriver driver) {
        super(driver);
        this.sidebar = new LeftSideFilterBar(driver);
        this.featured = new FeaturedProductsSection(driver);
        this.waitUtils = new WaitUtils(driver);
        this.footer = new Footer(driver);
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("/products");
    }
    
    public String getCategoryTitle() {
        return waitUtils.waitForVisibility(productsTitle).getText();
    }
    
    public List<ProductCard> getProducts() {

        List<WebElement> productElements = driver.findElements(productCards);
        return productElements.stream()
                .map(element -> new ProductCard(driver, element))
                .collect(Collectors.toList());
    }

	
	public ProductsPage searchProduct(String productName) {

	    sendKeys(waitUtils.waitForVisibility(searchBox), productName);
	    click(waitUtils.waitForClickable(searchButton));
	    waitUtils.waitForTextToBe(productsTitle, "SEARCHED PRODUCTS");
	    return this;
	}
    
    public boolean hasProducts() {
        return !getProducts().isEmpty();
    }

    public int getProductCount() {
        return getProducts().size();
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
    
    public String getBrandTitle() {
        return waitUtils.waitForVisibility(brandTitle)
                .getText()
                .trim()
                .toUpperCase();
    }
}
