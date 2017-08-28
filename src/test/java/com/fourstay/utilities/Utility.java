package com.fourstay.utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	WebDriver driver;

	public Utility(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(String type, String locator) {
		type = type.toLowerCase();
		if (type.equals("id")) {
			System.out.println("Element found with id: " + locator);
			return this.driver.findElement(By.id(locator));
		} else if (type.equals("css")) {
			System.out.println("Element found with css: " + locator);
			return this.driver.findElement(By.cssSelector("type"));
		} else if (type.equals("xpath")) {
			System.out.println("Element found with xpath: " + locator);
			return this.driver.findElement(By.xpath("type"));
		} else if (type.equals("linktext")) {
			System.out.println("Element found with linkText: " + locator);
			return this.driver.findElement(By.linkText("type"));
		} else if (type.equals("partiallinktext")) {
			System.out.println("Element found with partialLinkText: " + locator);
			return this.driver.findElement(By.partialLinkText("type"));
		} else if (type.equals("name")) {
			System.out.println("Element found with name: " + locator);
			return this.driver.findElement(By.name("type"));
		} else if (type.equals("classname")) {
			System.out.println("Element found with className: " + locator);
			return this.driver.findElement(By.className("type"));
		} else if (type.equals("tagname")) {
			System.out.println("Element found with tagName: " + locator);
			return this.driver.findElement(By.tagName("type"));
		} else {
			System.out.println("Locator invalid: " + type + "-" + locator);
			return null;
		}
	}

	public List<WebElement> getElementList(String type, String locator) {
		type = type.toLowerCase();
		if (type.equals("id")) {
			System.out.println("ElementList found with id: " + locator);
			return this.driver.findElements(By.id(locator));
		} else if (type.equals("css")) {
			System.out.println("ElementList found with css: " + locator);
			return this.driver.findElements(By.cssSelector("type"));
		} else if (type.equals("xpath")) {
			System.out.println("ElementList found with xpath: " + locator);
			return this.driver.findElements(By.xpath("type"));
		} else if (type.equals("linktext")) {
			System.out.println("ElementList found with linkText: " + locator);
			return this.driver.findElements(By.linkText("type"));
		} else if (type.equals("partiallinktext")) {
			System.out.println("ElementList found with partialLinkText: " + locator);
			return this.driver.findElements(By.partialLinkText("type"));
		} else if (type.equals("name")) {
			System.out.println("ElementList found with name: " + locator);
			return this.driver.findElements(By.name("type"));
		} else if (type.equals("classname")) {
			System.out.println("ElementList found with className: " + locator);
			return this.driver.findElements(By.className("type"));
		} else if (type.equals("tagname")) {
			System.out.println("ElementList found with tagName: " + locator);
			return this.driver.findElements(By.tagName("type"));
		} else {
			System.out.println("Locator invalid: " + type + "-" + locator);
			return null;
		}
	}

	public boolean isElementPresent(String type, String locator) {
		List<WebElement> elementList = getElementList(type, locator);
		int size = elementList.size();

		if (size > 0) {
			return true;
		} else {
			return false;
		}

	}

	public static void waitForPageLoad() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 45);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.se-pre-con")));

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void switchTab(String url) {
		String defaultTab = Driver.getInstance().getWindowHandle();
		for (String windowHandle : Driver.getInstance().getWindowHandles()) {
			Driver.getInstance().switchTo().window(windowHandle);
			if (Driver.getInstance().getCurrentUrl().contains(url)) {
				return;
			}
		}
		Driver.getInstance().switchTo().window(defaultTab);
	}

	public static List<String> getFromDates() {
		// get all the date elements
		List<WebElement> dates1 = Driver.getInstance().findElements(By.cssSelector("span[class='ng-binding']"));
		// store the string value
		List<String> dateList = new ArrayList<>();
		for (WebElement elem : dates1) {
			dateList.add(elem.getText());
		}
		for (String string : dateList) {
			string = string.substring(5);
			if (string.charAt(0) == '0')
				string = string.substring(1);

		}

		return dateList;
	}

	public static ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				WebElement toReturn = driver.findElement(locator);
				if (toReturn.isDisplayed()) {
					return toReturn;
				}
				return null;
			}
		};
	}

	public static void populateTextBox(WebDriver driver, By by, String value) {
		WebElement inputElement = driver.findElement(by);
		if ("".equals(value)) {
			inputElement.clear();
		} else {
			inputElement.sendKeys(value);
		}
	}

	public static void checkRadio(WebDriver driver, By by) {
		WebElement inputElement = driver.findElement(by);
		inputElement.click();
	}

	public static void goToTab(WebDriver driver, By by) {
		waitUntilClickable(driver, by);
		driver.findElement(by).click();
	}

	public static WebElement waitForVisibility(WebDriver driver, By by) {
		return waitForVisibility(driver, by, 45);
	}

	public static WebElement waitUntilClickable(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, 45);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		return element;
	}

	public static WebElement waitForVisibility(WebDriver driver, By by, int waitTime) {
		Wait<WebDriver> wait = new WebDriverWait(driver, waitTime);
		WebElement divElement = wait.until(visibilityOfElementLocated(by));
		return divElement;
	}

	public static WebElement switchToNewWindow(WebDriver driver, String iframeId) {
		driver.switchTo().frame(iframeId);
		WebElement window = driver.switchTo().activeElement();
		return window;
	}

	// public static String getTextFromId(WebDriver driver, WebElement navigator,
	// String id) {
	//
	// //String text = navigator.findElement(By.id(id)).getText();
	// System.out.print(id);
	// final WebElement element = findElement(driver, By.id(id),3);
	//
	// ElementType currentElementType=getCurrentElement(element);
	// String text=StringUtils.EMPTY;
	// switch (currentElementType) {
	// case INPUT:
	// text=element.getAttribute("value");
	// break;
	// case DIV:
	// text=element.getText().trim();
	// break;
	// case TEXTAREA:
	// //text=element.getText();
	// text = (String)((JavascriptExecutor) driver).executeScript("return
	// document.getElementById('"+id+"').value","");
	// break;
	// case SELECT:
	// //Select select=new Select(element);
	// //text=select.getFirstSelectedOption().getAttribute("value");
	// text = (String)((JavascriptExecutor) driver).executeScript("return
	// document.getElementById('"+id+"').value","");
	// break;
	// default:
	// break;
	// }
	//
	// System.out.println(" : Text value : "+text);
	// return text;
	//
	// }
	//
	// private static ElementType getCurrentElement(WebElement element) {
	// String tagName=element.getTagName();
	// ElementType elementType=null;
	// if (StringUtils.equalsIgnoreCase(tagName, Constants.INPUT)){
	// elementType=ElementType.INPUT;
	// }
	// else if (StringUtils.equalsIgnoreCase(tagName, Constants.SELECT)){
	// elementType=ElementType.SELECT;
	// }
	// else if (StringUtils.equalsIgnoreCase(tagName, Constants.DIV)){
	// elementType=ElementType.DIV;
	// }
	// else if (StringUtils.equalsIgnoreCase(tagName, Constants.TEXTAREA)){
	// elementType=ElementType.TEXTAREA;
	// }
	// else{
	// logger.error("Unhandled element type : "+element.getTagName());
	// }
	//
	// return elementType;
	// }

	// public static WebElement findElement(WebDriver driver, By by, int
	// timeoutInSeconds){
	// try {
	// WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
	// wait.until(ExpectedConditions.presenceOfElementLocated(by));
	// } catch (TimeoutException e) {
	// logger.error(e.getMessage(),e);
	// e.printStackTrace();
	// return null;
	// }
	// return driver.findElement(by);
	// }

	public static boolean isEnabled(WebDriver driver, String eachField) {
		return (driver.findElement(By.id(eachField)).isEnabled());
	}

	public static boolean isDisabled(WebDriver driver, String eachField) {
		return (!driver.findElement(By.id(eachField)).isEnabled());
	}

	public static boolean isVisible(WebDriver driver, String eachField) {
		return (driver.findElement(By.id(eachField)).isDisplayed());
	}

	public static boolean isInvisible(WebDriver driver, String eachField) {
		return (!driver.findElement(By.id(eachField)).isDisplayed());
	}

	// public static List<Message> checkEnabledFields(WebDriver driver, List<String>
	// enabledFields) {
	// System.out.println("Check enabled fields");
	// if (enabledFields==null) return ListUtils.EMPTY_LIST;
	// List<Message> messages=new ArrayList<Message>();
	// boolean result=false;
	// for (String eachField: enabledFields) {
	// result=isEnabled(driver, eachField);
	// messages.add(ValidationReportUtils.constructFieldValidationMessageFromResult(result,
	// Constants.ENABLED, Constants.DISABLED, eachField));
	// }
	// return messages;
	// }

	// public static List<Message> checkDisabledFields(WebDriver driver,
	// List<String> fields) {
	// if (fields==null) return ListUtils.EMPTY_LIST;
	// List<Message> messages=new ArrayList<Message>();
	// boolean result=false;
	// for (String eachField: fields) {
	// result=isDisabled(driver, eachField);
	// messages.add(ValidationReportUtils.constructFieldValidationMessageFromResult(result,
	// Constants.DISABLED, Constants.ENABLED, eachField));
	// }
	// return messages;
	//
	// }

	// public static List<Message> checkVisibleFields(WebDriver driver, List<String>
	// fields) {
	// if (fields==null) return ListUtils.EMPTY_LIST;
	// List<Message> messages=new ArrayList<Message>();
	// boolean result=false;
	// for (String eachField: fields) {
	// result=isVisible(driver, eachField);
	// messages.add(ValidationReportUtils.constructFieldValidationMessageFromResult(result,
	// Constants.VISIBLE, Constants.INVISIBLE, eachField));
	// }
	// return messages;
	//
	// }
	//
	// public static List<Message> checkInvisibleFields(WebDriver driver,
	// List<String> fields) {
	// if (fields==null) return ListUtils.EMPTY_LIST;
	// List<Message> messages=new ArrayList<Message>();
	// boolean result=false;
	// for (String eachField: fields) {
	// result=isInvisible(driver, eachField);
	// messages.add(ValidationReportUtils.constructFieldValidationMessageFromResult(result,
	// Constants.INVISIBLE, Constants.VISIBLE, eachField));
	// }
	// return messages;
	//
	// }
	//
	// public static String captureScreenshot(WebDriver driver, String folder,
	// String fileName) {
	//
	// File screenshotFile =
	// ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	// File targetFile=new File(folder, fileName+".png");
	// try {
	// FileUtils.copyFile(screenshotFile,targetFile );
	// } catch (IOException e) {
	// logger.error ("Error while writing file ",e);
	// }
	//
	// return targetFile.getAbsolutePath();
	// }

}
