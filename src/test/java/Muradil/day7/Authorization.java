package Muradil.day7;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Authorization {

    String accessToken= "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI= "https://cybertek-reservation-api-qa3.herokuapp.com";
    }


    @Test
    public void getAllCampuses_Using_AccessToken(){

       Response response= given().header("Authorization", accessToken)
                .accept(ContentType.JSON)
                .when().get("api/campuses").prettyPeek();

// using jsonPath print name of room id 111 in light side:


    }
}
