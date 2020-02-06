package Muradil.d4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Day4 {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= "https://uinames.com/api/";
    }


    /**
     *  Given accept type is Json
     *  And query parameter values are:
     *  gender | female
     *  name contains | a
     *
     *  * Gender test
     *  * 1. Create a request by providing query parameter: gender=female response payload, name=Anca
     *  * 2. Verify status code 200, content type application/json; charset=utf-8
     *  * 3. Verify that value of gender field is same from step 1
     *  */

    @Test
    public void test1(){

        Response response= given().queryParam("gender", "female")
                                    .when().get();

        JsonPath jsonPath= response.jsonPath();

        assertEquals(jsonPath.getString("gender"), "female");

        response.then().statusCode(200).contentType("application/json; charset=utf-8");

        response.prettyPeek();
    }



    @Test
    public void test2(){

        Response response1= given().accept(ContentType.XML)
                            .queryParam("gender", "female")
                            .get();

        Map<String, Object> paramsMap= new HashMap<>();
        paramsMap.put("gender", "female");
        assertEquals(200, response1.statusCode());
    }

























}
