package inetSolve1.inetSolve1;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import inet.bank.com.BaseClass;

public class Test_Auto_001 extends BaseClassTest {
	

	@Test
	  public  void LoginTest() throws IOException {
		
		driver.get(baseURL);
		//logger.info("URL is opened");
		BaseClass bc = new BaseClass(driver);
		bc.setUserName(username);
		
		bc.setPassword(password);
		
		
		bc.clickSubmit();
		if(driver.getTitle().equals("Guru99 Bank Manager Homepage")) {
			Assert.assertTrue(true);
			
		}else { 
			captureScreen( driver,"LoginTest" );
			Assert.assertFalse(false);
			
			
		}
}

}
