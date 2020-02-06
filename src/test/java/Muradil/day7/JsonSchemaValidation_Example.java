package Muradil.day7;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidation_Example {

    @Test
    public void jsonSchemaValidationOfSingleSpartan(){

        given().accept(ContentType.JSON)
                .pathParam("id", 101)
                .when().get("http://54.208.62.52:8000/api/spartans/{id}").prettyPeek()
                .then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("singleSpartanSchema.json"));
    }

    @Test
    public void JsonSchemaValidation(){

        given().accept(ContentType.JSON)
                .pathParam("id", 101)
                .when().get("http://54.208.62.52:8000/api/spartans/{id}").prettyPeek()
                .then().statusCode(200)
                .and().body(matchesJsonSchemaInClasspath("mileSpartan.json"));
    }

    ///Users/Martiandj/Desktop/path/singleSpartanSchema.json
    @Test
    public void From_Desktop_jsonSchemaValidationOfSingleSpartan(){

        given().accept(ContentType.JSON)
                .pathParam("id", 101)
                .when().get("http://54.208.62.52:8000/api/spartans/{id}").prettyPeek()
                .then().statusCode(200)
                .body(matchesJsonSchema(new File("/Users/Martiandj/Desktop/path/singleSpartanSchema.json")));

    }
}
