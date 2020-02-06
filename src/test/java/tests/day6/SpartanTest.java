package tests.day6;

import Utilities.ConfigurationReader;
import Utilities.SpartanApiUtils;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SpartanTest {

        @BeforeAll
        public static void setup(){
                RestAssured.baseURI = ConfigurationReader.getProperty("spartanApiBaseURL");

        }

        @Test
        public void createSpartanTest(){
                Faker faker = new Faker();
                String name = faker.name().firstName();
                String gender = "Female";
                int phone =2022323333;

                Spartan spartan = new Spartan(gender, name, phone);
                System.out.println(spartan);

                Response response = SpartanApiUtils.createSpartan(spartan);

                response.then().statusCode(201);
                response.then().body("data.name", equalTo(spartan.getName()));
        }

        /**
         * create a new spartan
         * then delete spartan
         * verify status code 204
         * call the get spartan api and verify code 404
         */
        @Test
        public void deleteTest(){

//CREATING A SPARTAN:
                // creating the Pojo:
                Spartan spartan = SpartanApiUtils.createSpartanObject();

                // send request to API to create a Spartan:
                Response postResponse = SpartanApiUtils.createSpartan(spartan);
                postResponse.then().statusCode(201);

                int id = postResponse.path("data.id");

//DELETING A SPARTAN:
                given().pathParam("id", id).
                        when().delete("/api/spartans/{id}").
                        then().statusCode(204);

//CONFIRMING IT'S DELETED:
                // call the get spartan api and verify code 404:
                given().pathParam("id", id).
                        when().get("/api/spartans/{id}").
                        then().statusCode(404);
        }

        @Test
        public void Put_Test(){

                // creating the pojo
                Spartan spartan = SpartanApiUtils.createSpartanObject();
                // send request to api to create a spartan
                Response postResponse = SpartanApiUtils.createSpartan(spartan);
                postResponse.then().statusCode(201);

                int id= postResponse.path("data.id");


                // Updating Info:

                //sending new Name
                spartan.setName("Zoom");

                given().contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .pathParam("id", id)
                        .body(spartan)
                        .when().put("/{id}")
                        .prettyPeek()
                        .then().statusCode(204);

                /**verify updated  info by getting the spartan by id
                 * verify name is updated
                 * verify other info is NOT updated
                 */

               given().pathParam("id", id)
                        .accept(ContentType.JSON)
                        .when().get("{id}")
                       .prettyPeek()
                       .then().statusCode(200)
                       .body("name", equalTo(spartan.getName()))
                       .body("gender", equalTo(spartan.getGender()))
                       .body("gender", equalTo(spartan.getPhone()));



        }



}
