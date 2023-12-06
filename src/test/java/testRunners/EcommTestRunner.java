package testRunners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.codoid.products.exception.FilloException;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import utility.BaseClass;
import utility.GenericMethods;
import utility.SendEmailAfterexecution;


@CucumberOptions(
		features = "src/test/resources/featureFiles",
		glue = "stepDefinitions",
		tags="@Ecomm",
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"json:target/cucumber.json",
				"de.monochromata.cucumber.report.PrettyReports:target/cucumber"},
		dryRun = false,
		monochrome = false
     )


public class EcommTestRunner extends AbstractTestNGCucumberTests{
	BaseClass bc = new BaseClass();
	@BeforeMethod
	public void startAppium() throws FilloException {
		bc.initApp();
	}
	
	@AfterMethod
	public void ssonfail(ITestResult result) {
		int res = result.getStatus();
		if(res==2) {   // 1=Pass, 2=Fail
			try {
				GenericMethods.takeScreenshot("FailedScreen");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@AfterTest
	public void closeApp() {
		bc.tearDownReport();
	}
	
	@AfterSuite
	public void tearDown() throws Exception {
		File reportOutputDirectory = new File("target/cucumber");
		List<String> jsonFiles = new ArrayList<String>();
		jsonFiles.add("target/cucumber.json");
		//String buildNumber = "1";
		String projectName = "Mobile_Automation_Demo";
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		@SuppressWarnings("unused")
		Reportable result = reportBuilder.generateReports();
		//Send email from local after execution
//		SendEmailAfterexecution.sendmail();
//		bc.tearDownReport();
//		BaseClass.setUp();
//		BaseClass.driver.get(System.getProperty("user.dir")+"\\target\\cucumber\\cucumber-html-reports\\overview-features.html");
//		JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
//		js.executeScript("arguments[0].setAttribute('style','display: none;');",BaseClass.driver.findElement(By.xpath("//*[@id='navigation']")));
//		js.executeScript("arguments[0].setAttribute('style','display: none;');",BaseClass.driver.findElement(By.xpath("//*[@class='fa fa-chevron-left']")));
//		js.executeScript("arguments[0].setAttribute('style','display: none;');",BaseClass.driver.findElement(By.xpath("//*[@class='fa fa-chevron-right']")));
//		js.executeScript("arguments[0].setAttribute('style','display: none;');",BaseClass.driver.findElement(By.xpath("//*[@class='carousel-indicators']")));
//
//		Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(BaseClass.driver);
//		Thread.sleep(3000);
//		ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir")+"\\screenshots\\screen.png"));
//		File imageFile = new File(System.getProperty("user.dir")+"\\screenshots\\screen.png");
//	    File destFile = new File(System.getProperty("user.dir")+"\\screenshots\\image-crop.png");
//	    CropImage.resize(imageFile, destFile, 800, 600);
//		bc.tearDownReport();
	}
}
