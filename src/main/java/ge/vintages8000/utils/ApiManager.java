package ge.vintages8000.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;

public class ApiManager {


    public ApiManager(String baseURI) {
        RestAssured.baseURI = baseURI;

    }


    // GET
    public Response get(String endpoint, int statusCode, Map<String, String> headers,
                        Map<String, Object> queryParams, Map<String, Object> pathParams) {

        return given()
                .headers(headers)
                .queryParams(queryParams)
                .pathParams(pathParams)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode)
                .log().all()
                .extract().response();
    }

    // GET
    public Response get(String endpoint, int statusCode) {

        return given()
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode)
                .log().all()
                .extract().response();
    }

    // GET
    public Response get(String endpoint, int statusCode, Map<String, String> headers) {

        return given()
                .headers(headers)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode)
                .log().all()
                .extract().response();
    }

    // GET
    public Response get(String endpoint, int statusCode, Map<String, String> headers,
                        Map<String, Object> queryParams) {

        return given()
                .headers(headers)
                .queryParams(queryParams)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode)
                .log().all()
                .extract().response();
    }

    // POST
    public Response post(String endpoint, int statusCode, Map<String, String> headers, Object body) {

        return given()
                .headers(headers)
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .statusCode(statusCode)
                .log().all()
                .extract().response();
    }

    // PUT
    public Response put(String endpoint, int statusCode, Map<String, String> headers, Object body) {

        return given()
                .headers(headers)
                .body(body)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .statusCode(statusCode)
                .log().all()
                .extract().response();
    }



    // DELETE
    public Response delete(String endpoint, int statusCode) {

        return given()
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .statusCode(statusCode)
                .log().all()
                .extract().response();
    }
}
