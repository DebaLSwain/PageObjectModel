package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;  //public so that can be used in all child classes
	public static Properties prop;
	
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListner;
	
	//create constructor and load properties file
	public TestBase() {     
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("D:\\SeleniumJava\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}	
	}
	
	
	//create initialization method which will read properties and initialize common properties
	public static void initialization() {
		
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\SeleniumJava\\chromedriver.exe");
			driver = new ChromeDriver(); 
		}else if(browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "D:\\SeleniumJava\\geckodriver.exe");
			driver = new FirefoxDriver(); 
		}	
		
		e_driver = new EventFiringWebDriver(driver);
		eventListner = new WebEventListener();
		e_driver.register(eventListner);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url")); 
	}

}
