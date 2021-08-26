package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{

	//Page factory - OR:(Object Repository)
	@FindBy(xpath="//td[contains(text(),'User: Debalaxmi')]")
	WebElement loggedInUser;
	
	@FindBy(xpath="//a[contains(text(),'Home')]")
	WebElement homeLink;
	
	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath="//a[@title='Deals']")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[@title='Tasks']")
	WebElement tasksLink;
	
	
	//Initializing the Page Objects :: using initiElements method of PageFactory :: for that create page class constructor
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	//Define Actions : features available on this(Login) page
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean verfyCorrectUserName() {
		return loggedInUser.isDisplayed();
	}
	
	public void clickOnHomeLink() {
		homeLink.click();
	}
	
	public ContactsPage clickOnContactsLink(){ 
		contactsLink.click();
		return new ContactsPage(); //return next landing page object which is ContactsPage object
	}
	
	public DealsPage clickOnDealsLink(){
		dealsLink.click();    
		return new DealsPage(); //return next landing page object which is DealsPage object
	}
	
	public TasksPage clickOnTasksLink(){
		tasksLink.click();    
		return new TasksPage(); //return next landing page object which is DealsPage object
	}
	
	public void clickOnNewContactLink(){
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();
		
	}
}
