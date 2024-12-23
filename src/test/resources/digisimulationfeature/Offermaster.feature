Feature: verify offermaster API

@Smoke
Scenario: verify if admin login API is working
Given create admin login api payload
When user calls with admin POST http request
Then admin API call executed with 200 status code 


@Smoke
Scenario: verify if AddOffer API is working
Given create AddOffer api payload
When user calls with AddOffer POST http request
Then  AddOffer API call executed with 201 status code

@Smoke
Scenario: verify if OfferMaster API is working
Given get OfferMaster api payload
When user calls with OfferMaster GET http request
Then  OfferMaster API call executed with 200 status code 


@Smoke
Scenario: verify if GetOfferById API is working
Given get GetOfferById api payload
When user calls with GetOfferById GET http request
Then  GetOfferById API call executed with 200 status code 

@Smoke
Scenario: verify if UpdateOffer API is working
Given update UpdateOffer api payload
When user calls with UpdateOffer PUT http request
Then  UpdateOffer API call executed with 200 status code 

@Smoke
Scenario: verify if DeleteOffer API is working
Given create DeleteOffer api payload
When user calls with DeleteOffer DELETE http request
Then  DeleteOffer API call executed with 200 status code 