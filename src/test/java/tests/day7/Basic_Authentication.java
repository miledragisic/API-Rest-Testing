package tests.day7;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Basic_Authentication {

    /**
     * https://the-internet.herokuapp.com/basic_auth
     *
     * admin/admin
     */
    @Test
    public void preemtive(){

        given().auth().preemptive().basic("admin", "admin")
                .get("https://the-internet.herokuapp.com/basic_auth")
                .then().statusCode(200);

    }

    /**
     * CHALLENGED
     */
    @Test
    public void challenged(){

        given().auth().basic("admin", "admin")
                .get("https://the-internet.herokuapp.com/basic_auth")
                .then().statusCode(200);

    }

}
