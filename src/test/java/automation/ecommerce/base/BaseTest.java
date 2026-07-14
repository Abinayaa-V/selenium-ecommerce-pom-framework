package automation.ecommerce.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import automation.ecommerce.flows.UserFlow;
import automation.ecommerce.pages.HomePage;
import automation.ecommerce.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	protected UserFlow userFlow;
	protected boolean userCreated = false;
	
	//@BeforeMethod
	public void initializeDriver() {

		String url = ConfigReader.getProperty("url");
		
		// Priority: System property(-Dbrowser) > config file > default
		String browserName = System.getProperty("browser");

		// Fallback to properties file if no system property is provided
		if (browserName == null || browserName.isEmpty()) {
			browserName = ConfigReader.getProperty("browser", "chrome");
		}

		browserName = browserName.toLowerCase();

		switch (browserName) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver = new ChromeDriver(options);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "safari":
			driver = new SafariDriver();
			break;

		default:
			throw new RuntimeException("Unsupported browser: " + browserName);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(url);
		
		userFlow = new UserFlow(driver);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setup() {
	    initializeDriver();
	    userFlow = new UserFlow(driver);
	}


	//@AfterMethod
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (userFlow.isUserCreated() && userFlow.isUserLoggedIn()) {

	        try {
	            userFlow.deleteUser(new HomePage(driver));
	        }
	        catch(Exception e) {
	            System.out.println(
	                "User cleanup failed: " + e.getMessage()
	            );
	        }
	    }

	    if (driver != null) {
	        driver.quit();
	    }
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/reports/screenshots/" + testCaseName + ".png";

		File destinationFile = new File(path);
		FileUtils.copyFile(sourceFile, destinationFile);

		return path;
	}
	
	
	
}