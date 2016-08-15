package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utility.BasePageHelper;
import utility.ExtentReportManager;

public class DemoQAHomePage {

	WebDriver driver;
	private final String pageTitle="Demoqa | Just another WordPress site"; 
	BasePageHelper basePage;
	ExtentTest pageLogger;

	@FindBy(linkText="Registration")
	WebElement registrationButton;

	public DemoQAHomePage(WebDriver driver, ExtentTest logger){
		this.driver=driver;		
		pageLogger=logger;
		basePage=new BasePageHelper(driver, pageLogger);
		PageFactory.initElements(driver, this);
	}

	public boolean validateHomePage(){
		return basePage.validatePageTitle(pageTitle, driver.getTitle());
	}

	public void clickOnRegistrationButton(){
		if(validateHomePage()){
		    pageLogger.log(LogStatus.PASS, "We came to demoQa page",
		    		pageLogger.addScreenCapture(ExtentReportManager.CaptureScreen(driver)));
			registrationButton.click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}
}
