Feature: validate Productmaster  API

@Smoke
Scenario: verify if AddProduct admin login API is working
Given create AddProduct admin login api payload
When user calls with AddProduct admin login POST http request
Then AddProduct admin API call executed with 200 status code 

@Smoke
Scenario:  verify if playerforproduct login API is working
Given create playerforproduct login api payload
When user playerforproduct login API calls with POST http request
Then  playerforproduct login API call executed with 200 status code 

@Smoke
Scenario:  verify if AddProductByGameList API is working
Given create AddProductByGameList api payload
When user AddProductByGameList API calls with POST http request
Then  AddProductByGameList API call executed with 201 status code 

@Smoke
Scenario:  verify if GetAllProduct API is working
Given get GetAllProduct api payload
When user GetAllProduct API calls with GET http request
Then  GetAllProduct API call executed with 200 status code 

@Smoke
Scenario:  verify if UpdateProduct API is working
Given UpdateProduct api payload
When user UpdateProduct API calls with PUT http request
Then  UpdateProduct API call executed with 200 status code 

@Smoke
Scenario:  verify if GetProductById API is working
Given get GetProductById api payload
When user GetProductById API calls with GET http request
Then  GetProductById API call executed with 200 status code 

@Smoke
Scenario:  verify if DeleteProduct API is working
Given get DeleteProduct api payload
When user DeleteProduct API calls with DELETE http request
Then  DeleteProduct API call executed with 400 status code 

@Smoke
Scenario: verify if AddProduct API is working
Given create AddProduct api payload
When user AddProduct API calls with POST http request
Then  AddProduct API call executed with 201 status code 

@Smoke
Scenario:  verify if AddProduct GetAllProduct API is working
Given get AddProduct GetAllProduct api payload
When user AddProduct GetAllProduct API calls with GET http request
Then  AddProduct GetAllProduct API call executed with 200 status code 


@Smoke
Scenario:  verify if AddProduct UpdateProduct API is working
Given update AddProduct UpdateProduct api payload
When user AddProduct UpdateProduct API calls with PUT http request
Then AddProduct UpdateProduct API call executed with 200 status code 

@Smoke
Scenario:  verify if ProductStatusIdUpdate API is working
Given update ProductStatusIdUpdate api payload
When user ProductStatusIdUpdate API calls with PUT http request
Then ProductStatusIdUpdate API call executed with 200 status code

@Smoke
Scenario:  verify if GetProductByIdPublic API is working
Given get GetProductByIdPublic api payload
When user GetProductByIdPublic API calls with GET http request
Then GetProductByIdPublic API call executed with 200 status code

@Smoke
Scenario:  verify if BundleProductUpdate API is working
Given update BundleProductUpdate api payload
When user BundleProductUpdate API calls with PUT http request
Then BundleProductUpdate API call executed with 200 status code

@Smoke
Scenario:  verify if TrendingGameAndBundleToggle API is working
Given update TrendingGameAndBundleToggle api payload
When user TrendingGameAndBundleToggle API calls with GET http request
Then TrendingGameAndBundleToggle API call executed with 200 status code

@Smoke
Scenario:  verify if AddProduct GetProductById API is working
Given get AddProduct GetProductById api payload
When user AddProduct GetProductById API calls with GET http request
Then  AddProduct GetProductById API call executed with 200 status code 

@Smoke
Scenario:  verify if AddProduct DeleteProduct API is working
Given get AddProduct DeleteProduct api payload
When userAddProduct  DeleteProduct API calls with DELETE http request
Then  DeleteProductAddProduct  API call executed with 400 status code 

@Smoke
Scenario:  verify if GetProductPublic API is working
Given get GetProductPublic api payload
When user GetProductPublic API calls with GET http request
Then GetProductPublic API call executed with 200 status code

@Smoke
Scenario: verify PlayerRoleUpdate  API is working
Given : create PlayerRoleUpdate API payload
When : user PlayerRoleUpdate API calls with PUT http request
Then : PlayerRoleUpdate API call executed with 200 status code


@Smoke
Scenario:  verify if GetProductByTester API is working
Given get GetProductByTester api payload
When user GetProductByTester API calls with GET http request
Then GetProductByTester API call executed with 200 status code
