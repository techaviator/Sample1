package com.dataprovider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.genericlibrary.XLHandler;

public class DataProviderFromExcel {
/*	  TEst Script > Dataprovider > XlHandler> ExcelReader > Excel (This is the flow of the method calls)
		MANY to ONE (Many @Test annotation to ONE dataProvider)
		Trace data from @Test method to Data provider using Method name (Package used is java.lang.reflect.Method)
		DAta flow in the framework(DDT approach)
		1. execution starts from @Test 
		2. DataProvider (gets the method name of the @Test and Pass Method Name to) > getXLdata Method in XLHandlerClass
		3. getXLdata Method in XLHandlerClass pass the method name to getMethodSheetName in XLHANDLER CLass
		4. getMethodSheetName in XLHANDLER CLass (Fetch Method Name from Reference sheet of the excel and pass the corresponding
		excel sheet name back to getXLdata Method in XLHandlerClass).
		5. Fetch data from the sheetName in the getXLData method and filter based on the RUN STATUS.
		6. populate each row into a map Object (example 5 rows , 5 map object)
		7. Each map in each Object[] ( obj = new Object[1])
		8. add each object[] into the list<Object[]>
		9. Pass list<Object[]> to DataProvider
		10. Pass the list<Object[]> from DP to @Test as Iterator<Object[]>*/
	
	@DataProvider(name="DP_excel")
	public static Iterator<Object[]> dataProviderMethod(Method m) throws FileNotFoundException, IOException{
		//System.out.println(m.getName());
		List<Object[]> xlData = XLHandler.getXlData(m.getName());
		return xlData.iterator();
	}

}
