# WebShop
Team Work Samer - Aram - Kerim Kozo

## WebshopApplication

### Projektöversikt

WebshopApplication är en Spring Boot-baserad webbutik. 
Detta projekt demonstrerar användning av Spring Boot tillsammans med säkerhetshantering, 
JWT-autentisering och JPA för datahantering.

### Tekniska Krav

För att köra projektet behöver du följande tekniska krav:

- Java 21
- Maven
- Spring Boot 3.2.0
- MySQL Connector Java 8.0.33
- Lombok
- Swagger 3.0.0 för API-dokumentation

### Installation och Konfiguration

Följ dessa steg för att installera och konfigurera projektet:

1. Klona projektet till din lokala maskin.
2. Se till att Java och Maven är korrekt installerade.
3. Konfigurera dina databasinställningar i `application.properties`.
4. Kör `mvn clean install` för att bygga projektet.
5. Starta applikationen genom att köra `java -jar target/webshop-0.0.1-SNAPSHOT.jar`.

### Användning

Efter att ha startat applikationen kommer API:erna att vara tillgängliga via [http://localhost:8080/](http://localhost:8080/). 
Använd Swagger UI för att utforska och testa olika API-ändpunkter på [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

För att använda programmet:

1. Starta `WebshopApplication` först.
2. I `Client`-mappen, kör `ApplicationRunner`. Du får alternativ om att logga in som admin eller användare. 
Om du använder Postman, kan du generera en token därifrån och använda den vid inloggning som admin eller användare.
3. För att registrera dig måste du använda ett lösenord som uppfyller följande krav:
- Minst 8 tecken långt
- Innehålla minst en stor bokstav (A-Z)
- Innehålla minst en siffra (0-9)
- Innehålla minst ett specialtecken (!@#$%^&*())

### Bidragsguide

Vi välkomnar bidrag till projektet! Läs vår [bidragsguide](CONTRIBUTING.md) för att förstå våra processer och riktlinjer.

### Licens

Detta projekt är licensierat under MIT. Se [LICENSE-filen](LICENSE) för mer information.
