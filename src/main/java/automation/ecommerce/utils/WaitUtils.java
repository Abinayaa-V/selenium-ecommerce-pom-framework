package automation.ecommerce.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	//private WebDriver driver;
	private WebDriverWait wait;
	private static final int DEFAULT_TIMEOUT = 10;

	public WaitUtils(WebDriver driver) {
		//this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
	}

	// Wait for element to be visible
	public WebElement waitForVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Wait for element to be click-able
	public WebElement waitForClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}
	
	public WebElement waitForClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	// Wait for element to disappear
    public boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    public boolean waitForTextToBe(By locator, String expectedText) {
    	return wait.until(ExpectedConditions.textToBe(locator, expectedText));
    }
    
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

	public WebElement waitForAnyVisibleElement(By locator) {

	    return wait.until(driver -> {
	        List<WebElement> elements = driver.findElements(locator);

	        return elements.stream()
	                .filter(WebElement::isDisplayed)
	                .findFirst()
	                .orElse(null);
	    });
	}
 
}
