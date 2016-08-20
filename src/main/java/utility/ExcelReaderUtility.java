package utility;


import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReaderUtility {

	private Workbook workbook;
	private Sheet sheet;
	private int foundRow_StartOfTestCase;
	private int foundColumn_StartOfTestCase;
	private int foundRow_EndOfTestCase;
	private String testCaseName;

	private final String filePathFixedPart=System.getProperty("user.dir")+"//src//test//java//datafiles//";
	
	public ExcelReaderUtility(String fileName, String sheetName, String testCaseName){
		this.testCaseName=testCaseName;
		String filePath=filePathFixedPart+fileName;
		initializeWorkbook(filePath);
		initializeSheet(sheetName);
	}

	private void initializeWorkbook(String filePath){
		try{
			workbook=WorkbookFactory.create(new File(filePath));
		}catch(InvalidFormatException exception){
			System.out.println("File format not correct" +exception.getMessage());
		}catch(IOException exception){
			System.out.println("file not found"+exception.getMessage());
		}
	}

	public void initializeSheet(String sheetName){
		sheet=workbook.getSheet(sheetName);		
	}


	private void initializeTheStartingRow_and_ColumnOfTheTestCase(){
		boolean found=false;
		try{
			for(Row row:sheet){			
				for(Cell cell:row){	
					if(cell.getRichStringCellValue().getString().equals(testCaseName)){
						found=true; 
						setFoundRow_StartOfTestCase(row.getRowNum());  
						setFoundColumn_StartOfTestCase(cell.getColumnIndex());
						break;
					}				
				}if(found){
					break;
				}
			}
		}catch(NullPointerException exception){
			System.out.println("The test case might Not exist" +exception.getMessage());
		}
	}

	private int findTheNumberOfRowsInATestCase(){
		try{
			for(int rowCounter=(getFoundRow_StartOfTestCase()+1);rowCounter<(sheet.getLastRowNum()+1);rowCounter++){
				Row row=sheet.getRow(rowCounter);
				Cell endCell=row.getCell(getFoundColumn_StartOfTestCase());
				if(endCell.getRichStringCellValue().getString().equals(getTestCaseName())){
					setFoundRow_EndOfTestCase(row.getRowNum());
					break;
				}
			}
		}catch(NullPointerException exception){
			System.out.println("Check if the test case exist in the excel sheet"+exception.getMessage());
		}

		int numberOfLinesOfTestData=(getFoundRow_EndOfTestCase()-getFoundRow_StartOfTestCase())-1;
		return numberOfLinesOfTestData;
	}

	private int findTheNumberOfColumnsInA_TestCase(){
		int columncounter=0; //because column counter will point to the cloumn next to testcase name
		Row row=sheet.getRow(getFoundRow_StartOfTestCase());
		int intialColumnIndex=getFoundColumn_StartOfTestCase()+columncounter;
		Cell cellWithData=row.getCell(intialColumnIndex);
		try{
			while(!(Cell.CELL_TYPE_BLANK==cellWithData.getCellType())){
				columncounter++;
				cellWithData=row.getCell(intialColumnIndex+columncounter);		

			}
		}catch(NullPointerException exception){
			
		}
		return (columncounter-1); 
	}


	public Object[][] readNumberOfLinesInTestCase_andReturnDataArray(){
		initializeTheStartingRow_and_ColumnOfTheTestCase();
		int testCaseStartingRowNum=getFoundRow_StartOfTestCase()+1; //because the starting row will be all headers;
		int numberOfRowsOfTestCase=findTheNumberOfRowsInATestCase();
		int testCaseStartingColumnNum=getFoundColumn_StartOfTestCase()+1; //because the data of the test case starts from the second column
		int numberOfColumnInTheTestCase=findTheNumberOfColumnsInA_TestCase();

		Object[][] testDataArray =new String[numberOfRowsOfTestCase][numberOfColumnInTheTestCase];

		for(int rowIterator=testCaseStartingRowNum;rowIterator<=numberOfRowsOfTestCase;rowIterator++){
			Row row=sheet.getRow(rowIterator);
			for(int columnIterator=testCaseStartingColumnNum;columnIterator<=numberOfColumnInTheTestCase;columnIterator++){				
				Cell cell=row.getCell(columnIterator);
				testDataArray[rowIterator-1][columnIterator-1]=cell.getRichStringCellValue().toString();
			}
		}

		return testDataArray;
	}

		
	private int getFoundRow_StartOfTestCase() {
		return foundRow_StartOfTestCase;
	}

	private void setFoundRow_StartOfTestCase(int foundRow_StartOfTestCase) {
		this.foundRow_StartOfTestCase = foundRow_StartOfTestCase;
	}

	private int getFoundColumn_StartOfTestCase() {
		return foundColumn_StartOfTestCase;
	}

	private void setFoundColumn_StartOfTestCase(int foundColumn_StartOfTestCase) {
		this.foundColumn_StartOfTestCase = foundColumn_StartOfTestCase;
	}

	private int getFoundRow_EndOfTestCase() {
		return foundRow_EndOfTestCase;
	}

	private void setFoundRow_EndOfTestCase(int foundRow_EndOfTestCase) {
		this.foundRow_EndOfTestCase = foundRow_EndOfTestCase;
	}

	private String getTestCaseName() {
		return testCaseName;
	}

	private void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}





}
