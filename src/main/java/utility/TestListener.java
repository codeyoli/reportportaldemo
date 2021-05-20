package utility;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    // ===== TEST SUITES ====== //

    public void onStart(ISuite suite) {

    }

    public void onFinish(ISuite suite) {

    }

    // ===== TEST  ====== //
    public void onStart(ITestContext test) {
    }

    public void onFinish(ITestContext test) {
    }


    // ===== TEST CASE  ====== //
    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {

    }

    public void onTestFailure(ITestResult result) {

    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {

    }

}
