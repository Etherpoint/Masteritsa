package ru.familyproject.ryabov.masteritsa.seleium_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
public class LoginTest {

    private static ChromeDriver driver;

    @BeforeAll
    static void init(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    void login(){
        driver.manage().window().maximize();
        driver.get("http://localhost:8080");
        WebElement openMenu = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/form[2]/div/a"));
        openMenu.click();

        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        username.sendKeys("Наталья");

        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys("password");
    }

    @AfterAll
    static void closeBrowser(){
        driver.quit();
    }
}
