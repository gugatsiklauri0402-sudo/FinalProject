package org.example.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiManager {


    public ApiManager(String baseURI) {
        RestAssured.baseURI = baseURI;

    }


    public Response getAllProducts(String endpoint, Map<String, String> headers, int statusCode) {

        return given()
                .headers(headers)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode)
                .log().all()
                .extract()
                .response();

    }

    public Response getProductById(String endpoint, int productId, int statusCode) {
        return given()
                .pathParam("id", productId)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode)
                //.log().all()
                .extract()
                .response();
    }

    public Response invalidProductId(String endpoint, int productId, Map<String, String> headers) {
        return given()
                .headers(headers)
                .log().all()
                .pathParam("id", productId)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().
                response();
    }

    public Response getCategories(String endpoint) {

        return given()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response()
                .prettyPeek();


    }

    public Response createUser(String endpoint, Map<String, Object> body, Map<String, String> headers) {

        return given()
                .headers(headers)
                .log().all()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response deleteProduct(int id) {

        return given()
                .pathParam("id", id)
                .when()
                .delete("/products/{id}")
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response getAuthToken(String endpoint, Map<String, String> headers, Map<String, Object> body) {
        return given()
                .headers(headers)
                .log().all()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response updateUser(String endpoint, Map<String, Object> body, Map<String, String> headers) {
        return given()
                .headers(headers)
                .log().all()
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();

    }

    public Response getProductTitlesAndSlugs(String endpoint){
        return given()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .path("title");
    }
}