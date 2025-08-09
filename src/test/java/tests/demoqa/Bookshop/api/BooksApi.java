package tests.demoqa.Bookshop.api;

import org.openqa.selenium.json.Json;
import tests.demoqa.Bookshop.models.*;
import tests.demoqa.Bookshop.tests.TestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static tests.demoqa.Bookshop.specs.BaseSpecs.authRequestSpec;
import static tests.demoqa.Bookshop.specs.BaseSpecs.responseSpec;
import static tests.demoqa.Bookshop.tests.TestBase.ALL_BOOKS_END_POINT;
import static tests.demoqa.Bookshop.tests.TestBase.SINGLE_BOOK_END_POINT;


public class BooksApi extends TestData {

    public static void deleteAllBooks() {
        given(authRequestSpec(TOKEN))
                .when()
                .delete(ALL_BOOKS_END_POINT + "?UserId=" + userId)
                .then()
                .spec(responseSpec(204));
    }

    public static void addBook(AddBooksRequestModel addBookData) {
        given(authRequestSpec(TOKEN))
                .body(addBookData)
                .when()
                .post(ALL_BOOKS_END_POINT)
                .then()
                .spec(responseSpec(201));
    }

    public static void deleteBook(DeleteBooksRequestModel deleteBookData) {
        given(authRequestSpec(TOKEN))
                .body(deleteBookData)
                .when()
                .delete(SINGLE_BOOK_END_POINT)
                .then()
                .spec(responseSpec(204));
    }

    public static void replaceBook(String oldIsbn, String newIsbn) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", userId);
        requestBody.put("isbn", newIsbn);
        String endpoint = ALL_BOOKS_END_POINT + "/" + oldIsbn;

        given(authRequestSpec(TOKEN))
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .spec(responseSpec(200));
    }

}