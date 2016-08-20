package testdrivers;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;

import utility.ExtentReportManager;
import utility.ProcessPropertyFile;

public class BaseTestDriver extends ProcessPropertyFile {

	public static WebDriver driver;
	private final String fixedFilePath=System.getProperty("user.dir")+"/src/test/java/resources";
	private final String propertyFilePath=fixedFilePath+"/propertyfiles/configuration.properties";
	private final String chromeFilePath=fixedFilePath+"/drivers/chromedriver.exe";
	private final String ieFilePath=fixedFilePath+"/drivers/IEDriverServer64.exe";
	private ProcessPropertyFile propertyFile;
	private String browser;
	public  ExtentReports extentReports;

	@BeforeSuite
	public void beforeSuite(){
		System.out.println("before suite");
		browser=getBrowserFromProperty();
		driver=getDriver(driver, browser);
		extentReports=ExtentReportManager.Instance(browser);

	}

	@AfterSuite
	public void tearDown(){
		System.out.println("teardown before time");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		extentReports.flush();
		System.out.println("after suite");
//		if(driver!=null){
//			driver.quit();
//		}
	}

	public String getBrowserFromProperty(){
		propertyFile=new ProcessPropertyFile();
		Properties configFile=propertyFile.getPropertyFile(propertyFilePath);
		return configFile.getProperty("browser");
	}
	
	
	private WebDriver getDriver(WebDriver driver, String browser){
		if(browser.equals("firefox")){
			driver=new FirefoxDriver();
		}else if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver",chromeFilePath);
			driver=new ChromeDriver();
		}else if(browser.equals("ie")){
			System.setProperty("webdriver.ie.driver", ieFilePath);
			driver=new InternetExplorerDriver();
		}else{
			driver=new FirefoxDriver();
		}
		return driver;

	}

}
