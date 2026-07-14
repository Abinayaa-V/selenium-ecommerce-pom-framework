package automation.ecommerce.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdHandler {
	
	private WebDriver driver;

    public AdHandler(WebDriver driver) {
        this.driver = driver;
    }

	public void removeAds() {
		List<WebElement> ads = driver.findElements(By.cssSelector("iframe[id^='aswift']"));

		for (WebElement ad : ads) {
			try {
				if (ad.isDisplayed()) {
					((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", ad);
				}

			} catch (StaleElementReferenceException ignored) {
			}
		}
		// Also hide the ad containers
		((JavascriptExecutor) driver)
				.executeScript("document.querySelectorAll('ins.adsbygoogle').forEach(e => e.style.display='none');");
	}

}
