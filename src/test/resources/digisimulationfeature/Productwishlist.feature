Feature: validate ProductWishList API

@Smoke
Scenario: verify if ProductWishList player login API is working
Given create ProductWishList login api payload
When user calls with ProductWishList player POST http request
Then ProductWishList API call executed with 200 status code 

@Smoke
Scenario: verify if ProductWishListAdd API is working
Given create ProductWishListAdd api payload
When user calls with ProductWishListAdd POST http request
Then ProductWishListAdd API call executed with 201 status code 

@Smoke
Scenario: verify if ProductWishListGetAll API is working
Given get ProductWishListGetAll api payload
When user calls with ProductWishListGetAll GET http request
Then ProductWishListGetAll API call executed with 200 status code 

@Smoke
Scenario: verify if RemoveWishList API is working
Given get RemoveWishList api payload
When user calls with RemoveWishList DELETE http request
Then RemoveWishList API call executed with 200 status code

