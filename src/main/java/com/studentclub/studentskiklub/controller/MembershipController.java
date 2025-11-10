package com.studentclub.studentskiklub.controller;

import com.studentclub.studentskiklub.model.Membership;
import com.studentclub.studentskiklub.model.Student;
import com.studentclub.studentskiklub.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    // Prikaz svih članstava
    @GetMapping
    public String listMemberships(Model model) {
        List<Membership> memberships = membershipService.getAllMemberships();
        model.addAttribute("memberships", memberships);
        return "membership-list";
    }

    // Forma za dodavanje novog članstva
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Membership membership = new Membership();
        List<Student> students = membershipService.getAllStudents();

        model.addAttribute("membership", membership);
        model.addAttribute("students", students);
        return "membership-form";
    }

    // Kreiranje novog članstva
    @PostMapping
    public String createMembership(@ModelAttribute Membership membership,
                                   @RequestParam Long studentId) {
        Student student = membershipService.getStudentById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        membership.setStudent(student);
        membershipService.createMembership(membership);

        return "redirect:/memberships";
    }

    // Forma za izmjenu članstva
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Membership membership = membershipService.getMembershipById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        List<Student> students = membershipService.getAllStudents();

        model.addAttribute("membership", membership);
        model.addAttribute("students", students);
        return "membership-edit";
    }

    // Ažuriranje članstva
    @PostMapping("/update/{id}")
    public String updateMembership(@PathVariable Long id,
                                   @ModelAttribute Membership membershipDetails,
                                   @RequestParam Long studentId) {
        Student student = membershipService.getStudentById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        membershipDetails.setStudent(student);
        membershipService.updateMembership(id, membershipDetails);

        return "redirect:/memberships";
    }

    // Brisanje članstva
    @GetMapping("/delete/{id}")
    public String deleteMembership(@PathVariable Long id) {
        membershipService.deleteMembership(id);
        return "redirect:/memberships";
    }
}