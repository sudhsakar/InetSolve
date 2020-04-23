package inetSolve1.inetSolve1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import inet.bank.utilities.ReadConfig;


public class BaseClassTest  {
	
	//private static final File New = null;

	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeMethod
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) {
		 logger.getLogger("ebancking");
		 
		 PropertyConfigurator.configure("Log4j.properties");
		 
		 if(br.equals("firefox")) {
		 System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
		 driver = new FirefoxDriver();
		 }else {
			 if(br.equals("chrome")) {
				 System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
				 driver = new FirefoxDriver();
			 }	
			 driver.get(baseURL);
		 }
		 
    }
	
@AfterMethod
@AfterClass 
public void tearDown() {
	driver.quit();
}
public void captureScreen(WebDriver driver, String tname) throws IOException{
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	File target = new File(System.getProperty("user.dir")+"\\Screenshorts\\" + tname +".png");
	FileUtils.copyFile(source, target);
	System.out.println("Screenshot taken");
}

}
