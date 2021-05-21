package tests;

import base.UITestBase;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UISmokeTest extends UITestBase {

   // @Test
    public void verify_trello_homepage_is_displayed() {
        home().open();
        boolean homePageIsDisplayed = home().verifyPage();
        Assert.assertTrue(homePageIsDisplayed);
    }


    @Test
    public void verify_invalid_login_results_in_error() {
        // Test data
        String fakeUser = new Faker().name().username();
        String fakePass = new Faker().internet().password();
        String expectedErrMsg = "This user does not exist";

        // Test Step
        home().open();
        home().visitLoginPage();
        login().enterCredentials(fakeUser, fakePass);
        String actualErrorMessage = login().getErrorMessage();

        // Test Assertion
        Assert.assertEquals(actualErrorMessage, expectedErrMsg);
    }
}
