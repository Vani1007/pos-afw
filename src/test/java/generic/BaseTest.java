package generic;

import java.io.File;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest implements IAutoConst{

	public WebDriver driver;
	public WebDriverWait wait;
	
	@Parameters({"grid","gridURL","browser","env"})
	@BeforeMethod
	public void preCondition(@Optional(GRID)String grid,@Optional(GRIDURL)String gridURL,@Optional(BROWSER)String browser,@Optional(ENV)String env) throws Exception
	{
		if(grid.equalsIgnoreCase("yes"))
		{
			URL hub=new URL(gridURL);
			if(browser.equalsIgnoreCase("chrome"))
			{
				ChromeOptions browserOptions=new ChromeOptions();
				driver=new RemoteWebDriver(hub,browserOptions);
				Reporter.log("Open Chrome in Grid",true);
			}
			else
			{
				EdgeOptions browserOptions=new EdgeOptions();
				driver=new RemoteWebDriver(hub,browserOptions);
				Reporter.log("Open Edge in Grid",true);
			}
			
			
		}
		else
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				driver=new ChromeDriver();
				Reporter.log("Open Chrome in Local",true);
			}
			else
			{
				driver=new EdgeDriver();
				Reporter.log("Open Edge in Local",true);
			}
		}
		
		
		driver.manage().window().maximize();
		Reporter.log("maximize the browser",true);
		
		String appURL=Utility.getProperty(env,"APPURL");
		driver.get(appURL);
		Reporter.log("enter the app URL:"+appURL,true);
		
		
		int ITO=Integer.parseInt(Utility.getProperty(env,"ITO"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ITO));
		Reporter.log("Set ITO:"+ITO,true);
		
		
		int ETO=Integer.parseInt(Utility.getProperty(env,"ETO"));
		wait=new WebDriverWait(driver, Duration.ofSeconds(ETO));
		Reporter.log("Set ETO:"+ETO,true);
	}
	
	@AfterMethod
	public void postCondition(ITestResult testResult) throws Exception
	{
		String testName=testResult.getName();
		int status=testResult.getStatus();//1-PASS 2-FAIL 3-SKIP
		
		if(status==2)
		{
			TakesScreenshot t=(TakesScreenshot)driver;
			File srcFile = t.getScreenshotAs(OutputType.FILE);
			
			String path=SCREENSHOT+testName+".png";
			File dstFile = new File(path);
			
			FileUtils.copyFile(srcFile, dstFile);
			Reporter.log("Test:"+testName+" is Failed, hence screenshot has been taken:"+path,true);
		}
		else
		{
			Reporter.log("Test:"+testName+" is PASSED, hence NO screenshot has been taken:",true);
		}
		driver.quit();
		Reporter.log("Close the browser",true);
	}
}
