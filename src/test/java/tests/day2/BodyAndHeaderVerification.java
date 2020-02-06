package tests.day2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class BodyAndHeaderVerification {



    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.208.62.52:1000/ords/hr/";
    }


    @Test
    public void test1(){

        given().pathParam("id", "101")
                .when().get("/employees/{id}").prettyPeek()
                .then()
                .assertThat().statusCode(200)
                // extract the header value content type and verify
                .and().header("Content-Type", equalTo("application/json"))
                // extract the value of the key first_name and verify
                .and().body("first_name", equalTo("Neena"));


    }


    @Test
    public void test2(){

       JsonPath jsonPath=  given().pathParam("id", "101")
                .when().get("/employees/{id}").jsonPath();
     // Jsonpath -> class used to navigate through Json body and extract the value
     // Jsonpath -> is NOT a String.

        //System.out.println(jsonPath.prettyPrint());


        String first_name= jsonPath.getString("first_name");
        System.out.println("first_name: "+first_name);

        String salary= jsonPath.getString("salary");
        System.out.println("salary: "+salary);

    }




    @Test
    public void test3(){
// get info of the finance dep.


        JsonPath jsonPath= given().pathParam("id", "100")
                                    .when().get("/departments/{id}").jsonPath();

        String manager_id= jsonPath.getString("manager_id");
        System.out.println("manager_id: "+manager_id);

        jsonPath= given().pathParam("id", manager_id)
                            .when().get("/employees/{id}").jsonPath();
        System.out.println(jsonPath.get().toString());


        //assertThat(jsonPath.getString("first_name"), is("Nancy"));

    }
    @Test
    public void test4(){

        JsonPath jsonPath= when().get("/countries/").jsonPath();
        //jsonPath.prettyPrint();
        String allCountries= jsonPath.getString("items.country_name");

//        System.out.println(allCountries.length()); // 248 cause it puts in one single String
//        System.out.println(allCountries);
//
//        List<Object> countryList= jsonPath.getList("items.country_name");
//        System.out.println(countryList);
//        System.out.println(countryList.size());// 25

        List<String> countryList2= jsonPath.getList("items.country_name");
        System.out.println("jsonPath.getList: "+countryList2);

        List<Integer> region_id = jsonPath.getList("items.region_id");



    }




}
