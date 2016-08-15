package steps;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import pages.DemoQAHomePage;
import pages.FormsPage;

public class DemoQASteps {
	
	 WebDriver driver;
	DemoQAHomePage demoQaHomePage;
	FormsPage formsPage;
	
	
	public DemoQASteps(WebDriver driver, ExtentTest logger){
		this.driver=driver;
		demoQaHomePage=new DemoQAHomePage(driver, logger);
		formsPage=new FormsPage(driver, logger);
	}
	
	public void verify_You_Are_On_DemoQa_and_Click_Register(){
		demoQaHomePage.validateHomePage();
		demoQaHomePage.clickOnRegistrationButton();
	}
	
	public void verify_You_Are_On_FormsPage_and_Fillform(){
		formsPage.validatePageTitle();
		formsPage.fillInFormAndSave();
	}
	
	public void upload_Image_and_Register(){
		formsPage.uploadImage();
		formsPage.saveForm();
	}

}
