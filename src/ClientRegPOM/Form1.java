import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Form1 {
    private WebDriver driver;
    By regButton = By.linkText("Register");
    By clientButton = By.xpath("//div[text()='A Client']");
    By userText = By.xpath("//input[@formcontrolname='userId']");
    By passwordText = By.xpath("//input[@formcontrolname='password']");
    By confirmPasswordText = By.xpath("//input[@formcontrolname='confirm_password']");

    public Form1(WebDriver driver) {
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
    public void fistForm(String userText, String passwordText, String confirmPasswordText){
        this.setRegButton();
        this.setClientButton();
        this.setUserText(userText);
        this.setPasswordText(passwordText);
        this.setConfirmPasswordText(confirmPasswordText);
    }
}
