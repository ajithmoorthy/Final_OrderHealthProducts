package org.atmecs.practo.pages;

import java.io.IOException;
import java.util.Properties;

import org.atmecs.practo.helper.PractoHelper;
import org.atmecs.practo.reports.LogReporter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Painrelief Class contains method painrelif() to navigate the painrelief page
 * 
 * @author ajith.periyasamy
 *
 */
public class PainRelief {
	PractoHelper practohelp = new PractoHelper();
	LogReporter log = new LogReporter();

	// painrelief method will navigate to the pain relief page
	public void painRelief(WebDriver driver, Properties prop, String expectedurl,String fitTitle) throws IOException {
		driver = practohelp.winHandler(driver);
		log.logReport("Successfully Navigated to the painrelief webpage");
		// verify the user landed on the correct url
		practohelp.correctUrlchecker(driver, expectedurl);
		practohelp.clickElement(prop.getProperty("loc.submenu.fitness&wellness.xpath"), driver);
		log.logReport("Navigate to the webpage fitness & wellness through left panel");
		validateFitWell(driver,fitTitle);
		// adding a products to the cart
		practohelp.clickElement(prop.getProperty("loc.btn.add1.xpath"), driver);
		practohelp.clickElement(prop.getProperty("loc.btn.add2.xpath"), driver);
		log.logReport("Successfully products added to the cart");
		practohelp.clickElement(prop.getProperty("loc.btn.viewcart.xpath"), driver);
		log.logReport("Click the View cart link button");
	}
	//this method validate the fit&wellness page document title
	public void validateFitWell(WebDriver driver, String title){
		try {
			Assert.assertEquals(driver.getTitle(), title);
			log.logReport("Document title is validated :"+driver.getTitle());
		}
		catch(AssertionError e)
		{
		System.out.println("Document title is not match with Expected :"+driver.getTitle());
			
		}
	}

}
