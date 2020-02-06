package tests.day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.everyItem;

public class practice {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= "http://54.208.62.52:8000";
    }

    @Test
    public void test(){

        given().log().uri()
                .queryParams("nameContains", "ha")
                .when().get("/api/spartans/search")
                .prettyPeek()
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("content.name", everyItem(containsStringIgnoringCase("ha")));
    }
}
