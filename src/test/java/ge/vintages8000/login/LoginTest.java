package ge.vintages8000.login;

import ge.vintages8000.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ge.vintages8000.pages.LoginPage;
import ge.vintages8000.pages.ProductPage;


public class LoginTest extends BaseTest {

    // Page Object-ები — სხვადასხვა გვერდთან სამუშაოდ
    private ProductPage productPage;
    private LoginPage loginPage;


    // ეს მეთოდი გაეშვება ყოველი ტესტის წინ
    @BeforeMethod
    public void setUpPages() {
        productPage = new ProductPage(driver);
        loginPage = new LoginPage(driver);
    }


    // ტესტი 1: წარმატებული ლოგინი (Valid Login)
    @Test(dataProvider = "loginData", dataProviderClass = LoginData.class)
    @Parameters({"email", "password"})
    public void testValidLogin(String email, String password) {
        loginPage.login(email, password);
        assertElementDisplayed(loginPage.getSignInBtn());
        assertElementDisplayed(productPage.getMyAccountText());
    }


    // ტესტი 2: ლოგაუთი (Sign Out)
    @Test(dataProvider = "loginData", dataProviderClass = LoginData.class)
    @Parameters({"email", "password"})
    public void testLogOut(String email, String password) {
        loginPage.login(email, password);
        assertElementDisplayed(productPage.getMyAccountText());
        productPage.LogOut();
        assertElementDisplayed(loginPage.getSignInText());
    }
}