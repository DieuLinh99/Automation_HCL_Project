package supplierRegistrationPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalDetails {
	WebDriver driver;
	By firstname = By.xpath("//input[@formcontrolname='firstName']");
	By lastname = By.xpath("//input[@formcontrolname='lastName']");
	By email = By.xpath("//input[@formcontrolname='email']");
	By mobileNumber = By.xpath("//input[@formcontrolname='phone']");
	By address = By.xpath("//input[@formcontrolname='addressLine1']");
	By city = By.xpath("//input[@formcontrolname='city']");
	By state = By.xpath("//input[@formcontrolname='state']");
	By province = By.xpath("//input[@formcontrolname='province']");
	By country = By.xpath("//input[@formcontrolname='country']");
	By nextBtn = By.xpath("//span[text()='Next']");

	// constructor
	public PersonalDetails(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setFirstname(String firstname) {
		driver.findElement(this.firstname).click();
		driver.findElement(this.firstname).sendKeys(firstname);
	}
	
	public void setLastname(String lastname) {
		driver.findElement(this.lastname).sendKeys(lastname);
	}
	
	public void setEmail(String email) {
		driver.findElement(this.email).sendKeys(email);
	}
	
	public void setMobilenumber(String mobileNumber) {
		driver.findElement(this.mobileNumber).sendKeys(mobileNumber);
	}
	
	public void setAddress(String address) {
		driver.findElement(this.address).sendKeys(address);
	}
	
	public void setCity(String city) {
		driver.findElement(this.city).sendKeys(city);
	}
	
	public void setState(String state) {
		driver.findElement(this.state).sendKeys(state);
	}
	
	public void setProvince(String province) {
		driver.findElement(this.province).sendKeys(province);
	}
	
	public void setCountry(String country) {
		driver.findElement(this.country).sendKeys(country);
	}
	
	public void clickButtons() {
		driver.findElement(this.nextBtn).click();
	}

	public void regPersonalDetails(String firstname, String lastname, String email, String mobileNumber, String address,
			String city, String state, String province, String country) {
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setEmail(email);
		this.setMobilenumber(mobileNumber);
		this.setAddress(address);
		this.setCity(city);
		this.setState(state);
		this.setProvince(province);
		this.setCountry(country);
		this.clickButtons();
	}

}
