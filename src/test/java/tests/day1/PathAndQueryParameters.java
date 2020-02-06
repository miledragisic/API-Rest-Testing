package tests.day1;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PathAndQueryParameters {

    @Test
    public void pathParamTest(){

        Response response= given().pathParam("date", "2020-01-02").
                            when().get("http://api.openrates.io/{date}");

        response.prettyPeek().then().statusCode(200);
        assertTrue(response.asString().contains("2020-01-02"));
    }

    /**
     * 400 message test http://api.openrates.io/{date}
     * given i create request with wrong parameter 2020-30-02
     * when i send my request to
     * then the status code should 400
     */

    @Test
    public void pathParamTestNegative(){

// PATH Parameter: .pathParam(
// Indicated with a colon ( : ) -> http://api.openrates.io/:date
// pass "date"  in {}

        Response response= given().pathParam("date", "2020-30-02").
                        when().get("http://api.openrates.io/{date}");

        //.assertThat():
        //.then().assertThat().statusCode(400) SAME AS .then().statusCode(400);
        response.prettyPeek().then().assertThat().statusCode(400);
        response.prettyPeek().then().statusCode(400);

// short:
        Response response2= given().pathParam("date", "2020-30-02").
                                    when().get("http://api.openrates.io/{date}");
        System.out.println("=============");
        response2.prettyPeek().then().assertThat().statusCode(400);



    }

    /**
     * given i crate request w. query parameter base=USD
     * when i send my request to http://api.openrates.io/latest?base
     * then the response should contain USD
     */

    @Test
    public void QueryParamTest(){
// QUERY Parameter:  .queryParams()
// Indicated with ' ?base=USD '

        Response response2= given().queryParam("base", "USD").
                                    when().get("http://api.openrates.io/latest");
        response2.prettyPeek().then().assertThat().statusCode(200).toString().contains("USD");



    }
    /**
     * given i create request with query parameter base=USD and symbol=MYR
     * when i send my request to http://api.openrates.io/latest?base
     * then the response should contain "base": "USD"
     * and body should contain MYR
     * but should not contain EUR
     */

    @Test
    public void test2queryParams(){

        Response response= given().queryParams("base", "USD").
                                   queryParams("symbols", "MYR").
                                when().get("http://api.openrates.io/latest");

        response.prettyPeek();
        String responseStr= response.asString();
        assertTrue(responseStr.contains("USD") && responseStr.contains("MYR"));
        assertFalse(responseStr.contains("EUR"));
    }


    /**
     * given i create request with query parameter base=USD and symbol=MYR
     * and path parameter date = 2020-01-02
     * when i send my request to http://api.openrates.io/{date}
     * then the response should contain "base": "USD"
     * and body should contain MYR
     * but should not contain EUR
     */

    @Test
    public void testPathAndQueryParam(){

// this request uses both Path and Query Parameters:

        Response response= given().pathParam("date", "2020-01-02")
                                  .queryParam("base", "USD")
                                  .queryParam("symbols", "MYR")
                                  .when().get("http://api.openrates.io/{date}");

        response.prettyPeek();
        String responseStr= response.asString();
        assertTrue(responseStr.contains("2020-01-02") && responseStr.contains("MYR"));


    }


    /**
     * Based on this request, write tests cases.
     * Positive, negative. 2 with 2 different cities at least.
     * In negative, test by passing wrong parameter.
     */
    @Test
    public void city(){

        Response response= given().queryParam("query", "San Francisco")
                                    .when().get("https://www.metaweather.com/api/location/search/");
        response.prettyPeek();

        String contentType= response.header("Content-Type");
        System.out.println("contentType: "+contentType);

        assertTrue(response.asString().contains("San"));
        assertEquals("application/json", contentType);

    }

}
