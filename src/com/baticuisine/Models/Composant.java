package com.baticuisine.Models;

import java.util.Objects;

public class Composant {
    private int id;
    private String name;
    private String typeComposant;
    private double tauxTVA;

    public Composant(int id, String name, String typeComposant, double tauxTVA) {
        this.id = id;
        this.name = name;
        this.typeComposant = typeComposant;
        setTauxTVA(tauxTVA);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(String typeComposant) {
        this.typeComposant = typeComposant;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        if (tauxTVA < 0 || tauxTVA > 100) {
            throw new IllegalArgumentException("tauxTVA must be between 0 and 100");
        }
        this.tauxTVA = tauxTVA;
    }

    @Override
    public String toString() {
        return "Composant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", tauxTVA=" + tauxTVA +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Composant)) return false;
        Composant composant = (Composant) o;
        return id == composant.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
