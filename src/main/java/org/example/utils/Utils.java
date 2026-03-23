package org.example.utils;

// Utils — დამხმარე კლასი ლოგირებისათვის (Extent Report-ში)
public class Utils {


    public static void log(String status, String msg) {

        if (ExtentReportManager.getTest() == null) {
            return;
        }

        switch (status.toUpperCase()) {

            case "INFO":
                ExtentReportManager.getTest().info(msg);
                break;

            case "PASS":
                ExtentReportManager.getTest().pass(msg);
                break;

            case "FAIL":
                ExtentReportManager.getTest().fail(msg);
                break;

            default:
                ExtentReportManager.getTest().info(msg);
        }

        // კონსოლშიც დაიბეჭდოს
        //System.out.println(status + ": " + msg);
    }
}

