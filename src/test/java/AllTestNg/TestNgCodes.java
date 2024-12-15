package AllTestNg;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNgCodes {

	
	@BeforeClass
	void SetUp()
	{
		System.out.println("This is the set up will run once before all the test");
	}
	
	@BeforeTest
	void bf()
	{
		System.out.println("This will run before each of the test");
	}
	
	@BeforeMethod
	void f()
	{
		System.out.println("This will run before each of the method");
	}
	@Test
	void m()
	{
		System.out.println("This is test m");
	}
	
	@Test
	void a()
	{
		System.out.println("This is test a");
		Assert.fail();
	}
	
	@Test(dependsOnMethods={"a"})
	void c()
	{
		System.out.println("This is test c");
	}
	
	@AfterMethod
	void af()
	{
		System.out.println("This will run after each of the method");
	}
	
	@AfterTest
	void g()
	{
		System.out.println("This will run after each of the test");
	}
	
	
	@AfterClass
	void TearDown()
	{
		System.out.println("This is the set up will run once after all the test");
	}
}
