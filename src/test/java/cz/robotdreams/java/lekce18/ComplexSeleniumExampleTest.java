package cz.robotdreams.java.lekce18;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;


public class ComplexSeleniumExampleTest {

    private static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        assumeFalse("true".equals(System.getenv("CI"))); //Do not run on GitHub action
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void badAssertExample() {
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            assertThat(driver.getTitle().equals("Selenium")).isTrue();
    }


    @Test
    public void testElement() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys("Hello selenium");
        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        submitButton.click();

        WebElement confirmation = driver.findElement(By.id("message"));

        assertThat(confirmation.getText()).isEqualTo("Received!");


    }




}
