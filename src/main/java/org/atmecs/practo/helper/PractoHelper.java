package org.atmecs.practo.helper;


import java.io.IOException;
import java.util.Set;

import org.atmecs.practo.extentreports.Extent;
import org.atmecs.practo.reports.LogReporter;
import org.atmecs.practo.utils.Productaccess;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
/*
 * Class is created for the implement th reusablity
 * it is contains many method when ever we wat we can access*/
public class PractoHelper extends Extent {
	LogReporter log=new LogReporter();
	Productaccess  access=new Productaccess();
	/*
	 * this method will perform the click operation ion web elements*/
	public void clickElement(String locators, WebDriver webdriver) {
		WebDriverWait wait2 = new WebDriverWait(webdriver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(locators)));
		WebElement click_operation = webdriver.findElement(By.xpath(locators));
		click_operation.click();
	}
	//this the method for handle the windows in web driver
	public WebDriver winHandler(WebDriver driver) {
		String window_array[]=new String[5];
		Set<String> windows=driver.getWindowHandles();
		int initial=0;
		for (String win:windows)
		{
			window_array[initial]=win;
		}
		driver=driver.switchTo().window(window_array[0]);
		return driver;
	}
	//this method will check the url is correct or not
	public void correctUrlchecker(WebDriver driver,String expectedurl){
		try {
			Assert.assertEquals(driver.getCurrentUrl(),expectedurl);
			System.out.println("User landed or Reached the correct webpage");
			log.logReport("Successfully Validated the correct Url is :"+ driver.getCurrentUrl());
			logger.log(LogStatus.INFO,"Successfully Validated the correct Url is :" +driver.getCurrentUrl());
		}catch(AssertionError e) {
			log.logReport("Navigate to wrong Webpage");
			logger.log(LogStatus.INFO, "Navigate to wrong Webpage");
		}	
	}
	//this method verify the cart products total price using assertion
	public void validateTotalPrice(WebDriver driver,String locator,String paytotal) {
		try {
			System.out.println();
			WebElement ele=driver.findElement(By.xpath(locator));
			String total=ele.getText();
			total=total.substring(1,total.length());
			Assert.assertEquals(total, paytotal);
			System.out.println("Successfully Verified the total payment :"+paytotal);
			log.logReport("Successfully verified the total payment :"+total);
			logger.log(LogStatus.INFO, "Successfully verified the total payment:"+total);
		}
		catch(AssertionError e ) {
			log.logReport("Wrong Payment Total");
			logger.log(LogStatus.INFO,"Wrong Payment Total (Expected is :"+paytotal+")");
		}
	}
	//this method will give the value of the cart products in the array manner
	public String[] elementLoader(WebDriver driver,String locator1) {
		WebElement proddetails=driver.findElement(By.xpath(locator1));
		String[] prodarray= proddetails.getText().split("\n"); 
		return prodarray;
	}
	//this method verify the cart product name after the product added to the cart using assertion
	public void validateName(WebDriver driver,String locator1,String prodname,int index) throws IOException {
		String[] prodarray=elementLoader(driver,locator1);
		try {
			Assert.assertEquals(prodarray[index],prodname);
			System.out.println("Successfully Verified the Product Name :"+prodname);
			log.logReport("Successfully verified the Product Name :"+prodarray[index]);
			logger.log(LogStatus.INFO, "Successfully verified the Product Name:"+prodarray[index]);

		}catch(AssertionError e) {
			System.out.println("Product Name "+prodarray[index]+" is not match with :"+prodname);
			log.logReport("Product Name "+prodarray[index]+" is not match with :"+prodname);
			logger.log(LogStatus.INFO, "Product Name "+prodarray[index]+" is not match with :"+prodname );
		}
	}
	//this method verify the cart product price after the product added to the cart using assertion
	public void validatePrice(WebDriver driver,String locator1,String prodprice,int index) {
		String[] prodarray=elementLoader(driver,locator1);
		String price=prodarray[index];
		price=price.substring(1,price.length());
		try {
			Assert.assertEquals(price,prodprice);
			log.logReport("Successfully verified the Product Price :"+price);
			logger.log(LogStatus.INFO, "Successfully verified the Product Price:"+price);
		}catch(AssertionError e) {
			System.out.println("Product price "+price+" is not match with :"+prodprice);
			log.logReport("Product price "+price+" is not match with :"+prodprice);
			logger.log(LogStatus.INFO, "Product price "+price+" is not match with :"+prodprice);
		}
	}
	//this method verify the cart product count after the product added to the cart using assertion
	public void validateProdcount(WebDriver driver,String locator1,String prodcount,int index) throws IOException {
		String[] prodarray=elementLoader(driver,locator1);
		try {
			Assert.assertEquals(prodarray[index],prodcount);
			log.logReport("Successfully verified the Product count :"+prodarray[index]);
			logger.log(LogStatus.INFO, "Successfully verified the Product count:"+prodarray[index]);
		}catch(AssertionError e) {
			log.logReport("Product count "+prodarray[index]+" is not match with :"+prodcount);
			logger.log(LogStatus.INFO, "Product count "+prodarray[index]+" is not match with :"+prodcount);
		}
	}
	/**this method is manage all the three method after the product added
	 * three methods are:
	 * validate price
	 * validate name
	 * validate prodcount and 
	 *  validate using assertion
	 */
	public void  validateManage(WebDriver driver,String locator,String prodname,String prodprice,String noofprod,String prodname1,String prodprice1,String noofprod1) throws IOException {
		String[] list=elementLoader(driver,locator);
		int length=list.length/7;
		length=(length*4)-1;
		length=list.length-length;
		int storage[]=new int[length];
		int limit=(list.length/7)+1;
		int[] prodindex=access.indexgenerator(storage, limit);
		int count=0;
		while(count<1) {
			validateName(driver,locator, prodname,prodindex[count]);
			count++;
			validatePrice(driver, locator, prodprice,prodindex[count]);
			count++;
			validateProdcount(driver, locator, noofprod,prodindex[count]);
			count++;
			validateName(driver,locator, prodname1,prodindex[count]);
			count++;
			validatePrice(driver, locator, prodprice1,prodindex[count]);
			count++;
			validateProdcount(driver, locator, noofprod1,prodindex[count]);
		}

	}

	/*this method is manage all the three method after the product deleted
	 * three methods are:
	 * validate price
	 * validate name
	 * validate prodcount and 
	 *  validate using assertion
	 */
	public void validateAfter(WebDriver driver,String locator,String prodname1,String prodprice1,String noofprod1) throws IOException {

		String[] list=elementLoader(driver,locator);
		int length=list.length/7;
		length=(length*4)-1;
		length=list.length-length;
		int storage[]=new int[length];
		int limit=(list.length/7)+1;
		int[] prodindex=access.indexgenerator(storage, limit);
		int count=0;
		while(count<1) {
			validateName(driver,locator, prodname1,prodindex[count]);
			count++;
			validatePrice(driver, locator, prodprice1,prodindex[count]);
			count++;
			validateProdcount(driver, locator, noofprod1,prodindex[count]);
		}
	}
	public void sendKeys(String locators, WebDriver webdriver,String value) {
		WebDriverWait wait2 = new WebDriverWait(webdriver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(locators)));
		WebElement sendtext = webdriver.findElement(By.xpath(locators));
		sendtext.sendKeys(value);
	}
	


}
