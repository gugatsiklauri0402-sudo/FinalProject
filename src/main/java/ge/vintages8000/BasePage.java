package ge.vintages8000;

import ge.vintages8000.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ge.vintages8000.utils.ConfigReader;

import java.time.Duration;


public class BasePage {

    // WebDriver — ბრაუზერის კონტროლი
    protected WebDriver driver;

    // WebDriverWait — explicit wait-ებისთვის
    protected WebDriverWait wait;

    // კონსტრუქტორი — იღებს driver-ს და ქმნის wait ობიექტს ConfigReader-დან წამოღებული დროით
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getLong("wait")));
        PageFactory.initElements(driver, this);
    }


    // ელემენტის ტექსტის წამოღება
    public String getText(WebElement element) {
        waitForElementToBeVisible(element);

        Utils.log("STEP","Get Text: " + element.getText());
        return element.getText();
    }

    // ელემენტის გამოჩენის დალოდება (visibility)
    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // ელემენტის clickable მდგომარეობამდე დალოდება
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // URL-ის კონკრეტულ მნიშვნელობამდე დალოდება
    public void waitForElementUrlToBe(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    // ტექსტის შეყვანა input-ში
    public void sendKeys(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.sendKeys(text);

       Utils.log("STEP","Entered text: " + text);
    }

    // ელემენტზე click
    public void click(WebElement element ) {
        String text = element.getText();

        if (text.isEmpty()) {
            text = element.getAttribute("textContent");
        }

        System.out.println("Clicked on element with text: " + text);
        waitForElementToBeClickable(element);
        element.click();

        Utils.log("STEP", "Clicked on element: " + text);
    }

    // მიმდინარე URL-ის დაბრუნება
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // input ველის გასუფთავება
    public void clear(WebElement element) {
        waitForElementToBeVisible(element);
        element.clear();
    }

    }



