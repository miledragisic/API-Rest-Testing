package Muradil.d6_Gson_Serialization_and_Post;

import Muradil.pojo2.Spartan2;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * Given accept type and Content type is JSON
 * And request json body is:
 * {"gender":"Male",
 * "name":"Maximus",
 * "phone":8877445596}
 * When user sends POST request to '/spartans/'
 * Then status code 201
 * And content type should be application/json
 * And json payload/response should contain:
 * "A Spartan is Born!" message
 * and same data what is posted
 */
public class post_Actions {

    @Test
    public void post_new_spartan(){

// this type of body() is NOT good

        Response response= given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("{\"gender\":\"Male\",\n" +
                        " \"name\":\"Lemi\",\n" +
                        " \"phone\":1234567891}")
                .when().post("http://54.208.62.52:8000/api/spartans/");

        // RESPONSE  VALIDATION:

        assertEquals(201, response.statusCode());
        assertEquals("application/json",  response.contentType());

        //EXTRACT MESSAGE USING PATH METHOD:
        String message1= response.path("success");

        //EXTRACT MESSAGE USING JSON PATH:
        JsonPath jsonPath= response.jsonPath();
        String message2= jsonPath.getString("success");

        System.out.println("message1: "+message1);
        System.out.println("message2: "+message2);

        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals("300", jsonPath.getString("data.id"));

    }

    @Test
    public void post_new_spartan_with_Map(){

// Creating a MAP is GOOD way for the BODY()

        Map<String, Object> requestMap= new HashMap<>();
        requestMap.put("gender", "Male");
        requestMap.put("name", "Bond");
        requestMap.put("phone", "7777777777");



        Response response= given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().post("http://54.208.62.52:8000/api/spartans/");

        // RESPONSE  VALIDATION:

        assertEquals(201, response.statusCode());
        assertEquals("application/json",  response.contentType());

        //EXTRACT MESSAGE USING PATH METHOD:
        String message1= response.path("success");

        //EXTRACT MESSAGE USING JSON PATH:
        JsonPath jsonPath= response.jsonPath();
        String message2= jsonPath.getString("success");

        System.out.println("message1: "+message1);
        System.out.println("message2: "+message2);

        assertEquals("Male", jsonPath.getString("data.gender"));
    }

    @Test
    public void create_new_Spartan_with_Object(){

        // create a spartan object to be used as a body for post request

        Spartan2 spartan2= new Spartan2();

        spartan2.setGender("Male");
        spartan2.setName("Zizu");
        spartan2.setPhone(1010101010);

        Response response= given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan2)
                .when().post("http://54.208.62.52:8000/api/spartans/").prettyPeek();

        // RESPONSE  VALIDATION:

        assertEquals(201, response.statusCode());
        assertEquals("application/json",  response.contentType());

        //EXTRACT MESSAGE USING PATH METHOD:
        String message1= response.path("success");

        //EXTRACT MESSAGE USING JSON PATH:
        JsonPath jsonPath= response.jsonPath();
        String message2= jsonPath.getString("success");

        System.out.println("message1: "+message1);
        System.out.println("message2: "+message2);

        assertEquals("Male", jsonPath.getString("data.gender"));

    }

    @Test
    public void verify_Zizu() {

        Response response = given().queryParams("nameContain", "Zizu")
                .queryParams("gender", "Male")
                .queryParams("id", 109)
                .when().get("http://54.208.62.52:8000/api/spartans/search/");//.prettyPeek();


       JsonPath jsonPath= response.jsonPath();
       String str= jsonPath.get("content.find{it.id==109}name");
       assertEquals("Zizu", str);
    }

    public static String initCap(String word) {
        return word.substring(0,1).toUpperCase()+word.substring(1);
    }

    /**
     * send get request to https://uinames.com/api/ and extract name and gender
     * {"name":"Danica","surname":"Basa","gender":"female","region":"Slovakia"}
     */
    @Test
    public void extract_name_and_gender(){


        JsonPath jsonPath= get("https://uinames.com/api/").jsonPath();

        Spartan2 spartan2= new Spartan2();
        spartan2.setGender(initCap(jsonPath.getString("gender")));
        spartan2.setName(jsonPath.getString("name")+" "+jsonPath.getString("surname"));
        spartan2.setPhone(1010101010);

        Response response= given().accept(ContentType.JSON)
                                    .and().contentType(ContentType.JSON)
                                    .and().body(spartan2)
                                    .when().post("http://54.208.62.52:8000/api/spartans/").prettyPeek();
    }


}












