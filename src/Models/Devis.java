package Models;

import java.time.LocalDate;

public class Devis {

    private int id;
    private double montantEstime ;
    private LocalDate datevalidite ;
    private boolean accepte;

    public Devis(int id, double montantEstime, LocalDate datevalidite, boolean accepte) {
        this.id = id;
        this.montantEstime = montantEstime;
        this.datevalidite = datevalidite;
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
        this.montantEstime = montantEstime;
    }

    public LocalDate getDatevalidite() {
        return datevalidite;
    }

    public void setDatevalidite(LocalDate datevalidite) {
        this.datevalidite = datevalidite;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    @Override
    public String toString() {
        return "\nDevis{" +
                "id=" + id +
                ", montantEstime=" + montantEstime +
                ", datevalidite=" + datevalidite +
                ", accepte=" + accepte +
                '}';
    }
}
