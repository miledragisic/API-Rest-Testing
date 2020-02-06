package Utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenUtility {

    /**
     * Java Enums:
     * An enum is a special "class" that represents a group of constants (unchangeable variables, like final variables).
     * To create an enum , use the enum keyword (instead of class or interface), and separate the constants with a comma.
      */

    public enum UserType{TEACHER, TEAM_LEADER, TEAM_MEMBER};

    public static String getToken(UserType type){

        String token= null, email= null, password= null;

        switch (type){

            case TEACHER:
                email= ConfigurationReader.getProperty("teacher_email");
                password= ConfigurationReader.getProperty("teacher_password");
                break;

            case TEAM_LEADER:
                email= ConfigurationReader.getProperty("team_leader_email");
                password= ConfigurationReader.getProperty("team_leader_password");
                break;

            case TEAM_MEMBER:
                email= ConfigurationReader.getProperty("team_member_email");
                password= ConfigurationReader.getProperty("team_member_password");
                break;
        }
        Response response= given().queryParam("email", email)
                                .queryParam("password", password)
                                .when().get("/sign");
        response.then().statusCode(200);
        token= response.path("accessToken");

        return token;

    }

}
