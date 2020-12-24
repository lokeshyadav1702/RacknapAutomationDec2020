package Marketplace;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import resources.base;

public class SignIn extends base{
	

	//@Test(dataProvider="getLoginData")
	
	@Test
public void SignIn() {
		HomePage hp=new HomePage();
		LoginPage lp=new LoginPage(driver);
		try {
			hp.homePageNavigation();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	

	//lp.getUsername().clear();
	lp.getUsername().sendKeys(prop.getProperty("username"));
	lp.getPassword().sendKeys(prop.getProperty("Password"));
	lp.getSignIn().click();


}}

//@DataProvider
//
//public Object[][] getLoginData(){
//	Object[][] data=new Object[1][2];
//	data[0][0]="singh9779";
//	data[0][1]="Password12";
//	return data;
//}}