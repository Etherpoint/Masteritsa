package ru.familyproject.ryabov.masteritsa.seleium_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginTest {

    private static ChromeDriver driver;

    @BeforeAll
    static void init(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    void openTheMenu(){
        driver.get("http://localhost:8080");
        WebElement openMenu = driver.findElement(By.xpath("/html/body/div/nav/div/button"));
        openMenu.click();
    }

    @AfterAll
    static void closeBrowser(){
        driver.quit();
    }
}
