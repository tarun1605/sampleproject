package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.UserDetailsResponse;

import static io.restassured.RestAssured.given;

public class GitHubUserJourneySteps {

    UserDetailsResponse userDetailsResponse;
    Response response;


    @Given("^User makes github GET request to (.*) endpoint to get user details$")
    public void getGitHubUserDetails(String endpoint) {
        response = given().log().all().request("GET", "https://api.github.com/users/sachingunjal").then().log().all().and().extract().response();
    }

    @Then("User will get a {int} response")
    public void userWillGetAResponse(int statusCode) {
        this.response.then().log().body().statusCode(statusCode);
    }

    @And("The response contains valid user details")
    public void theResponseContainsValidUserDetails() {
        userDetailsResponse = this.response.as(UserDetailsResponse.class);
        Assert.assertEquals(userDetailsResponse.getName(), "Sachin Gunjal");
        Assert.assertEquals(userDetailsResponse.getLogin(), "SachinGunjal");
    }

    @And("The response contains valid repository details")
    public void theResponseContainsValidRepositoryDetails() {
        Assert.assertEquals(this.response.jsonPath().getList("$").size(), 5);

    }

    @Given("^User makes github GET request to (.*) endpoint to get list of repositories$")
    public void userMakesGithubGETRequestToUsersUsernameReposEndpointToGetListOfRepositories(String endpoint) {
        response = given().log().all().request("GET", "https://api.github.com/users/sachingunjal/repos").then().log().all().and().extract().response();
    }
}
