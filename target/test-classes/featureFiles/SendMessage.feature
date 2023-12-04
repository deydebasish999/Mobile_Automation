@Demo
@SendSMS
Feature: Sending SMS

  Scenario Outline: Sending an SMS functionality
    Given I launch message app from device with "<appPackage>" and "<appActivity>"
    When I add a contact
    Then I enter message text
    And I click on the send button
    
    Examples: 
    | appPackage | appActivity  |
    |com.google.android.apps.messaging|com.google.android.apps.messaging.ui.ConversationListActivity|