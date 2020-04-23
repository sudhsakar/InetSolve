package inet.bank.utilities;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
public void startReport(ITestContext testContext) {
	
	//SimpleDateFormat timestamp = new SimpleDateFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    String timestamps = new SimpleDateFormat("YYYY.MM.DD").format(new Date());
	String repName = "Test Report-"+timestamps+".html";
    htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);
	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport.html");
	htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
	
	extent = new ExtentReports();
	
	extent.attachReporter(htmlReporter);
	extent.setSystemInfo("Host Name", "localhost");
	extent.setSystemInfo("Envernment", "QA");
	extent.setSystemInfo("user", "pavan");
	
	
	htmlReporter.config().setDocumentTitle("InetBancking Test Project");
	htmlReporter.config().setReportName("Functional Automation Test Report");
	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	htmlReporter.config().setTheme(Theme.STANDARD);
}
	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.CYAN));
	}
	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshorts\\"+tr.getName() +".png";
		File f = new File(screenshotPath);
		
		if(f.exists()) {
			try {
				logger.fail("ScreenShort is Below:" +logger.addScreenCaptureFromPath(screenshotPath));
			}catch(IOException e) {
				e.printStackTrace();
				
			}
		}
	}
	public void onTestSkipped(ITestResult tr) {
		
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	public void onFinesh(ITestContext testContext) {
		extent.flush();
	}
}
