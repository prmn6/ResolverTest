package com.testng;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestNGtest {

	WebDriver driver = new ChromeDriver();
  	
	  @Test
	  public void launchTest1() throws InterruptedException {
		  	System.out.println("Test Case #1");

		  	/** Assert that both the email address and password inputs 
		  	 * are present as well as the login button **/
		  	WebElement email = driver.findElement(By.id("inputEmail"));
			Assert.assertEquals(true, email.isDisplayed());
			System.out.println("Test pass - Email input displayed");
			Thread.sleep(1000);
			WebElement pwd = driver.findElement(By.id("inputPassword"));
			Assert.assertEquals(true, pwd.isDisplayed());
			System.out.println("Test pass- Password input displayed");
			Thread.sleep(1000);
			WebElement signin = driver.findElement(By.className("btn"));
			Assert.assertEquals(true, signin.isDisplayed());
			System.out.println("Test pass- SignIn input displayed");
			Thread.sleep(1000);
			
			/** Enter in an email address and password combination 
			 * into the respective fields **/
			driver.findElement(By.id("inputEmail")).sendKeys("xyz@gmail.com");
			Thread.sleep(1000);
			driver.findElement(By.id("inputPassword")).sendKeys("xyz@123");
			Thread.sleep(1000);
			driver.findElement(By.className("btn")).click();
			Thread.sleep(1000);
			String actual =  driver.getTitle();
			String expected = "Home";
			Assert.assertEquals(expected, actual);
			System.out.println("Test pass- SignIn performed and title validated");
	    }
	  
	  @Test
	  public void launchTest2() throws InterruptedException {
			System.out.println("Test Case #2");
			/** Page Scroll **/
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(1000);
			List<WebElement> list = driver.findElements(By.xpath("//ul[@class='list-group']"));
			String lookingFor = "List Item 2 6";
			/** In the test 2 div, assert that there are three values in the listgroup **/
			for (WebElement webElement : list) 
			{
				String name = webElement.getText().trim();
				System.out.println(name);
				if (webElement.getText().trim().contains(lookingFor)) 
				{
					/** Assert that the second list item's value is set to "List Item 2" **/
					System.out.println("Second List Item is " + lookingFor);
				}
			}
			/** Assert that the second list item's badge value is 6 **/
			WebElement value = driver.findElement(By.xpath("//descendant::span[contains(@class,'badge')][2]"));
			String av = value.getText();
			String ev = "6";
			Assert.assertEquals(ev, av);
			System.out.println("Test pass - The list value of 2 is 6");
	  }
			
	  @Test
	  public void launchTest3() throws InterruptedException {
		  	System.out.println("Test Case #3");
		  	JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)", "");
			Thread.sleep(1000);
			/** In the test 3 div, assert that "Option 1" is the default selected value **/
			WebElement text = driver.findElement(By.id("dropdownMenuButton"));
			String actual =  text.getText();
			String expected = "Option 1";
			Assert.assertEquals(expected, actual);
			System.out.println("Test pass - Option 1 is displayed as default");
			Thread.sleep(800);
			/** Select "Option 3" from the select list **/
			driver.findElement(By.id("dropdownMenuButton")).click();
		    driver.findElement(By.xpath("//a[contains(text(), 'Option 3')]")).click();
		    Thread.sleep(800);
	  }
	  
	  @Test
	  public void launchTest4() throws InterruptedException {
		  	System.out.println("Test Case #4");
		  	/** In the test 4 div, assert that the first button is enabled and that the second button is disabled **/
		  	JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,500)", "");
	        Thread.sleep(1000);
	        WebElement btn1 = driver.findElement(By.xpath("//descendant::div[@id='test-4-div']/button[contains(@class,'btn-primary')][1]"));
			Assert.assertEquals(true, btn1.isEnabled());
			System.out.println("Test pass- First button is enabled");
			Thread.sleep(800);
			WebElement btn2 = driver.findElement(By.xpath("//descendant::div[@id='test-4-div']/button[contains(@class,'btn-secondary')][1]"));
			Assert.assertEquals(false, btn2.isEnabled());
			System.out.println("Test pass- Second button is disabled");
			Thread.sleep(800);
	  }
	  
	  @Test
	  public void launchTest5() throws InterruptedException {
		  	System.out.println("Test Case #5");
		  	/** In the test 5 div, wait for a button to be displayed (note: the delay is random) and then click it
				Once you've clicked the button, assert that a success message is displayed
				Assert that the button is now disabled **/
		  	JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,800)", "");
			Thread.sleep(1000);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			WebElement click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='test5-button']")));
			click.click();
			Thread.sleep(1000);
			String heading = driver.findElement(By.xpath("//div[@id='test5-alert']")).getText();
			Thread.sleep(1000);
			String expectedHeading = "You clicked a button!";
			if (expectedHeading.equalsIgnoreCase(heading))
				System.out.println("The expected heading is same as actual heading --- " + heading);
			else
				System.out.println("The expected heading doesn't match the actual heading --- " + heading);
			WebElement btn5 = driver.findElement(By.xpath("//button[@id='test5-button']"));
			Thread.sleep(800);
			Assert.assertEquals(false, btn5.isEnabled());
			System.out.println("Test pass- Button 5 is disabled");
	  }
	  
	  @Test
	  public void launchTest6() throws InterruptedException {
		  	System.out.println("Test Case #6");
		  	findcellvalue();
	  }
	  
	  public void findcellvalue()
		{
		  /** 	Write a method that allows you to find the value of any cell on the grid
				Use the method to find the value of the cell at coordinates 2, 2 
				(staring at 0 in the top left corner)
				Assert that the value of the cell is "Ventosanzap" **/
		WebElement cellvalue = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
		String value = cellvalue.getText();
		System.out.println("Random cell value at 1,2 is " + value);
		
		WebElement cellvalue2 = driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]"));
		String value2 = cellvalue2.getText();
		System.out.println("cell value at 2,2 is "  + value2);
		
		WebElement cellvalue3 = driver.findElement(By.xpath("//table/tbody/tr[3]/td[3]"));
		String value3 = cellvalue3.getText();
		String av = "Ventosanzap";
		Assert.assertEquals(value3, av);
		System.out.println("Assert True - cell value at 3,2 is "  + value3);
		}
	  
	  @BeforeSuite
	  public void beforeMethod() {
	  	  System.out.println("Starting the browser session");
	  	System.setProperty("webdriver.chrome.driver",
				"chromedriver");
		driver.get("file:///Users/priyanka/QE-index.html");
		/** Navigate to the home page **/
		driver.navigate().to("file:///Users/priyanka/QE-index.html");
	  }
	 
	  @AfterSuite
	  public void afterMethod() {
	  	  System.out.println("Closing the browser session");
	  	  driver.quit();
	  }
	}
