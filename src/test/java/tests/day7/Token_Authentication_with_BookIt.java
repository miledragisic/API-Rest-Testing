package tests.day7;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Token_Authentication_with_BookIt {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI= "https://cybertek-reservation-api-qa3.herokuapp.com/";
    }
// SIGNING IN TO GET THE TOKEN:
    @Test
    public void test(){

        given().queryParams("email", "emaynell8f@google.es")
                .queryParams("password", "besslebond")
                .when().get("/sign").prettyPeek();
    }

// PROVIDING TOKEN TO GET ALL THE CAMPUSES>> Authorization -> Bearer +token
    @Test
    public void test2(){

        String token= "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";
        Response response= given().header("Authorization", token)
                .when().get("api/campuses").prettyPeek();

        JsonPath jsonPath= response.jsonPath();
        
        String roomName= jsonPath.getString("clusters[0].rooms[0].name[0]");
        String capacity= jsonPath.getString("clusters[0].rooms[0].find{it.name=='mit'}.id");
        System.out.println("roomName = " + roomName);
        System.out.println("capacity = " + capacity);

    }


}









