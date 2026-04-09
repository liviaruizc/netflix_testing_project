package tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestResultLogger implements ITestListener {

    private static final long TEST_START_DELAY_MS = Long.getLong("test.delay.ms", 750L);

    @Override
    public void onTestStart(ITestResult result) {
        pauseBetweenTests();
    }

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

    private void pauseBetweenTests() {
        if (TEST_START_DELAY_MS <= 0) {
            return;
        }

        try {
            Thread.sleep(TEST_START_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Reporter.log("Test delay interrupted.", true);
        }
    }
}
