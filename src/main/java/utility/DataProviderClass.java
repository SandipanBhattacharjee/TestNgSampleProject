package utility;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	private static String formDataProviderExcel="TestData.xlsx";
	private static String formDataSheetName="sheet1";	
	static ExcelReaderUtility excelReader;

	@DataProvider(name="fillUpForm")
	public static Object[][] getDataToFillUpForm(){
		String testCaseName="TestCase1";
		excelReader=new ExcelReaderUtility(formDataProviderExcel, formDataSheetName, testCaseName);	
		Object[][] formData=excelReader.readNumberOfLinesInTestCase_andReturnDataArray();
		return formData;
	}


}
