package tests.day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class JsonPathWith_Maps {

   /*
    get the employee with id 100
    verify last_name is King
    verify salary is 24000
 */

   @Test
    public void employeeInfoTest(){

        JsonPath jsonPath= given().pathParam("id", 100)
                .contentType(ContentType.JSON)
                .when().get("http://54.208.62.52:1000/ords/hr/employees/{id}")
                .jsonPath();

       Map<String, Object> personalInfo= jsonPath.getMap("");

       System.out.println(personalInfo.get("employee_id"));
       System.out.println(personalInfo.get("salary"));
       System.out.println(personalInfo.get("hire_date"));

//       System.out.println(personalInfo.get("links")); // will print all links in one Array

       Map<String, String> map= jsonPath.getMap("links[0]");
       System.out.println(map);
// OR:
       //System.out.println(jsonPath.getMap("links[0]"));
   }

   @Test
   public void metaWeatherMaps(){

            JsonPath jsonPath= given().queryParam("query", "london")
                                .when().get("https://www.metaweather.com/api/location/search").jsonPath();

            Map<String, Object> map= jsonPath.getMap("[0]");
            System.out.println("map: "+map);

            Map<String, Object> expected= new HashMap<>();
            expected.put("title", "London"); // to insert Key and Value this way it would be provided in documentation first
            expected.put("location_type", "City");
            expected.put("woeid", 44418);
            expected.put("latt_long", "51.506321,-0.12714");
            System.out.println("expected: "+expected);

            assertThat(map, equalTo(expected));
//            for(String expectedKey : expected.keySet()){
//                assertThat(map.get(expectedKey), is(expected.get(expectedKey)));
//            }
  }
    /**
     * call the metaweather api with query param = san
     * verify all names contain string 'san'
     */

    @Test
    public void metaWeather_MoreMaps(){

        JsonPath jsonPath= given().queryParam("query", "san")
                .when().get("https://www.metaweather.com/api/location/search").jsonPath();





//        jsonPath.prettyPrint();
//
//        List<Map<String, ? >> list= jsonPath.getList("");
//        System.out.println(list.size());





    }
}
