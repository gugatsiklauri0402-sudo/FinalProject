package ge.vintages8000.cart;

import ge.vintages8000.BaseTest;

import ge.vintages8000.pages.CartPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ge.vintages8000.pages.ProductPage;

public class CartTest extends BaseTest {

    // Page Object-ები — კალათასა და პროდუქტთან სამუშაოდ
    private ProductPage productPage;
    private CartPage cartPage;


    // ამ მეთოდის მუშაობის დროს თითოეული @Test-ის წინ ობიექტები ინიშნება
    @BeforeMethod
    public void pageSetup() {
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);


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
        productPage.addToAnotherCart();
        cartPage.clickPlusCartBtn();
        assertString(cartPage.checkPlusCartAmount(), "2");
    }

    // ტესტი 3: კალათიდან პროდუქტის წაშლა
    @Test
    public void testRemoveCart() {
        productPage.removeCartProduct();
        assertElementDisplayed(cartPage.checkCartIsEmpty());
    }
}
