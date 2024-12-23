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
import utils.ConfigReader;
import utils.SpecBuilder;

public class ProductTypeMasterSteps {

	RequestSpecification res;
	static Response response;
	static String admintoken;
	static int latestProductTypeId;

	ConfigReader cr = new ConfigReader();

	@Given("create for product master login api payload")
	public void create_for_product_master_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email", "avinashkarche@gmail.com");
		mainobject.put("password", "Avi@123");

		res = given(SpecBuilder.requestSpecification())

				.body(mainobject);

	}

	@When("user calls with  admin for product master POST http request")
	public void user_calls_with_admin_for_product_master_post_http_request() {

		response = res.when()

				.post("/Admin/adminlogin");

	}

	@Then("admin for product master API call executed with {int} status code")
	public void admin_for_product_master_api_call_executed_with_status_code(Integer stscode) {
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

	@Given("create  AddProductType api payload")
	public void create_add_product_type_api_payload() throws IOException {

		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("name", "bundleCreatedByAutomationTesting");

		res = given(SpecBuilder.requestSpecification())

				.header("Authorization", "Bearer " + admintoken)

				.body(mainobject);

	}

	@When("user calls with  AddProductType POST http request")
	public void user_calls_with_add_product_type_post_http_request() {
		response = res.when()

				.post("/ProductTypeMaster");

	}

	@Then("AddProductType API call executed with {int} status code")
	public void add_product_type_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

		finalresponse.asPrettyString();

	}

	@Given("get  GetAllProductType api payload")
	public void get_get_all_product_type_api_payload() throws IOException {

		res = given(SpecBuilder.requestSpecification())

				.header("Authorization", "Bearer " + admintoken);

	}

	@When("user calls with  GetAllProductType POST http request")
	public void user_calls_with_get_all_product_type_post_http_request() {

		response = res.when()

				.get("/ProductTypeMaster");

	}

	@Then("GetAllProductType API call executed with {int} status code")
	public void get_all_product_type_api_call_executed_with_status_code(Integer stscode) {
		response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				
				.response();

		Object nameCriteria = "bundleCreatedByAutomationTesting";
		Object createdByCriteria = "avi";
		JsonPath jsonPath = response.jsonPath();
		latestProductTypeId = jsonPath.getList("$", Map.class).stream()
				.filter(json -> nameCriteria.equals(json.get("name"))
						&& createdByCriteria.equals(json.get("createdBy")))
				.map(json -> (int) json.get("productTypeId")).max(Integer::compareTo)
				.orElseThrow(() -> new RuntimeException("No matching product found"));

		// Log the result
		System.out.println("Fetched latest productTypeId: " + latestProductTypeId);
	}

	@Given("create  UpdateProductType api payload")
	public void create_update_product_type_api_payload() throws IOException {
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("productTypeId", latestProductTypeId);
		mainobject.put("name", "bundleCreatedByAutomationTestingUpdated");

		res = given(SpecBuilder.requestSpecification()).pathParam("pathkey", latestProductTypeId)

				.body(mainobject)

				.header("Authorization", "Bearer " + admintoken);

	}

	@When("user calls with  UpdateProductType POST http request")
	public void user_calls_with_update_product_type_post_http_request() {
		response = res.when()

				.put("/ProductTypeMaster/{pathkey}");
	}

	@Then("UpdateProductType API call executed with {int} status code")
	public void update_product_type_api_call_executed_with_status_code(Integer stscode) {
		response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

	}

	@Given("get  GetProductTypeById api payload")
	public void get_get_product_type_by_id_api_payload() throws IOException {

		res = given(SpecBuilder.requestSpecification())
				
				.pathParam("pathkey", latestProductTypeId)

				.header("Authorization", "Bearer " + admintoken);

	}

	@When("user calls with  GetProductTypeById POST http request")
	public void user_calls_with_get_product_type_by_id_post_http_request() {

		response = res.when()

				.get("/ProductTypeMaster/{pathkey}");

	}

	@Then("GetProductTypeById API call executed with {int} status code")
	public void get_product_type_by_id_api_call_executed_with_status_code(Integer stscode) {
		response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

	}

	@Given("get  DeleteProductType api payload")
	public void get_delete_product_type_api_payload() throws IOException {

		res = given(SpecBuilder.requestSpecification()).pathParam("pathkey", latestProductTypeId)

				.header("Authorization", "Bearer " + admintoken);

	}

	@When("user calls with  DeleteProductType POST http request")
	public void user_calls_with_delete_product_type_post_http_request() {
		response = res.when()

				.delete("/ProductTypeMaster/{pathkey}");

	}

	@Then("DeleteProductType API call executed with {int} status code")
	public void delete_product_type_api_call_executed_with_status_code(Integer stscode) {
		response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

	}

}
