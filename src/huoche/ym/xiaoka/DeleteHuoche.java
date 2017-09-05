package huoche.ym.xiaoka;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.ym.xiaoka.Base;
import utils.ym.com.Setting;

/**
 * 删除EditHuoche编辑后的货车类型
 * @author Administrator
 *
 */
public class DeleteHuoche extends Base{
	private String id;
	
  @Test (priority=3,groups={"货车类型"})
  public void testDeleteHuoche() {
		driver.get(baseUrl + "/freight/truckTypes");
		List<WebElement> lists = driver.findElements(By.className("panel-group"));
		boolean flag = true;
		for (WebElement list : lists) {
			if (list.getAttribute("typename").equals(Setting.HuoChe_Type_e)) {
				flag = false;
				id = list.getAttribute("id");
				driver.findElement(By.xpath(".//*[@id='"+id+"']/div/div/div/div/button[2]")).click();//点击删除按钮
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//取消删除方法
				cancelDelete();
				//确定删除方法
				confirmDelete();
				return ;
			}				
		}
		
		if(flag){
			Assert.assertFalse(false);
			Reporter.log("失败！没有找到要删除的货车类型");
		}
  }
  
	private void cancelDelete(){
		//取消删除
		driver.findElement(By.linkText("取消")).click();
		List<WebElement> lists1 = driver.findElements(By.className("panel-group"));
		Boolean flag= false;
		
		for (WebElement list1 : lists1) {
			if (list1.getAttribute("typename").equals(Setting.HuoChe_Type_e)) //取消删除后列表中有该类型，则取消功能完成
				flag = true;	
		}
		if(flag)
			Assert.assertTrue(true,"成功：点击取消删除按钮后，该类型存在！");
		else
			Assert.assertFalse(true,"失败：点击取消删除按钮后，该类型不存在！");
	}
	
	private void confirmDelete(){
		//确定删除
		driver.findElement(By.xpath(".//*[@id='"+id+"']/div/div/div/div/button[2]")).click();//点击删除按钮
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.linkText("确定")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> lists2 = driver.findElements(By.className("panel-group"));
		Boolean flag= true;
		
		for (WebElement list2 : lists2) {
			if (list2.getAttribute("typename").equals(Setting.HuoChe_Type_e)) //确定删除后列表中有该类型，则删除功能有问题
				flag = false;	
		}
		if(flag)
		{
			Assert.assertTrue(true,"成功：点击确定删除按钮后，该类型不存在！");
			Reporter.log("删除成功！");
		}
		else
			Assert.assertFalse(true,"失败：点击确定删除按钮后，该类型存在！");
	}
	
}
