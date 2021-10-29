package testSupplierRegistration;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

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
import supplierRegistrationPOM.BankDetails;

/*
 * 1. Launch the application
 * 2. Navigate to View SignUp page
 * 3. Click the Supplier registration type  
 * 4. Enter the valid Username  
 * 5. Enter the valid Password 
 * 6. Enter the valid Re-password 
 * 7. Click the Next button
 * 8. Enter the valid First Name 
 * 9.Enter the valid Last Name 
 * 10.Enter the valid Email 
 * 11.Enter the valid Mobile Number 
 * 12.Enter the valid Address 
 * 13.Enter the valid City 
 * 14.Enter the valid State 
 * 15.Enter the valid Province 
 * 16.Enter the valid Country 
 * 17.Click the Next button
 * 18.Get the Account Number data from excel file
 * 19.Check the registration by last page - Bank Details is valid can click signup button or not
 */ 

public class TestBankDetails {
	private WebDriver driver;
	Home home;
	UserDetails userdetails;
	PersonalDetails perdetails;
	BankDetails bankdetails;
	static ExtentTest test;
	static ExtentReports report;
	private static String baseUrl = "http://54.237.43.64/sign-up";
	
	@BeforeClass
	public void beforeClass() throws Exception {
		System.setProperty("webdriver.edge.driver", "D:\\ForAutoTest\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
		test = report.startTest("SHS Bank - Supplier Registration - Bank Details");
	}

	@Test(dataProvider = "BankDetails")
	public void testBankDetails(String account) throws InterruptedException {
		driver.get(baseUrl);

		home = new Home(driver);
		home.supplierBtn();

		userdetails = new UserDetails(driver);
		userdetails.regUserDetails("Chung", "Chung12", "Chung12");
		Thread.sleep(1000);

		perdetails = new PersonalDetails(driver);
		perdetails.regPersonalDetails("Chung", "Nguyen", "chunnt1112@gmail.com", "123456", "VanXuanNam", "ThaiBinh", "ThaiThuy", "ThuyXuan", "VietNam");
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		
		bankdetails = new BankDetails(driver);
		bankdetails.regBankDetails(account);
		
		
		if (account.matches("[0-9]+")) {
			if (Integer.parseInt(account) > 10010000 || Integer.parseInt(account) < 10000001) {
				System.out.println(driver.findElement(By.xpath("//mat-label[text()='Validation Failed']")).getText());
				test.log(LogStatus.FAIL, "Validation Failed");

			} else if (account.equals("10000001")) {
				System.out.println(driver
						.findElement(By.xpath("//mat-icon[@role='img']/following-sibling::mat-label[1]")).getText());
				test.log(LogStatus.FAIL, "You already have an account, please login");
			} else {
				if (driver.findElement(By.xpath("//mat-icon[@role='img']/following-sibling::mat-label[1]"))
						.isDisplayed()) {
					assertTrue(false);
					test.log(LogStatus.PASS, " Supplier Registration - Bank Details - Successfully");
				}

			}

		} else if (!driver.findElements(By.xpath("//mat-error[@role='alert']")).isEmpty()) {
			System.out.println(driver.findElement(By.xpath("//mat-error[@role='alert']")).getText());
			test.log(LogStatus.FAIL, "Required");

		} else if (!driver.findElements(By.xpath("//mat-icon[@role='img']/following-sibling::mat-label[1]"))
				.isEmpty()) {
			System.out.println(
					driver.findElement(By.xpath("//mat-icon[@role='img']/following-sibling::mat-label[1]")).getText());
			test.log(LogStatus.FAIL, "Invalid Account Number");
		}

	}

	@DataProvider(name = "BankDetails")
	public String[][] getData() {
		String[][] rowCol = null;
		try {
			XLSReader reader = new XLSReader("D:\\SampleTest\\Bank.xlsx");
			String sheetName = "Bank Details";

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
