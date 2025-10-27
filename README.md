## Naziv Aplikacije : Studentski klub
## Imena članova : Ramić Vedad, Balić Meho, Starčević Ermin
## Opis Modela i Relacije

### Model A : Student

**Atributi:**

- `id` (Long) - Jedinstveni identifikator studenta
- `ime` (String) - Ime studenta
- `prezime` (String) - Prezime studenta
- `email` (String) - Email adresa studenta
- `brojIndeksa` (String) - Broj indeksa studenta


### Model B : Event

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


**Primjer relacije :**

- Student "Marko" (ID=1) prisustvuje eventima: Dobrodošlica, Hackathon
- Event "Hackathon" ima studente: Marko, Ana, Petar

**Screenshot aplikacije :**

<img width="1918" height="1028" alt="image" src="https://github.com/user-attachments/assets/ffb79720-c4b6-466f-ac0c-f85eb468718f" />
<img width="1918" height="1022" alt="image" src="https://github.com/user-attachments/assets/1eef1c20-dd6b-45cd-8c51-213733cc0616" />
<img width="1918" height="1027" alt="image" src="https://github.com/user-attachments/assets/cbaf4721-43b2-44f3-8384-f7d5deb62b11" />

