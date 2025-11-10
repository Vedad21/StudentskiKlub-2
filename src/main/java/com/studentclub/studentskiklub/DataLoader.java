package com.studentclub.studentskiklub;


import com.studentclub.studentskiklub.model.Event;
import com.studentclub.studentskiklub.model.Membership;
import com.studentclub.studentskiklub.model.Student;
import com.studentclub.studentskiklub.repository.EventRepository;
import com.studentclub.studentskiklub.repository.MembershipRepository;
import com.studentclub.studentskiklub.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public void run(String... args) throws Exception {
        // Provjeri da li već postoje podaci
        if (studentRepository.count() > 0) {
            return; // Podaci već postoje, ne dodavaj ponovo
        }

        // Dodaj studente
        Student s1 = studentRepository.save(new Student("Marko", "Marković", "marko@example.com", "12345"));
        Student s2 = studentRepository.save(new Student("Ana", "Anić", "ana@example.com", "12346"));
        Student s3 = studentRepository.save(new Student("Petar", "Petrović", "petar@example.com", "12347"));
        Student s4 = studentRepository.save(new Student("Jelena", "Jovanović", "jelena@example.com", "12348"));
        Student s5 = studentRepository.save(new Student("Nikola", "Nikolić", "nikola@example.com", "12349"));

        // Dodaj evente
        eventRepository.save(new Event("Dobrodošlica", "15.10.2024", "Aula A", "Dobrodošlica za nove članove"));
        eventRepository.save(new Event("Hackathon 2024", "20.11.2024", "IT Centar", "24-satni programerski maraton"));
        eventRepository.save(new Event("Predavanje: AI", "05.12.2024", "Sala 101", "Predavanje o umjetnoj inteligenciji"));
        eventRepository.save(new Event("Božićna Zabava", "22.12.2024", "Studentski Dom", "Godišnja proslava"));

        // Dodaj članstva
        membershipRepository.save(new Membership(s1, "Godišnje",
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31), "Aktivno", 50.0));
        membershipRepository.save(new Membership(s2, "Semestralno",
                LocalDate.of(2024, 9, 1), LocalDate.of(2025, 1, 31), "Aktivno", 30.0));
        membershipRepository.save(new Membership(s3, "Mjesečno",
                LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 31), "Isteklo", 10.0));
        membershipRepository.save(new Membership(s4, "Godišnje",
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31), "Aktivno", 50.0));
        membershipRepository.save(new Membership(s5, "Semestralno",
                LocalDate.of(2024, 2, 1), LocalDate.of(2024, 6, 30), "Isteklo", 30.0));

        System.out.println("Demo podaci su uspješno učitani!");
    }
}