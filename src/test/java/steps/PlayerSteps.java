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
import pojo.ProfileDetailGetByUserId;
import pojo.RegisterUser;
import pojo.UpdateUser;
import utils.ConfigReader;
import utils.SpecBuilder;
public class PlayerSteps {
	RequestSpecification res;
	Response response;
	static String playertoken;
	static int playeruserrid;
	static  String otpvalue;
	ConfigReader cr = new ConfigReader();

	
	@Given("create player login api payload")
	public void create_player_login_api_payload() throws IOException {
			
			HashMap<String, String> mainobject = new HashMap<String, String>();
			mainobject.put("email",cr.readConfigData("Playeremail"));
			mainobject.put("password",cr.readConfigData("Playerpassword"));
			mainobject.put("type",cr.readConfigData("Playertype") );
			mainobject.put("deviceId",cr.readConfigData("PlayerdeviceId") );
			
		       res = given(SpecBuilder.requestSpecification())
		           
		                                    .body(mainobject);
		             
		}
	    
	

	@When("user player login API calls with POST http request")
	public void user_player_login_api_calls_with_post_http_request() {
		     response = res. when()
		        
	              .post("/Player/playerLogin");
	}

	@Then("player login API call executed with {int} status code")
	public void player_login_api_call_executed_with_status_code(Integer stscode) {
		
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

	@Given(": create player change password API payload")
	public void create_player_change_password_api_payload() throws IOException {
		
		HashMap<String, String> mainobject = new HashMap<String, String>();
		
		mainobject.put("oldPassword",cr.readConfigData("oldPassword"));
		mainobject.put("newPassword",cr.readConfigData("oldPassword"));
		
	       res = given(SpecBuilder.requestSpecification())
	    		                        .header("Authorization","Bearer "+ playertoken)
	           
	                                    .body(mainobject);
	    
	}

	@When(": user player change password API calls with POST http request")
	public void user_player_change_password_api_calls_with_post_http_request() {
		
		 response = res. when()
			        
	              .put("/Player/changePassword");
	    
	}

	@Then(": player change password API call executed with {int} status code")
	public void player_change_password_api_call_executed_with_status_code(Integer stscode) {
		
		 Response finalresponse = response.then()
		    		
		    		.spec(SpecBuilder.responseSpecBuilder())
		    		 
		    		.assertThat().statusCode(stscode)
		    
		            .extract()
		            
		            .response();       
		    
		             finalresponse.asPrettyString();
		    
	   
	}

	@Given(": get username api payload")
	public void get_username_api_payload() throws IOException {
		
		  res = given(SpecBuilder.requestSpecification())
				  
				  .pathParam("pathkey", playeruserrid);
		
	}

	@When(": user username check api calls with GET http request")
	public void user_username_check_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/Player/userNameCheck/{pathkey}");
		
	}

	@Then(": username check api call executed with {int} status code")
	public void username_check_api_call_executed_with_status_code(Integer stscode) {
		
		 Response finalresponse = response.then()
		    		
		    		.spec(SpecBuilder.responseSpecBuilder())
		    		 
		    		.assertThat().statusCode(stscode)
		    
		            .extract()
		            
		            .response();       
		    
		             finalresponse.asPrettyString();
		    
	   
	}
	
	@Given(": create player verify generate email otp payload")
	public void create_player_verify_generate_email_otp_payload() throws IOException {
		HashMap<String , String> mainobject = new HashMap<String, String>();
		mainobject.put("email",cr.readConfigData("emailForGenerateOTP"));
		
		res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization","Bearer "+ playertoken)
				
				.body(mainobject);
				  
	
		
	   
		
	}
	@When(": user player generate email otp API calls with PUT http request")
	public void user_player_generate_email_otp_api_calls_with_put_http_request() {
		
		response = res. when()
		        
	              .put("/Player/generateEmailOTP");
		
		
	}
	@Then(": player generate email otp API call executed with {int} status code")
	public void player_generate_email_otp_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();       
	    
	             finalresponse.asPrettyString();
	             
	             JsonPath jsonextractdata = finalresponse.jsonPath();
	               otpvalue = jsonextractdata.getString("otp");
	               System.out.println(otpvalue);
		
	}
	
	
	@Given(": create player verify correct otp payload")
	public void create_player_verify_correct_otp_payload() throws IOException {
		HashMap<String , String> mainobject = new HashMap<String, String>();
		mainobject.put("otp",otpvalue);
		
		
		res = given(SpecBuilder.requestSpecification())		
				  .header("Authorization","Bearer "+ playertoken)
				
				  .body(mainobject);
		
	}
	@When(": user player correct otp API calls with PUT http request")
	public void user_player_correct_otp_api_calls_with_put_http_request() {
		response = res. when()
		        
	              .put("/Player/verifyOTP");
		
	}
	@Then(": player correct otp API call executed with {int} status code")
	public void player_correct_otp_api_call_executed_with_status_code(Integer stscode) {

		Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();       
	    
	             finalresponse.asPrettyString();
		
	}
	

	@Given(": create player verify wrong otp payload")
	public void create_player_verify_wrong_otp_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				
				  .header("Authorization","Bearer "+ playertoken)
				
				  .body(playeruserrid);
		
	    
	}

	@When(": user player wrong otp API calls with PUT http request")
	public void user_player_wrong_otp_api_calls_with_put_http_request() {
		response = res. when()
		        
	              .put("/Player/verifyOTP");
	    
	}

	@Then(": player otp API call executed with {int} status code")
	public void player_otp_api_call_executed_with_status_code(Integer stscode) {
		
		Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();       
	    
	             finalresponse.asPrettyString();
	   
	}

	

	@Given(": create PlayerProfileDetails API payload")
	public void create_player_profile_details_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				
				  .header("Authorization","Bearer "+ playertoken);
				
	    
		
	}

	@When(": user PlayerProfileDetails API calls with GET request")
	public void user_player_profile_details_api_calls_with_get_request() {
		response = res. when()
		        
	              .get("/Player/ProfileDetailGetByUserId");
	
	}

	@Then(": PlayerProfileDetailsGetByUserId API call executed with {int} status code")
	public void player_profile_details_get_by_user_id_api_call_executed_with_status_code(Integer stscode) {
		
    ProfileDetailGetByUserId[] finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response().as(ProfileDetailGetByUserId[].class)  ;   
    
                 String usermail = finalresponse[0].getEmail();      
                 System.out.println(usermail);
                 
                 String useraccountid = finalresponse[0].getAccountId();
                 System.out.println(useraccountid);
	 
	}
	
	@Given(": create update user profile API payload")
	public void create_update_user_profile_api_payload() throws IOException {
		  UpdateUser userpojo = new UpdateUser();
		  userpojo.setFirstName(cr.readConfigData("firstNameOfProfile"));
			userpojo.setMiddleName(cr.readConfigData("middleNameOfProfile"));
			userpojo.setLastName(cr.readConfigData("lastNameOfProfile"));
			userpojo.setCityId(cr.readConfigData("cityIdOfProfile"));
		    userpojo .setStateId(cr.readConfigData("stateIdOfProfile"));
			userpojo.setCountryId(cr.readConfigData("countryIdOfProfile"));
			userpojo.setPinCode(cr.readConfigData("pinCodeOfProfile"));
			userpojo.setAddress(cr.readConfigData("addressOfProfile"));
			userpojo.setEmail(cr.readConfigData("emailOfProfile"));
		
		res = given(SpecBuilder.requestSpecification())
				
				  .header("Authorization","Bearer "+ playertoken)
				  .body(userpojo);
		
	}

	@When(": user update user profile API calls with PUT http request")
	public void user_update_user_profile_api_calls_with_put_http_request() {
		response = res. when()
		        
	              .put("/Player/profileUpdate");
	}

	@Then(":  update user profile API call executed with {int} status code")
	public void update_user_profile_api_call_executed_with_status_code(Integer stscode) {
		 Response finalresponse = response.then()
		    		
		    		.spec(SpecBuilder.responseSpecBuilder())
		    		 
		    		.assertThat().statusCode(stscode)
		    
		            .extract()
		            
		            .response();       
		    
		             finalresponse.asPrettyString();
	    
	}

	@Given(": create forgetByEmail API payload")
	public void create_forget_by_email_api_payload() throws IOException {
		
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("emailId", cr.readConfigData("emailIdForForget"));
		
		res = given(SpecBuilder.requestSpecification())
				
				  .body(mainobject);
	   
	}

	@When(": user update forgetByEmail API calls with PUT http request")
	public void user_update_forget_by_email_api_calls_with_put_http_request() {
		response = res. when()
		        
	              .put("/Player/forgetByEmail");
	}

	@Then(": forgetByEmail API call executed with {int} status code")
	public void forget_by_email_api_call_executed_with_status_code(Integer stscode) {
		 Response finalresponse = response.then()
		    		
		    		.spec(SpecBuilder.responseSpecBuilder())
		    		 
		    		.assertThat().statusCode(stscode)
		    
		            .extract()
		            
		            .response();       
		    
		             finalresponse.asPrettyString();
	    
	}

	@Given(": create wrong playerChangeEmail API payload")
	public void create_wrong_player_change_email_api_payload() throws IOException {
		
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email","haiderintekhab08@gmail.com");
		mainobject.put("otp","561215");
		
		res = given(SpecBuilder.requestSpecification())
				
				.header("Authorization","Bearer "+ playertoken)
				
				  .body(mainobject);
	}

	@When(": user playerChangeEmail API calls with PUT http request")
	public void user_player_change_email_api_calls_with_put_http_request() {
		response = res. when()
		        
	              .put("/Player/playerChangeEmail");
	    
	}

	@Then(": playerChangeEmail API call executed with {int} status code")
	public void player_change_email_api_call_executed_with_status_code(Integer stscode) {
		Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();       
	    
	             finalresponse.asPrettyString();
	   
	}
	
	@Given(": create playerRegister API payload")
	public void create_player_register_api_payload() throws IOException {
		
		RegisterUser registerpojo = new RegisterUser();
		registerpojo.setCountryCallingCodeId(cr.readConfigData("countryCallingCodeIdForRegister"));
		registerpojo.setUserName(cr.readConfigData("userNameForRegister"));
		registerpojo.setMobileNo(cr.readConfigData("mobileNoForRegister"));
		registerpojo.setEmail(cr.readConfigData("emailForRegister"));
		registerpojo.setPassword(cr.readConfigData("passwordForRegister"));
		registerpojo.setName(cr.readConfigData("nameForRegister"));
		registerpojo.setDob(cr.readConfigData("dobForRegister"));
		registerpojo.setType(cr.readConfigData("typeForRegister"));
		
		res = given(SpecBuilder.requestSpecification())
				
				  .body(registerpojo);
	   
		
		
	   
	}

	@When(": user playerRegister API calls with POST http request")
	public void user_player_register_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .post("/Player/playerRegister");
	    
		
	    
	}

	@Then(": playerRegister API call executed with {int} status code")
	public void player_register_api_call_executed_with_status_code(Integer stscode) {
Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();       
	    
	             finalresponse.asPrettyString();
	    
		
		
		
	}
	
	
	@Given(": create PlayerAccountDelete API payload")
	public void create_player_account_delete_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				.header("Authorization","Bearer "+ playertoken);
				
				 
	    
		
	}
	@When(": user PlayerAccountDelete API calls with POST http request")
	public void user_player_account_delete_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .delete("/Player/PlayerAccountDelete");
		
		
	}
	@Then(": PlayerAccountDelete API call executed with {int} status code")
	public void player_account_delete_api_call_executed_with_status_code(Integer stscode) {
Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();       
	    
	             finalresponse.asPrettyString();
	    
		
		
	}
	
	
	@Given(": create PlayerLastLoginDetails API payload")
	public void create_player_last_login_details_api_payload() throws IOException {
		res = given(SpecBuilder.requestSpecification())
				.header("Authorization","Bearer "+ playertoken);
		
		
	}
	@When(": user PlayerLastLoginDetails API calls with POST http request")
	public void user_player_last_login_details_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .get("/Player/PlayerLastLoginDetails");
		
		
	}
	@Then(": PlayerLastLoginDetails API call executed with {int} status code")
	public void player_last_login_details_api_call_executed_with_status_code(Integer stscode) {
          Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();  
          
          
          JsonPath jsonextract = finalresponse.jsonPath();
          String idcount = jsonextract.getString("id");
         System.out.println( idcount.length());
          

		
		
	}
	
	@Given(": create generateOTP API payload")
	public void create_generate_otp_api_payload() throws IOException {
		res = given(SpecBuilder.requestSpecification())
				.header("Authorization","Bearer "+ playertoken);
		
	}
	@When(": user generateOTP API calls with POST http request")
	public void user_generate_otp_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .post("/Player/generateOTP");
		
		
	}
	@Then(": generateOTP API call executed with {int} status code")
	public void generate_otp_api_call_executed_with_status_code(Integer stscode) {
		             response.then()
		    		
		    		.spec(SpecBuilder.responseSpecBuilder())
		    		 
		    		.assertThat().statusCode(stscode)
		    
		            .extract()
		            
		            .response();  
		
	}
	
	@Given(": create changeDisplayName with wrong body format API payload")
	public void create_change_display_name_with_wrong_body_format_api_payload() throws IOException {
		res = given(SpecBuilder.requestSpecification())
				.body("{\r\n"
						+ "  \"user Name\": \"Haider@1234\"\r\n"
						+ "}")
				.header("Authorization","Bearer "+ playertoken);
		
		
	}

	@When(": user changeDisplayName with wrong body format API calls with PUT http request")
	public void user_change_display_name_with_wrong_body_format_api_calls_with_put_http_request() {
		response = res. when()
		        
	              .put("/Player/changeDisplayName");
		
		
	}

	@Then(": changeDisplayName with wrong body format API call executed with {int} status code")
	public void change_display_name_with_wrong_body_format_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
 		
 		.spec(SpecBuilder.responseSpecBuilder())
 		 
 		.assertThat().statusCode(stscode)
 
         .extract()
         
         .response();  
	
	}
	

}
