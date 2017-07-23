package com.testScriptSample;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.genericlibrary.BaseClass;
import com.pageObjectGenericMethods.HomePageGeneric;
import com.pageObjectGenericMethods.LoginGeneric;

public class SearchScenario extends BaseClass {
	Logger log = Logger.getLogger(SearchScenario.class);
	@Test(dataProvider = "DP_excel",dataProviderClass = com.dataprovider.DataProviderFromExcel.class)
	public void validSearchScenario(Map<String,String> map) throws Exception{
		
		
		/*driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.xpath("//input[@name = 'logid']")).sendKeys(map.get("UserName"));
		driver.findElement(By.xpath("//input[@name = 'pswd']")).sendKeys(map.get("Password"));
		driver.findElement(By.xpath("//td[@class = 'sb1']/input")).click();
		String result = driver.findElement(By.xpath(".//span[@id='username']/a")).getText();
		if(result.equalsIgnoreCase(map.get("AccountID"))){
			System.out.println("Test case passed");
		}else{
			System.out.println("Test case has failed");
		}*/
		
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		HomePageGeneric homePageGeneric = new HomePageGeneric(driver);
		log.info("Launched URL");
		loginGeneric.getUserLoggedin(map);
		log.info("logged in successfull");
		homePageGeneric.searchBooks(map);
		log.info("BookSearch Complete");
		
		/*driver.findElement(By.xpath("//input[@id='srchword']")).sendKeys(map.get("SearchKeyword"));
		driver.findElement(By.xpath("//input[@class = 'newsrchbtn']")).click();*/
		//Explicit Wait
		//WebElement searchElement = driver.findElement(By.xpath(".//span[@id='find']"));
		/*WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@id='find']")));
		*/
	/*	String searchResult = driver.findElement(By.xpath(".//span[@id='find']")).getText();
		if(searchResult.equalsIgnoreCase(map.get("BookNo.").replace(".0", ""))){
			System.out.println("Test Case has passed");
		}else{
			System.out.println("Test Case has failed");
		}*/
		
	}
	
	@Test(dataProvider = "DP_excel",dataProviderClass = com.dataprovider.DataProviderFromExcel.class)
	public void invalidSearchScenario(Map<String,String> map) throws Exception{
		
	/*	
		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.xpath("//input[@name = 'logid']")).sendKeys(map.get("UserName"));
		driver.findElement(By.xpath("//input[@name = 'pswd']")).sendKeys(map.get("Password"));
		driver.findElement(By.xpath("//td[@class = 'sb1']/input")).click();
		String result = driver.findElement(By.xpath(".//span[@id='username']/a")).getText();
		if(result.equalsIgnoreCase(map.get("AccountID"))){
			System.out.println("Test case passed");
		}else{
			System.out.println("Test case has failed");
		}*/
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		HomePageGeneric homePageGeneric = new HomePageGeneric(driver);
		
		loginGeneric.getUserLoggedin(map);
		homePageGeneric.searchBooks(map);
		
		/*driver.findElement(By.xpath("//input[@id='srchword']")).sendKeys(map.get("SearchKeyword"));
		driver.findElement(By.xpath("//input[@class = 'newsrchbtn']")).click();*/
		//Explicit Wait
		//WebElement searchElement = driver.findElement(By.xpath(".//span[@id='find']"));
		/*WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@id='find']")));
		*/
	/*	String searchResult = driver.findElement(By.xpath(".//*[@id='notify']/p")).getText();
		if(searchResult.contains(map.get("invalidmsg"))){
			System.out.println("Test Case has passed");
		}else{
			System.out.println("Test Case has failed");
		}*/
		
	}

}
