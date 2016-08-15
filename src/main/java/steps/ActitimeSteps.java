package steps;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import pages.actitimePages.ActitimeFirstPageAfterLogin;
import pages.actitimePages.ActitimeHomePage;

public class ActitimeSteps {
	WebDriver driver;
	ActitimeHomePage actitimeHomePage;
	ActitimeFirstPageAfterLogin welcomePage;
	
	public ActitimeSteps(ExtentTest logger, WebDriver driver){
		this.driver=driver;
		actitimeHomePage=new ActitimeHomePage(logger, driver);
		welcomePage=new ActitimeFirstPageAfterLogin(logger, driver);
	}
	
	public void validate_and_LoginToActitime(){
		actitimeHomePage.validatePageTitle();
		actitimeHomePage.processLogin();
	}

	public void validate_SuccesfulLogin_and_User(){
		welcomePage.validateCorrectPage();
		welcomePage.validateCorrectUserName();
	}

	public void getAllUserFromDropdown() {
		welcomePage.logOnlyUsersFromDropDown();
		
	}
}
