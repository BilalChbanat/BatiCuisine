package Models;

public class MainDoeuvre extends Composant {
    private double tauxHoraire;
    private double heuresTravail;
    private double productiviteOuvrier;

    public MainDoeuvre(int id, String name, String typeComposant, double tauxTVA,
                       double tauxHoraire, double heuresTravail, double productiviteOuvrier) {
        super(id, name, typeComposant, tauxTVA);
        setTauxHoraire(tauxHoraire); // Using setters to ensure validation
        setHeuresTravail(heuresTravail);
        setProductiviteOuvrier(productiviteOuvrier);
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        if (tauxHoraire < 0) {
            throw new IllegalArgumentException("Le taux horaire ne peut pas être négatif.");
        }
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(double heuresTravail) {
        if (heuresTravail < 0) {
            throw new IllegalArgumentException("Le nombre d'heures travaillées ne peut pas être négatif.");
        }
        this.heuresTravail = heuresTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(double productiviteOuvrier) {
        if (productiviteOuvrier <= 0) {
            throw new IllegalArgumentException("La productivité doit être supérieure à zéro.");
        }
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public String toString() {
        return "MainDoeuvre{" +
                "id=" + getId() + // Use the inherited getId() from Composant
                ", tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                ", name='" + getName() + '\'' + // Use getName() from Composant
                ", typeComposant='" + getTypeComposant() + '\'' + // From Composant
                ", tauxTVA=" + getTauxTVA() + // From Composant
                '}';
    }
}
