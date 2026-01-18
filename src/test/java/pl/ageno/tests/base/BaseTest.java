//BaseTest to abstrakcyjna klasa bazowa dla wszystkich testów
// Rola klasy BaseTest - dostarczenie wspólnych funkcjonalności
// 1. zarządzanie cyklem życia WebDrivera.
// Inicjalizuje WebDriver przed każdym testem i zamyka go po teście.

package pl.ageno.tests.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = DriverFactory.createChromeDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}