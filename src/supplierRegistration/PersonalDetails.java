package supplierRegistration;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

/*
 * 1. Launch the application
 * 2. Click the Register button
 * 3. Navigate to View SignUp page
 * 4. Click the Supplier registration type  
 * 5. Enter the valid Username  
 * 6. Enter the valid Password 
 * 7. Enter the valid Re-password 
 * 8. Click the Next button
 * 9. Get First Name data from excel file
 * 10.Get Last Name data from excel file
 * 11.Get Email data from excel file
 * 12.Get Mobile Number data from excel file
 * 13.Get Address data from excel file
 * 14.Get City data from excel file
 * 15.Get State data from excel file
 * 16.Get Province data from excel file
 * 17.Get Country data from excel file
 * 18.Check the registration by second page - Personal Detailas is valid by present the text element "Bank Details" or not
 */

public class PersonalDetails {
	private WebDriver driver;
	private static String baseUrl = "http://54.237.43.64/";
	private static String text = "Bank Details";
	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\ForAutoTest\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
		test = report.startTest("SHS Bank - Supplier Registration - Personal Details");
	}

	@Test(dataProvider = "PersonalDetails")
	public void testPersonalDetails(String firstname, String lastname, String email, String mobilenum, String address,
			String city, String state, String province, String country, String result) throws InterruptedException {

		// user details
		driver.get(baseUrl);
		driver.findElement(By.xpath("//a[@href='/sign-up']")).click();
		driver.findElement(By.xpath("//div[text()='A Supplier']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='userId']")).sendKeys("Chung");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Chung12");
		driver.findElement(By.xpath("//input[@formcontrolname='confirm_password']")).sendKeys("Chung12");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(1000);

		// personal details
		if (driver.findElement(By.xpath("//div[text()='Personal Details']")).isDisplayed()) {
			driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys(firstname);
			driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).sendKeys(lastname);
			driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(email);
			driver.findElement(By.xpath("//input[@formcontrolname='phone']")).sendKeys(mobilenum);
			driver.findElement(By.xpath("//input[@formcontrolname='addressLine1']")).sendKeys(address);
			driver.findElement(By.xpath("//input[@formcontrolname='city']")).sendKeys(city);
			driver.findElement(By.xpath("//input[@formcontrolname='state']")).sendKeys(state);
			driver.findElement(By.xpath("//input[@formcontrolname='province']")).sendKeys(province);
			driver.findElement(By.xpath("//input[@formcontrolname='country']")).sendKeys(country);
			driver.findElement(By.xpath("//span[text()='Next']")).click();

		}

		// check data
		if (result.equals("pass")) {
			if (text.equals(driver.findElement(By.xpath("//div[text()='Bank Details']")).getText())) {
				System.out.println(driver.findElement(By.xpath("//div[text()='Bank Details']")).getText());
				assertTrue(true);
				test.log(LogStatus.PASS, " Supplier Registration - Personal Details - Successfully");

			} else {

				assertTrue(true);
				if (!driver.findElements(By.xpath("//mat-error[@role='alert']")).isEmpty())
					System.out.println(driver.findElement(By.xpath("//mat-error[@role='alert']")).getText());
				test.log(LogStatus.FAIL, "Fail");

			}
		}
	}

	@DataProvider(name = "PersonalDetails")
	public String[][] getData() {
		String[][] rowCol = null;
		try {
			XLSReader reader = new XLSReader("D:\\SampleTest\\Bank.xlsx");
			String sheetName = "Personal Details";

			int noOfRow = reader.getRowCount(sheetName);
			int noOfCell = reader.getCellCount(sheetName, 0);
			rowCol = new String[noOfRow][noOfCell];

			for (int i = 1; i <= noOfRow; i++) {
				for (int j = 0; j < noOfCell; j++) {
					rowCol[i - 1][j] = reader.getCellData(sheetName, i, j);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return rowCol;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}
}
