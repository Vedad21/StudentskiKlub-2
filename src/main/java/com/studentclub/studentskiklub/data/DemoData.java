package com.studentclub.studentskiklub.data;

import com.studentclub.studentskiklub.model.Student;
import com.studentclub.studentskiklub.model.Event;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoData {
    private static List<Student> studenti = new ArrayList<>();
    private static List<Event> eventi = new ArrayList<>();

    static {
        // Inicijalizacija studenata
        studenti.add(new Student(1L, "Marko", "Marković", "marko@example.com", "12345"));
        studenti.add(new Student(2L, "Ana", "Anić", "ana@example.com", "12346"));
        studenti.add(new Student(3L, "Petar", "Petrović", "petar@example.com", "12347"));
        studenti.add(new Student(4L, "Jelena", "Jovanović", "jelena@example.com", "12348"));
        studenti.add(new Student(5L, "Nikola", "Nikolić", "nikola@example.com", "12349"));

        // Inicijalizacija evenata
        eventi.add(new Event(1L, "Dobrodošlica", "15.10.2024", "Aula A",
                "Dobrodošlica za nove članove kluba", Arrays.asList(1L, 2L, 3L)));
        eventi.add(new Event(2L, "Hackathon 2024", "20.11.2024", "IT Centar",
                "24-satni programerski maraton", Arrays.asList(1L, 4L, 5L)));
        eventi.add(new Event(3L, "Predavanje: AI", "05.12.2024", "Sala 101",
                "Predavanje o umjetnoj inteligenciji", Arrays.asList(2L, 3L, 4L)));
        eventi.add(new Event(4L, "Božićna Zabava", "22.12.2024", "Studentski Dom",
                "Godišnja proslava studentskog kluba", Arrays.asList(1L, 2L, 3L, 4L, 5L)));
    }

    public static List<Student> getSviStudenti() {
        return studenti;
    }

    public static List<Event> getSviEventi() {
        return eventi;
    }

    public static Student getStudentById(Long id) {
        return studenti.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Student> getStudentiZaEvent(List<Long> studentIds) {
        List<Student> rezultat = new ArrayList<>();
        for (Long id : studentIds) {
            Student student = getStudentById(id);
            if (student != null) {
                rezultat.add(student);
            }
        }
        return rezultat;
    }
}