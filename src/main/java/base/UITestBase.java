package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import utility.DriverUtils;

public abstract class UITestBase {

    private HomePage homePage;
    private LoginPage loginPage;


    // --- Test Pre-requisites ---- //
    @BeforeMethod
    public void beforeEachTestCase() {
        DriverUtils.open();
    }

    @AfterMethod
    public void afterEachTestCase() {
        DriverUtils.close();
    }


    // ---- Page Objects ---- //
    protected HomePage home() {
        if (homePage == null) {
            homePage = new HomePage();
            return homePage;
        }
        return homePage;
    }


    protected LoginPage login() {
        if (loginPage == null) {
            loginPage = new LoginPage();
            return loginPage;
        }
        return loginPage;
    }
}
