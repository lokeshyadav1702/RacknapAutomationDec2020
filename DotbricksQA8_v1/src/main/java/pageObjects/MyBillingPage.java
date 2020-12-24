package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MyBillingPage {

	public  WebDriver driver;
	
	
	By MyBilling=By.xpath("//span[contains(text(),'My Billing')]");
	By Ledger=By.xpath("//span[contains(text(),'Ledger')]");
	
	By Invoices=By.xpath(" //span[contains(text(),'Invoices')]");
	
	By Orders=By.xpath("//span[contains(text(),'Orders')]");
	
	By Receipts=By.xpath("//span[contains(text(),'Receipts')]");
	
	By ServicesDueForRenewal=By.xpath("//span[contains(text(),'Services due for Renewal')]");
	 
	By TopUp=By.xpath("//span[@class='m-menu__link-text'][contains(text(),'Top Up')]");
			
	By PlaceNewOrder=By.xpath("//span[@class='m-menu__link-text'][contains(text(),'Place New Order')]");		
	
	By AmountInInvoices=By.xpath(" //div[contains(@class,'m-grid m-grid--hor m-grid--root m-page')]//tr[1]//td[5]");
	
	By AmountInOrders=By.xpath("//tr[1]//td[4]");
	By AmountInReceipts=By.xpath("//tr[1]//td[4]");
	By DebitAmountInLedger=By.xpath("//tr[td[span[@class='m--font-bolder']]]/preceding-sibling::tr[1]/td[5]"); 
			
	By CreditAmountInLedger=By.xpath("//tr[td[span[@class='m--font-bolder']]]/preceding-sibling::tr[2]/td[6]"); 
	
	public MyBillingPage(WebDriver driver) {
		
		this.driver=driver;
		
	}

	public  WebElement goToMyBilling() {
		return driver.findElement(MyBilling);
	}
	
	public  WebElement goToLedger() {
		return driver.findElement(Ledger);
	}
	
	public  WebElement goToInvoices() {
		return driver.findElement(Invoices);
	}
	
	
	public  WebElement goToOrders() {
		return driver.findElement(Orders);
	}
	
	public  WebElement goToReceipts() {
		return driver.findElement(Receipts);
	}
	public  WebElement goToServicesDueForRenewal() {
		return driver.findElement(ServicesDueForRenewal);
	}
	
	public  WebElement goToTopUp() {
		return driver.findElement(TopUp);
	}
	
	public  WebElement goToPlaceNewOrder() {
		return driver.findElement(PlaceNewOrder);
	}
	
	public  WebElement getAmountInInvoices() {
		return driver.findElement(AmountInInvoices);
	}
	
	public  WebElement getAmountInOrders() {
		return driver.findElement(AmountInOrders);
	}
	
	public  WebElement getAmountInReceipts() {
		return driver.findElement(AmountInReceipts);
	}
	
	public WebElement getDebitAmountInLedger(){
	 
   return driver.findElement(DebitAmountInLedger);
   }
	
	public WebElement getCreditAmountInLedger(){
		 
		return driver.findElement(CreditAmountInLedger);
}

}