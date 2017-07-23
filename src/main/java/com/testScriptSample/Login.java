package com.testScriptSample;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.genericlibrary.BaseClass;
import com.pageObjectGenericMethods.LoginGeneric;


public class Login extends BaseClass{
	Logger log = Logger.getLogger(Login.class);
	String info = "Changes are added here";
	@Test(dataProvider = "DP_excel", dataProviderClass = com.dataprovider.DataProviderFromExcel.class)
	public void validLoginScenario(Map<String, String> map) throws Exception{
			
		/*driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.xpath("//input[@name = 'logid']")).sendKeys(map.get("UserName"));
		driver.findElement(By.xpath("//input[@name = 'pswd']")).sendKeys(map.get("Password"));
		driver.findElement(By.xpath("//td[@class = 'sb1']/input")).click();
		String result = driver.findElement(By.xpath(".//span[@id='username']/a")).getText();*/
		
		/*HomePage homePage = new HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		homePage.HomePage_SignIn_Link.click();
		loginPage.LoginPage_UserName_TextBox.sendKeys(map.get("UserName"));
		loginPage.LoginPage_Password_TextBox.sendKeys(map.get("Password"));
		loginPage.LoginPage_Submit_Button.click();
		String result = homePage.HomePage_AccountID.getText();*/
		log.info("Launch URL");
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);		
		
		loginGeneric.getUserLoggedin(map);
		log.info("User logged successfully");
		
	}
	
	@Test(dataProvider = "DP_excel",dataProviderClass = com.dataprovider.DataProviderFromExcel.class)
	public void invalidLoginScenario(Map<String, String> map) throws Exception{
		
	/*	driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.xpath("//input[@name = 'logid']")).sendKeys(map.get("UserName"));
		driver.findElement(By.xpath("//input[@name = 'pswd']")).sendKeys(map.get("Password"));
		driver.findElement(By.xpath("//td[@class = 'sb1']/input")).click();
		String result = driver.findElement(By.xpath(".//b[contains(.,'Sorry')]")).getText();*/
		
	/*	HomePage homePage = new HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		homePage.HomePage_SignIn_Link.click();
		loginPage.LoginPage_UserName_TextBox.sendKeys(map.get("UserName"));
		loginPage.LoginPage_Password_TextBox.sendKeys(map.get("Password"));
		loginPage.LoginPage_Submit_Button.click();*/
		
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);		
		
		loginGeneric.getUserLoggedin(map);
			
		
		
	}

}
