package pages.actitimePages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import utility.BasePageHelper;

public class ActitimeHomePage {
	
	WebDriver driver;
	BasePageHelper helper;
	
	private final String expectedPageTitle="actiTIME - Login";
	public ActitimeHomePage(ExtentTest logger, WebDriver driver){
		this.driver=driver;
		helper=new BasePageHelper(driver, logger);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="username")
	WebElement userName;
	
	@FindBy(name="pwd")
	WebElement passWord;
	
	@FindBy(id="loginButton")
	WebElement loginButton;
	
	
	public void validatePageTitle(){
		helper.validatePageTitle(expectedPageTitle, driver.getTitle());
	}
	
	public void processLogin(){
		userName.sendKeys("admin");
		passWord.sendKeys("manager");
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

}
