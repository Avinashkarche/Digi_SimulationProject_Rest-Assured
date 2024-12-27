Feature: verify the UserTypeMaster API

@Smoke
Scenario: verify if adminforusertype login API is working
Given create adminforusertype login api payload
When user calls with adminforusertype POST http request
Then adminforusertype API call executed with 200 status code 

@Smoke
Scenario: verify if UserTypeMasterAdd API is working
Given : create the UserTypeMasterAdd API payload
When : UserTypeMasterAdd call with POST request
Then : UserTypeMasterAdd API call executed with 201 status code

@Smoke
Scenario: verify if UserTypeMasterGetAll API is working
Given : get the UserTypeMasterGetAll API payload
When : UserTypeMasterGetAll call with GET request
Then : UserTypeMasterGetAll API call executed with 200 status code

@Smoke
Scenario: verify if UserTypeMasterGetById API is working
Given : get the UserTypeMasterGetById API payload
When : UserTypeMasterGetById call with GET request
Then : UserTypeMasterGetById API call executed with 200 status code

#@Smoke
#Scenario: verify if UserTypeMasterUpdate API is working
#Given : create the UserTypeMasterUpdate API payload
#When : UserTypeMasterUpdate call with PUT request
#Then : UserTypeMasterUpdate API call executed with 200 status code

@Smoke
Scenario: verify if UserTypeMasterDelete API is working
Given : get the UserTypeMasterDelete API payload
When : UserTypeMasterDelete call with DELETE request
Then : UserTypeMasterDelete API call executed with 200 status code


