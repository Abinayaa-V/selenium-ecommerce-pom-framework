package automation.ecommerce.sections;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import automation.ecommerce.components.ProductCard;

public class RecommendedProductsSection {
	private WebDriver driver;
    private By productCards = By.cssSelector(".recommended_items .product-image-wrapper");

    public RecommendedProductsSection(WebDriver driver) {
        this.driver = driver;
    }

    public List<ProductCard> getProducts() {
        return driver.findElements(productCards)
                .stream()
                .map(el -> new ProductCard(driver, el))
                .collect(Collectors.toList());
    }
}
