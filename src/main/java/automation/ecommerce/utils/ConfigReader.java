package automation.ecommerce.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/java/automation/ecommerce/resources/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // returns value OR null if key not found
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    // returns value OR defaultValue if key not found
    public static String getProperty(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }
}