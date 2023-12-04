package utility;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class GenericMethods extends BaseClass{
	
	static WebDriverWait wait;
	static Logger logger = LogManager.getLogger(BaseClass.class);
	
//-----------------------Android - Common methods for Browser and App------------------------------------------
	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	public static void waitUntilVisibilityOfElement(WebElement element) {
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitUntilTextAttributeContains(WebElement element,String expectedAttributeText) {
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.attributeContains(element, "text", expectedAttributeText));
	}
	
	//Logging
	public static void getLoggerInfo(String logMessage) {
		logger.info(logMessage);
	}
	
	//Assertions
	
			public static void assertEqual(String actual,String expected) {
				 Assert.assertEquals(actual, expected,"The expected and actual string doesnot match");
			}
			
			public static void assertEqual(Double actual,Double expected) {
				 Assert.assertEquals(actual, expected,"The expected and actual string doesnot match");
			}
			
			public static void assertEqual(Boolean actual,Boolean expected) {
				 Assert.assertEquals(actual, expected,"The expected and actual string doesnot match");
			}
			
			public static void assertEqual(Float actual,Float expected) {
				 Assert.assertEquals(actual, expected,"The expected and actual string doesnot match");
			}
			
			public static void assertEqual(int actual,int expected) {
				 Assert.assertEquals(actual, expected,"The expected and actual string doesnot match");
			}
			
			public static void assertEqual(Object actual,Object expected) {
				 Assert.assertEquals(actual, expected,"The expected and actual string doesnot match");
			}
			
			//Handle Checkbox
			public static void checkboxAction(WebElement element,String selection) { //Selection-yes or no
				Boolean selectCheckbox = element.isSelected();
				if(selection.equalsIgnoreCase("yes") && selectCheckbox==false) {
					element.click();
				}
				else if(selection.equalsIgnoreCase("no") && selectCheckbox==true) {
					element.click();
				}
			}
			
			//Handle RadioButton
			public static void radioButtonAction(WebElement element) {
				Boolean radioBttnIsSelected = element.isSelected();
				if(radioBttnIsSelected==false) {
					element.click();
				}
			}
			
			//Screenshot
			public static void takeScreenshot(String snapName) throws IOException {
				File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcfile, new File (".\\screenshots" +"/"+ snapName + System.currentTimeMillis() + ".jpg"));
			}
			
			//full page screenshot
			public static void fullPageScreenshot() {
				Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
		        try {
					ImageIO.write(myScreenshot.getImage(),"PNG",new File("./screenshots/elementScreenshot.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			//Events
			
			public static void clickwithActions(WebElement element) {
				Actions ac = new Actions(driver);
				ac.moveToElement(element).click().build().perform();
			}
			

			public static String getTextofElement(WebElement ele) {
				
				String text = ele.getText();
				return text;		
			}
			
			public static String getTextofElements(List<WebElement> elements) {
				String elementsName = null;
				for(int i=0;i<elements.size();i++) {
					elementsName = elements.get(i).getText();
				}
				return elementsName;		
			}
			
			public static void click(WebElement ele) {
				ele.click();
			}
	
			
			public static void enterText(WebElement ele,String textToEnter) {
				ele.sendKeys(textToEnter);	
			}
			
		
//--------------------------Android - Generic methods for App---------------------------------------------
		
//		public static void scrollToElement(WebElement ele,String directionOfScroll) {
//			((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
//				    "elementId", ((RemoteWebElement)ele).getId(),"direction",directionOfScroll,"percent",100
//				));
//		}
	
		public static void scrollToElement(String textOfElement) {
			driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ textOfElement+ "\"));"));
			
		}
		
	
		public static void scrollToEnd(String scrollDirection) {    //direction up,down,left or right
			boolean canScrollMore; 
			do {
			canScrollMore= (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200,
				    "direction", scrollDirection,"percent", 5.0));
			}
			while(canScrollMore);
		}
		

		
		//Toast Messages
		public static String getToastMessageText(WebElement element) {
			return element.getAttribute("name");
		}
		
		
		//Handle Dropdowns
		public static void dropDownAction(WebElement element,String textOfElement) {
			element.click();
			driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ textOfElement+ "\"));"));

		}
		
		
		//Handling Context switch between app and browser		
		public static void getContextNames() {   //get the context name to to switch to browser
			Set<String> contextNames=driver.getContextHandles();
			for(String context:contextNames) {
				System.out.println(context);
			}
		}
		
		public static void switchToWebView(String contextName) { //switch to browser using the context name as parameter from getContextNames()

			driver.context(contextName);
		}

	
		//Handle Android Events
		public static void openNotifications() {
			driver.openNotifications();
		}
		
		// Clear previous notifications
		
		public static void clearNotifications() {
			driver.findElementByXPath("//android.widget.TextView[@text='Clear all']").click();
		}
		
		// Wait for the OTP notification
		public static void waitForOTPNotification() {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, 'OTP')]")));
		}
		
		// Retrieve the OTP text
		//String otpText = driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'OTP')]")).getText();
		//String otp = otpText.replaceAll("[^0-9]", ""); // Extract only the numeric OTP value
		
		public static void rotateActionLandscape() {
			DeviceRotation landscape = new DeviceRotation(0, 0, 90);
			driver.rotate(landscape);
		}
		
		public static void copyClipboardText(WebElement ele) {
			driver.setClipboardText(ele.getText());
		}
		
		public static void retrieveClipboardText(WebElement ele) {
			ele.sendKeys(driver.getClipboardText());
		}
		
		//Android
		public static void longPressActionOnElement(WebElement ele)
		{
			((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
					ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
							"duration",2000));
		}
		

		public static void longPressActionOnCoordinates(int xCoordinate,int yCoordinate)
		{
			((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
					ImmutableMap.of("x",xCoordinate,"y",yCoordinate,
							"duration",2000));
		}
		
		public static void doubleClickActionOnElement(WebElement ele)
		{
		
			((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId()
			));
		}
		
		public static void doubleClickActionOnCoordinates(int xCoordinate,int yCoordinate)
		{
		
			((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
				"x",xCoordinate,"y",yCoordinate));
		}
		
		public static void clickActionOnCoordinates(int xCoordinate,int yCoordinate)
		{
		
			((JavascriptExecutor) driver).executeScript("mobile: clickGesture", ImmutableMap.of(
				"x",xCoordinate,"y",yCoordinate));
		}
		
		public static void dragAndDropAction(WebElement ele, int targetXCoordinate,int targetYCoordinate) {
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) ele).getId(),
				    "endX", targetYCoordinate,
				    "endY", targetYCoordinate
				));
		}
		
		public static void swipeAction(WebElement ele,String direction)  //directions up, down, left and right
		{
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
					"elementId", ((RemoteWebElement)ele).getId(),"direction", direction,"percent", 1.0));		
			
		}
		
		public static void pinchZoomInAction(WebElement ele) {
			((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) ele).getId(),
				    "percent", 1.0
				));
		}
		
		public static void pinchZoomOutAction(WebElement ele) {
			((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) ele).getId(),
				    "percent", 2.0
				));
		}
		

		
		//Handle Android key Events
		public static void pressHomeButton() {
			driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.HOME));
		}
		
		public static void pressBackButton() {
			driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.BACK));
		}
		
		public static void pressCAPSButton() {
			driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.CAPS_LOCK));
		}
		
		public static void pressEnterButton() {
			driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.ENTER));
		}
		
		public static void pressForwardButton() {
			driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.FORWARD));
		}
		
		public static void pressVolumeUpButton() {
			driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.VOLUME_UP));
		}
		
		public static void pressVolumeDownButton() {
			driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.VOLUME_DOWN));
		}
		
		public static void hideKeyboardAction() {
			driver.hideKeyboard();
		}
		
			
		public static void typeUsingRobot(WebElement element) throws AWTException {
		int xaxis = element.getLocation().x;
		int yaxis=element.getLocation().y;
		int width = element.getSize().width;
		int height= element.getSize().height;
		 
		Robot r=new Robot();
		r.mouseMove(xaxis+width/2, yaxis+height/2+70);
		r.mousePress(KeyEvent.BUTTON1_MASK);//click function
		r.mouseRelease(KeyEvent.BUTTON1_MASK);
		 
		//typing text in text box one by one
		r.keyPress(KeyEvent.VK_N);
		r.keyPress(KeyEvent.VK_C);
		r.keyPress(KeyEvent.VK_T);
		r.keyPress(KeyEvent.VK_R);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_N);
		r.keyRelease(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_R);
		r.keyRelease(KeyEvent.VK_A);
	}
		
//--------------------------------//Android - Generic methods for Browser Actions---------------------------------
		//Handle Alerts
		public static void getAlertText() {
			driver.switchTo().alert().getText();
		}
		
		public static void acceptAlert() {
			driver.switchTo().alert().accept();
		}
		
		public static void dismisstAlert() {
			driver.switchTo().alert().dismiss();;
		}
		
		public static void setTextInAlert(String textToSet) {
			driver.switchTo().alert().sendKeys(textToSet);
		}
		
		//Handle Frames
		public static void switchToFrame(int frameIndex) {
			driver.switchTo().frame(frameIndex);
		}
		
		public static void switchToFrame(String frameName) {
			driver.switchTo().frame(frameName);
		}
		
		public static void switchToFrame(WebElement frameElement) {
			driver.switchTo().frame(frameElement);
		}
		
		//Browser Navigations
		
		public static void navigateBack() {
			driver.navigate().back();
		}
		
		public static void navigateForward() {
			driver.navigate().forward();
		}
		
		public static void refreshWebPage() {
			driver.navigate().refresh();
		}
		
		//Window Handle
		public void switchToParticularWindowUsingTitle(String windowTitle) {
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> itr = windows.iterator();
			while(itr.hasNext()) {
				String currentWindowId = itr.next();
				if(driver.getTitle().equalsIgnoreCase(windowTitle)) {
					driver.switchTo().window(currentWindowId);
				}
				
			}
			
		}
		
		public String getWindowId() {
			String currentWindowId = driver.getWindowHandle();
			return currentWindowId;
		}
		
		public void switchToWindowWithId(){
			String windowId=getWindowId();
			driver.switchTo().window(windowId);
		}
		
		public void switchToParentWindow() {
			driver.switchTo().defaultContent();
		}
		
		//Actions class
		public void clickAndHoldElement(WebElement element) {
			Actions action =new Actions(driver);
			action.clickAndHold(element).build().perform();
		}
		
		public void doubleClickOnElement(WebElement element) {
			Actions action =new Actions(driver);
			action.doubleClick(element).build().perform();
		}
		
		public void dragandDropElement(WebElement element) {
			Actions action =new Actions(driver);
			action.dragAndDrop(element, element).build().perform();
		}
		
		public void hoverOverElement(WebElement element) {
			Actions action =new Actions(driver);
			action.moveToElement(element).build().perform();
		}
		

//QR Code Reader
		public BufferedImage generateImage( MobileElement element, File screenshot) throws IOException {

		    BufferedImage fullImage = ImageIO.read(screenshot);
		    org.openqa.selenium.Point imageLocation = element.getLocation();
		    int qrCodeImageWidth = element.getSize().getWidth();
		    int qrCodeImageHeight = element.getSize().getHeight();
		    int pointXPosition = imageLocation.getX();
		    int pointYPosition = imageLocation.getY();
		    BufferedImage qrCodeImage = fullImage.getSubimage(pointXPosition, pointYPosition, qrCodeImageWidth, qrCodeImageHeight);
		    ImageIO.write(qrCodeImage, "png", screenshot);
		    return qrCodeImage;

		}
		
		public String decodeQRCode(BufferedImage qrCodeImage) throws NotFoundException {
	        LuminanceSource source = new BufferedImageLuminanceSource(qrCodeImage);
	        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
	        Result result = new MultiFormatReader().decode(bitmap);
	        return result.getText();
	    }
		
		//PinchZoom
		public void pinchAndZoom() throws InterruptedException {

		    //finf the element first
		    Thread.sleep(3000);
		    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		    PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

		    Dimension size = driver.manage().window().getSize();
		    Point source = new Point(size.getWidth(), size.getHeight());

		    Sequence pinchAndZoom1 = new Sequence(finger, 0);
		    pinchAndZoom1.addAction(finger.createPointerMove(Duration.ofMillis(0),
		            PointerInput.Origin.viewport(), source.x / 2, source.y / 2));
		    pinchAndZoom1.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		    pinchAndZoom1.addAction(new Pause(finger, Duration.ofMillis(100)));
		    pinchAndZoom1.addAction(finger.createPointerMove(Duration.ofMillis(600),
		            PointerInput.Origin.viewport(), source.x / 3, source.y / 3));
		    pinchAndZoom1.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		}
		
		//IOS
				public static void longPressActionOnElementIOS(WebElement ele)
				{
					Map<String,Object> params = new HashMap<String, Object>();
					params.put("elementId",((RemoteWebElement)ele).getId());
					params.put("duration",2000);
					((JavascriptExecutor)driver).executeScript("mobile:touchAndHold",params);
				}

				public static void scrollToElementIOS(WebElement ele)
				{
					Map<String,Object> params = new HashMap<String, Object>();
					params.put("elementId",((RemoteWebElement)ele).getId());
					params.put("duration",2000);
					((JavascriptExecutor)driver).executeScript("mobile:scroll",params);
				}

				public static void swipeActionIOS(WebElement ele,String direction)  //directions up, down, left and right
				{
					Map<String,Object> params = new HashMap<String, Object>();
					params.put("elementId",((RemoteWebElement)ele).getId());
					params.put("direction", direction);
					((JavascriptExecutor) driver).executeScript("mobile: swipe", params);		
					
				}
		
		
}
