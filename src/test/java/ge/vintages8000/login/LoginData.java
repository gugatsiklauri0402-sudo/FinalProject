package ge.vintages8000.login;

import ge.vintages8000.BaseTest;
import org.testng.annotations.DataProvider;

public class LoginData extends BaseTest {


    @DataProvider(name = "loginData")
    public static Object[][] loginData(){
        return new Object[][]{
                {"automattt444@gmail.com", "automation123"},
                {"gugatsiklauri0402@gmail.com", "gugaguga123"}
        };
    }
}
