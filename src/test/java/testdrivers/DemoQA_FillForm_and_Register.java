package testdrivers;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import steps.DemoQASteps;

public class DemoQA_FillForm_and_Register extends BaseTestDriver {
  
	public DemoQASteps qaSteps;
	ExtentTest logger1;
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("before class");
		driver.get("http://demoqa.com");
	}
	
	@Test
	public void validatePageTest(){
		logger1=extentReports.startTest("TEST: Validate we are on DemoQa Page");
		initializeStepsWithDriverAndLogger(logger1);
	    qaSteps.verify_You_Are_On_DemoQa_and_Click_Register();
	    extentReports.endTest(logger1);
	}
	
	@Test
	public void fillUpFormTest(){
		logger1=extentReports.startTest("TEST: Fill up the registration form on DemoQa Page");
		initializeStepsWithDriverAndLogger(logger1);
		qaSteps.verify_You_Are_On_FormsPage_and_Fillform();
		extentReports.endTest(logger1);
	}
	
	@Test
	public void uploadImageAndSubmitDataTest(){
		logger1=extentReports.startTest("TEST:Upload image and click on Save button");
		initializeStepsWithDriverAndLogger(logger1);
		qaSteps.upload_Image_and_Register();
		extentReports.endTest(logger1);
	}
	
	
	private void initializeStepsWithDriverAndLogger(ExtentTest logger){
		qaSteps=new DemoQASteps(driver,logger);
	}
	
}
