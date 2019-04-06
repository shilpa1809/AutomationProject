package iSTOX_Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import iSTOXRegistration.BaseClass.ISTOXTestBase;


public class ScreenShot extends ISTOXTestBase {
	
 
	/*
	 * static ExtentReports extent; static ExtentTest logger;
	 */
	// Capture ScreenShot

	/*
	 * public static String capture(WebDriver driver, String screenShotName) throws
	 * Exception { Screenshot screenshot = new
	 * AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
	 * .takeScreenshot(driver); String dest = System.getProperty("user.dir") +
	 * "/ScreenShots/" + screenShotName + ".png";
	 * ImageIO.write(screenshot.getImage(), "PNG", new File(dest)); return dest; }
	 */
	
	public static String getScreenshot(WebDriver driver,String screenShotName)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		String path=System.getProperty("user.dir")+"/ScreenShots/"+screenShotName+System.currentTimeMillis()+".png";
		
		File destination=new File(path);
		
		try 
		{
			FileUtils.copyFile(src, destination);
		} catch (IOException e) 
		{
			System.out.println("Capture Failed "+e.getMessage());
		}
		
		return path;
	}
	
	
	// Extent Report
	/*
	 * public static void extentReport(String errorMsg,String screenshot) throws
	 * IOException { String timeStamp = new
	 * SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	 * ExtentHtmlReporter reporter = new
	 * ExtentHtmlReporter("./Reports/learn_automation" + timeStamp + ".html");
	 * 
	 * extent = new ExtentReports();
	 * 
	 * extent.attachReporter(reporter);
	 * 
	 * logger=extent.createTest("LoginTest");
	 * 
	 * logger.log(Status.FAIL, errorMsg,
	 * MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
	 * 
	 * 
	 * }
	 */
	
	
	
	// Read Data From 
	
	
		

		public static void loadProperties() throws IOException {
			 Properties prop= new Properties();
			InputStream input = null;

			try {

				input = new FileInputStream(System.getProperty("user.dir") + "\\Config\\configuration.properties");

				// load a properties file
				prop.load(input);

				// get the property value and print it out
				System.out.println(prop.getProperty("browserName"));

				System.out.println(prop.getProperty("browserName"));
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
}
