import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.junit.Assert; 
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class OpenBrowser {

	@Test
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeDriver dr=new ChromeDriver();
		dr.get("https://www.browserstack.com/");
		dr.manage().window().maximize();
		WebElement ele=dr.findElement(By.id("signupModalProductButton"));
		Assert.isTrue(ele.isDisplayed(), "Not displayed");
		Thread.sleep(10000);
		dr.quit();

	}

}
