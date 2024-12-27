Feature: verify the MenuMaster API

@Smoke
Scenario: verify if adminforMenuMaster login API is working
Given create adminforMenuMaster login api payload
When user calls with adminforMenuMaster POST http request
Then adminforMenuMaster API call executed with 200 status code 

@Smoke
Scenario: verify if MenuMasterAdd API is working
Given : create the MenuMasterAdd API payload
When : MenuMasterAdd call with POST request
Then : MenuMasterAdd API call executed with 201 status code

@Smoke
Scenario: verify if MenuMasterGetAll API is working
Given : get the MenuMasterGetAll API payload
When : MenuMasterGetAll call with GET request
Then : MenuMasterGetAll API call executed with 200 status code

@Smoke
Scenario: verify if MenuMasterGetById API is working
Given : get the MenuMasterGetById API payload
When : MenuMasterGetById call with GET request
Then : MenuMasterGetById API call executed with 200 status code

@Smoke
Scenario: verify if MenuMasterUpdate API is working
Given : create the MenuMasterUpdate API payload
When : MenuMasterUpdate call with PUT request
Then : MenuMasterUpdate API call executed with 200 status code

@Smoke
Scenario: verify if MenuMasterDelete API is working
Given : get the MenuMasterDelete API payload
When : MenuMasterDelete call with DELETE request
Then : MenuMasterDelete API call executed with 200 status code
