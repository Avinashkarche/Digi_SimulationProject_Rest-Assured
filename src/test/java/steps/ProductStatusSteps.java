package steps;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.SpecBuilder;

public class ProductStatusSteps {
	RequestSpecification res;
	static Response response;
	static String admintoken;
	static Object latestProductstatusTypeId;

	@Given("create for ProductStatus login api payload")
	public void create_for_product_status_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email", "avinashkarche@gmail.com");
		mainobject.put("password", "Avi@123");

		res = given(SpecBuilder.requestSpecification())

				.body(mainobject);

	}

	@When("user calls with  admin for ProductStatus POST http request")
	public void user_calls_with_admin_for_product_status_post_http_request() {
		response = res.when()

				.post("/Admin/adminlogin");

	}

	@Then("admin for product masteProductStatus API call executed with {int} status code")
	public void admin_for_product_maste_product_status_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

		finalresponse.asPrettyString();

		JsonPath jsonvalue = finalresponse.jsonPath();

		admintoken = jsonvalue.getString("token");
		System.out.println(admintoken);

		int superadminuserid = jsonvalue.getInt("userId");
		System.out.println(superadminuserid);

		String superadminusername = jsonvalue.getString("userName");
		System.out.println(superadminusername);

		System.out.println("**************************Super admin successfully loged in**************************");

	}

	@Given("create  ProductStatusAdd api payload")
	public void create_product_status_add_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("name", "TestForAutomation");

		res = given(SpecBuilder.requestSpecification()).header("Authorization", "Bearer " + admintoken)

				.body(mainobject);

	}

	@When("user calls with  ProductStatusAdd POST http request")
	public void user_calls_with_product_status_add_post_http_request() {

		response = res.when()

				.post("/ProductStatusMaster");

	}

	@Then("ProductStatusAdd API call executed with {int} status code")
	public void product_status_add_api_call_executed_with_status_code(Integer stscode) {
		response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

	}

	@Given("get  ProductStatusGetAll api payload")
	public void get_product_status_get_all_api_payload() throws IOException {

		res = given(SpecBuilder.requestSpecification())

				.header("Authorization", "Bearer " + admintoken);

	}

	@When("user calls with  ProductStatusGetAll POST http request")
	public void user_calls_with_product_status_get_all_post_http_request() {

		response = res.when()

				.get("/ProductStatusMaster");

	}

	@Then("ProductStatusGetAll API call executed with {int} status code")
	public void product_status_get_all_api_call_executed_with_status_code(Integer stscode) {
		response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

		String nameCriteria = "TestForAutomation";
		String createdByCriteria = "avi";
		JsonPath jsonPath = response.jsonPath();
		latestProductstatusTypeId = jsonPath.getList("$", Map.class).stream()
				.filter(json -> nameCriteria.equals(json.get("name"))
						&& createdByCriteria.equals(json.get("createdBy")))
				.map(json -> (int) json.get("productStatusId")).max(Integer::compareTo)
				.orElseThrow(() -> new RuntimeException("No matching product found"));

// Log the result
		System.out.println("Fetched latest productTypeId: " + latestProductstatusTypeId);
	}

	@Given("create  ProductStatusById api payload")
	public void create_product_status_by_id_api_payload() throws IOException {

		res = given(SpecBuilder.requestSpecification())
				.pathParam("pathkey", latestProductstatusTypeId)

				.header("Authorization", "Bearer " + admintoken);

	}

	@When("user calls with  ProductStatusById POST http request")
	public void user_calls_with_product_status_by_id_post_http_request() {
		response = res.when()

				.get("/ProductStatusMaster/{pathkey}");

	}

	@Then("ProductStatusById API call executed with {int} status code")
	public void product_status_by_id_api_call_executed_with_status_code(Integer stscode) {
		response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

	}

	@Given("get  ProductStatusUpdate api payload")
	public void get_product_status_update_api_payload() throws IOException {
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("id", latestProductstatusTypeId);
		mainobject.put("name", "TestForAutomationupdated");

		res = given(SpecBuilder.requestSpecification())

				.pathParam("pathkey", latestProductstatusTypeId)

				.body(mainobject)

				.header("Authorization", "Bearer " + admintoken);

	}

	@When("user calls with  ProductStatusUpdate POST http request")
	public void user_calls_with_product_status_update_post_http_request() {

		response = res.when()

				.put("/ProductStatusMaster/{pathkey}");

	}

	@Then("ProductStatusUpdate API call executed with {int} status code")
	public void product_status_update_api_call_executed_with_status_code(Integer stscode) {
		response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

	}

	@Given("get  ProductStatusDelete api payload")
	public void get_product_status_delete_api_payload() throws IOException {
		res = given(SpecBuilder.requestSpecification())

				.pathParam("pathkey", latestProductstatusTypeId)

				.header("Authorization", "Bearer " + admintoken);

	}

	@When("user calls with  ProductStatusDelete POST http request")
	public void user_calls_with_product_status_delete_post_http_request() {
		response = res.when()

				.delete("/ProductStatusMaster/{pathkey}");

	}

	@Then("ProductStatusDelete API call executed with {int} status code")
	public void product_status_delete_api_call_executed_with_status_code(Integer stscode) {
		response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

	}

}
