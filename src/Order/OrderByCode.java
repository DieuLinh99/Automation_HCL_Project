package Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.LoginBefore;


public class OrderByCode {
	private WebDriver driver;
	LoginBefore login;
	String Url = "http://54.237.43.64/invoice/view-invoices";
	/*
	 * 
	 */
  @Test
  public void OrderByCode() {
	  driver.get(Url);
	  login = new LoginBefore(driver);
	  login.LoginSHSBank("banker1", "password");
	  driver.findElement(By.xpath("//div[text()=' View Invoices ']")).click();
	  //driver.findElement(By.xpath("//div[contains(@class,'mat-sort-header-arrow ng-trigger')]//div")).click();
	  //driver.findElement(By.xpath("//td[contains(@class,'mat-cell cdk-cell')]")).getText();
	  
	  WebElement element = driver.findElement(By.xpath("//div[text()='#']"));
		Select se = new Select(element);
		List<String> originalList = new ArrayList();
		for (WebElement e : se.getOptions()) {
			originalList.add(e.getText());
		}
		//----logic block starts
		List<String> tempList= originalList;
		Collections.sort(tempList);
		Assert.assertEquals(tempList, originalList);
		//----logic ends starts
}
  
  @BeforeClass
  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\User\\Downloads\\HCL\\Technical\\XPath\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
