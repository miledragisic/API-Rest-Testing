package step_definitions;

import Utilities.BookApiUtil;
import Utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;

public class BookApiStepDefs {

    String oauthToken;
    Response response;

   @Given("Authorization token is provided")
    public void authorization_token_is_provided() {

        oauthToken= BookApiUtil.generateToken();

        if(oauthToken==null || oauthToken.isEmpty()){ // if for some reason did our oathToke is null it will,
            fail("oauthToken was not generated successfully"); // 'fail' will throw an exception 'AssertionError'
        }
       System.out.println("oauthToken= "+oauthToken);

    }

    @When("user sends a POST request to api teams team with following data:")
    public void user_sends_a_POST_request_to_api_teams_team_with_following_data(Map<String, String> dataTable) {

       response= given().accept(ContentType.JSON)
               .header("Authorization", oauthToken)
               .and().body(dataTable)
               .when().post(ConfigurationReader.getProperty("bookitQA3")+"/api/teams/team");
       
       response.prettyPeek();

        res
    }


    @Then("status code should be {int}")
    public void status_code_should_be(int statusCode) {

       
    }




}
