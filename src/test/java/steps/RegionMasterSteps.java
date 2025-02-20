package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.RegionMaster;
import utils.SpecBuilder;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class RegionMasterSteps {
	
	RequestSpecification res;
	Response response;
	
	
	@Given(": get the CountryCallingCode API payload")
	public void get_the_country_calling_code_api_payload() throws IOException {
	
		       res=given()
				
				.spec(SpecBuilder.requestSpecification());
		
	   
	}

	@When(": CountryCallingCode call with GET request")
	public void country_calling_code_call_with_get_request() {
		
		 response = res.when()
		.get("/RegionMaster");
	   
	}

	@Then(": CountryCallingCode API call executed with {int} status code")
	public void country_calling_code_api_call_executed_with_status_code(Integer stscode) {
		
		Response finalString = response.then()		
 		 
 		.assertThat().statusCode(stscode)
 
         .extract()
         
         .response();  
		
		RegionMaster[] pojoObject = finalString.as(RegionMaster[].class);
		
		 String searchCountry = "India";  // Searching for India

	        boolean countryFound = false;
	        
	        // Iterate through the array to find the country and print its calling code
	        
	        for (RegionMaster region : pojoObject) 
	        {
	        	
	            if (region.getCountryName().equalsIgnoreCase(searchCountry)) 
	            {
	                // Country found, print its calling code
	            	
	            	
	                String code = region.getCallingCode();
	                System.out.println("Country: " + searchCountry + " - Calling Code: " + code);
	                countryFound = true;
	                
	                break;  // Stop iterating once the country is found
	            }
	        }
	        
	        if (!countryFound) {
	            System.out.println("Country not found: " + searchCountry);
	        }
	        
	        System.out.println("Successfully fetched country code with its name.");
	    }
		 
		 
	    
	

	@Given(": get the CountryGetAll API payload")
	public void get_the_country_get_all_api_payload() throws IOException {
                res=given()
				
				.spec(SpecBuilder.requestSpecification());
	}

	@When(": CountryGetAll call with GET request")
	public void country_get_all_call_with_get_request() {
		 response = res.when()
					.get("/RegionMaster/country");
	}

	@Then(": CountryGetAll API call executed with {int} status code")
	public void country_get_all_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
	 		
	 		.spec(SpecBuilder.responseSpecBuilder())
	 		 
	 		.assertThat().statusCode(stscode)
	 
	         .extract()
	         
	         .response();   
	}

	@Given(": get the StateGetAllByCountryId API payload")
	public void get_the_state_get_all_by_country_id_api_payload() throws IOException {
                 res=given()
				
				.spec(SpecBuilder.requestSpecification());
	}

	@When(": StateGetAllByCountryId call with GET request")
	public void state_get_all_by_country_id_call_with_get_request() {
		response = res.when()
				.get("/RegionMaster/state/101");
	}

	@Then(": StateGetAllByCountryId API call executed with {int} status code")
	public void state_get_all_by_country_id_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
	 		
	 		.spec(SpecBuilder.responseSpecBuilder())
	 		 
	 		.assertThat().statusCode(stscode)
	 
	         .extract()
	         
	         .response();  
	}

	@Given(": get the DistrictGetAllByStateId API payload")
	public void get_the_district_get_all_by_state_id_api_payload() throws IOException {
		
                  res=given()				
				.spec(SpecBuilder.requestSpecification());
	}

	@When(": DistrictGetAllByStateId call with GET request")
	public void district_get_all_by_state_id_call_with_get_request() {
		response = res.when()
				.get("/RegionMaster/district/34");
	}

	@Then(": DistrictGetAllByStateId API call executed with {int} status code")
	public void district_get_all_by_state_id_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
	 		
	 		.spec(SpecBuilder.responseSpecBuilder())
	 		 
	 		.assertThat().statusCode(stscode)
	 
	         .extract()
	         
	         .response();  
	}

	@Given(": get the CityGetAllByStateId API payload")
	public void get_the_city_get_all_by_state_id_api_payload() throws IOException {
		
		        res=given()				
				.spec(SpecBuilder.requestSpecification());
	}

	@When(": CityGetAllByStateId call with GET request")
	public void city_get_all_by_state_id_call_with_get_request() {
		         response = res.when()
				.get("/RegionMaster/city/4008");
	}
		

	@Then(": CityGetAllByStateId API call executed with {int} status code")
	public void city_get_all_by_state_id_api_call_executed_with_status_code(Integer stscode) {
		 response.then()
	 		
	 		.spec(SpecBuilder.responseSpecBuilder())
	 		 
	 		.assertThat().statusCode(stscode)
	 
	         .extract()
	         
	         .response();
	}

}
