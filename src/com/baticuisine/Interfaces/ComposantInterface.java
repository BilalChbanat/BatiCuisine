package com.baticuisine.Interfaces;

import com.baticuisine.Models.Composant;

import java.util.List;

public interface ComposantInterface {

    void add(Composant composant);
    Composant findByName(String  nom);
    List<Composant> findAll();
    void update(Composant composant);
    void delete(int id);
}
