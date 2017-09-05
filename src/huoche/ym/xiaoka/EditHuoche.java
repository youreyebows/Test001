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
 * 编辑AddHuoche添加的货车类型
 * 
 * @author Administrator
 *
 */
public class EditHuoche extends Base {
	private String id;// 需编辑的货车类型的ID

	@Test(priority=2,groups = { "货车类型" })
	public void testEditHuoche() {
		driver.get(baseUrl + "/freight/truckTypes");

		List<WebElement> webElements = driver.findElements(By.className("panel-group"));
		for (WebElement webElement : webElements) {
			if (webElement.getAttribute("typename").equals(Setting.HuoChe_Type)) {
				id = webElement.getAttribute("id");
				Reporter.log("编辑的货车类型名称为："+webElement.getAttribute("typename"));
				edit();
				return;
			}
		}
		Assert.assertFalse(true, "失败：没有找到刚刚添加的货车类型！");
	}

	private void edit() {

		// 点击编辑按钮
		driver.findElement(By.xpath(".//*[@id='" + id + "']/div/div/div/div/button[1]")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(driver.findElement(By.id("typeName2")).getAttribute("value"), Setting.HuoChe_Type);
		Assert.assertEquals(driver.findElement(By.id("len2")).getAttribute("value"), Setting.HuoChe_Length);
		Assert.assertEquals(driver.findElement(By.id("width2")).getAttribute("value"), Setting.HuoChe_Width);
		Assert.assertEquals(driver.findElement(By.id("height2")).getAttribute("value"), Setting.HuoChe_Height);
		Assert.assertEquals(driver.findElement(By.id("carryingCapacity2")).getAttribute("value"),
				Setting.HuoChe_Weight);

		driver.findElement(By.id("typeName2")).clear();
		driver.findElement(By.id("typeName2")).sendKeys(Setting.HuoChe_Type_e);
		driver.findElement(By.id("len2")).clear();
		driver.findElement(By.id("len2")).sendKeys(Setting.HuoChe_Length_e);
		driver.findElement(By.id("width2")).clear();
		driver.findElement(By.id("width2")).sendKeys(Setting.HuoChe_Width_e);
		driver.findElement(By.id("height2")).clear();
		driver.findElement(By.id("height2")).sendKeys(Setting.HuoChe_Height_e);
		driver.findElement(By.id("carryingCapacity2")).clear();
		driver.findElement(By.id("carryingCapacity2")).sendKeys(Setting.HuoChe_Weight_e);
		driver.findElement(By.id(Setting.HuoChe_RuCheng_e)).click();// 入城证
		driver.findElement(By.id("sequence2")).clear();
		driver.findElement(By.id("sequence2")).sendKeys(Setting.HuoChe_Sequence_e);
		driver.findElement(By.id("update")).click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// lists是找到编辑后的那一行
		List<WebElement> lists = driver.findElements(By.className("panel-group"));
		for (WebElement list : lists) {
			if (list.getAttribute("typename").equals(Setting.HuoChe_Type_e)) {

				// 找到修改后所在行的id
				id = list.getAttribute("id");
				driver.findElement(By.xpath(".//*[@id='" + id + "']/div/div/div/div/button[1]")).click();// 单击编辑按钮
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Assert.assertEquals(driver.findElement(By.id("typeName2")).getAttribute("value"),
						Setting.HuoChe_Type_e);
				Assert.assertEquals(driver.findElement(By.id("len2")).getAttribute("value"), Setting.HuoChe_Length_e);
				Assert.assertEquals(driver.findElement(By.id("width2")).getAttribute("value"), Setting.HuoChe_Width_e);
				Assert.assertEquals(driver.findElement(By.id("height2")).getAttribute("value"),
						Setting.HuoChe_Height_e);
				Assert.assertTrue(driver.findElement(By.id(Setting.HuoChe_RuCheng_e)).isSelected());
				Assert.assertEquals(driver.findElement(By.id("carryingCapacity2")).getAttribute("value"),
						Setting.HuoChe_Weight_e);
				driver.findElement(By.id("close_modal_update")).click();
				Assert.assertTrue(true, "成功：编辑货车类型成功！");
				Reporter.log("编辑成功！");
				return;
			}
		}
		Assert.assertTrue(false, "失败：编辑货车类型失败！");
	}

}
