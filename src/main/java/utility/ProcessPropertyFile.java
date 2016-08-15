package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Reporter;

public class ProcessPropertyFile {
	public Properties getPropertyFile(String filePath){
		Properties propertyFile=null;
		try{
			propertyFile=new Properties();
			FileInputStream objectFile=new FileInputStream(filePath);
			propertyFile.load(objectFile);
		}catch(IOException e){
			Reporter.log("File is not present"+e.getMessage());
		}
		return propertyFile;

	}

}
