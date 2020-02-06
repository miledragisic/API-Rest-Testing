package Muradil.d6_Gson_Serialization_and_Post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class JsonToCollection {

    /**
     * HR API Employee jsonData to Java Object
     */
    @Test
    public void test1(){

        Response response= given().accept(ContentType.JSON)
                                    .pathParam("employee_id", 105)
                                    .when().get("http://54.208.62.52:1000/ords/hr/employees/{employee_id}");

        Map<String, Object> employeeMap= response.body().as(Map.class);

        //System.out.println(employeeMap.toString());

        double salary= (double)employeeMap.get("salary");

    }

    @Test
    public void test2(){
//TODO fix it:
        Response response= given().accept(ContentType.JSON)
                .when().get("http://54.208.62.52:1000/ords/hr/employees");

//        List<Map<String, ?>> jsonListOfMap= response.body().as(List.class);
//
//        System.out.println(jsonListOfMap.get(0));
    }

}
