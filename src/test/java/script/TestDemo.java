package script;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
//i want to run only one script (this one) but on grid
public class TestDemo extends BaseTest
{
	@Test
	public void test1()
	{
		String v = Utility.getExcelData("./data/input.xlsx", "Sheet1", 0, 0);
		Reporter.log("Bhanu Executing test1...."+v,true);
//		Assert.fail();
	}
}
