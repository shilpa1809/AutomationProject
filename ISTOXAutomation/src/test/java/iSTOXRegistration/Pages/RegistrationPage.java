package iSTOXRegistration.Pages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import iSTOXRegistration.BaseClass.ISTOXTestBase;
import iSTOX_Utility.ScreenShot;

public class RegistrationPage extends ISTOXTestBase {

	// locate WebElement using locators

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	String errorMsg = " ";
	String errorScreenshot = "";

	// Generate Logs
	Logger logs = Logger.getLogger("RegistrationPage.class");

	@FindBy(xpath = "//input[@placeholder='Email']")
	public WebElement iStox_Email;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement iStox_Password;

	@FindBy(xpath = "//input[@placeholder='Repeat Password']")
	public WebElement iStox_ReapeatPassword;

	@FindBy(xpath = "//input[@class='react-datepicker-ignore-onclickoutside']")
	public WebElement iStox_DateTime;

	@FindBy(xpath = "//div[@class='datepicker-calender-container']")

	public WebElement DateTimePicker;

	@FindBy(xpath = "//span[text()='Date Time']")
	public WebElement click_DateTimeLable;

	@FindBy(xpath = "//input[@placeholder='Your Age']")
	public WebElement iStox_Age;

	@FindBy(xpath = "//input[@placeholder='Your investment']")
	public WebElement iStox_Investment;

	@FindBy(xpath = "//select[@class='form-control']")
	public WebElement iStox_CounttryDrpDown;

	@FindBy(xpath = "//div[@class='react-toggle-thumb']")
	public WebElement iStox_ClickToggle;

	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement iStox_SubmitBtn;

	@FindBy(xpath = "//div[@class='confirm-container']")
	public WebElement iStox_PopUp;

	@FindBy(xpath = "//button[text()='Yes']")
	public WebElement iStox_ConfirmYes;

	@FindBy(xpath = "//input[@placeholder='Email']//following::div[4]//span")
	public WebElement eMail_ErrorMsg;

	@FindBy(xpath = "//input[@placeholder='Password']//following::div[4]//span")
	public WebElement password_ErrorMsg;

	@FindBy(xpath = "//input[@placeholder='Repeat Password']//following::div[4]//span")
	public WebElement repeatPassword_ErrorMsg;

	@FindBy(xpath = "//input[@placeholder='Date time']//following::div[4]//span")
	public WebElement dateTime_ErrorMsg;

	@FindBy(xpath = "//input[@placeholder='Your Age']//following::div[4]//span")
	public WebElement yourAge_ErrorMsg;

	@FindBy(xpath = "//input[@placeholder='Your investment']//following::div[4]//span")
	public WebElement yourInvestment_ErrorMsg;

	@FindBy(xpath = "//div[@class='react-toggle-thumb']//following::div[3]//span")
	public WebElement toggleThumb_ErrorMsg;

	// Filled the form
	public void registrationDetailsForms(String Email, String Password, String RepeatPassword, String DateTime,
			String Age, String Investment, String Country) throws InterruptedException, Exception {

		try {
			

			// configure log4j properties file
			PropertyConfigurator.configure(".\\Log4J\\Log4j.properties");

			// Enter Email and check Validations
			if (Email != null && eMailValidate(Email)) {
				Thread.sleep(2000);
				iStox_Email.sendKeys(Email);

			} else if (Email == null || !(eMailValidate(Email))) {

				iStox_Email.sendKeys(Email, Keys.ENTER);
				String errorMsg = eMail_ErrorMsg.getText();
				logs.info("errorMsz...." + errorMsg);
				Thread.sleep(1000);
				iStox_Email.sendKeys(Keys.TAB);
			}

			// Enter Password and check Validation
			if (Password != null && passwordValidate(Password)) {
				iStox_Password.sendKeys(Password);
			} else if (Password == null || !(passwordValidate(Password))) {
				iStox_Password.sendKeys(Password, Keys.ENTER);
				errorMsg = password_ErrorMsg.getText();
				logs.info("errorMsz...." + errorMsg);
				iStox_Password.sendKeys(Keys.TAB);
			}

			// Enter ReapeatPassword and check Validations
			if (RepeatPassword != null && passwordValidate(RepeatPassword)) {
				iStox_ReapeatPassword.sendKeys(RepeatPassword, Keys.TAB);
			} else if (RepeatPassword == null || !(passwordValidate(RepeatPassword))) {
				iStox_ReapeatPassword.sendKeys(RepeatPassword, Keys.ENTER);
				errorMsg = repeatPassword_ErrorMsg.getText();
				logs.info("errorMsz...." + errorMsg);
				iStox_ReapeatPassword.sendKeys(Keys.TAB);

			}

			// Check Date Time and Validations
			if (DateTime != null) {

				iStox_DateTime.sendKeys(DateTime);
				iStox_DateTime.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
				iStox_DateTime.sendKeys(DateTime, Keys.ENTER);

				Thread.sleep(1000);

			} else if (DateTime == null) {

				iStox_DateTime.sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				errorMsg = dateTime_ErrorMsg.getText();
				logs.info("errorMsz...." + errorMsg);
				iStox_DateTime.sendKeys(Keys.TAB);
				Thread.sleep(1000);
			}

			
			if (Age != null) {
				iStox_Age.sendKeys(Age);
			} else if (Age == null) {
				iStox_Age.sendKeys(Age, Keys.ENTER);
				errorMsg = yourAge_ErrorMsg.getText();
				logs.info("errorMsz...." + errorMsg);
				iStox_Age.sendKeys(Keys.TAB);

			}

			if (Investment != null) {
				iStox_Investment.sendKeys(Investment);
			}

			else {
				iStox_Investment.sendKeys(Investment, Keys.ENTER);
				errorMsg = yourInvestment_ErrorMsg.getText();
				logs.info("errorMsz...." + errorMsg);
				iStox_Investment.sendKeys(Keys.TAB);
			}

			Select drop = new Select(iStox_CounttryDrpDown);
			if (Country != null) {
				drop.selectByVisibleText(Country);
			}

			iStox_ClickToggle.click();

			iStox_SubmitBtn.click();

			Thread.sleep(2000);
			// Switch to Alert
			iStox_PopUp.click();
			iStox_ConfirmYes.click();

			logs.info("Data submitted Successfully");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	// Email validation
	public boolean eMailValidate(String email) {
		String regex = "^(.+)@(.+)$";

		Boolean value = email.matches(regex);

		return value;

	}
	// Password Validations

	public boolean passwordValidate(String password) {

		String pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
		Boolean value = password.matches(pattern);

		return value;

	}

	// Date Time Picker
	public void dateTimeD() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('iStox_DateTime').value='09-07-2019'");

	}

}
