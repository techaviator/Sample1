package com.pageObjectGenericMethods;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.pageObjectModel.HomePage;

public class HomePageGeneric {
	
	WebDriver driver = null;
	
	public HomePageGeneric(WebDriver driver){
		this.driver = driver;
	}
	
	public void searchBooks(Map<String, String> map){
	/*	driver.findElement(By.xpath("//input[@id='srchword']")).sendKeys(map.get("SearchKeyword"));
		driver.findElement(By.xpath("//input[@class = 'newsrchbtn']")).click();
		String searchResult = driver.findElement(By.xpath(".//span[@id='find']")).getText();
		if(searchResult.equalsIgnoreCase(map.get("BookNo.").replace(".0", ""))){
			System.out.println("Test Case has passed");
		}else{
			System.out.println("Test Case has failed");
		}*/
		
		HomePage homePage = new HomePage(driver);
		homePage.HomePage_Search_TextBox.sendKeys(map.get("SearchKeyword"));
		homePage.HomePage_Search_Button.click();
		if(homePage.HomePage_valid_searchcount_msg_List.size()>0){
			String searchResult = homePage.HomePage_valid_searchcount_msg.getText();
			if(searchResult.equalsIgnoreCase(map.get("BookNo.").replace(".0", ""))){
				System.out.println("Test Case has passed");
			}else{
				System.out.println("Test Case has failed");
			}
		}else{
			String searchResult = homePage.HomePage_invalid_searchcount_msg.getText();
			if(searchResult.contains(map.get("invalidmsg"))){
				System.out.println("Test Case has passed");
			}else{
				System.out.println("Test Case has failed");
			}
		}
	}

}
