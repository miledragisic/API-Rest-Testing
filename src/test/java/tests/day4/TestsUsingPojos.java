package tests.day4;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Employee;
import pojo.Job;

import java.util.List;

import static io.restassured.RestAssured.*;

public class TestsUsingPojos {

    @BeforeAll
    public static void setUp(){

        baseURI= "http://54.208.62.52:1000/ords/hr";
    }

    // one Object /
    @Test
    public void getASingle(){

        Response response= given().pathParam("id", "IT_PROG")
                                    .when().get("/jobs/{id}");

        response.then().statusCode(200);
        response.prettyPrint();

        Job itJob= response.as(Job.class);
        System.out.println("itJob: "+itJob);

        System.out.println("==========================");
        System.out.println("itJob.getJob_title()= "+itJob.getJob_title());
        System.out.println("itJob.getJob_it = " + itJob.getJob_id());
    }

    // many Objects / many JOBS:
    @Test
    public void test2(){

        Response response= when().get("/jobs");

        List<Job> jobs= response.jsonPath().getList("items", Job.class);// it works without Job.class
        System.out.println("jobs: "+jobs);

    }

    @Test
    public void getOneEmployee(){

        Response response= given().pathParam("id", "102")
                            .when().get("employees/{id}");

        Employee employee= response.as(Employee.class);
        System.out.println("employee: "+employee);

    }
}
