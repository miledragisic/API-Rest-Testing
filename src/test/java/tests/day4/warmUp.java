package tests.day4;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class warmUp {

    /**
     * WARMUP
     * when user makes get request to "/employees" in hr ORDS-HR api
     * then user verifies that status code is 200
     * and  user verifies that average salary is greater that 5000
     */

    @Test
    public void warmUp(){

        Response response= when().get("http://54.208.62.52:1000/ords/hr/employees");
        response.then().statusCode(200);
        List<Integer> salaryList= response.jsonPath().getList("items.salary");
// how to get the average from a list of ints:
        int total= 0;
        for(Integer each : salaryList)   {
            total= total + each;
        }
        int average= total/salaryList.size();
        assertThat(average, greaterThan(5000));

    }

}
