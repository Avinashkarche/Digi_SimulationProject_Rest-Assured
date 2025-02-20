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

public class AdminSteps {
	RequestSpecification res;
	 static Response response;
	 static String superadmintoken;
	 static String childadmintoken;
	 static int childadminuserid;
	 static String playerid;
	 
	 ConfigReader cr = new ConfigReader();
	
	

	@Given("create login api payload")
	public void create_login_api_payload() throws IOException {
		
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email", "avinashkarche@gmail.com");
		mainobject.put("password","Avi@123");
		
	    res = given(SpecBuilder.requestSpecification())
	           
	                                    .body(mainobject);
	             
	}

	@When("user calls with super admin POST http request")
	public void user_calls_with_super_admin_post_http_request() {
		
		          response = res. when()    
	              .post("/Admin/adminlogin");
	}
	
	@Then("API call executed with {int} status code")
	public void api_call_executed_with_status_code(Integer stscode) {
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
	
	

	@Given("create admin API payload")
	public void create_admin_api_payload() throws IOException {
		
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("userName",cr.readConfigData("userName"));
		mainobject.put("email",cr.readConfigData("email"));
		mainobject.put("password",cr.readConfigData("password"));
		mainobject.put("countryCallingCodeId",cr.readConfigData("countryCallingCodeId"));
		mainobject.put("mobileNo",cr.readConfigData("mobileNo"));
		mainobject.put("userTypeId",cr.readConfigData("userTypeId"));
		
		res=given()
		
		.spec(SpecBuilder.requestSpecification())
		
		.header("Authorization","Bearer "+superadmintoken)
		
		.body(mainobject);  
	}
	
	@When ("user calls with POST http request")
	public void user_calls_with_POST_http_request() {
		
		response=res.when()
		
		.post("/Admin/adminRegister");
	   
	}
	
	@Then("API call executed with the {int} status code")
	public void api_call_executed_with_the_status_code(Integer statusCode) {
		
		Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		
	    		.assertThat().statusCode(statusCode)
	    
	            .extract()
	            
	            .response(); 
	             
	            finalresponse.asPrettyString();   
	            System.out.println("**************************Super admin successfully resgistered child admin**************************");
	}
	
	
	
	@Given("create child admin login api payload")
	public void create_child_admin_login_api_payload() throws IOException {
		
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email",cr.readConfigData("emailForLogin"));
		mainobject.put("password",cr.readConfigData("passwordForLogin"));
		
	    res = given(SpecBuilder.requestSpecification())
	           
	                                    .body(mainobject);
	
	}

	@When("user calls with super child admin POST http request")
	public void user_calls_with_super_child_admin_post_http_request() {
		response = res. when()
		        
	              .post("/Admin/adminlogin");
	}

	@Then("API call should be executed with {int} status code")
	public void api_call_should_be_executed_with_status_code(Integer stscode) {
		
  Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();       
	    
	             finalresponse.asPrettyString();
	    
	   
	    
	    JsonPath jsonvalue = finalresponse.jsonPath();
	    
	     childadmintoken = jsonvalue.getString("token");
	    System.out.println(childadmintoken);
	    
	     childadminuserid = jsonvalue.getInt("userId");
	    System.out.println(childadminuserid);
	    
	    String superadminusername = jsonvalue.getString("userName");
	    System.out.println(superadminusername);	
	    
	    System.out.println("**************************Super admin successfully login child admin**************************");
	}
	
	
	
	@Given("update admin API payload")
	public void update_admin_api_payload() throws IOException {
		
		HashMap<String, String> mainobject= new HashMap<String, String>();
		mainobject.put("userName",cr.readConfigData("userNameForUpdate"));
		mainobject.put("mobileNo", cr.readConfigData("mobileNoForUpdate"));
		
		res=given()
				
				.spec(SpecBuilder.requestSpecification())
				
				.header("Authorization","Bearer "+childadmintoken)
				
				.body(mainobject);
				
	}

	@When("user calls with PUT http request")
	public void user_calls_with_put_http_request() {
		response=res.when()
		.put("/Admin/adminUpdate");   
	}

	@Then("API call should executed with {int} status code")
	public void api_call_should_executed_with_status_code(Integer statusCode) {
Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		
	    		.assertThat().statusCode(statusCode)
	    
	            .extract()
	            
	            .response(); 
	             
	            finalresponse.asPrettyString(); 
	            System.out.println("**************************Super admin successfully updated the child admin username and mobile number**************************");
	}
	
	
	
	@Given("update admin API payload for change password")
	public void update_admin_api_payload_for_change_password() throws IOException {
		
		HashMap<String, String> mainobject =new HashMap<String, String>();
		mainobject.put("oldPassword",cr.readConfigData("oldPasswordforadmin"));
		mainobject.put("email",cr.readConfigData("emailForChangePassword"));
		mainobject.put("newPassword",cr.readConfigData("newPasswordforadmin"));
		
	    res=given()
	    
	    .spec(SpecBuilder.requestSpecification())
		
		.header("Authorization","Bearer "+childadmintoken)
		
	    .body(mainobject);
	    
	}

	@When("user calls with PUT http request for change Password")
	public void user_calls_with_put_http_request_for_change_password() {
		
		response=res.when()
		   .put("/Admin/ChangePassword");
	   
	}

	@Then("API call should be executedfor change admin password with {int} status code")
	public void api_call_should_be_executedfor_change_admin_password_with_status_code(Integer statusCode) {
		
Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		
	    		.assertThat().statusCode(statusCode)
	    
	            .extract()
	            
	            .response(); 
	             
	            finalresponse.asPrettyString(); 
	    System.out.println("**************************Super admin successfully updated the child admin password**************************");
	}
	
	
	@Given("create player API payload")
	public void create_player_api_payload() throws IOException {

		HashMap<String, String> mainobject =new HashMap<String, String>();
		
		mainobject.put("userName",cr.readConfigData("userNameOfPlayerresgisteredByAdmin"));
		mainobject.put("mobileNo",cr.readConfigData("mobileNoOfPlayerresgisteredByAdmin"));
		mainobject.put("email",cr.readConfigData("emailOfPlayerresgisteredByAdmin"));
		mainobject.put("password",cr.readConfigData("passwordOfPlayerresgisteredByAdmin") );
		mainobject.put("userType",cr.readConfigData("userTypeOfPlayerresgisteredByAdmin") );
		mainobject.put("name",cr.readConfigData("nameOfPlayerresgisteredByAdmin") );
		mainobject.put("dob", cr.readConfigData("nameOfPlayertRegisteredDateOfBirth"));
		mainobject.put("type", cr.readConfigData("nameOfPlayertRegisteredTyepe"));
		
		
	    res=given()
	    
	    .spec(SpecBuilder.requestSpecification())
		
		.header("Authorization","Bearer "+childadmintoken)
		
	    .body(mainobject);
		
		
	}

	@When("user calls for register player with POST http request")
	public void user_calls_for_register_player_with_post_http_request() {
		response=res.when()
				   .post("/Admin/PlayerRegisterByAdmin");
		
	}

	@Then("API register player call executed with {int} status code")
	public void api_register_player_call_executed_with_status_code(Integer statusCode) {
 Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		
	    		.assertThat().statusCode(statusCode)
	    
	            .extract()
	            
	            .response(); 
	             
	            finalresponse.asPrettyString();
		System.out.println("**************************Child admin successfully registered the player**************************");
	}
	
	
	@Given("create admin login the player player API payload")
	public void create_admin_login_the_player_player_api_payload() throws IOException {
		
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("email",cr.readConfigData("emailForLoginPlayer"));
		mainobject.put("password",cr.readConfigData("passwordForLoginPlayer"));
		mainobject.put("type",cr.readConfigData("type") );
		mainobject.put("deviceId",cr.readConfigData("deviceId"));
		
	    res = given(SpecBuilder.requestSpecification())
	    		
	    		.header("Authorization","Bearer "+childadmintoken)
	           
	                                    .body(mainobject);
		
		
	}

	@When("user calls for login player with POST http request")
	public void user_calls_for_login_player_with_post_http_request() {
		response=res.when()
				   .post("/Player/playerLogin");
		
		
	}

	@Then("API login player call executed with {int} status code")
	public void api_login_player_call_executed_with_status_code(Integer statusCode) {
Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		
	    		.assertThat().statusCode(statusCode)
	    
	            .extract()
	            
	            .response(); 
	             
	            finalresponse.asPrettyString();
	            
	            JsonPath jsonpath = finalresponse.jsonPath();
	             playerid = jsonpath.getString("userId");
	            System.out.println(playerid);
		System.out.println("**************************Child admin successfully login the player**************************");
		
	}



	
	@Given("update player API payload")
	public void update_player_api_payload() throws IOException {
		
HashMap<String, String> mainobject =new HashMap<String, String>();
		
		mainobject.put("id",playerid);
		mainobject.put("userName",cr.readConfigData("userNameOfPlayerLoginByAdmin"));
		mainobject.put("mobileNo",cr.readConfigData("mobileNoOfPlayerLoginByAdmin"));
		mainobject.put("email",cr.readConfigData("emailOfPlayerLoginByAdmin") );
		mainobject.put("password",cr.readConfigData("passwordOfPlayerLoginByAdmin") );
		mainobject.put("userType",cr.readConfigData("userTypeOfPlayerLoginByAdmin") );
		mainobject.put("name",cr.readConfigData("nameOfPlayerLoginByAdmin"));
		
res = given(SpecBuilder.requestSpecification())
	    		
	    		.header("Authorization","Bearer "+childadmintoken)
	    		.pathParam("pathkey",playerid)
	           
	                                    .body(mainobject);
	    
		
	}

	@When("user update player calls with PUT http request")
	public void user_update_player_calls_with_put_http_request() {
		response=res.when()
				   .put("/Admin/PlayerUpdateByAdmin/{pathkey}");
		
		
	}

	@Then("API update player call executed with {int} status code")
	public void api_update_player_call_executed_with_status_code(Integer statusCode) {
Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		
	    		.assertThat().statusCode(statusCode)
	    
	            .extract()
	            
	            .response(); 
	             
	            finalresponse.asPrettyString();
		System.out.println("**************************child admin successfully updated the register player**************************");
		
	}

	
	
	@Given("get a delete API payload")
	public void get_a_delete_api_payload() throws IOException {
		res=given()
				   
				   .spec(SpecBuilder.requestSpecification())
				   
				   .pathParam("pathkey",playerid)
					
					.header("Authorization","Bearer "+ childadmintoken);
				   
		
		
	}

	@When("user calls with DELETE http request")
	public void user_calls_with_delete_http_request() {
		response=res.when()
				.delete("/Admin/PlayerDeleteByAdmin/{pathkey}");
		
		
	}

	@Then("API delete register player call executed with {int} status code")
	public void api_delete_register_player_call_executed_with_status_code(Integer statusCode) {
		Response finalresponse = response.then()
				.spec(SpecBuilder.responseSpecBuilder())
				
				.assertThat().statusCode(statusCode)

		        .extract()
		        
		        .response(); 
		         
		        finalresponse.asPrettyString();
		        System.out.println("**************************Child admin successfully deleted the registered player**************************");
		

	}

	
	
	@Given("get a child admin delete API payload")
	public void get_a_child_admin_delete_api_payload() throws IOException {
	   res=given()
	   
	   .spec(SpecBuilder.requestSpecification())
	   
	   .pathParam("pathkey",childadminuserid)
		
		.header("Authorization","Bearer "+ childadmintoken);
	   
		
		
	}

	@When("user calls with  child admin DELETE http request")
	public void user_calls_with_child_admin_delete_http_request() {
		response=res.when()
				.delete("/Admin/{pathkey}"); 
		
	}

	@Then("API  child admin call be executed with {int} status code")
	public void api_child_admin_call_be_executed_with_status_code(Integer statusCode) {
		Response finalresponse = response.then()
		.spec(SpecBuilder.responseSpecBuilder())
		
		.assertThat().statusCode(statusCode)

        .extract()
        
        .response(); 
         
        finalresponse.asPrettyString();
        System.out.println("**************************super admin successfully deleted the registered child  admin**************************");
	
	}
	
	
	@Given("get a all player API payload")
	public void get_a_all_player_api_payload() throws IOException {
		res=given()
				   
				   .spec(SpecBuilder.requestSpecification())
					
					.header("Authorization","Bearer "+ superadmintoken);
				   
		
	    
	}

	@When("user calls with  all player GET http request")
	public void user_calls_with_all_player_get_http_request() {
		response=res.when()
				.get("/Player/playerGetAll"); 
		
	   
	}

	@Then("API  all player call be executed with {int} status code")
	public void api_all_player_call_be_executed_with_status_code(Integer statusCode) {
		
		Response finalresponse = response.then()
				
				.assertThat().statusCode(statusCode)
				
		        .extract()
		        
		        .response(); 
		JsonPath jsonvalue = finalresponse.jsonPath();
		
		String idcount = jsonvalue.getString("id");
		
		System.out.println("Total User present on DigiSimulations  "+idcount.length());
	
		        System.out.println("**************************super admin successfully get all player**************************");
	   
	}
	
	
	@Given(": create superadmin PlayerRoleUpdate API payload")
	public void create_superadmin_player_role_update_api_payload() throws IOException {
		
		           res=given()
				   
				   .spec(SpecBuilder.requestSpecification())
				   .header("Authorization","Bearer "+ superadmintoken)
				   .pathParam("pathkey","1378");
	
			
		
	}

	@When(": user superadmin PlayerRoleUpdate API calls with PUT http request")
	public void user_superadmin_player_role_update_api_calls_with_put_http_request() {
		response=res.when()
				.put("/Player/PlayerRoleUpdate/{pathkey}"); 
		
		
	}

	@Then(": superadmin PlayerRoleUpdate API call executed with {int} status code")
	public void superadmin_player_role_update_api_call_executed_with_status_code(Integer statusCode) {
	              response.then()
				.spec(SpecBuilder.responseSpecBuilder())
				
				.assertThat().statusCode(statusCode)

		        .extract()
		        
		        .response();
		
		
	}
	
	


		 }
			
	

	



