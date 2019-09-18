package org.atmecs.practo.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.atmecs.practo.constants.FileConstants;
import org.atmecs.practo.pages.PainRelief;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
/**
 * PainReliefTest is used for the navigate to the painrelief page and add any product to the cart
 * @author ajith.periyasamy
 *
 */
public class PainReliefTest extends PharmacyTest {
	PainRelief painpage=new PainRelief();
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

}
