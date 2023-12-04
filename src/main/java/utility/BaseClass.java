package utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.codoid.products.exception.FilloException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;


public class BaseClass {

	
	public static AndroidDriver<WebElement> driver;
	public  DesiredCapabilities dc = new DesiredCapabilities();
	public  URL url = null;
	ConfigFileReader configreader= new ConfigFileReader();
	ExtentReports extent;
	
	//implement ios caps: String appPackage,String appActivity
	public void initApp() throws FilloException {
		GenericMethods.getLoggerInfo("Application Initialization started");
		if(ReadDataFromExcel.ReadDeviceParameters("DeviceType", "DeviceType_Platform_Selection").equalsIgnoreCase("Android")) {
			if(ReadDataFromExcel.ReadDeviceParameters("platform", "DeviceType_Platform_Selection").equalsIgnoreCase("App")) {
				dc.setCapability(MobileCapabilityType.PLATFORM_NAME,configreader.getPlatformName());
				dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,configreader.getPlatformVersion());
				dc.setCapability(MobileCapabilityType.DEVICE_NAME,configreader.getDeviceName());
				dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
				dc.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"//resources//"+configreader.getApkName());
				dc.setCapability("appPackage",configreader.getAppPackage());
				dc.setCapability("appActivity",configreader.getAppActivity());
				dc.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"//resources//chromedriver.exe");
				dc.setCapability("unicodeKeyboard", "true");
				dc.setCapability("resetKeyboard", "true");
				try {
					url = new URL(configreader.getUrl());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			else if(ReadDataFromExcel.ReadDeviceParameters("platform", "DeviceType_Platform_Selection").equalsIgnoreCase("Browser")) {
				dc.setCapability(MobileCapabilityType.PLATFORM_NAME,configreader.getPlatformName());
				dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,configreader.getPlatformVersion());
				dc.setCapability(MobileCapabilityType.DEVICE_NAME,configreader.getDeviceName());
				dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
				dc.setCapability(MobileCapabilityType.BROWSER_NAME, ReadDataFromExcel.ReadDeviceParameters("BrowserName", "DeviceType_Platform_Selection"));
				dc.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"//resources//chromedriver.exe");
				dc.setCapability("unicodeKeyboard", "true");
				dc.setCapability("resetKeyboard", "true");
				try {
					url = new URL(configreader.getUrl());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			else if(ReadDataFromExcel.ReadDeviceParameters("platform", "DeviceType_Platform_Selection").equalsIgnoreCase("BrowserStack")) {
				String userName= "BrowserStack Username";
				String accessKey= "BrowserStack accesskey";
				dc.setCapability("browserstack.user", userName);
				dc.setCapability("browserstack.key", accessKey);
				dc.setCapability("app", "app URL");
				dc.setCapability("platformName", "");
				dc.setCapability("device", "Google Pixel 3");
				dc.setCapability("os_version", "9.0");
				dc.setCapability("project", "BrowserStack Sample");
				dc.setCapability("build", "browserstack-build-1");
				dc.setCapability("name", "browserstack-test");
				dc.setCapability("browserstackLocal", "true");
				try {
					url = new URL("https//"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub");
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			
		}
		else if(ReadDataFromExcel.ReadDeviceParameters("DeviceType", "DeviceType_Platform_Selection").equalsIgnoreCase("IOS")) {
			if(ReadDataFromExcel.ReadDeviceParameters("platform", "DeviceType_Platform_Selection").equalsIgnoreCase("App")) {
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.3");
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12 Pro");
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
			dc.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
			dc.setCapability("commandTimeouts", "12000");		
			dc.setCapability(MobileCapabilityType.APP, "Path to App");
			}
			else if(ReadDataFromExcel.ReadDeviceParameters("platform", "DeviceType_Platform_Selection").equalsIgnoreCase("Browser")) {
				dc.setCapability(MobileCapabilityType.PLATFORM_NAME,configreader.getPlatformName());
				dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,configreader.getPlatformVersion());
				dc.setCapability(MobileCapabilityType.DEVICE_NAME,configreader.getDeviceName());
				dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
				dc.setCapability(MobileCapabilityType.BROWSER_NAME, ReadDataFromExcel.ReadDeviceParameters("BrowserName", "DeviceType_Platform_Selection"));
				//dc.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"//resources//chromedriver.exe");
				try {
					url = new URL(configreader.getUrl());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
			
		driver = new AndroidDriver<WebElement>(url,dc);

		GenericMethods.implicitWait();
		GenericMethods.getLoggerInfo("Application Initialization completed");
		GenericMethods.getLoggerInfo("Starting test execution");

	}
	
	public void initializeReport() throws IOException {
		ExtentSparkReporter spark = new ExtentSparkReporter("testReports/");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		spark.loadXMLConfig("src//test//resources//extent-config.xml");
		
	}
	
	
	public  void tearDownReport() {
		//driver.close();
		driver.quit();
	}
	
}
