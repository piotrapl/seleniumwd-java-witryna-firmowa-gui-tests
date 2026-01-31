package pl.ageno.tests.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// To jest klasa reprezentująca stronę kontaktową witryny firmowej

//  @FindBy(css = "input[name='your-email']") to selektor pola email
//  wyrażenie  @FindBy(..) nad emailInput wskazuje, że jest
//  to pole input dla adresu email identyfikowane przez selektor CSS

//  @FindBy oznacza elementy strony, które będą inicjalizowane przez PageFactory

public class ContactPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String URL = "https://ageno.pl/kontakt/";
    private static final int MAX_RETRIES = 3;

    @FindBy(css = "input[name='your-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='your-email']/following-sibling::span[contains(@class,'wpcf7-not-valid-tip')]")
    private WebElement emailValidationTip;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    /**
     * public ContactPage open() - metoda otwierająca stronę kontaktową
     * 
     * Otwiera stronę z retry + wait na pełne załadowanie DOM
     * pętla while próbuje otworzyć stronę do MAX_RETRIES razy w przypadku błędu WebDriverException
     * jeśli strona nie załaduje się poprawnie
     */
    public ContactPage open() throws TimeoutException {
        int attempt = 0;
// waitForPageToLoad - metoda czekająca aż strona się w pełni załaduje
// sleepSilently - metoda wstrzymująca wykonanie na określony czas (1 sekundę) 
// bez rzucania wyjątku

        while (attempt < MAX_RETRIES) {
            try {
                driver.get(URL);
                waitForPageToLoad();
                return this;
            } catch (WebDriverException e) {
                attempt++;
                if (attempt >= MAX_RETRIES) {
                    throw e;
                }
                sleepSilently(1000);
            }
        }
        return this;
    }

    public ContactPage clickEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).click();
        return this;
    }

    public ContactPage typeEmail(String value) {
        emailInput.clear();
        emailInput.sendKeys(value);
        return this;
    }

    public ContactPage leaveEmailByTab() {
        emailInput.sendKeys(Keys.TAB);
        return this;
    }

    public String waitAndGetEmailValidationMessageText() {
        wait.until(ExpectedConditions.visibilityOf(emailValidationTip));
        return emailValidationTip.getText();
    }

    /**
     * Czeka aż document.readyState == 'complete'
     */
    private void waitForPageToLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driver ->
            ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete");

        wait.until(pageLoadCondition);
    }

    private void sleepSilently(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}