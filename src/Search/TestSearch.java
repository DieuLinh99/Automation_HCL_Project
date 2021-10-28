package Search;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.LoginBefore;
import Pages.Search;

//import com.relevantcodes.extentreports.*;
/*
 * 1. Launch the application
 * 2. Login with valid data
 * 3. Navigate to View Invoices page
 * 4. Enter Client ID and click enter on keyboard 
 * 5. Check the bank account present in recently searched by Client ID.
 * 6. Clear Client ID and enter Invoice and click enter on keyboard 
 * 7. Check the bank account present in recently searched by Invoice.
 * 8. Clear Invoice and enter Supplier ID and click enter on keyboard 
 * 9. Check the bank account present in recently searched by Supplier ID.
 */

public class TestSearch {
	private WebDriver driver;
	LoginBefore login;
	Search search;
	static ExtentTest test;
	static ExtentReports report;
	String baseUrl = "http://54.237.43.64/sign-in";
	String AfterUrl = "http://54.237.43.64/invoice/view-invoices";
	public static boolean isUsernamePresent, isPasswordPresent, isButtonPresent, isInvoiceNumberPresent,
			isClientCodePresent, isSupplierCodePresent;

	// searchbyClientCode
	@Test
	public void searchByClientID() {
		// driver.get(AfterUrl);
		driver.get(baseUrl);
		isUsernamePresent = driver.findElement(By.xpath("//input[@label='username']")).isDisplayed();
		isPasswordPresent = driver.findElement(By.xpath("//input[@label='password']")).isDisplayed();
		isButtonPresent = driver.findElement(By.tagName("button")).isDisplayed();

		if (isUsernamePresent && isPasswordPresent && isButtonPresent) {
			login = new LoginBefore(driver);
			login.LoginSHSBank("banker1", "password");

			search = new Search(driver);
			search.ViewInvoice();
			
			isClientCodePresent = driver.findElement(By.name("clientId")).isDisplayed();
			if (isClientCodePresent) {
				search.SearchByClientCode("CL_00006");
				WebElement textbox1 = driver.findElement(By.name("clientId"));
				textbox1.sendKeys(Keys.ENTER);
				String c1 = driver.findElement(By.xpath("//td[text()=' 11 ']")).getText();
				if (c1.equals("11")) {
					test.log(LogStatus.PASS, "Search By Client Code Successfully");
				} else {
					test.log(LogStatus.FAIL, "Wrong");
				}
			}
		}
	}

	@Test
	public void searchByInvoice() {
		isInvoiceNumberPresent = driver.findElement(By.name("invoiceNumber")).isDisplayed();
		driver.findElement(By.name("clientId")).clear();

		if (isInvoiceNumberPresent) {
			search = new Search(driver);
			search.SearchByInvoiceNumber("02255666");
			WebElement textbox = driver.findElement(By.name("invoiceNumber"));
			textbox.sendKeys(Keys.ENTER);
			String c2 = driver.findElement(By.xpath("//td[text()=' 13 ']")).getText();
			if (c2.equals("13")) {
				test.log(LogStatus.PASS, "Search By Client Code Successfully");
			} else {
				test.log(LogStatus.FAIL, "Wrong");
			}
		}
	}

	// searchBySupplierCode
	@Test
	public void searchBySupplierID() {
		isSupplierCodePresent = driver.findElement(By.name("supplierId")).isDisplayed();
		driver.findElement(By.name("invoiceNumber")).clear();

		if (isSupplierCodePresent) {
			search = new Search(driver);
			search.SearchBySupplierCode("SP_00003");
			WebElement textbox2 = driver.findElement(By.name("supplierId"));
			textbox2.sendKeys(Keys.ENTER);
			String c3 = driver.findElement(By.xpath("//td[text()=' 17 ']")).getText();
			if (c3.equals("17")) {
				test.log(LogStatus.PASS, "Search By Client Code Successfully");
			} else {
				test.log(LogStatus.FAIL, "Wrong");
			}
		}
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\HCL\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
		test = report.startTest("SHS Bank");
	}

	@AfterClass
	public void afterClass() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}

}
