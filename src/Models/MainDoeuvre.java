package Models;

public class MainDoeuvre {
    private int id ;
    private double tauxHoraire ;
    private double heuresTravail ;
    private double productiviteOuvrier ;

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
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public String toString() {
        return "\nMainDoeuvre{" +
                "id=" + id +
                ", tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                '}';
    }
}
