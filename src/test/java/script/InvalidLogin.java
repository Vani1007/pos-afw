package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.LoginPage;

public class InvalidLogin extends BaseTest
{
	@Test(priority = 2)
	public void testInvalidLogin()
	{
		String un=Utility.getExcelData(XLPATH,"InvalidLogin",1,0);
		String pw=Utility.getExcelData(XLPATH,"InvalidLogin",1,1);
		String eErrMsg=Utility.getExcelData(XLPATH,"InvalidLogin",1,2);
		
//		1. enter invalid user name
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName(un);
//		2. enter invalid password
		loginPage.setPassword(pw);
//		3. click on go button
		loginPage.clicGoButton();
//		4. verify that error message is displayed
		boolean result = loginPage.verifyErrMsgIsDisplayed(wait,eErrMsg);
		Assert.assertTrue(result);
	}
}
