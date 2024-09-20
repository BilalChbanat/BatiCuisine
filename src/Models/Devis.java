package Models;

import java.time.LocalDate;

public class Devis {

    private int id;
    private double montantEstime;
    private LocalDate dateValidite;
    private boolean accepte;

    public Devis(int id, double montantEstime, LocalDate dateValidite, boolean accepte) {
        this.id = id;
        this.montantEstime = montantEstime;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(double montantEstime) {
        if(montantEstime < 0) {
            throw new IllegalArgumentException("Montant estimé ne peut pas être négatif.");
        }
        this.montantEstime = montantEstime;
    }

    public LocalDate getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(LocalDate dateValidite) {
        this.dateValidite = dateValidite;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    @Override
    public String toString() {
        return "Devis{" +
                "id=" + id +
                ", montantEstime=" + montantEstime +
                ", dateValidite=" + dateValidite +
                ", accepte=" + accepte +
                '}';
    }
}
