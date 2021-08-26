package com.crm.qa.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static long PAE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 30;
	
	
	public static String DATA_FILE_PATH="D:\\SeleniumJava\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRMTestData.xlsx";
	static Workbook book;
	static Sheet dataSheet;
	
	
	
	public void switchToFrame() 
	{
		driver.switchTo().frame("mainpanel");
	}	
	
	
	//read excel
	public static Object[][] getTestData(String sheetName)
	{		
		FileInputStream file=null;; 
		try {
			file = new FileInputStream(DATA_FILE_PATH);
			}catch(FileNotFoundException e) {
			e.printStackTrace();}
		
		try {
			book = WorkbookFactory.create(file);
			} catch (InvalidFormatException e){
				e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();}
		
		dataSheet=book.getSheet(sheetName);
		Object[][] data = new Object[dataSheet.getLastRowNum()][dataSheet.getRow(0).getLastCellNum()];
		
		for (int i=0; i<dataSheet.getLastRowNum(); i++) {
	        Row row = dataSheet.getRow(i+1);
	        for (int j = 0; j < row.getLastCellNum(); j++){						
	        	data[i][j]=row.getCell(j).toString();
	        }    
	    }	
		return data;
	}
	

	public static void takeScreenshotAtEndOfTest() throws IOException 
	{
		TakesScreenshot srcShot = ((TakesScreenshot)driver);
		File scrFile = srcShot.getScreenshotAs(OutputType.FILE); 
		String currentDir = System.getProperty("user.dir");
		File destFile = new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png");	
		FileUtils.copyFile(scrFile, destFile);
	}	
}
