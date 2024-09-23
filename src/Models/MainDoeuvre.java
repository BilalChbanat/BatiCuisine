package Models;

public class MainDoeuvre extends Composant {
    private int id;
    private double tauxHoraire;
    private double heuresTravail;
    private double productiviteOuvrier;

    public MainDoeuvre(int id, String name, String typeComposant, double tauxTVA) {
        super(id, name, typeComposant, tauxTVA);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        if(tauxHoraire < 0) {
            throw new IllegalArgumentException("Le taux horaire ne peut pas être négatif.");
        }
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(double heuresTravail) {
        if(heuresTravail < 0) {
            throw new IllegalArgumentException("Le nombre d'heures travaillées ne peut pas être négatif.");
        }
        this.heuresTravail = heuresTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(double productiviteOuvrier) {
        if(productiviteOuvrier <= 0) {
            throw new IllegalArgumentException("La productivité doit être supérieure à zéro.");
        }
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public String toString() {
        return "MainDoeuvre{" +
                "id=" + id +
                ", tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                '}';
    }
}
