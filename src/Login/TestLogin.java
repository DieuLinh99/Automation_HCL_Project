package Login;

import java.util.concurrent.TimeUnit;
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
import Pages.LoginBefore;
import Pages.Home;


public class TestLogin {
	LoginBefore login;
	Home home;
	private WebDriver driver;
	private static String baseUrl = "http://54.237.43.64/";
	private static String mainUrl = "http://54.237.43.64/dashboard";
	//public static String username, password;
	public static boolean isUsernamePresent, isPasswordPresent;
	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\AutoTest\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		report = new ExtentReports(System.getProperty("user.dir")+"/test-output/MenuTestResults.html");
		test = report.startTest("TestLogin");
	}

	@Test(dataProvider = "testLogin")
	public void testLogin(String username, String password, String rs) {
		driver.get(baseUrl);

		home = new Home(driver);
		home.signInBtn();

		isUsernamePresent = driver.findElement(By.xpath("//input[@label='username']")).isDisplayed();
		isPasswordPresent = driver.findElement(By.xpath("//input[@label='password']")).isDisplayed();

		if(isUsernamePresent && isPasswordPresent) {
			driver.findElement(By.xpath("//input[@label='username']")).click();
			driver.findElement(By.xpath("//input[@label='username']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@label='password']")).click();
			driver.findElement(By.xpath("//input[@label='password']")).sendKeys(password);
			driver.findElement(By.tagName("button")).click();
			driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

			if (rs.equals("pass")) {
				if (driver.getCurrentUrl().contains(mainUrl)) {
					driver.findElement(By.xpath("(//mat-icon[text()='logout'])[2]")).click();
					Assert.assertTrue(true);
					test.log(LogStatus.PASS, "Successfully");
				} else {
					//driver.findElement(By.xpath("(//mat-icon[text()='logout'])[2]")).click();
					Assert.assertTrue(false);
					test.log(LogStatus.FAIL, "Unsuccessfully");
				}
			} else {
				if (mainUrl.equals(driver.getCurrentUrl())) {
					Assert.assertTrue(false);
					test.log(LogStatus.FAIL, "Unsuccessfully");
				} else {
					Assert.assertTrue(true);
					test.log(LogStatus.PASS, "Successfully");
				}
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
	public void afterClass() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}
}
