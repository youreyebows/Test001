package kehu.ym.xiaoka;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.ym.xiaoka.Base;
import utils.ym.com.Util;

public class Search extends Base{
	Boolean flag;

	@Test(groups = { "客户-个人客户" })
	public void testSearchKKehu() {
		driver.get(baseUrl + "/member/list");
		
		search(null, "18113041111111", "杨", null, null, null, null, null, null);
		search(null, "135", null, null, null, null, null, null, null);
		search(null, null, "杨", null, null, null, null, null, null);
		search(null, null, null, "A", null, null, null, null, null);
		search(null, null, null, null, "2017-07-01", "2017-07-10", null, null, null);
		search(null, null, null, null, null, null, "用户等级", null, null);
		search(null, null, null, null, null, null, null, "个人用户", null);
		search(null, null, null, null, null, null, null, "企业用户", null);
		search(null, null, null, null, null, null, null, null, "是");
	}

  /**
   * 客户-高级查询-往查询输入框中输入值
   * @param company 所属公司
   * @param phone 客户电话
   * @param name 客户姓名
   * @param carNum 车牌号
   * @param startDate 注册时间-起始时间
   * @param endDate 注册时间-结束时间
   * @param level 用户等级
   * @param userType 用户类型
   * @param free 是否签单
   */
	private void search(String company, String phone, String name, String carNum, String startDate, String endDate,
			String level, String userType, String free) {
		
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.equals(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table tr:nth-child(2)")));
		
		//所属公司
		if (company != null && company.length() != 0) {
			driver.findElement(By.className("multiselect")).click();
			String tCompany = null;
			flag = true;
			List<WebElement> webElements = driver.findElements(By.className("checkbox"));
			for (WebElement webElement : webElements) {
				tCompany = webElement.getText();
				if (tCompany.contains(company)) {
					flag = false;
					webElement.click();
				}
			}
			if (flag) {
				Reporter.log("客户-高级查询，所属公司中没有： "+company);
			}
			
		}
		
		//客户电话
		if (phone != null && phone.length() != 0) {
			driver.findElement(By.id("phone")).clear();
			driver.findElement(By.id("phone")).sendKeys(phone);
		}
		
		//客户姓名
		if (name != null && name.length() != 0) {
			driver.findElement(By.id("name")).clear();
			driver.findElement(By.id("name")).sendKeys(name);
		}
		
		//车牌号
		if (carNum !=null && carNum.length() !=0) {
			driver.findElement(By.id("jiant")).click();
			WebDriverWait wait1 = new WebDriverWait(driver, 5);
			wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("carNumber")))); 
			driver.findElement(By.id("carNumber")).clear();
			driver.findElement(By.id("carNumber")).sendKeys(carNum);
		}
		
		//注册时间
		if (startDate !=null && startDate.length() !=0 && endDate !=null && endDate.length() !=0) {
			if (!driver.findElement(By.id("carNumber")).isDisplayed()) {
				driver.findElement(By.id("jiant")).click();
				WebDriverWait wait2 = new WebDriverWait(driver, 5);
				wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("carNumber")))); 
			}
			driver.findElement(By.id("startTime")).sendKeys(startDate);
			driver.findElement(By.id("endTime")).sendKeys(endDate);
		}
		
		//用户等级，默认查询第一个用户等级
		if (level !=null && level.length() !=0) {
			if (!driver.findElement(By.id("carNumber")).isDisplayed()) {
				driver.findElement(By.id("jiant")).click();
				WebDriverWait wait3 = new WebDriverWait(driver, 5);
				wait3.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("carNumber")))); 
			}
			driver.findElement(By.cssSelector(".media-wit input:nth-child(2)")).click();//自动选择第一个等级
			level = driver.findElement(By.cssSelector(".media-wit span:nth-child(3)")).getText();
		}
		
		//用户类型
		if (userType !=null && userType.length() !=0) {
			if (!driver.findElement(By.id("carNumber")).isDisplayed()) {
				driver.findElement(By.id("jiant")).click();
				WebDriverWait wait4 = new WebDriverWait(driver, 5);
				wait4.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("carNumber")))); 
			}
			if (userType.equals("个人用户")) {
				driver.findElement(By.xpath("//input[@value='0']")).click();
			} else if (userType.equals("企业用户")) {
				driver.findElement(By.xpath("//input[@value='1'][@name='type']")).click();
			}else {
				System.out.println("客户-高级查询-用户类型，并未找到："+userType+"的类型");
				Reporter.log("客户-高级查询-用户类型，并未找到："+userType+"的类型");
			}
		}
		
		//是否签单
		if (free !=null && free.length() !=0) {
			if (!driver.findElement(By.id("carNumber")).isDisplayed()) {
				driver.findElement(By.id("jiant")).click();
				WebDriverWait wait4 = new WebDriverWait(driver, 5);
				wait4.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("carNumber")))); 
			}
			if (free.equals("是")) {
				driver.findElement(By.xpath("//input[@value='true']")).click();
			} else if (free.equals("否")) {
				driver.findElement(By.xpath("//input[@value='false']")).click();
			}else {
				System.out.println("客户-高级查询-是否签单，并没有："+free+"的选项");
				Reporter.log("客户-高级查询-是否签单，并没有："+free+"的选项");
			}
		}
		
		driver.findElement(By.id("searchButton")).click();
		vertify(company, phone, name, carNum, startDate, endDate, level, userType, free);
	}
	
	private void vertify(String company, String phone, String name, String carNum, String startDate, String endDate,
			String level, String userType, String free){
		//问题
		WebDriverWait wait = new WebDriverWait(driver, 5);
		try {
		wait.equals(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".table tr:nth-child(2)"))));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("没有符合条件的用户");
			Reporter.log("没有符合条件的用户");
			return;
		}
		
		//所属公司
		if (company != null && company.length() != 0) {
			List<WebElement> companys = driver.findElements(By.cssSelector(".tr-hui td:nth-child(8)"));
			for (WebElement webElement : companys) {
				Assert.assertTrue(webElement.getText().contains(company),"客户-高级查询，根据所属公司："+company+" 查询失败");
			}
		}
		
		//客户电话
		if (phone != null && phone.length() != 0) {
			List<WebElement> phones = driver.findElements(By.cssSelector(".tr-hui td:nth-child(4)"));
			for (WebElement webElement : phones) {
				Assert.assertTrue(webElement.getText().contains(phone),"客户-高级查询，根据客户电话："+phone+" 查询失败");
			}
		}
		
		// 客户姓名
		if (name != null && name.length() != 0) {
			List<WebElement> names = driver.findElements(By.cssSelector(".tr-hui td:nth-child(1)"));

			for (WebElement webElement : names) {
				Assert.assertTrue(webElement.getText().contains(name), "客户-高级查询，根据客户姓名：" + name + " 查询失败");
			}
		}
		
		// 车牌号
		if (carNum != null && carNum.length() != 0) {
			List<WebElement> licenseNums = driver.findElements(By.cssSelector(".tr-hui td:nth-child(10)"));
			String attribute1 = carNum.toLowerCase();
			for (WebElement webElement : licenseNums) {
				String webText1 = webElement.getText().toLowerCase();
				
				Assert.assertTrue(webText1.contains(attribute1),"客户-高级查询，根据车牌："+carNum+" 查询失败");
			}
		}
		
		// 注册时间
		if (startDate != null && startDate.length() != 0 && endDate != null && endDate.length() != 0) {
			List<WebElement> registerDates = driver.findElements(By.cssSelector(".tr-hui td:nth-child(12)"));
			for (WebElement webElement : registerDates) {
				Assert.assertTrue(Util.isInDate(webElement.getText(), startDate, endDate));
			}
		}
		
		// 用户等级
		if (level != null && level.length() != 0) {
			//列表中的用户等级集合
			List<WebElement> levels = driver.findElements(By.cssSelector(".tr-hui td:nth-child(9)"));
			for (WebElement webElement : levels) {
				Assert.assertEquals(webElement.getText(), level,"客户-用户等级-vertify失败");
			}
			
		}
		
		// 用户类型
		if (userType != null && userType.length() != 0) {
			List<WebElement> types = driver.findElements(By.cssSelector(".tr-hui td:nth-child(2)"));
			if (userType.equals("个人用户")) {
				for (WebElement webElement : types) {
					Assert.assertEquals(webElement.getText(), "-","客户-高级查询-用户类型-个人用户，查询个人用户失败");
				}
			} else if (userType.equals("企业用户")) {
				for (WebElement webElement : types) {
					Assert.assertNotEquals(webElement.getText(), "-","客户-高级查询-用户类型-企业用户，查询个人用户失败");
				}
			}else {
				Reporter.log("客户-高级查询-用户类型-attribute参数有错！");
			}
		}
		
		// 是否签单
		if (free != null && free.length() != 0) {
			List<WebElement> frees = driver.findElements(By.cssSelector(".tr-hui td:nth-child(5)"));
			if (free.equals("是")) {
				for (WebElement webElement : frees) {
					Assert.assertEquals(webElement.getText(), "是","客户-高级查询-是否签单-是-查询失败！");
				}
			} else if (free.equals("否")) {
				for (WebElement webElement : frees) {
					Assert.assertEquals(webElement.getText(), "否","客户-高级查询-是否签单-否-查询失败！");
				}
			}else {
				Reporter.log("客户-高级查询-是否签单-attribute参数有错！");
			}
		}

	}

}
