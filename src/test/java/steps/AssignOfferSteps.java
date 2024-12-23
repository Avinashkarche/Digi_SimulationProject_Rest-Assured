package steps;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AssignOfferGetAll;
import utils.ConfigReader;
import utils.SpecBuilder;

public class AssignOfferSteps {
	
	RequestSpecification res;
	Response response;
	static String superadmintoken;
	static Integer assignofferid ;
	static Integer secondassignofferid ;
	static Integer productid;
	static Integer offerid;
	
	ConfigReader cr = new ConfigReader();
	
	@Given("create admin for AssignOffer login api payload")
	public void create_admin_for_assign_offer_login_api_payload() throws IOException {
		
			HashMap<String, String> mainobject = new HashMap<String, String>();
			mainobject.put("email", "avinashkarche@gmail.com");
			mainobject.put("password","Avi@123");
			
		    res = given(SpecBuilder.requestSpecification())
		           
		                                    .body(mainobject);
		
	}

	@When("user calls with admin for AssignOffer POST http request")
	public void user_calls_with_admin_for_assign_offer_post_http_request() {
		response = res. when()
		        
	              .post("/Admin/adminlogin");

		
	}

	@Then("admin for AssignOffer API call executed with {int} status code")
	public void admin_for_assign_offer_api_call_executed_with_status_code(Integer stscode) {
		
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

		
	

	@Given("create AssignOffer API payload")
	public void create_assign_offer_api_payload() throws IOException {
		
		HashMap<String, String> mainobject = new HashMap<String, String>();
		mainobject.put("productId",cr.readConfigData("productIdForAssignOffer"));
		mainobject.put("offerId",cr.readConfigData("offerIdForAssignOffer"));
		mainobject.put("offerStartDate",cr.readConfigData("offerStartDateForAssignOffer"));
		mainobject.put("offerEndDate",cr.readConfigData("offerEndDateForAssignOffer"));
		
		
	    res = given(SpecBuilder.requestSpecification())
	    		                        .header("Authorization","Bearer "+superadmintoken)
	           
	                                    .body(mainobject);
	   
		
		
	}

	@When("user AssignOffer API calls with POST http request")
	public void user_assign_offer_api_calls_with_post_http_request() {
		response = res. when()
		        
	              .post("/AssignOfferMasters");
		
		
	}

	@Then("AssignOffer API call executed with the {int} status code")
	public void assign_offer_api_call_executed_with_the_status_code(Integer stscode) {
	    
		 Response finalresponse = response.then()
		    		
		    		.spec(SpecBuilder.responseSpecBuilder())
		    		 
		    		.assertThat().statusCode(stscode)
		    
		            .extract()
		            
		            .response();       
		    
		             finalresponse.asPrettyString();
		
	}

	@Given("get AssignOfferGetAll API payload")
	public void get_assign_offer_get_all_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
                .header("Authorization","Bearer "+superadmintoken);

	   
		
		
	}

	@When("user AssignOfferGetAll API calls with GET http request")
	public void user_assign_offer_get_all_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/AssignOfferMasters");
		
		
		
	}

	@Then("AssignOfferGetAll API call executed with the {int} status code")
	public void assign_offer_get_all_api_call_executed_with_the_status_code(Integer stscode) {
		
		AssignOfferGetAll[] assignoffergetall = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response().as(AssignOfferGetAll[].class);  
		
		   assignofferid = assignoffergetall[1].getAssignOfferId();
		
		  System.out.println(assignofferid);
	             
	   
	}

	@Given("get AssignOffergetById API payload")
	public void get_assign_offerget_by_id_api_payload() throws IOException {
	    

		res = given(SpecBuilder.requestSpecification())
				.pathParam("pathkey",assignofferid )
                .header("Authorization","Bearer "+superadmintoken);

		
	}

	@When("user AssignOffergetById API calls with GET http request")
	public void user_assign_offerget_by_id_api_calls_with_get_http_request() {
		response = res. when()
		        
	              .get("/AssignOfferMasters/{pathkey}");
		
		
	}

	@Then("AssignOffergetById API call executed with the {int} status code")
	public void assign_offerget_by_id_api_call_executed_with_the_status_code(Integer stscode) {

		AssignOfferGetAll[] assignofferGetall = response.then()
		    		
		    		.spec(SpecBuilder.responseSpecBuilder())
		    		 
		    		.assertThat().statusCode(stscode)
		    
		            .extract()
		            
		            .response().as(AssignOfferGetAll[].class);  
		
		String offername = assignofferGetall[0].getOfferName();
		
		       System.out.println(offername);
		
		    assertEquals(offername, "Updated Offer Of New Year");
		    
		     secondassignofferid = assignofferGetall[0].getAssignOfferId();
		    
		     productid = assignofferGetall[0].getProductId();
		    
		     offerid = assignofferGetall[0].getOfferId();
		   
		    
		    
		             
		
		
	}

	@Given("create UpdateAssignOffer API payload")
	public void create_update_assign_offer_api_payload() throws IOException {
		
	    AssignOfferGetAll offergetall = new AssignOfferGetAll();
	    
	    offergetall.setAssignOfferId(secondassignofferid);
	    offergetall.setProductId(productid);
	    offergetall.setOfferId(offerid);
	    offergetall.setOfferStartDate(cr.readConfigData("offerStartDateForUpdate"));
	    offergetall.setOfferEndDate(cr.readConfigData("offerEndDateForUpdate"));
	    
	    
		res = given(SpecBuilder.requestSpecification())
				.pathParam("pathkey",secondassignofferid )
				.body(offergetall)
                .header("Authorization","Bearer "+superadmintoken);
		
	}

	@When("user UpdateAssignOffer API calls with PUT http request")
	public void user_update_assign_offer_api_calls_with_put_http_request() {
	    
		response = res. when()
		        
	              .put("/AssignOfferMasters/{pathkey}");
		
	}

	@Then("UpdateAssignOffer API call executed with the {int} status code")
	public void update_assign_offer_api_call_executed_with_the_status_code(Integer stscode) {
		 Response finalresponse = response.then()
		    		
		    		.spec(SpecBuilder.responseSpecBuilder())
		    		 
		    		.assertThat().statusCode(stscode)
		    
		            .extract()
		            
		            .response();       
		    
		             finalresponse.asPrettyString();
		
		
	}

	@Given("get DeleteAssignOffer API payload")
	public void get_delete_assign_offer_api_payload() throws IOException {
		
		res = given(SpecBuilder.requestSpecification())
				.pathParam("pathkey",secondassignofferid )
				
                .header("Authorization","Bearer "+superadmintoken);
		
		
	}

	@When("user DeleteAssignOffer API calls with DELETE http request")
	public void user_delete_assign_offer_api_calls_with_delete_http_request() {
	    
		response = res. when()
		        
	              .delete("/AssignOfferMasters/{pathkey}");
		
	}

	@Then("DeleteAssignOffer API call executed with the {int} status code")
	public void delete_assign_offer_api_call_executed_with_the_status_code(Integer stscode) {
		Response finalresponse = response.then()
	    		
	    		.spec(SpecBuilder.responseSpecBuilder())
	    		 
	    		.assertThat().statusCode(stscode)
	    
	            .extract()
	            
	            .response();       
	    
	             finalresponse.asPrettyString();
	
		
		
	}

}
