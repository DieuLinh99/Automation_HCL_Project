package testng_hcl;

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
public class Form3 {
	private static WebDriver driver;
	private static String baseUrl = "http://54.237.43.64/sign-up/client";
	static ExtentTest test;
	static ExtentReports report;
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/legallolicon/java_lib/chromedriver");
		driver = new ChromeDriver();
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
		test = report.startTest("SHS Bank - Client Registration - Form3");
	}
	
	@Test(dataProvider = "Form3")
	public static void testForm3(String accNum) {
		driver.get(baseUrl);
		driver.findElement(By.xpath("//input[@formcontrolname='userId']")).sendKeys("clientTest");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("clientPass01");
		driver.findElement(By.xpath("//input[@formcontrolname='confirm_password']")).sendKeys("clientPass01");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		
		driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys("John");
		driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).sendKeys("Adam");
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("email@testemail.com");
		driver.findElement(By.xpath("//input[@formcontrolname='phone']")).sendKeys("911202546");
		driver.findElement(By.xpath("//input[@formcontrolname='addressLine1']")).sendKeys("sample address");
		driver.findElement(By.xpath("//input[@formcontrolname='city']")).sendKeys("hanoi");
		driver.findElement(By.xpath("//input[@formcontrolname='state']")).sendKeys("sample state");
		driver.findElement(By.xpath("//input[@formcontrolname='province']")).sendKeys("hanoi");
		driver.findElement(By.xpath("//input[@formcontrolname='country']")).sendKeys("Viet Nam");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
		
		driver.findElement(By.xpath("//input[@formcontrolname='accountNumber']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='accountNumber']")).sendKeys(accNum);
		driver.findElement(By.xpath("//span[@class='mat-button-wrapper']//span[1]")).click();
		
		if (accNum.matches("[0-9]+")) {
			if (Integer.parseInt(accNum) > 10010000 || Integer.parseInt(accNum) < 10000001) {
				System.out.println(driver.findElement(By.xpath("//mat-label[text()='Validation Failed']")).getText());
				test.log(LogStatus.FAIL, "Validation Failed");

			} else if (accNum.equals("10000001")) {
				System.out.println(driver
						.findElement(By.xpath("//mat-icon[@role='img']/following-sibling::mat-label[1]")).getText());
				test.log(LogStatus.FAIL, "You already have an account, please login");
			} else {
				if (driver.findElement(By.xpath("//mat-icon[@role='img']/following-sibling::mat-label[1]"))
						.isDisplayed()) {
					assertTrue(false);
					test.log(LogStatus.PASS, "Fomr3 - Pass");
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
	
	
	
	@DataProvider(name = "Form3")
	public String[][] getData() {
		String[][] rowCol = null;
		try {
			DataConfig data = new DataConfig("/home/legallolicon/hcl/selenium/src/regdata.xlsx");
			int sheetIndex = 2;

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
