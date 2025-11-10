package com.studentclub.studentskiklub.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "memberships")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacija N:1 sa Student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String tipClanstva; // "Godišnje", "Semestralno", "Mjesečno"

    @Column(nullable = false)
    private LocalDate datumPocetka;

    @Column(nullable = false)
    private LocalDate datumIsteka;

    @Column(nullable = false)
    private String status; // "Aktivno", "Isteklo"

    private Double cijena;

    public Membership() {}

    public Membership(Student student, String tipClanstva, LocalDate datumPocetka,
                      LocalDate datumIsteka, String status, Double cijena) {
        this.student = student;
        this.tipClanstva = tipClanstva;
        this.datumPocetka = datumPocetka;
        this.datumIsteka = datumIsteka;
        this.status = status;
        this.cijena = cijena;
    }

    // Getteri i Setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTipClanstva() {
        return tipClanstva;
    }

    public void setTipClanstva(String tipClanstva) {
        this.tipClanstva = tipClanstva;
    }

    public LocalDate getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(LocalDate datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public LocalDate getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(LocalDate datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }
}