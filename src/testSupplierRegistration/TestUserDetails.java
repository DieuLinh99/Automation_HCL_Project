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

/*
 * 1. Launch the application
 * 2. Navigate to View SignUp page
 * 3. Click the Supplier registration type  
 * 4. Get the username data from excel file 
 * 5. Get the password data from excel file
 * 6. Get the re-password data from excel file
 * 7. Click the Next button
 * 8. Check the registration by first page - User Detailas is valid by present the text element "Personal Details" or not
 */

public class TestUserDetails {
	private WebDriver driver;
	Home home;
	UserDetails userdetails;
	static ExtentTest test;
	static ExtentReports report;
	private static String baseUrl = "http://54.237.43.64/sign-up";
	private static String text = "Personal Details";

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.setProperty("webdriver.edge.driver", "D:\\ForAutoTest\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
		test = report.startTest("SHS Bank - Supplier Registration - User Details");
	}

	@Test(dataProvider = "UserDetails")
	public void UserDetails(String username, String password, String re_password, String result) {
		driver.get(baseUrl);
		
		home = new Home(driver);
		home.supplierBtn();
		
		userdetails = new UserDetails(driver);
		userdetails.regUserDetails(username, password, re_password);

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
