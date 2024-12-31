Feature: validate MyGameAndScore api

@Smoke
Scenario:  verify if MyGameAndScore player login API is working
Given create MyGameAndScore player login api payload
When user MyGameAndScore  player login API calls with POST http request
Then  MyGameAndScore player login API call executed with 200 status code 

@Smoke
Scenario: verify if MyGameGetByUserId API is working 
Given : get MyGameGetByUserId API payload
When : user MyGameGetByUserId API calls with POST http request
Then : MyGameGetByUserId API call executed with 200 status code

@Smoke
Scenario: verify if MyGameGetByUserIdForMobile API is working 
Given : get MyGameGetByUserIdForMobile API payload
When : user MyGameGetByUserIdForMobile API calls with POST http request
Then : MyGameGetByUserIdForMobile API call executed with 200 status code

@Smoke
Scenario: verify if GetAllGameScoreByUserId API is working 
Given : get GetAllGameScoreByUserId API payload
When : user GetAllGameScoreByUserId API calls with POST http request
Then : GetAllGameScoreByUserId API call executed with 200 status code
