package cz.robotdreams.java.lekce18;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;


public class SimpleSeleniumExampleTest {

    @Test
    public void seleniumTest() {
        assumeFalse("true".equals(System.getenv("CI"))); //Do not run on GitHub action

        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            assertThat(driver.getTitle()).isEqualTo("Web form");
        } finally {
            driver.quit();
        }
    }




}
