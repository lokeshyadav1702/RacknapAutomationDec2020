package Marketplace;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.MemberDashboard;
import pageObjects.ProductsPurchasePage;
import pageObjects.SignUpPage;
import resources.base;

public class PurchaseAzurePostpaid extends base{
	
	
	public static String username;
	public static String domain;
	String Amount="$ 0.00";
	String Status="Due for Payment";
	String Message="Thanks for placing your order! You‘ll be receiving a mail with more details from us soon:)";
	
@BeforeTest
	
	public void initialize() throws IOException{
		driver=initializeDriver();
		driver.get(prop.getProperty("AzureURL"));
		
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
	}
@Test(dataProvider="getData",priority=1)

public void purchaseAzure(String email,String password,String ConfirmPassword,String FirstName,String LastName,String CompanyName,String Address1,String City,String Currency,String MobileNumber,String Zipcode,String PaypalEmail,String PaypalPassword) throws AWTException, InterruptedException {
	
	ProductsPurchasePage pp=new ProductsPurchasePage(driver);
	SignUpPage sign=new SignUpPage(driver);
	pp.clickOnNext().click();
	pp.creatAccount().click();
	 username=randomUsername(); 
	sign.getUsername().sendKeys(username);
	sign.getEmail().sendKeys(email);
	sign.getPassword().sendKeys(password);
	sign.getConfirmpassword().sendKeys(ConfirmPassword);
	sign.getFirstname().sendKeys(FirstName);
	sign.getLastname().sendKeys(LastName);
	sign.getCompanyname().sendKeys(CompanyName);
	sign.getAddress1().sendKeys(Address1);
	sign.getcity().sendKeys(City);
	Select currency=new Select(sign.getSelectCurrency());
	currency.selectByVisibleText(Currency);
	sign.getMobilenumber().sendKeys(MobileNumber);
	sign.getZipcode().sendKeys(Zipcode);
	
	sign.getSelectCheckbox().click();
	sign.getCreateAccountbutton().click();
	
	domain= randomDomainName(); 
	pp.getAzureHostname().sendKeys(domain);
	pp.getSelectButtonAzure().click();
	pp.getOKButtonAzure().click();
	
	
	try {
		Thread.sleep(5000);
		pp.getClickOnNextButton().click();
		
		}
		
		catch(NoSuchElementException e){
			
			Thread.sleep(5000);
			pp.getClickOnNextButton().click();
			
		}
		

	pp.getSelectCalender().click();
	pp.getPreviousButton().click();
	pp.getSelectDate().click();
	waitForElementToVisible(By.id("save_agreement_btn"));
	
	pp.getClickOnAcceptButton().click();
	waitForElementToVisible(By.xpath("//button[@class='confirm btn btn-lg btn-warning']"));
	
	pp.getClickOnAcceptAlert().click();
	pp.getCheckoutButton().click();
	waitForElementToVisible(By.xpath("//button[@class='confirm btn btn-lg btn-warning']"));
	pp.getClickOnAcceptPaymentAlert().click();
	
	
	
	
	try {
	    
	  
	    Assert.assertEquals(pp.getSuccessMessageAzure().getText(), Message);
		
	}
	catch (UnreachableBrowserException e) {
	


		 Assert.assertEquals(pp.getSuccessMessageAzure().getText(), Message);
	}
}
@Test(priority=2)

public void verifyDomainNameInMyAccount() throws InterruptedException {
	
	ProductsPurchasePage pp=new ProductsPurchasePage(driver);


	pp.goToMyAccountPage().click();
	waitForElementToBeClickable(By.xpath("//a[text()='Orders']"));
	
	for(int i=0; i<=2;i++){
		  try{
			  pp.goToMyOrder().click();
		     break;
		  }
		  catch(StaleElementReferenceException e){
		     System.out.println("Exception found");
		  }
		}
	
	pp.getSelectAllOrders().click();
	
	waitForElementToBeClickable(By.xpath("//div[@id='response_all']//div[1]//span[1]//a[1]"));
	for(int i=0; i<=2;i++){
		  try{
			  pp.getClickonOrderNo().click();
		     break;
		  }
		  catch(StaleElementReferenceException e){
		     System.out.println("Exception found");
		  }
		}

	waitForElementToVisible(By.xpath("//div[@class='product_details']"));
	String HostONUI=pp.getHostnameInOrder().getText();
    boolean ActualHostname=	HostONUI.contains(domain);
    System.out.println(ActualHostname);
   
    Assert.assertTrue(ActualHostname);
}

@Test(priority=3)

public void verifyTotalAmountInMyOrders() {
	MemberDashboard mm=new MemberDashboard(driver);
	waitForElementToVisible(By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div"));
	Assert.assertEquals(mm.getTotalAmount().getText(),Amount);
	
}

@Test(priority=4)

public void verifyOrderStatusInMyOrders() {
	MemberDashboard mm=new MemberDashboard(driver);

	Assert.assertEquals(mm.getOrderStatus().getText(),Status);
	
}
@AfterTest

		public void teardown() {
			//driver.close();
		}
	   
@DataProvider(name="getData")

public Object[][] getData(){
	Object[][] data=new Object[1][13];
	data[0][0]="tarun@gmail.com";
	data[0][1]="Password12";
	data[0][2]="Password12";
	data[0][3]="Tanmay";
	data[0][4]="Singh";
	data[0][5]="Znet Technologies";
	data[0][6]="Chitrakoot";
	data[0][7]="Jaipur";
	data[0][8]="$";
	data[0][9]="9782437496";
	data[0][10]="302021";
	
	
	data[0][11]="govind@znetlive.com";
	data[0][12]="znet@123";
	
	
	return data;
	
}
}
