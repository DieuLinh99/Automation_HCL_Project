import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Form3 {
    private WebDriver driver;
    By accountText = By.xpath("//input[@formcontrolname='accountNumber']");
    By verificationButton = By.xpath("//span[text()='Get Verification Code']");

    public Form3(WebDriver driver) {
        this.driver = driver;
    }
    public void setAccountText(String accountText) {
        driver.findElement(this.accountText).sendKeys(accountText);
    }

    public void setVerificationButton(){
        driver.findElement(this.verificationButton).click();
    }
    public void thirdForm(String accountText){
        this.setAccountText(accountText);
        this.setVerificationButton();
    }
}
