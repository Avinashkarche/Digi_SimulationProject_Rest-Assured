Feature: validate admin API

@Smoke
Scenario: verify if super admin login API is working
Given create login api payload
When user calls with super admin POST http request
Then API call executed with 200 status code 

@Smoke
Scenario: verify if super admin resgister child admin API is working
Given create admin API payload 
When user calls with POST http request
Then API call executed with the 201 status code

@Smoke
Scenario: verify if super admin login child admin  API is working
Given create child admin login api payload
When user calls with super child admin POST http request
Then API call should be executed with 200 status code 

@Smoke 
Scenario: verify if super admin update the child admin username and mobile number API is working
Given update admin API payload 
When user calls with PUT http request
Then API call should executed with 200 status code

@Smoke 
Scenario: verify if super admin update the child admin password API is working
Given update admin API payload for change password
When user calls with PUT http request for change Password
Then API call should be executedfor change admin password with 200 status code

@Smoke 
Scenario: verify if child admin register the player API is working
Given create player API payload 
When user calls for register player with POST http request
Then API register player call executed with 201 status code

@Smoke 
Scenario: verify if child admin login the player API is working
Given create admin login the player player API payload 
When user calls for login player with POST http request
Then API login player call executed with 200 status code

@Smoke 
Scenario: verify if child admin update the register player API is working
Given update player API payload 
When user update player calls with PUT http request
Then API update player call executed with 200 status code

@Smoke 
Scenario: verify if child admin delete the register player API is working
Given get a delete API payload 
When user calls with DELETE http request
Then API delete register player call executed with 200 status code

@Smoke 
Scenario: verify if super admin delete the registered admin API is working
Given get a child admin delete API payload 
When user calls with  child admin DELETE http request
Then API  child admin call be executed with 200 status code

@Smoke 
Scenario: verify if super admin get all player API is working
Given get a all player API payload 
When user calls with  all player GET http request
Then API  all player call be executed with 200 status code

@Smoke
Scenario: verify superadmin PlayerRoleUpdate  API is working
Given : create superadmin PlayerRoleUpdate API payload
When : user superadmin PlayerRoleUpdate API calls with PUT http request
Then : superadmin PlayerRoleUpdate API call executed with 200 status code









