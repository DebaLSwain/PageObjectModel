 package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page factory - OR:(Object Repository)
	@FindBy(name="username")
	//@CacheLookup
	WebElement username;
	
	@FindBy(name = "password")
	//@CacheLookup
	WebElement password;
	
	@FindBy(xpath= "//input[contains(@type, 'submit')]")
	@CacheLookup
	WebElement loginBtn;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement singBtn;
	
	@FindBy(xpath="//img[contains(@class, 'img-responsive')]")
	WebElement crmLogo;
	
	
	//Initializing the Page Objects :: using initiElements method of pageFactory :: for that create page class constructor
	public LoginPage() {
		PageFactory.initElements(driver, this); //'this' means it's pointing to the current class object, means it will initialize all current class web elements
	}
	
	
	//Define Actions : features available on this(Login) page
	public String verifyLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMLogo() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click(); 						//will return next landing page object which is HomePage object
		return new HomePage();
	  }
}
