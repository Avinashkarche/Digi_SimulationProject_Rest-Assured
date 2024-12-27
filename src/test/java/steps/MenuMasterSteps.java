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

public class MenuMasterSteps {
	RequestSpecification res;
	static Response response;
	static String superadmintoken;
	static int menuId;
	
	@Given("create adminforMenuMaster login api payload")
	public void create_adminfor_menu_master_login_api_payload() throws IOException {
		
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email", "avinashkarche@gmail.com");
		mainobject.put("password", "Avi@123");

		res = given(SpecBuilder.requestSpecification())

				.body(mainobject);
	}

	@When("user calls with adminforMenuMaster POST http request")
	public void user_calls_with_adminfor_menu_master_post_http_request() {
		response = res.when()

				.post("/Admin/adminlogin");
	}

	@Then("adminforMenuMaster API call executed with {int} status code")
	public void adminfor_menu_master_api_call_executed_with_status_code(Integer stscode) {
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

	@Given(": create the MenuMasterAdd API payload")
	public void create_the_menu_master_add_api_payload() throws IOException {
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("name", "TestMenuforAutomationtesting");
		mainobject.put("sequence", 1);

		res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization", "Bearer " + superadmintoken)

				.body(mainobject);
	}

	@When(": MenuMasterAdd call with POST request")
	public void menu_master_add_call_with_post_request() {
		response = res.when()

				.post("/MenuMasters");
	}

	@Then(": MenuMasterAdd API call executed with {int} status code")
	public void menu_master_add_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
	}

	@Given(": get the MenuMasterGetAll API payload")
	public void get_the_menu_master_get_all_api_payload() throws IOException {
		
        res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization", "Bearer " + superadmintoken);
	}

	@When(": MenuMasterGetAll call with GET request")
	public void menu_master_get_all_call_with_get_request() {
		response = res.when()

				.get("/MenuMasters");
	}

	@Then(": MenuMasterGetAll API call executed with {int} status code")
	public void menu_master_get_all_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
		
		// Extracting the latest user based on name and createdBy
        String targetName = "TestMenuforAutomationtesting"; // Define the target "name"
        String targetCreatedBy = "avi"; // Define the target "createdBy"

        // Using JsonPath to filter the user by name and createdBy
        menuId = response.jsonPath()
                .getInt("find { it.name == '" + targetName + "' && it.createdBy == '" + targetCreatedBy + "' }.menuId");

        // Use userTypeId in next steps
        System.out.println("menu ID for " + targetName + " created by " + targetCreatedBy + ": " + menuId);
	}

	@Given(": get the MenuMasterGetById API payload")
	public void get_the_menu_master_get_by_id_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization", "Bearer " + superadmintoken)
				.pathParam("pathkey", menuId);
	}

	@When(": MenuMasterGetById call with GET request")
	public void menu_master_get_by_id_call_with_get_request() {

		response = res.when()

				.get("/MenuMasters/{pathkey}");
	}

	@Then(": MenuMasterGetById API call executed with {int} status code")
	public void menu_master_get_by_id_api_call_executed_with_status_code(Integer stscode) throws InterruptedException {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
		
		Thread.sleep(2000);
		
	}

	@Given(": create the MenuMasterUpdate API payload")
	public void create_the_menu_master_update_api_payload() throws IOException {
		
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("menuId", menuId);
		mainobject.put("name", "Avi");
		mainobject.put("sequence", 10);
		

		res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization", "Bearer " + superadmintoken)
				.pathParam("pathkey", menuId)
				.body(mainobject);
	}

	@When(": MenuMasterUpdate call with PUT request")
	public void menu_master_update_call_with_put_request() {
		response = res.when()

				.put("/MenuMasters/{pathkey}");
	}

	@Then(": MenuMasterUpdate API call executed with {int} status code")
	public void menu_master_update_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
	}

	@Given(": get the MenuMasterDelete API payload")
	public void get_the_menu_master_delete_api_payload() throws IOException {
		
      res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization", "Bearer " + superadmintoken)
				.pathParam("pathkey", menuId);
	}

	@When(": MenuMasterDelete call with DELETE request")
	public void menu_master_delete_call_with_delete_request() {
		response = res.when()

				.delete("/MenuMasters/{pathkey}");
	}

	@Then(": MenuMasterDelete API call executed with {int} status code")
	public void menu_master_delete_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
	}

}
