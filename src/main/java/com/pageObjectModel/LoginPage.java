package com.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	//driver.findElement(By.xpath("//input[@name = 'logid']")).sendKeys(map.get("UserName"));
	//driver.findElement(By.xpath("//input[@name = 'pswd']")).sendKeys(map.get("Password"));
	//driver.findElement(By.xpath("//td[@class = 'sb1']/input")).click();
	//String result = driver.findElement(By.xpath(".//b[contains(.,'Sorry')]")).getText();
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name = 'logid']")
	public WebElement LoginPage_UserName_TextBox;
	
	@FindBy(xpath = "//input[@name = 'pswd']")
	public WebElement LoginPage_Password_TextBox;
	
	@FindBy(xpath = "//td[@class = 'sb1']/input")
	public WebElement LoginPage_Submit_Button;
	
	@FindBy(xpath = ".//b[contains(.,'Sorry')]")
	public WebElement LoginPage_InvalidLogin_Message;
	
	@FindBy(xpath = ".//b[contains(.,'Sorry')]")
	public List<WebElement> LoginPage_InvalidLogin_Message_list;
	
}
