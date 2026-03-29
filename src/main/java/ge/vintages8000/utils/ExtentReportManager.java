package ge.vintages8000.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// ExtentReportManager — მართავს ExtentReports-ს (რეპორტინგის სისტემა ტესტებისთვის)
public class ExtentReportManager {

    // ExtentReports ობიექტი — მთავარი რეპორტის გენერატორი
    private static ExtentReports extent;

    // ThreadLocal — თითოეულ ტესტს ექნება თავისი ExtentTest (parallel run-ისთვის)
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // აბრუნებს ExtentReports ობიექტს (თუ არ არსებობს — ქმნის)
    public static ExtentReports getExtentReports() {

        if (extent == null) {

            // რეპორტის ფაილის მისამართი (project root + /reports/report.html)
            String reportPath = System.getProperty("user.dir") + "/reports/report.html";

            // Spark Reporter — HTML რეპორტის გენერატორი
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // რეპორტის სახელი
            sparkReporter.config().setReportName("Automation Test Report");

            // ბრაუზერის ტაბის სათაური
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            // ExtentReports ინიციალიზაცია
            extent = new ExtentReports();

            // ვაბამთ Spark Reporter-ს
            extent.attachReporter(sparkReporter);

            // დამატებითი ინფორმაცია რეპორტში
            extent.setSystemInfo("Environment", ConfigReader.get("Environment"));
            extent.setSystemInfo("Tester", ConfigReader.get("Tester"));
        }

        return extent;
    }

    // ქმნის ახალ ტესტს რეპორტში
    public static ExtentTest createTest(String testName) {

        // ვქმნით ტესტს Extent-ში
        ExtentTest extentTest = getExtentReports().createTest(testName);

        // ვსვამთ ThreadLocal-ში (რომ სწორ thread-ზე იმუშაოს)
        test.set(extentTest);

        return extentTest;
    }

    // აბრუნებს მიმდინარე ტესტს (ThreadLocal-იდან)
    public static ExtentTest getTest() {
        return test.get();
    }

    // flush — წერს ყველაფერს ფაილში (რეპორტის დასრულება)
    public static void flushReports() {

        if (extent != null) {

            // რეალურად აქ იქმნება HTML report
            extent.flush();
        }
    }
}
