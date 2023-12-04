package locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utility.BaseClass;

public class EcommerceAppLocators {

	@FindBy(how= How.ID, using="com.androidsample.generalstore:id/nameField")
	public WebElement name;
	
	//@FindBy(how= How.XPATH, using="//android.widget.RadioButton[@text='Female']")
	//public WebElement gender;
	
	@FindBy(how= How.ID, using="com.androidsample.generalstore:id/spinnerCountry")
	public WebElement countryDropDown;
	
	@FindBy(how= How.ID, using="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement letsShopButton;
	
	@FindBy(how= How.XPATH, using="(//android.widget.Toast)[1]")
	public WebElement toastMessages;
	
	
	@FindBy(how= How.XPATH, using="//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']")
	public List<WebElement> productNamesinView;
	
	@FindBy(how= How.XPATH, using="//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']")
	public WebElement productName;
	
	@FindBy(how= How.ID, using="com.androidsample.generalstore:id/productAddCart")
	public List<WebElement> addToCart;
		
	@FindBy(how= How.ID, using="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement goToCartIcon;
	
	@FindBy(how= How.ID, using="com.androidsample.generalstore:id/toolbar_title")
	public WebElement toolbarTitle;
	
	@FindBy(how= How.ID, using="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productPricesInCart;
		
	@FindBy(how= How.ID, using="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmountInCart;
	
	@FindBy(how= How.CLASS_NAME, using="android.widget.CheckBox")
	public WebElement promotionalCheckBox;
	
	@FindBy(how= How.ID, using=	"android:id/button1")
	public WebElement termsansdsAconditionOkBttn;
		
	@FindBy(how= How.ID, using=	"com.androidsample.generalstore:id/btnProceed")
	public WebElement proceedToBuyBttn;
	
	@FindBy(how= How.NAME, using="q")
	public WebElement googleSearch;
	

	public EcommerceAppLocators() {
		PageFactory.initElements(BaseClass.driver, this);
		
	}
}
