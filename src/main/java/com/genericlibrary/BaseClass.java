package com.genericlibrary;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	protected WebDriver driver = null;
	private String browser = null;
	public static ExtentReports extentReport = null;
	public ExtentTest startTest= null;
	private String TCID = null;
	private String RunID = null;
	private String Functionality = null;
	
	@BeforeSuite
	public void intializeSuite() throws Exception{
		extentReport = new ExtentReports(System.getProperty("user.dir")+ReadPropertyFile.getPropertyFileData("REPORT_PATH"));
	}
	
	@BeforeMethod
	@Parameters("browsertype")
	public void launchBrowser(@Optional("chrome")String browser,Object[] obj) throws Exception{
		Map<String, String> mapObject = getMapObject(obj);
		TCID = mapObject.get("TC_ID");
		RunID = mapObject.get("Run");
		Functionality = mapObject.get("Functionality");
		startTest = extentReport.startTest(TCID+RunID+Functionality);
		
		startTest.log(LogStatus.INFO, "Launching the browser","Success");
		//browser = ReadPropertyFile.getPropertyFileData("DEFAULTBROWSER");
		if(browser.equalsIgnoreCase("firefox")){
			driver  = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/src/main/resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ReadPropertyFile.getPropertyFileData("IMPLICITWAIT")), TimeUnit.SECONDS);
		driver.get(ReadPropertyFile.getPropertyFileData("URL"));
	}
	@AfterMethod
	public void tearDown(ITestResult result){
		if(result.getStatus()==ITestResult.SUCCESS){
			startTest.log(LogStatus.PASS, "Test case has passed", "successfull");
		}else if(result.getStatus()==ITestResult.FAILURE){
			String screenshot = getScreenshot(TCID+RunID+Functionality);
			startTest.log(LogStatus.FAIL, "Test case has failed", startTest.addScreenCapture(screenshot));
		}
		driver.close();
		
		extentReport.endTest(startTest);
	}
	
	@AfterSuite
	public void endSuite(){
		extentReport.flush();
	}
	
	public static void forceFailTestCases(String message) throws Exception{
		throw new Exception(message);
	}
	
	public String getScreenshot(String testcasename){
		
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy_hh_mm_ss");
		String time = format.format(date);
		String path = System.getProperty("user.dir")+"/test-output/screenshot/"+testcasename+"_"+time+".png";
		File file = new File(path);
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotAs, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	public Map<String, String> getMapObject(Object[] obj){
		return(Map<String ,String>)obj[0];		
	}
}
