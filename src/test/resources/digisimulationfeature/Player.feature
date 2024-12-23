Feature: validate player api

@Smoke
Scenario:  verify if player login API is working
Given create player login api payload
When user player login API calls with POST http request
Then  player login API call executed with 200 status code 

@Smoke
Scenario: verify if player change password API is working 
Given : create player change password API payload
When : user player change password API calls with POST http request
Then : player change password API call executed with 200 status code

@Smoke
Scenario: verify username check api is working
Given : get username api payload
When : user username check api calls with GET http request
Then : username check api call executed with 200 status code

@Smoke
Scenario: verify player generate email otp  API is working
Given : create player verify generate email otp payload
When : user player generate email otp API calls with PUT http request
Then : player generate email otp API call executed with 200 status code

@Smoke
Scenario: verify player correct otp  API is working
Given : create player verify correct otp payload
When : user player correct otp API calls with PUT http request
Then : player correct otp API call executed with 200 status code

@Smoke
Scenario: verify player wrong otp  API is working
Given : create player verify wrong otp payload
When : user player wrong otp API calls with PUT http request
Then : player otp API call executed with 400 status code

@Smoke
Scenario: verify PlayerProfileDetailsGetByUserId API is working 
Given : create PlayerProfileDetails API payload
When : user PlayerProfileDetails API calls with GET request
Then : PlayerProfileDetailsGetByUserId API call executed with 200 status code

@Smoke
Scenario: verify update user profile API is working 
Given : create update user profile API payload
When : user update user profile API calls with PUT http request
Then :  update user profile API call executed with 200 status code

@Smoke
Scenario: verify player forgetByEmail API is working 
Given : create forgetByEmail API payload
When : user update forgetByEmail API calls with PUT http request
Then : forgetByEmail API call executed with 200 status code

@Smoke
Scenario: verify at change mail time giving wrong OTP playerChangeEmail API is working 
Given : create wrong playerChangeEmail API payload
When : user playerChangeEmail API calls with PUT http request
Then : playerChangeEmail API call executed with 400 status code

@Smoke 
Scenario: verify user registration with same mobile number and same mail id user register API is working
Given : create playerRegister API payload
When : user playerRegister API calls with POST http request
Then : playerRegister API call executed with 409 status code 

@Smoke 
Scenario: verify user PlayerAccountDelete API is working
Given : create PlayerAccountDelete API payload
When : user PlayerAccountDelete API calls with POST http request
Then : PlayerAccountDelete API call executed with 200 status code

@Smoke 
Scenario: verify user PlayerLastLoginDetails API is working
Given : create PlayerLastLoginDetails API payload
When : user PlayerLastLoginDetails API calls with POST http request
Then : PlayerLastLoginDetails API call executed with 200 status code

@Smoke 
Scenario: verify user generateOTP API is working
Given : create generateOTP API payload
When : user generateOTP API calls with POST http request
Then : generateOTP API call executed with 200 status code

@Smoke 
Scenario: verify user changeDisplayName with wrong body format API is working
Given : create changeDisplayName with wrong body format API payload
When : user changeDisplayName with wrong body format API calls with PUT http request
Then : changeDisplayName with wrong body format API call executed with 400 status code








 







































