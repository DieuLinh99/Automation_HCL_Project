package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
	WebDriver driver;
	By login = By.linkText("Sign In");
	By register = By.linkText("Register");
	
	public Home(WebDriver driver) {
		this.driver = driver;
	}
	
	public void signInBtn() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(login));
		driver.findElement(login).click();
	}
	
	public void registerBtn() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(login));
		driver.findElement(register).click();
	}
}