package tests.day2;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LogExamples {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.208.62.52:1000/ords/hr/";
    }
    @Test
    public void test1() {
        // request logging
        // log().all(). --> prints everything in the request
        given().log().everything().
                pathParam("id", "7").
                when().get("/regions/{id}").
                then().
                statusCode(200);




//        Response response= given().get("http://54.152.238.45:1000/ords/hr/employee").prettyPeek();


    }               // WHAT IS THE DIFFERENCE BETWEEN THE ABOVE METHOD WITH Response response=.. AND THE TEST 2 ???

    @Test
    public void test2() {
        // response logging
        // log().all(). --> prints everything in the response
        // log().ifError(). --> prints if we get a error status code like 4xx or 5xx
        // ifStatusCodeIsEqualTo(401). --> prints if the status code matches the provided one
        // log().ifValidationFails --> prints if any assertion fails

        given().pathParam("id", "1").
                when().get("/regions/{id}").
                then().log().ifStatusCodeIsEqualTo(200);

    }

    @Test
    public void test3(){
        given().log().everything().
                pathParam("id", "101").
                when().get("/employees/{id}").
                then().log().everything().
                statusCode(200);

    }














}
