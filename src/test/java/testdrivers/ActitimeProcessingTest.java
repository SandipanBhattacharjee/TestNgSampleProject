package testdrivers;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import steps.ActitimeSteps;
import utility.ExtentReportManager;

public class ActitimeProcessingTest extends BaseTestDriver {
	ActitimeSteps actitimeSteps;
	ExtentTest logger1;
	
	@BeforeClass
	public void beforeClass(){
		driver.get("http://localhost/login.do");
		extentReports=ExtentReportManager.Instance(getBrowserFromProperty());
	}
	
	@Test
	public void loginToActitime(){
		initializeLoggerAndSteps(logger1, "TEST: Validate we are on Actitime and Successful login");
		actitimeSteps.validate_and_LoginToActitime();
		extentReports.endTest(logger1);
	}
	
	@Test
	public void validateLoggedInUser(){
		initializeLoggerAndSteps(logger1, "TEST:Validate login was successful and logged in as correct user");
		actitimeSteps.validate_SuccesfulLogin_and_User();
		extentReports.endTest(logger1);
	}
	
	@Test
	public void logUsersFromThePage(){
		initializeLoggerAndSteps(logger1, "TEST: Validate all the expected users were present in the dropdown");
		actitimeSteps.getAllUserFromDropdown();
		extentReports.endTest(logger1);
	}

	
	private void initializeLoggerAndSteps(ExtentTest logger, String testStartMessage){
		logger=extentReports.startTest(testStartMessage);
		actitimeSteps=new ActitimeSteps(logger,driver);
	}

}
