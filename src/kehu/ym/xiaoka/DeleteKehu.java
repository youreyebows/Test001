package kehu.ym.xiaoka;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.ym.xiaoka.Base;
import utils.ym.com.Setting;

public class DeleteKehu extends Base{
	private String li1 = null;//获取列表中的电话号码
	private String li4 = null;//后去列表中的用户名称
	
	@Test(groups = { "客户-个人客户" })
	public void testDeleteKehu() {
		driver.get(baseUrl + "/member/list#");// 客户列表页面
		deleteUser();
	}
  
	private void deleteUser() {
		// 用户列表第一行
		WebElement webElement = driver.findElement(By.cssSelector(".table tr:nth-child(2)"));
		String list1 = webElement.findElement(By.cssSelector("td:nth-child(1)")).getText();
		String list4 = webElement.findElement(By.cssSelector("td:nth-child(4)")).getText();
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		// 点击下三角
		webElement.findElement(By.cssSelector(".btn-xs:not(a)")).click();
		// 点击删除
		webElement.findElement(By.cssSelector(".dropdown-menu li:nth-child(1)")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("确定"))));
		
		webElement.findElement(By.linkText("确定")).click();
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".table tr:nth-child(2)"))));
		
		vertifyDelete(list1,list4);
	}
	
	//验证是否删除成功
	private void vertifyDelete(String list1,String list4) {
		List<WebElement> webElement = driver.findElements(By.className("tr-hui"));

		for (WebElement webElement2 : webElement) {
			li1 = webElement2.findElement(By.cssSelector("td:nth-child(1)")).getText();
			li4 = webElement2.findElement(By.cssSelector("td:nth-child(4)")).getText();
			if (li1.equals(list1) && li4.equals(list4)) {
				Assert.assertTrue(false, "未能成功删除！在列表中仍能找到姓名为：" + Setting.Kehu_p_name_e + "的客户");
				return;
			}
		}
	}
}
