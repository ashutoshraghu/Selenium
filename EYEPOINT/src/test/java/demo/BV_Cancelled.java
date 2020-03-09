package demo;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.LogStatus;

public class BV_Cancelled extends ExtentReportClass{

	public WebDriver driver;

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyy_MMddhhmmss").format(new Date());
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// After execution you will see a folder "Failed Tests Screenshots" under source folder
		String destination = System.getProperty("user.dir")+ "/Failed Tests Screenshots/" + screenshotName +"_" + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@Test(priority =12)
	public void Open_AppianRx() {

		System.setProperty("webdriver.chrome.driver", "C:\\Chrome driver 78\\chromedriver.exe");	
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("localhost:4200");

		extentTest = extent.startTest("BV - Covered");
		String title =driver.getTitle();
		Assert.assertEquals(title,"AppianRx");

	}


	@Test(priority =13, dependsOnMethods = "Open_AppianRx")
	public void AppianRx_SignIn() throws InterruptedException {

		Thread.sleep(500);
		driver.findElement(By.id("login")).click();

		// Handling the Sign In window 
		String ParentDriver=driver.getWindowHandle();
		Set<String> Windows=driver.getWindowHandles();
		int numberOfWindows = Windows.size();

		if(numberOfWindows==2) 

			for(String handle: Windows) 
			{
				if(!handle.equals(ParentDriver)) {
					driver.switchTo().window(handle);
					Thread.sleep(1000);
					driver.manage().window();
				}		 
			}

		Thread.sleep(100);
		driver.findElement(By.xpath("//*[@id=\"i0116\"]")).sendKeys("ashutosh@ssrx.com");

		driver.findElement(By.xpath("//*[@id=\'idSIButton9\']")).click();

		Thread.sleep(2000);
		driver.findElement(By.id("i0118")).sendKeys("2112Ashu");
		Thread.sleep(100);
		driver.findElement(By.xpath("//*[@id=\'idSIButton9\']")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\'idSIButton9\']")).click();

		driver.switchTo().window(ParentDriver);

	}

	@Test(priority =14, dependsOnMethods = "AppianRx_SignIn")
	public void Open_Intake() throws InterruptedException {
		Thread.sleep(2000);
		
		
		
		driver.findElement(By.xpath("//*[@id=\"m_ver_menu\"]/ul/li[2]/a/i/img")).click();  
		Thread.sleep(3000);

	}

	@Test(priority =15, dependsOnMethods = "Open_Intake")
	public void Unattended_Faxes() throws InterruptedException {
		
		driver.findElement(By.xpath("//*[@id=\"mat-radio-3\"]/label/div[1]/div[1]")).click();

		driver.findElement(By.xpath("//*[@id=\"cdk-accordion-child-0\"]/div/mat-card/form/mat-card-content/table/tbody/tr[1]/td[1]/img")).click();
		Thread.sleep(2000);

		// Close Unattended_Faxes Tab
		driver.findElement(By.xpath("//*[@id=\"mat-expansion-panel-header-0\"]/span[1]/mat-panel-title")).click();

	}

	@Test(priority =16, dependsOnMethods = "Unattended_Faxes")
	public void Assigned_Faxes() throws InterruptedException {

		Thread.sleep(2000);

		// Open Assigned faxes Tab

		driver.findElement(By.xpath("//*[@id=\"mat-expansion-panel-header-1\"]/span[1]/mat-panel-title")).click();

		
		driver.findElement(By.xpath("//*[@id=\"mat-radio-7\"]/label/div[1]/div[1]")).click();
		
		
		driver.findElement(By.xpath("//*[@id=\"cdk-accordion-child-1\"]/div/mat-card/form/mat-card-content/table/tbody/tr/td[1]/img")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"mat-checkbox-1\"]/label")).click();

		driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/m-intake-popup/mat-card[2]/form/button[1]")).click();

		driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/button[1]")).click();
		
	}

	@Test(priority =17, dependsOnMethods = "Assigned_Faxes")
	public void SelectPatientTo_Case() throws InterruptedException {
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"mat-radio-9\"]/label/div[1]")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.id("saveAndNext")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/button[1]")).click();

	} 

	@Test(priority =18, dependsOnMethods = "SelectPatientTo_Case")
	public void SelectHCPTo_Case() throws InterruptedException {
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"mat-radio-10\"]/label/div[1]")).click();  
		driver.findElement(By.id("saveAndNext")).click();

	} 


	@Test(priority =19, dependsOnMethods = "SelectHCPTo_Case")
	public void SelectSiteTo_Case() throws InterruptedException {

		Thread.sleep(4000);

		driver.findElement(By.xpath("//*[@id=\"mat-radio-11\"]/label/div[1]")).click();  
		Thread.sleep(1000); 
		driver.findElement(By.id("saveAndNext")).click();

	}

	@Test(priority =20, dependsOnMethods = "SelectSiteTo_Case")
	public void Service_Validation() throws InterruptedException {

		WebElement element = driver.findElement(By.xpath("//*[@id=\"mat-expansion-panel-header-5\"]/span[1]/mat-panel-title"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);

		// Edit document
		driver.findElement(By.xpath("//*[@id=\"cdk-accordion-child-5\"]/div/mat-card/table/tbody/tr/td[1]/div/div[2]/img")).click();

		Thread.sleep(1000);

		// Open document dialog box to select all documents 
		driver.findElement(By.xpath("//*[@id=\"mat-select-25\"]/div/div[1]/span")).click();

		// Select Enrollment
		driver.findElement(By.xpath("//*[@id=\"mat-option-107\"]/mat-pseudo-checkbox")).click();   

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.id("mat-dialog-1")), 0, 0);
		int xCoordinate = 0;
		int yCoordinate = 0;
		actions.moveByOffset(xCoordinate, yCoordinate).click().build().perform();

		// Save
		driver.findElement(By.xpath("//*[@id=\"mat-dialog-1\"]/m-table-popup/mat-card[2]/form/button[1]")).click();

	}


	@Test(priority =21, dependsOnMethods = "Service_Validation")
	public void Check_Benefit() throws InterruptedException {

		Thread.sleep(3000);

		// Check Benefit
		driver.findElement(By.xpath("//*[@id=\"edit\"]")).click();

		Thread.sleep(2500);

		// RTME  
		driver.findElement(By.xpath("//*[@id=\"mat-select-40\"]/div/div[1]/span")).click();
		driver.findElement(By.xpath("//*[@id=\"mat-option-179\"]/span")).click();

		//java script executor object 
		JavascriptExecutor jse1=(JavascriptExecutor) driver;
		//
		//Scroll to the bottom of the Web page
		Thread.sleep(1000);
		jse1.executeScript("window.scrollTo(0,document.body.scrollHeight)");

		// BV Outcome
		driver.findElement(By.xpath("//*[@id=\"mat-select-46\"]/div/div[1]/span")).click();
		driver.findElement(By.xpath("//*[@id=\"mat-option-192\"]/span")).click();

		//java script executor object 
		JavascriptExecutor jse2=(JavascriptExecutor) driver;
		//
		//Scroll to the bottom of the Web page
		Thread.sleep(1000);
		jse2.executeScript("window.scrollTo(document.body.scrollHeight,0)");

		// Save
		driver.findElement(By.id("saveAndNext")).click();

	}

	@Test(priority =22, dependsOnMethods = "Check_Benefit")
	public void Proceed() throws InterruptedException {

		Thread.sleep(1000);

		// CheckBox
		driver.findElement(By.xpath("//*[@id=\"mat-radio-16\"]/label/div[1]/div[1]")).click();

		//Proceed Button
		driver.findElement(By.xpath("//*[@id=\"cdk-accordion-child-46\"]/div/mat-card/button/span")).click();	

		//Cancel button
		driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/button[1]")).click();

		//Service close button
		driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/button[2]")).click();

	}
	
//	@Test(priority =23, dependsOnMethods = "Proceed")
//	public void HCP_FAX() throws InterruptedException {
//
//		Thread.sleep(1000);
//
//		// Fax Number
//		driver.findElement(By.xpath("//*[@id=\"mat-input-61\"]")).sendKeys("000-000-0000");
//
//		driver.findElement(By.id("saveAndNext")).click();
//
//	}
//
//	@Test(priority =24, dependsOnMethods = "HCP_FAX")
//	public void KAM_Email() throws InterruptedException {
//
//		Thread.sleep(1000);
//
//		// Email Id
//		driver.findElement(By.xpath("//*[@id=\"mat-input-62\"]")).sendKeys("abc@gmail.com");
//
//		driver.findElement(By.id("saveAndNext")).click();
//
//	}

	@AfterMethod
	public void teardown(ITestResult result) throws IOException {

		if(result.getStatus()== ITestResult.FAILURE)
		{
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS : " + result.getName());
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS : " + result.getThrowable());

			String screenshotPath = BV_Cancelled.getScreenshot(driver,result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			extentTest.log(LogStatus.SKIP, "TEST CASE SKIPPED IS : " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS : " + result.getName());
		}
		 extent.endTest(extentTest);
		
	}

	@AfterTest
	public void endreport(){
extent.flush();
		extent.close();
		//driver.close();
	}

}