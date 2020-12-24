package Marketplace;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import pageObjects.ProductsPurchasePage;
import resources.base;

public class PurchaseAcronicsCredit extends base {
	String Password = "Password12";
	String TopUpAmount = "100000.00";
	String Amount="$ 390.00";
	
	String ServiceAmount = "$ 650.00";
	String Tenure = "";
	String PaypalEmail="govind@znetlive.com";
	String PaypalPassword="znet@123";
	String QuantityAmount;
	String UpgradeQuantity="7";
	String DowngradeQuantity="5";
	public static String acronicsUsername;

	@BeforeTest

	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("AcronicsURL"));

		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
		LoginPage lp = new LoginPage(driver);
		LandingPage landingpage = new LandingPage(driver);
		landingpage.getLogin().click();
		lp.getUsername().sendKeys(PurchaseAcronicsOffline.username);
	//	lp.getUsername().sendKeys("singh8764");
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

	public void purchaseAcronisWithCredit() throws InterruptedException, AWTException {

		ProductsPurchasePage pp = new ProductsPurchasePage(driver);
		MemberDashboard mm = new MemberDashboard(driver);
		switchToNewWindow();
		driver.get(prop.getProperty("AcronicsURL"));

		 for (int i = 1;i<5;i++) {
				
				pp.addQuantity().click();
				
			}
			
	       acronicsUsername=randomHostName();
			pp.getDesiredUserName().sendKeys(acronicsUsername);
			
			pp.getAcronicsSelectButton().click();
			
			
			waitForElementToVisible(By.id("ac_valid_msg"));
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

	public void verifyUserNameInMyAccount() throws InterruptedException {

		ProductsPurchasePage pp = new ProductsPurchasePage(driver);
		waitForElementToVisible(By.xpath("//div[@class='product_details']"));
		String HostONUI = pp.getHostnameInOrder().getText();
		boolean ActualHostname = HostONUI.contains(acronicsUsername);
		System.out.println(ActualHostname);

		Assert.assertTrue(ActualHostname);
	}

	@Test(priority = 4)
	public void upgradeTermAndVerifyDate() throws ParseException {

		MyServicesPage page = new MyServicesPage(driver);
		MemberDashboard mm = new MemberDashboard(driver);
		mm.getclickonClosebutton().click();

		// mm.goToMyDashboard().click();
		String dateOnUI = mm.getServiceDueDate().getText();

		waitForElementToVisible(By.linkText("My Services"));
		page.getMyServices().click();
		waitForElementToVisible(By.linkText("List/Search Services"));
		page.getListServices().click();
		waitForElementToVisible(By.xpath("//tr[1]//td[1]//a[1]"));
		page.getServiceName().click();
		page.getRenewalCenter().click();
		page.getTenure().click();
		page.getUpgradeNow().click();

		mm.getclickonOK().click();
		mm.getCreditSummaryNextButton().click();

		mm.goToMyDashboard().click();

		Date oldDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateOnUI);
		String expectedNewDate = renewalDate(oldDate, 2);

		String newDateOnUI = mm.getServiceDueDate().getText();
		Assert.assertEquals(newDateOnUI, expectedNewDate);

	}

	@Test(priority = 5)

	public void renewThisServiceAndVerifyDueDate() throws InterruptedException, ParseException {

		MemberDashboard mm = new MemberDashboard(driver);

		// mm.getclickonClosebutton().click();
		String dateOnUI = mm.getServiceDueDate().getText();
		mm.getServiceDropdown().click();
		mm.getRenewThisService().click();
		waitForElementToVisible(By.xpath("//button[@class='swal2-confirm btn btn-success m-btn m-btn--custom']"));
		mm.getclickonOK().click();
		mm.getCreditSummaryNextButton().click();

		mm.goToMyDashboard().click();

		Date oldDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateOnUI);
		String expectedNewDate = renewalDate(oldDate, 2);

		String newDateOnUI = mm.getServiceDueDate().getText();
		Assert.assertEquals(newDateOnUI, expectedNewDate);
	}

	@Test(priority = 6)

	public void verifyRenewServiceInvoiceStatus() throws ParseException {

		MemberDashboard mm = new MemberDashboard(driver);
		mm.getClickOnInvoiceNo().click();
		waitForElementToVisible(By.xpath("//div[5]//h5[1]//span[1]"));
		Assert.assertEquals(mm.getInvoiceStatus().getText(), "Paid");

	}

	@Test(priority = 7)
	public void verifyServiceAmountInLedger() {
		MyBillingPage billing = new MyBillingPage(driver);
		MemberDashboard mm = new MemberDashboard(driver);
		mm.getclickonClosebutton().click();
		billing.goToMyBilling().click();
		billing.goToLedger().click();
		Assert.assertEquals(billing.getDebitAmountInLedger().getText(), ServiceAmount);
	}

	@Test(priority = 8)

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

	
	
	@Test(priority=9)

	public void upgradeServiceAndVerifyAmountInLedger() throws ParseException, InterruptedException {
		
		MyBillingPage billing=new MyBillingPage(driver);
		MyServicesPage page=new MyServicesPage(driver);
		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		MemberDashboard mm=new MemberDashboard(driver);
		mm.getclickonOK().click();
//		try {
//		
//		waitForElementToVisible(By.linkText("My Services"));
//		page.getMyServices().click();
//		
//		}
//		catch(org.openqa.selenium.TimeoutException e) {
//			waitForElementToVisible(By.linkText("My Services"));
//			page.getMyServices().click();	
//			
//		}
//		
//		waitForElementToVisible(By.linkText("List/Search Services"));
//		page.getListServices().click();
		waitForElementToVisible(By.xpath("//tr[1]//td[1]//a[1]"));
		page.getServiceName().click();
		page.getUpgradeDowngradeCenterAcronics().click();

		
		 page.getAddQuantityAcronics().sendKeys(Keys.BACK_SPACE);
		 
		
			page.getAddQuantityAcronics().sendKeys("2");
			page.getAddToUpgrade().click();
			page.getUpgradeButton().click();
			waitForElementToVisible(By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div"));
		    QuantityAmount=mm.getTotalAmount().getText();
			mm.getclickonNextbutton().click();
			Thread.sleep(3000);
			mm.getclickonOK().click();
			mm.getCreditSummaryNextButton().click();
			 waitForElementToVisible(By.xpath("//a[contains(text(),'My Account')]"));
			   
			 pp.goToMyAccountPage().click();
			
			 billing.goToMyBilling().click();
			    billing.goToLedger().click();
			
			Assert.assertEquals(billing.getDebitAmountInLedger().getText(),QuantityAmount);
		}

		@Test(priority=10)
		public void verifyUpgradeQuantityInMyServices() {
			MyServicesPage page=new MyServicesPage(driver);
			 page.getMyServices().click();
		     page.getListServices().click();
				Assert.assertEquals(page.getQuantityOnUI().getText(),UpgradeQuantity);
				
		}
		
		@Test(priority=11)
		public void downgradeServiceAndVerifyAmountInLedger() throws InterruptedException {
			
			MyBillingPage billing=new MyBillingPage(driver);
			MyServicesPage page=new MyServicesPage(driver);
			ProductsPurchasePage pp=new ProductsPurchasePage(driver);
			MemberDashboard mm=new MemberDashboard(driver);

			waitForElementToVisible(By.xpath("//tr[1]//td[1]//a[1]"));
			page.getServiceName().click();
			page.getUpgradeDowngradeCenterAcronics().click();

			
			 page.getAddQuantityAcronics().sendKeys(Keys.BACK_SPACE);
			 
			
				page.getAddQuantityAcronics().sendKeys("2");
			
				page.getAddToDowngrade().click();
				
				
				waitForElementToVisible(By.xpath("//div[contains(@class,'m-portlet m-portlet--head-sm m-portlet--bord ered m-portl et--unair')]//form//strong//span"));
			   String QuantityAmountOnUI=page.getQuantityAmountAcronics().getText();
			  System.out.println(QuantityAmountOnUI);
				page.getDowngradeButton().click();
				waitForElementToVisible(By.id("swal2-content"));
				
				System.out.println(page.getDowngradeSuccessMesaage().getText());
				mm.getclickonOK().click();
				 billing.goToMyBilling().click();
				 billing.goToLedger().click();
				// waitForElementToVisible(By.xpath("//tr[td[span[@class='m--font-bolder']]]/preceding-sibling::tr[2]/td[6]"));
				 Assert.assertEquals(page.getCreditAmountInLedger().getText(),QuantityAmountOnUI);
				  
			
		}

		@Test(priority=12)
		public void verifyDowngradeQuantityInMyServices() {
			MyServicesPage page=new MyServicesPage(driver);
			 page.getMyServices().click();
		     page.getListServices().click();
				Assert.assertEquals(page.getQuantityOnUI().getText(),DowngradeQuantity);
				
		
			
		}
	
	@AfterTest
	
	public void teardown() {
		//driver.close();
	}

}
