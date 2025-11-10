package com.studentclub.studentskiklub.controller;

import com.studentclub.studentskiklub.model.Membership;
import com.studentclub.studentskiklub.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
public class MembershipRestController {

    @Autowired
    private MembershipService membershipService;

    // GET - Dohvati sva članstva (JSON)
    @GetMapping
    public ResponseEntity<List<Membership>> getAllMemberships() {
        List<Membership> memberships = membershipService.getAllMemberships();
        return ResponseEntity.ok(memberships);
    }

    // GET - Dohvati članstvo po ID-u (JSON)
    @GetMapping("/{id}")
    public ResponseEntity<Membership> getMembershipById(@PathVariable Long id) {
        Membership membership = membershipService.getMembershipById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        return ResponseEntity.ok(membership);
    }

    // GET - Dohvati članstva za studenta (JSON)
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Membership>> getMembershipsByStudent(@PathVariable Long studentId) {
        List<Membership> memberships = membershipService.getMembershipsByStudentId(studentId);
        return ResponseEntity.ok(memberships);
    }

    // POST - Kreiraj novo članstvo (JSON)
    @PostMapping
    public ResponseEntity<Membership> createMembership(@RequestBody Membership membership) {
        Membership created = membershipService.createMembership(membership);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT - Ažuriraj članstvo (JSON)
    @PutMapping("/{id}")
    public ResponseEntity<Membership> updateMembership(@PathVariable Long id,
                                                       @RequestBody Membership membershipDetails) {
        Membership updated = membershipService.updateMembership(id, membershipDetails);
        return ResponseEntity.ok(updated);
    }

    // DELETE - Obriši članstvo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id) {
        membershipService.deleteMembership(id);
        return ResponseEntity.noContent().build();
    }
}
