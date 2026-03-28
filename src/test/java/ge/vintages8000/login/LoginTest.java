package ge.vintages8000.login;

import ge.vintages8000.BaseTest;
import org.testng.annotations.BeforeMethod;
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

        // ვაინიციალიზებთ ProductPage-ს მიმდინარე driver-ით
        productPage = new ProductPage(driver);

        // ვაინიციალიზებთ LoginPage-ს
        loginPage = new LoginPage(driver);
    }


    // ტესტი 1: წარმატებული ლოგინი (Valid Login)
    @Test
    public void testValidLogin() {
        loginPage.login("automattt444@gmail.com", "automation123");
        assertElementDisplayed(loginPage.getSignInBtn());
        assertElementDisplayed(productPage.getMyAccountText());
    }


    // ტესტი 2: ლოგაუთი (Sign Out)
    @Test
    public void testLogOut(){
        loginPage.login("automattt444@gmail.com", "automation123");
        assertElementDisplayed(productPage.getMyAccountText());
        productPage.LogOut();
        assertElementDisplayed(loginPage.getSignInText());
    }
}