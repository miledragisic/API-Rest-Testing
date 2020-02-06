package Muradil.d4;

import Utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HRAPiTestsWithParameters {

    //@BeforeAll
    public static void setUp(){

 // set base URI that is defined in properties file
        RestAssured.baseURI= ConfigurationReader.getProperty("qa3URI");

    }

    /**
     * given accept type is Json
     * and parameters: q = "region_id=2"
     * when users send a GET request to "/countries"
     * then status code is 200
     * and content type is application/json
     * and paylaod should contain "United States of America"
     */


// response.path();

    @Test
    public void test(){

        RestAssured.baseURI= "https://uinames.com/api/";

        Response response= given().queryParam("region", "germany")
                                    .when().get();

        String str= response.body().path("name");
        String str2= response.path("gender");       // dont have to include ' .body()

        System.out.println("str: "+str);
        System.out.println("str2: "+str2);

//// Works with JsonPath as well:
//        JsonPath jsonPath= response.jsonPath();
//        String json= jsonPath.getString("name");
//        System.out.println("json: " +json);



    }

    @Test
    public void getCountries_Using_QueryParameter(){

        Response response= given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPeek();

    }

    @Test
    public void pathMethod(){

        Response response= when().get("http://54.208.62.52:1000/ords/hr/employees");

        response.prettyPeek();
        String item= response.path("items");
        //System.out.println("response: "+response);




    }














}
