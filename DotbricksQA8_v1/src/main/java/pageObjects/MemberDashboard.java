package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MemberDashboard {
	
	public WebDriver driver;
	
	By TotalAmount=By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div");
	By PopUpNextbutton=By.xpath("//a[@class='btn btn-focus']");
	By Confirmationbutton=By.xpath("//button[@class='swal2-confirm btn btn-success m-btn m-btn--custom']");
	By paypalonline=By.xpath("//strong[contains(text(),'Paypal')]");
	By PayNowButton=By.id("submit");
	By InvoiceNumber=By.xpath("//a[contains(text(),'INV')]");
	By InvoiceTotal=By.xpath("//span[text()='Invoice Total']/parent::td//following-sibling::td");
	By Closebutton=By.xpath(" //div[@id='view-modal-data']//button[@class='close']");
	
	By MyInvoices=By.xpath("//a[text()='Invoices']");
	By AllInvoices=By.xpath("//div[@class='dropdown-menu show']//span[text()='All']");
	By Receipts=By.xpath(" //a[contains(text(),'Receipts')]");
	By ReceiptNumber=By.xpath("//div[@id='response_receipts']//div[1]//span[1]//a[1]");
	By ReceiptAmount=By.xpath("//span[@class='m-invoice__text']//strong");
	By Topup=By.xpath("//a[@class='btn m-btn--pill btn-accent m-btn']//span[contains(text(),'Top Up')]");
	By Amount=By.id("amount1");
	By SelectService=By.xpath("//div[contains(@class,'m-widget28')]//div[contains(@class,'m-widget6__body')]//div[1]//span[3]//parent::div//following-sibling::a[@data-toggle='dropdown']");
	By RenewThisService=By.xpath("//div[contains(@class,'dropdown-menu dropdown-menu-right show')]//a[contains(@class,'dropdown-item')][contains(text(),'Renew this service')]");
	By BillingSummary=By.xpath("//span[@class='d-f lex line-height-56']");
	By TooltipIcon=By.xpath("//i[@class='la la-cog']");
	By listtransctions=By.xpath("//span[@class='m-nav__link-text'][contains(text(),'List')]");
	By topUpamount=By.xpath("//tr[3]//td[6]");
	By ServiceDueDate=By.xpath("//div[contains(@class,'m-widget28')]//div[@class='m-widget6__body']//div[1]//span[2]");
	By InvoiceStatus=By.xpath("//div[5]//h5[1]//span[1]");
	By OrderAmountOnDashboard=By.xpath(" //div[@id='m_tabs_1_4']//div[contains(@class,'m-widget6__body')]//div[1]//span[3]");
    By ReceiptAmountOnDashboard=By.xpath("//div[@id='m_personal_income_receipts']//div[2]//div[1]//span[3]");
    By InvoiceAmountOnDashboard=By.xpath("//div[div[span[contains(text(),'Amount (')]]]/following-sibling::div[1]/div[1]/span[@class='m-widget6__text m--align-right m--font-boldest m--font-brand']/text()[1]");
    By MyDashboard=By.xpath("//span[contains(text(),'My Dashboard')]");
    
    By CreditSummaryNextbutton=By.id("submit");
    By orderStatus=By.xpath("//span[contains(text(),'Status :')]//parent::span//following-sibling::span");
	
	public MemberDashboard(WebDriver driver) {
		
		this.driver=driver;
		
	}

	public WebElement getTotalAmount() {
		return driver.findElement(TotalAmount);
	}
	public WebElement getclickonNextbutton() {
		return driver.findElement(PopUpNextbutton);
	}
	
	public WebElement getclickonOK() {
		return driver.findElement(Confirmationbutton);
	}
	
	public WebElement getclickonPaypalOnline() {
		return driver.findElement(paypalonline);
	}
	

	public WebElement getclickonPayNowButton() {
		return driver.findElement(PayNowButton);
	}
	

	public WebElement getInvoiceTotal() {
		return driver.findElement(InvoiceTotal);
	}
	
	public WebElement getClickOnInvoiceNo() {
		return driver.findElement(InvoiceNumber);
	}
	
	public WebElement getclickonClosebutton() {
		return driver.findElement(Closebutton);
	}
	public WebElement getclickonInvoices() {
		return driver.findElement(MyInvoices);
	}
	
	
	public WebElement getSelectAllInvoices() {
		return driver.findElement(AllInvoices);
	}
	
	public WebElement getSelectReceipts() {
		return driver.findElement(Receipts);
	}
	
	public WebElement getSelectReceiptNumber() {
		return driver.findElement(ReceiptNumber);
	}
	
	public WebElement getTotalAmountInReceipts() {
		return driver.findElement(ReceiptAmount);
	}
	

	public WebElement getTopUpButton() {
		return driver.findElement(Topup);
	}
	

	public WebElement getAmount() {
		return driver.findElement(Amount);
	}
	

	public WebElement getServiceDropdown() {
		return driver.findElement(SelectService);
	}
	
	public WebElement getRenewThisService() {
		return driver.findElement(RenewThisService);
	}
	

	public WebElement getBillingSummary() {
		return driver.findElement(BillingSummary);
	}
	
	public WebElement getToolTipIcon() {
		return driver.findElement(TooltipIcon);
	}
	

	public WebElement getListTransactions() {
		return driver.findElement(listtransctions);
	}
	
	

	public WebElement getTopUpAmount() {
		return driver.findElement(topUpamount);
	}
	

	public WebElement getServiceDueDate() {
		return driver.findElement(ServiceDueDate);
	}
	

	public WebElement getInvoiceStatus() {
		return driver.findElement(InvoiceStatus);
	}
	
	public WebElement getOrderAmountOnDashboard() {
		return driver.findElement(OrderAmountOnDashboard);
	}
	
	public WebElement getReceiptAmountOnDashboard() {
		return driver.findElement(ReceiptAmountOnDashboard);
	}
	
	public WebElement getInvoiceAmountOnDashboard() {
		return driver.findElement(InvoiceAmountOnDashboard);
	}
	

	public WebElement goToMyDashboard() {
		return driver.findElement(MyDashboard);
	}
	
	public WebElement getCreditSummaryNextButton() {
		return driver.findElement(CreditSummaryNextbutton);
	}
	
	public WebElement getOrderStatus() {
		return driver.findElement(orderStatus);
	}
}
