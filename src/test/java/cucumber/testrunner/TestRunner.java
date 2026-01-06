package cucumber.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "classpath:CucumberFeatures",
        glue = {"cucumber.stepdefinitions"},
        plugin = "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        monochrome = true,
        dryRun = false,
        tags = "@RegressionTest"

)
public class TestRunner extends AbstractTestNGCucumberTests {
}
