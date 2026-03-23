package org.example;

import org.example.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.example.utils.ConfigReader;
import org.example.utils.DriverManager;

public class BaseTest {

    // WebDriver ობიექტი — ბრაუზერის სამართავად
    protected WebDriver driver;


    // ეს მეთოდი გაეშვება ყოველი ტესტის დაწყებამდე
    @BeforeMethod
    public void setUp() {
        // იღებს driver-ს DriverManager-დან (ChromeDriver-ის ინიციალიზაცია)
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));
    }


    // ეს მეთოდი გაეშვება ყოველი ტესტის დასრულების შემდეგ
    @AfterMethod
    public void tearDown() {

        // ხურავს ბრაუზერს და ასუფთავებს driver-ს
        DriverManager.quit();
    }


    // ამოწმებს String-ების თანხვედრას (actual vs expected)
    public void assertString(String actual, String expected) {


        if (actual.equals(expected)) {
            Utils.log("PASS", "Assertion: text is correct");

        } else {
            Utils.log("FAIL", "Assertion: text is wrong");

            // აგდებს AssertionError-ს → ტესტი ვარდება
            throw new AssertionError("Assertion: text is wrong");
        }
    }


    // ამოწმებს URL-ის სისწორეს
    public void assertUrl(String actual, String expected) {
        if (actual.equals(expected)) {
            Utils.log("PASS", "Assertion: URL is correct");
        } else {
            Utils.log("FAIL", "Assertion: URL is wrong");
            throw new AssertionError("Assertion: URL is wrong");
        }
    }


    // ამოწმებს ელემენტი ჩანს თუ არა გვერდზე
    public void assertElementDisplayed(WebElement element) {


        if (element.isDisplayed()) {
            Utils.log("PASS", "Assertion: Element is displayed");
            Assert.assertTrue(true);
        } else {
            Utils.log("FAIL", "Assertion: Element is NOT displayed");
            Assert.fail("Assertion: Element is not displayed!");
        }
    }
}


