# Studentski Klub - Upravljanje Eventima i Članstvima

## Informacije o Projektu

**Naziv aplikacije:** Studentski Klub - Eventi i Memberi

**Članovi tima:**
- [Vedad Ramić - Član 1]
- [Ermin Starčević - Član 2]
- [Meho Balić - Član 3]

**Univerzitet:** [Politehnički fakultet Univerziteta u Zenici]  
**Predmet:** Web Programiranje  
**Lab Vježba:** 2

---

## Opis Projekta

Aplikacija za upravljanje studentskim klubom koja omogućava praćenje članova (studenata), organizaciju evenata i upravljanje članstvima. Aplikacija koristi Spring Boot framework sa Thymeleaf template engine-om za prikaz i MySQL bazu podataka za perzistenciju.

---

## Struktura Modela

### Model A: Student
Predstavlja člana studentskog kluba sa sledećim atributima:
- `id` (Long) - Jedinstveni identifikator studenta
- `ime` (String) - Ime studenta
- `prezime` (String) - Prezime studenta
- `email` (String) - Email adresa (unique)
- `brojIndeksa` (String) - Broj indeksa (unique)
- `memberships` (List<Membership>) - Lista članstava studenta

### Model B: Event
Predstavlja događaj organizovan od strane studentskog kluba:
- `id` (Long) - Jedinstveni identifikator eventa
- `naziv` (String) - Naziv događaja
- `datum` (String) - Datum održavanja
- `lokacija` (String) - Lokacija događaja
- `opis` (String) - Opis događaja

### Model C: Membership (Novi Model - Lab 2)
Predstavlja članstvo studenta u klubu sa sledećim atributima:
- `id` (Long) - Jedinstveni identifikator članstva
- `student` (Student) - Referenca na studenta (Foreign Key)
- `tipClanstva` (String) - Tip članstva (Mjesečno, Semestralno, Godišnje)
- `datumPocetka` (LocalDate) - Datum početka važenja članstva
- `datumIsteka` (LocalDate) - Datum isteka članstva
- `status` (String) - Status članstva (Aktivno ili Isteklo)
- `cijena` (Double) - Cijena članstva u KM

---

## Relacije između Modela

### Student 1:N Membership (One-to-Many)

**Logika relacije:**
- Jedan student može imati **više članstava** tokom vremena (npr. godišnje članstvo 2023, godišnje članstvo 2024, itd.)
- Svako članstvo pripada **tačno jednom studentu**
- Relacija omogućava praćenje istorije članstava svakog studenta

**Implementacija:**
- U `Student` modelu: `@OneToMany(mappedBy = "student")` sa kaskadnim brisanjem
- U `Membership` modelu: `@ManyToOne` sa `@JoinColumn(name = "student_id")`
- Foreign Key `student_id` u tabeli `memberships` pokazuje na `students.id`

**Dodatne anotacije za JSON serijalizaciju:**
- `@JsonManagedReference` u Student modelu - upravlja serializacijom liste memberships
- `@JsonBackReference` u Membership modelu - sprečava circular reference grešku pri REST API pozivima

**Prednosti ovog pristupa:**
- Lako praćenje koliko puta je student obnovio članstvo
- Mogućnost analize podataka o članstvima (npr. koliko studenata ima aktivno članstvo)
- Mogućnost praćenja finansija (ukupan prihod od članstava po studentu)

---

## Funkcionalnosti Controllera

### 1. MembershipController (Thymeleaf - HTML Prikaz)

**Lokacija:** `src/main/java/com/studentclub/controller/MembershipController.java`

**Rute:**

| HTTP Metoda | Ruta | Opis |
|-------------|------|------|
| GET | `/memberships` | Prikazuje sve članstva u HTML tabeli |
| GET | `/memberships/new` | Prikazuje formu za dodavanje novog članstva |
| POST | `/memberships` | Kreira novo članstvo iz forme |
| GET | `/memberships/edit/{id}` | Prikazuje formu za izmenu postojećeg članstva |
| POST | `/memberships/update/{id}` | Ažurira postojeće članstvo |
| GET | `/memberships/delete/{id}` | Briše članstvo po ID-u |

**Funkcionalnosti:**
- **CRUD operacije** sa Thymeleaf HTML prikazom za krajnje korisnike
- **Validacija podataka** preko HTML formi (required fields, date validation)
- **Dropdown meni** sa listom svih studenata pri kreiranju/izmeni članstva
- **Prikaz povezanih podataka** - prikazuje ime i prezime studenta u tabeli članstava
- **Konfirmacija brisanja** - JavaScript alert pre brisanja podataka
- **Navigacija** - linkovi ka drugim stranicama (studenti, eventi)
- **Responzivni dizajn** - stilizovane tabele i forme sa CSS-om

**Primjer korištenja:**
1. Korisnik otvara `/memberships` i vidi sve članstva
2. Klikne "Dodaj Novo Članstvo" → otvara se forma
3. Izabere studenta iz dropdown menija, unese tip članstva, datume, cijenu
4. Klikne "Sačuvaj" → kreira se novo članstvo i korisnik se vraća na listu

---

### 2. MembershipRestController (REST API - JSON Response)

**Lokacija:** `src/main/java/com/studentclub/controller/MembershipRestController.java`

**REST Endpoints:**

| HTTP Metoda | Endpoint | Opis | Request Body | Response |
|-------------|----------|------|--------------|----------|
| GET | `/api/memberships` | Dohvata sva članstva | - | JSON array svih memberships |
| GET | `/api/memberships/{id}` | Dohvata jedno članstvo po ID-u | - | JSON objekat membership |
| GET | `/api/memberships/student/{studentId}` | Dohvata sva članstva za određenog studenta | - | JSON array memberships za studenta |
| POST | `/api/memberships` | Kreira novo članstvo | JSON membership objekat | HTTP 201 Created + JSON |
| PUT | `/api/memberships/{id}` | Ažurira postojeće članstvo | JSON membership objekat | HTTP 200 OK + JSON |
| DELETE | `/api/memberships/{id}` | Briše članstvo | - | HTTP 204 No Content |

**Funkcionalnosti:**
- **RESTful API** standardni pristup sa HTTP metodama (GET, POST, PUT, DELETE)
- **JSON format** - svi podaci se šalju i primaju u JSON formatu
- **ResponseEntity** - pravilno rukovanje HTTP status kodovima
- **Integracija sa drugim aplikacijama** - omogućava vanjskim aplikacijama pristup podacima
- **CORS podrška** - može se pozvati iz frontend aplikacija (React, Angular, Vue)
- **Error handling** - vraća odgovarajuće HTTP status kodove pri greškama

**Primjer JSON zahtjeva (POST):**
\`\`\`json
{
  "student": {
    "id": 1
  },
  "tipClanstva": "Godišnje",
  "datumPocetka": "2024-01-01",
  "datumIsteka": "2024-12-31",
  "status": "Aktivno",
  "cijena": 50.0
}
\`\`\`

**Primjer JSON odgovora (GET):**
\`\`\`json
[
  {
    "id": 1,
    "tipClanstva": "Godišnje",
    "datumPocetka": "2024-01-01",
    "datumIsteka": "2024-12-31",
    "status": "Aktivno",
    "cijena": 50.0
  }
]
\`\`\`

**Testiranje REST API-ja:**
- Koristi **Postman** ili **cURL** za testiranje
- Ili direktno u browseru za GET rute (npr. `http://localhost:8080/api/memberships`)

---

## Konfiguracija Baze Podataka

### MySQL Setup

**Baza podataka:** MySQL 8.0+  
**Ime baze:** `studentski_klub`  
**Korisnik:** `root`  
**Port:** `3306` (default)

**Kreiranje baze:**
\`\`\`sql
CREATE DATABASE IF NOT EXISTS studentski_klub;
USE studentski_klub;
\`\`\`

### application.properties Konfiguracija

**Lokacija:** `src/main/resources/application.properties`

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/studentski_klub?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tvoj_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true
