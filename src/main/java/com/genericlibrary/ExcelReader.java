package com.genericlibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private XSSFWorkbook wb = null;
	//Connection method
	public ExcelReader(String fileName) throws FileNotFoundException, IOException{
		FileInputStream fis = new FileInputStream(fileName);
		
		wb = new XSSFWorkbook(fis);
		if(wb==null){
			System.out.println("Connection UnSuccessfull");
		}
		fis.close();
		
	}
	//Get total row count
	public int getTotalRowCount(String sheetName){
		return wb.getSheet(sheetName).getLastRowNum()+1;
		
	}
	
	//Get Total col count
	
	public int getTotalColumnCount(String sheetName, int rowIndex){
		int colnum = 0;
		if(rowIndex>0)
		{
			rowIndex=rowIndex-1;
			colnum=wb.getSheet(sheetName).getRow(rowIndex).getLastCellNum();
		}
		else{
			System.out.println("There is no row number "+rowIndex);
		}
		return colnum;
	}
	
	public int getTotalColumnCount(String sheetName){
		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}
	
	// Read cell values
	
	public String readCellvalue(String sheetName, int rowIndex, int colIndex){
		XSSFCell cell = wb.getSheet(sheetName).getRow(rowIndex-1).getCell(colIndex-1);
		String cellValue = null;
		if(cell.getCellType()==Cell.CELL_TYPE_STRING){
			cellValue = cell.getStringCellValue();
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
			double numericCellValue = cell.getNumericCellValue();
			cellValue = String.valueOf(numericCellValue);
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
			cellValue = "";
		}
		return cellValue;
	}
	
	// Read cell values with colName
	
		public String readCellvalue(String sheetName, int rowIndex, String colName){
			int colIndex = getcolNameIndex(sheetName, colName);
			if(colIndex!=-1){
				XSSFCell cell = wb.getSheet(sheetName).getRow(rowIndex-1).getCell(colIndex-1);
				String cellValue = null;
				if(cell.getCellType()==Cell.CELL_TYPE_STRING){
					cellValue = cell.getStringCellValue();
				}
				else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
					double numericCellValue = cell.getNumericCellValue();
					cellValue = String.valueOf(numericCellValue);
				}
				else if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
					cellValue = "";
				}
				return cellValue;
			}
			else{
				return "No Data";
			}
			
		}
	
	// Write cell values
	public void setXLCellValues(String sheetName, int rowIndex, int ColIndex , String value){
		XSSFRow row = wb.getSheet(sheetName).getRow(rowIndex-1);
		if(row==null){
			row = wb.getSheet(sheetName).createRow(rowIndex-1);
		}
		XSSFCell cell = wb.getSheet(sheetName).getRow(rowIndex-1).getCell(ColIndex-1);
		if(cell==null){
			cell = wb.getSheet(sheetName).getRow(rowIndex-1).createCell(ColIndex-1);
		}
		wb.getSheet(sheetName).getRow(rowIndex-1).getCell(ColIndex-1).setCellValue(value);
	}
	
	
	private int getcolNameIndex(String sheetName, String colName){
		int totalColumnCount = getTotalColumnCount(sheetName, 1);
		int temp = 0;
		boolean flag = false;
		for(int i = 0;i<totalColumnCount;i++){			
			if(wb.getSheet(sheetName).getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(colName)){
				temp = i+1;
				flag = true;
				break;
			}
		}
		if(flag){
			return temp;
		}
		else{
			return -1;
		}		
	}
	
	//Close excel
	public void saveAndQuitXL(String path) throws Exception{
		FileOutputStream fout = new FileOutputStream(path);
		wb.write(fout);
		fout.close();
		wb.close();
		
	}
	
	

}
