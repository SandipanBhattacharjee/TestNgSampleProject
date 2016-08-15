package pages;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

import utility.BasePageHelper;

public class FormsPage {

	WebDriver driver;
	private final String pageTitle="Registration | Demoqa"; 
	BasePageHelper basePage;
	ExtentTest pageLogger;
	String uploadFileExePath=System.getProperty("user.dir")+"\\src\\test\\java\\resources\\propertyfiles\\UploadFile.exe";
	String imagePath=System.getProperty("user.dir")+"\\src\\test\\java\\resources\\propertyfiles\\mavni.png";
	//C:\JBehaveTestNg\misctest

	public FormsPage(WebDriver driver, ExtentTest logger){
		this.driver=driver;		
		pageLogger=logger;
		basePage=new BasePageHelper(driver, pageLogger);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="name_3_firstname")
	WebElement firstName;
	
	@FindBy(id="name_3_lastname")
	WebElement lastName;
	
	@FindBy(xpath="//input[@value='married']")
	WebElement martialStatus;
	
	@FindBy(xpath="//input[@value='cricket ']")
	WebElement hobby;
	
	@FindBy(id="dropdown_7")
	WebElement countrySelect;
	
	@FindBy(id="phone_9")
	WebElement phone;
	
	@FindBy(id="username")
	WebElement userName;
	
	@FindBy(id="email_1")
	WebElement email;
	
	@FindBy(id="password_2")
	WebElement password;
	
	@FindBy(id="confirm_password_password_2")
	WebElement confirmPassword;
	
	@FindBy(id="profile_pic_10")
	WebElement imageUpload;
	
	@FindBy(name="pie_submit")
	WebElement submitButton;

	public void validatePageTitle() {
		basePage.validatePageTitle(pageTitle, driver.getTitle());
		
	}

	public void fillInFormAndSave() {
		firstName.sendKeys("Sandipan");
		lastName.sendKeys("Bhatta");
		martialStatus.click();
		hobby.click();
		Select countryDropdown=new Select(countrySelect);
		countryDropdown.selectByValue("India");
		phone.sendKeys("6184130226");
		userName.sendKeys("Sandy");
		email.sendKeys("sandy@gmail.com");
		password.sendKeys("joy123");
		confirmPassword.sendKeys("joy123");
	}

	public void uploadImage() {
		String cmd=uploadFileExePath+" "+imagePath;
		try {
			imageUpload.click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println(cmd);
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void saveForm() {
		submitButton.submit();
		
	}
	
	
}
