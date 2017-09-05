package kehu.ym.xiaoka;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.ym.xiaoka.Base;
import utils.ym.com.Setting;

public class AccountKehu extends Base {
	private String list1 = null;
	private String list4 = null;
	private String amount = null; //金额
	private String remarks = null;
	private int count =0;

	@Test (groups={"客户-个人客户"})
	public void testMoneyKehu() {
		driver.get(baseUrl + "/member/list#");// 客户列表页面
		findUser();
		
		rechargeAndVertify(18);//充值18元
		rechargeAndVertify(0);//充值0元
		rechargeAndVertify(-6);//充值-6元
	}

	private void findUser() {
		// 用户列表第一行
		WebElement webElement = driver.findElement(By.cssSelector(".table tr:nth-child(2)"));

		// 点击下三角
		webElement.findElement(By.cssSelector(".btn-xs:not(a)")).click();
		webElement.findElement(By.cssSelector(".dropdown-menu li:nth-child(2)")).click();// 点击账户明细
		
	}
	
	private void rechargeAndVertify(int m) {
			recharge(m);
			vertify(m);
	}

	private void recharge(int money) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("money"))));
		// 充值money元
		driver.findElement(By.id("money")).clear();
		driver.findElement(By.id("money")).sendKeys(""+money);
		driver.findElement(By.id("memo")).clear();
		driver.findElement(By.id("memo")).sendKeys("备注个"+money+"元");
		driver.findElement(By.id("submit_button")).click();
		driver.findElement(By.linkText("确定")).click();
	}
	
	private void vertify(int money) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("submit_button")));
		
		//.table tr:nth-child(1) td:nth-child(3)第一个金额
		//.table tr:nth-child(1) td:nth-child(8)第一个备注
		amount = driver.findElement(By.cssSelector(".table tr:nth-child(1) td:nth-child(3)")).getText();
		remarks = driver.findElement(By.cssSelector(".table tr:nth-child(1) td:nth-child(8)")).getText();
		
		List<WebElement> lists = driver.findElements(By.cssSelector(".table tr"));
		
		if (money == 0) { //充值金额为0时不形成充值记录
			Assert.assertEquals(lists.size(), count, "Fail！充值为0元时充值成功了！");
		} else if(money > 0) {
			Assert.assertEquals(amount, "+"+money);//金额
			Assert.assertEquals(remarks, "备注个"+money+"元");//备注
		} else {
			Assert.assertEquals(amount, ""+money);//金额
			Assert.assertEquals(remarks, "备注个"+money+"元");//备注
		}
		
		count = lists.size();
	}
}
