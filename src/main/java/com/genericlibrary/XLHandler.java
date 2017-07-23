package com.genericlibrary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XLHandler {
	/*Execution flow in
	XLHANDLER
		1. getXlData method gets the method name of the @TEST for which data needs be fetched
		2. Pass the method to getMethodSheetName.
		3. To get the sheetName of the Method from the ReferenceSheet.
		4. Access all the rows in the 2nd column of the Reference sheet to match method Name.
		5. Get the sheetName from 3rd column once method name match and break the loop
		6. getMethodSheetName should return the sheet Name to getXlData
		7. Filter the rows based on the method Name and RunStatus at the row level
		8. Filtered rows are first inserted into each map object
		9. Each map objects are injected into each Object []  
		10 Each object arrays are stored in the List<Object[]>
		11. return the List<Object[]> to the data provider.
	*/
	
	
	/*public static void main(String[] args) throws FileNotFoundException, IOException {
		List<Object[]> xlData = getXlData();
		System.out.println(xlData.size());
	}*/
	private static ExcelReader xl = null;
	private static String referenceSheetName = "Reference";
	public static String getMethodSheetName(String methodName) throws FileNotFoundException, IOException{
		xl = new ExcelReader(System.getProperty("user.dir")+ReadPropertyFile.getPropertyFileData("EXCEL_PATH"));
		int totalRowCount = xl.getTotalRowCount(referenceSheetName);
		String xlSheetName = null;
		for(int i =2;i<=totalRowCount;i++){
			String xlMethodName = xl.readCellvalue(referenceSheetName, i, 2);
			if(xlMethodName.equalsIgnoreCase(methodName)){
				xlSheetName = xl.readCellvalue(referenceSheetName, i, 3);
				break;
			}
		}return xlSheetName;		
		
	}
	public static List<Object[]> getXlData(String methodName) throws FileNotFoundException, IOException{
		String sheetName = getMethodSheetName(methodName);
		
		//ExcelReader xl = new ExcelReader("D:\\Jim\\Personal\\RK\\Feb_Batch\\Jun2017\\src\\test\\resources\\BingSearch.xlsx");
		int totalRowCount = xl.getTotalRowCount(sheetName);
		int totalColumnCount = xl.getTotalColumnCount(sheetName);
		Map<String ,String> map = null;
		Object[] obj = null;
		List<Object[]> ls = new ArrayList<Object[]>();
		for(int i=2;i<=totalRowCount;i++){
			if(xl.readCellvalue(sheetName, i, 3).equalsIgnoreCase(methodName)&&(xl.readCellvalue(sheetName, i, 4).equalsIgnoreCase("Y"))){
			map = new HashMap<String ,String>();
			obj = new Object[1];
				for(int j = 1;j<=totalColumnCount;j++){				
					//System.out.print(xl.readCellvalue(sheetName, i, j));
					map.put(xl.readCellvalue(sheetName, 1, j), xl.readCellvalue(sheetName, i, j));
					//System.out.print("\t");
					
				}System.out.println(map);
				obj[0] = map;
				ls.add(obj);
			//System.out.println();
			}
		}		
		return ls;
	}

}
