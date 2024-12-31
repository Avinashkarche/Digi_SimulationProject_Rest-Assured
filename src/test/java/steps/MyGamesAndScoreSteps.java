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

public class MyGamesAndScoreSteps {
	
	RequestSpecification res;
	Response response;
	static String playertoken;
	static int playeruserrid;
	static  String otpvalue;
	ConfigReader cr = new ConfigReader();
	
	@Given("create MyGameAndScore player login api payload")
	public void create_my_game_and_score_player_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email",cr.readConfigData("Playeremail"));
		mainobject.put("password",cr.readConfigData("Playerpassword"));
		mainobject.put("type",cr.readConfigData("Playertype") );
		mainobject.put("deviceId",cr.readConfigData("PlayerdeviceId") );
		
	       res = given(SpecBuilder.requestSpecification())
	           
	                                    .body(mainobject);
	}

	@When("user MyGameAndScore  player login API calls with POST http request")
	public void user_my_game_and_score_player_login_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .post("/Player/playerLogin");
	}

	@Then("MyGameAndScore player login API call executed with {int} status code")
	public void my_game_and_score_player_login_api_call_executed_with_status_code(Integer stscode) {
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

	@Given(": get MyGameGetByUserId API payload")
	public void get_my_game_get_by_user_id_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				  
				.header("Authorization","Bearer "+ playertoken);
	}

	@When(": user MyGameGetByUserId API calls with POST http request")
	public void user_my_game_get_by_user_id_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .get("/MyGame/MyGameGetAllByUserId");
	}

	@Then(": MyGameGetByUserId API call executed with {int} status code")
	public void my_game_get_by_user_id_api_call_executed_with_status_code(Integer stscode) {
		response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();
	}

	@Given(": get MyGameGetByUserIdForMobile API payload")
	public void get_my_game_get_by_user_id_for_mobile_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				  
				.header("Authorization","Bearer "+ playertoken);

	}

	@When(": user MyGameGetByUserIdForMobile API calls with POST http request")
	public void user_my_game_get_by_user_id_for_mobile_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .get("/MyGame/MyGameGetByUserIdForMobile");
	}

	@Then(": MyGameGetByUserIdForMobile API call executed with {int} status code")
	public void my_game_get_by_user_id_for_mobile_api_call_executed_with_status_code(Integer stscode) {
          response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();
	}

	@Given(": get GetAllGameScoreByUserId API payload")
	public void get_get_all_game_score_by_user_id_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				  
				.header("Authorization","Bearer "+ playertoken);
	}

	@When(": user GetAllGameScoreByUserId API calls with POST http request")
	public void user_get_all_game_score_by_user_id_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .get("/Score/GetAllGameScoreByUserId/125");
	}

	@Then(": GetAllGameScoreByUserId API call executed with {int} status code")
	public void get_all_game_score_by_user_id_api_call_executed_with_status_code(Integer stscode) {
response.then()
		
		.spec(SpecBuilder.responseSpecBuilder())
		 
		.assertThat().statusCode(stscode)

        .extract()
        
        .response();
	}

}
