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
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
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
import pageObjects.SignUpPage;
import resources.base;

public class PurchaseDomainCredit extends base{
	
	
	String Password = "Password12";
	String TopUpAmount = "100000.00";
	String domainName;
	String Domain;
	
	String Amount="$ 118.00";
	String ServiceAmount = "$ 472.00";
	String Tenure = "";
	String PaypalEmail="govind@znetlive.com";
	String PaypalPassword="znet@123";

	@BeforeTest

	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("DomainURL"));

		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
		LoginPage lp = new LoginPage(driver);
		LandingPage landingpage = new LandingPage(driver);
		landingpage.getLogin().click();
		lp.getUsername().sendKeys(PurchaseDomainOffline.username);
		//lp.getUsername().sendKeys("singh3958");
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

	public void purchaseDomainWithCredit() throws InterruptedException, AWTException {

		ProductsPurchasePage pp = new ProductsPurchasePage(driver);
	
		
		 Actions action =new Actions(driver);
			
			
		   switchToNewWindow();
		
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
			pp.getSelect().click();
			
			Thread.sleep(3000);

			jse.executeScript("arguments[0].click()", pp.getContinueToCart());
			pp.getAddToCartDomain().click();
			
	
	
		

		try {
			
			Thread.sleep(5000);
			pp.getClickOnNextButton().click();
			waitForPageToLoad();

			waitForElementToVisible(By.xpath("//input[@class='bnt-gradient paynow']"));
			pp.getPaymentSummaryNextButton().click();
			waitForPageToLoad();
			waitForElementToVisible(By.xpath("//div[@class='bay-bill-balance']"));

			System.out.println(pp.getCreditMessage().getText());
		}

		catch (Exception e) {
			Thread.sleep(5000);
			pp.getClickOnNextButton().click();
			waitForPageToLoad();
			waitForElementToVisible(By.xpath("//input[@class='bnt-gradient paynow']"));

			pp.getPaymentSummaryNextButton().click();

			waitForPageToLoad();

			waitForElementToVisible(By.xpath("//div[@class='bay-bill-balance']"));

			System.out.println(pp.getCreditMessage().getText());

		}

		pp.goToMyAccountPage().click();
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
		waitForElementToVisible(By.xpath("//div[@class='product_details']"));
		String DomainONUI=pp.getHostnameInOrder().getText();
	    boolean ActualDomainname=	DomainONUI.contains(Domain);
	    System.out.println(ActualDomainname);
	   
	    Assert.assertTrue(ActualDomainname);

	}
	  @Test(priority=3)
		
		public void verifyTotalAmountInMyOrders() {
			MemberDashboard mm=new MemberDashboard(driver);
			waitForElementToVisible(By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div"));
			Assert.assertEquals(mm.getTotalAmount().getText(),Amount);
			
			
}
	  
	  @Test(priority = 4)
		public void upgradeTermAndVerifyDate() throws ParseException {

			MyServicesPage page = new MyServicesPage(driver);
			MemberDashboard mm = new MemberDashboard(driver);
			mm.getclickonClosebutton().click();

			 mm.goToMyDashboard().click();
			String dateOnUI = mm.getServiceDueDate().getText();

			waitForElementToVisible(By.linkText("My Services"));
			page.getMyServices().click();
			waitForElementToVisible(By.linkText("List/Search Services"));
			page.getListServices().click();
			waitForElementToVisible(By.xpath("//tr[1]//td[1]//a[1]"));
			page.getServiceName().click();
			//page.getBillingCenter().click();
			page.getDomainServiceTenure().click();
			page.getDomainUpgradeButton().click();

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

		

		@AfterTest
		
		public void teardown() {
			//driver.close();
		}

	}

