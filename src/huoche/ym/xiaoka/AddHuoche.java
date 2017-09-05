package huoche.ym.xiaoka;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.ym.xiaoka.Base;
import utils.ym.com.Setting;

public class AddHuoche extends Base{
	
  @Test (priority=1,groups={"货车类型"})
  public void testAddHuoche() {
	  driver.get(baseUrl + "/freight/truckTypes");
	    //WebElement element = driver.findElement(By.id("dialog_car"));
	    
	    driver.findElement(By.xpath("//div[3]/div/div/div/div[2]/button")).click();//点击添加货车类型
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	    
	    driver.findElement(By.id("typeName")).clear();
	    driver.findElement(By.id("typeName")).sendKeys(Setting.HuoChe_Type);
	    driver.findElement(By.id("len")).clear();
	    driver.findElement(By.id("len")).sendKeys(Setting.HuoChe_Length);
	    driver.findElement(By.id("width")).clear();
	    driver.findElement(By.id("width")).sendKeys(Setting.HuoChe_Width);
	    driver.findElement(By.id("height")).clear();
	    driver.findElement(By.id("height")).sendKeys(Setting.HuoChe_Height);
	    driver.findElement(By.id("carryingCapacity")).clear();
	    driver.findElement(By.id("carryingCapacity")).sendKeys(Setting.HuoChe_Weight);
	    driver.findElement(By.id(Setting.HuoChe_RuCheng)).click();//入城证
	    driver.findElement(By.id("sequence")).clear();
	    driver.findElement(By.id("sequence")).sendKeys(Setting.HuoChe_Sequence);
	    driver.findElement(By.id("save")).click();
	    
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    List<WebElement> lists = driver.findElements(By.className("panel-group"));
	    for (WebElement list : lists) {
			if(list.getAttribute("typename").equals(Setting.HuoChe_Type))
			{
				
				Assert.assertTrue(true,"成功：添加货车类型成功！");
				Reporter.log("添加成功！添加的货车类型名称为："+Setting.HuoChe_Type);
				return;
			}		
		}
	    
	    Assert.assertTrue(false,"失败：添加货车类型失败！");
  }
  
}
