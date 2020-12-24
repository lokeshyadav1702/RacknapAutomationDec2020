package Marketplace;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import pageObjects.MySupportPage;
import pageObjects.ProductsPurchasePage;
import resources.base;

public class PurchaseBulkOrderCredit extends base {
	String Password = "Password12";
	String TopUpAmount = "300000.00";
	
	String PaypalEmail="govind@znetlive.com";
	String PaypalPassword="znet@123";
	
	
	
	public static String HostName;
	public static String username;
	String Amount="$ 128,069.74";
	String domainName;
	String Domain;
	String domainExpEmailLog="Qa8 - Successful Registration of Domain";
	String expEmailLog ="Your Office 365 Account is Set up and Ready to Use - Qa8";

	@BeforeTest

	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("ServerURL"));

		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
		LoginPage lp = new LoginPage(driver);
		LandingPage landingpage = new LandingPage(driver);
		landingpage.getLogin().click();
		lp.getUsername().sendKeys(PurchaseBulkOrderOffline.username);
	   // lp.getUsername().sendKeys("singh5596");
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

	public void purchaseBulkOrderWithCredit() throws InterruptedException, AWTException {

		ProductsPurchasePage pp = new ProductsPurchasePage(driver);
		MemberDashboard mm = new MemberDashboard(driver);
		switchToNewWindow();
		driver.get(prop.getProperty("ServerURL"));
		pp.getHostnameTab().click();
		HostName=randomHostName();
		pp.getHostname().sendKeys(HostName);
		MouseClick();
		
	
		pp.getAddToCartButton().click();
		driver.get(prop.getProperty("DomainURL"));
		
		domainName= randomDomainName();
		pp.getTypeDomainName().sendKeys(domainName);
		pp.getSearchButton().click();
		
		
		try{
			waitForElementToBeClickable(By.xpath("//div[@class='all-ex']//div[2]//label[1]//span[contains(@class,'check-indicator')]"));
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click()", pp.getSelectFilter());
	    
		
		}
		
		catch(ElementClickInterceptedException e)
		
		{
		
			waitForElementToBeClickable(By.xpath("//div[@class='all-ex']//div[2]//label[1]//span[contains(@class,'check-indicator')]"));

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click()", pp.getSelectFilter());
			
		}
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", pp.getapplyButton());
		
		Thread.sleep(5000);
		
		Domain=pp.getDomainName().getText();
		System.out.println(Domain);
		//waitForElementToVisible(By.xpath("//div[@class='d-results']/div[1]//button"));
		pp.getSelect().click();
		
		Thread.sleep(3000);
		//waitForElementToVisible(By.xpath("//a[contains(@class,'addtocart bnt-gradient load_popup')]"));
		jse.executeScript("arguments[0].click()", pp.getContinueToCart());
		pp.getAddToCartDomain().click();
		driver.get(prop.getProperty("VPSCloudURL"));
		pp.getVPSHostnameTab().click();
		HostName=randomHostName();
		pp.getHostname().sendKeys(HostName);
		MouseClick();
		
		
		pp.addToCartVPS().click();
		driver.get(prop.getProperty("EmailURL"));
		
       for (int i = 1;i<5;i++) {
			
			pp.addQuantity().click();
			
		}

      
		pp.addToCart().click();
		waitForElementToVisible(By.xpath("//button[@class='confirm btn btn-lg btn-warning']"));
		pp.getClickOnAcceptAlert().click();

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




	  @Test(priority=3)

	   public void verifyOrderAmountInMyBilling() {
	   	    MemberDashboard mm=new MemberDashboard(driver);
	   		MyBillingPage billing=new MyBillingPage(driver);
			
	   		mm.getclickonClosebutton().click();
	   		
	   		String OrderamountOnDashboard=mm.getOrderAmountOnDashboard().getText();
	   		billing.goToMyBilling().click();
	   		billing.goToOrders().click();
	   		
	   		Assert.assertEquals(billing.getAmountInOrders().getText(),OrderamountOnDashboard);
	   	
	   }
	   
	   
	   @Test(priority=4)
		
		public void verifyTotalAmountInMyInvoices() {
			
			MemberDashboard mm=new MemberDashboard(driver); 
			mm.goToMyDashboard().click();
			
			waitForElementToVisible(By.xpath("//a[contains(text(),'INV')]"));
			mm.getClickOnInvoiceNo().click();
			waitForElementToVisible(By.xpath("//span[text()='Invoice Total']/parent::td//following-sibling::td"));
			Assert.assertEquals(mm.getInvoiceTotal().getText(),Amount);
			
		}
	   
	   @Test(priority=5)

	   public void verifyInvoiceAmountInMyledger() {
	   	MyBillingPage billing=new MyBillingPage(driver);
	   	MemberDashboard mm=new MemberDashboard(driver);
	   	String AmountOnInvoicePopUp=mm.getInvoiceTotal().getText();
	   	    mm.getclickonClosebutton().click();
	   		billing.goToMyBilling().click();
	   	    billing.goToLedger().click();
	   	
	   	Assert.assertEquals(billing.getDebitAmountInLedger().getText(),AmountOnInvoicePopUp);
	   }

	   @Test(priority=6)
		
		public void verifyTotalAmountInMyReceipts() {
			
			MemberDashboard mm=new MemberDashboard(driver); 
			MyBillingPage billing=new MyBillingPage(driver);
			String  ReceiptAmountInLedger= billing.getCreditAmountInLedger().getText();
			
			mm.goToMyDashboard().click();
			mm.getSelectReceipts().click();
			for(int i=0; i<=2;i++){
				  try{
					  mm.getSelectReceiptNumber().click();
				     break;
				  }
				  catch(StaleElementReferenceException e){
				     System.out.println("Exception found");
				  }
				}
		//	mm.getSelectReceiptNumber().click();
			waitForElementToVisible(By.xpath("//span[@class='m-invoice__text']//strong"));
			Assert.assertEquals(mm.getTotalAmountInReceipts().getText(),ReceiptAmountInLedger);
		
	   }
	   
	   
	   @Test(priority=7)

	   public void verifyReceiptAmountInMyBilling() {
	   	
	   	MyBillingPage billing=new MyBillingPage(driver);
	   	MemberDashboard mm=new MemberDashboard(driver);
	       mm.getclickonClosebutton().click();
	       String  ReceiptAmountOnDashboard= mm.getReceiptAmountOnDashboard().getText();
	       
	       
	       billing.goToMyBilling().click();
	       billing.goToReceipts().click();
	       
	   	Assert.assertEquals(billing.getAmountInReceipts().getText(),ReceiptAmountOnDashboard);
	   	
	   	
	   }
	   @Test(priority=8)
	   public void verifyDomainServiceEmailLogs() throws InterruptedException {
		   
			MySupportPage sp=new MySupportPage(driver);
	      sp.goToMySupport().click();
			sp.getEmailLogs().click();
			Thread.sleep(3000);
	      boolean flag = false;
			if (sp.getEmailSubjectDomain().getText().contains(domainExpEmailLog)) {
				flag = true;
				
				System.out.println("Yeah...Successful Registration of Domain");
			}
			
			Assert.assertTrue(flag, "Your domain is not registered successfully");
		}
	   @Test(priority=9)
	     public void verifyEmailServiceEmailLogs() throws InterruptedException {
		   MySupportPage sp=new MySupportPage(driver);
			
	        boolean flag = false;
			if (sp.getEmailSubject().getText().equalsIgnoreCase(expEmailLog)) {
				flag = true;
				
				System.out.println("Yeah... Your Office 365 Account is Set up and Ready to Use");
			}
			
			Assert.assertTrue(flag, "Your Office 365 Account is not set up");
		}

		
	
	@AfterTest
	
	public void teardown() {
		//driver.close();
	}

}
