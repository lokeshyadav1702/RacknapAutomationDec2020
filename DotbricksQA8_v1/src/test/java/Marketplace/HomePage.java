package Marketplace;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import resources.base;

public class HomePage extends base {
	public static Logger log=LogManager.getLogger(base.class.getName());
	
	@Test
	 
	public void homePageNavigation() throws IOException {
		initializeDriver();
		log.info("Driver is initialized...");
		driver.get(prop.getProperty("url"));
		//log.info("Navigated to Home Page");
		LandingPage landingpage=new LandingPage(driver);
		
		landingpage.getLogin().click();
		landingpage.getCustomerLogin().click();
		
		
	}

}
