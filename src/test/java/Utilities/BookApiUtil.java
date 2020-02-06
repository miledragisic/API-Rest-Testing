package Utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class BookApiUtil {

    public static String generateToken(){

        Response response= given().queryParams("email", ConfigurationReader.getProperty("bookitUserName"))
                .queryParams("password", ConfigurationReader.getProperty("bookitPassWord"))
                .when().get(ConfigurationReader.getProperty("bookitQA3")+"/sign");
                        //        https://cybertek-reservation-api-qa3.herokuapp.com/
        //String token= response.body().jsonPath().getString("accessToken");
// OR:
        JsonPath jsonPath= response.jsonPath();
        String token= jsonPath.getString("accessToken");
        return token;

    }

}
