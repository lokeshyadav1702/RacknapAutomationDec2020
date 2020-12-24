package resources;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class base {
	public WebDriver driver;
	public Properties prop;
	
public WebDriver initializeDriver() throws IOException {
	
	
    prop=new Properties();
	FileInputStream fis=new FileInputStream("C:\\Users\\tarun\\DotbricksQA8_v1\\src\\main\\java\\resources\\data.properties");
	//FileInputStream fis=new FileInputStream("Z:\\git\\MarketPlace\\Dotbricks\\src\\main\\java\\resources\\data.properties");
	//FileInputStream fis=new FileInputStream("user.dir"+"\\git\\MarketPlace\\Dotbricks\\src\\main\\java\\resources\\data.properties");

	prop.load(fis);
	String browserName=prop.getProperty("browser");
	
	if(browserName.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tarun\\chromedriver_win32 (2)\\chromedriver.exe");
		
//		ChromeOptions options = new ChromeOptions(); 
//		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
//	   driver = new ChromeDriver(options);
//		
		
	driver=new ChromeDriver();	
	}
	
	else if(browserName.equals("firefox")) {
		
		
		System.setProperty("webdriver.gecko.driver", "Z:\\Automation\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver=new FirefoxDriver();	
		
	}
	else if(browserName.equals("IE")) {
		
	//IE code
		
	}
	
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	driver.manage().window().maximize();
	return driver;
}

public String randomDomainName() {
	
	 Random random = new Random(); 
	 
	 int number = random.nextInt(100000); 
	 
	 return "rediff"+number;
	 
}

public String randomHostName() {
	
	 Random random = new Random(); 
	 
	 int number = random.nextInt(100000); 
	 
	 return "Znet"+number+".com";
	 
}
public String randomUsername() {
	
	 Random random = new Random(); 
	 
	 int number = random.nextInt(10000); 
	 
	 return "singh"+number;
	 
}

public void waitForElementToVisible(By locator){
	 
	WebDriverWait wait = new WebDriverWait(driver, 300);
	 
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

}

public void waitForElementToBeClickable(By locator){
	 
	WebDriverWait wait = new WebDriverWait(driver, 300);
	 
    
	wait.until(ExpectedConditions.elementToBeClickable(locator));
	
	
}

public void waitForPageToLoad() throws InterruptedException {
	driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);
	// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

}


public void waitForElementToDisappear(By locator){
	 
	WebDriverWait wait = new WebDriverWait(driver, 300);
	 
    
	wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	
	
}





public void acceptAlert() {
	Alert alt = driver.switchTo().alert();
	alt.accept();
}

public void refresh() {
	
	driver.navigate().refresh();
}

public void MouseClick() throws AWTException {
Actions actions = new Actions(driver);

Robot robot = new Robot();

robot.mouseMove(50,50);

actions.click().build().perform();
}

public void ScrollPage() {
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 
	 js.executeScript("window.scrollBy(0,1000)");
}
public void ScrollingPage(By Element) {
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 
	 js.executeScript("arguments[0].scrollIntoView();",Element );
}


public String renewalDate(Date dateBeforeRenewal, int renewalDurationInYears) {

	Calendar c = Calendar.getInstance();
	c.setTime(dateBeforeRenewal);
	c.add(Calendar.YEAR, renewalDurationInYears);
	  
	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	  String strDate= formatter.format(c.getTime());
	 
	
	  return strDate;
	
}

public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
{
	TakesScreenshot ts=(TakesScreenshot) driver;
	File source =ts.getScreenshotAs(OutputType.FILE);
	String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	FileHandler.copy(source,new File(destinationFile));
	return destinationFile;
}

public void switchToNewWindow() {
	

String parent=driver.getWindowHandle();

Set<String>s=driver.getWindowHandles();

// Now iterate using Iterator
Iterator<String> I1= s.iterator();

while(I1.hasNext())
{

String child_window=I1.next();


if(!parent.equals(child_window))
{
driver.switchTo().window(child_window);
}

}}}