package testRunners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
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
		tags="@TC1",
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"json:target/cucumber.json",
				"de.monochromata.cucumber.report.PrettyReports:target/cucumber"},
		dryRun = false,
		monochrome = false
     )


public class TestRunner extends AbstractTestNGCucumberTests{
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
		ExtentReports report = new ExtentReports();
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C://Users//debasishdey//Desktop//workspace//SAP_Automation_Workspace//Mobile_UI_Automation_Suite_New//testReports//SparkReport.html");
		List<ViewName> DEFAULT_ORDER = Arrays.asList(ViewName.DASHBOARD, ViewName.CATEGORY, ViewName.TEST);
		htmlReporter.viewConfigurer().viewOrder().as(DEFAULT_ORDER);
		htmlReporter.config().setTheme(Theme.DARK);
		report.attachReporter(htmlReporter);
//		report.flush();
		//bc.tearDownReport();
	}
	
	@AfterSuite
	public void tearDown() throws Exception {


//				try {
//					htmlReporter.loadXMLConfig("C://Users//debasishdey//Desktop//workspace//SAP_Automation_Workspace//Mobile_UI_Automation_Suite_New//src//test//resources//extent-config.xml");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

		File reportOutputDirectory = new File("target/cucumber");
		List<String> jsonFiles = new ArrayList<String>();
		jsonFiles.add("target/cucumber.json");
		//String buildNumber = "1";
		String projectName = "Mobile_Automation";
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		@SuppressWarnings("unused")
		Reportable result = reportBuilder.generateReports();
		
		//Send email from local after execution
		SendEmailAfterexecution.sendmail();
		bc.tearDownReport();
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
//		BaseClass.tearDownReport();
	}
}
