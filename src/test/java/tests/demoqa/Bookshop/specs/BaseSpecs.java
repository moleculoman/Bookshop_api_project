package tests.demoqa.Bookshop.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.*;
import static tests.demoqa.Bookshop.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

public class BaseSpecs {
    public static RequestSpecification authRequestSpec(String TOKEN) {
        return with()
                .filter(withCustomTemplates())
                .log().all()
                .header("Authorization", "Bearer " + TOKEN)
                .log().all()
                .contentType(JSON);
    }
    public static ResponseSpecification responseSpec(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .expectStatusCode(expectedStatusCode)
                .build();
    }

    public static ResponseSpecification bookResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("isbn", not(emptyString()))
                .expectBody("title", not(emptyString()))
                .expectBody("author", not(emptyString()))
                .expectBody("pages", greaterThan(0))
                .expectBody("publish_date", notNullValue())
                .log(ALL)
                .build();
    }

    public static ResponseSpecification bookReplaceResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("userId", not(emptyString()))
                .expectBody("username", not(emptyString()))
                .expectBody("books", not(emptyArray()))
                .expectBody("books[0].isbn", not(emptyString()))
                .expectBody("books[0].title", not(emptyString()))
                .expectBody("books[0].author", not(emptyString()))
                .expectBody("books[0].publish_date", notNullValue())
                .log(ALL)
                .build();
    }
}