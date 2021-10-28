package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Search {
	private WebDriver driver;
	By viewInvoice = By.xpath("//div[text()=' View Invoices ']");
	By invoiceNumber = By.name("invoiceNumber");
	By clientCode = By.name("clientId");
	By supplierCode = By.name("supplierId");
	public Search(WebDriver driver) {
		this.driver= driver;
	}
	public void ViewInvoice() {
		driver.findElement(this.viewInvoice).click();
	}
	public void setInvoiceNumber(String invoiceNumber) {
		driver.findElement(this.invoiceNumber).sendKeys(invoiceNumber);
	}
	public void setClientCode(String clientCode) {
		driver.findElement(this.clientCode).sendKeys(clientCode);
	}
	public void setSupplierCode(String supplierCode) {
		driver.findElement(this.supplierCode).sendKeys(supplierCode);
	}

	public void SearchByInvoiceNumber(String invoiceNumber) {
		//this.ViewInvoice();
		this.setInvoiceNumber(invoiceNumber);
	}
	public void SearchByClientCode(String clientCode) {
		//this.ViewInvoice();
		this.setClientCode(clientCode);
	}
	public void SearchBySupplierCode(String supplierCode) {
		//this.ViewInvoice();
		this.setSupplierCode(supplierCode);
	}
}

