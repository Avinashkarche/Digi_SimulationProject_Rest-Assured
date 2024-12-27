package steps;

import static io.restassured.RestAssured.given;

import java.io.File;
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

public class ProductMasterSteps {
	
	RequestSpecification res;
	 static Response response;
	 static String admintoken;
	 static String playertoken;
	 static Integer productId;
	 static Integer secondproductId;
	 static String playeruserid;
	 
	 ConfigReader cr = new ConfigReader();
	

	@Given("create AddProduct admin login api payload")
	public void create_add_product_admin_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email", "avinashkarche@gmail.com");
		mainobject.put("password","Avi@123");
		
	    res = given(SpecBuilder.requestSpecification())
	           
	                                    .body(mainobject);
	             
	}

	@When("user calls with AddProduct admin login POST http request")
	public void user_calls_with_add_product_admin_login_post_http_request() {
		response = res. when()
		        
	              .post("/Admin/adminlogin");
	}

	@Then("AddProduct admin API call executed with {int} status code")
	public void add_product_admin_api_call_executed_with_status_code(Integer stscode) {
Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();       
	    
	             finalresponse.asPrettyString();
	    
	    
	    
	    JsonPath jsonvalue = finalresponse.jsonPath();
	    
	     admintoken = jsonvalue.getString("token");
	    System.out.println(admintoken);
	    
	    String adminusername = jsonvalue.getString("userName");
	    System.out.println(adminusername);  
	    
	    System.out.println("**************************admin successfully loged in**************************");
	}

	@Given("create playerforproduct login api payload")
	public void create_playerforproduct_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email",cr.readConfigData("Playeremail"));
		mainobject.put("password",cr.readConfigData("Playerpassword"));
		mainobject.put("type",cr.readConfigData("Playertype") );
		mainobject.put("deviceId",cr.readConfigData("PlayerdeviceId") );
		
	       res = given(SpecBuilder.requestSpecification())
	           
	                                    .body(mainobject);
	}

	@When("user playerforproduct login API calls with POST http request")
	public void user_playerforproduct_login_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .post("/Player/playerLogin");
	}

	@Then("playerforproduct login API call executed with {int} status code")
	public void playerforproduct_login_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();       
	    
	             finalresponse.asPrettyString();
	    
	    
	    
	    JsonPath jsonvalue = finalresponse.jsonPath();
	    
	     playertoken = jsonvalue.getString("token");
	    System.out.println(playertoken);
	    
	    String playerusername = jsonvalue.getString("userName");
	    System.out.println(playerusername);  
	    
	    playeruserid = jsonvalue.getString("userId");
	    
	    System.out.println("**************************player successfully loged in**************************");
	}

	

	@Given("create AddProductByGameList api payload")
	public void create_add_product_by_game_list_api_payload() throws IOException {

        String imagePath= System.getProperty("user.dir") + "\\Testgame.png";
		
		File file = new File(imagePath);

	    
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("productTypeId",cr.readConfigData("productTypeIdforbundle"));
		mainobject.put("productName",cr.readConfigData("productNameforbundle"));
		mainobject.put("productDescription",cr.readConfigData("productDescriptionforbundle"));
		mainobject.put("pathUrl",cr.readConfigData("pathUrlforbundle"));
		mainobject.put("productPrice",cr.readConfigData("productPriceforbundle"));
		mainobject.put("sellPrice",cr.readConfigData("sellPriceforbundle"));
		mainobject.put("productStatusId",cr.readConfigData("productStatusIdforbundle"));
		mainobject.put("productCode",cr.readConfigData("productCodeforbundle"));
		mainobject.put("gameList","{\"productId\":\"1\",\"productId\":\"1\",\"productId\":\"1\",\"productId\":\"1\"}");
		mainobject.put("thumbnailUrl", file);
		res = given(SpecBuilder.requestSpecification())
		           
                .body(mainobject)
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user AddProductByGameList API calls with POST http request")
	public void user_add_product_by_game_list_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .post("/ProductMaster");
	}

	@Then("AddProductByGameList API call executed with {int} status code")
	public void add_product_by_game_list_api_call_executed_with_status_code(Integer stscode) {
         response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();   
	}

	@Given("get GetAllProduct api payload")
	public void get_get_all_product_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
		           
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user GetAllProduct API calls with GET http request")
	public void user_get_all_product_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/ProductMaster");
	}

	@Then("GetAllProduct API call executed with {int} status code")
	public void get_all_product_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()
			 
			.assertThat().statusCode(stscode)

	        .extract()
	        
	        .response(); 
		 
		//convert JSON to string
	      JsonPath j = new JsonPath(finalresponse.asString());

	      //length of JSON array
	      int s = j.getInt("data.size()");
	      System.out.println("Size of All products: "+s);
	      
		 JsonPath jsonPath = response.jsonPath();

	        // Extract the productId for a given productName
	        String targetProductName = "bundletestgame2"; // Change to your desired productName
	        
	         productId = jsonPath.getInt(
	                "find { it.productName == '" + targetProductName + "' }.productId"
	        );

	        if (productId != null) {
	            System.out.println("Product ID for '" + targetProductName + "' is: " + productId);
	        } else {
	            System.out.println("Product with name '" + targetProductName + "' not found.");
	        }
	    }
		 

	@Given("UpdateProduct api payload")
	public void update_product_api_payload() throws IOException {
String imagePath= System.getProperty("user.dir") + "\\Testgame.png";
		
		File file = new File(imagePath);

	    
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		
		mainobject.put("productId", productId);
		mainobject.put("productTypeId",cr.readConfigData("UpdatedproductTypeIdforbundle"));
		mainobject.put("productName",cr.readConfigData("UpdatedproductNameforbundle"));
		mainobject.put("productDescription",cr.readConfigData("UpdatedproductDescriptionforbundle"));
		mainobject.put("pathUrl",cr.readConfigData("UpdatedpathUrlforbundle"));
		mainobject.put("productPrice",cr.readConfigData("UpdatedproductPriceforbundle"));
		mainobject.put("sellPrice",cr.readConfigData("UpdatedsellPriceforbundle"));
		mainobject.put("productStatusId",cr.readConfigData("UpdatedproductStatusIdforbundle"));
		mainobject.put("productCode",cr.readConfigData("UpdatedproductCodeforbundle"));
		mainobject.put("thumbnailUrl", file);
		res = given(SpecBuilder.requestSpecification())
		           
                .body(mainobject)
                .pathParam("pathkey", productId)
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user UpdateProduct API calls with PUT http request")
	public void user_update_product_api_calls_with_put_http_request() {
		response = res. when()
		        
	              .put("/ProductMaster/{pathkey}");
	}

	@Then("UpdateProduct API call executed with {int} status code")
	public void update_product_api_call_executed_with_status_code(Integer stscode) {
response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();  
	}

	@Given("get GetProductById api payload")
	public void get_get_product_by_id_api_payload() throws IOException {
		res = given(SpecBuilder.requestSpecification())
				.pathParam("pathkey", productId)
		           
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user GetProductById API calls with GET http request")
	public void user_get_product_by_id_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/ProductMaster/{pathkey}");
	}

	@Then("GetProductById API call executed with {int} status code")
	public void get_product_by_id_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
			
			.spec(SpecBuilder.responseSpecBuilder())
			 
			.assertThat().statusCode(stscode)

	        .extract()
	        
	        .response();  
	}

	@Given("get DeleteProduct api payload")
	public void get_delete_product_api_payload() throws IOException {
		res = given(SpecBuilder.requestSpecification())
				.pathParam("pathkey", productId)
		           
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user DeleteProduct API calls with DELETE http request")
	public void user_delete_product_api_calls_with_delete_http_request() {
		response = res. when()
		        
	              .delete("/ProductMaster/{pathkey}");
	}

	@Then("DeleteProduct API call executed with {int} status code")
	public void delete_product_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
			
			.spec(SpecBuilder.responseSpecBuilder())
			 
			.assertThat().statusCode(stscode)

	        .extract()
	        
	        .response();  
	}
	
	@Given("create AddProduct api payload")
	public void create_add_product_api_payload() throws IOException {
		
        String imagePath= System.getProperty("user.dir") + "\\Testgame.png";
		
		File file = new File(imagePath);

	    
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("productTypeId",cr.readConfigData("productTypeId"));
		mainobject.put("productName",cr.readConfigData("productName"));
		mainobject.put("productDescription",cr.readConfigData("productDescription"));
		mainobject.put("pathUrl",cr.readConfigData("pathUrl"));
		mainobject.put("productPrice",cr.readConfigData("productPrice"));
		mainobject.put("sellPrice",cr.readConfigData("sellPrice"));
		mainobject.put("productStatusId",cr.readConfigData("productStatusId"));
		mainobject.put("productCode",cr.readConfigData("productCode"));
		mainobject.put("thumbnailUrl", file);
		res = given(SpecBuilder.requestSpecification())
		           
                .body(mainobject)
                .header("Authorization","Bearer "+admintoken);
		      
	}

	@When("user AddProduct API calls with POST http request")
	public void user_add_product_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .post("/ProductMaster");
	}

	@Then("AddProduct API call executed with {int} status code")
	public void add_product_api_call_executed_with_status_code(Integer stscode) {
		response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();     
	}
	
	@Given("get AddProduct GetAllProduct api payload")
	public void get_add_product_get_all_product_api_payload() throws IOException {
		res = given(SpecBuilder.requestSpecification())
		           
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user AddProduct GetAllProduct API calls with GET http request")
	public void user_add_product_get_all_product_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/ProductMaster");
	}

	@Then("AddProduct GetAllProduct API call executed with {int} status code")
	public void add_product_get_all_product_api_call_executed_with_status_code(Integer stscode) {
		response.then()
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response(); 
	 
	 JsonPath jsonPath = response.jsonPath();

        // Extract the productId for a given productName
        String targetProductName = "Test Product"; // Change to your desired productName
        
         productId = jsonPath.getInt(
                "find { it.productName == '" + targetProductName + "' }.productId"
        );

        if (productId != null) {
            System.out.println("Product ID for '" + targetProductName + "' is: " + productId);
        } else {
            System.out.println("Product with name '" + targetProductName + "' not found.");
        }
	}

	@Given("update AddProduct UpdateProduct api payload")
	public void update_add_product_update_product_api_payload() throws IOException {
		
String imagePath= System.getProperty("user.dir") + "\\Testgame.png";
		
		File file = new File(imagePath);

	    
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("productId", productId);
		mainobject.put("productTypeId",cr.readConfigData("UpdatedproductTypeId"));
		mainobject.put("productName",cr.readConfigData("UpdatedproductName"));
		mainobject.put("productDescription",cr.readConfigData("UpdatedproductDescription"));
		mainobject.put("pathUrl",cr.readConfigData("UpdatedpathUrl"));
		mainobject.put("productPrice",cr.readConfigData("UpdatedproductPrice"));
		mainobject.put("sellPrice",cr.readConfigData("UpdatedsellPrice"));
		mainobject.put("productStatusId",cr.readConfigData("UpdatedproductStatusId"));
		mainobject.put("productCode",cr.readConfigData("UpdatedproductCode"));
		mainobject.put("thumbnailUrl", file);
		res = given(SpecBuilder.requestSpecification())
		           
                .body(mainobject)
                .pathParam("pathkey", productId)
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user AddProduct UpdateProduct API calls with PUT http request")
	public void user_add_product_update_product_api_calls_with_put_http_request() {
		response = res. when()
		        
	              .put("/ProductMaster/{pathkey}");
	}

	@Then("AddProduct UpdateProduct API call executed with {int} status code")
	public void add_product_update_product_api_call_executed_with_status_code(Integer stscode) {
   response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();  
	}
	
	
	@Given("update ProductStatusIdUpdate api payload")
	public void update_product_status_id_update_api_payload() throws IOException {
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("productId", productId);
		mainobject.put("productStatusId","2");
	
		
		res = given(SpecBuilder.requestSpecification())
		           
                .body(mainobject)
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user ProductStatusIdUpdate API calls with PUT http request")
	public void user_product_status_id_update_api_calls_with_put_http_request() {
		response = res. when()
		        
	              .put("/ProductMaster/UpdateProductStatus");
	}

	@Then("ProductStatusIdUpdate API call executed with {int} status code")
	public void product_status_id_update_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
			
			.spec(SpecBuilder.responseSpecBuilder())
			 
			.assertThat().statusCode(stscode)

	        .extract()
	        
	        .response();  
	}
	
	
	@Given("update BundleProductUpdate api payload")
	public void update_bundle_product_update_api_payload() throws IOException {
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("productId", productId);
		mainobject.put("gameList","{\"productId\":\"10\"}");
	
		
		res = given(SpecBuilder.requestSpecification())
		           
                .body(mainobject)
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user BundleProductUpdate API calls with PUT http request")
	public void user_bundle_product_update_api_calls_with_put_http_request() {
		response = res. when()
		        
	              .put("/BundleInfo");
	}

	@Then("BundleProductUpdate API call executed with {int} status code")
	public void bundle_product_update_api_call_executed_with_status_code(Integer stscode) {
		
		      response.then()
			
			.spec(SpecBuilder.responseSpecBuilder())
			 
			.assertThat().statusCode(stscode)

	        .extract()
	        
	        .response();  
		 
		 
	}


	@Given("get GetProductByIdPublic api payload")
	public void get_get_product_by_id_public_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
		                      
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user GetProductByIdPublic API calls with GET http request")
	public void user_get_product_by_id_public_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/ProductMaster/GetProductPublic");
	}

	@Then("GetProductByIdPublic API call executed with {int} status code")
	public void get_product_by_id_public_api_call_executed_with_status_code(Integer stscode) {
		
		 response.then()

			.assertThat().statusCode(stscode)

	        .extract()
	        
	        .response();  
	}

	@Given("update TrendingGameAndBundleToggle api payload")
	public void update_trending_game_and_bundle_toggle_api_payload1() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
                .pathParam("pathkey", productId)
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user TrendingGameAndBundleToggle API calls with GET http request")
	public void user_trending_game_and_bundle_toggle_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .put("/ProductMaster/TrendingGameAndBundleToggle/{pathkey}");
	}

	@Then("TrendingGameAndBundleToggle API call executed with {int} status code")
	public void trending_game_and_bundle_toggle_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
			
			.spec(SpecBuilder.responseSpecBuilder())
			 
			.assertThat().statusCode(stscode)

	        .extract()
	        
	        .response();  
	}
	
	

	@Given("get AddProduct GetProductById api payload")
	public void get_add_product_get_product_by_id_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				
				.pathParam("pathkey", productId)
		           
                .header("Authorization","Bearer "+admintoken);
	}

	@When("user AddProduct GetProductById API calls with GET http request")
	public void user_add_product_get_product_by_id_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/ProductMaster/{pathkey}");
	}

	@Then("AddProduct GetProductById API call executed with {int} status code")
	public void add_product_get_product_by_id_api_call_executed_with_status_code(Integer stscode) {
		response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();  
	}

	@Given("get AddProduct DeleteProduct api payload")
	public void get_add_product_delete_product_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				.pathParam("pathkey", productId)
		           
                .header("Authorization","Bearer "+admintoken);
	}

	@When("userAddProduct  DeleteProduct API calls with DELETE http request")
	public void user_add_product_delete_product_api_calls_with_delete_http_request() {
		response = res. when()
		        
	              .delete("/ProductMaster/{pathkey}");
	}

	@Then("DeleteProductAddProduct  API call executed with {int} status code")
	public void delete_product_add_product_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
			
			.spec(SpecBuilder.responseSpecBuilder())
			 
			.assertThat().statusCode(stscode)

	        .extract()
	        
	        .response();  
	}
	
	@Given(": create PlayerRoleUpdate API payload")
	public void create_player_role_update_api_payload() throws IOException {
		
		           res=given()
				   
				   .spec(SpecBuilder.requestSpecification())
				   .header("Authorization","Bearer "+ admintoken)
				   .pathParam("pathkey",playeruserid);
	
			
		
	}

	@When(": user PlayerRoleUpdate API calls with PUT http request")
	public void user_player_role_update_api_calls_with_put_http_request() {
		response=res.when()
				.put("/Player/PlayerRoleUpdate/{pathkey}"); 
		
		
	}

	@Then(": PlayerRoleUpdate API call executed with {int} status code")
	public void player_role_update_api_call_executed_with_status_code(Integer statusCode) {
	              response.then()
				.spec(SpecBuilder.responseSpecBuilder())
				
				.assertThat().statusCode(statusCode)

		        .extract()
		        
		        .response();
		
	}
		

	@Given("get GetProductByTester api payload")
	public void get_get_product_by_tester_api_payload() throws IOException {
		res = given(SpecBuilder.requestSpecification())
		           
                .header("Authorization","Bearer "+playertoken);
	}

	@When("user GetProductByTester API calls with GET http request")
	public void user_get_product_by_tester_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/ProductMaster/GetProductByTester");
	}

	@Then("GetProductByTester API call executed with {int} status code")
	public void get_product_by_tester_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()
	
		.assertThat().statusCode(stscode)

        .extract()
        
        .response(); 
		
		//convert JSON to string
	      JsonPath j = new JsonPath(finalresponse.asString());

	      //length of JSON array
	      int s = j.getInt("data.size()");
	      System.out.println("Size of All public api's by tester: "+s);
	   
	}
	
	@Given("get GetProductPublic api payload")
	public void get_get_product_public_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification());
		           
            
	}

	@When("user GetProductPublic API calls with GET http request")
	public void user_get_product_public_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/ProductMaster/GetProductPublic");
	}

	@Then("GetProductPublic API call executed with {int} status code")
	public void get_product_public_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()
				 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();
       //convert JSON to string
	      JsonPath j = new JsonPath(finalresponse.asString());

	      //length of JSON array
	      int s = j.getInt("data.size()");
	      System.out.println("Size of All public api's: "+s);
          
	}
}
