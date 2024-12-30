Feature: validate ProductCart API

@Smoke
Scenario: verify if ProductCart player login API is working
Given create ProductCart login api payload
When user calls with ProductCart player POST http request
Then ProductCart API call executed with 200 status code 

@Smoke
Scenario: verify if ProductCartAdd API is working
Given create ProductCartAdd api payload
When user calls with ProductCartAdd POST http request
Then ProductCartAdd API call executed with 201 status code 

@Smoke
Scenario: verify if ProductCartGetAll API is working
Given get ProductCartGetAll api payload
When user calls with ProductCartGetAll GET http request
Then ProductCartGetAll API call executed with 200 status code 

@Smoke
Scenario: verify if ProductCartUpdate API is working
Given get ProductCartUpdate api payload
When user calls with ProductCartUpdate POST http request
Then ProductCartUpdate API call executed with 200 status code

@Smoke
Scenario: verify if ProductCartDelete API is working
Given get ProductCartDelete api payload
When user calls with ProductCartDelete DELETE http request
Then ProductCartDelete API call executed with 200 status code

