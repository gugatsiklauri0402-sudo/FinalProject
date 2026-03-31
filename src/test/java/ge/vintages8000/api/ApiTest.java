package ge.vintages8000.api;



import ge.vintages8000.utils.ConfigReader;
import io.restassured.response.Response;
import ge.vintages8000.utils.ApiManager;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;



import static org.testng.Assert.*;


public class ApiTest {

    private final ApiManager api = new ApiManager(ConfigReader.get("api.url"));

    // ეს მეთოდი ამზადებს JSON header-ს
    // ვიყენებთ POST / PUT / PATCH request-ებზე
    private Map<String, String> jsonHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    //ამოწმებს რომ /products endpoint მუშაობს
    // და პროდუქტების სია ცარიელი არ არის
    @Test
    public void testGetAllProducts() {
        Response response = api.get("/products", 200);

        assertTrue(response.getContentType().contains("application/json"));

        List<Map<String, Object>> products = response.jsonPath().getList("$");

        assertNotNull(products);
        assertFalse(products.isEmpty(), "Products list is empty");
    }

    // /products endpoint-იდან მხოლოდ title-ებს იღებს
    // და ამოწმებს რომ title-ების სია ცარიელი არ არის
    @Test
    public void testGetAllProductTitles() {

        Response response = api.get("/products", 200);

        List<String> titles = response.jsonPath().getList("title");


        assertNotNull(titles);
        assertFalse(titles.isEmpty(), "Title list is empty");

        // თითოეული title იბეჭდება კონსოლში
        for (String title : titles) {
            System.out.println(title);
        }
    }

    // ამოწმებს limit query parameter-ს
    // მოთხოვნილი limit-ზე მეტი პროდუქტი არ უნდა დაბრუნდეს
    @Test
    public void testGetProductsWithLimit() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("offset", 0);
        queryParams.put("limit", 5);

        Response response = api.get("/products", 200, new HashMap<>(), queryParams);

        List<Map<String, Object>> products = response.jsonPath().getList("$");


        assertNotNull(products);
        assertTrue(products.size() <= 5, "Returned more than 5 products");
    }

    //ქმნის ახალ კატეგორიას
    // name ყოველ გაშვებაზე იცვლება, რომ დუბლირება არ მოხდეს
    @Test
    public void testCreateCategory() {

        Map<String, Object> body = new HashMap<>();
        body.put("name", "New Category By Guga " + UUID.randomUUID());
        body.put("image", "https://placeimg.com/640/480/any");

        Response response = api.post("/categories", 201, jsonHeaders(), body);


        assertNotNull(response.jsonPath().get("id"));
        assertEquals(response.jsonPath().getString("name"), body.get("name"));
    }

    //  ჯერ ქმნის ახალ პროდუქტს
    // შემდეგ იმავე პროდუქტს შლის DELETE request-ით
    @Test
    public void testDeleteProduct() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Delete Product " + UUID.randomUUID());
        body.put("price", 150);
        body.put("description", "Delete Test Description");
        body.put("categoryId", 1);
        body.put("images", Collections.singletonList("https://placehold.co/600x400"));
        Response createResponse = api.post("/products/", 201, jsonHeaders(), body);
        assertNotNull(createResponse.jsonPath().get("id"));
        int productId = createResponse.jsonPath().getInt("id");

        Response deleteResponse = api.delete("/products/" + productId, 200);


    }

    //  ჯერ ქმნის ახალ user-ს
    // შემდეგ იმავე user-ს აახლებს PUT request-ით
    @Test
    public void testUpdateUser() {
        Map<String, Object> createBody = new HashMap<>();
        createBody.put("name", "Test User");
        createBody.put("email", "testuser" + UUID.randomUUID() + "@gmail.com");
        createBody.put("password", "123456");
        createBody.put("role", "customer");
        createBody.put("avatar", "https://picsum.photos/200");

        Response createResponse = api.post("/users/", 201, jsonHeaders(), createBody);

        assertNotNull(createResponse.jsonPath().get("id"));

        int userId = createResponse.jsonPath().getInt("id");

        Map<String, Object> updateBody = new HashMap<>();
        updateBody.put("name", "Updated User");
        updateBody.put("email", "Guga" + UUID.randomUUID() + "@gmail.com");
        updateBody.put("password", "654321");
        updateBody.put("role", "admin");
        updateBody.put("avatar", "https://picsum.photos/300");

        Response updateResponse = api.put("/users/" + userId, 200, jsonHeaders(), updateBody);

        assertEquals(updateResponse.jsonPath().getInt("id"), userId);
        assertEquals(updateResponse.jsonPath().getString("name"), "Updated User","User is not Updated");
        assertEquals(updateResponse.jsonPath().getString("role"), "admin","Role is not Updated");
    }

    //  ამოწმებს email ხელმისაწვდომია თუ არა
    @Test
    public void testCheckUserEmailIsAvailable() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "gugatsiklauri@gmail.com");

        Response response = api.post("/users/is-available", 201, jsonHeaders(), body);

        Boolean isAvailable = response.jsonPath().getBoolean("isAvailable");

        assertNotNull(isAvailable, "isAvailable' should not be null");
    }


    //  არეგისტრირებს ახალ მომხმარებელს
    @Test
    @Parameters({"email", "password"})
    public void testRegisterUser(String  email, String password) {


        Map<String, Object> registerBody = new HashMap<>();
        registerBody.put("name", "Test User");
        registerBody.put("email", email);
        registerBody.put("password", password);
        registerBody.put("avatar", "https://picsum.photos/200");

        Response registerResponse = api.post("/users/", 201, jsonHeaders(), registerBody);


        assertNotNull(registerResponse.jsonPath().get("id"));
    }

    //  login-ს აკეთებს უკვე შექმნილი მომხმარებლით
    // შემდეგ მიღებული bearer token-ით profile endpoint-ს ამოწმებს
    @Test(dependsOnMethods = "testRegisterUser")
    @Parameters({"email", "password"})
    public void testLoginAndGetProfile(String  email, String password) {


        Map<String, Object> loginBody = new HashMap<>();
        loginBody.put("email", email);
        loginBody.put("password", password);

        Response loginResponse = api.post("/auth/login", 201, jsonHeaders(), loginBody);



        String accessToken = loginResponse.jsonPath().getString("access_token");
        assertNotNull(accessToken,"accessToken is null");

        Map<String, String> authHeaders = new HashMap<>();
        authHeaders.put("Authorization", "Bearer " + accessToken);

        Response profileResponse = api.get("/auth/profile", 200, authHeaders);


        assertEquals(profileResponse.jsonPath().getString("email"), email,"email is not set");
    }
}