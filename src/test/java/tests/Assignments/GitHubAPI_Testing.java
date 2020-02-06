package tests.Assignments;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class GitHubAPI_Testing {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI= "https://api.github.com";
    }

    @Test
    public void Verify_organization_information(){

         given().pathParam("org","cucumber")
                                    .when().get(" /orgs/{org}").prettyPeek()
                                    .then().statusCode(200)
                                    .and().contentType("application/json; charset=utf-8")
                                    .and().assertThat().body("login", is("cucumber"))
                                    .and().assertThat().body("name", is("Cucumber"))
                                    .and().assertThat().body("id", is(320565));
    }

    @Test
    public void Verify_error_message(){

        given().accept(ContentType.XML)
                .and().pathParam("org","cucumber")
                .when().get(" /orgs/{org}").prettyPeek()
                .then().statusCode(415)
                .and().contentType("application/json; charset=utf-8")
                .and().header("Status", containsString("Unsupported Media Type"));
    }

    @Test
    public void Number_of_repositories(){

        Response response= given().pathParam("org","cucumber")
                .when().get(" /orgs/{org}");

       // response.prettyPrint();
        int  public_repos= response.body().path("public_repos");
        System.out.println("public_repos1: "+public_repos);
        System.out.println("===============");




        Response response2= given().pathParam("org","cucumber")
                .when().get("/orgs/{org}/repos");


        List<List<Object> >list= response2.jsonPath().getJsonObject("");
        System.out.println("===============");
        System.out.println(list.size());

    }


}



















