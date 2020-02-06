package Muradil.d3;





//import static io.restassured.RestAssured.*;
//import static org.junit.jupiter.api.Assertions.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Day3 {

    @BeforeAll
    public static void setUp(){

// baseURI -> means all END POINTS share this portion "54.152.238.45:1000/ords/hr/"
        RestAssured.baseURI= "http://54.208.62.52:1000/ords/hr/"; // here  RestAssured is a public class and baseURI is a static String in that class
                                                                   //                                  public static String baseURI = "http://localhost";

    }

    @Test
    public void test1() {
// REQUEST

        Response response = when().get("/employees");
        response.then().statusCode(200);
    }

// RESPONSE
        @Test
        public void test2(){
//        given().accept(ContentType.TEXT)// ??? accept JSON (what is the difference) ????
//                                    .and().pathParam("id", "100")
//                                    .when().get("/employees/{id}").then()
//                                    .log().all(); // shows the Headers

            Response response= given().accept(ContentType.HTML)
                    .queryParams("id","100" )
                    .log().everything()
                    .when().get("/employees").prettyPeek();

            String content= response.header("Content-Type");
            Assert.assertEquals("HTML", content);
            System.out.println("content is: "+content);






        //response1.prettyPrint();

    }

    /**
     * PRACTICE
     *
     *
     *
     */
    @Test
    public void test3(){



    }





}
