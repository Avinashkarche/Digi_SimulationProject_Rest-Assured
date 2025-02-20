Feature: verify the RegionMaster API

@Smoke
Scenario: verify if CountryCallingCode API is working
Given : get the CountryCallingCode API payload
When : CountryCallingCode call with GET request
Then : CountryCallingCode API call executed with 200 status code

@Smoke
Scenario: verify if CountryGetAll API is working
Given : get the CountryGetAll API payload
When : CountryGetAll call with GET request
Then : CountryGetAll API call executed with 200 status code

@Smoke
Scenario: verify if StateGetAllByCountryId API is working
Given : get the StateGetAllByCountryId API payload
When : StateGetAllByCountryId call with GET request
Then : StateGetAllByCountryId API call executed with 200 status code

@Smoke
Scenario: verify if DistrictGetAllByStateId API is working
Given : get the DistrictGetAllByStateId API payload
When : DistrictGetAllByStateId call with GET request
Then : DistrictGetAllByStateId API call executed with 200 status code

@Smoke
Scenario: verify if CityGetAllByStateId API is working
Given : get the CityGetAllByStateId API payload
When : CityGetAllByStateId call with GET request
Then : CityGetAllByStateId API call executed with 200 status code
