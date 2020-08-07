package Miko.SeleniumFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;

public class AutomationDriver {
	
	private WebDriver driver;
  
	public AutomationDriver(String name) {
		String driverPath = "C:\\Users\\UESR\\Documents\\eclipse_workspace\\SeleniumFramework\\drivers\\";
		System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.ie.driver", driverPath+"IEDriverServer.exe");
		System.setProperty("webdriver.edge.driver", driverPath+"msedgedriver.exe");
		switch (name) {
			case "Firefox":
				driver = new FirefoxDriver();
				break;
			case "Chrome":
				driver = new ChromeDriver();
				break;
			case "IE":
				driver = new InternetExplorerDriver();
				break;
			case "Edge":
				driver = new EdgeDriver();
				break;
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
