package iSTOXRegistrationForm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import iSTOXRegistration.BaseClass.ISTOXTestBase;
import iSTOXRegistration.Pages.RegistrationPage;
import iSTOX_Utility.ReadDataFromExcel;
import iSTOX_Utility.ScreenShot;

public class ISTOXTestCases extends ISTOXTestBase {

	@BeforeSuite
	public void openRep()
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/iStoxRegistration_Report" + timeStamp + ".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
	}
	
	@BeforeMethod
	public void setup() throws Exception {
     //Generate Extent report
	 logger=extent.createTest("RegistrationDetails");
	 
	 // initialize drivers and Launch URL
	    initialization();
	
	}

	//All the fields/coloumn names used in Excel sheet should be same in below function.We are using dataProvider to read the data from excel.
	@Test(dataProvider = "userDetails")
	public void iStoxRegistration(String Email, String Password, String RepeatPassword, String DateTime, String Age,
			String Investment, String Country) throws Exception {

		RegistrationPage registrationDetails = new RegistrationPage(driver);
		
		registrationDetails.registrationDetailsForms(Email,Password,RepeatPassword,DateTime,Age,Investment,Country);

		
	}
    //
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			String fullimage=ScreenShot.getScreenshot(driver, "RegistrationDetails_Screenshot");
			logger.log(Status.PASS, result.getName()+" details Successfully filled", MediaEntityBuilder.createScreenCaptureFromPath(fullimage).build());
			
		}
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String temp=ScreenShot.getScreenshot(driver, "RegistrationDetails_Screenshot");
			
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		
	//	extent.flush();
		driver.close();
		driver.quit();
	}

	@AfterSuite
	public void closeRep()
	{
		extent.flush();
		
	}
	
	//Using DataProvider to read the data from Excel and it is used to read multiusers data
	@DataProvider
	public Object[][] userDetails() throws Exception
	{
		Object[][] testObjArray = ReadDataFromExcel.readdata("RegistrationDetails");
		return (testObjArray);

	}

}
