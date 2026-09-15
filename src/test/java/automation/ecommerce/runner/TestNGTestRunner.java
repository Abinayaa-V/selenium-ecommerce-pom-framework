package automation.ecommerce.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "automation.ecommerce.steps",
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber.html"
        },
        tags = "@Login or @Registration or @ContactUs or @TestCases or @Products or @Checkout"
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
