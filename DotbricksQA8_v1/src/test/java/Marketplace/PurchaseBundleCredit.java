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

public class PurchaseBundleCredit extends base {
	String Password = "Password12";
	String TopUpAmount = "100000.00";
	
	String PaypalEmail="govind@znetlive.com";
	String PaypalPassword="znet@123";
	public static String acronicsUsername;
	
	
	public static String HostName;
	public static String username;
	String Amount="$ 590.05";
	String domainName;
	String Domain;

	String acronisExpEmailLog="Your Acronis Account is Set up and Ready to Use - Qa8";
	String expEmailLog ="Your Office 365 Account is Set up and Ready to Use - Qa8";

	@BeforeTest

	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("BundleURL"));

		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
		LoginPage lp = new LoginPage(driver);
		LandingPage landingpage = new LandingPage(driver);
		landingpage.getLogin().click();
	    lp.getUsername().sendKeys(PurchaseBundleOffline.username);
	  // lp.getUsername().sendKeys("singh962");
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

	public void purchaseBundleWithCredit() throws InterruptedException, AWTException {

		ProductsPurchasePage pp = new ProductsPurchasePage(driver);
		MemberDashboard mm = new MemberDashboard(driver);
		switchToNewWindow();
		driver.get(prop.getProperty("BundleURL"));
		acronicsUsername=randomHostName();
		pp.getbundleAcronicsUsername().sendKeys(acronicsUsername);
		pp.getAcSelectButton().click();
		waitForElementToVisible(By.xpath("//span[contains(text(),'Entered username is available.')]"));
	
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()",pp.getSelectDedicatedServer());
		pp.getExpandHostname().click();
		HostName=randomHostName();
		pp.getHostnameBundle().sendKeys(HostName);
		//pp.getSelectEmailSolution().click();
		MouseClick();
		//pp.getOKButtonHostname().click();
//		pp.getClickOnAcceptAlert().click();
	
		Thread.sleep(3000);
		pp.getAddToCartButton().click();
	

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
	   public void verifyAcronisServiceEmailLogs() throws InterruptedException {
		   
			MySupportPage sp=new MySupportPage(driver);
	      sp.goToMySupport().click();
			sp.getEmailLogs().click();
			Thread.sleep(3000);
	      boolean flag = false;
			if (sp.getAcronisEmailServiceLog().getText().contains(acronisExpEmailLog)) {
				flag = true;
				
				System.out.println("Yeah... Your Acronis Account is Set up and Ready to Use");
			}
			
			Assert.assertTrue(flag, "Your Acronis Account is not set up");
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
