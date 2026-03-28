package ge.vintages8000.product;

import ge.vintages8000.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ge.vintages8000.pages.FavoritesPage;
import ge.vintages8000.pages.LoginPage;
import ge.vintages8000.pages.ProductPage;

public class ProductTest extends BaseTest {

    // Page Object-ები — სხვადასხვა გვერდთან სამუშაოდ
    private ProductPage productPage;
    private LoginPage loginPage;
    private FavoritesPage favoritesPage;


    // ეს მეთოდი გაეშვება ყოველი ტესტის წინ (@BeforeMethod)
    @BeforeMethod
    public void setUpPages() {

        // ვაინიციალიზებთ ProductPage-ს მიმდინარე driver-ით
        productPage = new ProductPage(driver);

        // ვაინიციალიზებთ LoginPage-ს
        loginPage = new LoginPage(driver);

        // ვაინიციალიზებთ FavoritesPage-ს
        favoritesPage = new FavoritesPage(driver);
    }



    // ტესტი 1: ფასდაკლების გვერდზე ფილტრის შემოწმება
    @Test
    public void testDiscountPrice()  {

        productPage.discountBtn();
        productPage.priceSliders("50", "80");
        assertUrl(productPage.getCurrentUrl(), "https://8000vintages.ge/sale/?features_hash=1-50-80-GEL"
        );
    }



    // ტესტი 2: პროდუქტის ძებნა
    @Test
    public void testSearchProduct()  {
        productPage.searchProduct("ქისი");
        assertElementDisplayed(productPage.kisiWineTitle());
    }



    // ტესტი 3: პროდუქტის დამატება Favorites-ში
    @Test
    public void testAddFavoriteProduct()  {
        loginPage.login("automattt444@gmail.com", "automation123");
        productPage.addToFavoriteProduct();
        assertUrl(favoritesPage.getCurrentUrl(), "https://8000vintages.ge/wishlist-view/"
        );
    }



    // ტესტი 4: პროდუქტის წაშლა Favorites-იდან
    @Test
    public void testRemoveFavoriteProduct()  {
        loginPage.login("automattt444@gmail.com", "automation123");
        productPage.removeFromFavoriteProduct();
        favoritesPage.getRemoveFromWishBtn();
        assertElementDisplayed(favoritesPage.getCheckEmptyWishList());
    }



    // ტესტი 5: არასწორი (invalid) ძებნის შემოწმება
    @Test
    public void testInvalidSearchProduct()  {
        productPage.searchInvalidProduct("ფფ");
        assertElementDisplayed(productPage.checkInValidProduct());
    }
}
