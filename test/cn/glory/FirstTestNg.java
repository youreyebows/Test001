package cn.glory;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class FirstTestNg {
	
  @Test (groups = {"人"})
  public void f() {
	  System.out.println("学生");
	  Reporter.log("调用学生方法");
  }
  @Test (groups = {"人"})
  public void b() {
	  System.out.println("老师");
	  Reporter.log("调用老师方法");
  }
  
  @Test (groups = {"动物"})
  public void a() {
	  System.out.println("狗狗");
  }
  @Test (groups = {"人","动物"})
  public void n() {
	  System.out.println("人和动物");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("afterMethod");
  }

}
