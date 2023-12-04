package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import actions.EcommerceAppActions;
import actions.MessagingAppActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.BaseClass;
import utility.GenericMethods;

public class SendMessageSteps extends BaseClass{

	MessagingAppActions messagingAppActions = new MessagingAppActions();
	Logger logger = LogManager.getLogger(SendMessageSteps.class);
	EcommerceAppActions actions = new EcommerceAppActions();
	
	@Given("I launch message app from device with {string} and {string}")
	public void i_launch_message_app_from_device_with_and(String appPackage, String appActivity) {
		//initApp();
	}

	@When("I add a contact")
	public void i_add_a_contact() throws InterruptedException {
		messagingAppActions.startNewConversation();
		messagingAppActions.addContact();
	}

	@Then("I enter message text")
	public void i_enter_message_text() {
		messagingAppActions.enterMsgText();
	}

	@Then("I click on the send button")
	public void i_click_on_the_send_button() {
		messagingAppActions.sendMsg();
	}
	
	@Given("I launch message ecomm app from device")
	public void i_launch_message_ecomm_app_from_device() {

	}
	
	@When("I test features")
	public void i_test_features() throws InterruptedException, IOException {

		//actions.fillForm("Mahima Sett","Australia");
		//actions.errorScenarioFillform();
		//actions.addProductToCart("Jordan 6 Rings","Cart");
		//actions.verifyTotalCartAmount("Cart","yes");
		
	}

	
}
