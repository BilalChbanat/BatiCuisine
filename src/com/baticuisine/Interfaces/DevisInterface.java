package com.baticuisine.Interfaces;

import com.baticuisine.Models.Devis;
import java.util.List;

public interface DevisInterface {

    double calculateTax(double baseAmount, double taxRate);
    void add(Devis devis);
    List<Devis> findAll();
    Devis findById(int id);
    void update(Devis devis);
    void delete(int id);
}
