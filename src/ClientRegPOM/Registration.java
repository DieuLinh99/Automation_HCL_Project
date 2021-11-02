package ClientRegPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Registration {
    private static WebDriver driver;
    By regButton = By.linkText("Register");
    By clientButton = By.xpath("//div[text()='A Client']");
    By userText = By.xpath("//input[@formcontrolname='userId']");
    By passwordText = By.xpath("//input[@formcontrolname='password']");
    By confirmPasswordText = By.xpath("//input[@formcontrolname='confirm_password']");

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

    By accountText = By.xpath("//input[@formcontrolname='accountNumber']");
    By verificationButton = By.xpath("//span[text()='Get Verification Code']");

    public Registration(WebDriver driver) {
        this.driver = driver;
    }


    public void setRegButton() {
        driver.findElement(this.regButton).click();
    }

    public void setClientButton() {
        driver.findElement(this.clientButton).click();
    }

    public void setUserText(String userText) {
        driver.findElement(this.userText).sendKeys(userText);
    }

    public void setPasswordText(String passwordText) {
        driver.findElement(this.passwordText).sendKeys(passwordText);
    }

    public void setConfirmPasswordText(String confirmPasswordText) {
        driver.findElement(this.confirmPasswordText).sendKeys(confirmPasswordText + Keys.ENTER);
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

    public void setAccountText(String accountText) {
        driver.findElement(this.accountText).sendKeys(accountText);
    }

    public void setVerificationButton(){
        driver.findElement(this.verificationButton).click();
    }

    public void fistForm(String userText, String passwordText, String confirmPasswordText){
        this.setRegButton();
        this.setClientButton();
        this.setUserText(userText);
        this.setPasswordText(passwordText);
        this.setConfirmPasswordText(confirmPasswordText);
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
    public void thirdForm(String accountText){
        this.setAccountText(accountText);
        this.setVerificationButton();
    }
}
