package Classes;

public class Material {

    private int id;
    private double countUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQualite;

    public Material(int id, double countUnitaire, double quantite, double coutTransport, double coefficientQualite) {
        this.id = id;
        this.countUnitaire = countUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCountUnitaire() {
        return countUnitaire;
    }

    public void setCountUnitaire(double countUnitaire) {
        this.countUnitaire = countUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    @Override
    public String toString() {
        return "\nMaterial{" +
                "id=" + id +
                ", countUnitaire=" + countUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                '}';
    }
}
