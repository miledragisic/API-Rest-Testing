package tests.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import static org.junit.api.Assert.*;





public class FirstRestAssuredTest {

    /**
     * when i send request to http://api.openrates.io/latest
     * then the status must be 200
     */

    @Test
    public void verifyStatusCode(){

        //Response -> response is the result of our request
        // when(). -> need to import 'import static io.restassured.RestAssured.*; '
        // get -> send the request to given url
        Response response= when().get("http://api.openrates.io/latest");
        // prints the response
        response.prettyPrint();
        // verify the status
        // verifies that status matches the provided option
        response.then().statusCode(200);


    }

    /**
     * WHEN I SEND REQUEST TO http://api.openrates.io/latest
     * then body should contain "base": "EUR"
     */

    @Test
    public void verifyBodyContains(){

        Response response= when().get("http://api.openrates.io/latest");

        //asString() ->returns the body as a single String
        String bodyStr= response.asString();
        System.out.println("bodyStr: "+bodyStr);
        assertTrue(bodyStr.contains("\"base\":\"EUR\""));

    }

    /**
     * when i send a request to http://api.openrates.io/latest
     * response should contain header application/json
     */

    @Test
    public void verifyheader(){

        Response response= when().get("http://api.openrates.io/latest");

        //response.header() -> returns the value of the provided header
        String contentType= response.header("Content-Type");
        String date= response.header("date");

        System.out.println("contentTpe= "+contentType);
        System.out.println("date= "+date);

        assertEquals("application/json", contentType);
        assertTrue(date.contains("2020"));

       }
        @Test
        public void verifyContentType(){

            Response response= when().get("http://api.openrates.io/latest");

            //response.contentType() -> returns the content type of the response
            String contentType= response.contentType();
            System.out.println("contentType: "+contentType);

            //response.getStatusCheck -> status code of the response (200 or 400..)
            int statusCode= response.getStatusCode();
            System.out.println("statusCode: "+statusCode);
            assertEquals("application/json", contentType);

            //this line prints and verify Status Code
            // prettyPeek() -> prints HEADERS and BODY and we can keep typing .then().statusCode(200);
            response.prettyPeek().then().statusCode(200).contentType(contentType);
            // prettyPrint() -> prints only BODY but cannot keep typing
            // response.prettyPrint();

// short:

            Response response2= when().get("http://api.openrates.io/latest");

            int statusCod2e= response.getStatusCode();
            String contentType2= response2.contentType();
            response2.prettyPeek().then().statusCode(200).contentType(contentType2);




        }
    /** Task 1.
     *    when i send request to  http://api.zippopotam.us/us/22031
     *    Then the status must be 200
     *    And verify that response contains Fairfax
     *
     */

    /** Task 2.
     *    when i send request to  http://api.zippopotam.us/us/22031111
     *    Then the status must 404
     */

        @Test
        public void statusCodeVerify(){

            Response response= when().get("http://api.zippopotam.us/us/22031");
            //response.prettyPeek().then().statusCode(404);
            response.prettyPeek();
        }

        @Test
        public void viewSomething(){

            Response response= RestAssured.get("http://api.zippopotam.us/us/22031");
            response.body().prettyPeek();




        }


    }
