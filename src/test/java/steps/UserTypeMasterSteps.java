package steps;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.SpecBuilder;

public class UserTypeMasterSteps {

	RequestSpecification res;
	static Response response;
	static String superadmintoken;
	static  String userTypeId;

	@Given("create adminforusertype login api payload")
	public void create_adminforusertype_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email", "avinashkarche@gmail.com");
		mainobject.put("password", "Avi@123");

		res = given(SpecBuilder.requestSpecification())

				.body(mainobject);

	}

	@When("user calls with adminforusertype POST http request")
	public void user_calls_with_adminforusertype_post_http_request() {
		response = res.when()

				.post("/Admin/adminlogin");
	}

	@Then("adminforusertype API call executed with {int} status code")
	public void adminforusertype_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

		finalresponse.asPrettyString();

		JsonPath jsonvalue = finalresponse.jsonPath();

		superadmintoken = jsonvalue.getString("token");
		System.out.println(superadmintoken);

		int superadminuserid = jsonvalue.getInt("userId");
		System.out.println(superadminuserid);

		String superadminusername = jsonvalue.getString("userName");
		System.out.println(superadminusername);

		System.out.println("**************************Super admin successfully loged in**************************");
	}

	@Given(": create the UserTypeMasterAdd API payload")
	public void create_the_user_type_master_add_api_payload() throws IOException {
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("name", "testUser");
		mainobject.put("createdBy", 1);

		res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization", "Bearer " + superadmintoken)

				.body(mainobject);
	}

	@When(": UserTypeMasterAdd call with POST request")
	public void user_type_master_add_call_with_post_request() throws IOException {
		response = res.when()

				.post("/UserTypeMaster");
	}

	@Then(": UserTypeMasterAdd API call executed with {int} status code")
	public void user_type_master_add_api_call_executed_with_status_code(Integer stscode) {
		response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();
	}

	@Given(": get the UserTypeMasterGetAll API payload")
	public void get_the_user_type_master_get_all_api_payload() throws IOException {
		
                res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization", "Bearer " + superadmintoken);

				
	}

	@When(": UserTypeMasterGetAll call with GET request")
	public void user_type_master_get_all_call_with_get_request() {
		response = res.when()

				.get("/UserTypeMaster");
	}

	@Then(": UserTypeMasterGetAll API call executed with {int} status code")
	public void user_type_master_get_all_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
		
		// Extracting the latest user based on name and createdBy
        String targetName = "testUser"; // Define the target "name"
        String targetCreatedBy = "avi"; // Define the target "createdBy"

        // Using JsonPath to filter the user by name and createdBy
         userTypeId = response.jsonPath()
                .getString("find { it.name == '" + targetName + "' && it.createdBy == '" + targetCreatedBy + "' }.userTypeId");

        // Use userTypeId in next steps
        System.out.println("User Type ID for " + targetName + " created by " + targetCreatedBy + ": " + userTypeId);
    }
		
	

	@Given(": get the UserTypeMasterGetById API payload")
	public void get_the_user_type_master_get_by_id_api_payload() throws IOException {
		
             res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization", "Bearer " + superadmintoken)
				.pathParam("pathkey", userTypeId);
	}

	@When(": UserTypeMasterGetById call with GET request")
	public void user_type_master_get_by_id_call_with_get_request() {
		response = res.when()

				.get("/UserTypeMaster/{pathkey}");
	}

	@Then(": UserTypeMasterGetById API call executed with {int} status code")
	public void user_type_master_get_by_id_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
	}

	@Given(": create the UserTypeMasterUpdate API payload")
	public void create_the_user_type_master_update_api_payload() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When(": UserTypeMasterUpdate call with PUT request")
	public void user_type_master_update_call_with_put_request() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then(": UserTypeMasterUpdate API call executed with {int} status code")
	public void user_type_master_update_api_call_executed_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given(": get the UserTypeMasterDelete API payload")
	public void get_the_user_type_master_delete_api_payload() throws IOException {
		
          res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization", "Bearer " + superadmintoken)
				.pathParam("pathkey", userTypeId);
	}

	@When(": UserTypeMasterDelete call with DELETE request")
	public void user_type_master_delete_call_with_delete_request() {
		response = res.when()

				.delete("/UserTypeMaster/{pathkey}");
	}

	@Then(": UserTypeMasterDelete API call executed with {int} status code")
	public void user_type_master_delete_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
	}
}
