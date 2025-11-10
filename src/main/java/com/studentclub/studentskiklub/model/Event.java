package com.studentclub.studentskiklub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    private String datum;

    private String lokacija;

    @Column(length = 500)
    private String opis;

    public Event() {}

    public Event(String naziv, String datum, String lokacija, String opis) {
        this.naziv = naziv;
        this.datum = datum;
        this.lokacija = lokacija;
        this.opis = opis;
    }

    // Getteri i Setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}