package com.pageObjectGenericMethods;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.genericlibrary.BaseClass;
import com.pageObjectModel.HomePage;
import com.pageObjectModel.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginGeneric {
	WebDriver driver = null;
	ExtentTest startTest = null;
	
	public LoginGeneric(WebDriver driver,ExtentTest startTest){
		this.driver =driver;
		this.startTest = startTest;
	}
	public void getUserLoggedin(Map<String,String> map) throws Exception{
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		homePage.HomePage_SignIn_Link.click();
		loginPage.LoginPage_UserName_TextBox.sendKeys(map.get("UserName"));
		loginPage.LoginPage_Password_TextBox.sendKeys(map.get("Password"));
		loginPage.LoginPage_Submit_Button.click();
		startTest.log(LogStatus.INFO, "Username and password has been entered","successfull");
		if(homePage.HomePage_AccountID_list.size()>0){
			String result = homePage.HomePage_AccountID.getText();
			if(result.equalsIgnoreCase(map.get("AccountID"))){
				//System.out.println("Test case passed");
				startTest.log(LogStatus.INFO,"Successfully logged in as "+map.get("AccountID"),"Successfull");
			}else{
				System.out.println("Test case has failed");
				startTest.log(LogStatus.FAIL,"failed to login in as "+map.get("AccountID"),"Failed");
				BaseClass.forceFailTestCases("The login parameters are wrong");
			}
		}else if(loginPage.LoginPage_InvalidLogin_Message_list.size()>0){
			String result = loginPage.LoginPage_InvalidLogin_Message.getText();
			if(result.equalsIgnoreCase(map.get("InvalidMsg"))){
				System.out.println("Test case passed");
			}else{
				System.out.println("Test case has failed");
			}
		}	
		else{
			startTest.log(LogStatus.FAIL,"failed to login in as "+map.get("AccountID"),"Failed");
			BaseClass.forceFailTestCases("The login parameters are wrong");
		}
		
	}

}
