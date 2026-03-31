package ge.vintages8000.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;

    // ეს არის ApiManager კლასი
// ამ კლასში წერია ყველა ძირითადი HTTP მეთოდი:
// GET, POST, PUT, DELETE
    public class ApiManager {

        // კონსტრუქტორი
        // აქ ვაწერთ base URL-ს, რომ ყველა request ამ მისამართზე წავიდეს
        public ApiManager(String baseURI) {
            RestAssured.baseURI = baseURI;
        }

        // GET მეთოდი
        // ეს ვერსია გამოიყენება მაშინ, როცა მხოლოდ endpoint და status code გვჭირდება
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

        // GET მეთოდი
        // ეს ვერსია გამოიყენება მაშინ, როცა header-ებიც გვინდა
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

        // GET მეთოდი
        // ეს ვერსია გამოიყენება მაშინ, როცა header-ებთან ერთად query parameter-ებიც გვჭირდება
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

        // POST მეთოდი
        // გამოიყენება ახალი მონაცემის შესაქმნელად
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

        // PUT მეთოდი
        // გამოიყენება არსებული მონაცემის სრულად განახლებისთვის
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

        // DELETE მეთოდი
        // გამოიყენება მონაცემის წასაშლელად
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

