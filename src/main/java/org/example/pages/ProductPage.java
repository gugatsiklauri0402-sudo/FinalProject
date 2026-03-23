package org.example.pages;


import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {


        // "My Account" ტექსტი/დროპდაუნი (ლოგინის შემდეგ ჩანს)
        @FindBy(id = "sw_dropdown_3347")
        WebElement MyAccountText;

        // Discount (sale) გვერდზე გადასვლის ლინკი
        @FindBy(xpath = "//a[@class='ty-menu__item-link' and contains(@href,'sale')]")
        WebElement Discount;

        // ფასის მარცხენა სლაიდერი (მინიმალური ფასი)
        @FindBy(id = "slider_39_1_left")
        WebElement PriceLeftSlider;

        // ფასის მარჯვენა სლაიდერი (მაქსიმალური ფასი)
        @FindBy(id = "slider_39_1_right")
        WebElement PriceRightSlider;

        // Search input ველი
        @FindBy(id = "search_input")
        WebElement SearchInput;

        // Search ღილაკი
        @FindBy(xpath = "//button[@class='ty-search-magnifier']")
        WebElement SearchBtn;

        // კონკრეტული პროდუქტის (Kisi ღვინო) სათაური
        @FindBy(xpath = "//a[contains(@class,'product-title') and @href]")
        WebElement Kisi;

        // Add to Cart ღილაკი კონკრეტული პროდუქტის
        @FindBy(id = "button_cart_9500018162")
        WebElement AddToCartWineBtn;

        // კალათაში პროდუქტების რაოდენობის შემოწმება (მაგ: 1)
        @FindBy(xpath = "//span[@class='th_minicart__title ty-hand desk']//span[@class='tt_minicart__amount'][normalize-space()='1']")
        WebElement CheckProductAmount;

        // Add to Wishlist (ფავორიტებში დამატება)
        @FindBy(xpath = "//a[contains(@class,'ty-add-to-wish') and contains(@class,'cm-submit')]")
        WebElement AddToWishBtn;

        // My Account ღილაკი
        @FindBy(xpath = "//a[@class='ty-btn']//span[@class='desktop-visible']")
        WebElement MyAccountBtn;

        // Favorites (wishlist) გვერდზე გადასვლა
        @FindBy(xpath = "//a[@class='ty-account-info__a' and contains(@href,'wishlist-view')]")
        WebElement FavoritesBtn;

        // Cart (კალათის) ღილაკი
        @FindBy(id = "cart_status_3346")
        WebElement CartBtn;

        // View Cart ღილაკი (კალათის გახსნა)
        @FindBy(xpath = "//div[contains(@class,'dropdown-buttons')]//a[contains(@class,'ty-btn')][1]")
        WebElement ViewCartBtn;

        // Clear Cart ღილაკი (კალათის გასუფთავება)
        @FindBy(xpath = "//div[contains(@class,'ty-float-left ')]")
        WebElement ClearCartBtn;

        // Popup დახურვის ღილაკი (alert dismiss)
        @FindBy(xpath = "//button[@data-dismiss='alert']")
        WebElement WaitPopup;

        // არასწორი პროდუქტის ძიების შედეგი (no result)
        @FindBy(id = "products_search_11")
        WebElement InValidProduct;

        // Logout ღილაკი
        @FindBy(xpath = "//div[@class='ty-account-info__buttons buttons-container']//a[contains(@href,'auth.logout')]")
        WebElement SignOutBtn;

        // კონსტრუქტორი — driver გადმოეცემა BasePage-ს და ელემენტები ინიციალიზდება
        public ProductPage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        // აბრუნებს MyAccount ელემენტს (assert-ისთვის)
        public WebElement getMyAccountText() {
            return MyAccountText;
        }

        // გადადის Discount გვერდზე
        public void discountBtn() {
            click(Discount);
        }

        // აყენებს ფასის ფილტრს (min და max)
        public void priceSliders(String left, String right) {

            // მინიმალური ფასის გასუფთავება და ჩაწერა
            clear(PriceLeftSlider);
            sendKeys(PriceLeftSlider, left);

            // მაქსიმალური ფასის გასუფთავება და ჩაწერა
            clear(PriceRightSlider);
            sendKeys(PriceRightSlider, right);

            // ველოდებით URL-ის ცვლილებას (ფილტრის გამოყენების შემდეგ)
            waitForElementUrlToBe("https://8000vintages.ge/sale/?features_hash=1-50-80-GEL");
        }

        // პროდუქტის ძიება search-ით
        public void searchProduct(String productName) {
            clear(SearchInput);
            click(SearchInput);
            sendKeys(SearchInput, productName);
            click(SearchBtn);
        }

        // აბრუნებს Kisi პროდუქტის ელემენტს (assert-ისთვის)
        public WebElement kisiWineTitle() {
            waitForElementToBeVisible(Kisi);
            return Kisi;
        }

        // აბრუნებს კალათაში არსებული პროდუქტების რაოდენობას (assert-ისთვის)
        public String productAmount() {
            return getText(CheckProductAmount);
        }

        // ამატებს პროდუქტს ფავორიტებში და გადადის Favorites გვერდზე
        public void addToFavoriteProduct() {
            waitForElementToBeClickable(AddToWishBtn);
            click(AddToWishBtn);
            // popup-ის დახურვა თუ გამოჩნდა
            checkPopup();
            click(MyAccountBtn);
            click(FavoritesBtn);
        }

        // შლის პროდუქტს ფავორიტებიდან
        public void removeFromFavoriteProduct() {
            waitForElementToBeClickable(AddToWishBtn);
            click(AddToWishBtn);
            checkPopup();
            click(MyAccountBtn);
            click(FavoritesBtn);
        }

        // ამატებს პროდუქტს კალათაში და შემდეგ შლის
        public void removeCartProduct() {
            click(AddToCartWineBtn);
            // ზოგჯერ click ვერ ხვდება — ამიტომ try-catch
            try {
                click(CartBtn);
            } catch(Exception e) {
                click(CartBtn);
            }
            click(ViewCartBtn);

            // ვასუფთავებთ კალათას
            click(ClearCartBtn);
        }

        // ამატებს პროდუქტს კალათაში და ხსნის cart-ს
        public void addToCartProduct() {
            click(AddToCartWineBtn);

            click(CartBtn);
            click(ViewCartBtn);
        }

        // ამატებს კიდევ ერთ პროდუქტს, უკვე დამატებულ პროდუქტს კალათაში
        public void addToAnotherCart() {
            try {
                click(AddToCartWineBtn);
            }
            catch(Exception e) {
                System.out.println("Click blocked by overlay");
            }
            click(AddToCartWineBtn);
            click(CartBtn);
            click(ViewCartBtn);
        }

        // popup-ის დახურვის helper მეთოდი (თუ გამოჩნდა)
        public void checkPopup() {
            try {
                waitForElementToBeClickable(WaitPopup);
                click(WaitPopup);
            } catch (Exception e) {
                // თუ popup არ გამოჩნდა არაფერს ვაკეთებთ
            }
        }

        // არასწორი პროდუქტის ძიება
        public void searchInvalidProduct(String productName) {
            sendKeys(SearchInput, productName);
            click(SearchBtn);
        }

        // აბრუნებს invalid search შედეგს (assert-ისთვის)
        public WebElement checkInValidProduct() {
            return InValidProduct;
        }

        // logout პროცესი
        public void LogOut(){
            click(MyAccountBtn);
            click(SignOutBtn);
        }
    }




