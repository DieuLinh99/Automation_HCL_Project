package supplierRegistration;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

/*
 * 1. Launch the application
 * 2. Click the Register button
 * 3. Navigate to View SignUp page
 * 4. Click the Supplier registration type  
 * 5. Enter the valid Username  
 * 6. Enter the valid Password 
 * 7. Enter the valid Re-password 
 * 8. Click the Next button
 * 9. Enter the valid First Name 
 * 10.Enter the valid Last Name 
 * 11.Enter the valid Email 
 * 12.Enter the valid Mobile Number 
 * 13.Enter the valid Address 
 * 14.Enter the valid City 
 * 15.Enter the valid State 
 * 16.Enter the valid Province 
 * 17.Enter the valid Country 
 * 18.Click the Next button
 * 19.Get the Account Number data from excel file
 * 20.Check the registration by last page - Bank Details is valid can click signup button or not
 */

public class BankDetails {
	private WebDriver driver;
	private static String baseUrl = "http://54.237.43.64/sign-up/client";
	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\ForAutoTest\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
		test = report.startTest("SHS Bank - Supplier Registration - Bank Details");

	}

	@Test(dataProvider = "BankDetails")
	public void testBankDetails(String account) {

		// user details
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@formcontrolname='userId']")).sendKeys("Chung");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Chung12");
		driver.findElement(By.xpath("//input[@formcontrolname='confirm_password']")).sendKeys("Chung12");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		// personal details
		driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys("Chung");
		driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).sendKeys("Nguyen");
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("chunnt1112@gmail.com");
		driver.findElement(By.xpath("//input[@formcontrolname='phone']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@formcontrolname='addressLine1']")).sendKeys("VanXuanNam");
		driver.findElement(By.xpath("//input[@formcontrolname='city']")).sendKeys("ThaiBinh");
		driver.findElement(By.xpath("//input[@formcontrolname='state']")).sendKeys("ThaiThuy");
		driver.findElement(By.xpath("//input[@formcontrolname='province']")).sendKeys("ThuyXuan");
		driver.findElement(By.xpath("//input[@formcontrolname='country']")).sendKeys("VietNam");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// bank details
		driver.findElement(By.xpath("//input[@formcontrolname='accountNumber']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='accountNumber']")).sendKeys(account);
		driver.findElement(By.xpath("//span[@class='mat-button-wrapper']//span[1]")).click();

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
			XLSReader reader = new XLSReader("D:\\SampleTest\\BankDataRegistration.xlsx");
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
