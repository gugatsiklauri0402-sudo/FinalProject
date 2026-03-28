package ge.vintages8000.pages;

import ge.vintages8000.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    // ღილაკი "Sign In" / "Login" რომელიც ხსნის ლოგინის პოპაპს
    @FindBy(id="ui-dialog-iniciator")
    WebElement SingleSignInBtn;

    // Email input ველი (სადაც იწერება მომხმარებლის იმეილი)
    @FindBy(id="login_popup3347")
    WebElement EmailField;

    // Password input ველი (სადაც იწერება პაროლი)
    @FindBy(id="psw_popup3347")
    WebElement passwordField;

    // Login ღილაკი პოპაპში
    @FindBy(xpath = "(//button[@name='dispatch[auth.login]'])[2]")
    WebElement SignBtn;

    // იგივე Sign In ელემენტი ტექსტის წამოსაღებად (მაგ: ვამოწმებთ ავტორიზაციის მდგომარეობას)
    @FindBy(id = "ui-dialog-iniciator")
    WebElement SignInText;

    // კონსტრუქტორი — იღებს driver-ს BasePage-დან და ინიციალიზაციას უკეთებს ელემენტებს PageFactory-ის საშუალებით
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // login მეთოდი — ასრულებს ლოგინის სრულ პროცესს
    public void login(String email, String password){
        SingleSignInBtn.click();
        EmailField.clear();
        sendKeys(EmailField, email);
        passwordField.clear();
        sendKeys(passwordField, password);
        click(SignBtn);
    }

    // აბრუნებს Sign In ტექსტს (assert-ისთვის)
    public WebElement getSignInText(){
        return SignInText;
    }

    // აბრუნებს Login ღილაკის WebElement-ს (assert-ისთვის)
    public WebElement getSignInBtn(){
        return SignBtn;
    }
}