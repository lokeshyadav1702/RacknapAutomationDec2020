package Marketplace;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.MemberDashboard;
import pageObjects.MyBillingPage;
import pageObjects.MyServicesPage;
import pageObjects.ProductsPurchasePage;
import resources.base;

public class PurchaseESDCredit extends base {
	String Password = "Password12";
	String TopUpAmount = "100000.00";
	String Amount="$ 129.80";
	String renewalDate="N/A";
	String billingCycle="One Time";
	String firstPaymentAmount="$ 110.00";

	String PaypalEmail="govind@znetlive.com";
	String PaypalPassword="znet@123";
    String ProductName="Microsoft ESD - Office 365 Home";
	@BeforeTest

	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("ESDURL"));

		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
		LoginPage lp = new LoginPage(driver);
		LandingPage landingpage = new LandingPage(driver);
		landingpage.getLogin().click();
		lp.getUsername().sendKeys(PurchaseESDOffline.username);
		//lp.getUsername().sendKeys("singh3920");
		lp.getPassword().sendKeys(Password);
		lp.getSignIn().click();

		ProductsPurchasePage pp = new ProductsPurchasePage(driver);

		pp.goToMyAccountPage().click();

	}

	@Test(priority = 1)

	public void addTopUpAmountAndVerifyInReceipt() throws InterruptedException, AWTException {

		ProductsPurchasePage pp = new ProductsPurchasePage(driver);
		MemberDashboard mm = new MemberDashboard(driver);
		MyBillingPage billing = new MyBillingPage(driver);

		mm.getTopUpButton().click();
		mm.getAmount().sendKeys(TopUpAmount);
		MouseClick();
		waitForElementToVisible(By.xpath("//strong[contains(text(),'Paypal')]"));
		mm.getclickonPaypalOnline().click();
		Thread.sleep(3000);
		mm.getclickonOK().click();

		mm.getclickonPayNowButton().click();

		pp.getPaypalEmail().sendKeys(PaypalEmail);
		pp.getSubmitEmail().click();
		pp.getPaypalPassword().sendKeys(PaypalPassword);
		pp.getPaypalLogin().click();
		
		try {
		     Thread.sleep(25000);
		     JavascriptExecutor js = (JavascriptExecutor) driver;
		     js.executeScript("arguments[0].scrollIntoView();",pp.getPayPalPolicyLink() );
		     js.executeScript("arguments[0].click()", pp.getClickContinue());
			 
			}
			
			catch(TimeoutException e) {
				Thread.sleep(25000);
				
				pp.getClickContinue().click();	
				
				
				
			}

		try {
			
			waitForElementToVisible(By.xpath("//a[contains(text(),'My Account')]"));

			pp.goToMyAccountPage().click();
			billing.goToMyBilling().click();
			billing.goToReceipts().click();
			String AmountONUI = billing.getAmountInReceipts().getText();

			AmountONUI = AmountONUI.replaceAll(",", "");

			Assert.assertEquals(AmountONUI, TopUpAmount);

		}

		catch (UnreachableBrowserException e) {
			

			waitForElementToVisible(By.xpath("//a[contains(text(),'My Account')]"));
			pp.goToMyAccountPage().click();
			billing.goToMyBilling().click();
			billing.goToReceipts().click();
			String AmountONUI = billing.getAmountInReceipts().getText();

			AmountONUI = AmountONUI.replaceAll(",", "");

			Assert.assertEquals(AmountONUI, TopUpAmount);

		}

	}

	@Test(priority = 2)

	public void purchaseESDWithCredit() throws InterruptedException, AWTException {

		ProductsPurchasePage pp = new ProductsPurchasePage(driver);
		MemberDashboard mm = new MemberDashboard(driver);
		switchToNewWindow();
		driver.get(prop.getProperty("ESDURL"));

	
		pp.addToCart().click();

		try {
			Thread.sleep(5000);
			pp.getClickOnNextButton().click();
			
			}
			
			catch(NoSuchElementException e){
				
				Thread.sleep(5000);
				pp.getClickOnNextButton().click();
				
			}
			
		try {
			waitForPageToLoad();

			waitForElementToVisible(By.xpath("//input[@class='bnt-gradient paynow']"));
			pp.getPaymentSummaryNextButton().click();
			waitForPageToLoad();
			waitForElementToVisible(By.xpath("//div[@class='bay-bill-balance']"));

			System.out.println(pp.getCreditMessage().getText());
		}

		catch (Exception e) {
			waitForPageToLoad();
			waitForElementToVisible(By.xpath("//input[@class='bnt-gradient paynow']"));

			pp.getPaymentSummaryNextButton().click();

			waitForPageToLoad();

			waitForElementToVisible(By.xpath("//div[@class='bay-bill-balance']"));

			System.out.println(pp.getCreditMessage().getText());

		}

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
		//pp.goToMyOrder().click();
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

		waitForElementToVisible(By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div"));
		Assert.assertEquals(mm.getTotalAmount().getText(), Amount);

	}

	@Test(priority = 3)

	public void verifyProductNameInMyAccount() throws InterruptedException {
		
		ProductsPurchasePage pp=new ProductsPurchasePage(driver);

	
//		pp.goToMyAccountPage().click();
//		waitForElementToBeClickable(By.xpath("//a[text()='Orders']"));
//		
//		for(int i=0; i<=2;i++){
//			  try{
//				  pp.goToMyOrder().click();
//			     break;
//			  }
//			  catch(StaleElementReferenceException e){
//			     System.out.println("Exception found");
//			  }
//			}
//		
//		pp.getSelectAllOrders().click();
//		pp.getClickonOrderNo().click();
		waitForElementToVisible(By.xpath("//div[@class='product_details']"));
		String productOnUI=pp.getHostnameInOrder().getText();
	    boolean ActualProductName=	productOnUI.contains(ProductName);
	    System.out.println(ActualProductName);
	   
	    Assert.assertTrue(ActualProductName);
	}
	

	 @Test(priority=4)

	   public void verifyOrderAmountInMyBilling() {
	   	    MemberDashboard mm=new MemberDashboard(driver);
	   		MyBillingPage billing=new MyBillingPage(driver);
			
	   		mm.getclickonClosebutton().click();
	   		
	   		String OrderamountOnDashboard=mm.getOrderAmountOnDashboard().getText();
	   		billing.goToMyBilling().click();
	   		billing.goToOrders().click();
	   		
	   		Assert.assertEquals(billing.getAmountInOrders().getText(),OrderamountOnDashboard);
	   	
	   }
	   
	   
	   @Test(priority=5)
		
		public void verifyTotalAmountInMyInvoices() {
			
			new ProductsPurchasePage(driver);
			MemberDashboard mm=new MemberDashboard(driver); 
			mm.goToMyDashboard().click();
			
			waitForElementToVisible(By.xpath("//a[contains(text(),'INV')]"));
			mm.getClickOnInvoiceNo().click();
			waitForElementToVisible(By.xpath("//span[text()='Invoice Total']/parent::td//following-sibling::td"));
			Assert.assertEquals(mm.getInvoiceTotal().getText(),Amount);
			
		}
	   
	   @Test(priority=6)

	   public void verifyInvoiceAmountInMyledger() {
	   	MyBillingPage billing=new MyBillingPage(driver);
	   	MemberDashboard mm=new MemberDashboard(driver);
	   	String AmountOnInvoicePopUp=mm.getInvoiceTotal().getText();
	   	    mm.getclickonClosebutton().click();
	   		billing.goToMyBilling().click();
	   	    billing.goToLedger().click();
	   	
	   	Assert.assertEquals(billing.getDebitAmountInLedger().getText(),AmountOnInvoicePopUp);
	   }

	   @Test(priority=7)
		
		public void verifyTotalAmountInMyReceipts() {
			
			ProductsPurchasePage pp=new ProductsPurchasePage(driver);
			MemberDashboard mm=new MemberDashboard(driver); 
			MyBillingPage billing=new MyBillingPage(driver);
			String  ReceiptAmountInLedger= billing.getCreditAmountInLedger().getText();
			
			mm.goToMyDashboard().click();
			mm.getSelectReceipts().click();
			mm.getSelectReceiptNumber().click();
			waitForElementToVisible(By.xpath("//span[@class='m-invoice__text']//strong"));
			Assert.assertEquals(mm.getTotalAmountInReceipts().getText(),ReceiptAmountInLedger);
			
	   }
	   
	   
	   @Test(priority=8)

	   public void verifyReceiptAmountInMyBilling() {
	   	
	   	MyBillingPage billing=new MyBillingPage(driver);
	   	MemberDashboard mm=new MemberDashboard(driver);
	       mm.getclickonClosebutton().click();
	       String  ReceiptAmountOnDashboard= mm.getReceiptAmountOnDashboard().getText();
	       
	       
	       billing.goToMyBilling().click();
	       billing.goToReceipts().click();
	       
	   	Assert.assertEquals(billing.getAmountInReceipts().getText(),ReceiptAmountOnDashboard);
	   	
	   	
	   }
	   
	   @Test(priority=9)

	   public void verifyRenewalDateInService() {
		   
		   MyServicesPage page=new MyServicesPage(driver);
			try {
				
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
			System.out.println(page.getServiceRenewalDate().getText());
			Assert.assertEquals(page.getServiceRenewalDate().getText(),renewalDate);
	   }
	   
	   @Test(priority=10)

	   public void verifyBillingCycleInService() {
		   
		   MyServicesPage page=new MyServicesPage(driver);
		   System.out.println(page.getBillingCycle().getText());
		   Assert.assertEquals(page.getBillingCycle().getText(),billingCycle);
		   
	   } 
		   
		   @Test(priority=11)

		   public void verifyFirstPaymentAmountInService() {
			   
			   MyServicesPage page=new MyServicesPage(driver);
			   System.out.println(page.getFirstPaymentAmount().getText());
			   
			   Assert.assertEquals(page.getFirstPaymentAmount().getText(),firstPaymentAmount);
			   
		   }

	@Test(priority = 12)

	public void submitCancellationRequest() throws InterruptedException {

		MyServicesPage page = new MyServicesPage(driver);
		MemberDashboard mm = new MemberDashboard(driver);
		// mm.getclickonClosebutton().click();
		page.getMyServices().click();
		page.getListServices().click();
		page.getAction().click();

		if (page.getSubmitCancellationRequest().getText().equals("Submit Cancellation Request")) {

			page.getSubmitCancellationRequest().click();

			waitForElementToVisible(By.id("cancel_service_type"));
			Select type = new Select(page.getCancelServiceType());
			type.selectByVisibleText("Immediate");

			Select reason = new Select(page.getCancellationReason());
			reason.selectByVisibleText("High prices");

			page.getRemarks().sendKeys("TEST");

			page.getSubmitCancellation().click();

			mm.getclickonOK().click();
			waitForElementToVisible(By.xpath("//div[text()='Your request has been submitted successfully.']"));
			Assert.assertEquals(page.getSuccessCancellationMessage().getText(),
					"Your request has been submitted successfully.");
		}

		else {
			page.getCancellationVoid().click();

			mm.getclickonOK().click();
			waitForElementToVisible(
					By.xpath("//div[contains(text(),'Service Cancellation request cancelled Successfull')]"));
			Assert.assertEquals(page.getCancellationVoidMessage().getText(),
					"Service Cancellation request cancelled Successfully.");
		}
	}

	@AfterTest
	
	public void teardown() {
		//driver.close();
	}

}
