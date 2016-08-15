package pages.actitimePages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utility.BasePageHelper;

public class ActitimeFirstPageAfterLogin {
	
	WebDriver driver;
	private final String expectedPageTitle="actiTIME - Enter Time-Track";
	private final String expectedUser="Robert Barber";
	BasePageHelper helper;
	ExtentTest logger;
	
	public ActitimeFirstPageAfterLogin(ExtentTest logger, WebDriver driver){
		this.driver=driver;
		this.logger=logger;
		helper=new BasePageHelper(driver, logger);
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//a[contains(@class,'username')]")
	WebElement loggedOnUser;
	
	@FindBys({
		@FindBy(xpath="//div[contains(@class, 'singleUserSelector_list')]//span[contains(@class,'userName')]")
	})
	List<WebElement> usersList;
	
	@FindBy(xpath=".//img[contains(@class,'x-form-arrow-trigger')]")
	WebElement dropDownArrow;
	
	
   public void validateCorrectPage(){
	   helper.validatePageTitle(expectedPageTitle, driver.getTitle());
   }

    public void validateCorrectUserName(){
    	String actualLoggedOnUser=loggedOnUser.getText();
    	if(actualLoggedOnUser.equals(expectedUser)){
    		Reporter.log("<br>Actual logged on user and expected users are same-->"+ actualLoggedOnUser);
    		logger.log(LogStatus.INFO, "Actual logged on user and expected users are same-->"+ actualLoggedOnUser);
    		
    	}else{
    		Reporter.log("<br>Actual user -->"+actualLoggedOnUser+" expected user -->"+expectedUser);
    		logger.log(LogStatus.INFO, "<br>Actual user -->"+actualLoggedOnUser+" expected user -->"+expectedUser);
    	}

    }
    
    public void logOnlyUsersFromDropDown(){
    	dropDownArrow.click();
    	for(WebElement user:usersList){
    		Reporter.log("<br> Users from dropdown --> "+user.getText());
    		logger.log(LogStatus.INFO, "Users from dropdown --> "+user.getText());
    	}
    }
}
