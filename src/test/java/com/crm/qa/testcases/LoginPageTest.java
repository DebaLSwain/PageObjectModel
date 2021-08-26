package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;    //LoginPage.java object as global variable.
	HomePage homePage;
	
	public LoginPageTest() { 	//create constructor to call base class constructor using super() to initialize properties
		super();
	}
	
	
	@BeforeMethod 				//call base class initialization method 
	public void seUp() {
		initialization();
		loginPage = new LoginPage();  //create LoginPage.java object, which has OR of Login page.
	}
	
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.verifyLoginPageTitle();
		String expectedTitle = "CRMPRO - CRM software for customer relationship management, sales, and support.";	
		Assert.assertEquals(actualTitle,expectedTitle);
	}
	
	@Test(priority=2)
	public void crmLogoTest() {
		boolean flag = loginPage.validateCRMLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginTest() {
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@AfterMethod			//close browser after each method/test-case
	public void tearDown() {
		driver.quit();
	}
	
}
