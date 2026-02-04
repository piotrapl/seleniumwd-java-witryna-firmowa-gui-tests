package pl.ageno.tests;
/*
package pl.ageno.tests; - deklaracja pakietu, w którym znajduje się klasa testowa
        pl.ageno - bo to odwrócona nazwa domeny strony   (strona wybrana przypadkowo, jako typowy przykład)
        tests - bo to pakiet z testami
*/
/*
import - to jest dyrektywa importu w Javie, która pozwala na użycie klas z innych pakietów
 
importujemy:     
    1. klasy: bazową BaseTest klasę strony ContactPage
    2. adnotacje i klasy JUnit do testów parametryzowanych
    3. klasę Stream do tworzenia strumienia danych testowych
    4. statycznie metodę assertEquals do porównywania wartości w test
*/
import pl.ageno.tests.base.BaseTest;
import pl.ageno.tests.pages.ContactPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
PageFactory - wzorzec projektowy do tworzenia obiektów reprezentujących strony internetowe
     tutaj używany do tworzenia instancji ContactPage
*/
/*
Page Object Model (POM) - wzorzec projektowy do organizacji kodu testowego
    tutaj ContactPage reprezentuje stronę formularza kontaktowego
*/
public class ContactFormEmailValidationTest extends BaseTest {

/* Klasa pomocnicza do przechowywania danych testowych:
   record EmailCase(String notEmailValue, String expectedMessage)
*/
/* record - specjalny typ klasy w Javie do przechowywania niezmiennych danych
     notEmailValue - niepoprawny adres email do testu
     expectedMessage - oczekiwana wiadomość walidacyjna
*/
    record EmailCase(String notEmailValue, String expectedMessage) { }
    //     @MethodSource("emailValidationCases") - metoda dostarczająca dane testowe do testu walidacji email
    static Stream<EmailCase> emailValidationCases() {
        return Stream.of(
            new EmailCase(
                "fsfdadfsafsaf",
                "Proszę wpisać adres e-mail."
            )
        );
    }
// shouldShowExactEmailValidationMessage 
//    - test walidacji email
    //     @ParameterizedTest(name = "Walidacja email: \"{0}\" -> \"{1}\"") - test walidacji email  
    //     używa danych z metody emailValidationCases()
    //     tworzy instancję ContactPage, otwiera stronę, klika w pole email,
    //     @MethodSource to metoda dostarczająca dane testowe do testu walidacji email
    //     wg scenatiusza:
    //      - wpisuje niepoprawny email
    //      - opuszcza pole email za pomocą klawisza TAB
    //      - czeka na pojawienie się komunikatu walidacyjnego i pobiera jego tekst
    //     -  porównuje oczekiwaną wiadomość z faktyczną
    //
    // znak @ przed nazwą adnotacji wskazuje, że jest to adnotacja JUnit5
    // nazwa testu będzie zawierać wartości parametrów przekazanych do testu
    // w miejscu {0} i {1}
    @ParameterizedTest(name = "Walidacja email: \"{0}\" -> \"{1}\"")
    @MethodSource("emailValidationCases")
    void shouldShowExactEmailValidationMessage(EmailCase tc) {
        ContactPage page = new ContactPage(driver)
            .open()
            .clickEmail()
            .typeEmail(tc.notEmailValue())
            .leaveEmailByTab();

        String actual = page.waitAndGetEmailValidationMessageText();
        assertEquals(tc.expectedMessage(), actual);
    }
}

/*
waitAndGetEmailValidationMessageText - metoda w klasie ContactPage, 
    która czeka na pojawienie się komunikatu walidacyjnego dla pola email i zwraca jego tekst
clickEmail  - metoda w klasie ContactPage: klika w pole email
typeEmail   - metoda w kl. ContactPage:    wpisuje podany tekst do pola email
leaveEmailByTab - metoda w klasie ContactPage, która opuszcza pole email za pomocą klawisza TAB
notEmailValue - niepoprawny adres email do testu,
    to wartość przekazywana do testu w obiekcie EmailCase
    uzyskujemy do niej dostęp za pomocą me  tody notEmailValue()
expectedMessage - oczekiwana wiadomość walidacyjna 
    to też wartość przekazywana do testu w obiekcie EmailCase
    uzyskujemy do niej dostęp za pomocą metody expectedMessage()
    */