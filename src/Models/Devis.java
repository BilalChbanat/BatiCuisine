package Models;

import java.time.LocalDate;

public class Devis {
    private int id;
    private double montantEstime;
    private LocalDate dateEmission;
    private boolean accepte;
    private LocalDate dateValide;
    private int projectId;

    public Devis(int id, double montantEstime, LocalDate dateEmission, boolean accepte, LocalDate dateValide, int projectId) {
        this.id = id;
        this.setMontantEstime(montantEstime);
        this.dateEmission = dateEmission;
        this.accepte = accepte;
        this.dateValide = dateValide;
        this.projectId = projectId;
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
        if (montantEstime < 0) {
            throw new IllegalArgumentException("Montant estimé ne peut pas être négatif.");
        }
        this.montantEstime = montantEstime;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public LocalDate getDateValide() {
        return dateValide;
    }

    public void setDateValide(LocalDate dateValide) {
        if (dateValide != null && dateEmission != null && dateValide.isBefore(dateEmission)) {
            throw new IllegalArgumentException("La date de validité doit être postérieure à la date d'émission.");
        }
        this.dateValide = dateValide;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
