package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginBefore {
	private WebDriver driver;
	By username = By.xpath("//input[@label='username']");
	By password = By.xpath("//input[@label='password']");
	By singinButton = By.tagName("button");
	
	public LoginBefore(WebDriver driver) {
		this.driver= driver;
	}
	public void SinginButton() {
		driver.findElement(this.singinButton).click();
	}
	public void setUsername(String username) {
		driver.findElement(this.username).sendKeys(username);
	}
	public void setPassword(String password) {
		driver.findElement(this.password).sendKeys(password);
	}

	public void LoginSHSBank(String username, String password) {
		//this.SinginButton1();
		this.setUsername(username);
		this.setPassword(password);
		this.SinginButton();
	}
}
