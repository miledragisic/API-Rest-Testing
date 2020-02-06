package tests.day7;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Api_KeyTest {

    /**
     * test 401 status code
     *
     */

    @Test
    public void noKey(){

        given().queryParams("t", "A star is born")
                .when().get("http://www.omdbapi.com").prettyPeek()
                .then().statusCode(401)
                .body("Error", equalTo("No API key provided."));

    }

    /**
     * call the http://www.omdbapi.com
     * with a valid api key
     */

    @Test
    public void withKey(){

        given().queryParams("apikey", "e0484f01")
                .queryParams("t", "Goonies")
                .when().get("http://www.omdbapi.com")
                .prettyPeek()
                .then().statusCode(200)
                .body("Title",equalTo( "The Goonies"))
                .body("Year", equalTo("1985"));



    }
}
