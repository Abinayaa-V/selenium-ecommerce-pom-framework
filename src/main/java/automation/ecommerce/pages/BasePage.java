package automation.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.ecommerce.abstractComponents.AbstractComponent;
import automation.ecommerce.components.TopMenu;

public class BasePage extends AbstractComponent{
	protected WebDriver driver;
    public TopMenu menu;
        
    public BasePage(WebDriver driver) {
    	super(driver);
        this.driver = driver;
        this.menu = new TopMenu(driver);
    }
    
    protected void type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }   
    
}
