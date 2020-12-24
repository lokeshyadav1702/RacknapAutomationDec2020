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
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.MemberDashboard;
import pageObjects.MyBillingPage;
import pageObjects.ProductsPurchasePage;
import resources.base;

public class PurchaseMSOffice365Credit extends base {
	
	String Amount="$ 126,865.00";
	String TopUpAmount="300000.00";
	String ProductName="Office 365 Setup";
	String TotalPrice;
	String PaypalEmail="govind@znetlive.com";
	String PaypalPassword="znet@123";
	String Password="Password12";
	String URL="https://manage.qa8.dotbricks.net/";
 
	@BeforeTest

	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
		LoginPage lp=new LoginPage(driver);
		LandingPage landingpage=new LandingPage(driver);
		landingpage.getLogin().click();
	//	landingpage.getCustomerLogin().click();
		lp.getUsername().sendKeys(PurchaseMSOffice365Online.username);
		//lp.getUsername().sendKeys("singh9428");
		lp.getPassword().sendKeys(Password);
		lp.getSignIn().click();

		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		//waitForElementToAppear(By.xpath("//a[contains(text(),'My Account')]"));
		pp.goToMyAccountPage().click();
		
		}
	
		
	
		
	
	@Test(dataProvider="getData",priority=2)
	
	
	
	 
	public void purchaseOrderWithCredit(String username1,String password) throws InterruptedException {
		
		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		MemberDashboard mm=new MemberDashboard(driver);
		MyBillingPage billing=new MyBillingPage(driver);
		billing.goToMyBilling().click();
		billing.goToPlaceNewOrder().click();
		switchToNewWindow();
//		landingpage.getLogin().click();
//		landingpage.getCustomerLogin().click();
//		lp.getUsername().sendKeys(PurchaseService.username);
//		lp.getPassword().sendKeys(password);
//		lp.getSignIn().click();
	//	Thread.sleep(10000);
	//	driver.switchTo().newWindow(WindowType.TAB);

		// below code will navigate you to your desirable Url 
	//	driver.get("https://marketplace.qa2.dotbricks.net/");
        pp.clickProduct().click();
		pp.selectProduct().click();
		
		waitForElementToVisible(By.linkText("View Business Plans"));
		pp.viewBusinessPlan().click();
		//Thread.sleep(3000);
		waitForElementToVisible(By.xpath("//a[@class='planone_chnagedurl primary-border white-text primary-color btn-hovers font-16 padding-top-bottom-5 display-inline-block padding-right-left-20 text-center margin-bottom-20 margin-top-10']"));
		pp.orderNow().click();
		
		for (int i = 1;i<5;i++) {
			
			pp.addQuantity().click();
			
		}
		
		pp.addToCart().click();
	//	TotalPrice=pp.getTotalPriceOnOrderSummary().getText();
		//waitForElementToVisible(By.id("make_payment"));
		try {
			Thread.sleep(5000);
			pp.getClickOnNextButton().click();
			
			}
			
			catch(NoSuchElementException e){
				
				Thread.sleep(5000);
				pp.getClickOnNextButton().click();
				
			}
			
		
//		waitForElementToVisible(By.id("adjust_with_credit"));
//		pp.getSelectCredit().click();
	//	waitForElementToVisible(By.xpath("//button[@class='confirm btn btn-lg btn-warning']"));
		try {
			waitForPageToLoad();
			//pp.getClickOnAcceptPaymentAlert().click();
			waitForElementToVisible(By.xpath("//input[@class='bnt-gradient paynow']"));
			pp.getPaymentSummaryNextButton().click();
			waitForPageToLoad();
			waitForElementToVisible(By.xpath("//div[@class='bay-bill-balance']"));
			//Thread.sleep(120000);
		
		//	waitForElementToDisappear(By.id("span_load"));
			
			System.out.println("Try block");
			System.out.println(pp.getCreditMessage().getText());
		}	
		//pp.goToMyAccountPage().click();
				
				
//				pp.goToMyOrder().click();
//				pp.getSelectAllOrders().click();
//				pp.getClickonOrderNo().click();
//				
//				waitForElementToVisible(By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div"));
//				Assert.assertEquals(mm.getTotalAmount().getText(),Amount);
			// Assert.assertEquals(pp.getCreditMessage().getText(), "Thank you for making the payment. Your available credit is ? 199,999,487,422.55 and cash available is ? 512,577.45 DR");

		 catch (Exception e) {
			 waitForPageToLoad();
			 waitForElementToVisible(By.xpath("//input[@class='bnt-gradient paynow']"));
			//pp = new ProductsPurchasePage(driver);
			//waitForElementToVisible(By.xpath("//button[@class='confirm btn btn-lg btn-warning']"));
			//pp.getClickOnAcceptPaymentAlert().click();
		     pp.getPaymentSummaryNextButton().click();
			 System.out.println("CAtch block");
			 //refresh();
			 //Thread.sleep(90000);
              waitForPageToLoad();
		//	waitForElementToDisappear(By.id("span_load"));
//
         	waitForElementToVisible(By.xpath("//div[@class='bay-bill-balance']"));
			
			System.out.println(pp.getCreditMessage().getText()); 
			
		 }
			
			// Assert.assertEquals(pp.getCreditMessage().getText(), "Thank you for making the payment. Your available credit is ? 199,999,487,422.55 and cash available is ? 512,577.45 DR");
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
			
			waitForElementToVisible(By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div"));
			Assert.assertEquals(mm.getTotalAmount().getText(),Amount);
			

		}
		
		
	
		@Test(priority=3)
		
		public void verifyOrderInMyAccount() throws InterruptedException {
			
			ProductsPurchasePage pp=new ProductsPurchasePage(driver);
			
			//waitForElementToAppear(By.xpath("//a[contains(text(),'My Account')]"));
//			pp.goToMyAccountPage().click();
//			
//			
//			pp.goToMyOrder().click();
//			pp.getSelectAllOrders().click();
//			pp.getClickonOrderNo().click();
			waitForElementToVisible(By.xpath("//b[contains(text(),'Office 365 Setup')]"));
		    Assert.assertEquals(pp.getProductName().getText(),ProductName);
		}
//		@Test(priority=3)
//		
//		public void VerifyTotalAmountInMyOrders() {
//			MemberDashboard mm=new MemberDashboard(driver);
//			waitForElementToVisible(By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div"));
//			Assert.assertEquals(mm.getTotalAmount().getText(),Amount);
//			
			
		//}
	   @Test(priority=4)
		
		public void verifyTotalAmountInMyInvoices() {
			
			ProductsPurchasePage pp=new ProductsPurchasePage(driver);
			MemberDashboard mm=new MemberDashboard(driver); 
			String AmountonOrderPopUp=mm.getTotalAmount().getText();
			mm.getclickonClosebutton().click();
			mm.getclickonInvoices().click();
			//waitForElementToVisible(By.xpath("//a[@class='dropdown-item active clearfix recent_invoice_list']"));
			mm.getSelectAllInvoices().click();
			waitForElementToVisible(By.xpath("//a[contains(text(),'INV')]"));
			mm.getClickOnInvoiceNo().click();
			waitForElementToVisible(By.xpath("//span[text()='Invoice Total']/parent::td//following-sibling::td"));
			Assert.assertEquals(mm.getInvoiceTotal().getText(),AmountonOrderPopUp);
			
		}
	   @Test(priority=1)
		
		public void addTopUpAmountAndVerifyInReceipt() throws InterruptedException, AWTException {

			ProductsPurchasePage pp=new ProductsPurchasePage(driver);
			MemberDashboard mm=new MemberDashboard(driver); 
			MyBillingPage billing=new MyBillingPage(driver);
			//mm.getclickonClosebutton().click();
			mm.getTopUpButton().click();
			mm.getAmount().sendKeys(TopUpAmount);
			MouseClick();
			waitForElementToVisible(By.xpath("//strong[contains(text(),'Paypal')]"));
			mm.getclickonPaypalOnline().click();
			Thread.sleep(3000);
			mm.getclickonOK().click();
			
	//		try {
			mm.getclickonPayNowButton().click();
//			refresh();
         	Thread.sleep(10000);
//			//waitForElementToVisible(By.xpath("//a[text()='Log In']"));
//			pp.getLoginPaypal().click();
//			}
//			catch (UnreachableBrowserException e) {
//				mm.getclickonPayNowButton().click();
//			
//				refresh();
//				Thread.sleep(40000);
//				//waitForElementToVisible(By.xpath("//a[text()='Log In']"));
//				pp.getLoginPaypal().click();
//				
//			}
//			
//			Thread.sleep(10000);
			//pp.getAcceptCookies().click();
		   // waitForElementToVisible(By.id("email"));
			
			pp.getPaypalEmail().sendKeys("govind@znetlive.com");
			pp.getSubmitEmail().click();
			pp.getPaypalPassword().sendKeys("znet@123");
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
	   
	   @Test(priority=5)
	   
	   public void renewThisServiceAndVerifyDueDate() throws InterruptedException, ParseException {
		   
		   ProductsPurchasePage pp=new ProductsPurchasePage(driver);
			MemberDashboard mm=new MemberDashboard(driver);
			
            mm.getclickonClosebutton().click();
			String dateOnUI = mm.getServiceDueDate().getText();
            mm.getServiceDropdown().click();
			mm.getRenewThisService().click();
			waitForElementToVisible(By.xpath("//button[@class='swal2-confirm btn btn-success m-btn m-btn--custom']"));
			mm.getclickonOK().click();
			mm.getCreditSummaryNextButton().click();
			
			//waitForElementToVisible(By.xpath("//strong[contains(text(),'PayPal The Online Payment Gateway of World')]"));
			//mm.getclickonPaypalOnline().click();
		//	mm.getclickonOK().click();
			
//			try {
	//		mm.getclickonPayNowButton().click();
			//Thread.sleep(50000);
//			refresh();
//			Thread.sleep(50000);
//			//waitForElementToVisible(By.xpath("//a[text()='Log In']"));
//			pp.getLoginPaypal().click();
//			}
//			catch (UnreachableBrowserException e) {
//				mm.getclickonPayNowButton().click();
//				//Thread.sleep(50000);
//				refresh();
//				Thread.sleep(50000);
//				//waitForElementToVisible(By.xpath("//a[text()='Log In']"));
//				pp.getLoginPaypal().click();
//				
//			}
//			
//			Thread.sleep(10000);
//			pp.getAcceptCookies().click();
		   // waitForElementToVisible(By.id("email"));
//			
//			pp.getPaypalEmail().sendKeys(PaypalEmail);
//			pp.getSubmitEmail().click();
//			pp.getPaypalPassword().sendKeys(PaypalPassword);
//			pp.getPaypalLogin().click();
//
//			try {
//		     Thread.sleep(10000);
//			pp.getClickContinue().click();
//			Thread.sleep(30000);
////			waitForPageToLoad();
//	  //	waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
//			
//			pp.getClosePopUp().click();
//			//waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
//			//System.out.println(pp.getSuccessMessage().getText());
//	      //  Assert.assertEquals(pp.getSuccessMessage().getText(), "Your payment has been processed successfully");
//			}
//			
//			catch (UnreachableBrowserException e) {
//				Thread.sleep(10000);
//				pp.getClickContinue().click();
////				waitForPageToLoad();
////				waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
//				Thread.sleep(100000);
//				pp.getClosePopUp().click();
//			}	
			     mm.goToMyDashboard().click();
				  
				Date oldDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateOnUI);
				String expectedNewDate = renewalDate(oldDate, 10);
				
			    String newDateOnUI = mm.getServiceDueDate().getText();
				Assert.assertEquals(newDateOnUI, expectedNewDate);
				
//				waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
//				//System.out.println(pp.getSuccessMessage().getText());
//		        Assert.assertEquals(pp.getSuccessMessage().getText(), "Your payment has been processed successfully");
				}
			
			
	   
	   
	   @Test(priority=6)
	   
	   public void verifyRenewServiceInvoiceStatus() throws ParseException {
		   
		   ProductsPurchasePage pp=new ProductsPurchasePage(driver);
			MemberDashboard mm=new MemberDashboard(driver); 
			mm.getClickOnInvoiceNo().click();
			waitForElementToVisible(By.xpath("//div[5]//h5[1]//span[1]"));
			Assert.assertEquals(mm.getInvoiceStatus().getText(),"Paid");


			
	   }
		
	   @AfterTest
		
		public void teardown() {
			//driver.quit();
		}


	   @DataProvider(name="getData")
		
		public Object[][] getData(){
			Object[][] data=new Object[1][2];
			data[0][0]="singh5409";
			data[0][1]="Password12";
			return data;
}
}