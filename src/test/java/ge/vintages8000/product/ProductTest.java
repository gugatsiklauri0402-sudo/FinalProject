package ge.vintages8000.product;

import ge.vintages8000.BaseTest;
import ge.vintages8000.login.LoginData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ge.vintages8000.pages.FavoritesPage;
import ge.vintages8000.pages.LoginPage;
import ge.vintages8000.pages.ProductPage;

public class ProductTest extends BaseTest {

    // Page Object-ები — სხვადასხვა გვერდთან სამუშაოდ
    private ProductPage productPage;
    private LoginPage loginPage;
    private FavoritesPage favoritesPage;


    // ეს მეთოდი გაეშვება ყოველი ტესტის წინ
    @BeforeMethod
    public void setUpPages() {
        productPage = new ProductPage(driver);
        loginPage = new LoginPage(driver);
        favoritesPage = new FavoritesPage(driver);
    }


    // ტესტი 1: ფასდაკლების გვერდზე ფილტრის შემოწმება
    @Test
    public void testDiscountPrice() {

        productPage.discountBtn();
        productPage.priceSliders("50", "80");
        assertUrl(productPage.getCurrentUrl(), "https://8000vintages.ge/sale/?features_hash=1-50-80-GEL"
        );
    }


    // ტესტი 2: პროდუქტის ძებნა
    @Test
    public void testSearchProduct() {
        productPage.searchProduct("ქისი");
        assertElementDisplayed(productPage.kisiWineTitle());
    }


    // ტესტი 3: პროდუქტის დამატება Favorites-ში
    @Test(dataProvider = "loginData", dataProviderClass = LoginData.class)
    @Parameters({"email", "password"})
    public void testAddFavoriteProduct(String email, String password) {
        loginPage.login(email, password);
        productPage.addToFavoriteProduct();
        assertUrl(favoritesPage.getCurrentUrl(), "https://8000vintages.ge/wishlist-view/"
        );
    }


    // ტესტი 4: პროდუქტის წაშლა Favorites-იდან
    //რომელ კლასში მოძებნოს ეს loginData
    @Test(dataProvider = "loginData", dataProviderClass = LoginData.class)
    @Parameters({"email", "password"})
    public void testRemoveFavoriteProduct(String email, String password) {
        loginPage.login(email, password);
        productPage.removeFromFavoriteProduct();
        favoritesPage.getRemoveFromWishBtn();
        assertElementDisplayed(favoritesPage.getCheckEmptyWishList());
    }


    // ტესტი 5: არასწორი (invalid) ძებნის შემოწმება
    @Test
    public void testInvalidSearchProduct() {
        productPage.searchInvalidProduct("ფფ");
        assertElementDisplayed(productPage.checkInValidProduct());
    }
}
