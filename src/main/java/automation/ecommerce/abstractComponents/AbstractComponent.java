package automation.ecommerce.abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import automation.ecommerce.utils.AdHandler;
import automation.ecommerce.utils.WaitUtils;

public abstract class AbstractComponent {

	protected WebDriver driver;
	private WaitUtils waitUtils;
	protected AdHandler adHandler;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		this.waitUtils = new WaitUtils(driver);
		this.adHandler = new AdHandler(driver);
	}

	public void click(WebElement element) {
		scrollIntoView(element);
		element.click();
	}

	public void sendKeys(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	public void selectDropdown(WebElement element, String visibleText) {
		new Select(element).selectByVisibleText(visibleText);
	}

	public void scrollIntoView(WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	}

	public void safeClick(By locator) {

		int attempts = 0;

		while (attempts < 3) {

			try {
				WebElement element = waitUtils.waitForClickable(locator);
				scrollIntoView(element);
				element.click();
				return;

			} catch (ElementClickInterceptedException e) {

				attempts++;

				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
		}

		throw new RuntimeException("Unable to click element after retries: " + locator);
	}
}
