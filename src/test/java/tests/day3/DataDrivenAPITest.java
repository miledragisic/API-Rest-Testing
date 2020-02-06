package tests.day3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DataDrivenAPITest {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= "http://54.208.62.52:1000/ords/hr/";

    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4}) // only one value at the time
    public void validateRegionNameTest1(int id){

        given().pathParam("id", id)
                .when().get("/region/{id}")
                .prettyPeek()
                .then().assertThat().body("region_id", equalTo(id));

               // @CsvSource() multiple values

    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4}) // only one value at the time
    public void validateRegionNameTest2(int id){

        given().pathParam("id", id)
                .when().get("/region/{id}")
                .prettyPeek()
                .then().assertThat().body("region_id", equalTo(id));

        // @CsvSource() multiple values

    }

    @ParameterizedTest
    @CsvSource({ // multiple values
            "1, Europe",
            "2, Americas",
            "3, Asia",
            "4, Middle East and Africa"})
    public void validateRegionNameTest2(int id, String name){

        given().pathParam("id", id)
                .when().get("/regions/{id}")
                .prettyPeek()
                .then().assertThat().body("region_id", equalTo(id))
                .and().assertThat().body("region_name", equalTo(name));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv")
    public void validateRegionNameTest3(int id, String name){

        given().pathParam("id", id)
                .when().get("/regions/{id}")
                .prettyPeek()
                .then().assertThat().body("region_id", equalTo(id))
                .and().assertThat().body("region_name", equalTo(name));
    }



}
