package testRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

/* This class initialize Cucumber features(tests) and their step definitions */
@CucumberOptions(
        features = {
//                "src/test/resources/features/AutomatedTesting/Registration/Registration.feature",
//                "src/test/resources/features/AutomatedTesting/SearchBar/SearchByKeyword.feature",
//                "src/test/resources/features/AutomatedTesting/ContactPage/ContactForm.feature",
//                "src/test/resources/features/AutomatedTesting/Common/FooterInternalLinks.feature",
                "src/test/resources/features/AutomatedTesting/Common/AddProductToWishlistAsLoggedUser.feature"
        },
//                    "src/test/resources/features/Crud/Crud.feature"},
        glue = {"StepDefinitions"},
        monochrome = true,
        plugin = {"pretty", "html:target/html-reports/report.html"}
//        tags = "@SearchByKeyword and not (@Registration or @Crud)"
//        tags = "@Registration"
)

public class TestRunner extends AbstractTestNGCucumberTests {

}
