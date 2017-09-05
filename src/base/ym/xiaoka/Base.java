package base.ym.xiaoka;

import static org.junit.Assert.assertNotEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utils.ym.com.UserConfig;

public class Base {

	public static WebDriver driver;
	public static String baseUrl = "http://123.56.82.81:8080/server2/";

	@BeforeSuite
	public void beforeSuite() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(baseUrl);
		
		String title = driver.getTitle();
		driver.findElement(By.id("username")).sendKeys(UserConfig.Login_user);
		driver.findElement(By.id("password")).sendKeys(UserConfig.Login_pwd);
		driver.findElement(By.name("captcha")).sendKeys(UserConfig.yzm);
		driver.findElement(By.xpath(".//*[@id='login_button']/div/h2")).click();
		assertNotEquals(driver.getTitle(), title);
	}
	
	@AfterSuite
	public void afterSuite() {
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('客户-高级查询功能完毕');");*/
	}

}
