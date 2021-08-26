package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest() {
		super();
	}
	
	
	//test cases should be separated -- independent from each other
	//before each test case -- launch the browser gain
	//@test -- execute test case
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {	
		String expectedTitle ="CRMPRO";
		String actualTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Home page title not matched !");	
	}
	
	@Test(priority=2)
	public void verfyCorrectUserNameTest() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verfyCorrectUserName(),"Incorrect user name !");
	}
	
	@Test(priority=3)
	public void clickOnContactsLinkTest() {
		testUtil.switchToFrame();
		homePage.clickOnContactsLink();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
