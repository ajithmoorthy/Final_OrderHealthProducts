package org.atmecs.practo.pages;

import java.io.IOException;
import java.util.Properties;

import org.atmecs.practo.extentreports.Extent;
import org.atmecs.practo.helper.PractoHelper;
import org.atmecs.practo.reports.LogReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

/**
 * Cartpage contain the method cartpage() for the automate the cart opertion and
 * validate the cart details
 * 
 * @author ajith.periyasamy
 *
 */
public class CartPage  extends Extent{
	PractoHelper practohelp = new PractoHelper();
	LogReporter log = new LogReporter();

	// this method will automate the add and remove the product and validate the details
	public void cartPage(WebDriver driver, Properties prop, String prodname, String prodprice, String noofProd,
			String prodname1, String prodprice1, String noofProd1, String total, String aftertotal, String prodname2)
			throws InterruptedException, IOException {

		log.logReport("Successfully navigated to the cart");
		logger.log(LogStatus.INFO, "Sucessfully user landed in the Cart page");
		JavascriptExecutor js=(JavascriptExecutor) driver;
		practohelp.validateManage(driver, prop.getProperty("loc.panel.cart.xpath"), prodname, prodprice, noofProd,
				prodname2, prodprice1, noofProd1);
		practohelp.validateTotalPrice(driver, prop.getProperty("loc.chekoutpanel.payamt.xpath"), total);
		log.logReport("Successfully validated the cart details");
		logger.log(LogStatus.INFO, "Successfully validated the cart details");
		js.executeScript("navigator.geolocation.getCurrentPosition = function(success) { success({coords: {latitude: 17.4101, longitude: 78.3756}}); }");
		practohelp.clickElement(prop.getProperty("loc.linktxt.location.xpath"), driver);
		logger.log(LogStatus.INFO, "Successfully Select the location");
		Thread.sleep(4000);
		// remove the product from the cart and validate the cart details
		practohelp.clickElement(prop.getProperty("loc.btn.removeitem.xpath"), driver);
		practohelp.validateAfter(driver, prop.getProperty("loc.panel.cart.xpath"), prodname1, prodprice1, noofProd1);
		log.logReport("Successfully the product is removed");
		logger.log(LogStatus.INFO, "Successfully the product is removed");
		practohelp.validateTotalPrice(driver, prop.getProperty("loc.chekoutpanel.payamt.xpath"), aftertotal);
		log.logReport("Successfully validated the cart details after remove the product");
	}

}
