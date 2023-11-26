package testRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

/* This class initialize Cucumber features(tests) and their step definitions */
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"StepDefinitions"}
)

public class TestRunner extends AbstractTestNGCucumberTests {

}
