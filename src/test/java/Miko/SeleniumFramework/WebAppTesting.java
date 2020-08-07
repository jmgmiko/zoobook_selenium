package Miko.SeleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebAppTesting {
	
	public static WebElement retrieveElement(WebDriver driver, String findBy, String name) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		switch (findBy) {
			case "id":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(name)));
				element = driver.findElement(By.id(name));
				break;
			case "css":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(name)));
				element = driver.findElement(By.cssSelector(name));
				break;
			case "xpath":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(name)));
				element = driver.findElement(By.xpath(name));
				break;
			case "name":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
				element = driver.findElement(By.name(name));
				break;
			case "class":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(name)));
				element = driver.findElement(By.className(name));
				break;
		}
		return element;
	}
	
	public static void inputTextField(WebDriver driver, String findBy, String name, String input) {
		WebElement element = retrieveElement(driver, findBy, name);
		element.sendKeys(input);
	}
	
	public static void clickButton(WebDriver driver, String findBy, String name) {
		WebElement element = retrieveElement(driver, findBy, name);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void mouseHover(WebDriver driver, String findBy, String name) {
		Actions actions = new Actions(driver);
		WebElement element = retrieveElement(driver, findBy, name);
		actions.moveToElement(element).perform();
	}
	
	public static void selectDropdown(WebDriver driver, String findBy, String name, String input) {
		WebElement element = retrieveElement(driver, findBy, name);
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(input);
	}
}
