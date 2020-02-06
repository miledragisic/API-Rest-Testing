package tests.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class JsonPathTests {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= "http://54.208.62.52:1000/ords/hr/";

    }


    @Test
    public void testJsonPath_List(){

        JsonPath jsonPath= get("http://54.152.238.45:1000/ords/hr/countries").jsonPath();

        List<String> countryID= jsonPath.getList("items.findAll{it.country_id==2}.country_name");

        System.out.println("countryID= "+countryID);           //     items.find{it.employee_id==103}.first_name
    }
    /**
     * given url "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions/{id}"
     * when user makes get request with path param id=1
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe
     */

    @Test
    public void test1(){
// all in one line:
        given().pathParam("id", "1")
                            .when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions/{id}")
                            .then().assertThat().statusCode(200)
                            .and().assertThat().body("region_id", equalTo(1))
                            .and().assertThat().body("region_name", equalTo("Europe"));


    }

    @Test
    public  void test2(){
// separate lines:
        Response response= given().pathParam("id", "1")
                .when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions/{id}");
        JsonPath jsonPath= response.jsonPath();

        response.statusCode();
        response.prettyPeek();

        String id= jsonPath.getString("region_id");
        assertThat(id, equalTo("1"));
        String name= jsonPath.getString("region_name");
        assertThat(name, equalTo("Europe"));

    }

    /**
     * given url "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions/{id}"
     * when user makes get request with path param id=1
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe
     */

    @Test
    public  void test3(){

        given().contentType(ContentType.JSON) // accept type in JSON
                .pathParam("id", 100) // request with pathParam id= 100
                .when().get("/employees/{id}") // when user makes a GET request
                .then().statusCode(200)
                .and().assertThat().body("last_name", is("King"));

    }
    /**
     * given url "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions/{id}"
     * when user makes get request with path param id=1
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe
     */
    @Test
    public void test4(){
// getting one  href (link)
        Response response= given().contentType(ContentType.JSON)
                //.pathParam("id", 100)
                //.when().get("/employees/{id}");
                    .get("/employees");



        JsonPath jsonPath= response.jsonPath();
        String link= jsonPath.getString("links.href[0]"); // in Json file, find key links then finds its children href and get the first one
        System.out.println("link: "+link);                     // it uses . as a path (it goes from links then (.) to the first href[0]
                                                               // or could go to [1]or[2]
    }

    @Test
    public void test5(){

        Response response= given().contentType(ContentType.JSON)
                .when().get("/employees");

        JsonPath jsonPath= response.jsonPath();
        String firstLN= jsonPath.getString("items.last_name[0]");
        String firstS= jsonPath.getString("items.salary[0]");

        String lastLN= jsonPath.getString("items.last_name[-1]");
        String lastS= jsonPath.getString("items.salary[-1]");

        System.out.println("firstLN: "+firstLN);
        System.out.println("firstS: "+firstS);


        System.out.println("lastLN: "+lastLN);
        System.out.println("lastS = " + lastS);

    }

    /**
     * verify first_name of the employee with employee id= 100  is equal Lex
     */
    @Test
    public void test6(){


        Response response= given().contentType(ContentType.JSON)
                .when().get("/employees");

        JsonPath jsonPath= response.jsonPath();

        String maruf= jsonPath.getString("items.find{it.employee_id==103}.first_name");
        System.out.println("maruf: "+maruf);
    }

    @Test
    public void pojo(){

        Response response= given().pathParam("region_id", 10).contentType(ContentType.JSON)
                .when().get("/Regions/{region_id}");

    }

    @Test
// LIST :
    public void test7(){

        Response response= given().contentType(ContentType.JSON)
                .when().get("/countries");

        List<String> actualList= response.jsonPath().getList("items.country_name");
        System.out.println("actualList = " + actualList);

        List<String> expected= Arrays.asList(   "Argentina", "Australia", "Belgium", "Brazil", "Canada",
                                                "Switzerland", "China", "Germany", "Denmark", "Egypt",
                                                "France", "Israel", "India", "Italy", "Japan", "Kuwait",
                                                "Malaysia", "Mexico", "Nigeria", "Netherlands", "Singapore",
                                                "United Kingdom", "United States of America", "Zambia", "Zimbabwe");

        List<String> expected2= Arrays.asList(   "Argentina", "Australia", "Belgium", "Brazil", "Canada");
        assertThat(actualList, hasItems("Argentina", "Australia", "Belgium", "Brazil", "Canada"));
// TODO for loop
        for(String each : actualList){

        }
    }


    /**
     * given type Json
     * when user sends
     */
    @Test
    public void test8(){

        Response response= when().get("/employees");
        response.then().statusCode(200);

// RestAssuer will convert for us: we are sending
        List<Integer> list= response.jsonPath().getList("items.salary", Integer.class); // we are requesting the return type to be Integer
        System.out.println("list = " + list);

        // means: make sure that every item in the list (each salary) is bigger then 1000
        assertThat(list, everyItem(greaterThan(1000)));

    }

}
