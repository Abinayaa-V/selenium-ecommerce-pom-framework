package automation.ecommerce.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.ecommerce.abstractComponents.AbstractComponent;
import automation.ecommerce.pages.ProductsPage;
import automation.ecommerce.utils.WaitUtils;

public class LeftSideFilterBar extends AbstractComponent {
	
	private WaitUtils waitUtils;

	public LeftSideFilterBar(WebDriver driver) {
		super(driver);
		this.waitUtils = new WaitUtils(driver);
		}
	
	private By categoryHeader = By.cssSelector(".left-sidebar h2");
	private By categoriesSidebar = By.cssSelector("div.left-sidebar");
	private By categories = By.cssSelector("#accordian .panel");
	
	private By brandText = By.cssSelector(".left-sidebar .brands_products h2");
	private By brandsSidebar = By.cssSelector("div.left-sidebar .brands_products .brands-name");
	private By brands = By.cssSelector(".brands-name ul li a");
	
	
	// dynamic locators
	private By genderLink(String gender) {
		return By.cssSelector("#accordian a[href='#" + gender + "']");
	}

	private By genderPanel(String gender) {
		return By.id(gender); // matches id="Women", "Men", etc.
	}

	public void selectGender(String gender) {
		WebElement element = waitUtils.waitForClickable(genderLink(gender));
		click(element);
		waitUtils.waitForVisibility(genderPanel(gender));
	}
	
	private By subCategoryLink(String gender, String subCategory) {
	    return By.xpath(
	        "//div[@id='" + gender + "']//a[normalize-space()='" + subCategory + "']"
	    );
	}
	
	public ProductsPage selectSubCategory(String gender, String subCategory) {
	    WebElement element = waitUtils.waitForClickable(subCategoryLink(gender, subCategory));
	    click(element);
		return new ProductsPage(driver);
	}
	
	public String getCategoryHeader() {
		return waitUtils.waitForVisibility(categoryHeader).getText().trim().toUpperCase();
	}

	public String getBrandHeader() {
		return waitUtils.waitForVisibility(brandText).getText().trim().toUpperCase();
	}
	
	public int getCategoryCount() {
	    return driver.findElements(categories).size();
	}
	
	public boolean areCategoriesVisible() {
		return waitUtils.waitForVisibility(categoriesSidebar).isDisplayed()
	            && driver.findElements(categories).size() > 0;
	}
	
	public boolean areBrandsVisible() {
		return waitUtils.waitForVisibility(brandsSidebar).isDisplayed()
				&& driver.findElements(brands).size() > 0;
	}
	
	public ProductsPage selectBrand(String brandName) {

	    By brand = By.xpath("//div[@class='brands-name']//a[contains(.,'" + brandName + "')]");
	    waitUtils.waitForClickable(brand).click();
	    return new ProductsPage(driver);
	}
	
}
