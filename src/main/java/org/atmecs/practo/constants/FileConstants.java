package org.atmecs.practo.constants;
/* file canstants are created for the user can run any system the program should run 
 * and the file location is access is make easier using this class */
public class FileConstants {
	//creating file constants for the config properties file paths
	public static final String config_file = "./src/test/resources/testdata/config.properties";
	

	public static final String cartdata_file="./src/test/resources/testdata/carttestdata.xlsx";
	
	//testdata file paths
	public static final String expecteddata_test_file="./src/test/resources/testdata/expecteddata.xlsx";
	public static final String practopage_test_file="./src/test/resources/testdata/practopage.xlsx";
	public static final String painrelief_test_file="./src/test/resources/testdata/painRelief.xlsx";
	public static final String cartpage_test_file="./src/test/resources/testdata/cartpage.xlsx";
	
	//creating file constants for the browser .exe file paths
	public static final String chromefile ="./libs/chromedriver.exe";
	public static final String firefoxfile ="./libs/geckodriver.exe";
	public static final String Iefile="./libs/IEDriverServer.exe";
	public static final String edgefile="./libs/msedgedriver.exe";
	
	//creating file constants for the log4j file path
	public static final String logfile ="./src/test/resources/log4j/log4j.properties";
	
	//creating file constants for the extend report file paths
	public static final String extentfile ="./src/test/resources/extent/extent.html";
	public static final String pngfile="./src/test/resources/extent/"+System.currentTimeMillis()+".png";
	public static final String extentconfigfile ="./src/test/resources/extent/extent-config.xml";
	
	//creating the file path constants for the pages
	public static final String pharmacy_file = "./src/test/resources/locators/pharmacy.properties";
	public static final String cartpage_file="./src/test/resources/locators/cartpage.properties";
	public static final String painreliefpage_file="./src/test/resources/locators/painrelief.properties";
	public static final String practopage_file="./src/test/resources/locators/practopage.properties";
	}
