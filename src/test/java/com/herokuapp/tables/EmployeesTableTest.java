package com.herokuapp.tables;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fourstay.utilities.Config;
import com.fourstay.utilities.Driver;

public class EmployeesTableTest {
	static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		driver = Driver.getInstance();
		driver.get(Config.getProperty("herokuUrl"));

	}

	@Test
	public void test() {
		// print number of rows in table 1
		WebElement webtable1 = driver.findElement(By.id("table1"));
		List<WebElement> rows = webtable1.findElements(By.tagName("tr"));
		System.out.println("webtable1 rows count" + rows.size());

		// print number of rows in table 2
		WebElement webtable2 = driver.findElement(By.id("table2"));
		List<WebElement> rows2 = webtable1.findElements(By.tagName("tr"));
		System.out.println("webtable2 rows count" + rows2.size());

		List<WebElement> cells = rows.get(0).findElements(By.tagName("th"));

		// print value in webtable1, row 1, cell 1
		System.out.println(cells.get(0).getText());

		// table[@id='table1']//tr[1];th[1]

	}

	@Test
	public void webTableLoop() {
		WebElement table1 = driver.findElement(By.id("table1"));
		// get all rows from table1
		List<WebElement> rows = table1.findElements(By.tagName("tr"));

		// print rows to know for loops
		System.out.println(rows.size());

		for (WebElement row : rows) {
			// System.out.println(row.getText());
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				System.out.print(" " + cell.getText() + " |");
			}
			System.out.println();
		}

	}

	@Test
	public void loopTablesXpath() {

		List<WebElement> rowsInTable2 = driver.findElements(By.xpath("//table[@id='table2']/tbody/tr"));
		System.out.println(rowsInTable2.size());

		for (int rowNum = 1; rowNum <= 4; rowNum++) {
			for (int cellNum = 1; cellNum <= 6; cellNum++) {
				String cellValue = driver
						.findElement(By.xpath("//table[@id='table2']/tbody/tr[" + rowNum + "]/td[" + cellNum + "]"))
						.getText();
				System.out.println(cellValue + "|");
			}
			System.out.println();
		}

	}
}
