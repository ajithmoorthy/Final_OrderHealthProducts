package org.atmecs.practo.pages;

import java.io.IOException;
import java.util.Properties;

import org.atmecs.practo.helper.PractoHelper;
import org.atmecs.practo.reports.LogReporter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * 
 * Practopage class contains the method practohome() . parctohome method used to
 * navigate from home page to pharmacy page
 * 
 * @param driver
 * @param prop
 */

public class Practopage {
	LogReporter log = new LogReporter();
	PractoHelper practohelp = new PractoHelper();

	// this method is navigate the home page to pharmacy page
	public void practoHome(WebDriver driver, Properties prop,String title,String ptitle) throws IOException {
	
		driver.manage().window().maximize();
		practohelp.clickElement(prop.getProperty("loc.menu.pharmacy.xpath"), driver);
		validatePharmacy(driver,title);
		log.logReport("Successfully Navigated to the Pharmacy webpage");
		practohelp.clickElement(prop.getProperty("loc.imglink.painrelief.xpath"), driver);
		validatePainRelief(driver,ptitle);
		log.logReport("Click the to painrelief image link");
		log.logReport("Close the previous window");
		
	}
	//validate the pharmacy page title is correct or not
public void validatePharmacy(WebDriver driver,String title) {
	try {
		Assert.assertEquals(driver.getTitle(), title);
		log.logReport("Document title is validated "+driver.getTitle());
	}
	catch(AssertionError e) {
		System.out.println("Document title is not match with expected "+title);
		
	}
}
//validate the painrelief page title is correct or not
public void validatePainRelief(WebDriver driver,String title) {
	try {
		Assert.assertEquals(driver.getTitle(),title);
		log.logReport("Document title is validated "+driver.getTitle());
	}
	catch(AssertionError e) {
		System.out.println("Document title is not match with expected :");
		
	}
}
}
