package tests.Assignments;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

;

public class assignment1 {

/**
 * No params test
 * 1. Send a get request without providing any parameters
 * 2. Verify status code 200, content type application/json; charset=utf-8
 * 3. Verify that name, surname, gender, region fields have value
  */


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= "https://uinames.com/api/";

    }

    @Test
    public void test1(){

        Response response= when().get();

        response.then().statusCode(200).contentType("application/json; charset=utf-8");





    }

}
