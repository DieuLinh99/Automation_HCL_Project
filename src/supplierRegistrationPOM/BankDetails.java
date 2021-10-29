package supplierRegistrationPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BankDetails {
	WebDriver driver;
	By accountNum = By.xpath("//input[@formcontrolname='accountNumber']");
	By verifyBtn = By.xpath("//span[@class='mat-button-wrapper']//span[1]");

	public BankDetails(WebDriver driver) {
		this.driver = driver;
	}

	public void setAccountnum(String accountNum) {
		driver.findElement(this.accountNum).sendKeys(accountNum);
	}

	public void clickButtons() {
		driver.findElement(this.verifyBtn).click();
	}

	public void regBankDetails(String accountNum) {
		this.setAccountnum(accountNum);
		this.clickButtons();
	}

}
