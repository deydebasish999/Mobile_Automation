package actions;


import org.openqa.selenium.By;
import locators.DialPadLocators;
import utility.BaseClass;
import utility.GenericMethods;


public class DialPadActions extends BaseClass{

	DialPadLocators dialPadLocators = new DialPadLocators();
	
	public void clickDialer() throws InterruptedException {
//		Thread.sleep(3000);
		driver.findElement(By.id(dialPadLocators.dialer)).click();
		GenericMethods.getLoggerInfo("Clicked on dialer button");

	}
	
	public void dialPhoneNumber() throws InterruptedException {
		
		driver.findElement(By.id("com.google.android.dialer:id/nine")).click();//8
	    driver.findElement(By.id("com.google.android.dialer:id/one")).click();//2
	    driver.findElement(By.id("com.google.android.dialer:id/zero")).click();//1
	    driver.findElement(By.id("com.google.android.dialer:id/eight")).click();//7
	    driver.findElement(By.id("com.google.android.dialer:id/five")).click();//2
	    driver.findElement(By.id("com.google.android.dialer:id/four")).click();//6
	    driver.findElement(By.id("com.google.android.dialer:id/four")).click();//1
	    driver.findElement(By.id("com.google.android.dialer:id/zero")).click();//2
	    driver.findElement(By.id("com.google.android.dialer:id/four")).click();//9
	    driver.findElement(By.id("com.google.android.dialer:id/one")).click();//9
		GenericMethods.getLoggerInfo("Typing phone number completed");

//		Thread.sleep(5000);
	
	}
	
	public void clickDialIcon() throws InterruptedException {
		driver.findElement(By.id(dialPadLocators.dialIcon)).click();
		GenericMethods.getLoggerInfo("Clicked on dialer icon");
//	    Thread.sleep(5000);
	}
	
	public void clickEndCall() throws InterruptedException {
		driver.findElement(By.id(dialPadLocators.endCallBttn)).click();
		GenericMethods.getLoggerInfo("Clicked on end call button");
//		Thread.sleep(3000);
	}
	
}
