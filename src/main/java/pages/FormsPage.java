package pages;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

import testdrivers.BaseTestDriver;
import utility.BasePageHelper;

public class FormsPage {

	WebDriver driver;
	private final String pageTitle="Registration | Demoqa"; 
	BasePageHelper basePage;
	ExtentTest pageLogger;
	String fixedPartOfResouces=System.getProperty("user.dir")+"\\src\\test\\java\\resources\\propertyfiles\\";
	String uploadFileExePath_Chrome=fixedPartOfResouces+"UploadFile_Chrome.exe";
	String uploadFileExePath_Firefox=fixedPartOfResouces+"FileUpload_Firefox.exe";
	String imagePath=fixedPartOfResouces+"mavni.png";
	BaseTestDriver baseTest=new BaseTestDriver();

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


	@FindBy(xpath="//input[@value='cricket ']")
	WebElement hobbyCricket;

	@FindBy(xpath="//input[@value='dance']")
	WebElement hobbyDance;

	@FindBy(xpath="//input[@value='reading']")
	WebElement hobbyReading;



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

	public void fillInFormAndSave(Map<String, String> formDataMap) {
		fillDataInTextField(firstName, formDataMap.get("firstName"));
		fillDataInTextField(lastName, formDataMap.get("lastName"));

		driver.findElement(By.xpath("//input[@value='"+formDataMap.get("martialStatus")+"']")).click(); //married radio button.

		clickOnRequiredCheckbox(formDataMap.get("hobby")); // processing the checkbox

		Select countryDropdown=new Select(countrySelect);
		countryDropdown.selectByValue(formDataMap.get("country")); //country dropdown

		fillDataInTextField(phone, formDataMap.get("phone"));
		fillDataInTextField(userName, formDataMap.get("username"));
		fillDataInTextField(email,formDataMap.get("email"));
		fillDataInTextField(password,formDataMap.get("password"));
		fillDataInTextField(confirmPassword, formDataMap.get("confPassword"));

	}

	public void uploadImage() {
		String browser=baseTest.getBrowserFromProperty();
		String cmd=null;
		if(browser.equals("firefox")){
			cmd=uploadFileExePath_Firefox+" "+imagePath;
		}else if(browser.equals("chrome")){
			cmd=uploadFileExePath_Chrome+" "+imagePath;
		}
		try {
			imageUpload.click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println(cmd);
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveForm() {
		submitButton.submit();

	}

	private void fillDataInTextField(WebElement element, String text){
		element.clear();
		element.sendKeys(text);
	}

	private void uncheckCheckBoxIfAlreadyChecked(WebElement element){
		if(element.isSelected()){
			element.click();
		}
	}

	private void executePreconditon(){
		uncheckCheckBoxIfAlreadyChecked(hobbyCricket);
		uncheckCheckBoxIfAlreadyChecked(hobbyDance);
		uncheckCheckBoxIfAlreadyChecked(hobbyReading);
	}

	private void clickOnRequiredCheckbox(String choice){
		if(choice.contains("reading")){
			executePreconditon();
			hobbyReading.click();		
		}else if(choice.contains("dance")){
			executePreconditon();
			hobbyDance.click();	
		}else{
			executePreconditon();
			hobbyCricket.click();	
		}

	}

}
