package ge.vintages8000.pages;

import ge.vintages8000.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartPage extends BasePage {

    // ტექსტი/ელემენტი რომელიც ჩანს როცა კალათა ცარიელია
    @FindBy(xpath = "//p[@class='ty-no-items']")
    WebElement YourCartIsEmpty;

    // "+" ღილაკი — ზრდის პროდუქტის რაოდენობას კალათაში
    @FindBy(xpath = "//a[normalize-space()='+']")
    WebElement PlusCartBtn;

    // ელემენტი რომელიც ამოწმებს რომ რაოდენობა გახდა 2
    @FindBy(xpath = "//span[@class='th_minicart__title ty-hand desk']//span[@class='tt_minicart__amount'][normalize-space()='2']")
    WebElement PlusCartAmount;

    // კონსტრუქტორი — იღებს driver-ს და ინიციალიზაციას უკეთებს ელემენტებს PageFactory-ით
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // აბრუნებს "Cart is empty" ელემენტს (assertion-ში გამოიყენება)
    public WebElement checkCartIsEmpty(){
        return YourCartIsEmpty;
    }

    // აკლიკებს "+" ღილაკს (ზრდის პროდუქტის რაოდენობას)
    public void clickPlusCartBtn(){
        click(PlusCartBtn);
    }

    // აბრუნებს კალათაში არსებული პროდუქტის რაოდენობას (მაგ: "2") (assertion_ში გამიყენება)
    public String checkPlusCartAmount(){
        return getText(PlusCartAmount);
    }
}
