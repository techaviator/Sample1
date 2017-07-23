package com.pageObjectModel;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	//driver.findElement(By.linkText("Sign In")).click();
	
	//String result = driver.findElement(By.xpath(".//span[@id='username']/a")).getText();
	
	/*driver.findElement(By.xpath("//input[@id='srchword']")).sendKeys(map.get("SearchKeyword"));
	driver.findElement(By.xpath("//input[@class = 'newsrchbtn']")).click();*/
	//driver.findElement(By.xpath(".//span[@id='find']")).getText();
	//driver.findElement(By.xpath(".//*[@id='notify']/p")).getText();
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@id='srchword']")
	public WebElement HomePage_Search_TextBox;	
	
	@FindBy(xpath = "//input[@class = 'newsrchbtn']")
	public WebElement HomePage_Search_Button;	
	
	
	@FindBy(xpath = ".//span[@id='find']")
	public WebElement HomePage_valid_searchcount_msg;
	
	@FindBy(xpath = ".//span[@id='find']")
	public List<WebElement> HomePage_valid_searchcount_msg_List;
	
	@FindBy(xpath = ".//*[@id='notify']/p")
	public WebElement HomePage_invalid_searchcount_msg;
	
	@FindBy(linkText = "Sign In")
	public WebElement HomePage_SignIn_Link;	
	

	@FindBy(xpath = ".//span[@id='username']/a")
	public WebElement HomePage_AccountID;
	
	@FindBy(xpath = ".//span[@id='username']/a")
	public List<WebElement> HomePage_AccountID_list;
	
	
	
/*	@FindBy(xpath = ".//span[@id='username']/a")
	public List<WebElement> HomePage_AccountID_1;
	
	@FindAll({@FindBy(xpath = ".//span[@id='username']/a")})
	public List<WebElement> y;*/
	
	
	
	
	
	
	
}
