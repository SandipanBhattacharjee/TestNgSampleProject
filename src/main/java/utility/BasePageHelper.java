package utility;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePageHelper {
	ExtentTest baselogger;
	WebDriver driver;
	
	public BasePageHelper(WebDriver driver, ExtentTest logger){
		this.driver=driver;
		baselogger=logger;
	}
	
	public boolean validatePageTitle(String expectedPageTitle, String actualPageTitle){
		boolean validated=false;
		if(expectedPageTitle.equals(actualPageTitle)){
			validated=true;
			Reporter.log("The expected page Title matches -->"+actualPageTitle);
			baselogger.log(LogStatus.PASS, "Actual Page Title matches Expected",
					baselogger.addScreenCapture(ExtentReportManager.CaptureScreen(driver)));
		}else{
			Reporter.log("<br> Expeted was -->"+expectedPageTitle+" actual is --> "+actualPageTitle);
			baselogger.log(LogStatus.FAIL, "Actual Page Title and Expected are different",
					baselogger.addScreenCapture(ExtentReportManager.CaptureScreen(driver)));
		}
		return validated;
	}

}
