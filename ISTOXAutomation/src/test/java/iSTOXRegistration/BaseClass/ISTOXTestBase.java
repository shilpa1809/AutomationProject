package iSTOXRegistration.BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ISTOXTestBase {

	public WebDriver driver;

	public ExtentReports extent;
	public ExtentTest logger;
	String browser = "";
	String URL = "";

	// initialize drivers
	public void initialization() throws Exception {

		try {
			Properties prop = new Properties();
			InputStream input = null;
			File driverPath = null;
			input = new FileInputStream(System.getProperty("user.dir") + "\\Config\\configuration.properties");
			// load a properties file
			prop.load(input);

			browser = prop.getProperty("browserName");
			URL = prop.getProperty("url");

			if (browser.equalsIgnoreCase("ie")) {

				System.setProperty("webdriver.ie.driver", ".\\Drivers\\IEDriverServer.exe");

				driver = new InternetExplorerDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			} else if (browser.equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");

				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			}

			// driver.get("http://www.istox-qa-test.com.s3-website-ap-southeast-1.amazonaws.com/");
			driver.get(URL);
			driver.manage().window().maximize();

			// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
}
