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
import utils.ConfigReader;
import utils.SpecBuilder;

public class ProductWishlistSteps {
	
	RequestSpecification res;
	Response response;
	static String playertoken;
	static int playeruserrid;
	static String productCartId;
	static int productWishlistId;
	
	ConfigReader cr = new ConfigReader();
	
	@Given("create ProductWishList login api payload")
	public void create_product_wish_list_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email", cr.readConfigData("Playeremail"));
		mainobject.put("password", cr.readConfigData("Playerpassword"));
		mainobject.put("type", cr.readConfigData("Playertype"));
		mainobject.put("deviceId", cr.readConfigData("PlayerdeviceId"));

		res = given(SpecBuilder.requestSpecification())

				.body(mainobject);
	}

	@When("user calls with ProductWishList player POST http request")
	public void user_calls_with_product_wish_list_player_post_http_request() {
		response = res.when()

				.post("/Player/playerLogin");
	}

	@Then("ProductWishList API call executed with {int} status code")
	public void product_wish_list_api_call_executed_with_status_code(Integer stscode) {
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

	@Given("create ProductWishListAdd api payload")
	public void create_product_wish_list_add_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("productId","125");

		res = given(SpecBuilder.requestSpecification())
				.header("Authorization","Bearer "+ playertoken)
				.body(mainobject);
	}

	@When("user calls with ProductWishListAdd POST http request")
	public void user_calls_with_product_wish_list_add_post_http_request() {
		response = res.when()

				.post("/ProductWishlist");
	}

	@Then("ProductWishListAdd API call executed with {int} status code")
	public void product_wish_list_add_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
	}

	@Given("get ProductWishListGetAll api payload")
	public void get_product_wish_list_get_all_api_payload() throws IOException {
		res = given(SpecBuilder.requestSpecification())
				.header("Authorization","Bearer "+ playertoken);
	}

	@When("user calls with ProductWishListGetAll GET http request")
	public void user_calls_with_product_wish_list_get_all_get_http_request() {
		response = res.when()

				.get("/ProductWishlist");
	}

	@Then("ProductWishListGetAll API call executed with {int} status code")
	public void product_wish_list_get_all_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
		
		// Extract productWishlistId for a specific productId
        int productIdToFind = 125;
         productWishlistId = response.jsonPath()
                .getInt("find { it.productId == " + productIdToFind + " }.productWishlistId");

        // Use the extracted productWishlistId for the next step
        System.out.println("Extracted productWishlistId: " + productWishlistId);
	}

	@Given("get RemoveWishList api payload")
	public void get_remove_wish_list_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				.pathParam("pathkey", productWishlistId)
				.header("Authorization","Bearer "+ playertoken);
	}

	@When("user calls with RemoveWishList DELETE http request")
	public void user_calls_with_remove_wish_list_delete_http_request() {
		response = res.when()

				.delete("/ProductWishlist/{pathkey}");
	}

	@Then("RemoveWishList API call executed with {int} status code")
	public void remove_wish_list_api_call_executed_with_status_code(Integer stscode) {
		response.then()

		.spec(SpecBuilder.responseSpecBuilder())

		.assertThat().statusCode(stscode)

		.extract()

		.response();
	}
	}



