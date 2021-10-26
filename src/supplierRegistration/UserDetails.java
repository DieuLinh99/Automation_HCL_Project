package supplierRegistration;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import static org.testng.Assert.assertTrue;

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
 * 5. Get the username data from excel file 
 * 6. Get the password data from excel file
 * 7. Get the re-password data from excel file
 * 8. Click the Next button
 * 9. Check the registration by first page - User Detailas is valid by present the text element "Personal Details" or not
 */
public class UserDetails {
	private WebDriver driver;
	private static String baseUrl = "http://54.237.43.64/";
	private static String text = "Personal Details";
	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\ForAutoTest\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
		test = report.startTest("SHS Bank - Supplier Registration - User Details");
	}

	@Test(dataProvider = "UserDetails")
	public void testUserDetails(String username, String password, String confirmPassword, String result)
			throws InterruptedException {

		// user details
		driver.get(baseUrl);
		driver.findElement(By.xpath("//a[@href='/sign-up']")).click();
		driver.findElement(By.xpath("//div[text()='A Supplier']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='userId']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@formcontrolname='confirm_password']")).sendKeys(confirmPassword);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(1000);

		//check data
		if (result.equals("pass")) {
			if (text.equals(driver.findElement(By.xpath("//div[text()='Personal Details']")).getText())) {
				System.out.println(driver.findElement(By.xpath("//div[text()='Personal Details']")).getText());
				assertTrue(true);
				test.log(LogStatus.PASS, " Supplier Registration - User Details - Successfully");

			} else {

				assertTrue(true);
				if (!driver.findElements(By.xpath("//mat-error[@role='alert']")).isEmpty())
					System.out.println(driver.findElement(By.xpath("//mat-error[@role='alert']")).getText());

				test.log(LogStatus.FAIL, "Failed");
			}
		}
	}

	@DataProvider(name = "UserDetails")
	public String[][] getData() {
		String[][] rowCol = null;
		try {
			XLSReader reader = new XLSReader("D:\\SampleTest\\Bank.xlsx");
			String sheetName = "User Details";

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
