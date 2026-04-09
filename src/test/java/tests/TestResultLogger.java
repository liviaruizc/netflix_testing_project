package tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestResultLogger implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("PASS - " + getDisplayName(result), true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        StringBuilder message = new StringBuilder("FAIL - ").append(getDisplayName(result));
        if (result.getThrowable() != null && result.getThrowable().getMessage() != null) {
            message.append(" | Reason: ").append(result.getThrowable().getMessage());
        }
        Reporter.log(message.toString(), true);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        StringBuilder message = new StringBuilder("SKIP - ").append(getDisplayName(result));
        if (result.getThrowable() != null && result.getThrowable().getMessage() != null) {
            message.append(" | Reason: ").append(result.getThrowable().getMessage());
        }
        Reporter.log(message.toString(), true);
    }

    @Override
    public void onStart(ITestContext context) {
        Reporter.log("Starting test context: " + context.getName(), true);
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("Finished test context: " + context.getName(), true);
    }

    private String getDisplayName(ITestResult result) {
        return result.getTestClass().getName() + "." + result.getMethod().getMethodName();
    }
}
