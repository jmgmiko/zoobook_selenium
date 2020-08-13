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
	
	public static boolean inputTextField(WebDriver driver, String findBy, String name, String input) {
		boolean result = false;
		WebElement element = retrieveElement(driver, findBy, name);
		if (element != null) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			element.clear();
			if (findBy.equals("id") || findBy.equals("name")) {
				switch (findBy) {
					case "id":
						executor.executeScript("document.getElementById('"+name+"').value="+'"'+input+'"');
						break;
					case "name":
						executor.executeScript("document.getElementsByName('"+name+"')[0].value="+'"'+input+'"');
						break;
				}
			} else {
				element.sendKeys(input);
			}
			if (executor.executeScript("return arguments[0].value", element).toString().contains(input) || name.contains("password")) {
				result = true;
			}			
		}
		return result;
	}
	
	public static boolean clickButton(WebDriver driver, String findBy, String name) {
		boolean result = false;
		WebElement element = retrieveElement(driver, findBy, name);
		if (element != null) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			result = true;
		}
		return result;
	}
	
	public static boolean mouseHover(WebDriver driver, String findBy, String name) {
		boolean result = false;
		Actions actions = new Actions(driver);
		WebElement element = retrieveElement(driver, findBy, name);
		if (element != null) {
			actions.moveToElement(element).perform();
			result = true;
		}
		return result;
	}
	
	public static boolean selectDropdown(WebDriver driver, String findBy, String name, String input) {
		boolean result = false;
		WebElement element = retrieveElement(driver, findBy, name);
		if (element != null) {
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(input);
			if (dropdown.getFirstSelectedOption().getText().contains(input)) {
				result = true;
			}
		} 		
		return result;
	}
	
	public static boolean checkIfElementExists(WebDriver driver, String findBy, String name, String input) {
		boolean result = false;
		WebElement element = retrieveElement(driver, findBy, name);
		if ((element == null && input.equalsIgnoreCase("false")) || (element != null && input.equalsIgnoreCase("true"))) {
			result = true;
		}
		return result;
	}
	
	public static boolean checkIfElementContains(WebDriver driver, String findBy, String name, String input) {
		boolean result = false;
		WebElement element = retrieveElement(driver, findBy, name);
		if (element.getText().contains(input)) {
			result = true;
		}
		return result;
	}
	
	public static boolean checkIfElementIsDisplayed(WebDriver driver, String findBy, String name) {
		boolean result = false;
		WebElement element = retrieveElement(driver, findBy, name);
		if (element.isDisplayed()) {
			result = true;
		}
		return result;
	}
}
