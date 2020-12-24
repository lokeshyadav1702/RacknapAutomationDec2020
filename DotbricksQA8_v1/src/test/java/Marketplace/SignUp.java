package Marketplace;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ProductsPurchasePage;
import pageObjects.SignUpPage;
import resources.base;

public class SignUp extends base {

	@BeforeTest

	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();
		driver.get(prop.getProperty("SignUPURL"));

		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);
		ProductsPurchasePage pp = new ProductsPurchasePage(driver);
		pp.creatAccount().click();
	}

	// Registration with blank username
	@Test(dataProvider = "getData", priority = 4)
	public void emptyUsernameTest(String username, String email, String password, String ConfirmPassword,
			String FirstName, String LastName, String CompanyName, String Address1, String City, String Currency,
			String MobileNumber, String Zipcode) {
		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys(username);
		sign.getEmail().sendKeys(email);
		sign.getPassword().sendKeys(password);
		sign.getConfirmpassword().sendKeys(ConfirmPassword);
		sign.getFirstname().sendKeys(FirstName);
		sign.getLastname().sendKeys(LastName);
		sign.getCompanyname().sendKeys(CompanyName);
		sign.getAddress1().sendKeys(Address1);
		sign.getcity().sendKeys(City);
		Select currency = new Select(sign.getSelectCurrency());
		currency.selectByVisibleText(Currency);
		sign.getMobilenumber().sendKeys(MobileNumber);
		sign.getZipcode().sendKeys(Zipcode);

		sign.getSelectCheckbox().click();
		sign.getCreateAccountbutton().click();
		Assert.assertEquals(sign.getEmptyUsernameMessage().getText(), "Please enter username.");
	}

	// Registration with username which already have account
	@Test(priority = 1)
	public void duplicateUsernameTest() throws AWTException, InterruptedException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singh4753");
		MouseClick();
		Thread.sleep(3000);
		waitForElementToVisible(By.id("check_username"));
		System.out.println(sign.getInvalidUsernameMessage().getText());
		Assert.assertEquals(sign.getInvalidUsernameMessage().getText(), "Username already registered.");

	}

	// Registration with username which have invalid length
	@Test(priority = 2)
	public void invalidUsernameLengthTest() throws AWTException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("sing");
		MouseClick();
		waitForElementToVisible(By.xpath("//span[contains(text(),'Username minimum length is 6 characters.')]"));
		System.out.println(sign.getUsernameLengthMessage().getText());
		Assert.assertEquals(sign.getUsernameLengthMessage().getText(), "Username minimum length is 6 characters.");

		// Registration with username which have invalid input
	}

	@Test(priority = 3)
	public void invalidUsernameInputTest() throws AWTException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys(" singh4753");
		MouseClick();
		waitForElementToVisible(
				By.xpath("//span[contains(text(),'Only alphanumeric and . _ @ are allowed. Should no')]"));
		System.out.println(sign.getUsernameInputValidationMessage().getText());
		Assert.assertEquals(sign.getUsernameInputValidationMessage().getText(),
				"Only alphanumeric and . _ @ are allowed. Should not start with ._@.");

	}

	// Registration with invalid email
	@Test(priority = 5)
	public void invalidEmailTest() throws AWTException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getEmail().clear();
		sign.getEmail().sendKeys("defdfd");
		MouseClick();
		waitForElementToVisible(By.xpath("//span[contains(text(),'Please enter valid email id.')]"));
		System.out.println(sign.getInvalidEmailMessage().getText());
		Assert.assertEquals(sign.getInvalidEmailMessage().getText(), "Please enter valid email id.");

	}

	// Registration with invalid password
	@Test(priority = 6)
	public void invalidPasswordTest() throws AWTException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getPassword().clear();
		sign.getPassword().sendKeys("defdfd");
		MouseClick();
		waitForElementToVisible(By.xpath("//span[contains(text(),'Password must be atleast alphanumeric.')]"));
		System.out.println(sign.getPasswordValidationMessage().getText());
		Assert.assertEquals(sign.getPasswordValidationMessage().getText(), "Password must be atleast alphanumeric.");

	}

	// Registration with invalid password Length
	@Test(priority = 7)
	public void invalidPasswordLengthTest() throws AWTException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getPassword().clear();
		sign.getPassword().sendKeys("de");
		MouseClick();
		waitForElementToVisible(By.xpath("//span[contains(text(),'Please enter atleast 6 character.')]"));
		System.out.println(sign.getPasswordLengthMessage().getText());
		Assert.assertEquals(sign.getPasswordLengthMessage().getText(), "Please enter atleast 6 character.");

	}

	// Registration with invalid confirm password
	@Test(priority = 8)
	public void invalidConfirmPassword() throws AWTException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("de1");
		MouseClick();
		waitForElementToVisible(By.id("con_password_error"));
		System.out.println(sign.getConfirmPasswordValidationMessage().getText());
		Assert.assertEquals(sign.getConfirmPasswordValidationMessage().getText(),
				"Password and Confirm password fields should have the same entry.");

	}

	// Registration with invalid first Name
	@Test(priority = 9)

	public void invalidFirstName() throws AWTException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("d12");
		MouseClick();
		waitForElementToVisible(By.xpath("//span[contains(text(),'Please enter alphabets only.')]"));
		System.out.println(sign.getTextValidationMessage().getText());
		Assert.assertEquals(sign.getTextValidationMessage().getText(), "Please enter alphabets only.");

	}

	// Registration with invalid Last Name
	@Test(priority = 10)

	public void invalidLastName() throws AWTException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getLastname().clear();
		sign.getLastname().sendKeys("de2");
		MouseClick();
		waitForElementToVisible(By.xpath("//span[contains(text(),'Please enter alphabets only.')]"));
		System.out.println(sign.getTextValidationMessage().getText());
		Assert.assertEquals(sign.getTextValidationMessage().getText(), "Please enter alphabets only.");

	}

	// Registration with invalid City Name

	@Test(priority = 11)

	public void invalidCityName() throws AWTException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getcity().clear();
		sign.getcity().sendKeys("d3321");
		MouseClick();
		waitForElementToVisible(By.xpath("//span[contains(text(),'Please enter alphabets only.')]"));
		System.out.println(sign.getTextValidationMessage().getText());
		Assert.assertEquals(sign.getTextValidationMessage().getText(), "Please enter alphabets only.");

	}

	// Registration with invalid Mobile Number
	@Test(priority = 12)

	public void invalidMobileNumber() throws AWTException {
		SignUpPage sign = new SignUpPage(driver);
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("de");
		MouseClick();
		waitForElementToVisible(By.xpath("//span[contains(text(),'Mobile number should be numeric only.')]"));
		System.out.println(sign.getMobileNumberValidationMessage().getText());
		Assert.assertEquals(sign.getMobileNumberValidationMessage().getText(), "Mobile number should be numeric only.");

	}

	// Registration without accepting terms and condition tickbox
	@Test(priority = 13)
	public void uncheckedTerms() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("tarun900");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("test@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();
		Assert.assertEquals(sign.getUncheckTermAndConditionMessage().getText(), "Please accept terms and conditions.");

	}

	// Registration without providing username
	@Test(priority = 14)
	public void emptyUsernameTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("test@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyUsernameMessage().getText(), "Please enter username.");

	}

	// Registration without providing email
	@Test(priority = 15)
	public void emptyEmailTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyEmailMessage().getText(), "Please enter email id.");

	}

	// Registration without providing First Name
	@Test(priority = 16)
	public void emptyFirstNameTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("tarun@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyFirstNameMessage().getText(), "Please enter first name.");

	}

	// Registration without providing Last Name
	@Test(priority = 17)
	public void emptyLastNameTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("tarun@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyLastNameMessage().getText(), "Please enter last name.");

	}

	// Registration without providing company Name
	@Test(priority = 18)
	public void emptyCompanyNameTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("tarun@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyCompanyNameMessage().getText(), "Please enter company name.");

	}

	// Registration without providing Address
	@Test(priority = 19)
	public void emptyAddressTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("tarun@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyAddressMessage().getText(), "Please enter street address.");

	}

	// Registration without providing City
	@Test(priority = 20)
	public void emptyCityTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("tarun@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyCityMessage().getText(), "Please enter City.");

	}

	// Registration without providing mobile number
	@Test(priority = 21)
	public void emptyMobileNumberTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("test@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyMobileNoMessage().getText(), "Please enter mobile number.");

	}

	// Registration without providing zipcode
	@Test(priority = 22)
	public void emptyZipCodeTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("test@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyZipCodeMessage().getText(), "Please enter zip code.");

	}

	// Registration without providing password
	@Test(priority = 23)
	public void emptyPasswordTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("test@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyPasswordMessage().getText(), "Please enter password.");

	}

	// Registration without providing confirm password
	@Test(priority = 24)
	public void emptyConfirmPasswordTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("test@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		// Select currency=new Select(sign.getSelectCurrency());
		// currency.selectByVisibleText("");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyConfirmPasswordMessage().getText(), "Please enter confirm password.");

	}

	// Registration without providing currency
	@Test(priority = 25)
	public void emptyCurrencyTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("test@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		 Select currency=new Select(sign.getSelectCurrency());
		currency.selectByVisibleText("Select Currency *");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyCurrencyMessage().getText(), "Please select Currency.");

	}

	// Registration without providing country
	@Test(priority = 26)
	public void emptyCountryTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("test@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		
		 Select country=new Select(sign.getCountry());
		 country.selectByVisibleText("Select Country *");
		 sign.getcity().clear();
	    sign.getcity().sendKeys("Jaipur");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyCountryMessage().getText(), "Please select country.");

	}

	// Registration without providing state
	@Test(priority = 27)
	public void emptyStateTest() {

		SignUpPage sign = new SignUpPage(driver);
		sign.getUsername().clear();
		sign.getUsername().sendKeys("singeh62");
		sign.getEmail().clear();
		sign.getEmail().sendKeys("test@gmail.com");
		sign.getPassword().clear();
		sign.getPassword().sendKeys("Password12");
		sign.getConfirmpassword().clear();
		sign.getConfirmpassword().sendKeys("Password12");
		sign.getFirstname().clear();
		sign.getFirstname().sendKeys("TEST");
		sign.getLastname().clear();
		sign.getLastname().sendKeys("Singh");
		sign.getCompanyname().clear();
		sign.getCompanyname().sendKeys("Znet");
		sign.getAddress1().clear();
		sign.getAddress1().sendKeys("Chitrakoot");
		
		
		Select state=new Select(sign.getState());
		state.selectByVisibleText("Select");
		sign.getcity().clear();
		sign.getcity().sendKeys("Jaipur");
		sign.getMobilenumber().clear();
		sign.getMobilenumber().sendKeys("203920323");
		sign.getZipcode().clear();
		sign.getZipcode().sendKeys("320221");

		sign.getSelectCheckbox().click();

		sign.getCreateAccountbutton().click();

		Assert.assertEquals(sign.getEmptyStateMessage().getText(), "Please select state.");

	}

	
	@AfterTest

	public void teardown() {
		// driver.close();
	}

	@DataProvider(name = "getData")

	public Object[][] getData() {
		Object[][] data = new Object[1][12];
		data[0][0] = " ";
		data[0][1] = "tarun@gmail.com";
		data[0][2] = "Password12";
		data[0][3] = "Password12";
		data[0][4] = "Tanmay";
		data[0][5] = "Singh";
		data[0][6] = "Znet Technologies";
		data[0][7] = "Chitrakoot";
		data[0][8] = "Jaipur";
		data[0][9] = "$";
		data[0][10] = "9782437496";
		data[0][11] = "302021";

		return data;

	}
}
