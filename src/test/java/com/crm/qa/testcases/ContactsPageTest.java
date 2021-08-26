package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName = "Contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();	
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts label is missing on the page!");
	}
	
	@Test(priority=2)
	public void selectContactsTest() {
		contactsPage.selectContactsByName("DL Swain");
	}

	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactsByName("DL Swain");
		contactsPage.selectContactsByName("DL2 Swain");
	}
	
	
	@DataProvider
	public Object[][] getContacts() {
		Object[][] contacts = TestUtil.getTestData(sheetName);
		return contacts;
	}
	
	
	
	@Test(priority=4, dataProvider="getContacts") 
	public void validateCreateNewContact(String title, String fName, String lName, String comp) 
	{ 
	  homePage.clickOnHomeLink(); homePage.clickOnNewContactLink();
	  //contactsPage.createNewContact("Mr.", "Piyush", "Swain", "Student");
	  contactsPage.createNewContact(title, fName, lName, comp); 
	}
	 
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
}
