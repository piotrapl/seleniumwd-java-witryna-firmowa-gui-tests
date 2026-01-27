## Wprowadzenie (Intro)
Projekt testów automatycznych w Selenium Webdriver/Java/jUnit. Testowana aplikacja: formularz kontaktowy na stronie https://ageno.pl/kontakt/ - przykład prostej ale funkcjonalnej witryny firmwej.
### English abstract
*Automated test project using Selenium WebDriver, Java and JUnit.
The tested application is the contact form on https://ageno.pl/kontakt/_
.
example of a simple yet functional company website.*

## Scenariusz testowy (Test scenario)
Negatywny przypadek testowy – walidacja pola e-mail

- Otwarcie strony: https://ageno.pl/kontakt/

- Kliknięcie w pole „Twój e-mail”

- Wpisanie wartości, która nie jest adresem e-mail (np. fsfdadfsafsaf)

- Opuszczenie pola za pomocą klawisza TAB

- Weryfikacja wyświetlenia komunikatu walidacyjnego o dokładnej treści: 'Proszę wpisać adres e-mail'

## Wymagania (Requirements)

### Do uruchomienia projektu wymagane są:

Java JDK 17

Apache Maven 3.8+

Przeglądarka Google Chrome

Dostęp do Internetu

Projekt korzysta z Selenium Manager, dlatego nie wymaga ręcznego pobierania ani konfiguracji ChromeDrivera.

### Sprawdzenie wymagań (command line)
#### Java:

java -version

*Oczekiwany wynik:*

java 17.x

#### Maven:

mvn -version

*Oczekiwany wynik:*

Apache Maven 3.x
Java version: 17

## Uruchamianie testów (Running the tests)
#### Tryb standardowy (z widoczną przeglądarką)

mvn test

#### Tryb headless

mvn test -Dheadless=true

## Najważniejsze cechy rozwiązania (Key features)

#### Page Object Model wspierany przez PageFactory

  Wyłącznie explicit waits (brak implicit waits)

#### Testy data-driven oparte o JUnit 5 (@ParameterizedTest)

#### Czytelna i skalowalna struktura projektu

#### Uruchamianie testów przez Maven (projekt gotowy pod CI)

## Co można by ulepszyć ? (What could be improved ?)

#### Raportowanie i diagnostykę (np. Allure / Extent Reports)
  aby zapewnić czytelne raporty, szybkie debugowanie błędów oraz lepszą widoczność wyników testów.

#### Konfigurację i środowiska (profiles, properties)
  aby umożliwić uruchamianie testów na różnych środowiskach (URL, timeouty, tryb headless) bez zmian w kodzie.

#### Skalowalność (CI/CD, równoległość, wiele przeglądarek)
  aby skrócić czas wykonania testów i dostosować framework do pracy zespołowej oraz pipeline’ów CI.

## Zalety używania JUnit

- Łatwe pisanie i utrzymanie testów

- Nowoczesny design JUnit 5 → przejrzysty cykl życia, rozszerzenia

- B. dobra integracja z narzędziami i frameworkami

- Duże wsparcie społeczności → popularny standard

## Wady używania JUnit

- Ograniczone możliwości grupowania testów w porównaniu do TestNG

- Brak natywnej obsługi zależności między testami (dependsOnMethods)

- Mniej elastyczna kontrola równoległego uruchamiania testów

- Testy data-driven są nieco bardziej rozbudowane niż w TestNG (@DataProvider)