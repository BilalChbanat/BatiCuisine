package Classes;

public class Composant {
    private int id;
    private String name;
    private String typeComposant;
    private double tauxTVA;


    public Composant(int id, String name, String typeComposant, double tauxTVA) {
        this.id = id;
        this.name = name;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(String typeComposant) {
        this.typeComposant = typeComposant;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        this.tauxTVA = tauxTVA;
    }

    @Override
    public String toString() {
        return "\n Composant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", tauxTVA=" + tauxTVA +
                '}';
    }
}
