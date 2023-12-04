package stepDefinitions;

import actions.DialPadActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.BaseClass;

public class DialPhoneNumberSteps extends BaseClass{

	DialPadActions dialPadActions = new DialPadActions();
	
	@Given("I launch phone app from device with {string} and {string}")
	public void i_launch_phone_app_from_device_with_and(String appPackage, String appActivity) {
		//initApp(appPackage, appActivity);
	}

	@When("I click on dialer")
	public void i_click_on_dialer() throws InterruptedException {
		dialPadActions.clickDialer();
	}

	@Then("I enter the phone number to call")
	public void i_enter_the_phone_number_to_call() throws InterruptedException {
		dialPadActions.dialPhoneNumber();
	}

	@Then("I click on dial icon")
	public void i_click_on_dial_icon() throws Exception {
		dialPadActions.clickDialIcon();
	}

	@Then("I end the call")
	public void i_end_the_call() throws InterruptedException {
		dialPadActions.clickEndCall();
	}

}
