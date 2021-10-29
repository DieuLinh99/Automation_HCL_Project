package supplierRegistrationPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserDetails {
	WebDriver driver;
	By username = By.xpath("//input[@formcontrolname='userId']");
	By password = By.xpath("//input[@formcontrolname='password']");
	By re_password = By.xpath("//input[@formcontrolname='confirm_password']");
	By nextBtn = By.xpath("//span[text()='Next']");
	// constructor
	public UserDetails(WebDriver driver) {
		this.driver = driver;
	}

	public void setUsername(String username) {
		driver.findElement(this.username).click();
		driver.findElement(this.username).sendKeys(username);
	}

	public void setPassword(String password) {
		driver.findElement(this.password).sendKeys(password);
	}

	public void setRepassword(String re_password) {
		driver.findElement(this.re_password).sendKeys(re_password);
	}

	public void clickButtons() {
		driver.findElement(this.nextBtn).click();
	}

	public void regUserDetails(String username, String password, String re_password) {
		this.setUsername(username);
		this.setPassword(password);
		this.setRepassword(re_password);
		this.clickButtons();
	}

}
