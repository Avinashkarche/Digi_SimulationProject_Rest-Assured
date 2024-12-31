Feature: validate ProductOrder api

@Smoke
Scenario:  verify if ProductOrder player login API is working
Given create ProductOrder player login api payload
When user ProductOrder  player login API calls with POST http request
Then  ProductOrder player login API call executed with 200 status code 

@Smoke
Scenario: verify if ProductOrder admin login API is working
Given create ProductOrder login api payload
When user ProductOrder calls with super admin POST http request
Then ProductOrder API call executed with 200 status code 

@Smoke
Scenario: verify if ProductOrder API is working 
Given : create ProductOrder API payload
When : user ProductOrder API calls with POST http request
Then : ProductOrder API call executed with 201 status code

@Smoke
Scenario: verify if GetAllOrder API is working 
Given : get GetAllOrder API payload
When : user GetAllOrder API calls with GET http request
Then : GetAllOrder API call executed with 200 status code

@Smoke
Scenario: verify if GetAllOrderByUser API is working 
Given : get GetAllOrderByUser API payload
When : user GetAllOrderByUser API calls with GET http request
Then : GetAllOrderByUser API call executed with 200 status code

@Smoke
Scenario: verify if ProductOrderSingleBuy API is working 
Given : get ProductOrderSingleBuy API payload
When : user ProductOrderSingleBuy API calls with GET http request
Then : ProductOrderSingleBuy API call executed with 201 status code
