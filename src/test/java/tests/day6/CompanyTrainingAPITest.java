package tests.day6;

import Utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.Address;
import pojo.Company;
import pojo.Contact;
import pojo.Student;

import static io.restassured.RestAssured.given;

public class CompanyTrainingAPITest {

    @BeforeClass
    public static void setUp(){

        RestAssured.baseURI= ConfigurationReader.getProperty("companyAPiBaseURL");
    }

    /**
     * post a new student using: /student/create
     *
     * verify success message
     */
    @Test
    public void postAStudentTest(){

        //create a student Pojo with all required Info.
        Address address= new Address("123 Some st", "Arlington", "VA", 22221);

        Company company= new Company("Some Solutions", "Vice President", "01/26/2019", address);

        Contact contact= new Contact( "someVP@gmail.com", "2021111111", "123 Same St, Arlington, VA, 22221");

        Student student= new Student("John", "Malkovich", 1, "12/26/2009",
                "12/12/1990", "123456","Java", "Male", "324324", "Programing", "39", contact, company);


        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(student)
                .when().post("student/create")
                .prettyPeek()
                .then().statusCode(200);



    }

}
