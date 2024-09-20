package Models;

public class Material {

    private int id;
    private double coutUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQualite;

    public Material(int id, double coutUnitaire, double quantite, double coutTransport, double coefficientQualite) {
        this.id = id;
        setCoutUnitaire(coutUnitaire);
        setQuantite(quantite);
        setCoutTransport(coutTransport);
        setCoefficientQualite(coefficientQualite);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        if(coutUnitaire < 0) {
            throw new IllegalArgumentException("Le coût unitaire ne peut pas être négatif.");
        }
        this.coutUnitaire = coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        if(quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à zéro.");
        }
        this.quantite = quantite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        if(coutTransport < 0) {
            throw new IllegalArgumentException("Le coût de transport ne peut pas être négatif.");
        }
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        if(coefficientQualite < 1.0) {
            throw new IllegalArgumentException("Le coefficient de qualité doit être au moins 1.0.");
        }
        this.coefficientQualite = coefficientQualite;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                '}';
    }
}
