package com.studentclub.studentskiklub.controller;

import com.studentclub.studentskiklub.model.Student;
import com.studentclub.studentskiklub.model.Event;
import com.studentclub.studentskiklub.repository.StudentRepository;
import com.studentclub.studentskiklub.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EventRepository eventRepository;

    // Home page
    @GetMapping("/")
    public String home() {
        return "redirect:/a";
    }

    // GET ruta 1: Lista svih studenata
    @GetMapping("/a")
    public String listaStudenata(Model model) {
        List<Student> studenti = studentRepository.findAll();
        model.addAttribute("studenti", studenti);
        return "listA";
    }

    // GET ruta 2: Lista svih evenata
    @GetMapping("/b")
    public String listaEvenata(Model model) {
        List<Event> eventi = eventRepository.findAll();
        model.addAttribute("eventi", eventi);
        return "listB";
    }

    // GET ruta 3: Detalji studenta po ID-u
    @GetMapping("/a/action/{id}")
    public String detaljStudenta(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id).orElse(null);
        model.addAttribute("student", student);
        return "action";
    }
}