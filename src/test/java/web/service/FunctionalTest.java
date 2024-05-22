package web.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import web.service.MathQuestionService;
import static org.junit.Assert.*;

import org.junit.Test;

public class FunctionalTest {

private WebDriver driver;
	
	private void sleep(long sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Before
    public void setUp() {
    	System.setProperty(
				"webdriver.chrome.driver", 
				"C:/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginValidCredentials() {
        driver.get("http://localhost:3080/login");
        sleep(5);

        WebElement userName = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("passwd"));
        WebElement DOB = driver.findElement(By.id("dob"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        userName.sendKeys("ahsan");
        password.sendKeys("ahsan_pass");
        DOB.sendKeys("01/01/2000");
        loginButton.click();
        sleep(5);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.endsWith("/q1")); // Assuming successful login redirects to q1
    }

    @Test
    public void testLoginInvalidCredentials() {
        driver.get("http://localhost:3080/login");
        sleep(5);

        WebElement userName = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("passwd"));
        WebElement DOB = driver.findElement(By.id("dob"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        userName.sendKeys("abc");
        password.sendKeys("abc123");
        DOB.sendKeys("01/01/2000");
        loginButton.click();
        sleep(5);

        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='errorMessage']"));
        assertEquals("Incorrect credentials.", errorMessage.getText());
    }

    @Test
    public void testMathQuestionPageQ1ValidInput() {
        driver.get("http://localhost:3080/q1");
        sleep(5);

        WebElement num1 = driver.findElement(By.id("number1"));
        WebElement num2 = driver.findElement(By.id("number2"));
        WebElement result = driver.findElement(By.id("result"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));

        num1.sendKeys("5");
        num2.sendKeys("10");
        result.sendKeys("15");
        submitButton.click();
        sleep(5);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.endsWith("/q2")); // Assuming correct answer redirects to q2
    }

    @Test
    public void testMathQuestionPageQ1InvalidInput() {
        driver.get("http://localhost:3080/q1");
        sleep(5);

        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();
        sleep(5);

        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='errorMessage']"));
        assertEquals("Wrong answer, try again.", errorMessage.getText());
    }

}
