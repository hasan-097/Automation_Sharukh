package DataDrivenTest;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class DataDrivenExample {

		
	WebDriver driver;
	String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\DataDrivenTest\\UserData.xlsx";	
	
		@BeforeClass
		void init() throws InterruptedException
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			Thread.sleep(5000);
		}
		
		
		@Test
		void TestLogin() throws InterruptedException, IOException
		{
			int row=ExcelUtils.getRowCount(filePath, "Sheet1");
			ExcelUtils.setCellData(filePath, "Sheet1",0,2,"Result");
				
				for(int i=1;i<=row;i++)
				{
					//getting the data from excel
					String usr=ExcelUtils.getCellData(filePath, "Sheet1", i, 0);
					String pwd=ExcelUtils.getCellData(filePath, "Sheet1", i, 1);
					
					//Passing the data into the fields
					WebElement UsrName=driver.findElement(By.xpath("//input[@placeholder='Username']"));
					UsrName.sendKeys(usr);
					Thread.sleep(3000);
					WebElement Pwd=driver.findElement(By.xpath("//input[@placeholder='Password']"));
					Pwd.sendKeys(pwd);
					Thread.sleep(3000);
					
					
					WebElement Login=driver.findElement(By.xpath("//button[@type='submit']"));
					Login.click();
					Thread.sleep(3000);
					
					//validation
					try {	
					if(driver.findElement(By.xpath("//img[@class='oxd-userdropdown-img']")).isDisplayed())
					{
						ExcelUtils.setCellData(filePath, "Sheet1",i,2,"Pass");
						ExcelUtils.setCellColorGreen(filePath, "Sheet1", i, 2);
						driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
						Thread.sleep(3000);
					}
					}				
					catch(Exception e)
					{
						ExcelUtils.setCellData(filePath, "Sheet1",i,2,"Fail");
						ExcelUtils.setCellColorRed(filePath, "Sheet1", i, 2);
					}
				}
			}				
				
		
		
		@AfterClass
		void TearDown()
		{
			driver.quit();
		}
	
}
