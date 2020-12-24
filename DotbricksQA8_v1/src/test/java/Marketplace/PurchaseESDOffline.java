package Marketplace;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.MemberDashboard;
import pageObjects.MyBillingPage;
import pageObjects.MySupportPage;
import pageObjects.ProductsPurchasePage;
import pageObjects.SignUpPage;
import resources.base;

public class PurchaseESDOffline extends base {
	public static Logger log=LogManager.getLogger(base.class.getName());
	    String ProductName="Microsoft ESD - Office 365 Home";
		public static String username;
		String Amount="$ 129.80";
	
	String PaypalEmail="govind@znetlive.com";
	String PaypalPassword="znet@123";
    String Currency="$";

	
	
	@BeforeTest
	public void initialize() throws IOException{
		driver=initializeDriver();
	
		driver.get(prop.getProperty("ESDURL"));
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
		
	}
	
	@Test(dataProvider="getData",priority=1)
	
	
	public void purchaseESDOffline(String email,String password,String ConfirmPassword,String FirstName,String LastName,String CompanyName,
			String Address1,String City,String MobileNumber,String Zipcode,String VAT,String Nationlity,String AadharNumber,String GSTNumber,String PANNumber,String GreenCardID,String PaypalEmail,String PaypalPassword )
throws IOException, InterruptedException, AWTException {
		
		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		SignUpPage sign=new SignUpPage(driver);
		
		 
		pp.addToCart().click();
	   pp.clickOnNext().click();
		pp.creatAccount().click();
		username= randomUsername(); 
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
	//	waitForElementToVisible(By.id("make_payment"));
		try {
			Thread.sleep(5000);
			pp.getClickOnNextButton().click();
			
			}
			
			catch(NoSuchElementException e){
				
				Thread.sleep(5000);
				pp.getClickOnNextButton().click();
				
			}
			
		
		//pp.getClickOnAcceptButton().click();
		
		
		
		pp.getSelectBanktransfer().click();
		waitForElementToVisible(By.xpath("//button[@class='confirm btn btn-lg btn-warning']"));
		
		pp.getClickOnAcceptPaymentAlert().click();
		waitForElementToVisible(By.xpath("//div[@class='offline-success offline_success_message']"));
		System.out.println(pp.getOfflineSuccessMessage().getText());

		
	}
		
		
	
	@Test(priority=2)
	
	public void verifyProductNameInMyAccount() throws InterruptedException {
		
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
		String productOnUI=pp.getHostnameInOrder().getText();
	    boolean ActualProductName=	productOnUI.contains(ProductName);
	    System.out.println(ActualProductName);
	   
	    Assert.assertTrue(ActualProductName);
	}
	
	
	@Test(priority=3)
	
	public void verifyTotalAmountInMyOrders() {
		MemberDashboard mm=new MemberDashboard(driver);
		waitForElementToVisible(By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div"));
		Assert.assertEquals(mm.getTotalAmount().getText(),Amount);
		
		
	}
	
	@Test(priority=4)
	
	public void payPendingOrderPayment() throws InterruptedException {
		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		MemberDashboard mm=new MemberDashboard(driver);
		mm.getclickonNextbutton().click();
		Thread.sleep(3000);
		mm.getclickonOK().click();
		waitForElementToVisible(By.xpath("//strong[contains(text(),'Paypal')]"));
		mm.getclickonPaypalOnline().click();
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
	  
	
		waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));

        Assert.assertEquals(pp.getSuccessMessage().getText(), "Your payment has been processed successfully");
		}
		
		catch (UnreachableBrowserException e) {
			
			waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
			
	        Assert.assertEquals(pp.getSuccessMessage().getText(), "Your payment has been processed successfully");
			}
		
		
	}
	
	@Test(priority=5)
	
	public void verifyTotalAmountInMyInvoices() {
		
		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		MemberDashboard mm=new MemberDashboard(driver); 
		
		pp.goToMyAccountPage().click();
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
	
	
	
	@AfterTest
	
	public void teardown() {
		//driver.close();
	}

	@DataProvider(name="getData")
	
	public Object[][] getData(){
		Object[][] data=new Object[1][18];
		data[0][0]="tarun@microsoft.com";
		data[0][1]="Password12";
		data[0][2]="Password12";
		data[0][3]="Tanmay";
		data[0][4]="Singh";
		data[0][5]="Znet Technologies";
		data[0][6]="Chitrakoot";
		data[0][7]="Jaipur";
		data[0][8]="9782437496";
		data[0][9]="302021";
		data[0][10]="5%";
		data[0][11]="Indian";
		
		data[0][12]="3373345622";
		data[0][13]="2344343";
		data[0][14]="ETTPS3532H";
		data[0][15]="283092039";
		data[0][16]="govind@znetlive.com";
		data[0][17]="znet@123";
		
		
		return data;
		
	}
	

	}
