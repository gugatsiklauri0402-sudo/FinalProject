package org.example.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class TestListener implements ITestListener {

    // იძახება როცა ტესტი იწყება
    @Override
    public void onTestStart(ITestResult result){

        // ვიღებთ ტესტის მეთოდის სახელს
        String testName = result.getMethod().getMethodName();

        // ვქმნით ახალ ტესტს Extent Report-ში
        ExtentReportManager.createTest(testName);

        // ვლოგავთ რომ ტესტი დაიწყო
        ExtentReportManager.getTest().info("Test Started: " + result.getName());

        // კონსოლშიც ვბეჭდავთ
        System.out.println("Test Started: " + result.getName());
    }

    // იძახება როცა ტესტი წარმატებით სრულდება
    @Override
    public void onTestSuccess(ITestResult result){

        // ვნიშნავთ ტესტს როგორც Passed Extent Report-ში
        ExtentReportManager.getTest().pass("Test Passed");

        // კონსოლში ბეჭდვა
        System.out.println("Test Passed:" + result.getName());
    }

    // იძახება როცა ტესტი ჩავარდება (FAIL)
    @Override
    public void onTestFailure(ITestResult result){

        // ვნიშნავთ ტესტს როგორც Failed
        ExtentReportManager.getTest().fail("Test Failed");

        // კონსოლში ბეჭდვა
        System.out.println("Test Failed: " + result.getName());

        // ვიღებთ driver-ს screenshot-ისთვის
        WebDriver driver = DriverManager.getDriver();

        // ვიღებთ screenshot-ს
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            // ვქმნით ფაილის path-ს
            String path = "reports/screenshots/" + result.getName() + ".png";

            // თუ folder არ არსებობს — ვქმნით
            new File("reports/screenshots/").mkdirs();

            // ვინახავთ screenshot-ს ფაილში
            FileUtils.copyFile(screenshot, new File(path));

            // ვამატებთ screenshot-ს Extent Report-ში
            ExtentReportManager.getTest()
                    .addScreenCaptureFromPath("screenshots/" + result.getName() + ".png");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // იძახება როცა ტესტი skip ხდება
    @Override
    public void onTestSkipped(ITestResult result){

        // ვნიშნავთ როგორც skipped
        ExtentReportManager.getTest().skip("Test Skipped");

        // კონსოლში ბეჭდვა
        System.out.println("Test Skipped: " + result.getName());
    }

    // იძახება როცა ტესტ სუიტი იწყება
    @Override
    public void onStart(ITestContext result){
        System.out.println("Test Suite Started: " + result.getName());
    }

    // იძახება როცა ტესტ სუიტი სრულდება
    @Override
    public void onFinish(ITestContext result){

        // კონსოლში ბეჭდვა
        System.out.println("Test Suite Finished: " + result.getName());

        // ვასრულებთ report-ს (flush — წერს HTML ფაილში)
        ExtentReportManager.flushReports();
    }
}
