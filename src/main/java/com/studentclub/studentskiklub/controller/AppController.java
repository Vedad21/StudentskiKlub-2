package com.studentclub.studentskiklub.controller;

import com.studentclub.studentskiklub.data.DemoData;
import com.studentclub.studentskiklub.model.Student;
import com.studentclub.studentskiklub.model.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AppController {

    // GET ruta 1: Lista svih studenata
    @GetMapping("/a")
    public String listaStudenata(Model model) {
        List<Student> studenti = DemoData.getSviStudenti();
        model.addAttribute("studenti", studenti);
        return "listA";
    }

    // GET ruta 2: Lista svih evenata
    @GetMapping("/b")
    public String listaEvenata(Model model) {
        List<Event> eventi = DemoData.getSviEventi();
        model.addAttribute("eventi", eventi);
        return "listB";
    }

    // GET ruta 3: Detalji studenta po ID-u
    @GetMapping("/a/action/{id}")
    public String detaljStudenta(@PathVariable Long id, Model model) {
        Student student = DemoData.getStudentById(id);
        model.addAttribute("student", student);
        return "action";
    }
}
//testtestasdasdsad