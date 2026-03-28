package ge.vintages8000.pages;

import ge.vintages8000.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FavoritesPage extends BasePage {

    // ღილაკი, რომელიც პროდუქტს შლის Wishlist-იდან (როცა უკვე დამატებულია)
    @FindBy(xpath = "//a[@class='is-in-wishlist']")
    WebElement RemoveFromWishBtn;

    // ელემენტი, რომელიც ჩნდება როცა Wishlist ცარიელია
    @FindBy(xpath = "//div[contains(@class,'ty-product-empty')]")
    WebElement CheckEmptyWishList;

    // კონსტრუქტორი — იღებს driver-ს და ინიციალიზაციას უკეთებს ელემენტებს PageFactory-ით
    public FavoritesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // აკლიკებს "Remove from Wishlist" ღილაკს (შლის პროდუქტს ფავორიტებიდან)
    public void getRemoveFromWishBtn() {
        click(RemoveFromWishBtn);
    }

    // ამოწმებს არის თუ არა Wishlist ცარიელი (აბრუნებს true/false) (assert-ისთვის)
    public WebElement getCheckEmptyWishList() {
        return CheckEmptyWishList;
    }
}
