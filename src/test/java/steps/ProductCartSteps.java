package steps;

import java.io.IOException;
import java.util.HashMap;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;
import utils.SpecBuilder;
import static io.restassured.RestAssured.given;

public class ProductCartSteps {
	RequestSpecification res;
	Response response;
	static String playertoken;
	static int playeruserrid;
	static String productCartId;
	ConfigReader cr = new ConfigReader();

	@Given("create ProductCart login api payload")
	public void create_product_cart_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email", cr.readConfigData("Playeremail"));
		mainobject.put("password", cr.readConfigData("Playerpassword"));
		mainobject.put("type", cr.readConfigData("Playertype"));
		mainobject.put("deviceId", cr.readConfigData("PlayerdeviceId"));

		res = given(SpecBuilder.requestSpecification())

				.body(mainobject);
	}

	@When("user calls with ProductCart player POST http request")
	public void user_calls_with_product_cart_player_post_http_request() {
		response = res.when()

				.post("/Player/playerLogin");
	}

	@Then("ProductCart API call executed with {int} status code")
	public void product_cart_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()

				.spec(SpecBuilder.responseSpecBuilder())

				.assertThat().statusCode(stscode)

				.extract()

				.response();

		finalresponse.asPrettyString();

		JsonPath jsonvalue = finalresponse.jsonPath();

		playertoken = jsonvalue.getString("token");
		System.out.println(playertoken);

		playeruserrid = jsonvalue.getInt("userId");
		System.out.println(playeruserrid);

		String playerusername = jsonvalue.getString("userName");
		System.out.println(playerusername);

		System.out.println("**************************player successfully loged in**************************");
	}

	@Given("create ProductCartAdd api payload")
	public void create_product_cart_add_api_payload() throws IOException {
		HashMap<String, Integer> mainobject = new HashMap<String, Integer>();
		mainobject.put("productId",125 );
		mainobject.put("quantity",1 );
		mainobject.put("taxId",0);

		res = given(SpecBuilder.requestSpecification())
				.header("Authorization","Bearer "+ playertoken)
				.body(mainobject);
	}

	@When("user calls with ProductCartAdd POST http request")
	public void user_calls_with_product_cart_add_post_http_request() {
		response = res.when()

				.post("/ProductCartMaster");
	}

	@Then("ProductCartAdd API call executed with {int} status code")
	public void product_cart_add_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
	}

	@Given("get ProductCartGetAll api payload")
	public void get_product_cart_get_all_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				.header("Authorization","Bearer "+ playertoken);
				
	}

	@When("user calls with ProductCartGetAll GET http request")
	public void user_calls_with_product_cart_get_all_get_http_request() {
		response = res.when()

				.get("/ProductCartMaster");
	}

	@Then("ProductCartGetAll API call executed with {int} status code")
	public void product_cart_get_all_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
		
		// Extracting the latest productCartId based on productId and taxPrice
		String targetProductId = "125"; // Define the target "productId"
		String targetTaxPrice = "0"; // Define the target "taxPrice"

		// Using JsonPath to filter the product by productId and taxPrice
		 productCartId = response.jsonPath()
		        .getString("find { it.productId == " + targetProductId + " && it.taxPrice == " + targetTaxPrice + " }.productCartId");

		// Check if productCartId is null or empty and handle accordingly
		if (productCartId != null && !productCartId.isEmpty()) {
		    // Use productCartId in the next steps
		    System.out.println("productCartId for productId " + targetProductId + " and taxPrice " + targetTaxPrice + ": " + productCartId);
		} else {
		    // Handle case where no matching productCartId is found
		    System.out.println("No matching product found for productId " + targetProductId + " and taxPrice " + targetTaxPrice);
		}
	}

	@Given("get ProductCartUpdate api payload")
	public void get_product_cart_update_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		
		mainobject.put("productCartId",productCartId);
		mainobject.put("quantity","6");
	
		res = given(SpecBuilder.requestSpecification())
				
				.pathParam("pathkey", productCartId)
				.header("Authorization","Bearer "+ playertoken)
				.body(mainobject);
	}

	@When("user calls with ProductCartUpdate POST http request")
	public void user_calls_with_product_cart_update_post_http_request() {
		response = res.when()

				.put("/ProductCartMaster/{pathkey}");
	}

	@Then("ProductCartUpdate API call executed with {int} status code")
	public void product_cart_update_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
	}

	@Given("get ProductCartDelete api payload")
	public void get_product_cart_delete_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				
				.pathParam("pathkey", productCartId)
				.header("Authorization","Bearer "+ playertoken);
	}

	@When("user calls with ProductCartDelete DELETE http request")
	public void user_calls_with_product_cart_delete_delete_http_request() {
		response = res.when()

				.delete("/ProductCartMaster/{pathkey}");
	}

	@Then("ProductCartDelete API call executed with {int} status code")
	public void product_cart_delete_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
	}
}
