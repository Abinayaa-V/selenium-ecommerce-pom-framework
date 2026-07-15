package automation.ecommerce.listeners;

import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import automation.ecommerce.reports.ExtentReporterNG;
import automation.ecommerce.utils.ExtentManager;
import automation.ecommerce.utils.ScreenshotUtils;

public class TestListener implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getReportObject();

	@Override
	public void onTestStart(ITestResult result) {
		
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		ExtentManager.setTest(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.getTest().pass("Test Passed");
		ExtentManager.removeTest();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentManager.getTest().fail(result.getThrowable());

		// Get driver from test class using reflection
		WebDriver driver = null;
		try {
			Field field = result.getTestClass()
		            .getRealClass()
		            .getSuperclass()
		            .getDeclaredField("driver");

		    field.setAccessible(true);

		    driver = (WebDriver) field.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Capture screenshot if driver is available
		String filePath = null;
		try {
			if (driver != null) {
				filePath = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName(), driver);
				System.out.println("Screenshot path: " + filePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Attach screenshot to Extent Report if capture succeeded
		if (filePath != null) {
			ExtentManager.getTest().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		}
		ExtentManager.removeTest();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentManager.getTest().skip("Test Skipped");
		ExtentManager.removeTest();
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
