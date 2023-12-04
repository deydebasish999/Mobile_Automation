package actions;


import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import locators.MessagingAppLocators;
import utility.BaseClass;
import utility.GenericMethods;

public class MessagingAppActions extends BaseClass{

	
	MessagingAppLocators messagingAppLocators = new MessagingAppLocators();

	public void startNewConversation() throws InterruptedException {
		driver.findElement(By.id(messagingAppLocators.newConversationBttn)).click();
		Thread.sleep(2000);
		driver.findElement(By.id(messagingAppLocators.newConversationBttn)).click();
		GenericMethods.getLoggerInfo("Clicked on new message button");
//		 Thread.sleep(5000);
	}
	
	@SuppressWarnings("rawtypes")
	public void addContact() throws InterruptedException {
		driver.findElement(By.id(messagingAppLocators.addContactBttn)).sendKeys("9108544041");
		 Thread.sleep(2000);
		((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		GenericMethods.getLoggerInfo("Contact added for message");
//		 Thread.sleep(3000);
	}
	
	public void enterMsgText() {
		driver.findElement(By.id(messagingAppLocators.enterMsgText)).sendKeys("TEST");
		GenericMethods.getLoggerInfo("Test message entered");
	}
	
	public void sendMsg() {
		driver.findElement(By.id(messagingAppLocators.sendMsgBttn)).click();
		GenericMethods.getLoggerInfo("Clicked on send message button");
	}
}
