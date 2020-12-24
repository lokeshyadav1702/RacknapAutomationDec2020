package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyServicesPage {
	

	public  WebDriver driver;
	
    By MyServices=By.linkText("My Services");
	By ListServices=By.linkText("List/Search Services");
	By SubmitCancellationRequest=By.xpath("//tr[1]//td[9]//div[1]//div[1]//a[text()='Submit Cancellation Request']");
	By ServiceRenewaldate=By.xpath("//tr[1]//td[5]");
	By Action=By.xpath("//div[@id='record']//tr[1]/td[9]/div[1]/a[1]");
	By CancelServiceType=By.id("cancel_service_type");
	By CancellationReason=By.id("cancel_reason_type");
	By Remarks=By.id("service_cancel_reason");
	By SubmitCancellationbutton=By.id("submit_cancel_request");
	By SuccessCancelationMessage=By.xpath("//div[text()='Your request has been submitted successfully.']");
	By CancellationVoid=By.xpath("//tr[1]//td[9]//div[1]//div[1]//a[contains(text(),'Cancellation Void')]");
	By CancellationVoidMessage=By.xpath("//div[contains(text(),'Service Cancellation request cancelled Successfull')]");
	By RenewalCenter=By.id("billing_center_anuj_test");
	By RenewNow=By.xpath("//form[@id='duepaymentform']//button[@class='btn m-btn btn-focus']");
	By ServiceName=By.xpath("//tr[1]//td[1]//a[1]");
	By ExpandButton=By.xpath("//tr[@id='m_accordion_1_item_1_head']//span[@class='m-accordion__item-mode']");
	
	By CreateTicket=By.xpath("//div[@class='dropdown-menu dropdown-menu-right show']//a[@class='dropdown-item'][contains(text(),'Create a Ticket')]");
	By ChooseService=By.id("server_id");
	By ChooseTicketType=By.id("ticket_type");
	By Department=By.id("department");
	By Subject=By.id("subject");
	By Description=By.id("description");
	By SubmitTicket=By.xpath("//input[@value='Submit Ticket']");
	By TicketSuccessMessage=By.xpath("//body/div/div/div/div[2]/div[2]");
	By ServiceTotalAmount=By.xpath("//span[3]//strong[1]");
	By UpgradeDowngradeCenter=By.xpath("//div[@id='main_portlet']//li[3]//a[1]");
	By UpgradeDowngradeCenterAcronics=By.xpath("//a[contains(text(),'Upgrade/Downgrade Center')]");
	By AddQuantityAcronics=By.xpath("//div[@class='col-md-2']//input[@class='form-control m-input m-input--air']");
	By AddQuantity=By.name("quantity_of_addons[116]");
	By addtoUpgrade=By.id("add_to_upgrade");
	By addtoDowngrade=By.id("add_to_downgrade");
	By Upgrade=By.id("addon_paynow");
	By Downgrade=By.id("addon_downgrade_pay");
	By DowngradeMessage=By.id("swal2-content");
	By getQuantity=By.xpath("//td[3]");
	By quantityAmount=By.xpath("//body//strong//span[2]");
	By quantityAmountAcronis=By.xpath("//div[contains(@class,'m-portlet m-portlet--head-sm m-portlet--bord ered m-portl et--unair')]//form//strong//span");
	By downgradeQuantityAmountLedger=By.xpath("//tr[td[span[@class='m--font-bolder']]]/preceding-sibling::tr[1]/td[6]");
	By tenure=By.xpath("//tr[@id='term_accordion_1_item_1_head0']//td//label//span");
	By UpgradeNow=By.id("upgrade_service");
	By billingCenter=By.linkText("Billing Center");
	By serviceTenure=By.xpath("//tr[@id='term_accordion_1_item_1_head1']//td//label//span");
	By upgadeNowDomain=By.id("change_domain_cycle_btn");
	By billingCycle=By.xpath("//span[contains(text(),'One Time')]");
	By firstPaymentamount=By.xpath("//p[4]//span[1]");
	
	public MyServicesPage(WebDriver driver) {
		
		this.driver=driver;
		
	}

	public  WebElement getMyServices() {
		return driver.findElement(MyServices);
	}
	
	public WebElement getListServices() {
		return driver.findElement(ListServices);
	}
	
	
	public WebElement getAction() {
		return driver.findElement(Action);
	}
	
	public WebElement getSubmitCancellationRequest() {
		return driver.findElement(SubmitCancellationRequest);
	}
	
	
	public WebElement getCancelServiceType() {
		return driver.findElement(CancelServiceType);
	}
	
	public WebElement getCancellationReason() {
		return driver.findElement(CancellationReason);
	}
	
	
	public WebElement getRemarks() {
		return driver.findElement(Remarks);
	}
	
	public WebElement getSubmitCancellation() {
		return driver.findElement(SubmitCancellationbutton);
	}
	

	public WebElement getSuccessCancellationMessage() {
		return driver.findElement(SuccessCancelationMessage);
	}
	
	public WebElement getCancellationVoid() {
		return driver.findElement(CancellationVoid);
	}
	

	public WebElement getCancellationVoidMessage() {
		return driver.findElement(CancellationVoidMessage);
	}
	

	public WebElement getServiceName() {
	
		return driver.findElement(ServiceName);
	}
	

	public WebElement getRenewalCenter() {
		return driver.findElement(RenewalCenter);
	}
	

	public WebElement getRenewNow() {
		return driver.findElement(RenewNow);
	}
	

	public WebElement getCreateTicket() {
		return driver.findElement(CreateTicket);
	}
	

	public WebElement getChooseService() {
		return driver.findElement(ChooseService);
	}
	

	public WebElement getTicketType() {
		return driver.findElement(ChooseTicketType);
	}
	

	public WebElement getDepartment() {
		return driver.findElement(Department);
	}
	

	public WebElement getSubject() {
		return driver.findElement(Subject);
	}
	
	public WebElement getDescription() {
		return driver.findElement(Description);
	}
	

	public WebElement getSubmitTicket() {
		return driver.findElement(SubmitTicket);
	}
	

	public WebElement getTicketSucessMessage() {
		return driver.findElement(TicketSuccessMessage);
	}
	
	public WebElement getServiceRenewalDate() {
		return driver.findElement(ServiceRenewaldate);
	}
	
	public WebElement getExpandButton() {
		return driver.findElement(ExpandButton);
	}
	
	public WebElement getServiceTotalAmount() {
		return driver.findElement(ServiceTotalAmount);
	}
	
	public WebElement getUpgradeDowngradeCenter() {
		return driver.findElement(UpgradeDowngradeCenter);
	}
	
	public WebElement getUpgradeDowngradeCenterAcronics() {
		return driver.findElement(UpgradeDowngradeCenterAcronics);
	}
	public WebElement getAddQuantity() {
		return driver.findElement(AddQuantity);
	}
	
	public WebElement getAddQuantityAcronics() {
		return driver.findElement(AddQuantityAcronics);
	}
	public WebElement getAddToUpgrade() {
		return driver.findElement(addtoUpgrade);
	}
	
	public WebElement getAddToDowngrade() {
		return driver.findElement(addtoDowngrade);
	}
	
	public WebElement getUpgradeButton() {
		return driver.findElement(Upgrade);
	}
	
	public WebElement getDowngradeButton() {
		return driver.findElement(Downgrade);
	}
	
	public WebElement getDowngradeSuccessMesaage() {
		return driver.findElement(DowngradeMessage);
	}
	
	public WebElement getQuantityOnUI() {
		return driver.findElement(getQuantity);
	}
	

	public WebElement getQuantityAmount() {
		return driver.findElement(quantityAmount);
	}
	
	public WebElement getQuantityAmountAcronics() {
		return driver.findElement(quantityAmountAcronis);
	}

	public WebElement getCreditAmountInLedger() {
		return driver.findElement(downgradeQuantityAmountLedger);
	}
	
	public WebElement getTenure() {
		return driver.findElement(tenure);
	}
	
	public WebElement getUpgradeNow() {
		return driver.findElement(UpgradeNow);
	}
	
	public WebElement getBillingCenter() {
		return driver.findElement(billingCenter);
	}
	
	public WebElement getDomainServiceTenure() {
		return driver.findElement(serviceTenure);
	}
	
	public WebElement getDomainUpgradeButton() {
		return driver.findElement(upgadeNowDomain);
	}
	
	public WebElement getBillingCycle() {
		return driver.findElement(billingCycle);
	}
	
	public WebElement getFirstPaymentAmount() {
		return driver.findElement(firstPaymentamount);
	}
	
}
