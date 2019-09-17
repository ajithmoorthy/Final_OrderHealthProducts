package org.atmecs.practo.testbase;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.atmecs.practo.constants.FileConstants;
import org.atmecs.practo.extentreports.Extent;
import org.atmecs.practo.reports.LogReporter;
import org.atmecs.practo.utils.ExcelReader;
import org.atmecs.practo.utils.PropertiesReader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;

/*this class will act as the base class for the test it will provide the browser based on the user choice */
public class TestBase extends Extent {
	PropertiesReader propread = new PropertiesReader();
	ExcelReader excelreader = new ExcelReader();
	LogReporter log = new LogReporter();

	/*
	 * this method will provide the browser driver based on the user need by using
	 * the switch case and properties file
	 */
	@BeforeTest
	public void baseSetup() throws IOException {
		Properties prop = propread.property(FileConstants.config_file);
		switch (prop.getProperty("webdrivername")) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", FileConstants.chromefile);
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("--disable-notifications");
			chromeoptions.addArguments("disable-geolocation");
			driver = new ChromeDriver(chromeoptions);
			log.logReport("Open chorme browser");
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", FileConstants.firefoxfile);
			FirefoxOptions profile = new FirefoxOptions();
			profile.addPreference("dom.webnotifications.enabled", false);
			profile.addPreference("geo.enabled", false);
	        profile.addPreference("geo.provider.use_corelocation", false);
			driver = new FirefoxDriver(profile);
			log.logReport("Open firefox browser");
			break;
		case "Ie":
			System.setProperty("webdriver.ie.driver", FileConstants.Iefile);
			driver = new InternetExplorerDriver();
			log.logReport("Open Ie browser");
			break;
		case "Edge":
			System.setProperty("webdriver.edge.driver", FileConstants.edgefile);
			driver = new EdgeDriver();
			log.logReport("Open Edge browser");
			break;
		}
	}
}
