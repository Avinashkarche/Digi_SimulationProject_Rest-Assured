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
import pojo.GetAllOffers;
import utils.ConfigReader;
import utils.SpecBuilder;

public class OfferMasterSteps {
	
	 RequestSpecification res;
	 Response response;
	 static String superadmintoken;
	 static Integer offerId ;
	 
	 
	 ConfigReader cr = new ConfigReader();
	
	@Given("create admin login api payload")
	public void create_admin_login_api_payload() throws IOException {
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("email", "avinashkarche@gmail.com");
		mainobject.put("password","Avi@123");
		
	     res = given(SpecBuilder.requestSpecification())
	           
	                                    .body(mainobject);
		
	}

	@When("user calls with admin POST http request")
	public void user_calls_with_admin_post_http_request() {
		 response = res. when()
		        
	              .post("/Admin/adminlogin");
		
	}

	@Then("admin API call executed with {int} status code")
	public void admin_api_call_executed_with_status_code(Integer stscode) {
		
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

	

	@Given("create AddOffer api payload")
	public void create_add_offer_api_payload() throws IOException {
		
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("offerName",cr.readConfigData("offerName") );
		mainobject.put("offerPrice",cr.readConfigData("offerPrice"));
		mainobject.put("description",cr.readConfigData("description"));
		mainobject.put("offerType",cr.readConfigData("offerType"));
		
		
	     res = given(SpecBuilder.requestSpecification())
	           
				 .header("Authorization","Bearer "+superadmintoken)
				 .body(mainobject);
		
		
	    
		
	}

	@When("user calls with AddOffer POST http request")
	public void user_calls_with_add_offer_post_http_request() throws IOException {
		response = res. when()
		        
	              .post("/OfferMaster");
		
		
	}

	@Then("AddOffer API call executed with {int} status code")
	public void add_offer_api_call_executed_with_status_code(Integer stscode) {
		  response.then()
  		
  		.spec(SpecBuilder.responseSpecBuilder())
  		 
  		.assertThat().statusCode(stscode)
  
          .extract()
          
          .response();       
  
		
		
	}
	
	@Given("get OfferMaster api payload")
	public void get_offer_master_api_payload() throws IOException {
		
		 res = given(SpecBuilder.requestSpecification())
		           
				 .header("Authorization","Bearer "+superadmintoken);
		
		
	}

	@When("user calls with OfferMaster GET http request")
	public void user_calls_with_offer_master_get_http_request() {
		response = res. when()
		        
	              .get("/OfferMaster");
		
		
	}

	@Then("OfferMaster API call executed with {int} status code")
	public void offer_master_api_call_executed_with_status_code(Integer stscode) {
		
                 GetAllOffers[] alloffers = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response().as(GetAllOffers[].class); 
                 
                 offerId = alloffers[6].getOfferId();
	             System.out.println(offerId);
		
	}

	@Given("get GetOfferById api payload")
	public void get_get_offer_by_id_api_payload() throws IOException {
		 res = given(SpecBuilder.requestSpecification())
				 
		         .pathParam("pathkey", offerId)  
				 .header("Authorization","Bearer "+superadmintoken);
		
		
	}

	@When("user calls with GetOfferById GET http request")
	public void user_calls_with_get_offer_by_id_get_http_request() {
		response = res. when()
		        
	              .get("/OfferMaster/{pathkey}");
		
		
		
	}

	@Then("GetOfferById API call executed with {int} status code")
	public void get_offer_by_id_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
	  		
	  		.spec(SpecBuilder.responseSpecBuilder())
	  		 
	  		.assertThat().statusCode(stscode)
	  
	          .extract()
	          
	          .response();       
	  
		
		
	}

	@Given("update UpdateOffer api payload")
	public void update_update_offer_api_payload() throws IOException {
		HashMap<String, Object> mainobject = new HashMap<String, Object>();
		mainobject.put("offerId", offerId);
		mainobject.put("offerName",cr.readConfigData("offerNameForUpdate") );
		mainobject.put("offerPrice",cr.readConfigData("offerPriceForUpdate"));
		mainobject.put("description",cr.readConfigData("descriptionForUpdate"));
		mainobject.put("offerType",cr.readConfigData("offerTypeForUpdate"));
		
		res = given(SpecBuilder.requestSpecification())
				  .body(mainobject)
				  .pathParam("pathkey", offerId)  
		           
				  .header("Authorization","Bearer "+superadmintoken);
		
		
	}

	@When("user calls with UpdateOffer PUT http request")
	public void user_calls_with_update_offer_put_http_request() {
		response = res. when()
		        
	              .put("/OfferMaster/{pathkey}");
		
		
	}

	@Then("UpdateOffer API call executed with {int} status code")
	public void update_offer_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
	  		
	  		.spec(SpecBuilder.responseSpecBuilder())
	  		 
	  		.assertThat().statusCode(stscode)
	  
	          .extract()
	          
	          .response();   
		
		
	}

	@Given("create DeleteOffer api payload")
	public void create_delete_offer_api_payload() throws IOException {
		res = given(SpecBuilder.requestSpecification())
				  
				  .pathParam("pathkey", offerId)  
		           
				  .header("Authorization","Bearer "+superadmintoken);
	
	
	}

	@When("user calls with DeleteOffer DELETE http request")
	public void user_calls_with_delete_offer_delete_http_request() {
		response = res. when()
		        
	              .delete("/OfferMaster/{pathkey}");
		
		
	}

	@Then("DeleteOffer API call executed with {int} status code")
	public void delete_offer_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
	  		
	  		.spec(SpecBuilder.responseSpecBuilder())
	  		 
	  		.assertThat().statusCode(stscode)
	  
	          .extract()
	          
	          .response();   
		
		
		
	}

}
