package utils;

import java.io.IOException;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
    @Step
	public static RequestSpecification requestSpecification() throws IOException {

		// RequestSpecification requestSpecification =these return also
		// RequestSpecification for that we add return it and change type of method

		return new RequestSpecBuilder()

				.setBaseUri("https://apitest.digisimulations.com")

				.setBasePath("/api/v1")

				.addHeader("Content-Type", "application/json")

				.log(LogDetail.ALL)
                
				.addFilter(new AllureRestAssured()) // Adding Allure filter
				
				.build();
	}
    @Step
	public static ResponseSpecification responseSpecBuilder() { 
		// Same process for return type

		return new ResponseSpecBuilder()

				.expectContentType(ContentType.JSON)

				.log(LogDetail.ALL)

				.build();

	}
}
