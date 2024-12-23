Feature: validate Product api

@Smoke
Scenario: verify if admin for product master login API is working
Given create for product master login api payload
When user calls with  admin for product master POST http request
Then admin for product master API call executed with 200 status code 

@Smoke
Scenario: verify if AddProductType  API is working
Given create  AddProductType api payload
When user calls with  AddProductType POST http request
Then AddProductType API call executed with 201 status code


@Smoke
Scenario: verify if GetAllProductType  API is working
Given get  GetAllProductType api payload
When user calls with  GetAllProductType POST http request
Then GetAllProductType API call executed with 200 status code

@Smoke
Scenario: verify if UpdateProductType  API is working
Given create  UpdateProductType api payload
When user calls with  UpdateProductType POST http request
Then UpdateProductType API call executed with 200 status code 

@Smoke
Scenario: verify if GetProductTypeById  API is working
Given get  GetProductTypeById api payload
When user calls with  GetProductTypeById POST http request
Then GetProductTypeById API call executed with 200 status code 

@Smoke
Scenario: verify if DeleteProductType  API is working
Given get  DeleteProductType api payload
When user calls with  DeleteProductType POST http request
Then DeleteProductType API call executed with 200 status code 




