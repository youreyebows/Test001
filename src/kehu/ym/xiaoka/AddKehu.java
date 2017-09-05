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

public class AddKehu extends Base {
	private String level_selected = null;
	private String telephone = null;//获取列表中的电话号码
	private String username = null;

	@Test (groups={"客户-个人客户"})
	public void testAddKehu() throws InterruptedException {
		driver.get(baseUrl + "/member/list");
		
		driver.findElement(By.linkText("客户")).click();
		// 点击“添加个人客户”按钮
		//driver.findElement(By.xpath("//button[@onclick=\"window.location='/member/addPersonal';\"]")).click();
		driver.findElement(By.xpath("//button[contains(@onclick,'/member/addPersonal')]")).click();
		
		addKehu();
		vertigyResults();// 验证是否添加成功
		
	}

	private void addKehu() throws InterruptedException {
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(Setting.Kehu_p_name);
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone")).sendKeys(Setting.Kehu_p_phone);
		driver.findElement(By.xpath("(//input[@name='gender'])[2]")).click();
		
		//选择用户等级Kehu_p_level
		Select level = new Select(driver.findElement(By.id("grade_id")));
		level.selectByIndex(1);
		level_selected = level.getFirstSelectedOption().getText();
		
		driver.findElement(By.id("idcard")).clear();
		driver.findElement(By.id("idcard")).sendKeys(Setting.Kuhu_p_sfz);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(Setting.Kehu_p_email);
	  	driver.findElement(By.id("plateNumber")).clear();
		driver.findElement(By.id("plateNumber")).sendKeys(Setting.Kehu_p_chepai);
		driver.findElement(By.id("memo")).clear();
		driver.findElement(By.id("memo")).sendKeys(Setting.Kehu_p_beizhu);
		driver.findElement(By.name("allowOverdraw")).click();//勾选允许签单
		driver.findElement(By.id("submit_button")).click();
		
		
		
	}
	
	/**
	 * 在用户列表中验证是否有刚刚添加的联系人名称和手机号
	 * @throws InterruptedException 
	 */
	private void vertigyResults(){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.equals(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".table tr:nth-child(2)"))));
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		List<WebElement> webElement = driver.findElements(By.className("tr-hui"));
		System.out.println("for之前");
		System.out.println(webElement);
		for (WebElement webElement2 : webElement) {
			System.out.println("进入了for");
			telephone = webElement2.findElement(By.cssSelector("td:nth-child(4)")).getText();
			username = webElement2.findElement(By.cssSelector("td:nth-child(1)")).getText();
			System.out.println("名称---》"+telephone);
			System.out.println("号码---》"+username);
			if (telephone.equals(Setting.Kehu_p_phone) && username.equals(Setting.Kehu_p_name)) {
				System.out.println("进入了for循环");
//				webElement2.findElement(By.xpath("//a[contains(@href,'editPersonal')]")).click();
				webElement2.findElement(By.cssSelector(".btn-xs:not(button)")).click();
				
				WebDriverWait wait1 = new WebDriverWait(driver, 5);
				wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("submit_button"))));
				System.out.println("即将进入checkEdit");
				checkEdit();
				System.out.println("出了checkEdit");
				return;
			}
		}
		
		System.out.println("for错误");
		Reporter.log("失败！添加的个人用户并没有在客户列表中。");
		Assert.assertTrue(false);

	}
	//验证点击编辑后，输入框的内容是否与输入的一致
	private void checkEdit() {
		
		System.out.println("进入了checkEdit");
		// 验证性别
		Assert.assertTrue(driver.findElement(By.xpath("(//input[@name='gender'])[2]")).isSelected());
		// 验证客户等级
		Select level1 = new Select(driver.findElement(By.id("grade_id")));
		Assert.assertEquals(level1.getFirstSelectedOption().getText(), level_selected,"addkehu，客户等级未保存成功");
		// 验证身份证
		Assert.assertEquals(driver.findElement(By.id("idcard")).getAttribute("value"), Setting.Kuhu_p_sfz);
		// 验证邮件
		Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), Setting.Kehu_p_email);
		// 验证车牌
		Assert.assertEquals(driver.findElement(By.id("plateNumber")).getAttribute("value"), Setting.Kehu_p_chepai);
		// 验证备注
		Assert.assertEquals(driver.findElement(By.id("memo")).getAttribute("value"), Setting.Kehu_p_beizhu);
		// 验证签单是否勾选
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='allowOverdraw']")).isSelected());
	}

}
