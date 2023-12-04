package stepDefinitions;

import java.io.IOException;

import com.codoid.products.exception.FilloException;

import actions.EcommerceAppActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.snowflake.client.jdbc.internal.org.bouncycastle.pqc.jcajce.provider.lms.LMSSignatureSpi.generic;
import utility.BaseClass;
import utility.GenericMethods;

public class EcommerceAppSteps extends BaseClass {
	
	EcommerceAppActions actions = new EcommerceAppActions();
	BaseClass bc = new BaseClass();
	@Given("I launch General Strore app from device")
	public void i_launch_general_strore_app_from_device() throws FilloException {
		//bc.initApp();
		
	}

	@When("I provide required details in form {string},{string} and {string}")
	public void i_provide_required_details_in_form_and(String name, String gender, String country) {
		actions.fillForm(name, gender,country);
	}

	@When("Click on shop button")
	public void click_on_shop_button() throws InterruptedException {
		actions.clickShopButton();
		
	}

	@Then("Product list page is displayed {string}")
	public void product_list_page_is_displayed(String toolBarTitle) {
		actions.validateProductListPage(toolBarTitle);
	}

	@When("I click on lets shop button without providing name field")
	public void i_click_on_lets_shop_button_without_providing_name_field() throws InterruptedException {
	  actions.clickShopButton();
	}

	@Then("I validate Toast error message {string}")
	public void i_validate_toast_error_message(String errorMessage) {
		actions.validateToastMessages(errorMessage);
	}

	@When("I provide required details in form")
	public void i_provide_required_details_in_form() throws FilloException {
	   actions.fillFormFromTestDataSheet();
	}

	@When("I search for a particular product and add the product to cart")
	public void i_search_for_a_particular_product_and_add_the_product_to_cart() throws FilloException {
	  actions.addProductToCart();
	}

	@When("I navigate to cart")
	public void i_navigate_to_cart() {
		actions.goToCart();
	}

	@Then("Validate correct product is added to cart")
	public void validate_correct_product_is_added_to_cart() throws FilloException {
	   actions.validateProductAddedToCart();
	}
	
	@When("I add the products to cart")
	public void i_add_the_products_to_cart() {
		actions.addMultipleProductsToCart();
	}

	@Then("Validate product added amount is equal to total cart amount")
	public void validate_product_added_amount_is_equal_to_total_cart_amount() throws InterruptedException {
		actions.verifyTotalCartAmount();
	}

	@When("I verify the terms and conditions functionality,check the promotional checkbox and click proceed to buy")
	public void i_verify_the_terms_and_conditions_functionality_check_the_promotional_checkbox_and_click_proceed_to_buy() throws InterruptedException {
	   actions.buyProduct();
	}

	@Then("Validate navigation to buy page")
	public void validate_navigation_to_buy_page() {
	
	}
}
