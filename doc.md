# Samer - Aram - Karim

## Inledning

### Syfte och Funktionalitet

WebshopApplication är en fullt fungerande e-handelsplattform med användarautentisering, 
produktkatalog, varukorgshantering och en enkel checkout-process. Denna applikation använder 
Spring Boot för backend och erbjuder RESTful API:er för frontend-interaktioner.

### Initiala Tankar och Förväntningar

Våra förväntningar när vi påbörjade detta projekt var att det skulle vara utmanande och lärorikt. 
Vi fokuserade på att använda Spring Boot och JWT-autentisering samt att förbättra vår förståelse för REST API-design och 
databasintegration med JPA.

## Planering och Genomförande

### Utvecklingsplan

Planeringen inkluderade att definiera de grundläggande funktionerna och strukturera dem som användarhistorier. 
Vi skapade även en projektplan med tydliga milstolpar för att hantera funktioner som användarhantering, 
produktkatalog och varukorgssystem. Valet av tekniker och verktyg, inklusive Spring Boot, MySQL och JWT, 
var en del av vår planering.

### Utvecklingssteg

Utvecklingen av projektet inleddes med konfigureringen av projektets grundstruktur med hjälp av Maven och Spring Boot. 
Därefter implementerade vi databasmodeller och repositories för användare och produkter. Vi byggde API:er för 
användarregistrering och inloggning med JWT-autentisering. Slutligen utvecklade vi API:er för varukorgshantering och 
checkout-funktionalitet.

## Utmaningar och Lösningar

### Största Utmaningar

De största utmaningarna inkluderade att säkerställa säker användarautentisering och hantering av sessionslösa 
förfrågningar med JWT. Integreringen av olika delar av applikationen var också en utmaning.

### Lösningar

Vi löste säkerhetsutmaningarna genom att noga studera och implementera JWT och Spring Security. 
För att hantera komplexiteten i projektet använde vi grundlig testning och regelbunden kodgranskning för att 
säkerställa att alla delar av applikationen integrerades korrekt.

## Reflektion och Utvärdering

### Lärdomar

Under processen att utveckla WebshopApplication lärde vi oss vikten av noggrann planering och strukturering av 
stora projekt. Våra färdigheter i Spring Boot förbättrades och vi fick en djupare förståelse för hur man säkert 
hanterar användarautentisering. Vi utvecklade även vår förmåga att skapa RESTful API:er och integrera databaser.

### Framtida Förbättringar

I framtida projekt skulle vi prioritera planering och design av API:er från början och beakta säkerhetsaspekter 
tidigt i projektet. Implementering av kontinuerlig integration och deployment (CI/CD) skulle också vara en prioritet 
för att förbättra utvecklingsprocessen.
