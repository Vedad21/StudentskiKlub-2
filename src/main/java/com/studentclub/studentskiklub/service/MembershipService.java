package com.studentclub.studentskiklub.service;

import com.studentclub.studentskiklub.model.Membership;
import com.studentclub.studentskiklub.model.Student;
import com.studentclub.studentskiklub.repository.MembershipRepository;
import com.studentclub.studentskiklub.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Dohvati sva članstva
    public List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    // Dohvati članstvo po ID-u
    public Optional<Membership> getMembershipById(Long id) {
        return membershipRepository.findById(id);
    }

    // Kreiraj novo članstvo
    public Membership createMembership(Membership membership) {
        return membershipRepository.save(membership);
    }

    // Ažuriraj postojeće članstvo
    public Membership updateMembership(Long id, Membership membershipDetails) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));

        membership.setTipClanstva(membershipDetails.getTipClanstva());
        membership.setDatumPocetka(membershipDetails.getDatumPocetka());
        membership.setDatumIsteka(membershipDetails.getDatumIsteka());
        membership.setStatus(membershipDetails.getStatus());
        membership.setCijena(membershipDetails.getCijena());

        return membershipRepository.save(membership);
    }

    // Obriši članstvo
    public void deleteMembership(Long id) {
        membershipRepository.deleteById(id);
    }

    // Dohvati članstva za određenog studenta
    public List<Membership> getMembershipsByStudentId(Long studentId) {
        return membershipRepository.findByStudentId(studentId);
    }

    // Dohvati sve studente
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Dohvati studenta po ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
}