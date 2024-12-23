Feature: validate ProductStatus api

@Smoke
Scenario: verify if admin for ProductStatus login API is working
Given create for ProductStatus login api payload
When user calls with  admin for ProductStatus POST http request
Then admin for product masteProductStatus API call executed with 200 status code 

@Smoke
Scenario: verify if ProductStatusAdd  API is working
Given create  ProductStatusAdd api payload
When user calls with  ProductStatusAdd POST http request
Then ProductStatusAdd API call executed with 201 status code


@Smoke
Scenario: verify if ProductStatusGetAll  API is working
Given get  ProductStatusGetAll api payload
When user calls with  ProductStatusGetAll POST http request
Then ProductStatusGetAll API call executed with 200 status code

@Smoke
Scenario: verify if ProductStatusById  API is working
Given create  ProductStatusById api payload
When user calls with  ProductStatusById POST http request
Then ProductStatusById API call executed with 200 status code 

@Smoke
Scenario: verify if ProductStatusUpdate  API is working
Given get  ProductStatusUpdate api payload
When user calls with  ProductStatusUpdate POST http request
Then ProductStatusUpdate API call executed with 200 status code 

@Smoke
Scenario: verify if ProductStatusDelete  API is working
Given get  ProductStatusDelete api payload
When user calls with  ProductStatusDelete POST http request
Then ProductStatusDelete API call executed with 200 status code 