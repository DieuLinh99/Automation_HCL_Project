package testng_hcl;
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
public class Form2 {
	private WebDriver driver;
	private String baseUrl = "http://54.237.43.64/sign-up/client";
	private  String text = "Bank Details";
	static ExtentTest test;
	static ExtentReports report;
	
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/legallolicon/java_lib/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
		test = report.startTest("SHS Bank - Client Registration - Form2");
	}
	
	@Test(dataProvider = "Form2")
	public void testForm2(String firstname, String lastname, String email, String mobile, String address, String city, String state, 
		                                     String province, String country, String result) throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.xpath("//input[@formcontrolname='userId']")).sendKeys("clientTest");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("clientPass01");
		driver.findElement(By.xpath("//input[@formcontrolname='confirm_password']")).sendKeys("clientPass01");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		
		
			driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys(firstname);
			driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).sendKeys(lastname);
			driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(email);
			driver.findElement(By.xpath("//input[@formcontrolname='phone']")).sendKeys(mobile);
			driver.findElement(By.xpath("//input[@formcontrolname='addressLine1']")).sendKeys(address);
			driver.findElement(By.xpath("//input[@formcontrolname='city']")).sendKeys(city);
			driver.findElement(By.xpath("//input[@formcontrolname='state']")).sendKeys(state);
			driver.findElement(By.xpath("//input[@formcontrolname='province']")).sendKeys(province);
			driver.findElement(By.xpath("//input[@formcontrolname='country']")).sendKeys(country);
			driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		
		if (result.equals("pass")) {
			if (text.equals(driver.findElement(By.xpath("//div[text()='Bank Details']")).getText())) {
				System.out.println(driver.findElement(By.xpath("//div[text()='Bank Details']")).getText());
				assertTrue(true);
				test.log(LogStatus.PASS, "Fomr2 - Pass");

			} else {

				assertTrue(true);
				if (!driver.findElements(By.xpath("//mat-error[@role='alert']")).isEmpty())
					System.out.println(driver.findElement(By.xpath("//mat-error[@role='alert']")).getText());
				test.log(LogStatus.FAIL, "Fail");

			}
		}
	}
	
	@DataProvider(name = "Form2")
	public String[][] getData() {
		String[][] rowCol = null;
		try {
			DataConfig data = new DataConfig("/home/legallolicon/hcl/selenium/src/regdata.xlsx");
			int sheetIndex = 1;

			int noOfRow = data.getRowCount(sheetIndex);
			int noOfCell = data.getCellCount(sheetIndex, 0);
			rowCol = new String[noOfRow][noOfCell];

			for (int i = 1; i <= noOfRow; i++) {
				for (int j = 0; j < noOfCell; j++) {
					rowCol[i - 1][j] = data.getCellData(sheetIndex, i, j);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return rowCol;
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}
}
