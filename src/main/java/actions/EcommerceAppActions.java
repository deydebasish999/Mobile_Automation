package actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import com.codoid.products.exception.FilloException;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import locators.EcommerceAppLocators;
import net.bytebuddy.description.type.TypeDescription.Generic;
import utility.BaseClass;
import utility.ConfigFileReader;
import utility.GenericMethods;
import utility.ReadDataFromExcel;

public class EcommerceAppActions extends BaseClass {
	EcommerceAppLocators locators = new EcommerceAppLocators();
	ConfigFileReader configReader= new ConfigFileReader();
	
	public void fillForm(String name,String gender,String country)
	{
		
		GenericMethods.enterText(locators.name, name);
		GenericMethods.hideKeyboardAction();
		//GenericMethods.click(locators.gender);
		GenericMethods.radioButtonAction(driver.findElement(By.xpath("//android.widget.RadioButton[@text='"+gender+"']")));
		GenericMethods.click(locators.countryDropDown);
		GenericMethods.scrollToElement(country);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();		
	}
	
	public void fillFormFromTestDataSheet() throws FilloException
	{		
		GenericMethods.enterText(locators.name, ReadDataFromExcel.ReadTestData("name", "AddProductToCart"));
		GenericMethods.hideKeyboardAction();
		//GenericMethods.click(locators.gender);
		GenericMethods.radioButtonAction(driver.findElement(By.xpath("//android.widget.RadioButton[@text='"+ReadDataFromExcel.ReadTestData("gender", "AddProductToCart")+"']")));
		GenericMethods.click(locators.countryDropDown);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GenericMethods.scrollToElement(ReadDataFromExcel.ReadTestData("country", "AddProductToCart"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+ReadDataFromExcel.ReadTestData("country", "AddProductToCart")+"']")).click();		
	}

	public void clickShopButton() throws InterruptedException {
		GenericMethods.click(locators.letsShopButton);
		
		//Thread.sleep(3000);
	}
	
	public void validateProductListPage(String toolBarTitle) {
		//GenericMethods.waitUntilTextAttributeContains(locators.toolbarTitle, toolBarTitle);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GenericMethods.assertEqual(locators.toolbarTitle.getText(), toolBarTitle);
		
	}
		
	public void validateToastMessages(String errorMessage) {
		//Handling-Validating Toast Messages(error alerts)	
		String actual = GenericMethods.getToastMessageText(locators.toastMessages);
		//String expected ="Please enter your name";
		GenericMethods.assertEqual(actual, errorMessage);
	}
		
		
	public void addProductToCart() throws FilloException {
		String productName = ReadDataFromExcel.ReadTestData("productname", "AddProductToCart");
		GenericMethods.scrollToElement(productName);
		int productCount =	locators.productNamesinView.size();
		//System.out.println(productCount);
		for(int i=0;i<productCount;i++)
		{ 
			String product =locators.productNamesinView.get(i).getText();
			//System.out.println(productName);
			if(product.equalsIgnoreCase(productName))
			{
				locators.addToCart.get(i).click();
			}
		}
		
	}	
	
	public void goToCart() {
		locators.goToCartIcon.click();
		
	}
	
	public void validateProductAddedToCart() throws FilloException {
		String productName = ReadDataFromExcel.ReadTestData("productname", "AddProductToCart");
		//GenericMethods.waitUntilTextAttributeContains(locators.toolbarTitle, "Cart");
		String lastPageProduct=locators.productName.getText();
		GenericMethods.assertEqual(lastPageProduct, productName);
	}
	
	public void addMultipleProductsToCart() {
		List<WebElement> addToCartElements = locators.addToCart;
		for(int i=0;i<addToCartElements.size();i++) {
			GenericMethods.click(addToCartElements.get(i));			
		}
		//locators.goToCartIcon.click();
		//GenericMethods.waitUntilTextAttributeContains(locators.toolbarTitle, "Cart");
	}
	
	public void verifyTotalCartAmount() throws InterruptedException {
		
		int count = locators.productPricesInCart.size();
		double totalSum =0;
		for(int i =0; i< count; i++)
		{
			String amountString =locators.productPricesInCart.get(i).getText();
			Double price = Double.parseDouble(amountString.substring(1));
			totalSum = totalSum + price;  //160.97 + 120 =280.97
				
		}
		String displaySum =GenericMethods.getTextofElement(locators.totalAmountInCart);
		Double displayFormattedSum = Double.parseDouble(displaySum.substring(1));
		GenericMethods.assertEqual(totalSum, displayFormattedSum);	
	}
	
	public void buyProduct() throws InterruptedException {
		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		GenericMethods.longPressActionOnElement(ele);
		GenericMethods.click(locators.termsansdsAconditionOkBttn);
		GenericMethods.checkboxAction(locators.promotionalCheckBox, "No");
		GenericMethods.click(locators.proceedToBuyBttn);
		Thread.sleep(3000);
		//Switching to webview Google Chrome
		GenericMethods.switchToWebView(configReader.getWebContextName());
		System.out.println("Web Page Title is "+driver.getTitle());

	}
	
}
