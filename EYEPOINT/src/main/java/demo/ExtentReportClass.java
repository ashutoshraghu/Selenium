package demo;


import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReportClass {
	
	public static ExtentReports extent;
	public static ExtentTest extentTest;

	@BeforeTest
	public void setExtent(){
		 extent =new ExtentReports(System.getProperty("user.dir")+ "/REPORT1.html");
	
		//extent =new ExtentReports(System.getProperty("user.dir")+ "/Extent Reports/HcpTestki.html", true);	
		extent.addSystemInfo("Host Name", "Ashutosh pc");
		extent.addSystemInfo("User Name", "Ashutosh");
		extent.addSystemInfo("Environment", "QA");
	
		
	}	
		
}
