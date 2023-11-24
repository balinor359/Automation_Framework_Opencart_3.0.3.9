package testRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"StepDefinitions"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
