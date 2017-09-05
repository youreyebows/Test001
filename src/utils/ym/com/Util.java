package utils.ym.com;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Util {
	/**
	 * 返回start-end之间的任意随机整数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int rangeInt(int start,int end){
		if(start<end){
			Random r = new Random();	
			//生成[0,10)区间的整数:int n2 = r.nextInt(10);
			int n = r.nextInt(Math.abs(start)+Math.abs(end)) - Math.abs(start);  
			return n;
		}else if(start == end){
			return start;
		}
		return -1;
	}
	
	/**
	 * 判断date YYYY-MM-DD 是否在begin-end的时间段内
	 * @param date 需要判断的时间
	 * @param strDateBegin 开始时间
	 * @param strDateEnd 结束时间
	 * @return true-在该时间段内，false-不在
	 */
	public static boolean isInDate(String date,String strDateBegin,String strDateEnd) {
		//去掉- 当前时间
		String date1 = date.replaceAll("-", "");
		int date2 = Integer.parseInt(date1);
		
		//去掉- 开始时间
		String strDateBegin1 =strDateBegin.replaceAll("-", ""); 
		int strDateBegin2 = Integer.parseInt(strDateBegin1);
		
		//去掉- 结束时间
		String strDateEnd1 = strDateEnd.replaceAll("-", "");
		int strDateEnd2 = Integer.parseInt(strDateEnd1);
		
		if (date2>=strDateBegin2 && date2<=strDateEnd2) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断元素是否存在
	 * @param driver
	 * @param locator 可以直接传By.xxx("xxx")
	 * @return
	 */
	public static boolean isElementExsit(WebDriver driver, By locator) {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			flag = true;
		} catch (NoSuchElementException e) {
			System.out.println("元素: " + locator.toString() + " 不存在");
		}
		return flag;
	}
}
