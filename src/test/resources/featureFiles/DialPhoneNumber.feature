@Demo
@DialPhoneNumber
Feature: Calling functionality

  Scenario Outline: Open phone app and dial a number
    Given I launch phone app from device with "<appPackage>" and "<appActivity>"
    When I click on dialer
    Then I enter the phone number to call
    And I click on dial icon
    And I end the call
    
    Examples: 
    | appPackage | appActivity  |
    |com.google.android.dialer|com.google.android.dialer.extensions.GoogleDialtactsActivity|