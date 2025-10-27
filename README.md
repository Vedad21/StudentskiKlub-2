## Naziv Aplikacije : Studentski klub
## Imena članova : Ramić Vedad, Balić Meho, Starčević Ermin
## Opis Modela i Relacije

### Model A: Student

**Atributi:**

- `id` (Long) - Jedinstveni identifikator studenta
- `ime` (String) - Ime studenta
- `prezime` (String) - Prezime studenta
- `email` (String) - Email adresa studenta
- `brojIndeksa` (String) - Broj indeksa studenta


### Model B: Event

**Atributi:**

- `id` (Long) - Jedinstveni identifikator eventa
- `naziv` (String) - Naziv eventa
- `datum` (String) - Datum održavanja eventa
- `lokacija` (String) - Lokacija gdje se event održava
- `opis` (String) - Kratak opis eventa
- `studentIds` (List`<Long>`) - Lista ID-eva studenata koji prisustvuju eventu


### Relacija: Many-to-Many (N:N)

- **Jedan student** može prisustvovati **više evenata**
- **Jedan event** može imati **više studenata**
- Relacija je implementirana kroz `studentIds` listu u Event modelu
- U DemoData.java možete vidjeti kako su studenti povezani sa eventima


**Primjer relacije:**

- Student "Marko" (ID=1) prisustvuje eventima: Dobrodošlica, Hackathon, Božićna Zabava
- Event "Božićna Zabava" ima studente: Marko, Ana, Petar, Jelena, Nikola
