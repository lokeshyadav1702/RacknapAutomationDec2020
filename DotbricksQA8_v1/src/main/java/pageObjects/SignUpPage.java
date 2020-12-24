package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpPage {
	
	public WebDriver driver;
	
	By username=By.id("regi_username");
	By email=By.id("regi_email");
	By password=By.id("password");
	By confirmPassword=By.id("con_password");
	By Firstname=By.name("firstname");
	By lastName=By.id("lastname");
	By companyName=By.name("companyname");
	By address1=By.id("address1");
    By city=By.name("city");
	By mobileNumber=By.name("contact_no");
	By phoneNumber=By.name("phonenumber");
	By zipCode=By.name("pincode");
	By VAT=By.id("custom2");
	By nationality=By.id("custom3");
	By aadharNumber=By.id("custom4");
	By GSTNumber=By.id("custom5");
	By PAN=By.id("custom7");
	By greenCardID=By.id("custom11");
	By Selectcheckbox=By.id("terms");
	By createAccount=By.name("submit");
	
	By SelectCurrency=By.name("currency");
	By usernameMessage=By.id("check_username");
	By emptyUsernameMessage=By.xpath("//span[contains(text(),'Please enter username.')]");
	By termsConditionMessage=By.xpath("//span[contains(text(),'Please accept terms and conditions.')]");
	By emptyEmailmessage=By.xpath("//span[contains(text(),'Please enter email id.')]");
	By emptyPasswordMessage=By.xpath("//span[contains(text(),'Please enter password.')]");
	By emptyConfirmPasswordMessage=By.xpath("//span[contains(text(),'Please enter confirm password.')]");
	By emptyFirstNameMessage=By.xpath("//span[contains(text(),'Please enter first name.')]");
	By emptyLastNameMessage=By.xpath("//span[contains(text(),'Please enter last name.')]");
	By emptyCompanyNameMessage=By.xpath("//span[contains(text(),'Please enter company name.')]");
	By emptyAddressMessage=By.xpath("//span[contains(text(),'Please enter street address.')]");
	By emptyCityMessage=By.xpath("//span[contains(text(),'Please enter City.')]");
	By emptymobileNoMessage=By.xpath("//span[contains(text(),'Please enter mobile number.')]");
	By emptyZipCodeMessage=By.xpath("//span[contains(text(),'Please enter zip code.')]");
	By usernameLengthMessage=By.xpath("//span[contains(text(),'Username minimum length is 6 characters.')]");
	By usernameInputValidationMessage=By.xpath("//span[contains(text(),'Only alphanumeric and . _ @ are allowed. Should no')]");
	By invalidEmail=By.xpath("//span[contains(text(),'Please enter valid email id.')]");
	By passwordLength=By.xpath("//span[contains(text(),'Please enter atleast 6 character.')]");
	By passwordValidation=By.xpath("//span[contains(text(),'Password must be atleast alphanumeric.')]");
	By confirmPasswordvalidation=By.id("con_password_error");
	By textValidation=By.xpath("//span[contains(text(),'Please enter alphabets only.')]");
	By emptyCurrency=By.xpath("//span[contains(text(),'Please select Currency.')]");
	By mobileNumberValidation=By.xpath("//span[contains(text(),'Mobile number should be numeric only.')]");
	By emptyCountry=By.xpath("//span[contains(text(),'Please select country.')]");
	
	By Country=By.id("countryid");
	By state=By.id("state");
	By emptyState=By.xpath("//span[contains(text(),'Please select state.')]");
	
			
	public SignUpPage(WebDriver driver) {
		
		this.driver=driver;
		
	}


	public WebElement getUsername() {
		return driver.findElement(username);
	}



	public WebElement getEmail() {
		return driver.findElement(email);
	}


	public WebElement getPassword() {
		return driver.findElement(password);
	}


	public WebElement getConfirmpassword() {
		return driver.findElement(confirmPassword);
	}
		
		public WebElement getFirstname() {
			return driver.findElement(Firstname);
		}
		
		public WebElement getLastname() {
			return driver.findElement(lastName);
		}
		
		public WebElement getCompanyname() {
			return driver.findElement(companyName);
		}
		public WebElement getAddress1() {
			return driver.findElement(address1);
		}
		public WebElement getcity() {
			return driver.findElement(city);
		}
		public WebElement getMobilenumber() {
			return driver.findElement(mobileNumber);
		}
		
		public WebElement getPhonenumber() {
			return driver.findElement(phoneNumber);
		}
		public WebElement getZipcode() {
			return driver.findElement(zipCode);
		}
		
		public WebElement getVAT() {
			return driver.findElement(VAT);
		}
		public WebElement getSelectNationality() {
			return driver.findElement(nationality);
		}
		public WebElement getAadharnumber() {
			return driver.findElement(aadharNumber);
		}
		public WebElement getGSTnumber() {
			return driver.findElement(GSTNumber);
		}
		public WebElement getPANnumber() {
			return driver.findElement(PAN);
		}
		public WebElement getGreenCardID() {
			return driver.findElement(greenCardID);
		}
		
		public WebElement  getSelectCheckbox() {
			return driver.findElement(Selectcheckbox);
		}
		
		public WebElement getCreateAccountbutton() {
			return driver.findElement(createAccount);
		}
		
		public WebElement getSelectCurrency() {
			return driver.findElement(SelectCurrency);
		}
		
		public WebElement getInvalidUsernameMessage() {
			return driver.findElement(usernameMessage);
		}
		
		public WebElement getEmptyUsernameMessage() {
			return driver.findElement(emptyUsernameMessage);
		}
		
		public WebElement getUsernameLengthMessage() {
			return driver.findElement(usernameLengthMessage);
		}
		
		public WebElement getUsernameInputValidationMessage() {
			return driver.findElement(usernameInputValidationMessage);
		}

		public WebElement getUncheckTermAndConditionMessage() {
			return driver.findElement(termsConditionMessage);
		}
		
		public WebElement getEmptyEmailMessage() {
			return driver.findElement(emptyEmailmessage);
		}
		
		
		public WebElement getEmptyPasswordMessage() {
			return driver.findElement(emptyPasswordMessage);
		}
		
		
		public WebElement getEmptyConfirmPasswordMessage() {
			return driver.findElement(emptyConfirmPasswordMessage);
		}
		
		
		
		public WebElement getEmptyFirstNameMessage() {
			return driver.findElement(emptyFirstNameMessage);
		}
		
		
		
		public WebElement getEmptyLastNameMessage() {
			return driver.findElement(emptyLastNameMessage);
		}
		
		public WebElement getEmptyCompanyNameMessage() {
			return driver.findElement(emptyCompanyNameMessage);
		}
		
		
		public WebElement getEmptyAddressMessage() {
			return driver.findElement(emptyAddressMessage);
		}
		
		
		
		public WebElement getEmptyCityMessage() {
			return driver.findElement(emptyCityMessage);
		}
		
		
		
		public WebElement getEmptyMobileNoMessage() {
			return driver.findElement(emptymobileNoMessage);
		}
		

		public WebElement getEmptyZipCodeMessage() {
			return driver.findElement(emptyZipCodeMessage);
		}
		
		
		public WebElement getInvalidEmailMessage() {
			return driver.findElement(invalidEmail);
		}
		
		public WebElement getPasswordLengthMessage() {
			return driver.findElement(passwordLength);
		}
		
		
		public WebElement getPasswordValidationMessage() {
			return driver.findElement(passwordValidation);
		}
		
		
		public WebElement getConfirmPasswordValidationMessage() {
			return driver.findElement(confirmPasswordvalidation);
		}
		
		public WebElement getTextValidationMessage() {
			return driver.findElement(textValidation);
		}
		
		public WebElement getEmptyCurrencyMessage() {
			return driver.findElement(emptyCurrency);
		}
		
		public WebElement getMobileNumberValidationMessage() {
			return driver.findElement(mobileNumberValidation);
		}
		
		public WebElement getEmptyCountryMessage() {
			return driver.findElement(emptyCountry);
		}
		
		
		public WebElement getCountry() {
			return driver.findElement(Country);
		}
		
		public WebElement getState() {
			return driver.findElement(state);
		}
		
		public WebElement getEmptyStateMessage() {
			return driver.findElement(emptyState);
		}
	}


