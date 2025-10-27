package com.studentclub.studentskiklub.model;

import java.util.List;

public class Event {
    private Long id;
    private String naziv;
    private String datum;
    private String lokacija;
    private String opis;
    private List<Long> studentIds; // Lista ID-eva studenata koji prisustvuju

    public Event() {}

    public Event(Long id, String naziv, String datum, String lokacija, String opis, List<Long> studentIds) {
        this.id = id;
        this.naziv = naziv;
        this.datum = datum;
        this.lokacija = lokacija;
        this.opis = opis;
        this.studentIds = studentIds;
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

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}