package DataProviders;

import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataProviderTest {

	
WebDriver driver;
	
	@BeforeClass
	void init() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(5000);
	}
	
	
	@Test(dataProvider="dp")
	void TestLogin(String user,String pass) throws InterruptedException
	{
		WebElement UsrName=driver.findElement(By.xpath("//input[@placeholder='Username']"));
		UsrName.sendKeys(user);
		Thread.sleep(3000);
		WebElement Pwd=driver.findElement(By.xpath("//input[@placeholder='Password']"));
		Pwd.sendKeys(pass);
		Thread.sleep(3000);
		WebElement Login=driver.findElement(By.xpath("//button[@type='submit']"));
		Login.click();
		Thread.sleep(3000);
		boolean status=driver.findElement(By.xpath("//img[@class='oxd-userdropdown-img']")).isDisplayed();
		Thread.sleep(3000);
		if(status)
		{
			driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		}
		else
			Assert.fail();
	}
	
	
	@AfterClass
	void TearDown()
	{
		driver.quit();
	}

	@DataProvider(name="dp" , indices= {1})
	Object[][] DataProv()
	{
		Object data[][] = {
				{"Srk","123"},
				{"Admin","admin123"},
				{"Admin","ad123"}	
		};
		return data;
	}
	
	
	
	
}
