package Models;

public class Composant {
    private int id;
    private String name;
    private String typeComposant;
    private double tauxTVA;
    private Project project; // Add this line

    public Composant(int id, String name, String typeComposant, double tauxTVA, Project project) {
        this.id = id;
        this.name = name;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
        this.project = project; // Update constructor
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

    public Project getProject() {
        return project; // Add this getter
    }

    public void setProject(Project project) {
        this.project = project; // Add this setter
    }

    @Override
    public String toString() {
        return "Composant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", tauxTVA=" + tauxTVA +
                ", project=" + project +
                '}';
    }
}
