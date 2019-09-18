package org.atmecs.practo.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.atmecs.practo.constants.FileConstants;
import org.atmecs.practo.pages.CartPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * CartTest is used for the validate the cart details before and after remove the product
 * @author ajith.periyasamy
 *
 */
public class CartTest extends PainReliefTest {
	CartPage  cartpage=new CartPage();
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
		public void cartPage(String prodname,String noofProd,String prodprice,String prodname1,String prodprice1,String noofProd1,String total,String aftertotal,String prodname2) throws InterruptedException, IOException {
			logger=extent.startTest("Validate the product details and remove any product");
			Properties prop1=propread.property(FileConstants.cartpage_file);
			cartpage.cartPage(driver, prop1,prodname,prodprice,noofProd,prodname1, prodprice1,noofProd1,total,aftertotal,prodname2);
			log.logReport("Navigated to Cart page and Successfully validated items after add and remove the product");
			log.logReport("Driver is closed Successfully");
		}

}
