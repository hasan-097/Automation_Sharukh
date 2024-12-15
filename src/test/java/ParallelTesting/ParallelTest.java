package ParallelTesting;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class ParallelTest {
	
	WebDriver driver;
	
	@BeforeClass
	@Parameters({"browser"})
	void init(String browser ) throws InterruptedException
	{
		switch(browser.toLowerCase())
		{
			case "chrome" :driver=new ChromeDriver();break;
			case "edge" :driver=new EdgeDriver();break;
			default:System.out.println("Invalid Browser");return;
		}
		
		driver.manage().window().maximize();
		driver.get("https://support.orangehrm.com/portal/en/signin");
		Thread.sleep(5000);
	}
	
	@Test
	void TestLogo()
	{
		WebElement logo=driver.findElement(By.xpath("//div[@title='OrangeHRM']"));
		assertTrue(logo.isDisplayed());
	}
	
	@AfterClass
	void TearDown()
	{
		driver.quit();
	}
	

}
