package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPurchasePage {
	
	public WebDriver driver;
	
By product=By.xpath("//a[contains(text(),'Products')]");
By selectproduct=By.xpath("//span[@class='mega-menu-title'][contains(text(),'Microsoft Office 365')]");
By viewBusinessplan=By.linkText("View Business Plans");
By ordernow=By.xpath("//a[@class='planone_chnagedurl primary-border white-text primary-color btn-hovers font-16 padding-top-bottom-5 display-inline-block padding-right-left-20 text-center margin-bottom-20 margin-top-10']");
By addquantity=By.xpath("//a[@class='less-btn-quntity']");
By domainname=By.id("msother_domainname");
By selectbutton=By.id("domain_select_button1");
By addcart=By.id("submitbtn");
By nextbutton=By.id("next_without_login");
By createaccount=By.id("sign-up-sec");
By NextSignup=By.id("make_payment");
By acceptAgreement=By.id("save_agreement_btn");
By acceptAlert=By.xpath("//button[contains(@class,'confirm btn btn-lg btn-warning')]");
By selectPayment=By.id("paypal");
By acceptPaymentAlert=By.xpath("//button[@class='confirm btn btn-lg btn-warning']");
By PaynowButton=By.id("submit");
By PaypalEmail=By.id("email");
By emailNextButton=By.id("btnNext");
By PaypalPassword=By.id("password");
By PaypalLogin=By.id("btnLogin");
By PaypalContinue=By.id("payment-submit-btn");
By OrderSuccessMessage=By.xpath("//div[@class='paymentsuccess']");
By closePopup=By.id("sa_close");
By MyAccount=By.xpath("//a[contains(text(),'My Account')]");
By MyOrders=By.xpath("//a[text()='Orders']");
By Allorders=By.xpath("//div[@class='dropdown-menu show']//span[text()='All']");
By OrderNumber=By.xpath("//div[@id='response_all']//div[1]//span[1]//a[1]");
By ProductName=By.xpath("//b[contains(text(),'Office 365 Setup')]");
By selectcredit=By.id("adjust_with_credit");
By SuccessCreditMessage=By.xpath("//div[@class='bay-bill-balance']");
By Banktransfer=By.id("banktransfer");
By OfflineSuccessMessage=By.xpath("//div[@class='offline-success offline_success_message']");
By loginPaypal=By.xpath("//a[text()='Log In']");
By acceptCookies=By.id("acceptAllButton");
By TotalPrice=By.xpath("//div[@class='add-total-amounts total-costs']//span[@class='right-prices']");

By PaymentNext=By.xpath("//input[@class='bnt-gradient paynow']");
By Loader=By.id("span_load");

By domaninName=By.id("searchkeyword1");
By Searchbutton=By.className("searchbutton");
By searchdomain=By.xpath("//div[@class='d-nms']");
By hostnameTab=By.id("nav-hostname-tab");
By hostnameTabVPS=By.id("complete-tab");
By hostname=By.id("hostnamehostname");
By hostnameInorder=By.xpath("//div[@class='product_details']");
By SelectDomain=By.xpath("//div[@class='d-nms']//button");


By AddtoCartbtn=By.name("submit");
By previousbutton=By.xpath("//span[contains(@class,'ui-icon ui-icon-circle-triangle-w')]");
By selectDate=By.linkText("1");
By selectCalender=By.id("mca_agreement_date");

By selectFilter=By.xpath("//div[@class='all-ex']//div[2]//label[1]//span[contains(@class,'check-indicator')]");
By applyButton=By.id("apply_extension");
By selectButton=By.xpath("//div[@class='d-results']/div[1]//button");
By continueToCart=By.xpath("//a[contains(@class,'addtocart bnt-gradient load_popup')]");
By addToCartDomain=By.xpath("//button[@value='Add to Cart1']");
By getDomainName=By.xpath("//div[@class='d-results']/div[1]/div[@class='d-nms']");

By paypalPolicyLink=By.linkText("PayPal Policies");

By AddtoCartVPS=By.xpath("//button[contains(@class,'addcart-bnds opensrs-ssl')]");

By desiredUsername=By.id("new_ac_username_name");
By acSelectbutton=By.id("ac_username_select_button1");
By hostnameAzure=By.xpath("//input[@placeholder='Enter Hostname']");

By selectbuttonAzure=By.xpath("//input[@value='Select']");
By oKButtonAzure=By.xpath("//button[@class='confirm btn btn-lg btn-success']");
By checkout=By.name("Submit");
By AzureSuccessMessage=By.xpath("//div[@id='fraudcheckload']//p");
By bundleAcronicsUsername=By.name("new_ac_username_name");
By bundleACSelectButton=By.xpath("//input[@class='button-custom ac_username_select']");
By bundleAcvalidmsg=By.id("ac_valid_msg_1");
By selectDedicatedServer=By.xpath("//div[@id='headingfive']//button[contains(@class,'btn btn-link collapsed')]");
By HostnameBundle=By.xpath("//input[contains(@placeholder,'Please enter your hostname.')]");
By selectEmailsolution=By.xpath("//button[contains(text(),'Office 365 - Essential Suite - Office 365- Essenti')]");
By bundleDomainName=By.xpath("//input[@placeholder='Enter domain name']");
By bundleEmailSelectButton=By.xpath("//input[contains(@placeholder,'Enter domain name')]//parent::label//parent::section//following-sibling::section//input[1]");
By bundleEmailValidmsg=By.xpath("//span[contains(text(),'Entered domain name is available.')]");
By expandButtonHostName=By.xpath("//a[@class='active collapsed']");
By OkbuttonHostname=By.xpath("//button[text()='Ok']");
@FindBy(xpath="//div/img[@src='img/loding-img.gif']")

 public WebElement loadingIcon;


public ProductsPurchasePage(WebDriver driver) {
	
	this.driver=driver;
	
}

public WebElement clickProduct() {
	return driver.findElement(product);
}



public WebElement selectProduct() {
	return driver.findElement(selectproduct);
}


public WebElement viewBusinessPlan() {
	return driver.findElement(viewBusinessplan);
}


public WebElement orderNow() {
	return driver.findElement(ordernow);
}
	
	public WebElement addQuantity() {
		return driver.findElement(addquantity);
	}
	
	public WebElement enterDomain() {
		return driver.findElement(domainname);
	}
	
	public WebElement selectButton() {
		return driver.findElement(selectbutton);
	}
	public WebElement addToCart() {
		return driver.findElement(addcart);
	}
	public WebElement clickOnNext() {
		return driver.findElement(nextbutton);
	}
	public WebElement creatAccount() {
		return driver.findElement(createaccount);
	}
	
	public WebElement getClickOnNextButton() {
		return driver.findElement(NextSignup);
	}
	

	public WebElement getClickOnAcceptButton() {
		return driver.findElement(acceptAgreement);
	}
	

	public WebElement getClickOnAcceptAlert() {
		return driver.findElement(acceptAlert);
	}
	
	public WebElement getSelectPaymentMode() {
		return driver.findElement(selectPayment);
	}
	
	public WebElement getClickOnAcceptPaymentAlert() {
		return driver.findElement(acceptPaymentAlert);
	}
	public WebElement getClickPayNow() {
		return driver.findElement(PaynowButton);
	}
	
	public WebElement getPaypalEmail() {
		return driver.findElement(PaypalEmail);
	}
	
	public WebElement getSubmitEmail() {
		return driver.findElement(emailNextButton);
	}
	
	public WebElement getPaypalPassword() {
		return driver.findElement(PaypalPassword);
	}
	
	public WebElement getPaypalLogin() {
		return driver.findElement(PaypalLogin);
	}
	
	public WebElement getClickContinue() {
		return driver.findElement(PaypalContinue);
	}
	
	public WebElement getSuccessMessage() {
		return driver.findElement(OrderSuccessMessage);
	}
	

	public WebElement getClosePopUp() {
		return driver.findElement(closePopup);
	}
	

	public WebElement goToMyAccountPage() {
		return driver.findElement(MyAccount);
	}
	

	public WebElement goToMyOrder() {
		return driver.findElement(MyOrders);
	}
	
	public WebElement getSelectAllOrders() {
		return driver.findElement(Allorders);
	}
	
	public WebElement getClickonOrderNo() {
		return driver.findElement(OrderNumber);
	}
	
	public WebElement getProductName() {
		return driver.findElement(ProductName);
	}
	

	public WebElement getSelectCredit() {
		return driver.findElement(selectcredit);
	}
	

	public WebElement getCreditMessage() {
		return driver.findElement(SuccessCreditMessage);
	}
	
	public WebElement getSelectBanktransfer() {
		return driver.findElement(Banktransfer);
	}
	

	public WebElement getOfflineSuccessMessage() {
		return driver.findElement(OfflineSuccessMessage);
	}
	

	public WebElement getLoginPaypal() {
		return driver.findElement(loginPaypal);
	}
	

	public WebElement getAcceptCookies() {
		return driver.findElement(acceptCookies);
	}
	
	public WebElement getTotalPriceOnOrderSummary() {
		return driver.findElement(TotalPrice);
	}
	

	public WebElement getPaymentSummaryNextButton() {
		return driver.findElement(PaymentNext);
	}
	
	public WebElement getTypeDomainName() {
		return driver.findElement(domaninName);
	}
	
	public WebElement getSearchButton() {
		return driver.findElement(Searchbutton);
	}
	
	public List<WebElement> getSearchDomain() {
		return driver.findElements(searchdomain);
	}
	
	public WebElement getSelectButton() {
		return driver.findElement(SelectDomain);
	}
	
	public WebElement getHostnameTab() {
		return driver.findElement(hostnameTab);
	}
	
	public WebElement getHostname() {
		return driver.findElement(hostname);
	}
	
	public WebElement getAddToCartButton() {
		return driver.findElement(AddtoCartbtn);
	}
	
	public WebElement getHostnameInOrder() {
		return driver.findElement(hostnameInorder);
	}
	
	public WebElement getPreviousButton() {
		return driver.findElement(previousbutton);
	}
	
	public WebElement getSelectDate() {
		return driver.findElement(selectDate);
	}
	public WebElement getSelectCalender() {
		return driver.findElement(selectCalender);
	}
	
	public WebElement getSelectFilter() {
		return driver.findElement(selectFilter);
	}
	
	
	public WebElement getapplyButton() {
		return driver.findElement(applyButton);
	}
	
	
	public WebElement getSelect() {
		return driver.findElement(selectButton);
	}
	public WebElement getContinueToCart() {
		return driver.findElement(continueToCart);
	}
	
	public WebElement getAddToCartDomain() {
		return driver.findElement(addToCartDomain);
	}
	
	public WebElement getDomainName() {
		return driver.findElement(getDomainName);
	}
	
	public WebElement getDesiredUserName() {
		return driver.findElement(desiredUsername);
	}
	
	public WebElement getAcronicsSelectButton() {
		return driver.findElement(acSelectbutton);
	}
	
	public void waitForLoadingIconToDisappear() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 300);
		 
	    
		wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//div[@class='all-ex']//div[2]//label[1]//span[contains(@class,'check-indicator')]"))));
		
		
		
	}
	
	public WebElement getPayPalPolicyLink() {
		return driver.findElement(paypalPolicyLink);
	}
	
	public WebElement getVPSHostnameTab() {
		return driver.findElement(hostnameTabVPS);
	}
	
	public WebElement addToCartVPS() {
		return driver.findElement(AddtoCartVPS);
	}
	
	public WebElement getAzureHostname() {
		return driver.findElement(hostnameAzure);
	}
	
	public WebElement getSelectButtonAzure() {
		return driver.findElement(selectbuttonAzure);
	}
	
	public WebElement getOKButtonAzure() {
		return driver.findElement(oKButtonAzure);
	}
	
	public WebElement getCheckoutButton() {
		return driver.findElement(checkout);
	}
	
	public WebElement getSuccessMessageAzure() {
		return driver.findElement(AzureSuccessMessage);
	}
	
	
	
	public WebElement getbundleAcronicsUsername() {
		return driver.findElement(bundleAcronicsUsername);
	}
	
	public WebElement getAcSelectButton() {
		return driver.findElement(bundleACSelectButton);
	}
	
	public WebElement getACValidMsg() {
		return driver.findElement(bundleAcvalidmsg);
	}
	
	public WebElement getSelectDedicatedServer() {
		return driver.findElement(selectDedicatedServer);
	}
	

	public WebElement getHostnameBundle() {
		return driver.findElement(HostnameBundle);
	}
	

	public WebElement getSelectEmailSolution() {
		return driver.findElement(selectEmailsolution);
	}
	

	public WebElement getEmailDomainNameBundle() {
		return driver.findElement(bundleDomainName);
	}
	

	public WebElement getBundleEmailSelectButton() {
		return driver.findElement(bundleEmailSelectButton);
	}
	
	public WebElement getBundleEmailValidMsg() {
		return driver.findElement(bundleEmailValidmsg);
	}
	
	public WebElement getExpandHostname() {
		return driver.findElement(expandButtonHostName);
	}
	public WebElement getOKButtonHostname() {
		return driver.findElement(OkbuttonHostname);
	}
}
