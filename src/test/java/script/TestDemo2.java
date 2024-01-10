package script;

import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
//i want to run only one script (this one) but on grid
public class TestDemo2 extends BaseTest
{
	@Test
	public void test1()
	{
		Reporter.log("Ravi Executing test1....",true);
	}
}
