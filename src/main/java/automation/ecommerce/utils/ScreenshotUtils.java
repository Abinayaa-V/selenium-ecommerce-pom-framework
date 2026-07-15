package automation.ecommerce.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String captureScreenshot(
            String testCaseName,
            WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        String path =
                System.getProperty("user.dir")
                + "/reports/screenshots/"
                + testCaseName
                + ".png";

        File destinationFile = new File(path);
        destinationFile.getParentFile().mkdirs();
        FileUtils.copyFile(sourceFile, destinationFile);

        return path;
    }
}