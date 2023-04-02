import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Saucedemo {

    ChromeDriver driver;
    String BASE_URL = "https://www.saucedemo.com/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.driver", "");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(BASE_URL);
    }

    @Test

public void loginWithValidData () throws InterruptedException {
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        System.out.println(driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().contains("inventory"));

        WebElement inventoryContainer = driver.findElements(By.id("inventory_container")).get(0);
        assertTrue(inventoryContainer.isDisplayed());
           }
           @Test
public void loginInvalidData () throws InterruptedException {
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("locked_out_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement errorMassage = driver.findElement(By.tagName("h3"));
        assertEquals("Epic sadface: Sorry, this user has been locked out.",
                errorMassage.getText());
        assertTrue(errorMassage.getText().contains("Sorry, this user has been locked out"));
               sleep(10000);

}

    @Test
    public void loginInvalidData1 () throws InterruptedException {
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("12121212");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement errorMassage = driver.findElement(By.tagName("h3"));
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                errorMassage.getText());
       assertTrue(errorMassage.getText().contains("Username and password do not match any user in this service"));
        sleep(10000);

    }
    @After
    public void tearDown () {
        driver.quit();
    }

}
