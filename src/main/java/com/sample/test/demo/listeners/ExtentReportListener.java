package com.sample.test.demo.listeners;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.aventstack.extentreports.Status;
import com.sample.test.demo.base.LocalDriver;
import com.sample.test.demo.configuration.Configuration;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.sample.test.demo.base.TestBasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtentReportListener extends TestBasePage implements ITestListener {
    private Configuration config;
    private static final String OUTPUT_FOLDER = "./build/";
    private static final String FILE_NAME = "TestExecutionReport.html";

    private static ExtentReports extent = initialize();

    //Listener class used for extent reporting
    private static ExtentReports initialize() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/results/" + FILE_NAME);
        htmlReporter.config().setDocumentTitle("Automation Test Results");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);

        return extent;
    }

    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Test Suite is ending!"));
        extent.flush();
        test.remove();
        LocalDriver.getInstance().removeDriver();
    }

    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started!");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        try{
            test.get().log(Status.PASS,"Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
        }
        catch (Exception e)
        {
            Assert.fail("Exception caught" + e);
        }
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        try{
            test.get().log(Status.FAIL,"Test FAILED", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
        }
        catch (Exception e)
        {

        }
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        try {
            test.get().skip(result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
        } catch (IOException e) {
            System.err
                    .println("Exception thrown while updating test skip status " + Arrays.toString(e.getStackTrace()));
        }
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
