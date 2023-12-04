@Demo
@Ecomm
Feature: Ecommerece App Testing
	
	@TC1
  Scenario Outline: Form Filling
    Given I launch General Strore app from device
    When I provide required details in form "<name>","<gender>" and "<country>"
    And Click on shop button
    Then Product list page is displayed "<toolBarTitle>"
    
    Examples: 
    | name  | gender | country   | toolBarTitle |
    | Anaya | Female | Australia | Products     |
    
   @TC2 
   Scenario Outline: Validating error message on not providing name field
   	Given  I launch General Strore app from device
   	When I click on lets shop button without providing name field
   	Then I validate Toast error message "<message>"
   	
   	Examples:
   	|message                |
   	|Please enter your name |
   	
   @TC3
   Scenario: Adding product to cart
    Given I launch General Strore app from device
    When  I provide required details in form
    And 	Click on shop button
    And   I search for a particular product and add the product to cart
    And   I navigate to cart
    Then  Validate correct product is added to cart
    
    @TC4
    Scenario: Verify total cart amount
     Given I launch General Strore app from device
     When  I provide required details in form
     And 	 Click on shop button
     And   I add the products to cart
     And   I navigate to cart
     Then  Validate product added amount is equal to total cart amount
     
    @TC5
    Scenario: Validate buying the products
     Given I launch General Strore app from device
     When  I provide required details in form
     And 	 Click on shop button
     And   I search for a particular product and add the product to cart
     And   I navigate to cart
     And   I verify the terms and conditions functionality,check the promotional checkbox and click proceed to buy
     Then  Validate navigation to buy page 
    	
     