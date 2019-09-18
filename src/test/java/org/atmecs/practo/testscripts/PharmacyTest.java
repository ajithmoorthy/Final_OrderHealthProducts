package org.atmecs.practo.testscripts;

import java.io.IOException;

import java.util.Properties;

import org.atmecs.practo.constants.FileConstants;
import org.atmecs.practo.helper.PractoHelper;
import org.atmecs.practo.pages.Practopage;
import org.atmecs.practo.reports.LogReporter;
import org.atmecs.practo.testbase.TestBase;
import org.atmecs.practo.utils.ExcelReader;
import org.atmecs.practo.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

/**
 * Class Pharmacy Test is used to navigate from the home page to the painrelief page  
 * @author ajith.periyasamy
 *
 */
public class PharmacyTest extends TestBase {
	LogReporter log=new LogReporter();
	ExcelReader excelread=new ExcelReader();
	PropertiesReader propread=new PropertiesReader();
	PractoHelper practohelp=new PractoHelper();
	Practopage practopage=new Practopage();
		//dataprovider for the practo page() method
		@DataProvider(name = "practoPage")
		public String[][] getpracto() throws IOException {
			String Excelarray[][] = null;
			Excelarray = excelread.returnLocator(FileConstants.practopage_test_file);
			return Excelarray;
		}
		//This method call the practohome method for the navigate the painrelief page  
		@Test(priority=1,dataProvider="practoPage") 
		public void practoPage(String hometitle,String pharmacytitle,String paintitle) throws IOException {
			logger=extent.startTest("Load the Url and Navigate to the PainRelief Page");
			Properties prop=propread.property(FileConstants.config_file);
			Properties prop1=propread.property(FileConstants.practopage_file);
			driver.get(prop.getProperty("url"));
			logger.log(LogStatus.INFO, "Url is loaded");
			log.logReport("User landed the practo home page Successfully");	
			validateTitle(driver,hometitle);
			practopage.practoHome(driver, prop1,pharmacytitle,paintitle);
			log.logReport("Successfully navigated to pain relief Page");
		}
		// validate the home page title to confirm the page
		public void validateTitle( WebDriver driver,String title) {
			try {
			Assert.assertEquals(driver.getTitle(), title);
			logger.log(LogStatus.INFO, "Document title is validated"+driver.getTitle());
			}
			catch(AssertionError e)
			{
				log.logReport("Document title is not matched with Expected "+title);
				logger.log(LogStatus.INFO, "Document title is not matched with Expected "+title );
			}
		}
}
