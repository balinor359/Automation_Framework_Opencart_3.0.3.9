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
//                "src/test/resources/features/AutomatedTesting/Common/AddProductToWishlistAsLoggedUser.feature",
//                "src/test/resources/features/AutomatedTesting/ProductPage/AddReviewToProduct.feature",
//                "src/test/resources/features/AutomatedTesting/ShoppingCart/EmptyCartCheckout.feature",
//                "src/test/resources/features/AutomatedTesting/ShoppingCart/MinicartCartData.feature",
//                "src/test/resources/features/AutomatedTesting/ShoppingCart/ShoppingCartDeleteProduct.feature",
//                "src/test/resources/features/AutomatedTesting/ShoppingCart/ShoppingCartQtyUpdate.feature",
//                "src/test/resources/features/AutomatedTesting/Checkout/CompleteOrderAsGuest.feature",
                "src/test/resources/features/AutomatedTesting/Checkout/CompleteOrderAsLoggedUser.feature",
//                "src/test/resources/features/Crud/Crud.feature"
                },

        glue = {"StepDefinitions"},
        monochrome = true,
        plugin = {"pretty", "html:target/html-reports/report.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests {

}
