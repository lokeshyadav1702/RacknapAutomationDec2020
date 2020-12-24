package Marketplace;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.MemberDashboard;
import pageObjects.MyBillingPage;
import pageObjects.MyServicesPage;
import pageObjects.MySupportPage;
import pageObjects.ProductsPurchasePage;
import resources.base;

public class MyServices extends base {

	public WebDriver driver;
	MemberDashboard mm=new MemberDashboard(driver);
	String OrderamountOnDashboard;
	String InvoiceamountOnDashboard;
	String subject="Test1";
	String PaypalEmail="govind@znetlive.com";
	String PaypalPassword="znet@123";
	//String Username="singh9779";
	String Password="Password12";
	String type="Upgrade";
	String URL="https://manage.qa8.dotbricks.net/";
	String UpgradeQuantity="7";
	String QuantityAmount;
	String DowngradeQuantity="5";
   
 
	@BeforeTest

	public void initialize() throws IOException, InterruptedException {

		driver=initializeDriver();
		driver.get(prop.getProperty("url"));
	 
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
		
		LoginPage lp=new LoginPage(driver);
		LandingPage landingpage=new LandingPage(driver);
		landingpage.getLogin().click();
	//	landingpage.getCustomerLogin().click();
	  // lp.getUsername().sendKeys("singh9512");
		lp.getUsername().sendKeys(PurchaseMSOffice365Offline.username);
		lp.getPassword().sendKeys(Password);
		lp.getSignIn().click();

		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		//waitForElementToAppear(By.xpath("//a[contains(text(),'My Account')]"));
		pp.goToMyAccountPage().click();
		//Thread.sleep(5000);
		}

	
		
	
	@Test(dataProvider="getData",priority=1,enabled=false)
	
	public void SignIn(String username,String password) {

		LoginPage lp=new LoginPage(driver);
		LandingPage landingpage=new LandingPage(driver);
		landingpage.getLogin().click();
		//landingpage.getCustomerLogin().click();
		lp.getUsername().sendKeys(username);
		lp.getPassword().sendKeys(password);
		lp.getSignIn().click();

		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		//waitForElementToAppear(By.xpath("//a[contains(text(),'My Account')]"));
		pp.goToMyAccountPage().click();
		
		}
	
	
	
	 @Test(priority=2,enabled=false)
	   
	   public void RenewThisServiceAndVerifyDueDate() throws InterruptedException, ParseException {
		   
		    ProductsPurchasePage pp=new ProductsPurchasePage(driver);
			MemberDashboard mm=new MemberDashboard(driver);
			MyServicesPage page=new MyServicesPage(driver);
			
			
			page.getMyServices().click();
			page.getListServices().click();
			String dateOnUI = page.getServiceRenewalDate().getText();
			page.getAction().click();
            
			mm.getRenewThisService().click();
			waitForElementToVisible(By.xpath("//button[@class='swal2-confirm btn btn-success m-btn m-btn--custom']"));
			mm.getclickonOK().click();
			
			waitForElementToVisible(By.xpath("//strong[contains(text(),'PayPal The Online Payment Gateway of World')]"));
			mm.getclickonPaypalOnline().click();
			mm.getclickonOK().click();
			
			try {
			mm.getclickonPayNowButton().click();
			//Thread.sleep(50000);
			refresh();
			Thread.sleep(50000);
			//waitForElementToVisible(By.xpath("//a[text()='Log In']"));
			pp.getLoginPaypal().click();
			}
			catch (UnreachableBrowserException e) {
				mm.getclickonPayNowButton().click();
				//Thread.sleep(50000);
				refresh();
				Thread.sleep(50000);
				//waitForElementToVisible(By.xpath("//a[text()='Log In']"));
				pp.getLoginPaypal().click();
				
			}
			
			Thread.sleep(10000);
			pp.getAcceptCookies().click();
		   // waitForElementToVisible(By.id("email"));
			
			pp.getPaypalEmail().sendKeys("govind@znetlive.com");
			pp.getSubmitEmail().click();
			pp.getPaypalPassword().sendKeys("znet@123");
			pp.getPaypalLogin().click();

			try {
		     Thread.sleep(10000);
			pp.getClickContinue().click();
			Thread.sleep(30000);
//			waitForPageToLoad();
	  //	waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
			
			pp.getClosePopUp().click();
			//waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
			//System.out.println(pp.getSuccessMessage().getText());
	      //  Assert.assertEquals(pp.getSuccessMessage().getText(), "Your payment has been processed successfully");
			}
			
			catch (UnreachableBrowserException e) {
				Thread.sleep(10000);
				pp.getClickContinue().click();
//				waitForPageToLoad();
//				waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
				Thread.sleep(10000);
				pp.getClosePopUp().click();
			}	
				pp.goToMyAccountPage().click();
				
				page.getMyServices().click();
				page.getListServices().click();
				  
				Date oldDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateOnUI);
				String expectedNewDate = renewalDate(oldDate, 1);
				
			    String newDateOnUI = page.getServiceRenewalDate().getText();
				Assert.assertEquals(newDateOnUI, expectedNewDate);
				
//				waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
//				//System.out.println(pp.getSuccessMessage().getText());
//		        Assert.assertEquals(pp.getSuccessMessage().getText(), "Your payment has been processed successfully");
				}
			
 @Test(priority=3)

   public void submitCancellationRequest() throws InterruptedException {
	 
	MyServicesPage page=new MyServicesPage(driver);
	MemberDashboard mm=new MemberDashboard(driver);
	mm.getclickonClosebutton().click();
	page.getMyServices().click();
	page.getListServices().click();
	page.getAction().click();
	
    if (page.getSubmitCancellationRequest().getText().equals("Submit Cancellation Request")){
	
	page.getSubmitCancellationRequest().click();
	
	waitForElementToVisible(By.id("cancel_service_type"));
	Select type=new Select(page.getCancelServiceType());
	type.selectByVisibleText("Immediate");
	

	Select reason=new Select(page.getCancellationReason());
	reason.selectByVisibleText("High prices");
	
	page.getRemarks().sendKeys("TEST");
	
	page.getSubmitCancellation().click();
	
	mm.getclickonOK().click();
	waitForElementToVisible(By.xpath("//div[text()='Your request has been submitted successfully.']"));
   Assert.assertEquals(page.getSuccessCancellationMessage().getText(),"Your request has been submitted successfully.");
}
	
	else {
		page.getCancellationVoid().click();

		mm.getclickonOK().click();
		waitForElementToVisible(By.xpath("//div[contains(text(),'Service Cancellation request cancelled Successfull')]"));
		   Assert.assertEquals(page.getCancellationVoidMessage().getText(),"Service Cancellation request cancelled Successfully.");
	}
	}
	



@Test(priority=4)
public void createTicket() throws InterruptedException{
	
	MyServicesPage page=new MyServicesPage(driver);
	MemberDashboard mm=new MemberDashboard(driver);
	MySupportPage sp=new MySupportPage(driver);
	
	mm.getclickonOK().click();
	page.getAction().click();
	page.getCreateTicket().click();
	
	Select type=new Select(page.getTicketType());
	type.selectByVisibleText("Change Request");
	
	Select department=new Select(page.getDepartment());
	department.selectByVisibleText("Abuse");
	
	//waitForElementToVisible(By.id("subject"));
	Thread.sleep(5000);
	page.getSubject().sendKeys(subject);
	page.getDescription().sendKeys("Test Description");
	page.getSubmitTicket().click();
	System.out.println(page.getTicketSucessMessage().getText());
	sp.goToMySupport().click();
	sp.getSearchTickets().click();
	Assert.assertEquals(sp.getTicketSubject().getText(),subject);
	
	
}

@Test(priority=1)
 public void renewNowAndVerifyAmount() throws InterruptedException {
	ProductsPurchasePage pp=new ProductsPurchasePage(driver);
	MyServicesPage page=new MyServicesPage(driver);
	MemberDashboard mm=new MemberDashboard(driver);
	MyBillingPage billing=new MyBillingPage(driver);
	try {
		//Thread.sleep(3000);
	waitForElementToVisible(By.linkText("My Services"));
	page.getMyServices().click();
	
	}
	catch(org.openqa.selenium.TimeoutException e) {
		waitForElementToVisible(By.linkText("My Services"));
		page.getMyServices().click();	
		
	}
	waitForElementToVisible(By.linkText("List/Search Services"));
	page.getListServices().click();
	waitForElementToVisible(By.xpath("//tr[1]//td[1]//a[1]"));
	page.getServiceName().click();
	page.getRenewalCenter().click();
	page.getExpandButton().click();
	page.getRenewNow().click();
	
	
	waitForElementToVisible(By.xpath("//button[@class='swal2-confirm btn btn-success m-btn m-btn--custom']"));
	mm.getclickonOK().click();
	String TotalAmount=page.getServiceTotalAmount().getText();
	
	waitForElementToVisible(By.xpath("//strong[contains(text(),'Paypal')]"));
	mm.getclickonPaypalOnline().click();
	mm.getclickonOK().click();
	

	mm.getclickonPayNowButton().click();
	
	pp.getPaypalEmail().sendKeys(PaypalEmail);
	pp.getSubmitEmail().click();
	pp.getPaypalPassword().sendKeys(PaypalPassword);
	pp.getPaypalLogin().click();
	//Thread.sleep(50000);
	//refresh();
	//Thread.sleep(200000);
	//waitForElementToVisible(By.xpath("//a[text()='Log In']"));
//	pp.getLoginPaypal().click();
//	}
//	catch (UnreachableBrowserException e) {
//		mm.getclickonPayNowButton().click();
//		//Thread.sleep(50000);
//		refresh();
//		Thread.sleep(50000);
//		//waitForElementToVisible(By.xpath("//a[text()='Log In']"));
//		pp.getLoginPaypal().click();
//		
//	}
//	
//	Thread.sleep(10000);
//	pp.getAcceptCookies().click();
//   // waitForElementToVisible(By.id("email"));
//	
//	pp.getPaypalEmail().sendKeys("govind@znetlive.com");
//	pp.getSubmitEmail().click();
//	pp.getPaypalPassword().sendKeys("znet@123");
//	pp.getPaypalLogin().click();

	try {
    Thread.sleep(25000);
    
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView();",pp.getPayPalPolicyLink() );
    js.executeScript("arguments[0].click()", pp.getClickContinue());
	
//	waitForPageToLoad();
//	waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
	
	//pp.getClosePopUp().click();
	//waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
	//System.out.println(pp.getSuccessMessage().getText());
  //  Assert.assertEquals(pp.getSuccessMessage().getText(), "Your payment has been processed successfully");
	}
	
	catch (Exception e) {
		Thread.sleep(25000);
		pp.getClickContinue().click();
//		waitForPageToLoad();
//		waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
		
		//pp.getClosePopUp().click();
	}	
	
	    waitForElementToVisible(By.xpath("//a[contains(text(),'My Account')]"));
		pp.goToMyAccountPage().click();
		
		billing.goToMyBilling().click();
		billing.goToLedger().click();
		Assert.assertEquals(billing.getDebitAmountInLedger().getText(),TotalAmount);
}


@Test(priority=2)

public void verifyRenewServiceInvoiceStatus() throws ParseException {
	   
	   ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		MemberDashboard mm=new MemberDashboard(driver); 
		mm.goToMyDashboard().click();
		mm.getClickOnInvoiceNo().click();
		waitForElementToVisible(By.xpath("//div[5]//h5[1]//span[1]"));
		Assert.assertEquals(mm.getInvoiceStatus().getText(),"Paid");


		
}

@Test(priority=5)

public void upgradeServiceAndVerifyAmountInLedger() throws ParseException, InterruptedException {
	
	MyBillingPage billing=new MyBillingPage(driver);
	MyServicesPage page=new MyServicesPage(driver);
	ProductsPurchasePage pp=new ProductsPurchasePage(driver);
	MemberDashboard mm=new MemberDashboard(driver);
	waitForElementToVisible(By.linkText("My Services"));
	page.getMyServices().click();
	waitForElementToVisible(By.linkText("List/Search Services"));
	page.getListServices().click();
	waitForElementToVisible(By.xpath("//tr[1]//td[1]//a[1]"));
	page.getServiceName().click();
	page.getUpgradeDowngradeCenter().click();

	//page.getAddQuantity().clear();
	//page.getAddQuantity().sendKeys("2");
	 page.getAddQuantity().sendKeys(Keys.BACK_SPACE);
	 
	
		page.getAddQuantity().sendKeys("2");
		page.getAddToUpgrade().click();
		page.getUpgradeButton().click();
		waitForElementToVisible(By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div"));
	    QuantityAmount=mm.getTotalAmount().getText();
		mm.getclickonNextbutton().click();
		Thread.sleep(3000);
		mm.getclickonOK().click();
		waitForElementToVisible(By.xpath("//strong[contains(text(),'Paypal')]"));
		mm.getclickonPaypalOnline().click();
		Thread.sleep(3000);
		mm.getclickonOK().click();
		
//		try {
		mm.getclickonPayNowButton().click();
		
		 Thread.sleep(10000);
	     pp.getAcceptCookies().click();
		
//		pp.getPaypalEmail().sendKeys(PaypalEmail);
//		pp.getSubmitEmail().click();
//		pp.getPaypalPassword().sendKeys(PaypalPassword);
//		pp.getPaypalLogin().click();

		try {
			
	     Thread.sleep(10000);
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("arguments[0].scrollIntoView();",pp.getPayPalPolicyLink() );
	     js.executeScript("arguments[0].click()", pp.getClickContinue());
		}
		
		catch(Exception e){
			  Thread.sleep(20000);
				pp.getClickContinue().click();
			
		}

  //	waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
		try {
		//pp.getClosePopUp().click();
		waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
		//System.out.println(pp.getSuccessMessage().getText());
        System.out.println(pp.getSuccessMessage().getText());
		}
		
		catch (UnreachableBrowserException e) {
			
        waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
		
			   System.out.println(pp.getSuccessMessage().getText());
		}
//		refresh();
   waitForElementToVisible(By.xpath("//a[contains(text(),'My Account')]"));
   
	 pp.goToMyAccountPage().click();
	 billing.goToMyBilling().click();
	    billing.goToLedger().click();
	
	Assert.assertEquals(billing.getDebitAmountInLedger().getText(),QuantityAmount);
}

@Test(priority=6)
public void verifyUpgradeQuantityInMyServices() {
	MyServicesPage page=new MyServicesPage(driver);
	 page.getMyServices().click();
     page.getListServices().click();
		Assert.assertEquals(page.getQuantityOnUI().getText(),UpgradeQuantity);
		
}

@Test(priority=7)

public void downgradeServiceAndVerifyAmountInLedger() throws InterruptedException {
	
	MyBillingPage billing=new MyBillingPage(driver);
	MyServicesPage page=new MyServicesPage(driver);
	ProductsPurchasePage pp=new ProductsPurchasePage(driver);
	MemberDashboard mm=new MemberDashboard(driver);
//	waitForElementToVisible(By.linkText("My Services"));
//	page.getMyServices().click();
//	waitForElementToVisible(By.linkText("List/Search Services"));
//	page.getListServices().click();
	waitForElementToVisible(By.xpath("//tr[1]//td[1]//a[1]"));
	page.getServiceName().click();
	page.getUpgradeDowngradeCenter().click();

	  page.getAddQuantity().sendKeys(Keys.BACK_SPACE);
	 page.getAddQuantity().sendKeys("2");
	
		page.getAddToDowngrade().click();
		//Thread.sleep(3000);
		
		waitForElementToVisible(By.xpath("//body//strong//span[2]"));
	   String QuantityAmountOnUI=page.getQuantityAmount().getText();
	  // System.out.println(QuantityAmountOnUI);
		page.getDowngradeButton().click();
		waitForElementToVisible(By.id("swal2-content"));
		
		System.out.println(page.getDowngradeSuccessMesaage().getText());
		mm.getclickonOK().click();
		 billing.goToMyBilling().click();
		 billing.goToLedger().click();
		// waitForElementToVisible(By.xpath("//tr[td[span[@class='m--font-bolder']]]/preceding-sibling::tr[2]/td[6]"));
		 Assert.assertEquals(page.getCreditAmountInLedger().getText(),QuantityAmountOnUI);
		  
	
}

@Test(priority=8)
public void verifyDowngradeQuantityInMyServices() {
	MyServicesPage page=new MyServicesPage(driver);
	 page.getMyServices().click();
     page.getListServices().click();
		Assert.assertEquals(page.getQuantityOnUI().getText(),DowngradeQuantity);
		
}
//
//@Test(priority=3)
//
//public void verifyAmountInMyInvoices() {
//	MyBillingPage billing=new MyBillingPage(driver);
//	MemberDashboard mm=new MemberDashboard(driver);
//	driver.navigate().back();
//	InvoiceamountOnDashboard=mm.getInvoiceAmountOnDashboard().getText();
//	billing.goToMyBilling().click();
//	billing.goToInvoices().click();
//
//	Assert.assertEquals(billing.getAmountInInvoices().getText(),InvoiceamountOnDashboard);
	
		

@AfterTest

public void teardown() {
	//driver.close();
}
	
	
		

@DataProvider(name="getData")

		public Object[][] getData(){
			Object[][] data=new Object[1][2];
			data[0][0]="singh9779";
			data[0][1]="Password12";
			return data;
}
}
