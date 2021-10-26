package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestLogin {
	static ExtentTest test;
	static ExtentReports report;
	private WebDriver driver;
	public static String baseUrl = "http://54.237.43.64/";
	public static String mainUrl = "http://54.237.43.64/dashboard";
	/*
	 * Pre-condition: network access and Browser with latest updates
	 * 
	 * Login: 
	 * 	1. Navigate to Login page
	 * 	2. Check for the Fields and Enter required fields if exists
	 *  3. Click on Sign In button
	 *	4. Redirect to Dashboard
	 * 
	 */
	
	@BeforeClass
	public static void startTest() {
		report = new ExtentReports(System.getProperty("user.dir")+"/test-output/MenuTestResults.html");
		test = report.startTest("TestLogin");
	}
	
	@Test(dataProvider = "testLogin")
	public void testLogin(String username, String password, String rs) {
		System.setProperty("webdriver.chrome.driver", "D:\\AutoTest\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		
		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.xpath("//input[@label='username']")).click();
		driver.findElement(By.xpath("//input[@label='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@label='password']")).click();
		driver.findElement(By.xpath("//input[@label='password']")).sendKeys(password);
		driver.findElement(By.tagName("button")).click();
		
		if (rs.equals("pass")) {
			if (mainUrl.equals(driver.getCurrentUrl())) {
				driver.findElement(By.xpath("(//mat-icon[text()='logout'])[2]")).click();
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Successfully");
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Unsuccessfully");
			}
		} else {
			if (mainUrl.equals(driver.getCurrentUrl())) {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Successfully");
			} else {
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Unuccessfully");
			}
		}
	}
	
	@DataProvider(name = "testLogin")
	public Object[][] getData() {
		String[][] tabArray = null;
		try {
        	XLSReader reader = new XLSReader("C:\\Users\\Admin\\Desktop\\RV\\HCL\\Final Project - Automation Test\\SHS Bank.xlsx");
        	String sname = "Login";

			int row = reader.getRowCount(sname);
			int cell = reader.getCellCount(sname, 0);

			tabArray = new String[row][cell];
			int ci = 0;
			for(int i = 1; i <= row; i++, ci++) {
				int cj=0;
				for(int j = 0; j < cell; j++, cj++) {
					tabArray[ci][cj]=reader.getCellData(sname,i,j);
				}
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
		return tabArray;
	}
	
	@AfterClass
	public static void endTest() {
		report.endTest(test);
		report.flush();
	}
}
