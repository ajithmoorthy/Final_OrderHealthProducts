package org.atmecs.practo.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.atmecs.practo.constants.FileConstants;
import org.atmecs.practo.helper.PractoHelper;
import org.atmecs.practo.pages.CartPage;
import org.atmecs.practo.pages.PainRelief;
import org.atmecs.practo.pages.Practopage;
import org.atmecs.practo.reports.LogReporter;
import org.atmecs.practo.testbase.TestBase;
import org.atmecs.practo.utils.ExcelReader;
import org.atmecs.practo.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
/**
 * 
 * @author ajith.periyasamy
 *PractoHomepage is a main class for automate the Practo.com 
 *This contains practohome() with @Test annotation method to test the practo wepage.
 * This method is implemented based on the order health product scenario . 
 */
public class PractoHomepage extends TestBase  {
	LogReporter log=new LogReporter();
	ExcelReader excelread=new ExcelReader();
	PropertiesReader propread=new PropertiesReader();
	PractoHelper practohelp=new PractoHelper();
	Practopage practopage=new Practopage();
	PainRelief painpage=new PainRelief();
	CartPage  cartpage=new CartPage();
	
	/**
	 * data provider with name of pharmacy it call the returnlocator() method from the Excel Reader class
	 * and give the data to the @Test method row by row 
	 * @return
	 * @throws IOException
	 */
	
	@DataProvider(name = "pharmacy")
	public String[][] getExpected() throws IOException {
		String Excelarray[][] = null;
		Excelarray = excelread.returnLocator(FileConstants.expecteddata_test_file);
		return Excelarray;
	}
	//Practohome method contains selenium script for the practo page scenario order health product 
	@Test(priority=0,dataProvider="pharmacy")
	void practoHome(String title)throws IOException {
		try {
			logger=extent.startTest("Load the Url");
			Properties prop=propread.property(FileConstants.config_file);
		driver.get(prop.getProperty("url"));
		Assert.assertEquals(driver.getTitle(), title);
		log.logReport("User landed the practo home page Successfully");
		logger.log(LogStatus.INFO, "Url is loaded");
		System.out.println("Document title is validated :"+driver.getTitle());
		logger.log(LogStatus.INFO, "Document title is validated"+driver.getTitle());
		}
		catch(AssertionError e)
		{
			System.out.println("Document title is not matched with Expected "+title);
			
		}
	}
	//dataprovider for the practo page() method
	@DataProvider(name = "practoPage")
	public String[][] getpracto() throws IOException {
		String Excelarray[][] = null;
		Excelarray = excelread.returnLocator(FileConstants.practopage_test_file);
		return Excelarray;
	}
	//This method call the practohome method for the navigate the painrelief page 
	@Test(priority=1,dataProvider="practoPage") 
	public void practoPage(String pharmacytitle,String paintitle) throws IOException {
		logger=extent.startTest("Navigate to the painrelief page");
		Properties prop1=propread.property(FileConstants.practopage_file);
		practopage.practoHome(driver, prop1,pharmacytitle,paintitle);
		log.logReport("Successfully navigated to pain relief Page");
	}
	//dataprovider for the painrelief() method
	@DataProvider(name = "painReliefPage")
	public String[][] getPainRelief() throws IOException {
		String Excelarray[][] = null;
		Excelarray = excelread.returnLocator(FileConstants.painrelief_test_file);
		return Excelarray;
	}
	/**
	 * This method call the painrelief method for the navigate to the protein supplements 
	 * add product to the cart 
	 * and validate the product details
	 * @param expectedurl
	 * @param fittitle
	 * @throws IOException
	 */
	@Test(priority=2,dataProvider="painReliefPage")
	public void painRelief(String expectedurl,String fittitle,String carttitle) throws IOException {
		logger=extent.startTest("Add any product to the cart");
		Properties prop1=propread.property(FileConstants.painreliefpage_file);
		painpage.painRelief(driver, prop1,expectedurl,fittitle,carttitle);
		log.logReport("navigated to firness and wellness page and Successfully add to items in protein suppliments");
	}
	//dataprovider for the cart page() method
	@DataProvider(name = "cartPage")
	public String[][] getCartPage() throws IOException {
		String Excelarray[][] = null;
		Excelarray = excelread.returnLocator(FileConstants.cartpage_test_file);
		return Excelarray;
	}
	/**
	 * This method call the painrelief method for the navigate the protein supplements
	 *  and remove the product from cart
	 *  validate the product is remove or not  
	 *  then browser driver is closed
	 * @param prodname
	 * @param noofProd
	 * @param prodprice
	 * @param prodname1
	 * @param prodprice1
	 * @param noofProd1
	 * @param total
	 * @param aftertotal
	 * @param prodname2
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority=3,dataProvider="cartPage")
	public void cartPage(String prodname,String noofProd,String prodprice,String prodname1,String prodprice1,String noofProd1,String total,String aftertotal,String prodname2,String location) throws InterruptedException, IOException {
		logger=extent.startTest("Validate the product details and remove any product");
		Properties prop1=propread.property(FileConstants.cartpage_file);
		cartpage.cartPage(driver, prop1,prodname,prodprice,noofProd,prodname1, prodprice1,noofProd1,total,aftertotal,prodname2,location);
		log.logReport("Navigated to Cart page and Successfully validated items after add and remove the product");
		log.logReport("Driver is closed Successfully");
	}
}
