package ClientReg;

import ClientRegPOM.DataConfig;
import ClientRegPOM.Registration;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Configuration.browser;
import static org.testng.AssertJUnit.assertTrue;

public class Form1 {
    private WebDriver driver;
    private static String text = "Personal Details";
    private static String baseUrl = "http://54.237.43.64/sign-up/client";
    static ExtentTest test;
    static ExtentReports report;
    Registration reg;


//    @BeforeClass
//    public void before() throws Exception {
//        System.setProperty("webdriver.chrome.driver", "/home/legallolicon/java_lib/chromedriver");
//        driver = new ChromeDriver();
//        driver = new EdgeDriver();
//        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
//        report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
//        test = report.startTest("SHS Bank - CLient Registration - Form1");
//    }
    @Parameters("browser")
    @BeforeClass
    public void before() throws Exception {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/home/legallolicon/java_lib/chromedriver");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.drver", "/home/legallolicon/Downloads/deb file/driver_browser/msedgedriver");
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "/home/legallolicon/Downloads/deb file/driver_browser/geckodriver");
            driver = new FirefoxDriver();
        } else {
            throw new Exception("Browser is not correct");
        }
        report = new ExtentReports(System.getProperty("user.dir") + "/test-output/MenuTestResults.html");
        test = report.startTest("SHS Bank - CLient Registration - Form1");
    }

    @Test(dataProvider = "Form1")
    public void testForm1(String username, String password, String confirmPassword, String result) throws Exception {
        driver.get(baseUrl);
        reg = new Registration(driver);
        reg.fistForm(username, password, confirmPassword);

        if (result.equals("pass")) {
            if (text.equals(driver.findElement(By.xpath("//div[text()='Personal Details']")).getText())) {
                System.out.println(driver.findElement(By.xpath("//div[text()='Personal Details']")).getText());
                assertTrue(true);
                test.log(LogStatus.PASS, "Form1 - Pass");

            } else {

                assertTrue(true);
                if (!driver.findElements(By.xpath("//mat-error[@role='alert']")).isEmpty())
                    System.out.println(driver.findElement(By.xpath("//mat-error[@role='alert']")).getText());

                test.log(LogStatus.FAIL, "Failed");
            }
        }
    }

    @DataProvider(name = "Form1")
    public Object[][] getData() {
        String[][] rowCol = null;
        try {
            DataConfig data = new DataConfig("/home/legallolicon/hcl/selenium/src/regdata.xlsx");
            int sheetIndex = 0;

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
    public void after() throws Exception {
        driver.close();
    }
}
