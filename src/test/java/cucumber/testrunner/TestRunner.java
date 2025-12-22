package cucumber.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "classpath:CucumberFeatures",
        glue = {"cucumber.stepdefinitions"},
        monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {
}
