package ge.vintages8000.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



// DriverManager — პასუხისმგებელია WebDriver-ის შექმნასა და მართვაზე
public class DriverManager {

    // static driver — ერთი და იგივე დრაივერი გამოიყენება მთელ ტესტებში
    private static WebDriver driver;

    // აბრუნებს WebDriver-ს (თუ არ არსებობს — ქმნის)
    public static WebDriver getDriver(){
        if(driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        return driver;
    }

    // ხურავს ბრაუზერს და ასუფთავებს driver-ს
    public static void quit() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
