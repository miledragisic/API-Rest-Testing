package Muradil.day7;

import Utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Spartan_Delete_Put {

    @BeforeClass
    public static void setUp(){

        RestAssured.baseURI= ConfigurationReader.getProperty("spartan");
    }


    @Test
    public void updateExisting_Spartan_PUT_request_test(){

        Map<String, Object> requestMap= new HashMap<>();
        requestMap.put("name", "Marufjon");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 1231231234L);

        given().contentType(ContentType.JSON)
                .and().body(requestMap)
                .and().pathParam("id", 6 )
                .when().put("/{id}").prettyPeek()
                .then().assertThat().statusCode(204);
    }

}
