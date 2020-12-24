package Marketplace;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MemberDashboard;
import pageObjects.MyBillingPage;
import pageObjects.MySupportPage;
import pageObjects.ProductsPurchasePage;
import pageObjects.SignUpPage;
import resources.base;

public class PurchaseMSOffice365Online extends base {
	public static Logger log=LogManager.getLogger(base.class.getName());
	String Amount="$ 126,865.00";
	String AmountOnInvoicePopUp;
	String ProductName="Office 365 Setup";
	String expEmailLog ="Your Office 365 Account is Set up and Ready to Use - Qa8";
	public static String username;
	String URL="https://manage.qa8.dotbricks.net/";
	String Currency="$";
	String adminUsername="demoadmin@racknap.com";
	String adminPassword="abcd5678";
	

	@BeforeTest
	
	public void initialize() throws IOException{
		driver=initializeDriver();
		driver.get(prop.getProperty("url"));
	
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
	}
	
	@Test(dataProvider="getData",priority=1)
	
	
	public void purchaseOrder(String email,String password,String ConfirmPassword,String FirstName,String LastName,String CompanyName,
			String Address1,String City,String MobileNumber,String Zipcode,String VAT,String Nationlity,String AadharNumber,String GSTNumber,String PANNumber,String GreenCardID,String PaypalEmail,String PaypalPassword ) throws IOException, InterruptedException {
		
		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		SignUpPage sign=new SignUpPage(driver);
		pp.clickProduct().click();
		pp.selectProduct().click();
		
		//ScrollPage();
		waitForElementToVisible(By.linkText("View Business Plans"));
		pp.viewBusinessPlan().click();
		waitForElementToVisible(By.xpath("//a[@class='planone_chnagedurl primary-border white-text primary-color btn-hovers font-16 padding-top-bottom-5 display-inline-block padding-right-left-20 text-center margin-bottom-20 margin-top-10']"));

		pp.orderNow().click();
		
		for (int i = 1;i<5;i++) {
			
			pp.addQuantity().click();
			
		}
		
		String Domain=randomDomainName();
		
		pp.enterDomain().sendKeys(Domain);
		
		pp.selectButton().click();
		
		waitForElementToVisible(By.id("ms_valid_msg"));
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
		//sign.getVAT().sendKeys(VAT);
//		Select nationality=new Select(sign.getSelectNationality());
//		nationality.selectByVisibleText(Nationlity);
//		sign.getAadharnumber().sendKeys(AadharNumber);
//		sign.getGSTnumber().sendKeys(GSTNumber);
//		sign.getPANnumber().sendKeys(PANNumber);
//		sign.getGreenCardID().sendKeys(GreenCardID);
		sign.getSelectCheckbox().click();
		sign.getCreateAccountbutton().click();
		
		//waitForElementToAppear(By.id("make_payment"));
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
		pp.getSelectPaymentMode().click();
		waitForElementToVisible(By.xpath("//button[@class='confirm btn btn-lg btn-warning']"));
		
		pp.getClickOnAcceptPaymentAlert().click();
		waitForElementToVisible(By.id("submit"));
	//	try {
		pp.getClickPayNow().click();
//		refresh();
//		Thread.sleep(40000);
//		//waitForElementToVisible(By.xpath("//a[text()='Log In']"));
//		pp.getLoginPaypal().click();
//		}
//		catch (UnreachableBrowserException e) {
//			pp.getClickPayNow().click();
//			waitForPageToLoad();
//			refresh();
//			waitForElementToVisible(By.xpath("//a[text()='Log In']"));
//			pp.getLoginPaypal().click();
//			
//		}
//		//waitForElementToVisible(By.id("email"));
//		Thread.sleep(10000);
//		pp.getAcceptCookies().click();
	
		pp.getPaypalEmail().sendKeys(PaypalEmail);
		pp.getSubmitEmail().click();
		pp.getPaypalPassword().sendKeys(PaypalPassword);
		//Thread.sleep(3000);
		
		
		pp.getPaypalLogin().click();
		try {
		     Thread.sleep(25000);
		   
			 
			 js.executeScript("arguments[0].scrollIntoView();",pp.getPayPalPolicyLink() );
			  js.executeScript("arguments[0].click()", pp.getClickContinue());
		}
			 catch(TimeoutException e) {
					Thread.sleep(25000);
					
					pp.getClickContinue().click();	
					
					
					
				}
			 
		//	}
			
//			catch(Exception e) {
//				Thread.sleep(10000);
//				 js.executeScript("arguments[0].scrollIntoView();",pp.getPayPalPolicyLink() );
//				pp.getClickContinue().click();	}
		
		
		try {
	    
		

		
	    waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
	
        Assert.assertEquals(pp.getSuccessMessage().getText(), "Your payment has been processed successfully");
		
		}
		catch (UnreachableBrowserException e) {
			
			waitForElementToVisible(By.xpath("//div[@class='paymentsuccess']"));
			
	        Assert.assertEquals(pp.getSuccessMessage().getText(), "Your payment has been processed successfully");
			}
	}
			
		
		
	//	Thread.sleep(30000);
//		//log.info("Order purchased successfully");
//        waitForPageToLoad();
//       
////		pp.goToMyAccountPage().click();
//       
//		pp.goToMyAccountPage().click();
//		//waitForElementToVisible(By.id("email"));
//		pp.goToMyOrder().click();
//		pp.getSelectAllOrders().click();
//		pp.getClickonOrderNo().click();
//		Assert.assertEquals(pp.getProductName().getText(),"Office 365 Setup");
	
		
		
	
	
  //  @Test(dataProvider="getProductData")
	@Test(priority=2)
	
	public void verifyOrderInMyAccount() throws InterruptedException {
		
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
		waitForElementToVisible(By.xpath("//b[contains(text(),'Office 365 Setup')]"));
	    Assert.assertEquals(pp.getProductName().getText(),ProductName);
	}
	
   @Test(priority=3)
	
	public void verifyTotalAmountInMyOrders() {
		MemberDashboard mm=new MemberDashboard(driver);
		waitForElementToVisible(By.xpath("//div[text()='Total Amount']/parent::div//following-sibling::div"));
		Assert.assertEquals(mm.getTotalAmount().getText(),Amount);
		
		
	}
   
   @Test(priority=4)

   public void verifyOrderAmountInMyBilling() {
   	    ProductsPurchasePage pp=new ProductsPurchasePage(driver);
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
		
		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		MemberDashboard mm=new MemberDashboard(driver); 
		mm.goToMyDashboard().click();
		//mm.getclickonClosebutton().click();
		//mm.getclickonInvoices().click();
		//waitForElementToVisible(By.xpath("//a[@class='dropdown-item active clearfix recent_invoice_list']"));
		//mm.getSelectAllInvoices().click();
		waitForElementToVisible(By.xpath("//a[contains(text(),'INV')]"));
		mm.getClickOnInvoiceNo().click();
		waitForElementToVisible(By.xpath("//span[text()='Invoice Total']/parent::td//following-sibling::td"));
		Assert.assertEquals(mm.getInvoiceTotal().getText(),Amount);
		
	}
   
   

@Test(priority=9,enabled=false)

public void verifyInvoiceAmountInMyBilling() {
	MyBillingPage billing=new MyBillingPage(driver);
	MemberDashboard mm=new MemberDashboard(driver);
    AmountOnInvoicePopUp=mm.getInvoiceTotal().getText();
    mm.getclickonClosebutton().click();
	billing.goToMyBilling().click();
	billing.goToInvoices().click();

	Assert.assertEquals(billing.getAmountInInvoices().getText(),AmountOnInvoicePopUp);
}


@Test(priority=6)

public void verifyInvoiceAmountInMyledger() {
	MyBillingPage billing=new MyBillingPage(driver);
	MemberDashboard mm=new MemberDashboard(driver);
	 AmountOnInvoicePopUp=mm.getInvoiceTotal().getText();
	    mm.getclickonClosebutton().click();
		billing.goToMyBilling().click();
	    billing.goToLedger().click();
	
	Assert.assertEquals(billing.getDebitAmountInLedger().getText(),AmountOnInvoicePopUp);
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
   
   @Test(priority=9)
     public void verifyEmailLogs() throws InterruptedException {
	   
		MySupportPage sp=new MySupportPage(driver);
        sp.goToMySupport().click();
		sp.getEmailLogs().click();
		Thread.sleep(3000);
        boolean flag = false;
		if (sp.getEmailSubject().getText().equalsIgnoreCase(expEmailLog)) {
			flag = true;
			
			System.out.println("Yeah... Your Office 365 Account is Set up and Ready to Use");
		}
		
		Assert.assertTrue(flag, "Your Office 365 Account is not set up");
	}
   @Test(priority=10)
	
	public void verifyServiceStatusInMyAdmin() throws InterruptedException {
		
		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
		LoginPage page=new LoginPage(driver);
		driver.get(prop.getProperty("adminURL"));
		
		
		page.getAdminUsername().sendKeys(adminUsername);
		page.getAdminPassword().sendKeys(adminPassword);
		page.getAdminSignIn().click();
		page.getClosePopUp().click();
		switchToNewWindow();

		driver.navigate().to("https://manage.qa8.dotbricks.net/adminp/accounts/customers");
		page.getClickClient().click();
		page.getProduct_Services().click();
		System.out.println(page.getServiceStatus().getText());
		
		Assert.assertEquals(page.getServiceStatus().getText(),"Active");
		
		
		
//         Actions action = new Actions(driver); 
//         action.moveToElement(page.getClientDropdown()).click();
//
//        //action.moveToElement(page.getClientDropdown()).perform();
//       // actiopage.getClientDropdown().click();
//        action.moveToElement(page.getSelectClient()).perform();
//        action.moveToElement(page.getSelectList()).perform();
//        Thread.sleep(3000);
//        action.moveToElement(page.getSelectList()).click();
//        Thread.sleep(2000);
//        
        
	
//		page.getSelectList().click();
		
   }
   @Test(priority=11)
	
  	public void verifyInvoiceStatusInMyAdmin() throws InterruptedException {
  		
  		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
  		LoginPage page=new LoginPage(driver);
  		page.getInvoicesTab().click();
  		page.getInvoiceStatus().getText();
  		Assert.assertEquals(page.getInvoiceStatus().getText(),"Paid");
   }
  
   @Test(priority=12)
	
  	public void verifyInvoiceAmountInMyAdmin() throws InterruptedException {
  		
  		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
  		LoginPage page=new LoginPage(driver);
  		Assert.assertEquals(page.getInvoiceAmount().getText(),"$ 126,865.00");
   }
  		
   
   
   @Test(priority=13)
	
  	public void verifyOrderStatusInMyAdmin() throws InterruptedException {
  		
  		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
  		LoginPage page=new LoginPage(driver);
  		page.getOrdersTab().click();
  		Assert.assertEquals(page.getOrderStatus().getText(),"Inprogress");
   }
   
   @Test(priority=14)
	
  	public void verifyOrderAmountInMyAdmin() throws InterruptedException {
  		
  		LoginPage page=new LoginPage(driver);
  		Assert.assertEquals(page.getOrderAmount().getText(),"$ 126,865.00");
  		
   }
   
   @Test(priority=15)
	
  	public void verifyReceiptAmountInMyAdmin() throws InterruptedException {
  		
  		LoginPage page=new LoginPage(driver);
  		
  		page.getReciepts().click();
  		Assert.assertEquals(page.getReceiptAmount().getText(),"$ 126,865.00");
  		
  		
   }
   
   @Test(priority=16)
	
  	public void verifyReceiptNumberInMyAdmin() throws InterruptedException {
  		
  		ProductsPurchasePage pp=new ProductsPurchasePage(driver);
  		LoginPage page=new LoginPage(driver);
  		System.out.println(page.getReceiptNumber().getText());
  		
   }
   
   @Test(priority=17)
	
 	public void verifyLedgerAmountInMyAdmin() throws InterruptedException {
 		
 		LoginPage page=new LoginPage(driver);
 		page.getLedger().click();
 		ScrollPage();
 		Thread.sleep(3000);
 		//waitForElementToVisible(By.xpath("//tbody[1]/tr[2]/td[8]"));
 		System.out.println(page.getLedgerAmount().getText());
 		Assert.assertEquals(page.getLedgerAmount().getText(),"$ 126,865.00");
 		
  }
   @Test(priority=18)
	
  	public void verifyEmailLogsInMyAdmin() throws InterruptedException {
  		
  		LoginPage page=new LoginPage(driver);
  		page.getEmailLogs().click();
  		
  		 boolean flag = false;
 		if (page.getEmailSubject().getText().equalsIgnoreCase(expEmailLog)) {
 			flag = true;
 			
 			System.out.println("Yeah... Your Office 365 Account is Set up and Ready to Use");
 		}
 		
 		Assert.assertTrue(flag, "Your Office 365 Account is not set up");
 	}
  		//Assert.assertEquals(page.getEmailSubject().getText(),"Your Office 365 Account is Set up and Ready to Use - Qa8");
  		
   
	@AfterTest
	
	public void teardown() {
		//driver.close();
	}

	@DataProvider(name="getData")
	
	public Object[][] getData(){
		Object[][] data=new Object[1][18];
		data[0][0]="tarun@gmail.com";
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
	
//	public Object[][] getProductData(){
//		Object[][] data=new Object[1][1];
//		data[0][1]="Office 365 Setup";
//		return data;
	}
