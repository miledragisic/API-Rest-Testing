package tests.day7;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class Json_Schema_Validator_Test {

    /**
     * THIS WOULD BE GIVEN:
     *
     *  call the company training API
     *  get teacher by id 101
     *  verify that matches the given schema
     *  schema is in resources folder
     *  name: teacher_spartan.json
     */

// no baseURI, we are doing only one test here:
    @Test
    public void test_Schema(){

        given().pathParam("id", 101)
                .when().get("http://api.cybertektraining.com/teacher/{id}").prettyPeek()
                .then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("teacher_tamplet.json"));
    }


}
