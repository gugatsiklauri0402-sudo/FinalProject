package ge.vintages8000.api;


import io.restassured.response.Response;
import ge.vintages8000.utils.ApiManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;



import static org.testng.Assert.*;


public class ApiTest {

    private final ApiManager api = new ApiManager("https://api.escuelajs.co/api/v1");

    private Map<String, String> jsonHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    // ტესტი 1
    // ამოწმებს რომ /products აბრუნებს პროდუქტების სიას
    @Test
    public void getAllProducts() {
        Response response = api.get("/products", 200);

        assertTrue(response.getContentType().contains("application/json"));

        List<Map<String, Object>> products = response.jsonPath().getList("$");

        assertNotNull(products);
        assertFalse(products.isEmpty(), "Products list is empty");
    }

    // ტესტი 2
    // ამოწმებს limit query parameter-ს
    @Test
    public void getProductsWithLimit() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("offset", 0);
        queryParams.put("limit", 5);

        Response response = api.get("/products", 200, new HashMap<>(), queryParams);

        List<Map<String, Object>> products = response.jsonPath().getList("$");

        assertEquals(response.statusCode(), 200);
        assertNotNull(products);
        assertTrue(products.size() <= 5, "Returned more than 5 products");
    }

    // ტესტი 3
    // ეს ტესტი ქმნის ახალ პროდუქტს
    @Test
    public void createCategory() {

        Map<String, Object> body = new HashMap<>();
        body.put("name", "New Category By Guga " + UUID.randomUUID());
        body.put("image", "https://placeimg.com/640/480/any");

        Response response = api.post("/categories", 201, jsonHeaders(), body);

        assertEquals(response.statusCode(), 201);
        assertNotNull(response.jsonPath().get("id"));
        assertEquals(response.jsonPath().getString("name"), body.get("name"));
    }


    // ტესტი 4
    // ამოწმებს პროდუქტის სრულ განახლებას PUT-ით
    // ეს ტესტი ანახლებს ერთ პროდუქტს
    @Test
    public void updateProduct() {

        String updatedTitle = "Updated Product " + UUID.randomUUID();

        Map<String, Object> body = new HashMap<>();
        body.put("title", updatedTitle);
        body.put("price", 200);
        body.put("description", "Updated Description");
        body.put("categoryId", 1);
        body.put("images", Collections.singletonList("https://picsum.photos/300"));

        Response response = api.put("/products/1", 200, jsonHeaders(), body);

        assertEquals(response.statusCode(), 200);
        assertEquals(response.jsonPath().getString("title"), updatedTitle);
    }

    // ტესტი 5
    // ამოწმებს პროდუქტის წაშლას DELETE-ით
    @Test
    public void deleteProduct() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Delete Product " + UUID.randomUUID());
        body.put("price", 150);
        body.put("description", "Delete Test Description");
        body.put("categoryId", 1);
        body.put("images", Collections.singletonList("https://picsum.photos/200"));

        Response createResponse = api.post("/products/", 201, jsonHeaders(), body);

        assertNotNull(createResponse.jsonPath().get("id"));

        int productId = createResponse.jsonPath().getInt("id");

        Response deleteResponse = api.delete("/products/" + productId, 200);

        assertEquals(deleteResponse.statusCode(), 200);
    }



    @Test
    public void updateUser() {
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
        assertEquals(updateResponse.jsonPath().getString("name"), "Updated User");
        assertEquals(updateResponse.jsonPath().getString("role"), "admin");
    }

    // ეს ტესტი ამოწმებს email ხელმისაწვდომია თუ არა
    @Test
    public void checkUserEmailIsAvailable() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "gugatsiklauri@gmail.com");

        Response response = api.post("/users/is-available", 201, jsonHeaders(), body);

        Boolean isAvailable = response.jsonPath().getBoolean("isAvailable");

        assertNotNull(isAvailable);
    }

    @Test
    public void getAllLocationDescriptions() {
        Response response = api.get("/locations", 200);

        List<String> descriptions = response.jsonPath().getList("description");

        assertNotNull(descriptions);
        assertFalse(descriptions.isEmpty(), "Description list is empty");

        for (String description : descriptions) {
            Assert.assertNotNull(description);
            System.out.println(description);
        }
    }



    @Test
    public void registerUser() {

        String email = "gugatsiklauri10@gmail.com";
        String password = "123456";

        Map<String, Object> registerBody = new HashMap<>();
        registerBody.put("name", "Test User");
        registerBody.put("email", email);
        registerBody.put("password", password);
        registerBody.put("avatar", "https://picsum.photos/200");

        Response registerResponse = api.post("/users/", 201, jsonHeaders(), registerBody);

        assertEquals(registerResponse.statusCode(), 201);
        assertNotNull(registerResponse.jsonPath().get("id"));
    }
    @Test
    public void loginAndGetProfile() {

        String email = "gugatsiklauri10@gmail.com";
        String password = "123456";

        Map<String, Object> loginBody = new HashMap<>();
        loginBody.put("email", email);
        loginBody.put("password", password);

        Response loginResponse = api.post("/auth/login", 201, jsonHeaders(), loginBody);

        assertEquals(loginResponse.statusCode(), 201);

        String accessToken = loginResponse.jsonPath().getString("access_token");
        assertNotNull(accessToken);

        Map<String, String> authHeaders = new HashMap<>();
        authHeaders.put("Authorization", "Bearer " + accessToken);

        Response profileResponse = api.get("/auth/profile", 200, authHeaders);

        assertEquals(profileResponse.statusCode(), 200);
        assertEquals(profileResponse.jsonPath().getString("email"), email);
    }



}