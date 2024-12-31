package steps;

import java.io.IOException;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;
import utils.SpecBuilder;

public class ProductOrderSteps {
	
	RequestSpecification res;
	 static Response response;
	 static String superadmintoken;
	 static String playertoken;
		static int playeruserrid;
	 
	 ConfigReader cr = new ConfigReader();
	
	@Given("create ProductOrder player login api payload")
	public void create_product_order_player_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email",cr.readConfigData("Playeremail"));
		mainobject.put("password",cr.readConfigData("Playerpassword"));
		mainobject.put("type",cr.readConfigData("Playertype") );
		mainobject.put("deviceId",cr.readConfigData("PlayerdeviceId") );
		
	       res = given(SpecBuilder.requestSpecification())
	           
	                                    .body(mainobject);
	}

	@When("user ProductOrder  player login API calls with POST http request")
	public void user_product_order_player_login_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .post("/Player/playerLogin");
	}

	@Then("ProductOrder player login API call executed with {int} status code")
	public void product_order_player_login_api_call_executed_with_status_code(Integer stscode) {
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

	@Given("create ProductOrder login api payload")
	public void create_product_order_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email", "avinashkarche@gmail.com");
		mainobject.put("password","Avi@123");
		
	    res = given(SpecBuilder.requestSpecification())
	           
	                                    .body(mainobject);
	}

	@When("user ProductOrder calls with super admin POST http request")
	public void user_product_order_calls_with_super_admin_post_http_request() {
		response = res. when()
		        
	              .post("/Admin/adminlogin");
	}

	@Then("ProductOrder API call executed with {int} status code")
	public void product_order_api_call_executed_with_status_code1(Integer stscode) {
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

	@Given(": create ProductOrder API payload")
	public void create_product_order_api_payload() throws IOException {
		 res = given(SpecBuilder.requestSpecification())
	    .header("Authorization","Bearer "+ playertoken)
	    .body("{\r\n"
	    		+ "    \"productOrderList\": \"[{\\\"productId\\\":\\\"64\\\",\\\"cartId\\\":\\\"125\\\",\\\"quantity\\\":\\\"1\\\"}]\"\r\n"
	    		+ "}");
	}

	@When(": user ProductOrder API calls with POST http request")
	public void user_product_order_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .post("/ProductOrder");
	}

	@Then(": ProductOrder API call executed with {int} status code")
	public void product_order_api_call_executed_with_status_code(Integer stscode) {
		response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();       

     
	}

	@Given(": get GetAllOrder API payload")
	public void get_get_all_order_api_payload() throws IOException {
		
		 res = given(SpecBuilder.requestSpecification())
		    .header("Authorization","Bearer "+ superadmintoken);
	}

	@When(": user GetAllOrder API calls with GET http request")
	public void user_get_all_order_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/Player/playerGetAll");
	}

	@Then(": GetAllOrder API call executed with {int} status code")
	public void get_all_order_api_call_executed_with_status_code(Integer stscode) {
       Response finalresponse = response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();
       
      JsonPath jsonPath = finalresponse.jsonPath();
      int count = jsonPath.getList("$").size();

      // Printing the count
      System.out.println("Count of AllOrders: " + count);
        
	}

	@Given(": get GetAllOrderByUser API payload")
	public void get_get_all_order_by_user_api_payload() throws IOException {
		 res = given(SpecBuilder.requestSpecification())
		    .header("Authorization","Bearer "+playertoken);
		    
	}

	@When(": user GetAllOrderByUser API calls with GET http request")
	public void user_get_all_order_by_user_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/ProductOrder/ProductOrderGetByUserId");
	}

	@Then(": GetAllOrderByUser API call executed with {int} status code")
	public void get_all_order_by_user_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()
			
			.spec(SpecBuilder.responseSpecBuilder())
			 
			.assertThat().statusCode(stscode)

	        .extract()
	        
	        .response();
		 JsonPath jsonPath = finalresponse.jsonPath();
	      int count = jsonPath.getList("$").size();

	      // Printing the count
	      System.out.println("Count of AllOrderByUser: " + count);
	}

	@Given(": get ProductOrderSingleBuy API payload")
	public void get_product_order_single_buy_api_payload() throws IOException {
		HashMap<String, Integer> mainobject = new HashMap<String, Integer>();
		mainobject.put("productId", 125);
		 res = given(SpecBuilder.requestSpecification())
		    .body(mainobject)
		    .header("Authorization","Bearer "+ playertoken);
	}

	@When(": user ProductOrderSingleBuy API calls with GET http request")
	public void user_product_order_single_buy_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .post("/ProductOrder/ProductOrderSingleBuy");
	}
	

	@Then(": ProductOrderSingleBuy API call executed with {int} status code")
	public void product_order_single_buy_api_call_executed_with_status_code(Integer stscode) {
		response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();
	}

}
