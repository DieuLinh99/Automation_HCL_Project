package ClientReg;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import static com.codeborne.selenide.Configuration.browser;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ClientRegPOM.DataConfig;
import ClientRegPOM.Registration;
public class Form3 {
    private WebDriver driver;
    private String baseUrl = "http://54.237.43.64/sign-up/client";
    static ExtentTest test;
    static ExtentReports report;
    Registration reg;

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
        test = report.startTest("SHS Bank - Client Registration - Form3");
    }
    @Test(dataProvider = "Form3")
    public void Form3(String accNum, String result) {
        driver.get(baseUrl);
        reg = new Registration(driver);
        reg.fistForm("clientTest", "clientPass01", "clientPass01");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        reg.secondForm("John", "Adam", "email@email.com", "911202546", "sample address", "hanoi", "sample state", "hanoi", "vietnam");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

        reg.thirdForm(accNum);
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
    @DataProvider(name = "Form3")
    public Object[][] getData() {
        String[][] rowCol = null;
        try {
            DataConfig data = new DataConfig("/home/legallolicon/jar_lib/regdata.xlsx");
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
