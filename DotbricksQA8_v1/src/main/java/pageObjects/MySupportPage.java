package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MySupportPage {
	public  WebDriver driver;
	
	
	
	By MySupport=By.xpath("//span[contains(@class,'m-menu__link-text')][contains(text(),'My Support')]");
	By Listtickets=By.xpath("//span[contains(@class,'m-menu__link-text')][contains(text(),'List/Search Tickets')]");
	
	By TicketSubject=By.xpath("//tr[1]//td[1]");
	
	By EmailLogs=By.xpath("//span[@class='m-menu__link-text'][contains(text(),'Email Logs')]");
	
	By EmailSubject=By.xpath("//tr[2]//td[1]");
	By EmailSubjectDomain=By.xpath("//tr[1]//td[1]");
	By emailServiceLog=By.xpath("//tr[3]//td[1]");
	By emailAcronisServiceLog=By.xpath("//tr[4]//td[1]");
	
	
	
public MySupportPage(WebDriver driver) {
		
		this.driver=driver;
		
	}

	public  WebElement goToMySupport() {
		return driver.findElement(MySupport);
	}
	public  WebElement getSearchTickets() {
		return driver.findElement(Listtickets);
	}
	
	public  WebElement getTicketSubject() {
		return driver.findElement(TicketSubject);
	}
	

	public  WebElement getEmailLogs() {
		return driver.findElement(EmailLogs);
	}
	

	public  WebElement getEmailSubject() {
		return driver.findElement(EmailSubject);
	}
	
	public  WebElement getEmailSubjectDomain() {
		return driver.findElement(EmailSubjectDomain);
	}
	
	public  WebElement getEmailServiceLog() {
		return driver.findElement(emailServiceLog);
	}
	
	public  WebElement getAcronisEmailServiceLog() {
		return driver.findElement(emailAcronisServiceLog);
	}
}
