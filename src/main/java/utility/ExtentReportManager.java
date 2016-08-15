package utility;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportManager {
	private static final String EXTENT_REPORT_PATH=System.getProperty("user.dir")+"//target//java//reports//";
	private static final String EXTENT_IMAGE_PATH=System.getProperty("user.dir")+"//target//java//reports//images//";
	
	public static ExtentReports Instance(String browser){
		ExtentReports extent;
		String Path = EXTENT_REPORT_PATH+"//TestReport"+"_"+browser+Calendar.getInstance().getTimeInMillis()+".html";
		System.out.println(Path);
		extent = new ExtentReports(Path, false);
		extent.assignProject("Selenium Architect Assignment Test Report");
		return extent;
	}
	
	
	public static String CaptureScreen(WebDriver driver)
	{
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File screenShotFile = screenShot.getScreenshotAs(OutputType.FILE);
		String imagePath=EXTENT_IMAGE_PATH+"//image"+Calendar.getInstance().getTimeInMillis()+".png";
		File screenShotDestination = new File(imagePath);
		try {
			FileUtils.copyFile(screenShotFile, screenShotDestination);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return imagePath;
	}
}


