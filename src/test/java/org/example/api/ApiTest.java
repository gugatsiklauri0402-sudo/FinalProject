package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.utils.ApiManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class ApiTest {

    private final ApiManager api = new ApiManager("https://api.escuelajs.co/api/v1");


    @Test
    public void testGetAllProducts() {


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer simpleTestToken");
        headers.put("Content-Type", "application/json");
        Response response = api.getAllProducts("/products", headers, 200);


        //System.out.println(response.asString());
        //Assert.assertTrue(response.jsonPath().getList("$").size() > 0);


    }

    @Test
    public void testGetProductById() {


        Response response = api.getProductById("/products/{id}", 2,  200);
        Assert.assertEquals(response.getStatusCode(), 200);
        //Assert.assertNotNull(response.jsonPath().getString("title"));
    }

    @Test
    public void getProduct() {
        RestAssured.baseURI = "https://fakeapi.platzi.com/en/about/introduction";
        List <String> titles = given()
                .when()
                .header("Content-Type", "application/json")
                .queryParam("name", 1)
                .get("https://api.escuelajs.co/api/v1/products/")
                .then()
                //.log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("title");
                //.response();
        //System.out.println("Response: " + response.asString());


        for (String title: titles) {
            System.out.println(title);
        }
    }

    @Test
    public void testInvalidProductId() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer simpleTestToken");
        headers.put("Content-Type", "application/json");
        Response response = api.invalidProductId("/products/{id}", 32254, headers);
        Assert.assertEquals(response.statusCode(), 400);
    }


    @Test
    public void testGetCategories() {

        Response response = api.getCategories("/categories");
         response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testCrateUser() {

        Map<String, Object > body = new HashMap<>();
        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");


        body.put("name", "Nicolas");
        body.put("email", "nico@gmail.com");
        body.put("password", "1234");
        body.put("avatar", "https://picsum.photos/800");
        body.put("role", "customer"); // 🔥 ეს გაკლია
        Response response = api.createUser("/users",body, headers);
        Assert.assertEquals(response.statusCode(), 201);
//        String email = response.jsonPath().getString("email");
//        Assert.assertEquals(email, "test@gmail.com");
   }


    @Test
    public void testDeleteProduct(){

        Response response = api.deleteProduct(8);

        Assert.assertEquals(response.statusCode(),200);

    }
    @Test
    public void testGetAuthToken(){
        Map<String, String> headers = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer simpleTestToken");
        body.put("email", "john@mail.com");
        body.put("password", "changeme");
        Response response = api.getAuthToken("auth/login",headers,body);
        Assert.assertEquals(response.statusCode(),201);
    }

    @Test
    public void testUpdateUser(){
        Map<String, String> headers = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Token");
        body.put("name", "Guga Updated");
        body.put("email", "guga@gmail.com");
        body.put("password", "1234");
        body.put("avatar", "https://picsum.photos/800");
        body.put("role", "customer");
        Response response = api.updateUser("/users/1",body, headers);
        Assert.assertEquals(response.statusCode(), 200);
    }
    @Test
    public void testGetProductTitlesAndSlugs() {

        Response response = api.getProductTitlesAndSlugs("/products");
        List<String> titles = response.jsonPath().getList("title");
        //List<String> slugs = response.jsonPath().getList("slug");
int count = titles.size();
//int slugCount = slugs.size();

        for (String title : titles) {
            System.out.println();
            Assert.assertNotNull(title, "Title არის null!");
        }

//        for (String slug : slugs) {
//            Assert.assertNotNull(slug, "Slug არის null!");
//        }
//        System.out.println( slugCount);
        Assert.assertEquals(response.statusCode(), 200);
    }

}
