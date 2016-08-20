package testdrivers;


import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import steps.DemoQASteps;
import utility.DataProviderClass;

public class DemoQA_FillForm_and_Register extends BaseTestDriver {
  
	public DemoQASteps qaSteps;
	ExtentTest logger1;
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("before class");
		driver.get("http://demoqa.com");
		driver.manage().window().maximize();
	}
	
	@Test
	public void validatePageTest(){
		logger1=extentReports.startTest("TEST: Validate we are on DemoQa Page");
		initializeStepsWithDriverAndLogger(logger1);
	    qaSteps.verify_You_Are_On_DemoQa_and_Click_Register();
	    extentReports.endTest(logger1);
	}
	
	@Test(dataProvider="fillUpForm",dataProviderClass=DataProviderClass.class)
	public void fillUpFormTest(String firstName, String lastName, String martialStatus,
			String hobby,String country,String username, String email, String phone, String password, String confPassword){
		Map<String, String> formDataMap=new HashMap<String, String>();
		formDataMap.put("firstName", firstName);
		formDataMap.put("lastName", lastName);
		formDataMap.put("martialStatus",martialStatus);
		formDataMap.put("hobby", hobby);
		formDataMap.put("country", country);
		formDataMap.put("username", username);
		formDataMap.put("email", email);
		formDataMap.put("phone", phone);
		formDataMap.put("password", password);
		formDataMap.put("confPassword", confPassword);
		
		
		logger1=extentReports.startTest("TEST: Fill up the registration form on DemoQa Page");
		initializeStepsWithDriverAndLogger(logger1);
		qaSteps.verify_You_Are_On_FormsPage_and_Fillform(formDataMap);
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
