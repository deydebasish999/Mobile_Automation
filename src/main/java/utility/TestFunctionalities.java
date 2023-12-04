package utility;

import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class TestFunctionalities {
	AndroidDriver<WebElement> driver;
	DesiredCapabilities dc = new DesiredCapabilities();
	URL url = null;
	ConfigFileReader configreader= new ConfigFileReader();


		
		@BeforeTest
		public void setup() {
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME,configreader.getPlatformName());
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,configreader.getPlatformVersion());
			dc.setCapability(MobileCapabilityType.DEVICE_NAME,configreader.getDeviceName());
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
			dc.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"//resources//"+"ApiDemos-debug.apk");
			//dc.setCapability("appPackage",configreader.getAppPackage());
			//dc.setCapability("appActivity",configreader.getAppActivity());
			dc.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"//resources//chromedriver.exe");
			dc.setCapability("unicodeKeyboard", "true");
			dc.setCapability("resetKeyboard", "true");
			try {
				url = new URL(configreader.getUrl());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver = new AndroidDriver<WebElement>(url,dc);
		}

		//GenericMethods.implicitWait();
		//GenericMethods.getLoggerInfo("Application Initialization completed");
		//GenericMethods.getLoggerInfo("Starting test execution");

	
//	@Test
//	public void swipe() {
//		//Swipe
//		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Views']")).click();
//		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Gallery']")).click();
//		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='1. Photos']")).click();
//		WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
//		Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),"true");	
//		
//		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
//				"elementId", ((RemoteWebElement)firstImage).getId(),"direction", "left","percent", 1.0));
//	}
	
//	@Test
//	public void DragDropTest() throws MalformedURLException, InterruptedException
//	{
//
//		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Views']")).click();
//		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Drag and Drop']")).click();
//		WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
//		
//		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
//			    "elementId", ((RemoteWebElement) source).getId(),
//			    "endX", 619,
//			    "endY", 560
//			));
//		
//		Thread.sleep(3000);
//		String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
//		Assert.assertEquals(result, "Dropped!");
//	}
	
//	@Test
//	public void rotateActionLandscape() {
//		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
//		driver.rotate(landscape);
//	}
	
//	@Test
//	public void zoomIn() {
//		
//	driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Views']")).click();
//	driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Gallery']")).click();
//	WebElement ele =driver.findElement(By.xpath("//android.widget.TextView[@content-desc='1. Photos']"));
//			//.click();
//	//WebElement ele = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
//	((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
//		    "elementId", ((RemoteWebElement) ele).getId(),
//		    "percent", 1.0
//		));
//
//	
//	}
}
