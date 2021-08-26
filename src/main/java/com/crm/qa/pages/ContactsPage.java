package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
 
	//Page factory - OR:(Object Repository
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement lastName;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath="//input[@value='Load From Company']//following-sibling::input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	 
	
	//Initializing the Page Objects :: using initiElements method of PageFactory :: for that create page class constructor
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	
	//Actions
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	//xpath for select contact check-box where name = "DL Swain"
	// //a[text()='DL Swain' ]//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//child::input[@name='contact_id']
	public void selectContactsByName(String name) {
		driver.findElement(By.xpath("//a[text()='"+name+"' ]//parent::td[@class='datalistrow']"
				+"//preceding-sibling::td[@class='datalistrow']//child::input[@name='contact_id']")).click();
	}
	
	public void createNewContact(String title, String ftName, String ltName, String comp){
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);;
		
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		company.sendKeys(comp);
		saveBtn.click();
	}
	
}
