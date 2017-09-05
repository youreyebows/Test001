package kehu.ym.xiaoka;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.ym.xiaoka.Base;
import utils.ym.com.Setting;

public class EditKehu extends Base{
	private String telephone = null;//获取列表中的电话号码
	private String username = null;//后去列表中的用户名称

	@Test (groups={"客户-个人客户"})
	public void testEdiKehu() throws InterruptedException {
		driver.get(baseUrl + "/member/list#");//进入客户页面
		//找到AddKehu添加的用户后点击编辑
		findKehu();
		edit();
		vertifyEditKehu();
		
	}
	
	private void findKehu() throws InterruptedException{
		//用户列表第一行
		WebElement webElement2 = driver.findElement(By.cssSelector(".table tr:nth-child(2)"));
		//点击编辑按钮
		webElement2.findElement(By.cssSelector(".btn-xs:not(button)")).click();
		//等待直到成功跳转到编辑界面
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("submit_button"))));
	}
	
	private void edit() {
		driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys(Setting.Kehu_p_name_e);
	    driver.findElement(By.id("phoneShow")).clear();
	    driver.findElement(By.id("phoneShow")).sendKeys(Setting.Kehu_p_phone_e);
	    driver.findElement(By.xpath("(//input[@name='gender'])[1]")).click();//选择性别为男
/*	    Select levelGroups = new Select(driver.findElement(By.id("grade_id")));
	    String level = levelGroups.getFirstSelectedOption().getText();
*/	   
	    driver.findElement(By.id("idcard")).clear();
	    driver.findElement(By.id("idcard")).sendKeys(Setting.Kehu_p_sfz_e);
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys(Setting.Kehu_p_email_e);
	    driver.findElement(By.id("introducer")).clear();
	    driver.findElement(By.id("introducer")).sendKeys("1811304");//推荐人
	    driver.findElement(By.xpath("(//input[@name='introduceType'])[2]")).click();//服务人员推荐
	    driver.findElement(By.id("plateNumber")).clear();
	    driver.findElement(By.id("plateNumber")).sendKeys(Setting.Kehu_p_chepai_e);
	    driver.findElement(By.id("memo")).clear();
	    driver.findElement(By.id("memo")).sendKeys(Setting.Kehu_p_beizhu_e);
	    driver.findElement(By.name("allowOverdraw")).click();//再次点击，即不允许签单
		driver.findElement(By.id("submit_button")).click();//点击保存按钮
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table tr:nth-child(2)"))); 
	}
	
	private void vertifyEditKehu() {
		List<WebElement> webElement = driver.findElements(By.className("tr-hui"));

		for (WebElement webElement2 : webElement) {
			telephone = webElement2.findElement(By.cssSelector("td:nth-child(4)")).getText();
			username = webElement2.findElement(By.cssSelector("td:nth-child(1)")).getText();
			if (telephone.equals(Setting.Kehu_p_phone_e) && username.equals(Setting.Kehu_p_name_e)) {
				webElement2.findElement(By.cssSelector(".btn-xs:not(button)")).click();
				WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.urlContains("editPersonal")); 
				vertifyEdit();
				driver.navigate().back();
				return;
			}
		}
		
		Reporter.log("失败！没有找到刚刚编辑的用户。");
		Assert.assertTrue(false);
		
	}
	
	private void vertifyEdit() {
		//验证联系人
		System.out.println("哈哈啊哈"+driver.findElement(By.id("name")).getAttribute("value"));
		Assert.assertNotEquals(driver.findElement(By.id("name")).getAttribute("value"), Setting.Kehu_p_name_e);
		//验证手机号
		Assert.assertEquals(driver.findElement(By.id("phoneShow")).getAttribute("value"), Setting.Kehu_p_phone_e);
		// 验证性别
		Assert.assertTrue(driver.findElement(By.xpath("(//input[@name='gender'])[1]")).isSelected());
		// 验证客户等级
		/*Assert.assertEquals(
				driver.findElement(By.xpath("//select[@id='grade_id']/option[@selected='selected']")).getText(),
				Setting.Kehu_p_level_e);*/
		// 验证身份证
		Assert.assertEquals(driver.findElement(By.id("idcard")).getAttribute("value"), Setting.Kehu_p_sfz_e);
		// 验证邮件
		Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), Setting.Kehu_p_email_e);
		//验证推荐人
		Assert.assertEquals(driver.findElement(By.id("introducer")).getAttribute("value"), "1811304");
		//验证推荐人类型
		Assert.assertTrue(driver.findElement(By.xpath("(//input[@name='introduceType'])[2]")).isSelected());
		// 验证车牌
		Assert.assertEquals(driver.findElement(By.id("plateNumber")).getAttribute("value"), Setting.Kehu_p_chepai_e);
		// 验证备注
		Assert.assertEquals(driver.findElement(By.id("memo")).getAttribute("value"), Setting.Kehu_p_beizhu_e);
		// 验证签单是否勾选,应该是没有勾选的
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='allowOverdraw']")).isSelected());
	}

}
