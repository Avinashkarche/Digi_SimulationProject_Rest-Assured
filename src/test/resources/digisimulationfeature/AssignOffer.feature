Feature: validate AssignOffer API
@Smoke
Scenario: verify if admin for AssignOffer login API is working
Given create admin for AssignOffer login api payload
When user calls with admin for AssignOffer POST http request
Then admin for AssignOffer API call executed with 200 status code 


@Smoke
Scenario: verify AssignOffer API is working
Given create AssignOffer API payload 
When user AssignOffer API calls with POST http request
Then AssignOffer API call executed with the 201 status code

@Smoke
Scenario: verify AssignOfferGetAll API is working
Given get AssignOfferGetAll API payload 
When user AssignOfferGetAll API calls with GET http request
Then AssignOfferGetAll API call executed with the 200 status code

@Smoke
Scenario: verify AssignOffergetById API is working
Given get AssignOffergetById API payload 
When user AssignOffergetById API calls with GET http request
Then AssignOffergetById API call executed with the 200 status code

@Smoke
Scenario: verify UpdateAssignOffer API is working
Given create UpdateAssignOffer API payload 
When user UpdateAssignOffer API calls with PUT http request
Then UpdateAssignOffer API call executed with the 200 status code

@Smoke
Scenario: verify DeleteAssignOffer API is working
Given get DeleteAssignOffer API payload 
When user DeleteAssignOffer API calls with DELETE http request
Then DeleteAssignOffer API call executed with the 200 status code

