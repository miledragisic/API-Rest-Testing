package OfficeH.APITesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class SpartanAPiTest300 {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= "http://54.208.62.52:8000/api/";
    }

    /**
     * call the hello endpoing
     * verify 200
     * content t. : application/json;charset=UTF-8
     * body: Hello from Sparta
     */
    @Test
    public void test1(){

            when().get("hello")
                //.then().contentType("application/json;charset=UTF-8")
                .then().header("Content-Type", is("text/plain;charset=UTF-8"))
                .and().statusCode(200)
                .and().body(is("Hello from Sparta"));

    }

    /**
     * Bad headers test:
     * call the hello endPoint
     * header" application/json
     * verify: 406 status code
     */

    @Test
    public void HeaderTest(){

        given().accept(ContentType.TEXT)
                .when().get("/hello")
                .then().contentType(is("application/json"))
                .and().statusCode(406);
    }

    /*
   call the hello endpoint" /api/spartans
       header-->
       accept : application/json
   verify:
       200 status code
       content type: pplication/json;charset=UTF-8
    */
    @Test
    public void ContentTypeJson(){

        given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then().contentType(is("application/json;charset=UTF-8"))
                .and().statusCode(200);
    }
    /*
   call the hello endpoint" /api/spartans
       header-->
       accept : application/xml
   verify:
       200 status code
       content type: application/xml;charset=UTF-8
    */

    @Test
    public void ContentTypeXML(){

        given().accept(ContentType.XML)
                .when().get("/spartans")
                .then().contentType(is(ContentType.XML))
                .and().statusCode(200);
    }

    /*
call the hello endpoint" /api/spartans
    header-->
    accept : application/json
    path param-->
        id : 101
verify:
    200 status code
    body
        "id":       100,
        "name":     "Terence",
        "gender":   "Male",
        "phone":    1311814806
 */

    @Test
    public void IDpathParms(){
        int id= 100;
        int phoneN= 1311814806;

        given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("spartans/{id}")
                .then().statusCode(200)
                .and().body("id", equalTo(100))
                .and().body("name", equalTo("Terence"))
                .and().body("gender", equalTo("Male"))
                .and().body("phone", equalTo(phoneN));


    }
}


