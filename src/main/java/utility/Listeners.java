package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listeners  extends BaseClass implements ITestListener{

	public void onTestFailure(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {
	

	}

	public void onFinish(ITestContext context) {

	}

}
