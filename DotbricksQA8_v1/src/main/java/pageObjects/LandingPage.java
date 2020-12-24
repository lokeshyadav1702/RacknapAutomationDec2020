package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	public WebDriver driver;
	
By login =By.xpath("//a[contains(text(),'Login')]");
By CustomerLogin =By.xpath("//a[text()=' Customer Login']");


public LandingPage(WebDriver driver) {
	
	this.driver=driver;
	
}

public WebElement getLogin() {
	return driver.findElement(login);
}

public WebElement getCustomerLogin() {
	return driver.findElement(CustomerLogin);
}

}
