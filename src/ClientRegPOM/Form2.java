import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Form2 {
    private WebDriver driver;
    By firstNameText = By.xpath("//input[@formcontrolname='firstName']");
    By lastNameText = By.xpath("//input[@formcontrolname='lastName']");
    By emailText = By.xpath("//input[@formcontrolname='email']");
    By phoneText = By.xpath("//input[@formcontrolname='phone']");
    By addressText = By.xpath("//input[@formcontrolname='addressLine1']");
    By cityText = By.xpath("//input[@formcontrolname='city']");
    By stateText = By.xpath("//input[@formcontrolname='state']");
    By provinceText = By.xpath("//input[@formcontrolname='province']");
    By countryText = By.xpath("//input[@formcontrolname='country']");
    By nextButton = By.xpath("//span[text()='Next']");

    public Form2(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstNameText(String firstNameText) {
        driver.findElement(this.firstNameText).sendKeys(firstNameText);
    }

    public void setLastNameText(String lastNameText) {
        driver.findElement(this.lastNameText).sendKeys(lastNameText);
    }

    public void setEmailText(String emailText) {
        driver.findElement(this.emailText).sendKeys(emailText);
    }

    public void setPhoneText(String phoneText) {
        driver.findElement(this.phoneText).sendKeys(phoneText);
    }

    public void setAddressText(String addressText) {
        driver.findElement(this.addressText).sendKeys(addressText);
    }

    public void setCityText(String cityText) {
        driver.findElement(this.cityText).sendKeys(cityText);
    }

    public void setStateText(String stateText) {
        driver.findElement(this.stateText).sendKeys(stateText);
    }

    public void setProvinceText(String provinceText) {
        driver.findElement(this.provinceText).sendKeys(provinceText);
    }

    public void setCountryText(String countryText) {
        driver.findElement(this.countryText).sendKeys(countryText);
    }

    public void setNextButton() {
        driver.findElement(this.nextButton).click();
    }
    public void secondForm(String firstNameText, String lastNameText, String emailText, String phoneText, String addressText, String cityText, String stateText, String provinceText, String countryText){
        this.setFirstNameText(firstNameText);
        this.setLastNameText(lastNameText);
        this.setEmailText(emailText);
        this.setPhoneText(phoneText);
        this.setAddressText(addressText);
        this.setCityText(cityText);
        this.setStateText(stateText);
        this.setProvinceText(provinceText);
        this.setCountryText(countryText);
        this.setNextButton();
    }
}
