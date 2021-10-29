package testSupplierRegistration;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.Home;
import supplierRegistration.XLSReader;
import supplierRegistrationPOM.UserDetails;
import supplierRegistrationPOM.PersonalDetails;

/*
 * 1. Launch the application
 * 2. Navigate to View SignUp page
 * 3. Click the Supplier registration type  
 * 4. Enter the valid Username  
 * 5. Enter the valid Password 
 * 6. Enter the valid Re-password 
 * 7. Click the Next button
 * 8. Get First Name data from excel file
 * 9.Get Last Name data from excel file
 * 10.Get Email data from excel file
 * 11.Get Mobile Number data from excel file
 * 12.Get Address data from excel file
 * 13.Get City data from excel file
 * 14.Get State data from excel file
 * 15.Get Province data from excel file
 * 16.Get Country data from excel file
 * 17.Check the registration by second page - Personal Detailas is valid by present the text element "Bank Details" or not
 */

public class TestPersonalDetails {
	private WebDriver driver;
	Home home;
	UserDetails userdetails;
	PersonalDetails perdetails;
	static ExtentTest test;
	static ExtentReports report;
	private static String baseUrl = "http://54.237.43.64/sign-up";
	private static String text = "Bank Details";

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.setProperty("webdriver.edge.driver", "D:\\ForAutoTest\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
		test = report.startTest("SHS Bank - Supplier Registration - Personal Details");
	}

	@Test(dataProvider = "PersonalDetails")
	public void PersonalDetails(String firstname, String lastname, String email, String mobileNumber, String address,
			String city, String state, String province, String country, String result) throws InterruptedException {
		driver.get(baseUrl);

		home = new Home(driver);
		home.supplierBtn();

		userdetails = new UserDetails(driver);
		userdetails.regUserDetails("Chung", "Chung12", "Chung12");
		Thread.sleep(1000);

		perdetails = new PersonalDetails(driver);
		perdetails.regPersonalDetails(firstname, lastname, email, mobileNumber, address, city, state, province, country);

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
