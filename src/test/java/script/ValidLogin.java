package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.HomePage;
import page.LoginPage;

public class ValidLogin extends BaseTest
{
	@Test(priority = 1)
	public void testValidLogin()
	{
			String un=Utility.getExcelData(XLPATH, "ValidLogin",1,0);
			String pw=Utility.getExcelData(XLPATH, "ValidLogin",1,1);
//			1. enter valid user name
			LoginPage loginPage=new LoginPage(driver);
			loginPage.setUserName(un);
			
//			2. enter valid password
			loginPage.setPassword(pw);
			
//			3. click go button
			loginPage.clicGoButton();
			
//			4. verify that home page is displayed
			HomePage homePage=new HomePage(driver);
			boolean result = homePage.verifyHomePageIsDisplayed(wait);
			Assert.assertTrue(result);
	}
}
