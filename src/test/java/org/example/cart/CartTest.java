package org.example.cart;

import org.example.BaseTest;
import org.example.BasePage;
import org.example.pages.CartPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.pages.ProductPage;

public class CartTest extends BaseTest {

    // Page Object-ები — კალათასა და პროდუქტთან სამუშაოდ
    private ProductPage productPage;
    private CartPage cartPage;
    private BasePage basePage;

    // ამ მეთოდის მუშაობის დროს თითოეული @Test-ის წინ ობიექტები ინიშნება
    @BeforeMethod
    public void pageSetup() {

        // ვაინიციალიზებთ ProductPage-ს
        productPage = new ProductPage(driver);

        // ვაინიციალიზებთ CartPage-ს
        cartPage = new CartPage(driver);

        // BasePage: საერთო ფუნქციებისთვის
        basePage = new BasePage(driver);
    }

    // ტესტი 1: პროდუქტს კალათაში ვამატებთ
    @Test
    public void testAddCart() {
        productPage.addToCartProduct();
        assertUrl(productPage.getCurrentUrl(), "https://8000vintages.ge/checkout-cart/");
        assertString(productPage.productAmount(), "1");
    }

    // ტესტი 2: დამატებითი პროდუქტის დამატება და რაოდენობის გაზრდა
    @Test
    public void testAddAnotherCart() {

        // მეორე პროდუქტის დამატება კალათაში
        productPage.addToAnotherCart();

        // + ღილაკზე დაჭერა კალათაში რაოდენობის გაზრდისთვის
        cartPage.clickPlusCartBtn();

        // ვამოწმებთ, რომ რაოდენობა გაიზარდა 2-მდე
        assertString(cartPage.checkPlusCartAmount(), "2");
    }

    // ტესტი 3: კალათიდან პროდუქტის წაშლა
    @Test
    public void testRemoveCart() {
        productPage.removeCartProduct();
        assertElementDisplayed(cartPage.checkCartIsEmpty());
    }
}
