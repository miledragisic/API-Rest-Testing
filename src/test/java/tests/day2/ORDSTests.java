package tests.day2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

// http://54.152.238.45:1000/ords/hr/

public class ORDSTests {

    /**
     * get employee from employees table with id 100 (use path parameters)
     *     Verify response contains King
     *         response contains 100
     *         status code 200
     *         header application/json
     */
    @Test
    public void test1(){

        Response response= given().pathParam("id", "100")
                                    .when().get("http://54.208.62.52:1000/ords/hr/employees/{id}");

        response.prettyPeek().then().assertThat().statusCode(200).toString().contains("King");
        response.asString().contains("100");
        String content= response.header("Content-Type");
        assertEquals(content, "application/json");

    }


    /**
     * get employee from countries table with id AR (use path parameters)
     *     Verify response contains Argentina
     *         response contains AR
     *         status code 200
     *         header application/json
     */

    @Test
    public void test2(){

        Response response= given().pathParam("id", "AR")
                                    .when().get("http://54.208.62.52:1000/ords/hr/countries/{id}");

        response.prettyPeek().then().statusCode(200).toString().contains("AR");
        assertTrue(response.asString().contains("AR"));
        assertEquals(response.header("Content-Type"), "application/json");
        response.contentType().contains("application/json");

        System.out.println("content get: "+response.getContentType());
        System.out.println("content 2: "+response.contentType());
        System.out.println("header "+response.header("Content-Type"));



    }

    /**
     * get employee from departments table with id 2000 (use path parameters)
     *     Verify response contains Argentina
     *         response contains AR
     *         status code 404
     *         header text/html
     */
            @Test
        public void test3(){

                baseURI = "http://54.208.62.52:1000/ords/hr/";

                Response response= RestAssured.given().pathParam("id", "2000")
                        .when().get("/departments/{id}");

//                Response response= given().pathParam("id", "2000")
//                        .when().get(url)


//               RestAssured.given().accept(ContentType.TEXT)
//                                                        .when().get("http://api.openrates.io/latest")
//                                                        .then().statusCode(200)
//                                                        .and().contentType("application/json");

                System.out.println("content get: "+response.getContentType());
                System.out.println("content 2: "+response.contentType());
                System.out.println("header "+response.header("Content-Type"));




//                String header= response.header("Content-Type");
//                assertEquals(header, "text/html");
//                response.then().statusCode(404).toString().contains("AR");
            }
//////////////////////////////////////////////////////////////////////////////////////////
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
    public void test11(){

        Response response= when().get();

        JsonPath jsonPath= response.jsonPath();

        response.then().statusCode(200).contentType("application/json; charset=utf-8");
        assertNotNull(jsonPath.getString("name"));
        assertNotNull(jsonPath.getString("surname"));
        assertNotNull(jsonPath.getString("gender"));
        assertNotNull(jsonPath.getString("region"));
    }
/**
 * Gender test
 * 1. Create a request by providing query parameter: gender, male or female
 * 2. Verify status code 200, content type application/json; charset=utf-8
 * 3. Verify that value of gender field is same from step 1
 */
    @Test
    public void test12Male(){

        Response response= when().get();

        JsonPath jsonPath= response.jsonPath();

        response= given().queryParam("gender", "male")
                            .when().get();

        response.prettyPeek();
        assertTrue(jsonPath.getString("gender").contains("male"));
        response.then().statusCode(200).contentType("type application/json; charset=utf-8");
    }

    @Test
    public void test12Female(){

        Response response2= given().queryParam("gender", "female")
                                    .when().get();

        JsonPath jsonPath2= response2.jsonPath();
        
        response2.prettyPeek();
//        assertTrue(jsonPath2.getString("gender").contains("female"));
        assertEquals(jsonPath2.getString("gender"), ("female"));
        response2.then().statusCode(200).contentType("application/json; charset=utf-8");
    }

    /**
     * 2 params test
     * 1. Create a request by providing query parameters:
     * a valid region and gender NOTE: Available region values are given in the documentation
     * 2. Verify status code 200, content type application/json; charset=utf-8
     * 3. Verify that value of gender field is same from step 1
     * 4. Verify that value of region field is same from step 1
     */

    @Test
    public void test13(){

        Response response = given().queryParam("region", "Germany")
                            .when().get();

        JsonPath jsonPath= response.jsonPath();

        response.then().statusCode(200).contentType("application/json; charset=utf-8");
        //assertTrue(jsonPath.getString("gender").contains("female"));
        assertEquals(jsonPath.getString("region"),"Germany");


        response.prettyPeek();





    }







}
