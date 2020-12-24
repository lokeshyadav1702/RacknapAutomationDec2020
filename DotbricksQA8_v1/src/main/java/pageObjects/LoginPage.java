package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public WebDriver driver;
	
	By username =By.id("login_form_email");
	By password =By.id("login_form_password");
	By SignIn =By.name("log_in");
	
	By adminUsername=By.id("login_username");
	By adminpassword=By.id("login_password");
	By adminSignIn=By.xpath("//input[@value='Sign In']");
	By popup=By.linkText("Start");
	By clientDropdown=By.xpath("//ul[1]/li[1]/a[contains(text(),'Clients')]");
	By selectClient=By.xpath("//body/div[1]/div[2]/div[1]/div[3]/ul[1]/li[1]/ul[1]/li[1]/a[1]");
	By selectList=By.xpath("//body/div[1]/div[2]/div[1]/div[3]/ul[1]/li[1]/ul[1]/li[1]/ul[1]/li[1]/a[1]");
	By clickClient=By.xpath("//tbody/tr[1]/td[1]/a[1]");
	By ledgerAmount=By.xpath("//body[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[2]/td[8]");
	By product_Services=By.linkText("Products/Services");
	By serviceStatus=By.xpath("//tbody/tr[1]/td[12]");
	By invoices=By.linkText("Invoices");
	By invoiceAmount=By.xpath("//tbody/tr[1]/td[7]");
	By invoiceStatus=By.xpath("//tbody/tr[1]/td[8]");
	By orders=By.linkText("Orders");
	By orderAmount=By.xpath("//tbody/tr[1]/td[6]");
	By orderStatus=By.xpath("//tbody/tr[1]/td[7]");
	By Reciepts=By.linkText("Receipts");
	By ReceiptAmount=By.xpath("//tbody/tr[1]/td[9]");
	By ReceiptNumber =By.xpath("//tbody/tr[1]/td[2]");
	By Ledger =By.linkText("Ledger");
	By emailLogs=By.linkText("Email Logs");
	By emailSubject=By.xpath("//tbody/tr[2]/td[4]");
	By searchAll=By.id("search_all");
	By profileImage=By.xpath("//header/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[2]/a[1]/span[3]/img[1]");
	By myProfile=By.xpath("//span[contains(text(),'My Profile')]");
	By subUers=By.xpath("//a[contains(text(),'Subusers')]");
	By addSubuser=By.linkText("Add Subuser");
	By Username=By.id("c_username");
	By Firstname=By.id("c_firstname");
	By Lastname=By.id("c_lastname");
	By email=By.id("c_email");
	


	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
		
	}

	public WebElement getUsername() {
		return driver.findElement(username);
	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getSignIn() {
		return driver.findElement(SignIn);
	}
	public WebElement getAdminUsername() {
		return driver.findElement(adminUsername);
	}
	
	public WebElement getAdminPassword() {
		return driver.findElement(adminpassword);
	}
	
	public WebElement getAdminSignIn() {
		return driver.findElement(adminSignIn);
	}
	
	public WebElement getClosePopUp() {
		return driver.findElement(popup);
	}
	public WebElement getClientDropdown() {
		return driver.findElement(clientDropdown);
	}
	
	public WebElement getSelectClient() {
		return driver.findElement(selectClient);
	}
	
	public WebElement getSelectList() {
		return driver.findElement(selectList);
	}
	
	public WebElement getProduct_Services() {
		return driver.findElement(product_Services);
	}
	
	public WebElement getServiceStatus() {
		return driver.findElement(serviceStatus);
	}
	
	public WebElement getInvoicesTab() {
		return driver.findElement(invoices);
	}
	public WebElement getInvoiceAmount() {
		return driver.findElement(invoiceAmount);
	}
	public WebElement getInvoiceStatus() {
		return driver.findElement(invoiceStatus);
	}
	
	public WebElement getOrdersTab() {
		return driver.findElement(orders);
	}
	public WebElement getOrderAmount() {
		return driver.findElement(orderAmount);
	}
	
	public WebElement getOrderStatus() {
		return driver.findElement(orderStatus);
	}
	
	public WebElement getReciepts() {
		return driver.findElement(Reciepts);
	}
	
	public WebElement getReceiptAmount() {
		return driver.findElement(ReceiptAmount);
	}
	
	public WebElement getReceiptNumber() {
		return driver.findElement(ReceiptNumber);
	}
	
	public WebElement getLedger() {
		return driver.findElement(Ledger);
	}
	
	public WebElement getEmailLogs() {
		return driver.findElement(emailLogs);
	}
	public WebElement getEmailSubject() {
		return driver.findElement(emailSubject);
	}
	
	public WebElement getSearchAll() {
		return driver.findElement(searchAll);
	}
	
	public WebElement getClickClient() {
		return driver.findElement(clickClient);
	}

	public WebElement getLedgerAmount() {
		return driver.findElement(ledgerAmount);
	}
	}



